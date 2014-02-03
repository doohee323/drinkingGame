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

	private Output output = new Output(); // print the logging

	// logging status
	public void logStatus(List<Player> players) {
		output.append("There are " + players.size() + " players.");
//		output.append("It is " + players..getName() + "'s turn.");
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			output.append(player.getName() + " has had " + player.getDrunkCnt()
					+ " drinks.");
		}
		
		output.append("It is " + players.size() + " players.");
		
	}

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

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

}
