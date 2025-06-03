package org.uic.barcode.ticket.api.test.testtickets;

import java.util.List;

import org.uic.barcode.asn1.datatypes.Asn1BigInteger;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringIA5;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfStringUTF8;
import org.uic.barcode.asn1.datatypesimpl.SequenceOfUnrestrictedLong;
import org.uic.barcode.asn1.uper.UperEncoder;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv3.TravelerType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfRegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfRegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv3.DeltaCoordinates;
import org.uic.barcode.ticket.api.asn.omv3.BerthDetailData;
import org.uic.barcode.ticket.api.asn.omv3.BerthTypeType;
import org.uic.barcode.ticket.api.asn.omv3.BoardingOrArrivalType;
import org.uic.barcode.ticket.api.asn.omv3.CarCarriageReservationData;
import org.uic.barcode.ticket.api.asn.omv3.CardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.CodeTableType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentDetailsType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentGenderType;
import org.uic.barcode.ticket.api.asn.omv3.CompartmentPositionType;
import org.uic.barcode.ticket.api.asn.omv3.ConfirmationTypeType;
import org.uic.barcode.ticket.api.asn.omv3.ControlData;
import org.uic.barcode.ticket.api.asn.omv3.CountermarkData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerCardData;
import org.uic.barcode.ticket.api.asn.omv3.CustomerStatusType;
import org.uic.barcode.ticket.api.asn.omv3.DelayConfirmation;
import org.uic.barcode.ticket.api.asn.omv3.DocumentData;
import org.uic.barcode.ticket.api.asn.omv3.ExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.FIPTicketData;
import org.uic.barcode.ticket.api.asn.omv3.GenderType;
import org.uic.barcode.ticket.api.asn.omv3.GeoCoordinateSystemType;
import org.uic.barcode.ticket.api.asn.omv3.GeoCoordinateType;
import org.uic.barcode.ticket.api.asn.omv3.GeoUnitType;
import org.uic.barcode.ticket.api.asn.omv3.HemisphereLatitudeType;
import org.uic.barcode.ticket.api.asn.omv3.HemisphereLongitudeType;
import org.uic.barcode.ticket.api.asn.omv3.IncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.IssuingData;
import org.uic.barcode.ticket.api.asn.omv3.LineType;
import org.uic.barcode.ticket.api.asn.omv3.LinkMode;
import org.uic.barcode.ticket.api.asn.omv3.LoadingDeckType;
import org.uic.barcode.ticket.api.asn.omv3.LuggageRestrictionType;
import org.uic.barcode.ticket.api.asn.omv3.OpenTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ParkingGroundData;
import org.uic.barcode.ticket.api.asn.omv3.PassData;
import org.uic.barcode.ticket.api.asn.omv3.PassengerType;
import org.uic.barcode.ticket.api.asn.omv3.PlacesType;
import org.uic.barcode.ticket.api.asn.omv3.PolygoneType;
import org.uic.barcode.ticket.api.asn.omv3.PriceTypeType;
import org.uic.barcode.ticket.api.asn.omv3.RegionalValidityType;
import org.uic.barcode.ticket.api.asn.omv3.RegisteredLuggageType;
import org.uic.barcode.ticket.api.asn.omv3.ReservationData;
import org.uic.barcode.ticket.api.asn.omv3.ReturnRouteDescriptionType;
import org.uic.barcode.ticket.api.asn.omv3.RoofRackType;
import org.uic.barcode.ticket.api.asn.omv3.RouteSectionType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfActivatedDays;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfBerthDetailData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCardReferenceType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCarrierNum;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfCountries;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfDocumentData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfExtensionData;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfIncludedOpenTicketType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfPlaceNum;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfServiceBrands;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTariffType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTimeRangeType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTransportTypes;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerId;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfTravelerType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfVatDetail;
import org.uic.barcode.ticket.api.asn.omv3.SequenceOfViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.SeriesDetailType;
import org.uic.barcode.ticket.api.asn.omv3.ServiceType;
import org.uic.barcode.ticket.api.asn.omv3.StationPassageData;
import org.uic.barcode.ticket.api.asn.omv3.TariffType;
import org.uic.barcode.ticket.api.asn.omv3.TicketDetailData;
import org.uic.barcode.ticket.api.asn.omv3.TicketLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TicketType;
import org.uic.barcode.ticket.api.asn.omv3.TimeRangeType;
import org.uic.barcode.ticket.api.asn.omv3.TokenType;
import org.uic.barcode.ticket.api.asn.omv3.TrainLinkType;
import org.uic.barcode.ticket.api.asn.omv3.TrainValidityType;
import org.uic.barcode.ticket.api.asn.omv3.TravelClassType;
import org.uic.barcode.ticket.api.asn.omv3.TravelerData;
import org.uic.barcode.ticket.api.asn.omv3.UicRailTicketData;
import org.uic.barcode.ticket.api.asn.omv3.ValidityPeriodDetailType;
import org.uic.barcode.ticket.api.asn.omv3.ValidityPeriodType;
import org.uic.barcode.ticket.api.asn.omv3.VatDetailType;
import org.uic.barcode.ticket.api.asn.omv3.ViaStationType;
import org.uic.barcode.ticket.api.asn.omv3.VoucherData;
import org.uic.barcode.ticket.api.asn.omv3.ZoneType;

public class AllElementsTestTicketV3 {
		
	
		/*
 	     
value UicRailTicketData ::=  {     
       issuingDetail {
           securityProviderNum 1,
           securityProviderIA5 "1",
           issuerNum         32000,
           issuerIA5         "1",
           issuingYear       2018,
           issuingDay        1,
           issuingTime       600,
           issuerName        "name",
           specimen          TRUE,
           securePaperTicket FALSE,
           activated         TRUE,
           currency          "SRF",
           currencyFract     3,  
           issuerPNR         "issuerTestPNR",
           extension         { extensionId "1", extensionData '82DA'H },	 
           issuedOnTrainNum  123,
           issuedOnTrainIA5  "123",      
           issuedOnLine      12,
           pointOfSale       {
                geoUnit             microDegree,
                coordinateSystem    wgs84
                hemisphereLongitude north,
                hemisphereLatitude  east,
                longitude           12345,
                latitude            56789,
                accuracy            microDegree
           }
       },
       travelerDetail{
            traveler {
               {
	               	firstName      "John",
	       	     	secondName     "Little",
	       	     	lastName       "Dow",
	              	idCard         "12345",
	              	passportId     "JDTS",
	              	title          "PhD",
	              	gender         male,
	              	customerIdIA5  "DZE5gT",
	              	customerIdNum  12345,
		            yearOfBirth	   1901,     										
		            monthOfBirth   12, 
		            dayOfBirthInMonth 31,
		            ticketHolder   TRUE,
		            passengerType  senior,
		            passengerWithReducedMobility FALSE,
		            countryOfResidence 101,
		            countryOfPassport  102,
		            countryOfIdCard    103,
	       		    status  {
	       		       {customerStatus 1, customerStatusDescr "senior" }
	       		    }
               }
            },
            preferredLanguage "EN",
            groupName "myGroup"
       },
       transportDocument {
            {
		      ticket reservation : {
				    trainNum			 12345, 						
				    trainIA5       		 "12345", 	     						
				    departureDate  		 2, 
		            referenceIA5         "810123456789",	
		            referenceNum         80123456789,			     		        															
    			    productOwnerNum		 23456,    
				    productOwnerIA5		 "23456",    
				    productIdNum		 65535,    
				    productIdIA5		 "123456", 
				    serviceBrand    	 12,
				    serviceBrandAbrUTF8  "TGV",      					   	
				    serviceBrandNameUTF8 "Lyria",     					    
				    service         	 couchette,		
				    stationCodeTable     stationUIC,
		            fromStationNum       8100001,
		            fromStationIA5       "8100001",
		            toStationNum         8000002,
		            toStationIA5         "8100002",
				    fromStationNameUTF8  "A-STATION", 
				    toStationNameUTF8    "B-STATION", 
				    departureTime 		 1439,             
				    departureUTCOffset   -60,  
				    arrivalDate		     20,  			
				    arrivalTime		     0,  
				    arrivalUTCOffset     10,   
				    carrierNum          {1080, 1181},
				    carrierIA5          {"1080", "1181"},
				    classCode			first,
				    serviceLevel		"A",   
				    places {
				      coach             "31A",
				      placeString       "31-47",
				      placeDescription  "Window",
				      placeIA5          {"31A", "31B"}, 
				      placeNum          {31, 32}
				    },
   			        additionalPlaces    {
				      coach             "31A",
				      placeString       "31-47",
				      placeDescription  "Window",
				      placeIA5          {"31A", "31B"}, 
				      placeNum          {31, 32}
				    },    
					bicyclePlaces		{
				      coach             "31A",
				      placeString       "31-47",
				      placeDescription  "Window",
				      placeIA5          {"31A", "31B"}, 
				      placeNum          {31, 32}
				    },  
					compartmentDetails  {
				    	coachType				1,    
				    	compartmentType			99,    
				    	specialAllocation		50,   			  	
				    	coachTypeDescr			"xwz",    
				    	compartmentTypeDescr	"xwz",			
				    	specialAllocationDescr	"xwz", 		
				    	position                upperLevel 	
					},
				    numberOfOverbooked	        200,	      
				    berth {
				       {
							berthType   		single,
							numberOfBerths 		999,
							gender 				female			       
				       }
				    },		
		            tariff {
		              {
		              	numberOfPassengers 1,
		              	passengerType      senior,
		              	ageBelow           64,
		              	ageAbove           60,
		              	travelerid         { 1 },
		              	restrictedToCountryOfResidence FALSE,
                        restrictedToRouteSection {
					       stationCodeTable	stationERA,              
					       fromStationNum  		123,
					       fromStationIA5      	"123",
					       toStationNum      	234,     					                
					       toStationIA5        	"234", 				                 
					       fromStationNameUTF8 	"A",    
					       toStationNameUTF8   	"B"
					    },
                        seriesDataDetails {
						   supplyingCarrier    	12345,		
						   offerIdentification 	99,  		
						   series  				23456                     
                        },
		              	tariffIdNum        72,
		              	tariffIdIA5        "72",
		              	tariffDesc         "Leasure Fare",
		              	reductionCard            {
		              	   { 
				               cardIssuerNum            1234,
				               cardIssuerIA5            "1234",
				               cardIdNum                5678,
				               cardIdIA5                "5678",
				               cardName                 "testcard",
				               cardType                 123,
				               leadingCardIdNum	        3456,                                     
					           leadingCardIdIA5   	    "3456",
				               trailingCardIdNum        100,
				               trailingCardIdIA5        "100"
			               }
			            }
		              }
		            },
		            priceType travelPrice,
		            price 12345,
		            vatDetail {
		              {
		                country 80,
		                percentage 70,
		                amount 10,
		                vatId "IUDGTE"
		              }
		            },
		            typeOfSupplement	9,				
					numberOfSupplements	2,
				    luggage {
				          maxHandLuggagePieces    2,
				          maxNonHandLuggagePieces 1,
				          registeredLuggage {
				            {
				              registrationId "IODHUV",
				              maxWeight      20,
				              maxSize        100
				            },
				            {
				              registrationId "XXDHUV",
				              maxWeight      21,
				              maxSize        101
				            }
				          }
				    },
				    infoText	"reservation",
				    extension   {extensionId "1", extensionData '82DA'H}   		      
		      }
		    },
		    {
		      token {
		        tokenProviderNum  123, 
		        tokenProviderIA5 "VDV", 
		        tokenSpecification "TEST",
		        token            '82DA'H },
		      ticket carCarriageReservation : {
		        trainNum            123,
		        trainIA5            "123",
		        beginLoadingDate    10,
		        beginLoadingTime    0,
		        endLoadingTime      500,
		        loadingUTCOffset    30,
		        referenceIA5        "810123456789",
		        referenceNum        810123456789,
   			    productOwnerNum		 23456,    
				productOwnerIA5		 "23456",    
				productIdNum		 65535,        
				productIdIA5		 "123456", 		        
		        serviceBrand        100,
		        serviceBrandAbrUTF8 "AZ",
		        serviceBrandNameUTF8 "special train",
		        stationCodeTable    stationERA,
		        fromStationNum       8100001,
		        fromStationIA5       "8100001",
		        toStationNum         8000002,
		        toStationIA5         "8100002",
				fromStationNameUTF8  "A-STATION", 
				toStationNameUTF8    "B-STATION", 
		        coach "21",
		        place "41",
				compartmentDetails  {
				    	coachType				1,    
				    	compartmentType			99,    
				    	specialAllocation		50,   				    	
				    	coachTypeDescr			"xwz",    
				    	compartmentTypeDescr	"xwz",				
				    	specialAllocationDescr	"xwz", 				
				    	position                upperLevel 	
				},		        
		        numberPlate          "AD-DE-123",
		        trailerPlate         "DX-AB-123",
		        carCategory          3,
		        boatCategory         5,
		        textileRoof          FALSE,
		        roofRackType         bicycleRack,
		        roofRackHeight       20,
		        attachedBoats        2,
		        attachedBicycles     1,
		        attachedSurfboards   2,
		        loadingListEntry     421,
		        loadingDeck          upper,
		        carrierNum { 1080, 1181 },
		        carrierIA5 { "1080", "1181" },
		        tariff {
		          numberOfPassengers 1,
		          restrictedToCountryOfResidence FALSE,
		          tariffIdNum 72,
		          tariffDesc "Large Car Full Fare"
		        },
		        priceType          travelPrice,
		        price              12345,
		        vatDetail {
		          {
		            country 80,
		            percentage 70,
		            amount 10,
		            vatId "IUDGTE"
		          }
		        },
		        infoText "car carriage",
		        extension {extensionId "1", extensionData '82DA'H}   		       
              }
           },
           {
		      ticket openTicket : {
		      	  referenceNum         810123456789,
		          referenceIA5         "810123456789",
		          productOwnerNum	   23456,    
				  productOwnerIA5	   "23456",    
				  productIdNum		   65535,        
				  productIdIA5		   "123456", 
				  extIssuerId          12,
				  issuerAutorizationId 13,	
				  returnIncluded       FALSE,	      
		          stationCodeTable     stationERA,
		          fromStationNum       8100001,
		          fromStationIA5       "8100001",
		          toStationNum         8000002,
		          toStationIA5         "8100002",
				  fromStationNameUTF8  "A-STATION", 
				  toStationNameUTF8    "B-STATION", 
				  validRegionDesc      "From A to B via C",		      
		          validRegion 
			  	       {
				  	      viaStations { 
				  	          route {
				   		        { 
				   		        	stationNum 123455,
				   		        	stationIA5 "123455",
				   		        	border FALSE 
				   		        },
				   		        { stationNum 123456, border FALSE },
				                { alternativeRoutes {
				                    { route {  {stationNum 23455, border FALSE},{stationNum 23456, border FALSE }}, border FALSE  },
				                    { route {  {stationNum 3455, border FALSE },{stationNum 3456, border FALSE }},  border FALSE  }
				                  },
				                  border FALSE 
				                },
					    	    { stationNum 123457, border FALSE }
					          },
					          border FALSE,
					    	  seriesId 999,
					    	  routeId 21,
					    	  includedServiceBrands  { 108, 118 },
                              excludedServiceBrands  { 108, 118 }
					      },
				          zones { 
				            carrierNum             1080,
			                carrierIA5             "1080",
				            stationCodeTable       stationERA,
				            entryStationNum        1234,
				            entryStationIA5        "1234",
				            terminatingStationNum  2345,
				            terminatingStationIA5  "2345",
				            city                   123456,
				            zoneId                 {100,200},
				            binaryZoneId           '82DA'H,
				            nutsCode               "DE4711" 
				         },
				         lines { 
				            carrierNum             1080,
			                carrierIA5             "1080",
	                        lineId                 {100,200},
				            stationCodeTable       stationERA,
				            entryStationNum        1234,
				            entryStationIA5        "1234",
				            terminatingStationNum  2345,
				            terminatingStationIA5  "2345",
				            city                   123456
				         },
				         trainLink { 
						    trainNum			 12345, 						
						    trainIA5       		 "12345", 	     						
						    travelDate  		 2, 
						    departureTime 		 1439,             
						    departureUTCOffset   -60,  	
				            fromStationNum       8100001,
				            fromStationIA5       "8100001",
				            toStationNum         8000002,
				            toStationIA5         "8100002",
						    fromStationNameUTF8  "A-STATION", 
						    toStationNameUTF8    "B-STATION"
				         },
				         polygone { 
							firstEdge {
                               longitude           12345,
                               latitude            56789							
							}
							edges {
							  {longitude 12345, latitude 56789 },
							  {longitude 12345, latitude 56789 }							
							}
				         }			       	
				  },
				  returnDescription     {
		             fromStationNum       8100001,
		             fromStationIA5       "8100001",
		             toStationNum         8000002,
		             toStationIA5         "8100002",
				     fromStationNameUTF8  "A-STATION", 
				     toStationNameUTF8    "B-STATION", 	
				     validReturnRegionDesc "return",
				     validReturnRegion 
                       {
				         zones { 
				            carrierNum           1080,
			                carrierIA5           "1080",
				            stationCodeTable     stationERA,
				            zoneId               {100,200}
				         }				     
				     }
				  },
				  validFromDay	         700, 
				  validFromTime	         0,    
				  validFromUTCOffset     60,   
				  validUntilDay	         370,     	
				  validUntilTime         1439,       		
				  validUntilUTCOffset    10,   
				  activatedDay           { 1 , 2 },
				  classCode              first
   	    	      serviceLevel           "A",
   	    	      carrierNum             { 1080, 1181 },
		          carrierIA5             { "1080", "1181" },
                  includedServiceBrands  { 108, 118 },
                  excludedServiceBrands  { 108, 118 },
		          tariffs { 
		                {
		                  numberOfPassengers 1,
		                  restrictedToCountryOfResidence FALSE,
		                  tariffIdNum 72,
		                  tariffDesc "Large Car Full Fare"
		                }
		          },
		          price              12345,	    	          
	    	      vatDetail {{ country 80, percentage 70 }},
	    	      infoText          "openTicketInfo"
	    	      includedAddOns { 
		    	         {  
   			                productOwnerNum		 23456,    
				            productOwnerIA5		 "23456",    
				            productIdNum		 65535,        
				            productIdIA5		 "123456",
				            externalIssuerId     12,
				            issuerAutorizationId 13,		
				            stationCodeTable     stationERA,    	         
		    	            validRegion { zones { zoneId { 100 } }  },
		    	        	validFromDay 0,
		    	        	validFromTime 1000,
		    	        	validUntilDay 1,
		    	        	validUntilTime 1000,
		    	        	classCode second,
		    	        	serviceLevel         "A",
   	    	                carrierNum           { 1080, 1181 },
		                    carrierIA5           { "1080", "1181" },
                            includedServiceBrands  { 108, 118 },
                            excludedServiceBrands  { 108, 118 },
		                    tariffs { 
		                       {
		                          numberOfPassengers 1,
		                          restrictedToCountryOfResidence FALSE,
		                          tariffIdNum 72,
		                          tariffDesc "Large Car Full Fare"
		                       }
		                    },
		                    infoText "included ticket",
                            includedTransportType  { 10, 11 },
                            excludedTransportType  { 10, 18 },
  				            extension {extensionId "1", extensionData '82DA'H}  		    	        
		    	         }
	    	      },	
				  luggage { maxHandLuggagePieces 2, maxNonHandLuggagePieces 1},
                  includedTransportType  { 10, 11 },
                  excludedTransportType  { 10, 18 },
  				  extension {extensionId "1", extensionData '82DA'H}   		      
             }
		   },       
           {
		      ticket pass : {
		      	  referenceNum        810123456789,
		          referenceIA5        "810123456789",
		          productOwnerNum	  23456,    
				  productOwnerIA5	  "23456",    
				  productIdNum		  65535,        
				  productIdIA5		  "123456",	    			  
				  passType            2,
				  passDescription     "Eurail FlexPass",
	    	      classCode           first,
	    	      validFromDay        0,
	    	      validFromTime       1000,
	    	      validFromUTCOffset  1,
	    	      validUntilDay       1,
	    	      validUntilTime      1000,
	    	      validUntilUTCOffset 1,
	    	      validityPeriodDetails {
                   	 validityPeriod {
                   	   {
                   	 	  validFromDay 0,
	    	              validFromTime 1000,
	    	              validFromUTCOffset 1,
	    	              validUntilDay 1,
	    	              validUntilTime 1000,
                 	      validUntilUTCOffset 1
                   	   }
	                 },
	                 excludedTimeRange {
	                   { 
		                  fromTime	6, 
	                      untilTime	9
	                   }
	                 }	
                  },
                  numberOfValidityDays 5,
                  trainValidity {
   	    	        validFromDay 0,
	    	        validFromTime 1000,
	    	        validUntilDay 1,
	    	        validUntilTime 1000,
                    includedCarrierNum {1234, 5678},
                    boardingOrArrival boarding
                  },
                  numberOfPossibleTrips        3,
                  numberOfDaysOfTravel         10,
                  activatedDay                 {200, 201},
                  countries                    {10, 20},
                  includedCarrierNum           { 1080, 1181 },
		          includedCarrierIA5           { "1080", "1181" },
                  excludedCarrierNum           { 1080, 1181 },
		          excludedCarrierIA5           { "1080", "1181" },
                  includedServiceBrands        { 108, 118 },
                  excludedServiceBrands        { 108, 118 },
    	          validRegion { zones  { zoneId { 100 } } },
		          tariffs { 
		                       {
		                          numberOfPassengers 1,
		                          restrictedToCountryOfResidence FALSE,
		                          tariffIdNum 72,
		                          tariffDesc "Large Car Full Fare"
		                       }
		          },                  
                  price 10000,
	    	      vatDetail {
	    	        			     { country    80,
	    	        			       percentage 70,
	    	        			       amount     10,
	    	        			       vatId      "IUDGTE"
	    	        			     }
	    	      }, 
	    	      infoText          "pass info",
 				  extension {extensionId "1", extensionData '82DA'H}  		    	        		      
		      }
		   },         
           {
		      ticket voucher : {
		          referenceIA5        "810123456789",
		          referenceNum        810123456789,
		          productOwnerNum	 23456,    
				  productOwnerIA5	 "COFFEEMACHINE",    
				  productIdNum		 65535,        
				  productIdIA5		 "123456", 
		          validFromYear 2022,
		          validFromDay 1,
		          validUntilYear 2022,
		          validUntilDay 1,
		          value 500,
		          type 123,
		          infoText "coffee voucher",
		          extension {extensionId "1", extensionData '82DA'H}  		    	        		            
		      }
		   },  		
           {
		      ticket customerCard : {
		           customer {
		                 customerIdIA5    "1234",
		                 ticketHolder     FALSE,
		                 passengerType    senior
		           },
				   cardIdIA5		"2345",	 
				   cardIdNum 		123456,
				   validFromYear 	2269,
				   validFromDay		2,
				   validUntilYear 	1,    
				   validUntilDay 	5,     
				   classCode		second,
				   cardType			15,
				   cardTypeDescr	"RAILPLUS",		
				   customerStatus   1,  
				   customerStatusDescr "gold",      
				   includedServices   { 1 , 2 },  
		           extension {extensionId "1", extensionData '82DA'H}  		    	        		            
		      }
		   },  		      
       	   {
		     ticket counterMark : {
		          referenceIA5        "810123456789",
		      	  referenceNum        810123456789,
		          productOwnerNum	 23456,    
				  productOwnerIA5	 "23456",    
				  productIdNum		 65535,        
				  productIdIA5		 "123456",	 		     
		          ticketReferenceIA5 "810123456789",		     
		      	  ticketReferenceNum 810123456789,
		          numberOfCountermark 12,
		          totalOfCountermarks 24,
		          groupName           "groupName",
	              stationCodeTable    stationERA,
		          fromStationNum       8100001,
		          fromStationIA5       "8100001",
		          toStationNum         8000002,
		          toStationIA5         "8100002",
				  fromStationNameUTF8  "A-STATION", 
				  toStationNameUTF8    "B-STATION", 
				  validRegionDesc      "From A to B via C",			         
		          validRegion { 
		  	           viaStations { 
			  	          route {
				   		        { 
				   		        	stationNum 123455, 
				   		        	stationIA5 "123455", 
				   		        	border FALSE 
				   		        },
				   		        { stationNum 123456, border FALSE },
				                { alternativeRoutes {
				                    { route {  {stationNum 23455, border FALSE},{stationNum 23456, border FALSE }}, border FALSE  },
				                    { route {  {stationNum 3455, border FALSE },{stationNum 3456, border FALSE }},  border FALSE  }
				                  },
				                  border FALSE 
				                },
					    	    { stationNum 123457, border FALSE }
				          },
				          border               FALSE,
	    	              carrierNum           { 1080, 1181 },
			              carrierIA5           { "1080", "1181" },
				    	  seriesId             999,
				    	  routeId              21
				       }
			       },      
			       returnIncluded        FALSE,
				   returnDescription     {
			             fromStationNum       8100001,
			             fromStationIA5       "8100001",
			             toStationNum         8000002,
			             toStationIA5         "8100002",
					     fromStationNameUTF8  "A-STATION", 
					     toStationNameUTF8    "B-STATION", 	
					     validReturnRegionDesc "return",
					     validReturnRegion {
	                          zones { 
						            carrierNum           1080,
					                carrierIA5           "1181",
						            stationCodeTable     stationERA,
						            zoneId               {100,200}
					         }				     
					     }
				  },
				  validFromDay	       700, 
				  validFromTime	       0,    
				  validFromUTCOffset   60,   
				  validUntilDay	       370,     	
				  validUntilTime       1439,       		
				  validUntilUTCOffset  10,   
				  classCode            first
   	    	      carrierNum           { 1080, 1181 },
		          carrierIA5           { "1080", "1181" },
                  includedServiceBrands  { 108, 118 },
                  excludedServiceBrands  { 108, 118 },		           
		       	  infoText            "counterMark",
 				  extension {extensionId "1", extensionData '82DA'H}  		    	        		      		       	  
		      }
		   },	
           {
		      ticket parkingGround : {
		          referenceIA5        "810123456789",
		      	  referenceNum        810123456789,
		          parkingGroundId 	  "IA5", 	
                  fromParkingDate     370,          
                  untilParkingDate    370, 
		          
		          
		          productOwnerNum	  23456,    
				  productOwnerIA5	  "23456",    
				  productIdNum		  65535,        
				  productIdIA5		  "123456", 	
				  accessCode          "4ga"
   		          location            "Parking Frankfurt Main West",
			      stationCodeTable    stationUIC,
			      stationNum          8000001,
			      stationIA5          "8000001",
			      specialInformation  "outdoor parking",
			      entryTrack          "left",
			      numberPlate         "AA-DE-12345",
			      price               500,		
 	    	      vatDetail {
	    	        			     { country    80,
	    	        			       percentage 70,
	    	        			       amount     10,
	    	        			       vatId      "IUDGTE"
	    	        			     }
	    	      }, 
 				  extension {extensionId "1", extensionData '82DA'H}  		    	        		      			              
		      }
		   }, 			  
           {
		      ticket fipTicket : {
		          referenceIA5        "810123456789",
		      	  referenceNum        810123456789,
		          productOwnerNum	  23456,    
				  productOwnerIA5	  "23456",    
				  productIdNum		  65535,        
				  productIdIA5		  "123456",    				   		
   	              validFromDay		  -367,   		        	 
   	              validUntilDay	      -1,   							
  	              activatedDay        {1,13,14,15},  
   	    	      carrierNum          { 1080, 1181 },
		          carrierIA5          { "1080", "1181" },
                  numberOfTravelDays  8,                  
                  includesSupplements TRUE,			
                  classCode           first,
 				  extension {extensionId "1", extensionData '82DA'H}  		    	        		      
		      }
		   }, 
           {
		      ticket stationPassage : {
		          referenceIA5        "810123456789",
		      	  referenceNum        810123456789,
		          productOwnerNum	 23456,    
				  productOwnerIA5	 "23456",    
				  productIdNum		 65535,        
				  productIdIA5		 "123456",		      
 		          productName "passage",
			      stationCodeTable stationUIC,
			      stationNum {8200001},
			      stationIA5 {"AMS"},
			      stationNameUTF8 {"Amsterdam"},
			      areaCodeNum {8200001},
			      areaCodeIA5 {"AMS"},
			      areaNameUTF8 {"Amsterdam"},
			      validFromDay 5,
			      validFromTime 0,
			      validFromUTCOffset 1,
			      validUntilDay 5,
			      validUntilTime 1000,
			      validUntilUTCOffset 1,
			      numberOfDaysValid 5
 				  extension {extensionId "1", extensionData '82DA'H}  		    	        		      	        
		      }
		   }, 
           {
		      ticket extension :  {
		        extensionId "1", 
		        extensionData '82DA'H
		      }
		   }, 
           {
		      ticket delayConfirmation : {
			        referenceIA5       "ABDJ12345",
			        referenceNum       12345,
			        trainNum           100,
			        trainIA5           "100",
			        departureYear      2022,
			        departureDay       12,
			        departureTime      1000,
			        departureUTCOffset 30,
			        stationCodeTable   stationUIC,
			        stationNum         8000001,
			        stationIA5         "DJE"
			        delay              31,
			        trainCancelled     FALSE,
			        confirmationType   travelerDelayConfirmation,
			        affectedTickets {
			          {
			            referenceIA5    "KDJET",
			            referenceNum    801234567890,
			            issuerName      "XYZ",
			            issuerPNR       "LDWDUR45",
			            productOwnerNum 1080,
			            productOwnerIA5 "IEFHU",
			            ticketType      openTicket,
			            linkMode        issuedTogether
			          }
			        },
			        infoText "delay confirmation",
		            extension {extensionId "1", extensionData '82DA'H}
		      }
		   }		   
	   },
       controlDetail {
          identificationByCardReference {
            { 
               cardIssuerNum            1234,
               cardIssuerIA5            "1234",
               cardIdNum                5678,
               cardIdIA5                "5678",
               cardName                 "testcard",
               cardType                 123,
               leadingCardIdNum	        3456,                                     
	           leadingCardIdIA5   	    "3456",
               trailingCardIdNum        100,
               trailingCardIdIA5        "100"
            }
          },
 	      identificationByIdCard	    FALSE,
  	      identificationByPassportId    FALSE
  	      identificationItem            12,
          passportValidationRequired  	FALSE,
    	  onlineValidationRequired    	FALSE,
    	  randomDetailedValidationRequired 50,
          ageCheckRequired            	FALSE ,  	
  	      reductionCardCheckRequired  	FALSE,        
          infoText                     "control",
          includedTickets {
 			          {
			            referenceIA5    "KDJET",
			            referenceNum    801234567890,
			            issuerName      "XYZ",
			            issuerPNR       "LDWDUR45",
			            productOwnerNum 1080,
			            productOwnerIA5 "IEFHU",
			            ticketType      openTicket,
			            linkMode        issuedTogether
			          }
		  },
          extension {extensionId "1", extensionData '82DA'H}          
       }, 
       extension {
          { extensionId "1", extensionData '82DA'H },
          { extensionId "2", extensionData '83DA'H }
       }			
   }	
		*/
		
	
		public static String getEncodingHex() {
			return  	
			"7BFFE0000058FCFF016204004B008DCC2DACB69D28D06E9E7CFAE5E5532F3E94" +
			"275201620505B402F606C5933010C880230390300DDD50E02FFFFC1129BDA1B8" +
			"1931A5D1D1B1940D11BDDC158B266D1A824A89529D4344440D12D456B9EA0118" +
			"1C805FA21906519804C04041B9E5DDA77F28B381DB5E51DC9BDD5C03003FFFFF" +
			"FFFBF81181C82B164CDA3501863862C18B266D1AB66EE1C828953DD768ADB9F0" +
			"564CDA356DFFFE0CC593368D5B000C03544756054C7972696143DCC5003B862C" +
			"183060C5E848041DC3160C18306412825AA6A882A8929E9C12845AA6A882A892" +
			"9E9D67C054004602086E127008118B070C0118B170C4283F0366C6082B362B5A" +
			"370657696E646F77020366C6081B3630808787FE06CD8C10566C56B46E0CAED2" +
			"DCC8DEEE0406CD8C10366C61010F0FFC0D9B1820ACD8AD68DC195DA5B991BDDC" +
			"080D9B18206CD8C2021E1F7F0189881BC3BBD01BC3BBD01BC3BBD390028F9880" +
			"4FFC7F760200FE80001E80D8B2660001D206C99B401410142EC0E31012DD000A" +
			"40137641898CAC2E6EAE4CA408CC2E4CA02FFC268823164CDA010B1702356CDD" +
			"C043A32B9BA31B0B93200BD8106C0023368D5B00B201B160C008C0E40713C460" +
			"10A069356247A9164094101383499F12455AC4CC6E0D62C449156B143205B932" +
			"B9B2B93B30BA34B7B700B10282DA780BD81D6895812A45A7500A0B682FFFFFFF" +
			"FB780BD81B164CC16000FA5A0C70C583164CDA356CDDC390600BC9F1EF115B73" +
			"E0AC99B46ADBFFFC198B266D1AB600C60482B41AE6E0CAC6D2C2D840E8E4C2D2" +
			"DC9EE62801DC3160C183062F424020EE18B060C183209412D53544154494F4E0" +
			"9422D53544154494F4E0264C409A317F0189881BC3BBD01BC3BBD01BC3BBD213" +
			"0622D89156B164CC262585B0612D62C999D1CA4534A0410DC24E01023160E180" +
			"23162E18805005204D3185C99D94810D85C88119D5B1B0811985C99408C0E407" +
			"13C46010A069356247A914318D85C8818D85C9C9A5859D9405881416D027FFFF" +
			"FFFFF83005E4F8F788A863862C18B266D1AB66EE1CDB9F0564CDA356DFFFE0CC" +
			"593368D5B0086008693DCC5003B862C183060C5E848041DC3160C18306412825" +
			"AA6A882A8929E9C12845AA6A882A8929E9C228CE4DEDA408240E8DE408440ECD" +
			"2C240860A209E0860003C47C0CC593368D5A88000788FC100040800440000B73" +
			"C200005B9F0100088000035F8400001AFE08000789000203E701150200D601D4" +
			"08035807527FF086E08C5838608001344118B266D00024A01193368D40C07890" +
			"00805900803200A0B681A24568DD8B137FC21B823160E180100B201006420004" +
			"D10462C99B40009280464CDA350301E2400FF81181C82B164CDA3501D9F00F73" +
			"1400EE18B060C18317A12010770C583060C1904A096A9AA20AA24A7A704A116A" +
			"9AA20AA24A7A720008C0E40C0377540808C0E40C03775408C0E40C037755FEF7" +
			"31400EE18B060C18317A12010770C583060C1904A096A9AA20AA24A7A704A116" +
			"A9AA20AA24A7A7033932BA3AB93700938204370462C1C3040402C80401910AC0" +
			"078B9D9F8C04010105040821B849C020462C1C300462C5C310200D601D408035" +
			"80750100A00A409A630B933B29021B0B910233AB636102330B93281181C80827" +
			"88C1CDEE0CADCA8D2C6D6CAE892DCCCDE02FF5BFFDB9F0564CDA356DFFFE0CC5" +
			"93368D5B00860086A0120040101647D0027D024102086E127008118B070C0118" +
			"B170C40803580750200D601D40402802902698C2E4CECA4086C2E4408CEAD8D8" +
			"408CC2E4CA1ED2DCC6D8EAC8CAC840E8D2C6D6CAE804A5812A402C40A0B69010" +
			"129604A900B10282DA06FFBFFFFFC1802F27C7BC45431C3160C593368D5B3770" +
			"E6DCF82B266D1AB6FFFF0662C99B46AD8043D15D5C985A5B08119B195E14185C" +
			"DCC2FA1E809F43DC05F7D0F404FA1E80806012052D01F4009F40104D12C5A041" +
			"204C86481048981043709380408C58386008C58B8620410DC24E01023160E180" +
			"23162E1881006B00EA0401AC03A8090020080B200805005204D3185C99D94810" +
			"D85C88119D5B1B0811985C994089C400713C46010A069356247A91425C185CDC" +
			"C81A5B999BC05881416D047FE18E18B062C99B46AD9BB8720C01793E3DE22B6E" +
			"7C361CF8D1A2C59B061C8933A2FFFF833164CDA356C0C0106008100FA007A0E6" +
			"36F6666656520766F756368657201620505B415FFF0084008C59336810464CDA" +
			"350301E240FD0100814407042920A4A628262AA980808267DFB3201008080810" +
			"0B10282DA0CFFFFFFFE18E18B062C99B46AD9BB8720C01793E3DE22B6E7C1593" +
			"368D5B7FFF833164CDA356C18E18B062C99B46AD9BB8720C01793E3DE22A162E" +
			"12CEE4DEEAE09CC2DACA9EE62801DC3160C183062F424020EE18B060C1832094" +
			"12D53544154494F4E09422D53544154494F4E1146726F6D204120746F2042207" +
			"66961204301107C0430001E23E0662C99B46AD440003C47E0800204002200005" +
			"B9E100002DCF808004400001AFC200000D7F040003C48001043709380408C583" +
			"86008C58B8620407CE022A7FBDCC5003B862C183060C5E848041DC3160C18306" +
			"412825AA6A882A8929E9C12845AA6A882A8929E9C0CE4CAE8EAE4DC024E0810D" +
			"C118B170C50100B201006442B001E2E767E3040821B849C020462C1C300462C5" +
			"C310200D601D40803580750B636F756E7465724D61726B01620505B41DFEFF0C" +
			"70C583164CDA356CDDC390600BC9F1EF115039305ADC372B73E0AC99B46ADBFF" +
			"FC198B266D1AB603699F08DA830B935B4B73390233930B735B33AB93A1026B0B" +
			"4B7102BB2B9BA01BD0900839C18181818181887B7BABA3237B7B9103830B935B" +
			"4B733823632B33A05C182B62455AC593368D40807D00713C46010A069356247A" +
			"91405881416D087FFC31C3160C593368D5B3770E41802F27C7BC456DCF82B266" +
			"D1AB6FFFF0662C99B46AD80000010020D0703C0821B849C020462C1C300462C5" +
			"C31078405881416D097F7FFC31C3160C593368D5B3770E41802F27C7BC456DCF" +
			"82B266D1AB6FFFF0662C99B46AD81DC185CDCD859D94040DF47D04040E0CDA60" +
			"21282DAE6E8CAE4C8C2DA0206FA3E820207066D30109416D7374657264616D2E" +
			"8001E819F43D010501620505B42805881416D0B7FB709830A24A62C99B46A046" +
			"07202C806C58300605BE8B4F42400071254507802FC059712545A80C01751A9A" +
			"85A406B0B2B4113225789569346A10DC164C58D22A893232B630BC9031B7B733" +
			"34B936B0BA34B7B700B10282DA7E02FFC268823164CDA010B1702356CDDC043A" +
			"32B9BA31B0B93200BD8106C0023368D5B00B201B160C0010C1900EC6DEDCE8E4" +
			"DED802FC059712545A80C01751A9A85A406B0B2B4113225789569346A10DC164" +
			"C58D22A80B10282DA0201620505B402C80A0F680";
		}

		
		public static UicRailTicketData getUicTestTicket() {
			UicRailTicketData ticket = new UicRailTicketData();
	    	populateTicket(ticket);
			return ticket;
		}


		private static void populateTicket(UicRailTicketData ticket) {
			
			ticket.setControlDetail(new ControlData());
			populateControlDetail(ticket.getControlDetail()); 
			
			ticket.setExtension(new SequenceOfExtensionData());
			populateExtension(ticket.getExtension());
			
			ticket.setIssuingDetail(new IssuingData());
			populateIssuingData(ticket.getIssuingDetail());
			
			ticket.setTravelerDetail(new TravelerData());
			populateTravelerData(ticket.getTravelerDetail());
			
			ticket.setTransportDocument(new SequenceOfDocumentData());
			
			DocumentData d1 = new DocumentData();
			populateReservation(d1);
			ticket.getTransportDocument().add(d1);
			
			DocumentData d2 = new DocumentData();
			populateCarCarriage(d2);
			ticket.getTransportDocument().add(d2);
			
			DocumentData d3 = new DocumentData();
			populateOpenTicket(d3);
			ticket.getTransportDocument().add(d3);
			
			DocumentData d4 = new DocumentData();
			populatePass(d4);
			ticket.getTransportDocument().add(d4);
			
			DocumentData d5 = new DocumentData();
			populateVoucher(d5);
			ticket.getTransportDocument().add(d5);
			
			DocumentData d6 = new DocumentData();
			populateCustomerCard(d6);
			ticket.getTransportDocument().add(d6);
			
			DocumentData d7 = new DocumentData();
			populateCounterMark(d7);
			ticket.getTransportDocument().add(d7);
			
			DocumentData d8 = new DocumentData();
			populateParking(d8);
			ticket.getTransportDocument().add(d8);
			
			DocumentData d9 = new DocumentData();
			populateFip(d9);
			ticket.getTransportDocument().add(d9);
			
			DocumentData d10 = new DocumentData();
			populateStationPassage(d10);
			ticket.getTransportDocument().add(d10);
			
			DocumentData d11 = new DocumentData();
			populateExtensionDocument(d11);
			ticket.getTransportDocument().add(d11);
			
			DocumentData d12 = new DocumentData();
			populateDelay(d12);
			ticket.getTransportDocument().add(d12);
		
		}
		
		private static void populateDelay(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			DelayConfirmation dc = new DelayConfirmation();
			d.getTicket().setDelayConfirmation(dc);
		    
		    dc.setTrainNum(Asn1BigInteger.toAsn1(100L)); 						
		    dc.setTrainIA5("100");     						
		    dc.setPlannedArrivalYear(2022L); 
		    dc.setPlannedArrivalDay(12L); 
		    dc.setPlannedArrivalTime(1000L); 
		    dc.setDepartureUTCOffset(30L);
		    dc.setReferenceIA5("ABDJ12345");	
		    dc.setReferenceNum(12345L);			
		    
		    dc.setStationCodeTable(CodeTableType.stationUIC);
		    dc.setStationNum(8000001L);
		    dc.setStationIA5("DJE");
		    dc.setDelay(31L);
		    dc.setTrainCancelled(false);
		    dc.setConfirmationType(ConfirmationTypeType.travelerDelayConfirmation);
		    
		    dc.setAffectedTickets(new SequenceOfTicketLinkType());
		    TicketLinkType tl = new TicketLinkType();
		    dc.getAffectedTickets().add(tl);
		    
		    tl.setReferenceIA5("KDJET");
		    tl.setReferenceNum(801234567890L);
		    tl.setIssuerName("XYZ");
		    tl.setIssuerPNR("LDWDUR45");
		    tl.setProductOwnerNum(1080L);
		    tl.setProductOwnerIA5("IEFHU");
		    tl.setTicketType(TicketType.openTicket);
		    tl.setLinkMode(LinkMode.issuedTogether);
	        
	        dc.setInfoText("delay confirmation");
			dc.setExtension(new ExtensionData());
			dc.getExtension().setExtensionId("1");
			dc.getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			
		}


		private static void populateExtensionDocument(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			d.getTicket().setExtension(new ExtensionData());
			d.getTicket().getExtension().setExtensionId("1");
			d.getTicket().getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			
		}


		private static void populateStationPassage(DocumentData d) {
			
			d.setTicket(new TicketDetailData());		
			StationPassageData s = new StationPassageData();
			d.getTicket().setStationPassage(s);

			s.setExtension(new ExtensionData());
			s.getExtension().setExtensionId("1");
			s.getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
					
		    s.setReferenceIA5("810123456789");	
		    s.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    s.setProductOwnerNum(23456L);    
		    s.setProductOwnerIA5("23456");    
		    s.setProductIdNum(65535L);    
		    s.setProductIdIA5("123456"); 
				      
		    s.setProductName("passage");
	        s.setStationCodeTable(CodeTableType.stationUIC);
		    s.setStationNum(new SequenceOfUnrestrictedLong());
		    s.getStationNum().add(8200001L);	    	
		    s.setStationIA5(new SequenceOfStringIA5());
		    s.getStationIA5().add("AMS");
		    s.setStationNameUTF8(new SequenceOfStringUTF8());
		    s.getStationNameUTF8().add("Amsterdam");
		    s.setAreaCodeNum(new SequenceOfUnrestrictedLong());
		    s.getAreaCodeNum().add(8200001L);	    	    		
		    s.setAreaCodeIA5(new SequenceOfStringIA5());
			s.getAreaCodeIA5().add("AMS");
		    s.setAreaNameUTF8(new SequenceOfStringUTF8());
		    s.getAreaNameUTF8().add("Amsterdam");
		    s.setValidFromDay(5L);
		    s.setValidFromTime(0L);
		    s.setValidFromUTCOffset(1L);
		    s.setValidUntilDay(5L);
		    s.setValidUntilTime(1000L);
		    s.setValidUntilUTCOffset(1L);
		    s.setNumberOfDaysValid(5L);
		    
		}


		private static void populateFip(DocumentData d) {

			d.setTicket(new TicketDetailData());
			FIPTicketData f = new FIPTicketData();
			d.getTicket().setFipTicket(f);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			f.setExtension(e1);
					
		    f.setReferenceIA5("810123456789");	
		    f.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    f.setProductOwnerNum(23456L);    
		    f.setProductOwnerIA5("23456");    
		    f.setProductIdNum(65535L);    
		    f.setProductIdIA5("123456"); 
		    
	 				   		
		    f.setValidFromDay( -367L);   		        	 
	        f.setValidUntilDay(   -1L);							
	        f.setActivatedDay(new SequenceOfActivatedDays());     
	        f.getActivatedDay().add(1L);
	       	f.getActivatedDay().add(13L);
	        f.getActivatedDay().add(14L); 
	       	f.getActivatedDay().add(15L); 
	       	
		    f.setCarrierNum(new SequenceOfCarrierNum());
		    f.getCarrierNum().add(1080L);
		    f.getCarrierNum().add(1181L);
		 
		    f.setCarrierIA5(new SequenceOfStringIA5());
		    f.getCarrierIA5().add("1080");
		    f.getCarrierIA5().add("1181");       	
	       	
	        f.setNumberOfTravelDays(8L);                  
	        f.setIncludesSupplements(true);			
	        f.setClassCode(TravelClassType.first);
			
		}


		private static void populateParking(DocumentData d) {

			d.setTicket(new TicketDetailData());
			ParkingGroundData p = new ParkingGroundData();
			d.getTicket().setParkingGround(p);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			p.setExtension(e1);

	
		    p.setReferenceIA5("810123456789");	
		    p.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    p.setProductOwnerNum(23456L);    
		    p.setProductOwnerIA5("23456");    
		    p.setProductIdNum(65535L);    
		    p.setProductIdIA5("123456"); 
			
		    p.setParkingGroundId("IA5");
		    p.setFromParkingDate(370L);        
		    p.setToParkingDate(370L);
	        
		    p.setAccessCode("4ga");
		    p.setLocation("Parking Frankfurt Main West");
		    p.setStationCodeTable(CodeTableType.stationUIC);
		    p.setStationNum(8000001L);
		    p.setStationIA5("8000001");
		    p.setSpecialInformation("outdoor parking");
		    p.setEntryTrack("left");
		    p.setNumberPlate("AA-DE-12345");
		    p.setPrice(500L);	
		    p.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v = new VatDetailType();
		    p.getVatDetails().add(v);
		    v.setAmount(10L);
		    v.setCountry(80L);
		    v.setPercentage(70L);
		    v.setVatId("IUDGTE");  
			  
		}


		private static void populateCounterMark(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			CountermarkData t = new CountermarkData();
			d.getTicket().setCounterMark(t);
			
			t.setTicketReferenceIA5("810123456789");		     
			t.setTicketReferenceNum(810123456789L);
			t.setNumberOfCountermark(12L);
	        t.setTotalOfCountermarks(24L);
	        t.setGroupName("groupName");
	        
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			t.setExtension(e1);	
			
		    t.setReferenceIA5("810123456789");	
		    t.setReferenceNum(810123456789L);			     		        															
		    t.setProductOwnerNum( 23456L);    
		    t.setProductOwnerIA5("23456");    
		    t.setProductIdNum( 65535L);    
		    t.setProductIdIA5("123456"); 
		
			t.setReturnIncluded( false);	      
				  
			t.setStationCodeTable(CodeTableType.stationERA);
			t.setFromStationNum( 8100001L);
			t.setFromStationIA5("8100001");
			t.setToStationNum(   8000002L);
			t.setToStationIA5(  "8100002");
			t.setFromStationNameUTF8(  "A-STATION"); 
			t.setToStationNameUTF8(    "B-STATION");				  
 
			t.setValidRegionDesc("From A to B via C");		   
				  
			t.setValidRegion(new SequenceOfRegionalValidityType());
			t.getValidRegion().add(new RegionalValidityType());
			t.getValidRegion().get(0).setViaStations(new ViaStationType());
			populateCounterMarkRoute(t.getValidRegion().get(0).getViaStations());
					
		    t.setReturnDescription(new ReturnRouteDescriptionType());
		    t.getReturnDescription().setFromStationNum(  8100001L);
		    t.getReturnDescription().setFromStationIA5("8100001");
			t.getReturnDescription().setToStationNum(         8000002L);
		    t.getReturnDescription().setToStationIA5(         "8100002");
		    t.getReturnDescription().setFromStationNameUTF8(  "A-STATION");
			t.getReturnDescription().setToStationNameUTF8(    "B-STATION"); 	
			t.getReturnDescription().setValidReturnRegionDesc( "return");
			t.getReturnDescription().setValidReturnRegion(new SequenceOfRegionalValidityType());
			t.getReturnDescription().getValidReturnRegion().add(new RegionalValidityType());
			
			t.getReturnDescription().getValidReturnRegion().get(0).setZones(new ZoneType());
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setZoneId(new SequenceOfUnrestrictedLong());
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(100L);
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(200L);
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierNum(1080L);
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierIA5("1181");
			t.getReturnDescription().getValidReturnRegion().get(0).getZones().setStationCodeTable(CodeTableType.stationERA);
	       	                  
		    t.setValidFromDay(  700L);
		    t.setValidFromTime(  0L);
		    t.setValidFromUTCOffset(     60L); 
		    t.setValidUntilDay(  370L);
		    t.setValidUntilTime(  1439L);
		    t.setValidUntilUTCOffset(    10L);  
					       		   
			t.setClassCode(TravelClassType.first);
						
	        t.setCarriersNum(new SequenceOfCarrierNum());
	        t.getCarriersNum().add(1080L);
	        t.getCarriersNum().add(1181L);
	   
	        t.setCarriersIA5(new SequenceOfStringIA5());
	        t.getCarriersIA5().add("1080");
	        t.getCarriersIA5().add("1181");
	      
	        t.setIncludedServiceBrands(new SequenceOfServiceBrands());
	        t.getIncludedServiceBrands().add(108L);
	        t.getIncludedServiceBrands().add(118L);
	       
	        t.setExcludedServiceBrands(new SequenceOfServiceBrands());
	        t.getExcludedServiceBrands().add(108L);
	        t.getExcludedServiceBrands().add(118L);
			
    
	        t.setInfoText("counterMark");
			
		}


		private static void populateCustomerCard(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			CustomerCardData c = new CustomerCardData();
			d.getTicket().setCustomerCard(c);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			c.setExtension(e1);
			
			TravelerType t = new TravelerType();
			c.setCustomer(t);
			t.setCustomerIdIA5("1234");
            t.setTicketHolder(false);
            t.setPassengerType(PassengerType.senior);
	
			c.setCardIdIA5("2345"); 
			c.setCardIdNum(123456L);
			c.setValidFromYear(2269L);
			c.setValidFromDay(2L);
		    c.setValidUntilYear(1L);    
		    c.setValidUntilDay(5L);    
		    c.setClassCode(TravelClassType.second);
		    c.setCardType(15L);
		    c.setCardTypeDescr("RAILPLUS");	
		    c.setCustomerStatus(1L);  
		    
		    c.setCustomerStatusDescr("gold");      
		    c.setIncludedServices(new SequenceOfUnrestrictedLong());
		    c.getIncludedServices().add(1L);
		    c.getIncludedServices().add(2L);
			
		}


		private static void populateVoucher(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			VoucherData v = new VoucherData();
			d.getTicket().setVoucher(v);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			v.setExtension(e1);			
			
		    v.setReferenceIA5("810123456789");	
		    v.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    v.setProductOwnerNum(23456L);    
		    v.setProductOwnerIA5("COFFEEMACHINE");    
		    v.setProductIdNum(65535L);    
		    v.setProductIdIA5("123456"); 

		    v.setValidFromYear(2022L);
		    v.setValidFromDay(1L);
		    v.setValidUntilYear(2022L);
	        v.setValidUntilDay(1L);
	        v.setValue(500L);
	        v.setType(123L);
	        v.setInfoText("coffee voucher");
		}


		private static void populatePass(DocumentData d) {
			
			
			d.setTicket(new TicketDetailData());
			PassData p = new PassData();
			d.getTicket().setPass(p);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			p.setExtension(e1);			
			
		    p.setReferenceIA5("810123456789");	
		    p.setReferenceNum(Asn1BigInteger.toAsn1(810123456789L));			     		        															
		    p.setProductOwnerNum(23456L);    
		    p.setProductOwnerIA5("23456");    
		    p.setProductIdNum(65535L);    
		    p.setProductIdIA5("123456"); 
				  
			p.setPassType(2L);
			p.setPassDescription("Eurail FlexPass");
			p.setClassCode(TravelClassType.first);
		    p.setValidFromDay(       0L);
		    p.setValidFromTime(      1000L);
		    p.setValidFromUTCOffset( 1L);
		    p.setValidUntilDay(      1L);
		    p.setValidUntilTime(     1000L);
		    p.setValidUntilUTCOffset(1L);
		    
		    ValidityPeriodDetailType v = new ValidityPeriodDetailType();
		    p.setValidityPeriodDetails(v);
		    v.setValidityPeriod(new SequenceOfValidityPeriodType());
		    v.setExcludedTimeRange(new SequenceOfTimeRangeType());
	    	  
	        ValidityPeriodType vp = new ValidityPeriodType();
	        p.getValidityPeriodDetails().getValidityPeriod().add(vp);
	        vp.setValidFromDay(0L);
	        vp.setValidFromTime( 1000L);
	        vp.setValidFromUTCOffset( 1L);
	        vp.setValidUntilDay( 1L);
	        vp.setValidUntilTime( 1000L);
	        vp.setValidUntilUTCOffset(1L);

	        TimeRangeType tr = new TimeRangeType();
	        p.getValidityPeriodDetails().getExcludedTimeRange().add(tr);
		   
		    tr.setFromTime(6L);
		    tr.setUntilTime(9L);

		    p.setNumberOfValidityDays(5L);
		    
		    TrainValidityType t = new TrainValidityType();
	        p.setTrainValidity(t);
	        
	        t.setValidFromDay(0L);
	        t.setValidFromTime( 1000L);
	        t.setValidUntilDay( 1L);
		    t.setValidUntilTime(1000L);
		    t.setIncludedCarriersNum(new SequenceOfCarrierNum());
		    t.getIncludedCarriersNum().add(1234L);
		    t.getIncludedCarriersNum().add(5678L);
	        t.setBordingOrArrival(BoardingOrArrivalType.boarding);
	      
	        p.setNumberOfPossibleTrips( 3L);
	        p.setNumberOfDaysOfTravel(     10L);
	          
		    p.setActivatedDay(new SequenceOfActivatedDays());     
		    p.getActivatedDay().add(200L);
		    p.getActivatedDay().add(201L);
		       	
      
	        p.setCountries(new SequenceOfCountries());
	        p.getCountries().add(10L);
	        p.getCountries().add(20L);
	          
	        p.setIncludedCarriersNum(new SequenceOfCarrierNum());
	        p.getIncludedCarriersNum().add(1080L);
	        p.getIncludedCarriersNum().add(1181L);
	          
	        p.setIncludedCarriersIA5(new SequenceOfStringIA5());
	        p.getIncludedCarriersIA5().add("1080");
	        p.getIncludedCarriersIA5().add("1181");
	    
	        p.setExcludedCarriersNum(new SequenceOfCarrierNum());
	        p.getExcludedCarriersNum().add(1080L);
	        p.getExcludedCarriersNum().add(1181L);
	   
	        p.setExcludedCarriersIA5(new SequenceOfStringIA5());
	        p.getExcludedCarriersIA5().add("1080");
	        p.getExcludedCarriersIA5().add("1181");
	      
	        p.setIncludedServiceBrands(new SequenceOfServiceBrands());
	        p.getIncludedServiceBrands().add(108L);
	        p.getIncludedServiceBrands().add(118L);
	       
	        p.setExcludedServiceBrands(new SequenceOfServiceBrands());
	        p.getExcludedServiceBrands().add(108L);
	        p.getExcludedServiceBrands().add(118L);
	       
	        p.setValidRegion(new SequenceOfRegionalValidityType());
	        
	        RegionalValidityType rv = new RegionalValidityType();
	        p.getValidRegion().add(rv);
	        rv.setZones(new ZoneType());
	        rv.getZones().setZoneId(new SequenceOfUnrestrictedLong());
	        rv.getZones().getZoneId().add(100L);
	       	                  
	        p.setTariffs(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        p.getTariffs().add(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
	        
		    p.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    p.getVatDetails().add(v1);
		    v1.setAmount(10L);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);
		    v1.setVatId("IUDGTE");  

		    p.setPrice(10000L);
		    p.setInfoText("pass info");
			
		}


		private static void populateOpenTicket(DocumentData d) {
			
			d.setTicket(new TicketDetailData());
			OpenTicketData o = new OpenTicketData();
			d.getTicket().setOpenTicket(o);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			o.setExtension(e1);	
			
		    o.setReferenceIA5("810123456789");	
		    o.setReferenceNum(810123456789L);			     		        															
		    o.setProductOwnerNum( 23456L);    
		    o.setProductOwnerIA5("23456");    
		    o.setProductIdNum( 65535L);    
		    o.setProductIdIA5("123456"); 
			

			o.setExtIssuerId(         12L);
			o.setIssuerAutorizationId( 13L);	
			o.setReturnIncluded( false);	      
				  
			o.setStationCodeTable(CodeTableType.stationERA);
			o.setFromStationNum( 8100001L);
			o.setFromStationIA5("8100001");
			o.setToStationNum(   8000002L);
			o.setToStationIA5(  "8100002");
			o.setFromStationNameUTF8(  "A-STATION"); 
			o.setToStationNameUTF8(    "B-STATION");				  
 
			o.setValidRegionDesc("From A to B via C");		   
				  
			o.setValidRegion(new SequenceOfRegionalValidityType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(0).setViaStations(new ViaStationType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(1).setZones(new ZoneType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(2).setLines(new LineType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(3).setTrainLink(new TrainLinkType());
			o.getValidRegion().add(new RegionalValidityType());
			o.getValidRegion().get(4).setPolygone(new PolygoneType());
			populateRoute(o.getValidRegion().get(0).getViaStations());
			populateZone(o.getValidRegion().get(1).getZones());
			populateLine(o.getValidRegion().get(2).getLines());
			populateTrainLink(o.getValidRegion().get(3).getTrainLink());
			populatePolygon(o.getValidRegion().get(4).getPolygone());
		
		    o.setReturnDescription(new ReturnRouteDescriptionType());
		    o.getReturnDescription().setFromStationNum(  8100001L);
		    o.getReturnDescription().setFromStationIA5("8100001");
			o.getReturnDescription().setToStationNum(         8000002L);
		    o.getReturnDescription().setToStationIA5(         "8100002");
		    o.getReturnDescription().setFromStationNameUTF8(  "A-STATION");
			o.getReturnDescription().setToStationNameUTF8(    "B-STATION"); 	
			o.getReturnDescription().setValidReturnRegionDesc( "return");
			o.getReturnDescription().setValidReturnRegion(new SequenceOfRegionalValidityType());
			o.getReturnDescription().getValidReturnRegion().add(new RegionalValidityType());
			
			o.getReturnDescription().getValidReturnRegion().get(0).setZones(new ZoneType());
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setZoneId(new SequenceOfUnrestrictedLong());
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(100L);
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().getZoneId().add(200L);
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierIA5("1080");
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setCarrierNum(1080L);
			o.getReturnDescription().getValidReturnRegion().get(0).getZones().setStationCodeTable(CodeTableType.stationERA);	       	                  
			
		    o.setValidFromDay(  700L);
		    o.setValidFromTime(  0L);
		    o.setValidFromUTCOffset(     60L); 
		    o.setValidUntilDay(  370L);
		    o.setValidUntilTime(  1439L);
		    o.setValidUntilUTCOffset(    10L);  
			 
		    o.setActivatedDay(new SequenceOfActivatedDays());     
		    o.getActivatedDay().add(1L);
		    o.getActivatedDay().add(2L);
		    		  
			   
			o.setClassCode(TravelClassType.first);
			o.setServiceLevel("A");		
			
	        o.setCarriersNum(new SequenceOfCarrierNum());
	        o.getCarriersNum().add(1080L);
	        o.getCarriersNum().add(1181L);
	   
	        o.setCarriersIA5(new SequenceOfStringIA5());
	        o.getCarriersIA5().add("1080");
	        o.getCarriersIA5().add("1181");
	      
	        o.setIncludedServiceBrands(new SequenceOfServiceBrands());
	        o.getIncludedServiceBrands().add(108L);
	        o.getIncludedServiceBrands().add(118L);
	       
	        o.setExcludedServiceBrands(new SequenceOfServiceBrands());
	        o.getExcludedServiceBrands().add(108L);
	        o.getExcludedServiceBrands().add(118L);
			
	        o.setTariffs(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        o.getTariffs().add(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
	        
		    o.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    o.getVatDetails().add(v1);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);	
    				    
		    o.setPrice(  12345L);

    
	        o.setInfoText("openTicketInfo");
		
			o.setIncludedAddOns(new SequenceOfIncludedOpenTicketType());
			o.getIncludedAddOns().add(new IncludedOpenTicketType());
			populateIncludedTicket(o.getIncludedAddOns().get(0));
			
			o.setLuggage(new LuggageRestrictionType());
			o.getLuggage().setMaxHandLuggagePieces(2L);
			o.getLuggage().setMaxNonHandLuggagePieces(1L);
			
			o.setIncludedTransportTypes(new SequenceOfTransportTypes());
			o.getIncludedTransportTypes().add(10L);
			o.getIncludedTransportTypes().add(11L);
					
			o.setExcludedTransportTypes(new SequenceOfTransportTypes());
			o.getExcludedTransportTypes().add(10L);
			o.getExcludedTransportTypes().add(18L);	
			

			
						
		}
		



		private static void populateTrainLink(TrainLinkType t) {
			
			
		    t.setTrainNum( 12345L); 						
		    t.setTrainIA5("12345");     						
		    t.setTravelDate(  2L);
		    t.setDepartureTime( 		 1439L);           
		    t.setDepartureUTCOffset( -60L); 	    

		    t.setFromStationNum( 8100001L);
		    t.setFromStationIA5("8100001");
		    t.setToStationNum(   8000002L);
		    t.setToStationIA5(  "8100002");
		    t.setFromStationName(  "A-STATION"); 
		    t.setToStationName(    "B-STATION"); 
		         
		}

		private static void populatePolygon(PolygoneType p) {
			
			p.setFirstEdge(new GeoCoordinateType());
			p.getFirstEdge().setLongitude( 12345L);
	        p.getFirstEdge().setLatitude(  56789L);						
			p.setEdges(new SequenceOfDeltaCoordinates());
			p.getEdges().add(new DeltaCoordinates());
			p.getEdges().add(new DeltaCoordinates());
			p.getEdges().get(0).setLongitude( Asn1BigInteger.toAsn1(12345L));
			p.getEdges().get(0).setLatitude(Asn1BigInteger.toAsn1(56789L));
			p.getEdges().get(1).setLongitude(Asn1BigInteger.toAsn1(12345L));
			p.getEdges().get(1).setLatitude(Asn1BigInteger.toAsn1(56789L));

		}

		private static void populateZone(ZoneType z) {
			
			z.setCarrierNum( 1080L);
			z.setCarrierIA5("1080");
			z.setStationCodeTable(CodeTableType.stationERA);
			z.setEntryStationNum(         1234L);
	        z.setEntryStationIA5(        "1234");
	        z.setTerminatingStationNum(   2345L);
	        z.setTerminatingStationIA5(  "2345");
	        z.setCity(Asn1BigInteger.toAsn1(123456L));
	        z.setZoneId(new SequenceOfUnrestrictedLong());
	        z.getZoneId().add(100L);
	        z.getZoneId().add(200L);
			z.setNutsCode("DE4711");
			z.setBinaryZoneId(UperEncoder.bytesFromHexString("82DA"));
	        
		}

		private static void populateLine(LineType z) {
			
			z.setCarrierNum( 1080L);
			z.setCarrierIA5("1080");
			z.setStationCodeTable(CodeTableType.stationERA);
			z.setEntryStationNum(         1234L);
	        z.setEntryStationIA5(        "1234");
	        z.setTerminatingStationNum(   2345L);
	        z.setTerminatingStationIA5(  "2345");
	        z.setCity(123456L);
	        z.setLineId(new SequenceOfUnrestrictedLong());
	        z.getLineId().add(100L);
	        z.getLineId().add(200L);
	       	
		}

		private static void populateRoute(ViaStationType t) {
			
				
				t.setRoute(new SequenceOfViaStationType());
				t.getRoute().add(new ViaStationType());
				t.getRoute().add(new ViaStationType());
				t.getRoute().add(new ViaStationType());
				t.getRoute().add(new ViaStationType());
				
				t.getRoute().get(0).setStationNum( 123455L);
				t.getRoute().get(0).setStationIA5("123455");
				t.getRoute().get(0).setBorder( false);

				t.getRoute().get(1).setStationNum( 123456L);
				t.getRoute().get(1).setBorder( false);
				
				
				t.getRoute().get(2).setAlternativeRoutes(new SequenceOfViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
				t.getRoute().get(2).setBorder( false);
				
				t.getRoute().get(2).getAlternativeRoutes().get(0).setRoute(new SequenceOfViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(0).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setStationNum( 23455L);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setStationNum( 23456L);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(1).setRoute(new SequenceOfViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(1).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setStationNum( 3455L);
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setBorder(false);
				t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(1).setStationNum( 3456L);
				t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
				
				t.getRoute().get(3).setStationNum( 123457L);	
				t.getRoute().get(3).setBorder(false);
		  	          
		  	    t.setBorder( false);
			          
			    t.setSeriesId( 999L);
			    t.setRouteId( 21L);
			    	  
			    t.setIncludedServiceBrands(new SequenceOfServiceBrands());
			    t.getIncludedServiceBrands().add(108L);
			    t.getIncludedServiceBrands().add(118L);
			       
			    t.setExcludedServiceBrands(new SequenceOfServiceBrands());
			    t.getExcludedServiceBrands().add(108L);
			    t.getExcludedServiceBrands().add(118L);
			
		}
		
		private static void populateCounterMarkRoute(ViaStationType t) {
			
			
			t.setRoute(new SequenceOfViaStationType());
			t.getRoute().add(new ViaStationType());
			t.getRoute().add(new ViaStationType());
			t.getRoute().add(new ViaStationType());
			t.getRoute().add(new ViaStationType());
			
			t.getRoute().get(0).setStationNum( 123455L);
			t.getRoute().get(0).setStationIA5("123455");
			t.getRoute().get(0).setBorder( false);

			t.getRoute().get(1).setStationNum( 123456L);
			t.getRoute().get(1).setBorder( false);
			
			
			t.getRoute().get(2).setAlternativeRoutes(new SequenceOfViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().add(new ViaStationType());
			t.getRoute().get(2).setBorder( false);
			
			t.getRoute().get(2).getAlternativeRoutes().get(0).setRoute(new SequenceOfViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(0).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setStationNum( 23455L);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(0).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setStationNum( 23456L);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(1).setRoute(new SequenceOfViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(1).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().add(new ViaStationType());
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setStationNum( 3455L);
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(0).setBorder(false);
			t.getRoute().get(2).getAlternativeRoutes().get(1).getRoute().get(1).setStationNum( 3456L);
			t.getRoute().get(2).getAlternativeRoutes().get(0).getRoute().get(1).setBorder(false);
			
			t.getRoute().get(3).setStationNum( 123457L);	
			t.getRoute().get(3).setBorder(false);
			
			t.setCarriersIA5(new SequenceOfStringIA5());
			t.getCarriersIA5().add("1080");
			t.getCarriersIA5().add("1181");
			
			t.setCarriersNum(new SequenceOfCarrierNum());
			t.getCarriersNum().add(1080L);
			t.getCarriersNum().add(1181L);
			
			
	  	    t.setBorder( false);
		          
		    t.setSeriesId( 999L);
		    t.setRouteId( 21L);
		
	}

		private static void populateIncludedTicket(IncludedOpenTicketType t) {
			
			
		    t.setProductOwnerNum( 23456L);    
		    t.setProductOwnerIA5("23456");    
		    t.setProductIdNum( 65535L);    
		    t.setProductIdIA5("123456"); 

			
			t.setExternalIssuerId(12L);
			t.setIssuerAutorizationId(13L);
			t.setStationCodeTable(CodeTableType.stationERA);    
			
			t.setValidRegion(new SequenceOfRegionalValidityType());
	        RegionalValidityType rv = new RegionalValidityType();
	        t.getValidRegion().add(rv);
	        rv.setZones(new ZoneType());
	        rv.getZones().setZoneId(new SequenceOfUnrestrictedLong());
	        rv.getZones().getZoneId().add(100L);
	        
			t.setValidFromDay( 0L);
			t.setValidFromTime(1000L);
			t.setValidUntilDay(1L);
			t.setValidUntilTime(1000L);
			t.setClassCode(TravelClassType.second);
			t.setServiceLevel("A");
			
	        t.setIncludedCarriersNum(new SequenceOfCarrierNum());
	        t.getIncludedCarriersNum().add(1080L);
	        t.getIncludedCarriersNum().add(1181L);
	          
	        t.setIncludedCarriersIA5(new SequenceOfStringIA5());
	        t.getIncludedCarriersIA5().add("1080");
	        t.getIncludedCarriersIA5().add("1181");
	    
	              
		    t.setIncludedServiceBrands(new SequenceOfServiceBrands());
		    t.getIncludedServiceBrands().add(108L);
		    t.getIncludedServiceBrands().add(118L);
		       
		    t.setExcludedServiceBrands(new SequenceOfServiceBrands());
		    t.getExcludedServiceBrands().add(108L);
		    t.getExcludedServiceBrands().add(118L);
	               	
	        t.setTariffs(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        t.getTariffs().add(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
		   	                            
			t.setInfoText("included ticket");
					
			t.setIncludedTransportTypes(new SequenceOfTransportTypes());
			t.getIncludedTransportTypes().add(10L);
			t.getIncludedTransportTypes().add(11L);
					
			t.setExcludedTransportTypes(new SequenceOfTransportTypes());
			t.getExcludedTransportTypes().add(10L);
			t.getExcludedTransportTypes().add(18L);				
	        		    	        
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			t.setExtension(e1);		 
			
		}


		private static void populateCarCarriage(DocumentData d2) {
			
			d2.setTicket(new TicketDetailData());
			d2.setToken(new TokenType());
			
			
			assert(d2.getToken() != null);
			d2.getToken().setToken(UperEncoder.bytesFromHexString("82DA"));
			d2.getToken().setTokenProviderIA5("VDV");
			d2.getToken().setTokenProviderNum(123L);
			d2.getToken().setTokenSpecification("TEST");
			
			CarCarriageReservationData r = new CarCarriageReservationData();
			d2.getTicket().setCarCarriageReservation(r);
			
		    r.setTrainNum( 123L); 						
		    r.setTrainIA5("123");     						
		    r.setReferenceIA5("810123456789");	
		    r.setReferenceNum( 810123456789L);			     		        															
		    r.setProductOwnerNum( 23456L);    
		    r.setProductOwnerIA5("23456");    
		    r.setProductIdNum( 65535L);
		    r.setProductIdIA5("123456"); 
		    r.setServiceBrand( 100L);
		    r.setServiceBrandAbrUTF8("AZ");      					   	
		    r.setServiceBrandNameUTF8("special train");     					    
	        
	        r.setBeginLoadingDate(  10L);
	        r.setBeginLoadingTime(    0L);
	        r.setEndLoadingTime(      500L);
	        r.setLoadingUTCOffset(    30L);
	        
		    r.setStationCodeTable(CodeTableType.stationERA);
		    r.setFromStationNum( 8100001L);
		    r.setFromStationIA5("8100001");
		    r.setToStationNum(   8000002L);
		    r.setToStationIA5(  "8100002");
		    r.setFromStationNameUTF8(  "A-STATION"); 
		    r.setToStationNameUTF8(    "B-STATION");         
	         
		    r.setCoach("21");
		    r.setPlace("41");
		    r.setCompartmentDetails(new CompartmentDetailsType());
		    	
		    r.getCompartmentDetails().setCoachType(	1L);    
		    r.getCompartmentDetails().setCompartmentType(	99L);    
		    r.getCompartmentDetails().setSpecialAllocation(	50L);   				    	
		    r.getCompartmentDetails().setCoachTypeDescr("xwz");  
			r.getCompartmentDetails().setCompartmentTypeDescr("xwz");  			
			r.getCompartmentDetails().setSpecialAllocationDescr("xwz");  		
			r.getCompartmentDetails().setPosition(CompartmentPositionType.upperLevel);   	
				        
			r.setNumberPlate(          "AD-DE-123");
			r.setTrailerPlate(         "DX-AB-123");
			r.setCarCategory(          3L);
	        r.setBoatCategory(         5L);
	        r.setTextileRoof(         false);
	        r.setRoofRackType(RoofRackType.bicycleRack);
	        r.setRoofRackHeight(      20L);
	        r.setAttachedBoats(       2L);
	        r.setAttachedBicycles(    1L);
	        r.setAttachedSurfboards( 2L);
	        r.setLoadingListEntry(   421L);
	        r.setLoadingDeck(LoadingDeckType.upper);
	        
		    r.setCarrierNum(new SequenceOfCarrierNum());
		    r.getCarrierNum().add(1080L);
		    r.getCarrierNum().add(1181L);
		 
		    r.setCarrierIA5(new SequenceOfStringIA5());
		    r.getCarrierIA5().add("1080");
		    r.getCarrierIA5().add("1181");
	        

	        r.setPriceType(PriceTypeType.travelPrice);
	        r.setPrice(12345L);
	        
	        r.setInfoText("car carriage");
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			r.setExtension(e1);		
			
	        TariffType ta = new TariffType();
	        r.setTariff(ta);
	        ta.setNumberOfPassengers(1L);
	        ta.setRestrictedToCountryOfResidence(false);
	        ta.setTariffIdNum(72L);
	        ta.setTariffDesc("Large Car Full Fare"); 
			
		    r.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    r.getVatDetails().add(v1);
		    v1.setAmount(10L);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);
		    v1.setVatId("IUDGTE");  
			
		}


		private static void populateReservation(DocumentData d) {
						
			d.setTicket(new TicketDetailData());
			ReservationData r = new ReservationData();
			d.getTicket().setReservation(r);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			r.setExtension(e1);		
			
		    r.setTrainNum( 12345L); 						
		    r.setTrainIA5("12345");     						
		    r.setDepartureDate(  2L); 
		    r.setReferenceIA5("810123456789");	
		    r.setReferenceNum(80123456789L);			     		        															
		    r.setProductOwnerNum( 23456L);    
		    r.setProductOwnerIA5("23456");    
		    r.setProductIdNum( 65535L);    
		    r.setProductIdIA5("123456"); 
		    r.setServiceBrand( 12L);
		    r.setServiceBrandAbrUTF8("TGV");      					   	
		    r.setServiceBrandNameUTF8("Lyria");     					    
		    r.setService(ServiceType.couchette);		
		    
		    r.setStationCodeTable(CodeTableType.stationUIC);
		    r.setFromStationNum( 8100001L);
		    r.setFromStationIA5("8100001");
		    r.setToStationNum( 8000002L);
		    r.setToStationIA5(  "8100002");
		    r.setFromStationNameUTF8(  "A-STATION"); 
		    r.setToStationNameUTF8(    "B-STATION"); 
		    r.setDepartureTime( 		 1439L);           
		    r.setDepartureUTCOffset(   -60L); 
		    r.setArrivalDate(		     20L);  			
		    r.setArrivalTime(		     0L);
		    r.setArrivalUTCOffset(     10L);  
		    r.setCarrierNum(new SequenceOfCarrierNum());
		    r.getCarrierNum().add(1080L);
		    r.getCarrierNum().add(1181L);
		 
		    r.setCarrierIA5(new SequenceOfStringIA5());
		    r.getCarrierIA5().add("1080");
		    r.getCarrierIA5().add("1181");
		    r.setClassCode(TravelClassType.first);
		    r.setServiceLevel("A");
		    
		    r.setPlaces(new PlacesType());
		    r.getPlaces().setCoach("31A");
		    r.getPlaces().setPlaceString("31-47");
		    r.getPlaces().setPlaceDescription("Window");
		    r.getPlaces().setPlaceIA5(new SequenceOfStringIA5());
		    r.getPlaces().getPlaceIA5().add("31A");
		    r.getPlaces().getPlaceIA5().add("31B");
		    r.getPlaces().setPlaceNum(new SequenceOfPlaceNum());
		    r.getPlaces().getPlaceNum().add(31L);
		    r.getPlaces().getPlaceNum().add(32L);
		    
		    r.setAdditionalPlaces(new PlacesType());
		    r.getAdditionalPlaces().setCoach("31A");
		    r.getAdditionalPlaces().setPlaceString("31-47");
		    r.getAdditionalPlaces().setPlaceDescription("Window");
		    r.getAdditionalPlaces().setPlaceIA5(new SequenceOfStringIA5());
		    r.getAdditionalPlaces().getPlaceIA5().add("31A");
		    r.getAdditionalPlaces().getPlaceIA5().add("31B");
		    r.getAdditionalPlaces().setPlaceNum(new SequenceOfPlaceNum());
		    r.getAdditionalPlaces().getPlaceNum().add(31L);
		    r.getAdditionalPlaces().getPlaceNum().add(32L);
		    
		    r.setBicyclePlaces(new PlacesType());
		    r.getBicyclePlaces().setCoach("31A");
		    r.getBicyclePlaces().setPlaceString("31-47");
		    r.getBicyclePlaces().setPlaceDescription("Window");
		    r.getBicyclePlaces().setPlaceIA5(new SequenceOfStringIA5());
		    r.getBicyclePlaces().getPlaceIA5().add("31A");
		    r.getBicyclePlaces().getPlaceIA5().add("31B");
		    r.getBicyclePlaces().setPlaceNum(new SequenceOfPlaceNum());
		    r.getBicyclePlaces().getPlaceNum().add(31L);
		    r.getBicyclePlaces().getPlaceNum().add(32L);
		    
		    
			r.setCompartmentDetails(new CompartmentDetailsType());
			r.getCompartmentDetails().setCoachType( 1L); 
			r.getCompartmentDetails().setCompartmentType( 			99L); 
			r.getCompartmentDetails().setSpecialAllocation( 		50L);		  	
			r.getCompartmentDetails().setCoachTypeDescr("xwz");    
			r.getCompartmentDetails().setCompartmentTypeDescr(	"xwz");			
			r.getCompartmentDetails().setSpecialAllocationDescr(	"xwz"); 		
			r.getCompartmentDetails().setPosition(CompartmentPositionType.upperLevel); 	
			
		    r.setNumberOfOverbooked(  200L);
		    r.setBerth(new SequenceOfBerthDetailData());
		    BerthDetailData b = new BerthDetailData();
		    r.getBerth().add(b);
		    b.setBerthType(BerthTypeType.single);
		    b.setGender(CompartmentGenderType.female);
		    b.setNumberOfBerths( 999L);
		    
		    
	        r.setTariff(new SequenceOfTariffType());
	        TariffType ta = new TariffType();
	        r.getTariff().add(ta);
	        ta.setNumberOfPassengers(1L);
 
		    ta.setPassengerType(PassengerType.senior);
	       	ta.setAgeBelow(           64L);
	       	ta.setAgeAbove(           60L);
	       	ta.setTraverlerid(new SequenceOfTravelerId());
	       	ta.getTraverlerid().add(1L);
	       	
	       	ta.setRestrictedToCountryOfResidence( false);
	       	ta.setRestrictedToRouteSection(new RouteSectionType());
	       	ta.getRestrictedToRouteSection().setStationCodeTable(CodeTableType.stationERA);              
	      	ta.getRestrictedToRouteSection().setFromStationNum( 123L);
	      	ta.getRestrictedToRouteSection().setFromStationIA5(      	"123");
			ta.getRestrictedToRouteSection().setToStationNum(       	234L);     					                
			ta.getRestrictedToRouteSection().setToStationIA5(        	"234"); 				                 
			ta.getRestrictedToRouteSection().setFromStationNameUTF8( 	"A");    
		    ta.getRestrictedToRouteSection().setToStationNameUTF8(   	"B");

		    ta.setSeriesDataDetails(new SeriesDetailType());
		    ta.getSeriesDataDetails().setSupplyingCarrier(12345L);		
		    ta.getSeriesDataDetails().setOfferIdentification(	99L);  		
		    ta.getSeriesDataDetails().setSeries(	23456L);                     
	              
	        ta.setTariffIdNum(       72L);
	       	ta.setTariffIdIA5(       "72");
	       	ta.setTariffDesc(         "Leasure Fare");
	       	ta.setReductionCard(new SequenceOfCardReferenceType());
	       	ta.getReductionCard().add(new CardReferenceType());
	            
	        ta.getReductionCard().get(0).setCardIssuerNum(1234L);
	        ta.getReductionCard().get(0).setCardIssuerIA5(   "1234");
	        ta.getReductionCard().get(0).setCardIdNum(5678L);
	        ta.getReductionCard().get(0).setCardIdIA5(  "5678");
			ta.getReductionCard().get(0).setCardName(   "testcard");
			ta.getReductionCard().get(0).setCardType(123L);
			ta.getReductionCard().get(0).setLeadingCardIdNum(3456L);                                     
			ta.getReductionCard().get(0).setLeadingCardIdIA5("3456");
			ta.getReductionCard().get(0).setTrailingCardIdNum(100L);
			ta.getReductionCard().get(0).setTrailingCardIdIA5("100");
		       
		    
			r.setPriceType(PriceTypeType.travelPrice);
	        r.setPrice( 12345L);
	        
		    r.setVatDetails(new SequenceOfVatDetail());
		    VatDetailType v1 = new VatDetailType();
		    r.getVatDetails().add(v1);
		    v1.setAmount(10L);
		    v1.setCountry(80L);
		    v1.setPercentage(70L);
		    v1.setVatId("IUDGTE");  
	        	        
	        r.setTypeOfSupplement(	9L);		
	        r.setNumberOfSupplements(	2L);
	        
			r.setLuggage(new LuggageRestrictionType());
	        
			r.getLuggage().setMaxHandLuggagePieces(    2L);
			r.getLuggage().setMaxNonHandLuggagePieces( 1L);
			r.getLuggage().setRegisteredLuggage(new SequenceOfRegisteredLuggageType());
			r.getLuggage().getRegisteredLuggage().add(new RegisteredLuggageType());
			r.getLuggage().getRegisteredLuggage().add(new RegisteredLuggageType());
			r.getLuggage().getRegisteredLuggage().get(0).setRegistrationId("IODHUV");
			r.getLuggage().getRegisteredLuggage().get(0).setMaxWeight(       20L);
		    r.getLuggage().getRegisteredLuggage().get(0).setMaxSize(        100L);	  
			r.getLuggage().getRegisteredLuggage().get(1).setRegistrationId("XXDHUV");
			r.getLuggage().getRegisteredLuggage().get(1).setMaxWeight(       21L);
		    r.getLuggage().getRegisteredLuggage().get(1).setMaxSize( 101L);	
		    
		    r.setInfoText("reservation");
		    
			
		}


		private static void populateTravelerData(TravelerData td) {
			
			td.setGroupName("myGroup");
			td.setPreferedLanguage("EN");
			td.setTraveler(new SequenceOfTravelerType());
			TravelerType t = new TravelerType();
			td.getTraveler().add(t);
				 
			t.setCountryOfIdCard(103L);
			t.setCountryOfPassport(102L);
			t.setCountryOfResidence(101L);
			t.setCustomerIdIA5("DZE5gT");
			t.setCustomerIdNum(12345L);
			t.setYearOfBirth(1901L);
			t.setDayOfBirth(31L);
			t.setFirstName("John");
			t.setGender(GenderType.male);
			t.setIdCard("12345");
			t.setLastName("Dow");
			t.setMonthOfBirth(12L);
			t.setPassengerType(PassengerType.senior);
			t.setPassengerWithReducedMobility(false);
			t.setPassportId("JDTS");
			t.setSecondName("Little");
			t.setTitle("PhD");
			t.setTicketHolder(true);
			t.setStatus(new SequenceOfCustomerStatusType());
			CustomerStatusType cs = new CustomerStatusType();
			t.getStatus().add(cs);
			cs.setCustomerStatus(1L);
			cs.setCustomerStatusDescr("senior");
		}


		private static void populateIssuingData(IssuingData id) {	
			
			id.setActivated(true);
			id.setCurrency("SRF");
			id.setCurrencyFract(3L);
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			id.setExtension(e1);
			
			id.setIssuedOnLine(12L);
			id.setIssuedOnTrainIA5("123");
			id.setIssuedOnTrainNum(123L);
			id.setIssuerName("name");
			id.setIssuerIA5("1");
			id.setIssuerNum(32000L);
			id.setIssuerPNR("issuerTestPNR");
			id.setIssuingDay(1L);
			id.setIssuingTime(600L);
			id.setIssuingYear(2018L);
			id.setSecurePaperTicket(false);
			id.setSecurityProviderIA5("1");
			id.setSecurityProviderNum(1L);
			id.setSpecimen(true);
			id.setPointOfSale(new GeoCoordinateType());
			id.getPointOfSale().setGeoUnit(GeoUnitType.microDegree);
			id.getPointOfSale().setCoordinateSystem(GeoCoordinateSystemType.wgs84);
			id.getPointOfSale().setAccuracy(GeoUnitType.microDegree);
			id.getPointOfSale().setHemisphereLatitude(HemisphereLatitudeType.north);
			id.getPointOfSale().setHemisphereLongitude(HemisphereLongitudeType.east);
			id.getPointOfSale().setLatitude(56789L);
			id.getPointOfSale().setLongitude(12345L);
			
		}


		private static void populateExtension(List<ExtensionData> extensions) {
			
			ExtensionData e1 = new ExtensionData();
			e1.setExtensionId("1");
			e1.setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			extensions.add(e1);
			
			ExtensionData e2 = new ExtensionData();
			e2.setExtensionId("2");
			e2.setExtensionData(UperEncoder.bytesFromHexString("83DA"));
			extensions.add(e2);
			
		}


		private static void populateControlDetail(ControlData controlDetail) {

			controlDetail.setAgeCheckRequired(false);
			controlDetail.setIdentificationByPassportId(false);
			controlDetail.setIdentificationByIdCard(false);
			controlDetail.setOnlineValidationRequired(false);
			controlDetail.setIdentificationItem(12L);
			controlDetail.setPassportValidationRequired(false);
			controlDetail.setRandomDetailedValidationRequired(50L);
			controlDetail.setExtension(new ExtensionData());
			controlDetail.getExtension().setExtensionId("1");
			controlDetail.getExtension().setExtensionData(UperEncoder.bytesFromHexString("82DA"));
			
			controlDetail.setReductionCardCheckRequired(false);
			controlDetail.setInfoText("control");
			controlDetail.setIdentificationByCardReference(new SequenceOfCardReferenceType());
		
			CardReferenceType cr = new CardReferenceType();
			controlDetail.getIdentificationByCardReference().add(cr);
			cr.setCardIdIA5("5678");
			cr.setCardIdNum(5678L);
			cr.setCardIssuerIA5("1234");
			cr.setCardIssuerNum(1234L);
			cr.setCardName("testcard");
			cr.setCardType(123L);
			cr.setLeadingCardIdIA5("3456");
			cr.setLeadingCardIdNum(3456L);
			cr.setTrailingCardIdIA5("100");
			cr.setTrailingCardIdNum(100L);
			
			controlDetail.setIncludedTickets(new SequenceOfTicketLinkType());
			TicketLinkType tl = new TicketLinkType();		
			controlDetail.getIncludedTickets().add(tl);
			tl.setIssuerName("XYZ");
			tl.setIssuerPNR("LDWDUR45");
			tl.setProductOwnerIA5("IEFHU");
			tl.setProductOwnerNum(1080L);
			tl.setLinkMode(LinkMode.issuedTogether);
			tl.setReferenceIA5("KDJET");
			tl.setReferenceNum(801234567890L);
			tl.setTicketType(TicketType.openTicket);
			
		}

	}
