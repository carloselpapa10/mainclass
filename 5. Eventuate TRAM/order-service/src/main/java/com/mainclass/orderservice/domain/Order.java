package com.mainclass.orderservice.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.mainclass.servicemodel.coreapi.order.ProductInfo;

import lombok.ToString;

@ToString
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private ProductInfo productInfo;
    private String invoiceId;
    private boolean completed;
	
    public Order() {}
    
    public Order(ProductInfo productInfo) {
		super();
		this.productInfo = productInfo;
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
    
}
