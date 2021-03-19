package org.uic.barcode.ticket.api.spec;

public interface IVatDetail {
	
	public int getCountry();
	
	public void setCountry(int country);
	
	public int getPercentage();
	
	public void setPercentage(int percentage);	
	
	public Long getAmount();
	
	public void setAmount(Long amount);
	
	public String getVatId();
	
	public void setVatId(String vatId);
	

}
