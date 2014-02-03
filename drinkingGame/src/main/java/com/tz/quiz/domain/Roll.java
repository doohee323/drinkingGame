package com.tz.quiz.domain;

import java.util.ArrayList;
import java.util.List;

import com.tz.quiz.support.Constants;

/**
 * <pre>
 * model class for roll
 * </pre>
 * 
 */
public class Roll {

	private int pausetime = Constants.defaultRollSpeed; // rolling time
	private int maxDrinkCnt = Constants.defaultMaxDrinkCnt; // maximum count
															// which player can
															// drink in this
															// rolling
	private int leftDrintCnt = 0; // number of drinking player at this moment
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
		List<Player> newPlayers = Constants.clonePlayers(players, name);
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

}
