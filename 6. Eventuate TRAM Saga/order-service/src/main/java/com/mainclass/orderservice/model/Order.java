package com.mainclass.orderservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.mainclass.servicemodel.order.api.events.ProductInfo;

import lombok.ToString;

@ToString
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private ProductInfo productInfo;
    private String invoiceId;
    private String customerId;
    
    private boolean completed;
	
    public Order() {}
    
    public Order(ProductInfo productInfo) {
		super();
		this.productInfo = productInfo;
	}
    
    public Order(ProductInfo productInfo, String customerId) {
		super();
		this.productInfo = productInfo;
		this.customerId = customerId;
	}
    
	public Order(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
    
}