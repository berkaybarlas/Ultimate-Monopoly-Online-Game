package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Model.Cards.CCElectedDistrictAttorney;
import com.nullPointer.Domain.Model.Cards.CCPayHospitalBills;
import com.nullPointer.Domain.Model.Cards.ChanceCard;
import com.nullPointer.Domain.Model.Cards.ChanceGoToJail;
import com.nullPointer.Domain.Model.Cards.ChanceHolidayBonus;
import com.nullPointer.Domain.Model.Cards.CommunityChestCard;

public class CardFactory {
	private static CardFactory instance;
	
	public ChanceCard createChanceCard(String title)
	{
		if (title.equals("Holiday Bonus")) return new ChanceHolidayBonus(title, false);
		else if(title.equals("Go To Jail")) return new ChanceGoToJail(title, false);
		else return null;
	}
	
	public CommunityChestCard createCCCard(String title)
	{
		if (title.equals("Pay Hospital Bills")) return new CCPayHospitalBills(title, false);
		else if (title.equals("Elected District Attorney")) return new CCElectedDistrictAttorney(title, false);
		else return null;
	}
}
