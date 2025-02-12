package blackjack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class BlackjackAction {

	Dealer dealer = new Dealer();
	Player player = new Player();// 추후 개량시는 startBlackjack()쪽에 인수 추가하는 로직을 넣어야 함
	boolean gameOver = false;

	String input = "";
	
	int moneyIn = 0;

	Scanner sc = new Scanner(System.in);

	CardCase cc = new CardCase();

	HashMap<String, Integer> pointMap = new HashMap<String, Integer>();

	public void startBlackjack() {
		pointInit();
		player.playerInit();
		dealer.dealerInit();
		System.out.println("블랙잭을 시작합니다.");
		System.out.println("실력때문에 최대 4인이 플레이하는 게임을 1인 게임으로 만들 수 밖에 없어 슬픕니다.");
		System.out.println("실제 카지노에서는 카드를 여러벌 쓰는 것도 구현하고 싶습니다.");
		System.out.println("그 외 스플릿 같은 룰도 있는데 그것도 구현하고 싶습니다.");
		System.out.println("베팅도 구현 못했네요.");
		System.out.println("A가 21을 넘기면 11에서 1이 되는 것도 구현하고 싶습니다.");
		System.out.println("안된게 뭐이리 많아");
		System.out.println("---------------------------------------------");
		System.out.println("카드를 개봉합니다.");
//		딜러가 카드 케이스 개봉
		cc.init();
//		셔플
		System.out.println("딜러가 카드를 섞습니다.");
		shuffle();
		System.out.println("게임이 준비되었습니다. 블랙잭을 시작합니다.");
		System.out.println("베팅을 결정해 주세요.");
		moneyIn = sc.nextInt();
		sc.nextLine();
		
		player.playerMoney -= moneyIn;
		
	}

	public void DealerFirstOpen() {
		System.out.println("딜러가 첫 카드를 드로우합니다.");
		dealerDraw();
		System.out.println("딜러가 두 번째 카드를 드로우합니다.");
		dealerDraw();
		if (dealer.dealerPoint == 21) {

			System.out.println("블랙잭. 딜러의 승리입니다.");
			System.out.println(dealer.dealerCardList.get(0).getCard() + dealer.dealerCardList.get(1).getCard());
			dealer.dealerWin = true;
			gameOver = true;
			return;
		}

		System.out.println("딜러의 카드는 뒷면 한장과 " + dealer.dealerCardList.get(0).getCard());
//		
	}

	public void playerFirstOpen() {
		System.out.println("플레이어가 첫 카드를 드로우합니다.");
		playerDraw();

		System.out.println("플레이어가 두 번째 카드를 드로우합니다.");
		playerDraw();
		if (player.playerPoint == 21) {

			System.out.println("블랙잭. 플레이어의 승리입니다.");
			player.playerWin = true;
			gameOver = true;
			player.playerMoney += moneyIn;
			return;
		}
		System.out.println("플레이어의 점수는" + player.playerPoint);

	}

	public void playerHit() {

		while (player.playerPoint < 22) {
			System.out.println("카드를 받으시겠습니까? y/n");
			input = sc.next();
			
			if (input.toLowerCase().equals("n")) {
				return;
			} else if (input.toLowerCase().equals("y")) {
				playerDraw();
				System.out.println("플레이어의 점수는" + player.playerPoint);
			}sc.nextLine();
		}
		System.out.println("플레이어 버스트. 점수는" + player.playerPoint);
		gameOver = true;
		return;

	}

	public void dealerHit() {
		System.out.println("딜러의 카드.");
		for (int i = 0; i < dealer.dealerCardList.size(); i++) {
			System.out.print(dealer.dealerCardList.get(i).getCard() + " ");
		}
		while (dealer.dealerPoint <= 17) {
			System.out.println();
			dealerDraw();
			for (int i = 0; i < dealer.dealerCardList.size(); i++) {
				System.out.print(dealer.dealerCardList.get(i).getCard() + " ");
			}
		}if(dealer.dealerPoint > 21) {
			System.out.println("딜러 버스트. 플레이어 승");
			player.playerMoney += moneyIn*2;
			gameOver = true;
		}
	}

	public void result() {
		if (dealer.dealerPoint == player.playerPoint) {
			System.out.println("무승부");
			player.playerMoney += moneyIn;

		} else if (dealer.dealerPoint > player.playerPoint) {
			System.out.println("딜러 승리");
			

		} else if (dealer.dealerPoint < player.playerPoint) {
			System.out.println("플레이어 승리");
			player.playerMoney += moneyIn*2;
		}gameOver = true;
	}

	public void shuffle() {

		for (int i = 0; i < cc.getCardList().size(); i++) {
			Card temp = new Card();
			int randNum = 0;
			randNum = (int) (Math.random() * (cc.getCardList().size()));
			temp = cc.getCardList().get(randNum);
			cc.getCardList().set(randNum, cc.getCardList().get(i));
			cc.getCardList().set(i, temp);

		}
	}

	public void dealerDraw() {
		dealer.dealerCardList.add(0, cc.getCardList().get(0));
		cc.getCardList().remove(0);
		dealer.dealerPoint += pointMap.get(dealer.dealerCardList.get(0).getCard().substring(1));

	}

	public void playerDraw() {
		player.playerCardList.add(0, cc.getCardList().get(0));
		cc.getCardList().remove(0);
		player.playerPoint += pointMap.get(player.playerCardList.get(0).getCard().substring(1));
		System.out.println(player.playerCardList.get(0).getCard());
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
