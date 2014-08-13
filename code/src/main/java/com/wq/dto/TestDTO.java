package com.wq.dto;

import java.io.Serializable;
import java.util.Date;

public class TestDTO implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = -1536631111857815966L;

	private String s;

	private Integer i;

	private Long l;

	private Date d;

	/**
	 * @return the s
	 */
	public String getS() {
		return s;
	}

	/**
	 * @param s
	 *            the s to set
	 */
	public void setS(String s) {
		this.s = s;
	}

	/**
	 * @return the i
	 */
	public Integer getI() {
		return i;
	}

	/**
	 * @param i
	 *            the i to set
	 */
	public void setI(Integer i) {
		this.i = i;
	}

	/**
	 * @return the l
	 */
	public Long getL() {
		return l;
	}

	/**
	 * @param l
	 *            the l to set
	 */
	public void setL(Long l) {
		this.l = l;
	}

	/**
	 * @return the d
	 */
	public Date getD() {
		return d;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(Date d) {
		this.d = d;
	}

}
