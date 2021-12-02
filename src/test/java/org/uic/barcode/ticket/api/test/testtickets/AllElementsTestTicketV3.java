package org.uic.barcode.ticket.api.test.testtickets;

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
				    productIdNum		 123456,    
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
				productIdNum		 123456,    
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
				  productIdNum		   123456,    
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
				            productIdNum		 123456,    
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
				  productIdNum		  123456,    
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
				  productIdNum		 123456,    
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
				  productIdNum		 123456,    
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
				  productIdNum		  123456,    
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
				  productIdNum		  123456,    
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
				  productIdNum		 123456,    
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

		
		


	}
