package com.tz.quiz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.tz.quiz.domain.Output;
import com.tz.quiz.domain.Player;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.support.Constants;

public class DrinkingService {

	public List<Output> playDrinkingGame(Roll roll, List<Player> players) {

		List<Output> outputs = new ArrayList<Output>();

		// // 3) 순서 랜덤 처리
		// Collections.shuffle(players);
		roll.setPlayers(players);

		int nSecond = 0;
		int nSeq = 0;
		int nTurn = 0;
		boolean bDrinking = false;
		boolean bWin = false;
		while (roll.getPlayers().size() > 1) {

			// 드링킹 시간 정산
			for (int i = 0; i < roll.getPlayers().size(); i++) {
				// 드링킹해야 하는 사람의 드링킹 시간을 재조
				Player player = roll.getPlayers().get(i);
				if (player.getDrinkings().size() > 0) {
					if (player.drinking(nSecond, roll.getMaxDrinkCnt())) { // finished
						roll.redueLeftDrintCnt();
						// 다 마셨으면 roll에 참가할 수 있다.
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

			// 게임 참가자가 1명 밖에 안남으면 끝내
			if(roll.getPlayers().size() < 2) {
				break;
			}

			// 주사위 던지기
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
					// 램덤하게 드링커를 선택하고
					List<Player> drinkers = clone(roll.getPlayers(),
							curPlayer.getName());
					// Collections.shuffle(drinkers);
					String selectedPlayer = drinkers.get(0).getName();
					for (int i = 0; i < roll.getPlayers().size(); i++) {
						// 드링킹을 할당
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
				// 아직 마시고 있는 사람이 있으면 이긴 사람이 계속 주사위를 던진다.
				if (!bDrinking) {
					// 마시고 있지 않는 다음 선수 찾기 
					nTurn = findNextDicer(roll, nTurn);
				}
			}
			
			nSecond++;
		}

		// 4) print output
		return outputs;
	}

	/**
	 * 마시고 있지 않는 다음 선수 찾기 
	 */
	public int findNextDicer(Roll roll, int nTurn) {
		// 아직 다 안마신 사람이 있으면 계속 던진다.
		// 그렇지 않았을 때에는 턴이 바뀐다.
		
		if(roll.getLeftDrintCnt() == 0) {
			nTurn++;
		}
//		nTurn++;
//		for (int i = nTurn; i < roll.getPlayers().size(); i++) {
//			if(roll.getPlayers().get(i).getLeftDrinkingTime() == 0) {
//				break;
//			} else {
//				nTurn++;
//			}
//		}
		if (nTurn == roll.getPlayers().size()) {
			nTurn = 0;
		}
		return nTurn;
	}
	
	/**
	 * <pre>
	 * </pre>
	 * 
	 * @param data
	 *            HashMap<String, Object>
	 * @return HashMap<String, Object>
	 */
	public List<Player> clone(List<Player> data, String self) {
		List<Player> players = new ArrayList<Player>();
		Iterator<Player> e = data.iterator();
		while (e.hasNext()) {
			Player player = e.next();
			if (!player.getName().equals(self)) {
				players.add(player);
			}
		}
		return players;
	}
}
