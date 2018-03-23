package org.eventuate.saga.orderservice.service;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.eventuate.saga.orderservice.command.CompleteOrderCommand;
import org.eventuate.saga.orderservice.command.RejectOrderSagaCommand;
import org.eventuate.saga.orderservice.model.Order;
import org.eventuate.saga.orderservice.model.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import java.util.Optional;

@Component
public class OrderCommandHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderCommandHandler.class);

    @Autowired
    private OrderRepository orderRepository;

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel("orderService")
                .onMessage(RejectOrderSagaCommand.class, this::rejectOrder)
                .onMessage(CompleteOrderCommand.class, this::completeOrder)
                .build();
    }

    public Message rejectOrder(CommandMessage<RejectOrderSagaCommand> commandMessage) {
        log.info("received RejectOrderSagaCommand");
        RejectOrderSagaCommand command = commandMessage.getCommand();

        //orderRepository.delete(command.getOrderSagaData().getOrderId());

        log.info(String.format("order %s successfully rejected", command.getOrderSagaData().getOrderId()));
        return withSuccess();
    }

    public Message completeOrder(CommandMessage<CompleteOrderCommand> commandMessage) {
        log.info("received CompleteOrderCommand");
        CompleteOrderCommand command = commandMessage.getCommand();

        Optional<Order> order = orderRepository.findById(command.getOrderId());
        order.get().setCompleted(true);
        orderRepository.save(order.get());

        log.info(String.format("Order %s fully completed", order));
        return withSuccess();
    }

}
