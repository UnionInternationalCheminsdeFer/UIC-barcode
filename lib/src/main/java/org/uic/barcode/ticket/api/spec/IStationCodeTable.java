package org.uic.barcode.ticket.api.spec;

	public enum IStationCodeTable {
	
		stationUIC("stationUIC"),
		stationUICReservation("stationUICReservation"),
		stationERA("stationERA"),
		localCarrierStationCodeTable("localCarrierStationCodeTable"),
		proprietaryIssuerStationCodeTable("proprietaryIssuerStationCodeTable");
		
		public String text;

		IStationCodeTable(String text) {
			this.text = text;
		}
		
		public String toString(){
			return text;
		}		
		
		
 }



