package com.mainclass.orderservice.command;


public class CompleteOrderCommand extends OrderCommand{

	public CompleteOrderCommand() {}
	
    public CompleteOrderCommand(String orderId) {
    	super(orderId);
    }
   
}
