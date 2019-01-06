package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class HollandTunnelSquare extends Square {

	public HollandTunnelSquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
//		int HT1 = 14;
//		int HT2 = 114;
//
//		if (PlayerController.getInstance().getCurrentPlayer().getPosition() == HT1)
//		{
//			PlayerController.getInstance().movePlayer(HT2);
//			gameEngine.publishEvent("teleport" + HT2);
//		}
//		else if (PlayerController.getInstance().getCurrentPlayer().getPosition() == HT2)
//		{
//			PlayerController.getInstance().movePlayer(HT1);
//			gameEngine.publishEvent("teleport" + HT1);
//		}
//		else
//		{
//			throw new UnsupportedOperationException("Not in Holland Tunnel! " + PlayerController.getInstance().getCurrentPlayer().getPosition());
//		}
		gameEngine.publishEvent("empty");
	}

}
