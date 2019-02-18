z01001Zeidon    c:\temp\drvshiftbeforedeleting.por mDrvShiftRoutes 2018/03/26 10:44:47
mOIFLAGS    0
eDriverShift 1,0
aID        12537
aDayOfWeek Tuesday
aExpectedStartDateTime 20180320163000
aExpectedEndDateTime 20180321043000
aFK_ID_DRIVER 128
aFKC_ID_VEHICLECOMBINATION 5441

eDeliveryRoute 2,0
aID        27587
aScheduledDateTime 20180320163000
aExpectedStartDateTime 20180320163000
aExpectedEndDateTime 20180320181700
aSortOrderWithinShift 1
awChangeOrderNumber,2 1
aFK_ID_CARRIER 22
aFK_ID_EMPLOYEESHIFT 12537
aFK_ID_VEHICLECOMBINATION 5441
aFK_ID_DRIVER 128

eDeliveryLeg 3,0
aID        176114
aType      H-T
aTripStandardInMinutes 19
aDistanceInMiles 10.045111660680776
aExpectedStartDateTime 20180320163000
aExpectedEndDateTime 20180320164900
aSortOrder 1
aFKTO_ID_ADDRESS 1988
aFKFROM_ID_ADDRESS 13702
aFK_ID_DELIVERYROUTE 27587

eFromDeliveryLegAddress 4,0
aID        13702
aLine1     6910 Mission Gorge Road
aCity      San Diego
aStateProvince CA
aCountry   United States
aPostalCode 92120
aLongitude -117.09406
aLatitude  32.80004
aModifiedDateTime 20180116110404
aLastModifiedBy admin

eInitial_HB_Carrier 5,0
aID        22
aFK_ID_ORGANIZATION 962

eToDeliveryLegAddress 4,0
aID        1988
aType      B
aLine1     2351 Harbor Dr
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92113-3637
aCounty    San Diego
aLongitude -117.14082
aLatitude  32.6936
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eToDeliveryLegTerminal 5,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eToDeliveryLegTerminalAdd 6,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eDeliveryLeg 3,0
aID        176115
aType      T-D
aTripStandardInMinutes 43
aDistanceInMiles 34.3
aExpectedStartDateTime 20180320164900
aExpectedEndDateTime 20180320173200
aSortOrder 2
aFKTO_ID_ADDRESS 13812
aFKFROM_ID_ADDRESS 1988
aFK_ID_DELIVERYROUTE 27587

eFromDeliveryLegAddress 4,0
aID        1988
aType      B
aLine1     2351 Harbor Dr
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92113-3637
aCounty    San Diego
aLongitude -117.14082
aLatitude  32.6936
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eFromDeliveryLegTerminal 5,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eFromDeliveryLegTerminalAdd 6,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eToDeliveryLegAddress 4,0
aID        13812
aLine1     725 Center Dr
aCity      San Marcos
aStateProvince CA
aPostalCode 92069-3536
aCounty    San Diego
aLongitude -117.12092
aLatitude  33.13471

eToDeliveryLegLocation 5,0
aID        11630
aName      Costco # 1080**
aFKD_ID_ADDRESS 13812

eToDeliveryLegLocationAdd 6,0
aID        13812
aLine1     725 Center Dr
aCity      San Marcos

eFuelStop  3,1032
aID        53053
aExpectedLoadTimeMinutes 0
aSortOrder 1
aFK_ID_DELIVERYROUTE 27587
aFK_ID_TERMINAL 28

eFuelStopTerminal 4,1040
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eFuelStopTerminalAddress 5,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eFuelRequest 4,1040
aID        47712
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25013
aFK_ID_LOADINGACCOUNT 396
aFK_ID_TERMINAL 28
aFK_ID_DELIVERYLOCATION 11630
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25013
aFK_ID_DELIVERYROUTE 27587
aFK_ID_Z_ORDER 24513

eFuelStopOrder 6,1024
aID        24513
aType      FTL
aInternalOrderNumber 54513
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eFuelType  5,1024
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,1024
aID        396
aLoadingNumber 8301397
aFKBILL_ID_ORGANIZATION 963
aFK_ID_TERMINAL 28
aFKSUP_ID_ORGANIZATION 1010
aFK_ID_CARRIER 22

eFuelStop  3,4
aID        53054
aExpectedUnloadTimeMinutes 45
aSortOrder,2 1
aFK_ID_DELIVERYROUTE 27587
aFK_ID_DELIVERYLOCATION 11630

eFuelStopDeliveryLocation 4,0
aID        11630
aName      Costco # 1080**
aFKD_ID_ADDRESS 13812

eFuelStopDeliveryLocationAddress 5,0
aID        13812
aLine1     725 Center Dr
aCity      San Marcos

eFuelRequest 4,0
aID        47712
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25013
aFK_ID_LOADINGACCOUNT 396
aFK_ID_TERMINAL 28
aFK_ID_DELIVERYLOCATION 11630
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25013
aFK_ID_DELIVERYROUTE 27587
aFK_ID_Z_ORDER 24513

eFuelStopOrder 6,0
aID        24513
aType      FTL
aInternalOrderNumber 54513
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        396
aLoadingNumber 8301397
aFKBILL_ID_ORGANIZATION 963
aFK_ID_TERMINAL 28
aFKSUP_ID_ORGANIZATION 1010
aFK_ID_CARRIER 22

eLoadRequest 3,0
aID        25013
aStatus    Scheduled
aSpecialDriverInstructions  ****** TEST DATABASE DB2 ******
aFK_ID_DELIVERYROUTE 27587
aFK_ID_Z_ORDER 24513

eOrder     4,0
aID        24513
aInternalOrderNumber 54513
aType      FTL
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eRouteVehicleCombination 3,0
aID        5441
aCombinationDateTime 20180117155820
aFK_ID_VEHICLEPOWERUNIT 110

eRouteVehiclePowerUnit 4,0
aID        110
aTruckIdentifier Truck # 16
aFK_ID_ADDRESS 13702
aFK_ID_CARRIER 22

eRouteVehicleDeliveryTank 4,0
aID        156
aType      T
aTruckTrailerIdentifier TRAILER # 116
aFK_ID_CARRIER 22

eDeliveredByDriver 3,0
aID        128
aStatus    A
aDLNumber  U2013514
aFK_ID_PERSON 330
aFK_ID_CARRIER 22

eDeliveredByDriverPerson 4,0
aID        330
aLastName  Bennett**
aFirstName Gary

eDeliveryRoute 2,6
aScheduledDateTime,2 20180320190000
aExpectedStartDateTime,2 20180320203000
aExpectedEndDateTime,2 20180320221900
aSortOrderWithinShift,2 2
awChangeOrderNumber,2 2

eFuelStop  3,6
aSortOrder,2 1

eFuelStopTerminal 4,32
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eFuelStopTerminalAddress 5,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eFuelRequest 4,32
aID        47700
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25004
aFK_ID_LOADINGACCOUNT 396
aFK_ID_TERMINAL 28
aFK_ID_DELIVERYLOCATION 11844
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25004
aFK_ID_DELIVERYROUTE 27606
aFK_ID_Z_ORDER 24504

eFuelStopOrder 6,0
aID        24504
aType      FTL
aInternalOrderNumber 54504
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        396
aLoadingNumber 8301397
aFKBILL_ID_ORGANIZATION 963
aFK_ID_TERMINAL 28
aFKSUP_ID_ORGANIZATION 1010
aFK_ID_CARRIER 22

eLoadRequest 3,6
aStatus,2  Scheduled
aSpecialDriverInstructions,2  ****** TEST DATABASE DB2 ******

eOrder     4,32
aID        24504
aInternalOrderNumber 54504
aType      FTL
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eRouteVehicleCombination 3,32
aID        5459
aCombinationDateTime 20180117160205
aFK_ID_VEHICLEPOWERUNIT 98

eRouteVehiclePowerUnit 4,0
aID        98
aTruckIdentifier Truck # 4
aType      B
aActiveInactiveStatus A
aPlateNumber 69027H1
aSerialOrVIN 1NPVL79XXCD167557
aMake      PETERBILT
aModel     384
aYear      2012
aColor     Green
aEngine    Cummins
aPlateState CA
aFK_ID_ADDRESS 13702
aFK_ID_CARRIER 22

eDeliveredByDriver 3,32
aID        128
aStatus    A
aDLNumber  U2013514
aFK_ID_PERSON 330
aFK_ID_CARRIER 22

eDeliveredByDriverPerson 4,0
aID        330
aLastName  Bennett**
aFirstName Gary

eDeliveryRoute 2,6
aScheduledDateTime,2 20180320163000
aExpectedStartDateTime,2 20180320163000
aExpectedEndDateTime,2 20180320181700
aSortOrderWithinShift,2 3
awChangeOrderNumber,2 3

eFuelStop  3,6
aSortOrder,2 1

eFuelStopDeliveryLocation 4,32

eFuelStopDeliveryLocationAddress 5,0

eFuelRequest 4,32

eFuelStopLoadRequest 5,0

eFuelStopOrder 6,0

eFuelType  5,0

eLoadingAccount 5,0

eFuelStop  3,6
aSortOrder,2 2

eFuelStopDeliveryLocation 4,32

eFuelStopDeliveryLocationAddress 5,0

eFuelRequest 4,32

eFuelStopLoadRequest 5,0

eFuelStopOrder 6,0

eFuelType  5,0

eLoadingAccount 5,0

eLoadRequest 3,6
aStatus,2  Scheduled
aSpecialDriverInstructions,2  ****** TEST DATABASE DB2 ******

eOrder     4,32

eRouteVehicleCombination 3,32

eRouteVehiclePowerUnit 4,0

eRouteVehicleDeliveryTank 4,0

eDeliveredByDriver 3,32

eDeliveredByDriverPerson 4,0

eDeliveryRoute 2,4
aID        27594
aScheduledDateTime 20180320180900
aExpectedStartDateTime 20180320181700
aExpectedEndDateTime 20180320203000
aSortOrderWithinShift,2 4
awChangeOrderNumber,2 4
aFK_ID_CARRIER 22
aFK_ID_EMPLOYEESHIFT 12537
aFK_ID_VEHICLECOMBINATION 5441
aFK_ID_DRIVER 128

eDeliveryLeg 3,0
aID        176116
aTripStandardInMinutes 43
aDistanceInMiles 34.3
aExpectedStartDateTime 20180320181700
aExpectedEndDateTime 20180320190000
aSortOrder 1
aFKTO_ID_ADDRESS 1988
aFKFROM_ID_ADDRESS 13812
aFK_ID_DELIVERYROUTE 27594

eFromDeliveryLegAddress 4,0
aID        13812
aLine1     725 Center Dr
aCity      San Marcos
aStateProvince CA
aPostalCode 92069-3536
aCounty    San Diego
aLongitude -117.12092
aLatitude  33.13471

eFromDeliveryLegLocation 5,0
aID        11630
aName      Costco # 1080**
aFKD_ID_ADDRESS 13812

eFromDeliveryLegLocAddress 6,0
aID        13812
aLine1     725 Center Dr
aCity      San Marcos

eToDeliveryLegAddress 4,0
aID        1988
aType      B
aLine1     2351 Harbor Dr
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92113-3637
aCounty    San Diego
aLongitude -117.14082
aLatitude  32.6936
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eToDeliveryLegTerminal 5,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eToDeliveryLegTerminalAdd 6,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eDeliveryLeg 3,0
aID        176117
aTripStandardInMinutes 27
aDistanceInMiles 17.2
aExpectedStartDateTime 20180320190000
aExpectedEndDateTime 20180320192700
aSortOrder 2
aFKTO_ID_ADDRESS 14026
aFKFROM_ID_ADDRESS 1988
aFK_ID_DELIVERYROUTE 27594

eFromDeliveryLegAddress 4,0
aID        1988
aType      B
aLine1     2351 Harbor Dr
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92113-3637
aCounty    San Diego
aLongitude -117.14082
aLatitude  32.6936
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eFromDeliveryLegTerminal 5,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eFromDeliveryLegTerminalAdd 6,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eToDeliveryLegAddress 4,0
aID        14026
aLine1     101 Town Center Pkwy
aCity      Santee
aStateProvince CA
aPostalCode 92071-5802
aCounty    San Diego
aLongitude -116.98832
aLatitude  32.83981

eToDeliveryLegLocation 5,0
aID        11844
aName      Costco #403**
aFKD_ID_ADDRESS 14026

eToDeliveryLegLocationAdd 6,0
aID        14026
aLine1     101 Town Center Pkwy
aCity      Santee

eDeliveryLeg 3,0
aID        176334
aTripStandardInMinutes 18
aDistanceInMiles 7.6
aExpectedStartDateTime 20180320201200
aExpectedEndDateTime 20180320203000
aSortOrder 3
aFKTO_ID_ADDRESS 13702
aFKFROM_ID_ADDRESS 14026
aFK_ID_DELIVERYROUTE 27594

eFromDeliveryLegAddress 4,0

eFromDeliveryLegLocation 5,0

eFromDeliveryLegLocAddress 6,0

eFromDeliveryLegLocAddress 6,0

eToDeliveryLegAddress 4,0
aID        13702
aLine1     6910 Mission Gorge Road
aCity      San Diego
aStateProvince CA
aCountry   United States
aPostalCode 92120
aLongitude -117.09406
aLatitude  32.80004

eFinal_HB_Carrier 5,0
aID        22
aActiveInactiveStatus A
aIsRegisteredCarrierFlag Y
aLogInCarrierCode MTI
aSCACCode  MHXQ
aOrderNumberBaseNumber 30000
aTravelTimeComputationErrFactor 1.2
aFK_ID_ORGANIZATION 962

eFuelStop  3,0
aID        53067
aExpectedLoadTimeMinutes 0
aSortOrder 1
aFK_ID_DELIVERYROUTE 27594
aFK_ID_TERMINAL 28

eFuelStopTerminal 4,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eFuelStopTerminalAddress 5,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eFuelRequest 4,0
aID        47714
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25015
aFK_ID_LOADINGACCOUNT 396
aFK_ID_TERMINAL 28
aFK_ID_DELIVERYLOCATION 11844
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25015
aFK_ID_DELIVERYROUTE 27594
aFK_ID_Z_ORDER 24515

eFuelStopOrder 6,0
aID        24515
aType      FTL
aInternalOrderNumber 54515
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        396
aLoadingNumber 8301397
aFKBILL_ID_ORGANIZATION 963
aFK_ID_TERMINAL 28
aFKSUP_ID_ORGANIZATION 1010
aFK_ID_CARRIER 22

eFuelStop  3,0
aID        53068
aExpectedUnloadTimeMinutes 45
aSortOrder 2
aFK_ID_DELIVERYROUTE 27594
aFK_ID_DELIVERYLOCATION 11844

eFuelStopDeliveryLocation 4,0
aID        11844
aName      Costco #403**
aFKD_ID_ADDRESS 14026

eFuelStopDeliveryLocationAddress 5,0
aID        14026
aLine1     101 Town Center Pkwy
aCity      Santee

eFuelRequest 4,0
aID        47714
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25015
aFK_ID_LOADINGACCOUNT 396
aFK_ID_TERMINAL 28
aFK_ID_DELIVERYLOCATION 11844
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25015
aFK_ID_DELIVERYROUTE 27594
aFK_ID_Z_ORDER 24515

eFuelStopOrder 6,0
aID        24515
aType      FTL
aInternalOrderNumber 54515
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        396
aLoadingNumber 8301397
aFKBILL_ID_ORGANIZATION 963
aFK_ID_TERMINAL 28
aFKSUP_ID_ORGANIZATION 1010
aFK_ID_CARRIER 22

eLoadRequest 3,0
aID        25015
aStatus    Scheduled
aSpecialDriverInstructions  ****** TEST DATABASE DB2 ******
aFK_ID_DELIVERYROUTE 27594
aFK_ID_Z_ORDER 24515

eOrder     4,0
aID        24515
aInternalOrderNumber 54515
aType      FTL
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eRouteVehicleCombination 3,0
aID        5441
aCombinationDateTime 20180117155820
aFK_ID_VEHICLEPOWERUNIT 110

eRouteVehiclePowerUnit 4,0
aID        110
aTruckIdentifier Truck # 16
aFK_ID_ADDRESS 13702
aFK_ID_CARRIER 22

eRouteVehicleDeliveryTank 4,0
aID        156
aType      T
aTruckTrailerIdentifier TRAILER # 116
aFK_ID_CARRIER 22

eDeliveredByDriver 3,0
aID        128
aStatus    A
aDLNumber  U2013514
aFK_ID_PERSON 330
aFK_ID_CARRIER 22

eDeliveredByDriverPerson 4,0
aID        330
aLastName  Bennett**
aFirstName Gary

eDeliveryRoute 2,4
aID        27606
aScheduledDateTime 20180320190000
aExpectedStartDateTime 20180320203000
aExpectedEndDateTime 20180320221900
aSortOrderWithinShift,2 5
awChangeOrderNumber,2 5
aFK_ID_CARRIER 22
aFK_ID_EMPLOYEESHIFT 12537
aFK_ID_VEHICLECOMBINATION 5459
aFK_ID_DRIVER 128

eDeliveryLeg 3,0
aID        176118
aTripStandardInMinutes 19
aDistanceInMiles 10.045111660680776
aExpectedStartDateTime 20180320203000
aExpectedEndDateTime 20180320204900
aSortOrder 1
aFKTO_ID_ADDRESS 1988
aFKFROM_ID_ADDRESS 13702
aFK_ID_DELIVERYROUTE 27606

eFromDeliveryLegAddress 4,0

eInitial_HB_Carrier 5,0

eToDeliveryLegAddress 4,0
aID        1988
aType      B
aLine1     2351 Harbor Dr
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92113-3637
aCounty    San Diego
aLongitude -117.14082
aLatitude  32.6936
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eToDeliveryLegTerminal 5,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eToDeliveryLegTerminalAdd 6,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eDeliveryLeg 3,0
aID        176119
aTripStandardInMinutes 27
aDistanceInMiles 17.2
aExpectedStartDateTime 20180320204900
aExpectedEndDateTime 20180320211600
aSortOrder 2
aFKTO_ID_ADDRESS 14026
aFKFROM_ID_ADDRESS 1988
aFK_ID_DELIVERYROUTE 27606

eFromDeliveryLegAddress 4,0
aID        1988
aType      B
aLine1     2351 Harbor Dr
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92113-3637
aCounty    San Diego
aLongitude -117.14082
aLatitude  32.6936
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eFromDeliveryLegTerminal 5,0
aID        28
aName      Chevron San Diego**
aFK_ID_ORGANIZATION 37
aFK_ID_CUSTOMER 28
aFKP_ID_ADDRESS 1988

eFromDeliveryLegTerminalAdd 6,0
aID        1988
aLine1     2351 Harbor Dr
aCity      San Diego

eToDeliveryLegAddress 4,0
aID        14026
aLine1     101 Town Center Pkwy
aCity      Santee
aStateProvince CA
aPostalCode 92071-5802
aCounty    San Diego
aLongitude -116.98832
aLatitude  32.83981

eToDeliveryLegLocation 5,0
aID        11844
aName      Costco #403**
aFKD_ID_ADDRESS 14026

eToDeliveryLegLocationAdd 6,0
aID        14026
aLine1     101 Town Center Pkwy
aCity      Santee

eDeliveryLeg 3,0
aID        176335
aTripStandardInMinutes 18
aDistanceInMiles 7.6
aExpectedStartDateTime 20180320220100
aExpectedEndDateTime 20180320221900
aSortOrder 3
aFKTO_ID_ADDRESS 13702
aFKFROM_ID_ADDRESS 14026
aFK_ID_DELIVERYROUTE 27606

eFromDeliveryLegAddress 4,0

eFromDeliveryLegLocation 5,0

eFromDeliveryLegLocAddress 6,0

eFromDeliveryLegLocAddress 6,0

eToDeliveryLegAddress 4,0

eFinal_HB_Carrier 5,0

eFuelStop  3,1032
aID        53091
aExpectedLoadTimeMinutes 0
aSortOrder 1
aFK_ID_DELIVERYROUTE 27606
aFK_ID_TERMINAL 28

eFuelStopTerminal 4,1040

eFuelStopTerminalAddress 5,0

eFuelRequest 4,1040

eFuelStopLoadRequest 5,0

eFuelStopOrder 6,1024

eFuelType  5,1024

eLoadingAccount 5,1024

eFuelStop  3,4
aID        53092
aExpectedUnloadTimeMinutes 45
aSortOrder,2 1
aFK_ID_DELIVERYROUTE 27606
aFK_ID_DELIVERYLOCATION 11844

eFuelStopDeliveryLocation 4,0
aID        11844
aName      Costco #403**
aFKD_ID_ADDRESS 14026

eFuelStopDeliveryLocationAddress 5,0
aID        14026
aLine1     101 Town Center Pkwy
aCity      Santee

eFuelRequest 4,0
aID        47700
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25004
aFK_ID_LOADINGACCOUNT 396
aFK_ID_TERMINAL 28
aFK_ID_DELIVERYLOCATION 11844
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25004
aFK_ID_DELIVERYROUTE 27606
aFK_ID_Z_ORDER 24504

eFuelStopOrder 6,0
aID        24504
aType      FTL
aInternalOrderNumber 54504
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 963

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        396
aLoadingNumber 8301397
aFKBILL_ID_ORGANIZATION 963
aFK_ID_TERMINAL 28
aFKSUP_ID_ORGANIZATION 1010
aFK_ID_CARRIER 22

eLoadRequest 3,0
aID        25004
aStatus    Scheduled
aSpecialDriverInstructions  ****** TEST DATABASE DB2 ******
aFK_ID_DELIVERYROUTE 27606
aFK_ID_Z_ORDER 24504

eOrder     4,0

eRouteVehicleCombination 3,0

eRouteVehiclePowerUnit 4,0

eDeliveredByDriver 3,0

eDeliveredByDriverPerson 4,0

eDeliveryRoute 2,4
aID        27619
aScheduledDateTime 20180320195500
aExpectedStartDateTime 20180320221900
aExpectedEndDateTime 20180320235100
aSortOrderWithinShift,2 6
awChangeOrderNumber,2 6
aFK_ID_CARRIER 22
aFK_ID_EMPLOYEESHIFT 12537
aFK_ID_VEHICLECOMBINATION 5441
aFK_ID_DRIVER 128

eDeliveryLeg 3,0
aID        176120
aTripStandardInMinutes 8
aDistanceInMiles 2.1
aExpectedStartDateTime 20180320221900
aExpectedEndDateTime 20180320222700
aSortOrder 1
aFKTO_ID_ADDRESS 1984
aFKFROM_ID_ADDRESS 13702
aFK_ID_DELIVERYROUTE 27619

eFromDeliveryLegAddress 4,0

eInitial_HB_Carrier 5,0

eToDeliveryLegAddress 4,0
aID        1984
aType      B
aLine1     9950 San Diego Mission Rd
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92108-1705
aCounty    San Diego
aLongitude -117.11638
aLatitude  32.78674
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eToDeliveryLegTerminal 5,0
aID        23
aName      Kinder Morgan Mission Valley**
aFK_ID_ORGANIZATION 28
aFK_ID_CUSTOMER 19
aFKP_ID_ADDRESS 1984

eToDeliveryLegTerminalAdd 6,0
aID        1984
aLine1     9950 San Diego Mission Rd
aCity      San Diego

eDeliveryLeg 3,0
aID        176121
aTripStandardInMinutes 18
aDistanceInMiles 11.9
aExpectedStartDateTime 20180320222700
aExpectedEndDateTime 20180320224500
aSortOrder 2
aFKTO_ID_ADDRESS 13815
aFKFROM_ID_ADDRESS 1984
aFK_ID_DELIVERYROUTE 27619

eFromDeliveryLegAddress 4,0
aID        1984
aType      B
aLine1     9950 San Diego Mission Rd
aCity      San Diego
aStateProvince CA
aCountry   US
aPostalCode 92108-1705
aCounty    San Diego
aLongitude -117.11638
aLatitude  32.78674
aModifiedDateTime 20171115160039
aLastModifiedBy PS System Standardization

eFromDeliveryLegTerminal 5,0
aID        23
aName      Kinder Morgan Mission Valley**
aFK_ID_ORGANIZATION 28
aFK_ID_CUSTOMER 19
aFKP_ID_ADDRESS 1984

eFromDeliveryLegTerminalAdd 6,0
aID        1984
aLine1     9950 San Diego Mission Rd
aCity      San Diego

eToDeliveryLegAddress 4,0
aID        13815
aLine1     4605 Morena Blvd
aCity      San Diego
aStateProvince CA
aPostalCode 92117-3650
aCounty    San Diego
aLongitude -117.22753
aLatitude  32.82286

eToDeliveryLegLocation 5,0
aID        11633
aName      Costco # 401**
aFKD_ID_ADDRESS 13815

eToDeliveryLegLocationAdd 6,0
aID        13815
aLine1     4605 Morena Blvd
aCity      San Diego

eDeliveryLeg 3,0
aID        176122
aTripStandardInMinutes 21
aDistanceInMiles 13.6
aExpectedStartDateTime 20180320233000
aExpectedEndDateTime 20180320235100
aSortOrder 3
aFKTO_ID_ADDRESS 13702
aFKFROM_ID_ADDRESS 13815
aFK_ID_DELIVERYROUTE 27619

eFromDeliveryLegAddress 4,0
aID        13815
aLine1     4605 Morena Blvd
aCity      San Diego
aStateProvince CA
aPostalCode 92117-3650
aCounty    San Diego
aLongitude -117.22753
aLatitude  32.82286

eFromDeliveryLegLocation 5,0
aID        11633
aName      Costco # 401**
aFKD_ID_ADDRESS 13815

eFromDeliveryLegLocAddress 6,0
aID        13815
aLine1     4605 Morena Blvd
aCity      San Diego

eToDeliveryLegAddress 4,0
aID        13702
aLine1     6910 Mission Gorge Road
aCity      San Diego
aStateProvince CA
aCountry   United States
aPostalCode 92120
aLongitude -117.09406
aLatitude  32.80004
aModifiedDateTime 20180116110404
aLastModifiedBy admin

eFinal_HB_Carrier 5,0
aID        22
aFK_ID_ORGANIZATION 962

eFuelStop  3,0
aID        53117
aExpectedLoadTimeMinutes 0
aSortOrder 1
aFK_ID_DELIVERYROUTE 27619
aFK_ID_TERMINAL 23

eFuelStopTerminal 4,0
aID        23
aName      Kinder Morgan Mission Valley**
aFK_ID_ORGANIZATION 28
aFK_ID_CUSTOMER 19
aFKP_ID_ADDRESS 1984

eFuelStopTerminalAddress 5,0
aID        1984
aLine1     9950 San Diego Mission Rd
aCity      San Diego

eFuelRequest 4,0
aID        47717
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25018
aFK_ID_LOADINGACCOUNT 440
aFK_ID_TERMINAL 23
aFK_ID_DELIVERYLOCATION 11633
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25018
aFK_ID_DELIVERYROUTE 27619
aFK_ID_Z_ORDER 24518

eFuelStopOrder 6,0
aID        24518
aType      FTL
aInternalOrderNumber 54518
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 972

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        440
aLoadingNumber 473-94111
aFKBILL_ID_ORGANIZATION 972
aFK_ID_TERMINAL 23
aFKSUP_ID_ORGANIZATION 992
aFKCON_ID_ORGANIZATION 972
aFK_ID_CARRIER 22

eFuelStop  3,0
aID        53118
aExpectedUnloadTimeMinutes 45
aSortOrder 2
aFK_ID_DELIVERYROUTE 27619
aFK_ID_DELIVERYLOCATION 11633

eFuelStopDeliveryLocation 4,0
aID        11633
aName      Costco # 401**
aFKD_ID_ADDRESS 13815

eFuelStopDeliveryLocationAddress 5,0
aID        13815
aLine1     4605 Morena Blvd
aCity      San Diego

eFuelRequest 4,0
aID        47717
aRequestedGallonsToDeliver 8800.0
aFK_ID_LOADREQUEST 25018
aFK_ID_LOADINGACCOUNT 440
aFK_ID_TERMINAL 23
aFK_ID_DELIVERYLOCATION 11633
aFK_ID_FUELTYPE 134

eFuelStopLoadRequest 5,0
aID        25018
aFK_ID_DELIVERYROUTE 27619
aFK_ID_Z_ORDER 24518

eFuelStopOrder 6,0
aID        24518
aType      FTL
aInternalOrderNumber 54518
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 972

eFuelType  5,0
aID        134
aTypeName  87
aFK_ID_BASICFUELTYPE 5
aFK_ID_FUELTYPE 80
aFK_ID_CARRIER 22

eLoadingAccount 5,0
aID        440
aLoadingNumber 473-94111
aFKBILL_ID_ORGANIZATION 972
aFK_ID_TERMINAL 23
aFKSUP_ID_ORGANIZATION 992
aFKCON_ID_ORGANIZATION 972
aFK_ID_CARRIER 22

eLoadRequest 3,0
aID        25018
aStatus    Scheduled
aSpecialDriverInstructions  ****** TEST DATABASE DB2 ******
aFK_ID_DELIVERYROUTE 27619
aFK_ID_Z_ORDER 24518

eOrder     4,0
aID        24518
aInternalOrderNumber 54518
aType      FTL
aFK_ID_CARRIER 22
aFK_ID_ORGANIZATION 972

eRouteVehicleCombination 3,0
aID        5441
aCombinationDateTime 20180117155820
aFK_ID_VEHICLEPOWERUNIT 110

eRouteVehiclePowerUnit 4,0
aID        110
aTruckIdentifier Truck # 16
aFK_ID_ADDRESS 13702
aFK_ID_CARRIER 22

eRouteVehicleDeliveryTank 4,0
aID        156
aType      T
aTruckTrailerIdentifier TRAILER # 116
aFK_ID_CARRIER 22

eDeliveredByDriver 3,0
aID        128
aStatus    A
aDLNumber  U2013514
aFK_ID_PERSON 330
aFK_ID_CARRIER 22

eDeliveredByDriverPerson 4,0
aID        330
aLastName  Bennett**
aFirstName Gary

eDriver    2,0
aID        128
aStatus    A
aDLNumber  U2013514
aFK_ID_PERSON 330
aFK_ID_CARRIER 22

eDriverPerson 3,0
aID        330
aLastName  Bennett**
aFirstName Gary

eVehicleCombination 2,0
aID        5441
aCombinationDateTime 20180117155820
aCurrentForSchedulingFlag Y
aFK_ID_VEHICLEPOWERUNIT 110

eDeliveryTank 3,0
aID        156
aTruckTrailerIdentifier TRAILER # 116
aPlateNumber 4PX2598
aFK_ID_CARRIER 22

eVehiclePowerUnit 3,0
aID        110
aTruckIdentifier Truck # 16
aFK_ID_ADDRESS 13702
aFK_ID_CARRIER 22

i55        24
i56        25
i57        26
i58        27
i59        28
i60        29
i61        30
i63        24
i64        25
i65        26
i66        27
i67        28
i68        29
i69        30
i71        32
i72        33
i73        34
i74        35
i75        36
i76        37
i93        89
i94        90
i95        89
i96        91
i124       97
i125       98
i137       133
i138       134
i139       133
i140       135
i141       97
i142       98
i144       40
i145       41
i146       42
i147       43
i148       44
i149       45
i150       46
i160       48
i161       49
i162       50
i163       51
i164       52
i167       97
i168       98
ZEND
