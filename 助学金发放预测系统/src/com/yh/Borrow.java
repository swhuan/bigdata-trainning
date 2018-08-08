package com.yh;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class Borrow {
  private	HashMap<Integer, BorrowNo> bo = new HashMap<Integer, BorrowNo>();
	
  public void uploadFile(Configuration configuration, String srcs,String dsts) throws IOException{
		FileSystem fileSystem = FileSystem.get(configuration);
		Path src =new Path(srcs);
		Path dst =new Path(dsts);
		
		if (!fileSystem.exists(dst))
		{
			fileSystem.copyFromLocalFile(src, dst);
		}
		System.out.println("Upload file ok.");
  }
  
  //
  public void borrowCountTotal(Configuration conf,String file) throws IOException {
	  
	    FileSystem fs = FileSystem.get(conf);
	    Path path = new Path(file);
	    
	    String inputLine=null;
	    FSDataInputStream fsDataInputStream = fs.open(path);
	    while((inputLine=fsDataInputStream.readLine())!=null) {
	    	inputLine  = new String(inputLine.getBytes());
	    	String[] arrTemp = inputLine.split(",\"");
	    	if(arrTemp != null && arrTemp.length >= 1){
	    		int no = Integer.valueOf(arrTemp[0].trim());
	    		addTotal(no); 		
	    	}
	    }
	    fsDataInputStream.close();
	    fs.close();
	    System.out.println("total ok");
  }
  
  public void addTotal(int no) {
	BorrowNo bn = new BorrowNo();
	if(bo.containsKey(no)) {
		bn = bo.get(no);
		bo.remove(no);
	}
	bn.setNo(no);
    bn.setTotal(bn.getTotal()+1);
    bo.put(no, bn);
}
  
  //
  public LinkedList<String> getType(Configuration conf, String file) throws IOException
	{
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(file);

		LinkedList<String> lst = new LinkedList<String>();

		String inputLine=null;
		FSDataInputStream fsDataInputStream = fs.open(path);
		while((inputLine=fsDataInputStream.readLine())!=null)
		{
			inputLine=new String(inputLine.getBytes());
			String[] arrTemp = inputLine.split(",\"");
			if (arrTemp != null && arrTemp.length >= 4)
			{
				String tps = (arrTemp[3].trim());
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < tps.length(); i++)
				{
					if (Character.isAlphabetic(tps.charAt(i)))
					{
						sb.append(tps.charAt(i));
					}
					else
					{
						break;
					}
				}
				
				if ((sb.toString().trim().length() > 0) && (!lst.contains(sb.toString().trim())))
				{
					lst.add(sb.toString().trim());
				}
			}
		}
		
		fsDataInputStream.close();
		fs.close();
		return lst;
	}
  
  public HashMap<Integer, String> no_type(Configuration conf, String file) throws IOException {
	    FileSystem fs = FileSystem.get(conf);
	    Path path = new Path(file);
	    HashMap<Integer, String> no = new HashMap<Integer, String>();
	    
	    String inputLine=null;
	    FSDataInputStream fsDataInputStream = fs.open(path);
	    while((inputLine=fsDataInputStream.readLine())!=null) {
	    	inputLine  = new String(inputLine.getBytes());
	    	String[] arrTemp = inputLine.split(",\"");
	    	if(arrTemp != null && arrTemp.length >= 1){
	    	Integer	sno = Integer.valueOf(arrTemp[0].trim());
	    	String  type = arrTemp[3].trim();
	    	no.put(sno, type);
	    	}
	    }
	    fsDataInputStream.close();
	    fs.close();
	    
	return  no;
}
  
 

  
}
