package com.mainclass.orderservice.commandhandlers;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mainclass.orderservice.command.CompleteOrderCommand;
import com.mainclass.orderservice.command.RejectOrderCommand;
import com.mainclass.orderservice.model.Order;
import com.mainclass.orderservice.model.OrderRepository;
import com.mainclass.orderservice.saga.createorder.CreateOrderSaga;
import com.mainclass.orderservice.service.OrderService;
import com.mainclass.servicemodel.common.Constants;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class OrderServiceCommandHandlers {

private static final Logger log = LoggerFactory.getLogger(CreateOrderSaga.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public CommandHandlers commandHandlers() {
		return SagaCommandHandlersBuilder
				.fromChannel(Constants.ORDER_SERVICE)				
				.onMessage(CompleteOrderCommand.class, this::completeOrder)
				.onMessage(RejectOrderCommand.class, this::rejectOrder)
				.build();
	}
	
	public Message completeOrder(CommandMessage<CompleteOrderCommand> cm) {
		log.info("completeOrder() - OrderCommandHandlers");
		
		CompleteOrderCommand command = cm.getCommand();
		Order order = orderService.findOrder(command.getOrderId());
		
		if(order==null) {
			return withFailure();
		}
		
		log.info("order completed successfully. orderId= "+order.getId());
		order.setCompleted(true);
		orderRepository.save(order);
		
		return withSuccess();
	}
	
	public Message rejectOrder(CommandMessage<RejectOrderCommand> cm) {
		log.info("rejectOrder() - OrderCommandHandlers");
		
		RejectOrderCommand command = cm.getCommand();
		Order order = orderService.findOrder(command.getOrderId());
		
		if(order==null) {
			return withFailure();
		}
		
		log.info("order rejected successfully. orderId= "+order.getId());
		order.setCompleted(false);
		orderRepository.save(order);
		return withSuccess();
	}
}
