package org.uic.barcode.ticket.api.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.logger.LoggerFactory;
import org.uic.barcode.ticket.EncodingFormatException;
import org.uic.barcode.ticket.api.spec.ICarCarriageReservation;
import org.uic.barcode.ticket.api.spec.IDocumentData;
import org.uic.barcode.ticket.api.spec.ILoadingDeckType;
import org.uic.barcode.ticket.api.spec.IPriceTypeType;
import org.uic.barcode.ticket.api.spec.IRoofRackType;
import org.uic.barcode.ticket.api.spec.IUicRailTicket;
import org.uic.barcode.ticket.api.test.testtickets.CarCarriageReservationTestTicketV3;
import org.uic.barcode.ticket.api.utils.Api2AsnEncoder;
import org.uic.barcode.ticket.api.utils.Api2OpenAsnEncoderV3;
import org.uic.barcode.ticket.api.utils.Asn2ApiDecoder;
import org.uic.barcode.ticket.api.utils.OpenAsn2ApiDecoderV3;


/**
 * The Class CarCarriageTestV1.
 * 
 * 
 * 
 */
public class CarCarriageTestV3 {
	
	   
    /** The decoder. */
    Asn2ApiDecoder decoder = new OpenAsn2ApiDecoderV3();
    
    /** The encoder. */
    Api2AsnEncoder encoder = new Api2OpenAsnEncoderV3();
    
    /** The API ticket low level encoded for case 1. */
    IUicRailTicket iTicketDecodedFromAsn1Case1 = null;
      
    
    /** The ticket decoded 1. */
    IUicRailTicket iTicketDecodedCase1 = null;
        
    byte[] encodedInTimeZone1 = null;
   
       
    TimeZone defaulttimeZone = null;
    
	/**
	 * Prepare tickets.
	 */
	@Before public void prepare() {
			
		LoggerFactory.setActivateConsoleLog(true);
		
		defaulttimeZone = TimeZone.getDefault();  	

		
	}
	
	/**
	 * clean up
	 */
	@After public void resetTimeZone() {
		TimeZone.setDefault(defaulttimeZone);
	}
	
    
	/**
	 * Test encode test tickets in UTC and decode in CET.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParseException 
	 */
	@Test public void testDelayConfirmation() throws IllegalArgumentException, IllegalAccessException, ParseException {
		
		//get tickets
		
		defaulttimeZone = TimeZone.getDefault();
    	//encode in UTC time zone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		IUicRailTicket ticketDecoded = null;
		try {
			ticketDecoded = decoder.decodeFromAsn(CarCarriageReservationTestTicketV3.getEncodingBytes());
		} catch (Exception e) {
			assert(false);
		}
		
		IDocumentData document = ticketDecoded.getDocumentData().iterator().next();
		assert(document != null);
		assert(document instanceof ICarCarriageReservation);
		
        ICarCarriageReservation c = (ICarCarriageReservation) document;
		
		assert(c.getAttachedBicycles() == 1L);
		assert(c.getAttachedSurfboards() == 2L);

		assert(c.getCarCategory() == 3L);
		assert(c.getCoach().equals("21"));
		assert(c.getPlace().equals("41"));
		assert(c.getFromStation().equals("8100001"));
		assert(c.getToStation().equals("800001"));
		assert(c.getInfoText().equals("car carriage"));
		assert(c.getLoadingDeck().equals(ILoadingDeckType.upper));
		assert(c.getLoadingListEntry() == 421L);
		assert(c.getTrain().equals("123"));
		assert(c.getNumberPlate().equals("AD-DE-123"));
		assert(c.getTrailerPlate().equals("DX-AB-123"));
		assert(c.isTextileRoof() == false);
		assert(c.getServiceBrand().getServiceBrandAbbreviation().equals("AZ"));
		assert(c.getServiceBrand().getServiceBrandDescription().equals("special train"));
		assert(c.getServiceBrand().getServiceBrand() == 100L);
		assert(c.getRoofRackType().equals(IRoofRackType.bicycleRack));
		assert(c.getTariff() != null);
		assert(c.getRoofRackHeight() == 20L);
		assert(c.getPriceType().equals(IPriceTypeType.travelPrice));
		assert(c.getReference().equals("810123456789"));
		assert(c.getVatDetails() != null);
		assert(c.getCarriers().contains("1080"));
		assert(c.getCarriers().contains("1181"));
		assert(c.getPrice() == 12345L);
		
		DateFormat dateFormat = new SimpleDateFormat( "yyyy.MM.dd-HH:mm" );
		String pdb = dateFormat.format(c.getBeginLoading());
		assert(pdb.equals("2018.01.11-00:00"));
		
		String pde = dateFormat.format(c.getEndLoading());
		assert(pde.equals("2018.01.11-08:20"));
		
    	
        byte[] encoded = null;
		try {
			encoded = encoder.encode(ticketDecoded);
		} catch (EncodingFormatException e) {
			assert(false);
		}
		
		
		IUicRailTicket ticketDecoded2 = null;
		try {
			ticketDecoded2 = decoder.decodeFromAsn(encoded);
		} catch (Exception e) {
			assert(false);
		}
				
		assert (ticketDecoded2 != null);
		
		String hex1 = UperEncoder.hexStringFromBytes(encoded);
		String hex2 = CarCarriageReservationTestTicketV3.getEncodingHex();
	    assert(hex1.equals(hex2));
		
		
		
              
	    TimeZone.setDefault(defaulttimeZone);
    }    
		

	
}
