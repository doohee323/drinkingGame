package com.tz.quiz.domain;


public class Dice {

	// http://k.daum.net/qna/view.html?qid=3ed5I
	public int dice() {
		int dice = 0;
		
		dice = (int) (Math.random() * 6 + 1);

		return dice;
	}
}