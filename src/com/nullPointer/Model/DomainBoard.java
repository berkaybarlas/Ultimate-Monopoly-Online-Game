package com.nullPointer.Model;
import java.util.*;

import com.nullPointer.Model.Cards.Card;
import com.nullPointer.Model.Square.ChanceCardSquare;
import com.nullPointer.Model.Square.CommunityChestCardSquare;
import com.nullPointer.Model.Square.GoSquare;
import com.nullPointer.Model.Square.HollandTunnelSquare;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.ReverseDirectionSquare;
import com.nullPointer.Model.Square.Square;
import com.nullPointer.Model.Square.SubwaySquare;
import com.nullPointer.Model.Square.TaxRefundSquare;
import com.nullPointer.Model.Square.UtilitySquare;

import sun.security.util.Length;

public class DomainBoard {

	private ArrayList<Square> squares;
	private Queue<Card> cards;

	public String[] squareNames_inner = {"SQUEEZE PLAY","THE EMBARCADERO","FISHERMAN'S WHARF","TELEPHONE COMPANY","COMMUNITY CHEST","BEACON STREET","BONUS",
			"BOYLSTON STREET","NEWBURY STREET","PENNSYLVANIA RAILROAD","FIFTH AVENUE","MADISON AVENUE","STOCK EXCHANGE","WALL STREET","TAX REFUND","GAS COMPANY",
			"CHANCE","FLORIDA AVENUE","HOLLAND TUNNEL","MIAMI AVENUE","BISCAYNE AVENUE","SHORT LINE","REVERSE DIRECTION","LOMBARD STREET"};
			
	public String[] squareNames_middle = {"GO","MEDITERRANEAN AVENUE","COMMUNITY CHEST","BALTIC AVENUE","INCOME TAX","READING RAILROAD","ORIENTAL AVENUE",
			"CHANCE","VERMONT AVENUE","CONNECTICUT AVENUE","IN JAIL","ST. CHARLES PLACE","ELECTRIC COMPANY","STATES AVENUE","VIRGINIA AVENUE",
			"PENNSYLVANIA RAILROAD","ST. JAMES PLACE","COMMUNITY CHEST","TENNESSE AVENUE","NEW YORK AVENUE","FREE PARKING","KENTUCKY AVENUE","CHANCE",
			"INDIANA AVENUE","ILLINOIS AVENUE","B&O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","ROLL ONCE","PACIFIC AVENUE",
			"NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE","PARK PLACE","LUXURY TAX","BOARDWALK"};

	public String[] squareNames_outer = {"SUBWAY","LAKE STREET","COMMUNITY CHEST", "NICOLLET AVENUE","HENNEPIN AVENUE", "BUS TICKET","CHECKER CAB CO.", "READING RAILROAD",
			"ESPLANADE AVENUE","CANAL STREET","CHANCE","CABLE COMPANY","MAGAZINE STREET","BOURBON STREET","HOLLAND TUNNEL","AUCTION","KATY FREEWAY","WESTHEIMER ROAD",
			"INTERNET SERVICE PROVIDER","KIRBY DRIVE","CULLEN BOULEVARD","CHANCE","BLACK & WHITE CAB CO.","DEKALB AVENUE","COMMUNITY CHEST","ANDREW YOUNG INTL BOULEVARD",
			"DECATUR STREET","PEACHTREE STREET","PAY DAY","RANDOLPH STREET","CHANCE","LAKE SHORE DRIVE","WACKER DRIVE","MICHIGAN AVENUE","YELLOW CAB CO.","B&O RAILROAD",
			"COMMUNITY CHEST","SOUTH TEMPLE","WEST TAMPLE","TRASH COLLECTOR","NORTH TEMPLE","TEMPLE SQUARE","GO TO JAIL","SOUTH STREET","BROAD STREET","WALNUT STREET",
			"COMMUNITY CHEST","MARKET STREET","BUS TICKET","SEWAGE SYSTEM","UTE CAB CO.","BIRTHDAY GIFT","MULHOLLAND DRIVE","VENTURA BOULEVARD","CHANCE","RODEO DRIVE"};

	public int[] price_inner_properties = {};
	public int[] price_middle_properties = {};	
	public int[] price_outer_properties = {};
			
	
	public DomainBoard(){
		squares=new ArrayList<Square>();
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(1);
		createSquares();
		cards=new LinkedList<Card>();
	}
	
	public void addSquare(Square sq)
	{
		squares.add(sq);
	}
	
	public void createSquares() {
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(1);
		
		for (int i = 0; i < squareNames_inner.length ; i++) {
			if(i== 0){
		//		squares.add(i, new SquareSqueezePlay(squareNames_middle[i], "SqueezePlay", i, 2));
			}else if(i == 9 || i == 21 ){
				squares.add(new SubwaySquare(squareNames_middle[i], "SubwaySquare", i, 2));
			}else if(i == 4){
				squares.add(new CommunityChestCardSquare(squareNames_middle[i], "CommunityChestCard", i, 2));
			}else if(i == 16 ){
				squares.add(new ChanceCardSquare(squareNames_middle[i], "ChanceCard", i, 2));
			}else if(i == 3 || i == 15  ){
				squares.add(new UtilitySquare(squareNames_middle[i], "Utility", i, 2));			
			}else if(i == 6){
				squares.add(new GoSquare(squareNames_middle[i], "Go", i, 2));
			//}else if(i == 12){
			//	squares.add(i, new SquareStock(squareNames_middle[i], "Stock", i, 2));
				//stock exchange
			}else if(i == 14 ){
				squares.add(new TaxRefundSquare(squareNames_middle[i], "TaxRefund", i, 2));
			}else if(i == 18 ){
				squares.add(new HollandTunnelSquare(squareNames_middle[i], "Tunnel", i, 2));
			}else if(i == 22){
				squares.add(new ReverseDirectionSquare(squareNames_middle[i], "Reverse", i, 2));
			}else{ //  price, color & rent will be added. Now price = 100, color = "BLUE"
				squares.add(new PropertySquare(squareNames_middle[i], "Property", i, 2, 100 , "BLUE", list));
			}
		}

	}
	

	public ArrayList<Square> getSquares() {
		return squares;
	}
	public Queue<Card> getCards() {
		return cards;
	}
}