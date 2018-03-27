package com.mainclass.orderservice.command;

public class RejectOrderCommand extends OrderCommand{

	private RejectOrderCommand() {
	}
	
	public RejectOrderCommand(String orderId) {
		super(orderId);
	}
}
