package org.uic.barcode.ticket.api.impl;


import org.uic.barcode.ticket.api.spec.IVatDetail;

public class SimpleVatDetail implements IVatDetail {
	

	public int country;
	
	public int percentage;

	public Long amount;

    public String vatId;

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getVatId() {
		return vatId;
	}

	public void setVatId(String vatId) {
		this.vatId = vatId;
	}



	
    

}
