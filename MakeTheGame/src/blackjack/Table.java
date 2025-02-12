package blackjack;

import java.util.Scanner;

public class Table {
	public static void main(String[] args) {
		BlackjackAction bj = new BlackjackAction();
		String input = "";
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			while(bj.gameOver==false) {
		bj.startBlackjack();
		
			
		bj.DealerFirstOpen();
		if(bj.gameOver == true) {
			continue;
		}
		
		bj.playerFirstOpen();
		if(bj.gameOver == true) {
			continue;
		}
		bj.playerHit();
		if(bj.gameOver == true) {
			continue;
		}
		bj.dealerHit();
		if(bj.gameOver == true) {
			continue;
		}
		bj.result();
			}
		System.out.println("소지금" + bj.player.playerMoney);
		System.out.println("게임을 계속하시겠습니까? y/n");
		input = sc.next();
		if(input.toLowerCase().equals("n")) {
			return;
		}else if(input.toLowerCase().equals("y")) {
			bj.gameOver = false;
			sc.nextLine();
			continue;
		}
		
	}

		
		
	}
}
