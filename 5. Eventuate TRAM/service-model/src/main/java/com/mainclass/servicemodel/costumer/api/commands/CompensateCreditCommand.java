package com.mainclass.servicemodel.costumer.api.commands;

import com.mainclass.servicemodel.common.Money;

import io.eventuate.tram.commands.common.Command;

public class CompensateCreditCommand implements Command{

	private Long orderId;
	private Money orderTotal;
	private long customerId;

	public CompensateCreditCommand() {
	}
	
	public CompensateCreditCommand(Long customerId) {
		this.customerId = customerId;
	}

	public CompensateCreditCommand(Long customerId, Long orderId, Money orderTotal) {
		this.customerId = customerId;
		this.orderId = orderId;
		this.orderTotal = orderTotal;
	}

	public Money getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Money orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Long getOrderId() {

		return orderId;
	}

	public void setOrderId(Long orderId) {

		this.orderId = orderId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
}
