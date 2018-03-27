package com.mainclass.invoiceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.invoiceservice.model.Invoice;
import com.mainclass.invoiceservice.model.InvoiceRepository;
import com.mainclass.servicemodel.order.api.events.ProductInfo;


import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Transactional
public class InvoiceService {

	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
    private InvoiceRepository invoiceRepository;
	
	public Invoice createInvoice(String orderId, ProductInfo productInfo) {
		log.info("computing invoice for order - " + orderId);
		
		return invoiceRepository.save(new Invoice(orderId, productInfo.getProductId()));
	}
		
	public void deleteInvoice(String orderId) {
		log.info("deleting invoice for order - " + orderId);
		
		Invoice invoice = invoiceRepository.findByOrderId(orderId);
		invoiceRepository.delete(invoice);
	}
	
}
