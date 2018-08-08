package com.yh;

import java.util.HashMap;

public class BorrowNo {
	private int no =0;
	private int total = 0;
	//	HashMap<Book's type, borrowed number of this type>
	private HashMap<String, Integer> type = null;


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public HashMap<String, Integer> getType() {
		return type;
	}

	public void setType(HashMap<String, Integer> type) {
		
		this.type = type;
	}

}
