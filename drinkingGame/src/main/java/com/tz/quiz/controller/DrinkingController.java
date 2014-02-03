package com.tz.quiz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tz.quiz.domain.Player;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.service.DrinkingService;

public class DrinkingController {

	/**
	 * <pre>
	 * main(String[] args)
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		Random DICE = new Random();
//		Scanner s = new Scanner(System.in);
//
//		System.out.print("주사위를 굴려볼까요? (Enter를 누르세요!!)");
//		s.nextLine();
//		int value1 = DICE.nextInt(5) + 1;
//		System.out.println("사용자의 수는 : " + value1 + " 입니다");
//		System.out.println("컴퓨터가 주사위를 굴립니다!");
//		int value2 = DICE.nextInt(5) + 1;
//		System.out.println("컴퓨터의 수는 : " + value2 + " 입니다");
//		System.out.println();
//		System.out.println();
//		if (value1 > value2)
//			System.out.println("사용자가 이겼습니다!");
//		else if (value1 == value2)
//			System.out.println("비겼습니다! 다시 한번 도전해보세요!");
//		else
//			System.out.println("컴퓨터가 이겼습니다!");
		
		
		// 1) 게임 만들기
		Roll roll = new Roll();
		roll.setMaxDrinkCnt(2);
		roll.setPausetime(2);
		
		// 2) 선수 입장
		List<Player> players = new ArrayList<Player>();
		Player player1 = new Player("Alex", 4);
		players.add(player1);
		Player player2 = new Player("Bob", 3);
		players.add(player2);
//		Player player3 = new Player("Chris", 5);
//		players.add(player3);

		DrinkingService game = new DrinkingService();
		game.playDrinkingGame(roll, players);
		
	}

}
