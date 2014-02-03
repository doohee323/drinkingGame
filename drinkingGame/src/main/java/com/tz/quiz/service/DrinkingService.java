package com.tz.quiz.service;

import java.util.ArrayList;
import java.util.List;

import com.tz.quiz.domain.Output;
import com.tz.quiz.domain.Player;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.support.Constants;

/**
 * <pre>
 * business class for the app.
 * </pre>
 * 
 */
public class DrinkingService {

	/**
	 * <pre>
	 * clone players except for self in order to manipulate player list
	 * </pre>
	 * 
	 * @param List
	 *            <Player> input player to be cloned
	 * @param List
	 *            <Player> players participants of game
	 * @return List<Output> output
	 */
	public List<Output> playDrinkingGame(Roll roll, List<Player> players) {

		List<Output> outputs = new ArrayList<Output>();

		// // 3) 순서 랜덤 처리
		// Collections.shuffle(players);
		roll.setPlayers(players);

		int nSecond = 0; // time by second
		int nSeq = 0; // for rolling time (speed of game)
		int nTurn = 0; // current turn of player
		boolean bDrinking = false; // whether exist currently drinking person
		boolean bWin = false; // case of getting winning value
		while (roll.getPlayers().size() > 1) {

			// calculate for drinker's drinking time
			for (int i = 0; i < roll.getPlayers().size(); i++) {
				Player player = roll.getPlayers().get(i);
				if (player.getDrinkings().size() > 0) {
					if (player.drinking(nSecond, roll.getMaxDrinkCnt())) { // finished
						roll.redueLeftDrintCnt();
						// once finished drinking, can join rolling agin
						nTurn = findNextDicer(roll, nTurn);
					}
					if (player.getDrunkSeq() == roll.getMaxDrinkCnt()
							&& player.getLeftDrinkingTime() == 0) {
						System.out.println(nSecond + " / droped off :"
								+ player.getName());
						roll.removePlayer(player.getName());
					}
				}
			}

			// if only one player is left, game finish
			if (roll.getPlayers().size() < 2) {
				break;
			}

			// dicing considering with game speed
			if (nSecond == 0 || nSecond >= (roll.getPausetime() * nSeq)) {
				Player curPlayer = roll.getPlayers().get(nTurn);
				curPlayer.dice();

				// check exist drinking player
				bDrinking = roll.getLeftDrintCnt() > 0 ? true : false;

				System.out.println(nSecond + " / dice:" + curPlayer.getName()
						+ " (" + nTurn + ") / bDrinking:" + bDrinking + " ("
						+ curPlayer.getDiceVale() + ")");
				bWin = Constants.isWin(curPlayer.getDiceVale());
				if (bWin) {
					// choose driker at ramdon
					List<Player> drinkers = Constants.clonePlayers(
							roll.getPlayers(), curPlayer.getName());
					// Collections.shuffle(drinkers);
					String selectedPlayer = drinkers.get(0).getName();
					for (int i = 0; i < roll.getPlayers().size(); i++) {
						// assign a drinking to drinker
						Player player = roll.getPlayers().get(i);
						if (selectedPlayer.equals(player.getName())) {
							roll.getPlayers()
									.get(i)
									.addDrinking(nSecond, roll.getMaxDrinkCnt());
							roll.addLeftDrintCnt();
							break;
						}
					}
				}
				nSeq++;
				// when existing drinking plaer, current winner can roll again.
				if (!bDrinking) {
					// if else, find the next player who is'nt drinking
					nTurn = findNextDicer(roll, nTurn);
				}
			}

			nSecond++;
		}

		// 4) print output
		return outputs;
	}

	/**
	 * <pre>
	 * find the next player who is'nt drinking
	 * </pre>
	 * 
	 * @param Roll
	 *            roll <Player> current roll
	 * @param int nTurn current rolling turn
	 * @return int next rolling turn
	 */
	public int findNextDicer(Roll roll, int nTurn) {
		// if there is no one drinking, turn change.
		if (roll.getLeftDrintCnt() == 0) {
			nTurn++;
		}

		// if the next player is drinking, then the turn pass to the next one.
		// nTurn++;
		// for (int i = nTurn; i < roll.getPlayers().size(); i++) {
		// if(roll.getPlayers().get(i).getLeftDrinkingTime() == 0) {
		// break;
		// } else {
		// nTurn++;
		// }
		// }

		if (nTurn == roll.getPlayers().size()) {
			nTurn = 0;
		}
		return nTurn;
	}

}
