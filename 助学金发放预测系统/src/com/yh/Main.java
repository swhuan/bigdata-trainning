package com.yh;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Borrow borrow = new Borrow();
		BorrowNo borrowNo = new BorrowNo();
		Configuration conf = new Configuration();
		
		try {
			//borrow.uploadFile(conf,"/opt/yh/borrow_train.txt", "/yh/borrow_train.txt");
			
			borrow.getType(conf, "/yh/borrow_train.txt");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
