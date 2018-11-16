package com.nullPointer.Model;

import java.util.ArrayList;
import java.util.Arrays;

import com.nullPointer.Model.Square.*;

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
    int[] MediterranianAveList = {2, 10, 30, 90, 160, 250, 750, 30, 50};
    int[] BalticAveList = {4, 20, 60, 180, 320, 450, 900, 30, 50};


    int[] OrientalAveList = {6, 30, 90, 270, 400, 550, 1050, 50, 50};
    int[] VermontAveList = OrientalAveList;
    int[] ConnecticutAveList = {8, 40, 100, 300, 450, 600, 1100, 60, 50};


    int[] StCharlesList = {10, 50, 150, 450, 625, 750, 1250, 70, 100};
    int[] StatesAveList = StCharlesList;
    int[] VirginiaAveList = {12, 60, 180, 500, 700, 900, 1400, 80, 100};

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
    int MediterranianPrice = 60;
    int BalticPrice = 60;

    int OrientalPrice = 100;
    int VermontPrice = 100;
    int ConnecticutPrice = 120;

    int StCharlesPrice = 140;
    int StatesPrice = 140;
    int VirginiaPrice = 160;

    /*
     * Color values for each property square
     */
    String MediterranianColor, BalticColor = "Dark Purple";

    String OrientalColor, VermontColor, ConnecticutColor = "Light Blue";

    String StCharlesColor, StatesColor, VirginiaColor = "Purple";

    public Square createMiddleSquare(int index) {
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
                return new PropertySquare(squareNames_middle[index], "PropertySquare", ConnecticutPrice, ConnecticutColor, MediterranianAveList);
            case 10:
                return new EmptySquare(squareNames_middle[index], "JailSquare");
            case 11:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", StCharlesPrice, StCharlesColor, StCharlesList);
            case 12:
                return new UtilitySquare(squareNames_middle[index], "UtilitySquare");
            case 13:
                return new PropertySquare(squareNames_middle[index], "PropertySquare", StatesPrice, StatesColor, StatesAveList);
            case 14:
                return new PropertySquare(squareNames_inner[index], "PropertySquare", VirginiaPrice, VirginiaColor, VirginiaAveList);
            default:
                return null;
        }
    }
}
