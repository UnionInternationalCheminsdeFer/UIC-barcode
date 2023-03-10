package org.uic.barcode.test.utils;

import org.uic.barcode.ssbFrame.SsbClass;
import org.uic.barcode.ssbFrame.SsbCommonTicketPart;
import org.uic.barcode.ssbFrame.SsbFrame;
import org.uic.barcode.ssbFrame.SsbGroup;
import org.uic.barcode.ssbFrame.SsbHeader;
import org.uic.barcode.ssbFrame.SsbNonReservation;
import org.uic.barcode.ssbFrame.SsbNonUic;
import org.uic.barcode.ssbFrame.SsbPass;
import org.uic.barcode.ssbFrame.SsbReservation;
import org.uic.barcode.ssbFrame.SsbStationCodeTable;
import org.uic.barcode.ssbFrame.SsbStations;
import org.uic.barcode.ssbFrame.SsbTicketType;

public class SsbTicketFactory {
	
	public static SsbFrame getSsbPass() {
		
		SsbFrame ssbPass = new SsbFrame();
		
		ssbPass.setHeader(new SsbHeader());
		ssbPass.getHeader().setIssuer(4711);
		ssbPass.getHeader().setTicketType(SsbTicketType.UIC_4_RPT);
		ssbPass.getHeader().setVersion(1);
		
		
		ssbPass.setPassData(new SsbPass());
		ssbPass.getPassData().setClassCode(SsbClass.FIRST);
		ssbPass.getPassData().setCountry_1(10);
		ssbPass.getPassData().setCountry_2(12);
		ssbPass.getPassData().setDay(1);
		ssbPass.getPassData().setFirstDayOfValidity(120);
		ssbPass.getPassData().setHasSecondPage(false);
		ssbPass.getPassData().setInfoCode(12);
		ssbPass.getPassData().setMaximumValidityDuration(2);
		ssbPass.getPassData().setNumberOfAdults(2);
		ssbPass.getPassData().setNumberOfChildren(3);
		ssbPass.getPassData().setNumberOfTravels(3);
		ssbPass.getPassData().setPassSubType(1);
		ssbPass.getPassData().setSpecimen(true);
		ssbPass.getPassData().setText("Test");
		ssbPass.getPassData().setTicketNumber("SKCTS86");
		ssbPass.getPassData().setYear(3);
		
		return ssbPass;
	}
	
	public static SsbFrame getSsbGroup() {
		
		SsbFrame ssb = new SsbFrame();
		
		ssb.setHeader(new SsbHeader());
		ssb.getHeader().setIssuer(4711);
		ssb.getHeader().setTicketType(SsbTicketType.UIC_3_GRP);
		ssb.getHeader().setVersion(1);
		
		
		ssb.setGroupData(new SsbGroup());
		ssb.getGroupData().setClassCode(SsbClass.FIRST);
		ssb.getGroupData().setCounterMarkNumber(1);
		ssb.getGroupData().setDay(1);
		ssb.getGroupData().setFirstDayOfValidity(10);
		ssb.getGroupData().setFirstDayOfValidity(120);
		ssb.getGroupData().setGroupName("GroupName");
		ssb.getGroupData().setInfoCode(12);
		ssb.getGroupData().setLastDayOfValidity(3);
		ssb.getGroupData().setNumberOfAdults(2);
		ssb.getGroupData().setNumberOfChildren(3);
		ssb.getGroupData().setReturnJourney(false);
		ssb.getGroupData().setSpecimen(true);
		ssb.getGroupData().setText("Test");
		ssb.getGroupData().setTicketNumber("SKCTS86");
		ssb.getGroupData().setYear(3);
		
		ssb.getGroupData().getStations().setArrivalStationCode("8012345");
		ssb.getGroupData().getStations().setDepartureStationCode("8054321");
		ssb.getGroupData().getStations().setCodeTable(SsbStationCodeTable.NRT);
		
		return ssb;
	}
	
	public static SsbFrame getSsbNonReservation() {
		
		SsbFrame ssb = new SsbFrame();
		
		ssb.setHeader(new SsbHeader());
		ssb.getHeader().setIssuer(4711);
		ssb.getHeader().setTicketType(SsbTicketType.UIC_2_NRT);
		ssb.getHeader().setVersion(1);
		
		
		ssb.setNonReservationData(new SsbNonReservation());
		ssb.getNonReservationData().setClassCode(SsbClass.FIRST);
		ssb.getNonReservationData().setDay(1);
		ssb.getNonReservationData().setFirstDayOfValidity(10);
		ssb.getNonReservationData().setFirstDayOfValidity(120);
		ssb.getNonReservationData().setInfoCode(12);
		ssb.getNonReservationData().setLastDayOfValidity(3);
		ssb.getNonReservationData().setNumberOfAdults(2);
		ssb.getNonReservationData().setNumberOfChildren(3);
		ssb.getNonReservationData().setReturnJourney(false);
		ssb.getNonReservationData().setSpecimen(true);
		ssb.getNonReservationData().setText("Test");
		ssb.getNonReservationData().setTicketNumber("SKCTS86");
		ssb.getNonReservationData().setYear(3);
		
		
		ssb.getNonReservationData().getStations().setArrivalStationCode("8012345");
		ssb.getNonReservationData().getStations().setDepartureStationCode("8054321");
		ssb.getNonReservationData().getStations().setCodeTable(SsbStationCodeTable.NRT);
		
		return ssb;
	}
	
	public static SsbFrame getSsbReservation() {
		
		SsbFrame ssb = new SsbFrame();
		
		ssb.setHeader(new SsbHeader());
		ssb.getHeader().setIssuer(4711);
		ssb.getHeader().setTicketType(SsbTicketType.UIC_1_IRT_RES_BOA);
		ssb.getHeader().setVersion(1);
		
		
		ssb.setReservationData(new SsbReservation());
		ssb.getReservationData().setClassCode(SsbClass.FIRST);
		ssb.getReservationData().setDay(1);
		ssb.getReservationData().setCoach(123);
		ssb.getReservationData().setDepartureDate(120);
		ssb.getReservationData().setDepartureTime(500);
		ssb.getReservationData().setOverbooking(false);
		ssb.getReservationData().setNumberOfAdults(2);
		ssb.getReservationData().setNumberOfChildren(3);
		ssb.getReservationData().setPlace("05B");
		ssb.getReservationData().setTicketSubType(2);
		ssb.getReservationData().setTrain("1234B");	
		
		ssb.getReservationData().setSpecimen(true);
		ssb.getReservationData().setText("Test");
		ssb.getReservationData().setTicketNumber("SKCTS86");
		ssb.getReservationData().setYear(3);
		
		
		ssb.getReservationData().getStations().setArrivalStationCode("8012345");
		ssb.getReservationData().getStations().setDepartureStationCode("8054321");
		ssb.getReservationData().getStations().setCodeTable(SsbStationCodeTable.NRT);
		
		return ssb;
	}
	
	public static SsbFrame getSsbNonUic() {
		
		SsbFrame ssb = new SsbFrame();
		
		ssb.setHeader(new SsbHeader());
		ssb.getHeader().setIssuer(4711);
		ssb.getHeader().setTicketType(SsbTicketType.NONUIC_23_BILATERAL);
		ssb.getHeader().setVersion(1);
		
		
		ssb.setNonUicData(new SsbNonUic());
		ssb.getNonUicData().setOpenData("TestData".getBytes());

		
		return ssb;
	}

	public static void compareStations(SsbStations stations, SsbStations stations2) {
		
		assert (stations.getCodeTable().equals(stations2.getCodeTable()));
		
		assert (stations.getArrivalStationCode().equals(stations2.getArrivalStationCode()));
		
		assert (stations.getDepartureStationCode().equals(stations2.getDepartureStationCode()));
		
	}
	
	public static void compareCommonTicketPart(SsbCommonTicketPart part, SsbCommonTicketPart part2) {

        assert(part.isSpecimen() == part2.isSpecimen());
        assert(part.getDay() == part2.getDay());
        assert(part.getNumberOfAdults() == part2.getNumberOfAdults());
        assert(part.getNumberOfChildren() == part2.getNumberOfChildren());
        assert(part.getTicketNumber().equals(part2.getTicketNumber()));
        assert(part.getYear() == part2.getYear());
		
	}
	
	
	

}
