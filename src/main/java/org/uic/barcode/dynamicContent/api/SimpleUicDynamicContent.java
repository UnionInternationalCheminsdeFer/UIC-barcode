package org.uic.barcode.dynamicContent.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.uic.barcode.ticket.api.impl.SimpleExtension;
import org.uic.barcode.ticket.api.spec.IExtension;
import org.uic.barcode.ticket.api.spec.IGeoCoordinate;

public class SimpleUicDynamicContent implements IUicDynamicContent {
	
	
	protected String appId;	

	protected Date timeStamp;
	
	protected String challenge;	
	
	protected IGeoCoordinate geoCoordinate;
	
	protected IExtension dynamicContentExtension;
	
	protected List<IExtension> dynamicContentResponseList;
	
	
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}


	public IGeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(IGeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	public IExtension getDynamicContentExtension() {
		return dynamicContentExtension;
	}

	public void setDynamicContentExtension(IExtension dynamicContentExtension) {
		this.dynamicContentExtension = dynamicContentExtension;
	}


	@Override
	public Date getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Date date) {
		timeStamp = date;
		
	}

	@Override
	public IExtension getExtension() {
		return dynamicContentExtension;
	}

	@Override
	public void setExtension(IExtension extension) {
		dynamicContentExtension = extension;
	}

	@Override
	public String getChallengeString() {
		return challenge;
	}

	@Override
	public void setChallengeString(String challenge) {
		this.challenge = challenge;		
	}

	@Override
	public byte[] getPhoneIdHash() {
		return getExtensionData("phone");
	}

	@Override
	public void setPhoneIdHash(byte[] phoneIdHash) {
		if (dynamicContentResponseList == null) {
			dynamicContentResponseList = new ArrayList<IExtension>();
		}
		
		IExtension e = new SimpleExtension();
		e.setBinarydata(phoneIdHash);
		e.setId("phone");
		getDynamicContentResponseList().add(e);
	}

	@Override
	public byte[] getPassIdHash() {
		return getExtensionData("pass");
	}

	private byte[] getExtensionData(String name) {
		if (dynamicContentResponseList == null) return null;
		for (IExtension e : dynamicContentResponseList) {
			if (e.getId().equals(name)){
				return e.getBinarydata();
			}
		}
		return null;
	}

	@Override
	public void setPassIdHash(byte[] passIdHash) {
		if (dynamicContentResponseList == null) {
			dynamicContentResponseList = new ArrayList<IExtension>();
		}
		
		IExtension e = new SimpleExtension();
		e.setBinarydata(passIdHash);
		e.setId("pass");
		getDynamicContentResponseList().add(e);
	}

	@Override
	public List<IExtension> getDynamicContentResponseList() {
		if (dynamicContentResponseList == null) {
			dynamicContentResponseList = new ArrayList<IExtension>();
		}
		
		return dynamicContentResponseList;
	}

	@Override
	public void addDynamicContentResponse(IExtension challenge) {
		
		if (this.dynamicContentResponseList == null) {
			this.dynamicContentResponseList = new ArrayList<IExtension>();
		}
		
		this.dynamicContentResponseList.add(challenge);

	}
	
	
}
