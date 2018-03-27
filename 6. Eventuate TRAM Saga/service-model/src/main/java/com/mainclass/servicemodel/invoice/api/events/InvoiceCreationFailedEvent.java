package com.mainclass.servicemodel.invoice.api.events;

import io.eventuate.tram.events.common.DomainEvent;

public class InvoiceCreationFailedEvent implements DomainEvent{

	private String orderId;

	
	public InvoiceCreationFailedEvent() {
	}
	
	public InvoiceCreationFailedEvent(String orderId) {
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
