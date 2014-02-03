package com.tz.quiz.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name = null;
	private String diceVale = null;
	private int drinkingTime = 0;
	private int drunkSeq = 0;
	private int curDrunkSeq = 0;
	private List<Drinking> drinkings = new ArrayList<Drinking>();
	
	public class Drinking {
		private int secondToDrink = 0;

		public Drinking() {
			secondToDrink = drinkingTime;
		}
		
		public int getSecondToDrink() {
			return secondToDrink;
		}

		public int drinking() {
			if(this.secondToDrink > 0) this.secondToDrink--;
			return this.secondToDrink;
		}
	}

	public Player(String name, int drinkingTime) {
		this.name = name;
		this.drinkingTime = drinkingTime;
	}

	// http://k.daum.net/qna/view.html?qid=3ed5I
	public void dice() {
		int dice = (int) (Math.random() * 6 + 1);
		int dice2 = (int) (Math.random() * 6 + 1);
		this.diceVale = dice + "," + dice2;
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

	public void addDrinking(int nSecond, int maxDrinkingCnt) {
		if(drunkSeq < maxDrinkingCnt) {
			this.drinkings.add(new Drinking());
			System.out
			.println(nSecond + " / add drinking:" + name + " (" + drunkSeq + ")");
			drunkSeq++;
		}
	}
	
	public boolean drinking(int nSecond, int maxDrinkCnt) {
		// 현재 마시고 있는 curDrunkSeq
		int dringLeftTime = this.drinkings.get(curDrunkSeq).drinking();
		System.out
		.println(nSecond + " / " + this.getName() + " is drinking up to :" + dringLeftTime + ". and has next turn " + (this.drinkings.size() - 1));
		
		if(dringLeftTime == 0) {
			if((this.drinkings.size() - 1) > curDrunkSeq) {
				System.out
				.println(nSecond + " / finished drinking:" + name + " (" + curDrunkSeq + ")" );
				curDrunkSeq++;
			}
			return true;
		}
		return false;
	}
	
	public int getLeftDrinkingTime() {
		return this.drinkings.get(curDrunkSeq).getSecondToDrink();
	}

	public int getDrunkSeq() {
		return drunkSeq;
	}

	public void setDrunkSeq(int drunkSeq) {
		this.drunkSeq = drunkSeq;
	}

}