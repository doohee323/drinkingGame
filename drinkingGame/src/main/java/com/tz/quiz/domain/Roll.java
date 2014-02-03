package com.tz.quiz.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tz.quiz.support.Constants;

public class Roll {

	private int pausetime = Constants.defaultRollSpeed;
	private int maxDrinkCnt = Constants.defaultRollMaxCnt;
	private int leftDrintCnt = 0;
	private List<Player> players = new ArrayList<Player>();

	public int getPausetime() {
		return pausetime;
	}

	public void setPausetime(int pausetime) {
		this.pausetime = pausetime;
	}

	public int getMaxDrinkCnt() {
		return maxDrinkCnt;
	}

	public void setMaxDrinkCnt(int maxDrinkCnt) {
		this.maxDrinkCnt = maxDrinkCnt;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void removePlayer(String name) {
		List<Player> newPlayers = clone(players, name);
		this.players = newPlayers;
	}
	
	public int getLeftDrintCnt() {
		return leftDrintCnt;
	}

	public void addLeftDrintCnt() {
		this.leftDrintCnt++;
	}

	public void redueLeftDrintCnt() {
		this.leftDrintCnt--;
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
