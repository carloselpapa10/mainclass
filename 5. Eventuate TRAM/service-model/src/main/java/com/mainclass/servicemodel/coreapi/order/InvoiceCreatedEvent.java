package com.mainclass.servicemodel.coreapi.order;

import io.eventuate.tram.events.common.DomainEvent;

public class InvoiceCreatedEvent implements DomainEvent{

	private String orderId;
	private String invoiceId;

	public InvoiceCreatedEvent() {
	}
	
	public InvoiceCreatedEvent(String orderId, String invoiceId) {
		super();
		this.orderId = orderId;
		this.invoiceId = invoiceId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
}
