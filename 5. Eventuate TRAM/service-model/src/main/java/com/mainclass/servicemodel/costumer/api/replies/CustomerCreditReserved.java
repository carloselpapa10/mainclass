package com.mainclass.servicemodel.costumer.api.replies;

public class CustomerCreditReserved implements ResultCreditResult{

	private String algo;

	public CustomerCreditReserved() {
	}
	
	public CustomerCreditReserved(String algo) {
		super();
		this.algo = algo;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}
	
	
}
