package com.tz.quiz.domain;

public class Output {

	private int rollSn = 0; // roll Number
	private int playerCnt = 0; // playerCnt for roll

	public Output(int rollSn, int playerCnt) {
		this.rollSn = rollSn;
		this.playerCnt = playerCnt;
	}

	public String getlog() {
		String log = "Case #" + this.rollSn + ": " + this.playerCnt;
		return log;
	}

	public void print() {
		String log = "Case #" + this.rollSn + ": " + this.playerCnt;
		System.out.println(log);
	}

	public int getRollSn() {
		return rollSn;
	}

	public void setRollSn(int rollSn) {
		this.rollSn = rollSn;
	}

	public int getDrinkingCnt() {
		return playerCnt;
	}

	public void setDrinkingCnt(int playerCnt) {
		this.playerCnt = playerCnt;
	}
}
