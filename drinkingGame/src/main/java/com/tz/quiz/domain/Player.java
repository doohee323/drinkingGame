package com.tz.quiz.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name = null;
	private String diceVale = null;
	private int drinkingTime = 0;
	private int drunkSeq = 0;
	private int curDrunkSeq = -1;
	private List<Drinking> drinkings = new ArrayList<Drinking>();

	/**
	 * <pre>
	 * drinking status of players
	 * </pre>
	 */
	public class Drinking {
		private int secondToDrink = 0;

		public Drinking() {
			secondToDrink = drinkingTime;
		}

		public int getSecondToDrink() {
			return secondToDrink;
		}

		public int drinking() {
			if (this.secondToDrink > 0)
				this.secondToDrink--;
			return this.secondToDrink;
		}
	}

	public Player(String name, int drinkingTime) {
		this.name = name;
		this.drinkingTime = drinkingTime;
	}

	/**
	 * <pre>
	 * dice operation
	 * </pre>
	 */
	public void dice() {
		int dice = (int) (Math.random() * 6 + 1);
		int dice2 = (int) (Math.random() * 6 + 1);
		this.diceVale = dice + "," + dice2;
	}

	/**
	 * <pre>
	 * add a drinking to drink to the list
	 * </pre>
	 * 
	 * @param int nSecond second for logging
	 * @param int maxDrinkingCnt maximum drinking count which this player can
	 *        drink
	 */
	public void addDrinking(int nSecond, int maxDrinkingCnt) {
		if (drunkSeq < maxDrinkingCnt) {
			// when finished this drinking, move to the next drinking
			if ((this.drinkings.size() - 1) == curDrunkSeq
					&& getLeftDrinkingTime() == 0)
				curDrunkSeq++;

			this.drinkings.add(new Drinking());
			System.out.println(nSecond + " / add drinking:" + name + " ("
					+ (this.drinkings.size() - 1) + ")");
			drunkSeq++;
		}
	}

	/**
	 * <pre>
	 * drinking operation
	 * </pre>
	 * 
	 * @param int nSecond second for logging
	 * @param int maxDrinkingCnt maximum drinking count which this player can
	 *        drink
	 * @return boolean finished this drinking or not
	 */
	public boolean drinking(int nSecond, int maxDrinkCnt) {
		// when nothing to drink, return false
		if (getLeftDrinkingTime() == 0)
			return false;

		// get the left time to drink this drinking
		int dringLeftTime = this.drinkings.get(curDrunkSeq).drinking();
		System.out.println(nSecond + " / " + this.getName()
				+ " is drinking up to :" + dringLeftTime
				+ ". and has next turn " + (this.drinkings.size() - 1));

		// recalculate current drinking sequence (curDrunkSeq)
		if (dringLeftTime == 0) {
			System.out.println(nSecond + " / finished drinking:" + name + " ("
					+ curDrunkSeq + ")");
			if ((this.drinkings.size() - 1) > curDrunkSeq) {
				curDrunkSeq++;
			}
			return true;
		}
		return false;
	}

	/**
	 * <pre>
	 * get the left time to drink this drinking
	 * </pre>
	 * 
	 * @return left time to drink
	 */
	public int getLeftDrinkingTime() {
		if (this.drinkings.size() == 0)
			return 0;
		return this.drinkings.get(this.drinkings.size() - 1).getSecondToDrink();
	}

	public int getDrunkSeq() {
		return drunkSeq;
	}

	public void setDrunkSeq(int drunkSeq) {
		this.drunkSeq = drunkSeq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDrinkingTime() {
		return drinkingTime;
	}

	public void setDrinkingTime(int drinkingTime) {
		this.drinkingTime = drinkingTime;
	}

	public String getDiceVale() {
		return diceVale;
	}

	public void setDiceVale(String diceVale) {
		this.diceVale = diceVale;
	}

	public List<Drinking> getDrinkings() {
		return drinkings;
	}

}
