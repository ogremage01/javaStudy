package blackjack;

import java.util.ArrayList;

public class Player {
	
	int playerPoint = 0;
	boolean playerWin = false;
	
	int playerMoney = 10000;

	public void playerInit() {
		playerPoint = 0;
		playerCardList.clear();
	}
	
	
	ArrayList<Card> playerCardList = new ArrayList<Card>();
}
