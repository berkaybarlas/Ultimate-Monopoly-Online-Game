package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Model.Square.*;

public class SquareFactory {
    private static SquareFactory _instance;

    private SquareFactory() {

    }

    public static SquareFactory getInstance() {
        if(_instance == null) {
            _instance = new SquareFactory();
        }
        return _instance;
    }
    /*
     * RentList's for each property square
     */
    //Should these be public or private??
    int[] LombardList = {17, 85, 240, 475, 670, 1025, 1525, 105, 100};
    int[] EmbarcaderoList = LombardList;
    int[] FishermansWharfList = {21, 105, 320, 780, 950, 1125, 1625, 125, 100};

    int[] BeaconList = {30, 160, 470, 1050, 1250, 1500, 2500, 165, 200};
    int[] BoylstonList = BeaconList;
    int[] NewburyList = {40, 185, 550, 1200, 1500, 1700, 2700, 190, 200};

    int[] FifthList = {60, 220, 650, 1500, 1800, 2100, 3600, 215, 300};
    int[] MadisonList = FifthList;
    int[] WallList = {80, 300, 800, 1800, 2200, 2700, 4200, 250, 300};

    int[] FloridaList = {9, 45, 120, 350, 500, 700, 1200, 65, 50};
    int[] MiamiList = FloridaList;
    int[] BiscayneList = {11, 55, 160, 475, 650, 800, 1300, 75, 50};

    int[] MediterranianAveList = {2, 10, 30, 90, 160, 250, 750, 30, 50};
    int[] BalticAveList = {4, 20, 60, 180, 320, 450, 900, 30, 50};


    int[] OrientalAveList = {6, 30, 90, 270, 400, 550, 1050, 50, 50};
    int[] VermontAveList = OrientalAveList;
    int[] ConnecticutAveList = {8, 40, 100, 300, 450, 600, 1100, 60, 50};


    int[] StCharlesList = {10, 50, 150, 450, 625, 750, 1250, 70, 100};
    int[] StatesAveList = StCharlesList;
    int[] VirginiaAveList = {12, 60, 180, 500, 700, 900, 1400, 80, 100};
    
    int[] StJamesList = {14, 70, 200, 550, 750, 950, 1450, 90, 100};
    int[] TennesseeList = StJamesList;
    int[] NewYorkList = {16, 80, 220, 600, 800, 1000, 1500, 100, 100};
    
    int[] KentuckyList = {18, 90, 250, 700, 875, 1050, 2050, 100, 150};
    int[] IndianaList = KentuckyList;
    int[] IllinoisList = {20, 100, 300, 750, 925, 1100, 2100, 120, 150};
    
    int[] AtlanticList = {22, 110, 330, 800, 975, 1150, 2150, 130, 150};
    int[] VentnorList = AtlanticList;
    int[] MarvinList = {24, 120, 350, 850, 1025, 1200, 2200, 140, 150};
    
    int[] PacificList = {26, 130, 390, 900, 1100, 1275, 2275, 150, 200};
    int[] NorthCarolinaList = PacificList;
    int[] PennsylvaniaList = {150, 450, 1000, 2000, 1400, 2400, 160, 200};
    
    int[] ParkList = {35, 175, 500, 1100, 1300, 1500, 2500, 200, 200};
    int[] BoardwalkList = {50, 200, 600, 1400, 1700, 2000, 3000, 200, 200};  
    /*
     * Name lists for each square
     */
    public String[] squareNames_inner = {"SQUEEZE PLAY", "THE EMBARCADERO", "FISHERMAN'S WHARF", "TELEPHONE COMPANY", "COMMUNITY CHEST", "BEACON STREET", "BONUS",
            "BOYLSTON STREET", "NEWBURY STREET", "PENNSYLVANIA RAILROAD", "FIFTH AVENUE", "MADISON AVENUE", "STOCK EXCHANGE", "WALL STREET", "TAX REFUND", "GAS COMPANY",
            "CHANCE", "FLORIDA AVENUE", "HOLLAND TUNNEL", "MIAMI AVENUE", "BISCAYNE AVENUE", "SHORT LINE", "REVERSE DIRECTION", "LOMBARD STREET"};

    public String[] squareNames_middle = {"GO", "MEDITERRANEAN AVENUE", "COMMUNITY CHEST", "BALTIC AVENUE", "INCOME TAX", "READING RAILROAD", "ORIENTAL AVENUE",
            "CHANCE", "VERMONT AVENUE", "CONNECTICUT AVENUE", "IN JAIL", "ST. CHARLES PLACE", "ELECTRIC COMPANY", "STATES AVENUE", "VIRGINIA AVENUE",
            "PENNSYLVANIA RAILROAD", "ST. JAMES PLACE", "COMMUNITY CHEST", "TENNESSE AVENUE", "NEW YORK AVENUE", "FREE PARKING", "KENTUCKY AVENUE", "CHANCE",
            "INDIANA AVENUE", "ILLINOIS AVENUE", "B&O RAILROAD", "ATLANTIC AVENUE", "VENTNOR AVENUE", "WATER WORKS", "MARVIN GARDENS", "ROLL ONCE", "PACIFIC AVENUE",
            "NORTH CAROLINA AVENUE", "COMMUNITY CHEST", "PENNSYLVANIA AVENUE", "SHORT LINE", "CHANCE", "PARK PLACE", "LUXURY TAX", "BOARDWALK"};

    public String[] squareNames_outer = {"SUBWAY", "LAKE STREET", "COMMUNITY CHEST", "NICOLLET AVENUE", "HENNEPIN AVENUE", "BUS TICKET", "CHECKER CAB CO.", "READING RAILROAD",
            "ESPLANADE AVENUE", "CANAL STREET", "CHANCE", "CABLE COMPANY", "MAGAZINE STREET", "BOURBON STREET", "HOLLAND TUNNEL", "AUCTION", "KATY FREEWAY", "WESTHEIMER ROAD",
            "INTERNET SERVICE PROVIDER", "KIRBY DRIVE", "CULLEN BOULEVARD", "CHANCE", "BLACK & WHITE CAB CO.", "DEKALB AVENUE", "COMMUNITY CHEST", "ANDREW YOUNG INTL BOULEVARD",
            "DECATUR STREET", "PEACHTREE STREET", "PAY DAY", "RANDOLPH STREET", "CHANCE", "LAKE SHORE DRIVE", "WACKER DRIVE", "MICHIGAN AVENUE", "YELLOW CAB CO.", "B&O RAILROAD",
            "COMMUNITY CHEST", "SOUTH TEMPLE", "WEST TAMPLE", "TRASH COLLECTOR", "NORTH TEMPLE", "TEMPLE SQUARE", "GO TO JAIL", "SOUTH STREET", "BROAD STREET", "WALNUT STREET",
            "COMMUNITY CHEST", "MARKET STREET", "BUS TICKET", "SEWAGE SYSTEM", "UTE CAB CO.", "BIRTHDAY GIFT", "MULHOLLAND DRIVE", "VENTURA BOULEVARD", "CHANCE", "RODEO DRIVE"};

    /*
     * Prices for each property square
     */
    int LombardPrice = 210;
    int EmbarcaderoPrice = 210;
    int FishermansWharfPrice= 250;

    int TelephonePrice = 150;

    int BeaconPrice = 330;
    int BoylstonPrice = 330;
    int NewburyPrice = 380;

    int FifthPrice = 430;
    int MadisonPrice = 430;
    int WallPrice = 500;

    int GasPrice = 150;

    int FloridaPrice = 130;
    int MiamiPrice = 130;
    int BiscaynePrice = 150;

    int MediterranianPrice = 60;
    int BalticPrice = 60;

    int OrientalPrice = 100;
    int VermontPrice = 100;
    int ConnecticutPrice = 120;

    int StCharlesPrice = 140;
    int StatesPrice = 140;
    int VirginiaPrice = 160;

    int ElectricPrice = 150;

    int StJamesPrice = 180;
    int TennesseePrice = 180;
    int NewYorkPrice = 200;
    
    int KentuckyPrice = 220;
    int IndianaPrice = 220;
    int IllinoisPrice = 240;
    
    int AtlanticPrice = 260;
    int VentnorPrice = 260;
    int MarvinPrice = 280;

    int WaterPrice = 150;

    int PacificPrice = 300;
    int NorthCarolinaPrice = 300;
    int PennsylvaniaPrice = 320;
    
    int ParkPrice = 350;
    int BoardwalkPrice = 400;

    /*
     * Color values for each property square
     */
    String LombardColor, EmbarcaderoColor, FishermansWharfColor = "White";

    String BeaconColor, BoylstonColor, NewburyColor = "Black";

    String FifthColor, MadisonColor, WallColor = "Dark Gray";

    String FloridaColor, MiamiColor, BiscayneColor = "Brown";

    String MediterranianColor, BalticColor = "Dark Purple";

    String OrientalColor, VermontColor, ConnecticutColor = "Light Blue";

    String StCharlesColor, StatesColor, VirginiaColor = "Purple";
    
    String StJamesColor, TennesseeColor, NewYorkColor = "Orange";
    
    String KentuckyColor, IndianaColor, IllinoisColor = "Red";
    
    String AtlanticColor, VentnorColor, MarvinColor = "Yellow";
    
    String PacificColor, NorthCarolinaColor, PennsylvaniaColor = "Green";
    
    String ParkColor, BoardwalkColor = "Dark Blue";

    public Square createInnerSquares(int index) {
        switch(index) {
            case 0:
                return new EmptySquare(squareNames_inner[index],"SqueezePlaySquare");
            case 1:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",EmbarcaderoPrice,EmbarcaderoColor,EmbarcaderoList);
            case 2:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",FishermansWharfPrice,FishermansWharfColor,FishermansWharfList);
            case 3:
                return new UtilitySquare(squareNames_inner[index],"UtilitySquare",TelephonePrice);
            case 4:
                return new CommunityChestCardSquare(squareNames_inner[index],"CommunityChestSquare");
            case 5:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",BeaconPrice,BeaconColor,BeaconList);
            case 6:
                return new BonusSquare(squareNames_inner[index],"BonusSquare");
            case 7:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",BoylstonPrice,BoylstonColor,BoylstonList);
            case 8:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",NewburyPrice,NewburyColor,NewburyList);
            case 9:
                return new RailRoadTransitStationsSquare(squareNames_inner[index],"RailRoadTransitStationsSquare");
            case 10:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",FifthPrice,FifthColor,FifthList);
            case 11:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",MadisonPrice,MadisonColor,MadisonList);
            case 12:
                return new EmptySquare(squareNames_inner[index],"StockExchangeSquare");
            case 13:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",WallPrice,WallColor,WallList);
            case 14:
                return new TaxRefundSquare(squareNames_inner[index],"TaxRefundSquare");
            case 15:
                return new UtilitySquare(squareNames_inner[index],"UtilitySquare",GasPrice);
            case 16:
                return new ChanceCardSquare(squareNames_inner[index],"ChanceCardSquare");
            case 17:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",FloridaPrice,FloridaColor,FloridaList);
            case 18:
                return new HollandTunnelSquare(squareNames_inner[index],"HollandTunnelSquare");
            case 19:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",MiamiPrice,MiamiColor,MiamiList);
            case 20:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",BiscaynePrice,BiscayneColor,BiscayneList);
            case 21:
                return new RailRoadTransitStationsSquare(squareNames_inner[index],"RailRoadTransitStationsSquare");
            case 22:
                return new ReverseDirectionSquare(squareNames_inner[index],"ReverseDirectionSquare");
            case 23:
                return new PropertySquare(squareNames_inner[index],"PropertySquare",LombardPrice,LombardColor,LombardList);
            default:
                return null;
        }
    }

    public Square createMiddleSquares(int index) {
        switch (index) {
            case 0:
                return new GoSquare(squareNames_middle[index], "GoSquare");
            case 1:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", MediterranianPrice, MediterranianColor, MediterranianAveList);
            case 2:
                return new CommunityChestCardSquare(squareNames_middle[index], "CommunityChestCardSquare");
            case 3:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", BalticPrice, BalticColor, BalticAveList);
            case 4:
                return new IncomeTaxSquare(squareNames_middle[index], "IncomeTaxSquare");
            case 5:
                return new RailRoadTransitStationsSquare(squareNames_middle[index], "RailroadTransitSquare");
            case 6:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", OrientalPrice, OrientalColor, OrientalAveList);
            case 7:
                return new ChanceCardSquare(squareNames_middle[index], "ChanceCardSquare");
            case 8:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", VermontPrice, VermontColor, VermontAveList);
            case 9:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", ConnecticutPrice, ConnecticutColor, ConnecticutAveList);
            case 10:
                return new JailSquare(squareNames_middle[index], "JailSquare");
            case 11:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", StCharlesPrice, StCharlesColor, StCharlesList);
            case 12:
                return new UtilitySquare(squareNames_middle[index], "UtilitySquare",ElectricPrice);
            case 13:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", StatesPrice, StatesColor, StatesAveList);
            case 14:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", VirginiaPrice, VirginiaColor, VirginiaAveList);
            case 15:
            		return new RailRoadTransitStationsSquare(squareNames_middle[index], "RailroadTransitSquare");
            case 16:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", StJamesPrice, StJamesColor, StJamesList);
            case 17:
            		return new CommunityChestCardSquare(squareNames_middle[index], "CommunityChestCardSquare");
            case 18:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", TennesseePrice, TennesseeColor, TennesseeList);
            case 19: 
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", NewYorkPrice, NewYorkColor, NewYorkList);
            case 20:
            		return new EmptySquare(squareNames_middle[index], "FreeParkingSquare"); // Should I call EmptySquare?
            case 21: 
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", KentuckyPrice, KentuckyColor, KentuckyList);
            case 22:
            		return new ChanceCardSquare(squareNames_middle[index], "ChanceCardSquare");
            case 23:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", IndianaPrice, IndianaColor, IndianaList);
            case 24: 
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", IllinoisPrice, IllinoisColor, IllinoisList);
            case 25:
            		return new RailRoadTransitStationsSquare(squareNames_middle[index], "RailroadTransitSquare");
            case 26: 
        			return new PropertySquare(squareNames_middle[index], "PropertySquare", AtlanticPrice, AtlanticColor, AtlanticList);
            case 27:
        			return new PropertySquare(squareNames_middle[index], "PropertySquare", VentnorPrice, VentnorColor, VentnorList);
            case 28:
            	 	return new UtilitySquare(squareNames_middle[index], "UtilitySquare",WaterPrice);
            case 29:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", MarvinPrice, MarvinColor, MarvinList);
            case 30:
            		return new Roll3CardSquare(squareNames_middle[index], "Roll3CardSquare");
            case 31:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", PacificPrice, PacificColor, PacificList);
            case 32:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", NorthCarolinaPrice, NorthCarolinaColor, NorthCarolinaList);
            case 33:
            		return new CommunityChestCardSquare(squareNames_middle[index], "CommunityChestCardSquare");
            case 34:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", PennsylvaniaPrice, PennsylvaniaColor, PennsylvaniaList);
            case 35:
            		return new RailRoadTransitStationsSquare(squareNames_middle[index], "RailroadTransitSquare");
            case 36:
            		return new ChanceCardSquare(squareNames_middle[index], "ChanceCardSquare");
            case 37:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", ParkPrice, ParkColor, ParkList);
            case 38:
            		return new LuxuryTaxSquare(squareNames_middle[index], "LuxuryTaxSquare");
            case 39:
            		return new PropertySquare(squareNames_middle[index], "PropertySquare", BoardwalkPrice, BoardwalkColor, BoardwalkList);
            default:
                return null;
        }
    }
}
