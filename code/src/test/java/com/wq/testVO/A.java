package com.wq.testVO;

import java.io.Serializable;
import java.util.Date;

public class A implements Serializable {

	/**
	 * .
	 */
	private static final long serialVersionUID = 2631708877161067052L;

	private String str;

	private Integer iNum;

	private Long lNum;

	private Date date;

	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}

	/**
	 * @param str
	 *            the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}

	/**
	 * @return the iNum
	 */
	public Integer getiNum() {
		return iNum;
	}

	/**
	 * @param iNum
	 *            the iNum to set
	 */
	public void setiNum(Integer iNum) {
		this.iNum = iNum;
	}

	/**
	 * @return the lNum
	 */
	public Long getlNum() {
		return lNum;
	}

	/**
	 * @param lNum
	 *            the lNum to set
	 */
	public void setlNum(Long lNum) {
		this.lNum = lNum;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "A [str=" + str + ", iNum=" + iNum + ", lNum=" + lNum
				+ ", date=" + date + "]";
	}

}
