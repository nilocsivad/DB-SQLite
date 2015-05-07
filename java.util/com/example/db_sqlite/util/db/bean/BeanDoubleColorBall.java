/**
 * 
 */
package com.example.db_sqlite.util.db.bean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author Administrator
 *
 */
public class BeanDoubleColorBall extends HashMap<String, Object> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8765219744302243533L;

	/**
	 * 
	 */
	public BeanDoubleColorBall() {
		// TODO Auto-generated constructor stub
	}
	
	private String date, number, redA, redB, redC, redD, redE, redF, blue, line;

	/**
	 * @param date
	 * @param number
	 * @param redA
	 * @param redB
	 * @param redC
	 * @param redD
	 * @param redE
	 * @param redF
	 * @param blue
	 * @param line
	 */
	public BeanDoubleColorBall(String date, String number, String redA, String redB, String redC, String redD, String redE, String redF, String blue, String line) {
		super();
		this.date = date;
		this.number = number;
		this.redA = redA;
		this.redB = redB;
		this.redC = redC;
		this.redD = redD;
		this.redE = redE;
		this.redF = redF;
		this.blue = blue;
		this.line = line;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the redA
	 */
	public String getRedA() {
		return redA;
	}

	/**
	 * @param redA the redA to set
	 */
	public void setRedA(String redA) {
		this.redA = redA;
	}

	/**
	 * @return the redB
	 */
	public String getRedB() {
		return redB;
	}

	/**
	 * @param redB the redB to set
	 */
	public void setRedB(String redB) {
		this.redB = redB;
	}

	/**
	 * @return the redC
	 */
	public String getRedC() {
		return redC;
	}

	/**
	 * @param redC the redC to set
	 */
	public void setRedC(String redC) {
		this.redC = redC;
	}

	/**
	 * @return the redD
	 */
	public String getRedD() {
		return redD;
	}

	/**
	 * @param redD the redD to set
	 */
	public void setRedD(String redD) {
		this.redD = redD;
	}

	/**
	 * @return the redE
	 */
	public String getRedE() {
		return redE;
	}

	/**
	 * @param redE the redE to set
	 */
	public void setRedE(String redE) {
		this.redE = redE;
	}

	/**
	 * @return the redF
	 */
	public String getRedF() {
		return redF;
	}

	/**
	 * @param redF the redF to set
	 */
	public void setRedF(String redF) {
		this.redF = redF;
	}

	/**
	 * @return the blue
	 */
	public String getBlue() {
		return blue;
	}

	/**
	 * @param blue the blue to set
	 */
	public void setBlue(String blue) {
		this.blue = blue;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	@Override
	public Object get(Object key) {
		String field = key.toString();
		Object result = null;
		try {
			result = this.getClass().getDeclaredMethod( "get" + field, null ).invoke( this );
		} catch (NoSuchMethodException e) { 
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		}
		return result;
	}

}
