package com.joyousasia.model.filter;

import java.io.Serializable;


public class DataTableParameterObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1794799942931955768L;

	
	String sEcho;
	
	int iDisplayStart;
	
	int iDisplayLength;
	
	int iSortCol_0;
	
	String sSortDir_0;
	
	String sSearch;
	
	
/* Constructor */
	
	public DataTableParameterObj(){
		
	}
		
	public DataTableParameterObj(String sEcho, int iDisplayStart,
			int iDisplayLength, int iSortCol_0, String sSortDir_0) {
		super();
		this.sEcho = sEcho;
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.iSortCol_0 = iSortCol_0;
		this.sSortDir_0 = sSortDir_0;
	}

	public DataTableParameterObj(String sEcho, int iDisplayStart,
			int iDisplayLength, int iSortCol_0, String sSortDir_0,
			String sSearch) {
		super();
		this.sEcho = sEcho;
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.iSortCol_0 = iSortCol_0;
		this.sSortDir_0 = sSortDir_0;
		this.sSearch = sSearch;
	}

/* Getter and Setter */


	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	
}
