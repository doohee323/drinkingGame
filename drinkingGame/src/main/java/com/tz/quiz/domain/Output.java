package com.tz.quiz.domain;

import java.io.PrintWriter;

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
			logs.append(strInput);
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

}
