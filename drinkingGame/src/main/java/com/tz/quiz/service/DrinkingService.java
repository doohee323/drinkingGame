package com.tz.quiz.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tz.quiz.domain.Output;
import com.tz.quiz.domain.Player;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.support.Constants;

public class DrinkingService {

	public List<Output> playDrinkingGame(Roll roll, List<Player> players) {

		List<Output> outputs = new ArrayList<Output>();

		// 3) 순서 랜덤 처리
		Collections.shuffle(players);
		roll.setPlayers(players);

		int nSecond = 0;
		int nTurn = 0;
		boolean bStart = true;
		boolean bDrinking = false;
		boolean bWin = false;
		while (roll.getPlayers().size() > 1) {
			// 아직 마시고 있는 사람이 있으며 계속 주사위를 던진다.
			// check exist drinking player
			bDrinking = roll.getLeftDrintCnt() > 0 ? true : false;
			if (bStart || bDrinking) {
				// 주사위 던지기
				Player curPlayer = roll.getPlayers().get(nTurn);
				curPlayer.dice();
				nSecond++;
				System.out
				.println(nSecond + " / dice:" + curPlayer.getName() + " / bDrinking:" + bDrinking + " (" + curPlayer.getDiceVale() + ")" + " / bStart:" + bStart);
				bWin = Constants.isWin(curPlayer.getDiceVale());
				if (bWin) {
					// 램덤하게 드링커를 선택하고
					List<Player> drinkers = clone(roll.getPlayers(), curPlayer.getName());
					Collections.shuffle(drinkers);
					for (int i = 0; i < roll.getPlayers().size(); i++) {
						// 드링킹을 할당
						if (drinkers.get(0).getName()
								.equals(roll.getPlayers().get(i).getName())) {
							roll.getPlayers().get(i).addDrinking(nSecond, roll.getMaxDrinkCnt());
							roll.addLeftDrintCnt();
							break;
						}
					}
				}
			} else {
				nTurn++;
				if (nTurn >= roll.getPlayers().size()) {
					bStart = true;
					nTurn = 0;
				} else {
					bStart = false;
				}
			}

			// 드링킹 시간 정산
			for (int i = 0; i < roll.getPlayers().size(); i++) {
				// 드링킹해야 하는 사람의 드링킹 시간을 재조
				if (roll.getPlayers().get(i).getDrinkings().size() > 0) {
					if (roll.getPlayers().get(i).drinking(nSecond, roll.getMaxDrinkCnt())) { // finished
						roll.redueLeftDrintCnt();
					}
					if(roll.getPlayers().get(i).getDrunkSeq() == roll.getMaxDrinkCnt() && roll.getPlayers().get(i).getLeftDrinkingTime() == 0) {
						System.out
						.println(nSecond + " / droped off :" + roll.getPlayers().get(i).getName());
						roll.removePlayer(roll.getPlayers().get(i).getName());
					}
				}
			}
		}

		// 4) print output
		return outputs;
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
			if(!player.getName().equals(self)) {
				players.add(player);
			}
		}
		return players;
	}
}
