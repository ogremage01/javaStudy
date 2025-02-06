package blackjack;

import java.util.HashMap;

public class BlackjackAction {
	
	Dealer dealer = new Dealer();
	Player player = new Player();//추후 개량시는  startBlackjack()쪽에 인수 추가하는 로직을 넣어야 함
	
	CardCase cc = new CardCase();
	
	HashMap<String, Integer> pointMap = new HashMap<String, Integer>();
	
		
	public void startBlackjack(){
		pointInit();		
		System.out.println("블랙잭을 시작합니다.");
		System.out.println("실력때문에 최대 4인이 플레이하는 게임을 1인 게임으로 만들 수 밖에 없어 슬픕니다.");
		System.out.println("실제 카지노에서는 카드를 여러벌 쓰는 것도 구현하고 싶습니다.");
		System.out.println("그 외 스플릿 같은 룰도 있는데 그것도 구현하고 싶습니다.");
		System.out.println("베팅도 구현 못했네요.");
		System.out.println("A가 21을 넘기면 11에서 1이 되는 것도 구현하고 싶습니다.");
		System.out.println("카드를 개봉합니다.");
//		딜러가 카드 케이스 개봉
		cc.init();
//		셔플
		System.out.println("딜러가 카드를 섞습니다.");
		shuffle();
		System.out.println("게임이 준비되었습니다. 블랙잭을 시작합니다.");
		}
	
	public void firstOpen() {
		System.out.println("딜러가 첫 카드를 드로우합니다.");
		dealerDraw();
		System.out.println(dealer.dealerCardList.get(0).getCard() + "체크용. 추후 삭제 예정");
		dealer.dealerPoint += pointMap.get(dealer.dealerCardList.get(0).getCard().substring(1));
		System.out.println("딜러가 두 번째 카드를 드로우합니다.");
		dealerDraw();
		dealer.dealerPoint += pointMap.get(dealer.dealerCardList.get(0).getCard().substring(1));
		if(dealer.dealerPoint == 21) {
			
			System.out.println("블랙잭. 딜러의 승리입니다.");
			System.out.println(dealer.dealerCardList.get(0).getCard() + dealer.dealerCardList.get(1).getCard());
		}
		
		System.out.println("딜러의 카드는 뒷면 한장과 " + dealer.dealerCardList.get(1).getCard());
//		
	}

	public void shuffle() {
		
		for (int i = 0; i < cc.getCardList().size(); i++) {
			Card temp = new Card();
			int randNum = 0;
			randNum = (int) (Math.random() * (cc.getCardList().size()));
			temp=cc.getCardList().get(randNum);
			cc.getCardList().set(randNum, cc.getCardList().get(i));
			cc.getCardList().set(i, temp); 
			
		}
	}

	public void dealerDraw() {
		dealer.dealerCardList.add(cc.getCardList().get(0));
		cc.getCardList().remove(0);		
		
	}
	
	public void playerDraw() {
		player.playerCardList.add(cc.getCardList().get(0));
		cc.getCardList().remove(0);
	}
	
	
	public void pointInit() {
		
		
		pointMap.put("A", 11);
		pointMap.put("2", 2);
		pointMap.put("3", 3);
		pointMap.put("4", 4);
		pointMap.put("5", 5);
		pointMap.put("6", 6);
		pointMap.put("7", 7);
		pointMap.put("8", 8);
		pointMap.put("9", 9);
		pointMap.put("T", 10);
		pointMap.put("J", 10);
		pointMap.put("Q", 10);
		pointMap.put("K", 10);
	}
	
	
	
	
	
	
	
}



