package blackjack;

import java.util.ArrayList;

public class Human {
	
	int point = 0;
	
	ArrayList<Card> CardList = new ArrayList<Card>();
	
	public void init() {
		point = 0;
		CardList.clear();
	}
}
