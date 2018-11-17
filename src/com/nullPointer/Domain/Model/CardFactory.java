package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Model.Cards.*;

public class CardFactory {
	
	private static CardFactory _instance;
	
	public static CardFactory getInstance() {
		if(_instance == null) {
			_instance = new CardFactory();
		}
		return _instance;
	}

	/*
	 * Community Chest card names
	 */
	public String CCCard_names[] = {"Be Kind, Rewind", "Deal Buster", "Elected District Attorney", "Game Night", "Happy Birthday", "House Condemned",
			"The Insider's Edge", "A Moving Experience", "Pay Hospital Bills", "Share In Their Good Fortune", "Tornado Hits"};

	/*
	 * Chance card names
	 */
	public String ChanceCard_names[] = {"Advance To The Nearest Railroad", "Advance To The Pay Corner", "Advance To St. Charles Place", "Buyer's Market", "Changing Lanes",
			/*This is not a typo, 2 cards with same name exist*/ "Changing Lanes", "Foreclosed Property Sale", "Forward Thinker", "Get Out Of Jail Free", "Get Rollin'",
			"Go To Jail", "GPS Not Working", "Holiday Bonus", "Hurricane Makes Landfall", "Just Say \"NO\"", "Make General Repairs", "Mardi Gras", "Pay Back",
			"Property Taxes", "Ride The Subway", "See You In Court", "Social Media Fail", "Zero Dollars Down"};

	/*
	 * isImmediate values for community chest cards
	 */
	public boolean bekindRewindImmediate					=			true;

	public boolean dealBusterImmediate					=			false;

	public boolean electedDistrictAttorneyImmediate		=			false;

	public boolean gameNightImmediate					=			true;

	public boolean happyBirthdayImmediate				=			true;

	public boolean houseCondemnedImmediate				=			true;

	public boolean insidersEdgeImmediate					=			true;

	public boolean movingExperienceImmediate				=			true;

	public boolean payHospitalBillsImmediate				=			true;

	public boolean shareInTheirGoodFortuneImmediate		=			false;

	public boolean tornadoImmediate						=			true;

	/*
	 * isImmediate values for chance cards
	 */
	public boolean advanceToNearestRailRoadImmediate		=			true;

	public boolean advanceToPayCornerImmediate			=			true;

	public boolean advanceToStCharlesImmediate			=			true;

	public boolean buyersMarketImmediate					=			true;

	public boolean changingLanesAboveImmediate			=			true;

	public boolean changingLianesBelowImmediate			=			true;

	public boolean foreclosedPropSaleImmediate			=			true;

	public boolean forwardThinkerImmediate				=			true;

	public boolean getOutOfJailFreeImmediate				=			false;

	public boolean getRollinImmediate					=			true;

	public boolean goToJailImmediate						=			true;

	public boolean GPSNotWorkingImmediate				=			true;

	public boolean holidayBonusImmediate					=			true;

	public boolean hurricaneImmediate					=			true;

	public boolean justSayNOImmediate					=			false;

	public boolean generakRepairsImmediate				=			true;

	public boolean mardiGrasImmediate					=			true;

	public boolean payBackImmediate						=			true;

	public boolean propTaxesImmediate					=			true;

	public boolean rideTheSubwayImmediate				=			true;

	public boolean CUInCourtImmediate					=			true;

	public boolean socialMediaFailImmediate				=			true;

	public boolean zeroDollarsDownImmediate				=			false;




	public ChanceCard createChanceCard(int index)
	{
		switch(index)
		{
		case 0:
			return new ChanceAdvanceToNearestRailroad(ChanceCard_names[index], advanceToNearestRailRoadImmediate);
		case 1:
			return new ChanceAdvanceToPayCorner(ChanceCard_names[index], advanceToPayCornerImmediate);
		case 2:
			return new ChanceAdvanceToSaintCharles(ChanceCard_names[index], advanceToStCharlesImmediate);
		case 3:
			return new ChanceBuyersMarket(ChanceCard_names[index], buyersMarketImmediate);
		case 4:
			return new ChanceChangingLanesAbove(ChanceCard_names[index], changingLanesAboveImmediate);
		case 5:
			return new ChanceChangingLanesBelow(ChanceCard_names[index], changingLianesBelowImmediate);
		case 6:
			return new ChanceForeclosedPropertySale(ChanceCard_names[index], foreclosedPropSaleImmediate);
		case 7:
			return new ChanceForwardThinker(ChanceCard_names[index], forwardThinkerImmediate);
		case 8:
			return new ChanceGetOutOfJailFree(ChanceCard_names[index], getOutOfJailFreeImmediate);
		case 9:
			return new ChanceGetRollin(ChanceCard_names[index], getRollinImmediate);
		case 10:
			return new ChanceGoToJail(ChanceCard_names[index], goToJailImmediate);
		case 11:
			return new ChanceGPSNotWorking(ChanceCard_names[index], GPSNotWorkingImmediate);
		case 12:
			return new ChanceHolidayBonus(ChanceCard_names[index], holidayBonusImmediate);
		case 13:
			return new ChanceHurricane(ChanceCard_names[index], hurricaneImmediate);
		case 14:
			return new ChanceJustSayNo(ChanceCard_names[index], justSayNOImmediate);
		case 15:
			return new ChanceMakeGeneralRepairs(ChanceCard_names[index], generakRepairsImmediate);
		case 16:
			return new ChanceMardiGras(ChanceCard_names[index], mardiGrasImmediate);
		case 17:
			return new ChancePayBack(ChanceCard_names[index], payBackImmediate);
		case 18:
			return new ChancePropertyTaxes(ChanceCard_names[index], propTaxesImmediate);
		case 19:
			return new ChanceRideTheSubway(ChanceCard_names[index], rideTheSubwayImmediate);
		case 20:
			return new ChanceSeeUInCourt(ChanceCard_names[index], CUInCourtImmediate);
		case 21:
			return new ChanceSocialMediaFail(ChanceCard_names[index], socialMediaFailImmediate);
		case 22:
			return new ChanceZeroDollarsDown(ChanceCard_names[index], zeroDollarsDownImmediate);
		default:
			return null;
		}
	}



	public CommunityChestCard createCCCard(int index)
	{
		switch(index)
		{
		case 0:
			return new CCBeKindRewind(CCCard_names[index], bekindRewindImmediate);
		case 1:
			return new CCDealBuster(CCCard_names[index], dealBusterImmediate);
		case 2:
			return new CCElectedDistrictAttorney(CCCard_names[index], electedDistrictAttorneyImmediate);
		case 3:
			return new CCGameNight(CCCard_names[index], gameNightImmediate);
		case 4:
			return new CCHappyBirthday(CCCard_names[index], happyBirthdayImmediate);
		case 5:
			return new CCHouseCondemned(CCCard_names[index], houseCondemnedImmediate);
		case 6:
			return new CCInsidersEdge(CCCard_names[index], insidersEdgeImmediate);
		case 7:
			return new CCMovingExperience(CCCard_names[index], movingExperienceImmediate);
		case 8:
			return new CCPayHospitalBills(CCCard_names[index], payHospitalBillsImmediate);
		case 9:
			return new CCShareInTheirGoodFortune(CCCard_names[index], shareInTheirGoodFortuneImmediate);
		case 10:
			return new CCTornadoHits(CCCard_names[index], tornadoImmediate);
		default:
			return null;
		}
	}
}
