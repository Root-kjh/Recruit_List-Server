package com.DrK.entities;

import lombok.Data;

@Data
public class Page {

	private int StartPage;
	private int EndPage;
	private Criteria cri;
	private int total;
	public Page(Criteria cri,int total) {
		this.cri=cri;
		this.total=total;
	
		this.EndPage=(int)(Math.ceil(cri.getPageNum()/10.0))*10;
	}
}
