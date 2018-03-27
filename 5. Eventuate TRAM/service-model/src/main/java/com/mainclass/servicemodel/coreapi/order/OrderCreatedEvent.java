package com.mainclass.servicemodel.coreapi.order;

import io.eventuate.tram.events.common.DomainEvent;

public class OrderCreatedEvent implements DomainEvent{

	private String orderId;
	private ProductInfo productInfo;
	
	public OrderCreatedEvent() {
		super();
	}

	public OrderCreatedEvent(String orderId) {
		super();
		this.orderId = orderId;
	}

	public OrderCreatedEvent(String orderId, ProductInfo productInfo) {
		super();
		this.orderId = orderId;
		this.productInfo = productInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
}
