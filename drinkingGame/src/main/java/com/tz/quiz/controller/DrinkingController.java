package com.tz.quiz.controller;

import java.util.ArrayList;
import java.util.List;

import com.tz.quiz.domain.Output;
import com.tz.quiz.domain.Player;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.service.DrinkingService;
import com.tz.quiz.support.Constants;

/**
 * <pre>
 * runnable class for the app.
 * </pre>
 * 
 */
public class DrinkingController {

	/**
	 * <pre>
	 * main(String[] args)
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Random DICE = new Random();
		// Scanner s = new Scanner(System.in);
		//
		// System.out.print("주사위를 굴려볼까요? (Enter를 누르세요!!)");
		// s.nextLine();
		// int value1 = DICE.nextInt(5) + 1;
		// Output.debug("사용자의 수는 : " + value1 + " 입니다");
		// Output.debug("컴퓨터가 주사위를 굴립니다!");
		// int value2 = DICE.nextInt(5) + 1;
		// Output.debug("컴퓨터의 수는 : " + value2 + " 입니다");
		// Output.debug();
		// Output.debug();
		// if (value1 > value2)
		// Output.debug("사용자가 이겼습니다!");
		// else if (value1 == value2)
		// Output.debug("비겼습니다! 다시 한번 도전해보세요!");
		// else
		// Output.debug("컴퓨터가 이겼습니다!");

		// 1) making game
		Roll roll = new Roll();
		Constants.radomPlay = false;
		Constants.debug = false;
			roll.setMaxDrinkCnt(2);
		roll.setPausetime(2);

		// 2) define players
		List<Player> players = new ArrayList<Player>();
		Player player1 = new Player("Alex", 2);
		Output.debug("0 / ready:" + player1.getName() + " ("
				+ player1.getDrinkingTime() + ")");
		players.add(player1);
		Player player2 = new Player("Bob", 3);
		Output.debug("0 / ready:" + player2.getName() + " ("
				+ player2.getDrinkingTime() + ")");
		players.add(player2);
		Player player3 = new Player("Chris", 5);
		Output.debug("0 / ready:" + player3.getName() + " ("
				+ player3.getDrinkingTime() + ")");
		players.add(player3);

		// 3) run the game
		DrinkingService game = new DrinkingService();
		game.playDrinkingGame(roll, players);

	}

}
