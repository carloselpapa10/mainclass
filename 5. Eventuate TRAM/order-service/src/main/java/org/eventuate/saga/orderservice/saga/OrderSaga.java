package org.eventuate.saga.orderservice.saga;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.eventuate.saga.orderservice.command.CompleteOrderCommand;
import org.eventuate.saga.orderservice.command.RejectOrderSagaCommand;
import org.eventuate.saga.orderservice.model.Order;
import org.eventuate.saga.orderservice.model.OrderRepository;
import org.learn.eventuate.coreapi.CompensateInvoiceCommand;
import org.learn.eventuate.coreapi.CompensateShipmentCommand;
import org.learn.eventuate.coreapi.InvoiceInfo;
import org.learn.eventuate.coreapi.ParticipantFailureInfo;
import org.learn.eventuate.coreapi.RequestInvoiceCommand;
import org.learn.eventuate.coreapi.RequestShipmentCommand;
import org.learn.eventuate.coreapi.ShipmentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

import java.util.Optional;

@Component
public class OrderSaga implements SimpleSaga<OrderSagaData> {

    private static final Logger log = LoggerFactory.getLogger(OrderSaga.class);

    @Autowired
    private OrderRepository orderRepository;

    private SagaDefinition<OrderSagaData> sagaDefinition =
            step()
              .withCompensation(this::rejectSaga)          
            .step()
              .invokeParticipant(this::requestInvoice)
                .onReply(InvoiceInfo.class, this::invoiceReply)
                .onReply(ParticipantFailureInfo.class, this::invoiceFailure)
              .withCompensation(this::invoiceCompensate)
            .step()
              .invokeParticipant(this::finishOrder)
            .build();

    @Override
    public SagaDefinition<OrderSagaData> getSagaDefinition() {
        return sagaDefinition;
    }

    private CommandWithDestination rejectSaga(OrderSagaData orderSagaData) {
        log.info("rejectSaga()");

        return send(new RejectOrderSagaCommand(orderSagaData))
                .to("orderService")
                .build();
    }
 

    private CommandWithDestination requestInvoice(OrderSagaData orderSagaData) {
        log.info("requestInvoice()");

        return send(new RequestInvoiceCommand(orderSagaData.getOrderId(), orderSagaData.getProductInfo()))
                .to("invoiceService")
                .build();
    }

    private void invoiceReply(OrderSagaData orderSagaData, InvoiceInfo invoiceInfo) {
        log.info("invoiceReply()");

        Optional<Order>  order = orderRepository.findById(orderSagaData.getOrderId());
        order.get().setInvoiceId(invoiceInfo.getInvoiceId());
        orderRepository.save(order.get());
        log.info("order updated with invoice - " + order);
    }

    private CommandWithDestination invoiceCompensate(OrderSagaData orderSagaData) {
        log.info("invoiceCompensate()");

        return send(new CompensateInvoiceCommand(orderSagaData.getOrderId()))
                .to("invoiceService")
                .build();
    }

    private void invoiceFailure(OrderSagaData orderSagaData, ParticipantFailureInfo failureInfo) {
        log.info(String.format("invoiceFailure() for %s with cause '%s'", orderSagaData.getOrderId(), failureInfo.getCause()));
    }

    private CommandWithDestination finishOrder(OrderSagaData orderSagaData) {
        log.info("finishOrder()");
        return send(new CompleteOrderCommand(orderSagaData.getOrderId()))
               .to("orderService")
               .build();
    }
}
