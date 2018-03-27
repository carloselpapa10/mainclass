package com.mainclass.orderservice.command;

import io.eventuate.tram.commands.common.Command;

public class OrderCommand implements Command{

	private String orderId;

	public OrderCommand() {}
	
	public OrderCommand(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
