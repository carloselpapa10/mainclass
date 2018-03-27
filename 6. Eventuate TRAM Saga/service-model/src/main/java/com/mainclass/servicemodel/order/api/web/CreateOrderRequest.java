package com.mainclass.servicemodel.order.api.web;

import com.mainclass.servicemodel.order.api.events.ProductInfo;

public class CreateOrderRequest {

	private ProductInfo productInfo;
	private String customerId;
	
	public CreateOrderRequest() {}
	
	public CreateOrderRequest(ProductInfo productInfo, String customerId) {
		super();
		this.productInfo = productInfo;
		this.customerId = customerId;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
