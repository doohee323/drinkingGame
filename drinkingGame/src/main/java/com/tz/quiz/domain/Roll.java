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
	private int maxDrinkingCnt = Constants.defaultMaxDrinkingCnt; // maximum
																	// count
	// which player can
	// drink in this
	// rolling
	private int leftDrintCnt = 0; // number of drinking player at this moment
	private List<Player> players = new ArrayList<Player>();
	private int nTurn = 0; // current turn of player
	private String addedDrinker = null; // added drinker name at last
	private String finishedDrinker = null; // finished drinker name at last
	private String dropedDrinker = null; // droped drinker name at last

	private Output output = new Output(); // print the logging

	public Roll() {
		output = new Output(); // print the logging
	}

	// logging status
	public void logStatus() {
		if (Constants.debug) return;
		Player curPlayer = getCurPlayer();
		output.println("==== STATUS ====");
		output.println("There are " + players.size() + " players.");
		output.println("It is " + curPlayer.getName() + "'s turn.");
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.getDrinkingTime() >= 0) {
				output.println(player.getName() + " has had "
						+ player.getDrunkCnt()
						+ " drinks and is currently drinking "
						+ player.getLeftDrinkingCnt() + " more.");
			} else {
				output.println(player.getName() + " has had "
						+ player.getDrunkCnt() + " drinks.");
			}
		}
		output.println("\n");
		output.println(curPlayer.getName() + "'s turn.");
		output.println("\n");
		output.println(curPlayer.getName() + " rolled "
				+ curPlayer.getDiceVale());
		if (addedDrinker != null) {
			output.println(curPlayer.getName() + " says: '" + addedDrinker
					+ ", drink!'");
		}
		if (finishedDrinker != null) {
			output.println(finishedDrinker + " is done drinking.");
		}
		if (dropedDrinker != null) {
			output.println(dropedDrinker
					+ " says: 'I've had too many. I need to stop.'");
		}
		output.println("\n");
		output.flush();
		addedDrinker = null;
		finishedDrinker = null;
		dropedDrinker = null;
	}

	// logging end
	public void logEnd() {
		if (Constants.debug) return;
		Player curPlayer = getCurPlayer();
		output.println("==== STATUS ====");
		output.println("The game is over. " + curPlayer.getName()
				+ "is the winner.");
		output.println("\n");
		output.println(curPlayer.getName() + "is the winner!");
		output.flush();
		addedDrinker = null;
		finishedDrinker = null;
		dropedDrinker = null;
	}

	public Player getCurPlayer() {
		return getPlayers().get(nTurn);
	}

	public int getPausetime() {
		return pausetime;
	}

	public void setPausetime(int pausetime) {
		this.pausetime = pausetime;
	}

	public int getMaxDrinkCnt() {
		return maxDrinkingCnt;
	}

	public void setMaxDrinkCnt(int maxDrinkingCnt) {
		this.maxDrinkingCnt = maxDrinkingCnt;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setMaxDrinkingCnt(maxDrinkingCnt);
		}
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

	public int getnTurn() {
		return nTurn;
	}

	public void setnTurn(int nTurn) {
		this.nTurn = nTurn;
	}

	public String getAddedDrinker() {
		return addedDrinker;
	}

	public void setAddedDrinker(String addedDrinker) {
		this.addedDrinker = addedDrinker;
	}

	public String getFinishedDrinker() {
		return finishedDrinker;
	}

	public void setFinishedDrinker(String finishedDrinker) {
		this.finishedDrinker = finishedDrinker;
	}

	public String getDropedDrinker() {
		return dropedDrinker;
	}

	public void setDropedDrinker(String dropedDrinker) {
		this.dropedDrinker = dropedDrinker;
	}
}
