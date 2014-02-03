package com.tz.quiz.domain;

import java.io.PrintWriter;

import com.tz.quiz.support.Constants;

public class Output extends PrintWriter {

	private PrintWriter out = null;
	private StringBuffer logs = null;

	/**
	 * <pre>
	 * Output 기본 생성자.
	 * </pre>
	 * 
	 * @param logFileName
	 */
	public Output() {
		super(System.out, true);
		out = new PrintWriter(System.out);
		logs = new StringBuffer();
	}

	/**
	 * <pre>
	 * Accesslog 를 File에 Write하는 메소드
	 * </pre>
	 * 
	 * @param strInput
	 */
	public void println(String strInput) {
		synchronized (lock) {
			logs.append(strInput + "\n");
		}
	}

	/**
	 * <pre>
	 * Accesslog 를 File에 Write하는 메소드
	 * </pre>
	 * 
	 * @param strInput
	 */
	public void flush() {
		synchronized (lock) {
			out.print(logs);
			out.flush();
		}
	}
	
	/**
	 * <pre>
	 * Accesslog 를 File에 Write하는 메소드
	 * </pre>
	 * 
	 * @param strInput
	 */
	public static void debug(String strInput) {
		if (Constants.debug) {
			System.out.println(strInput);
		}
	}

}
