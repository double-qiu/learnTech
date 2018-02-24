package org.disrupto.demo8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		SystemLog log = new SystemLog();
//		log.setCreateTime(new Date());
//		log.setEntityType("user");
//		log.setEntityId("user123456");
//		log.setOperateMessage("add new user");
		
		
		 /* 读入TXT文件 */  
        String pathname = "C:\\examResult.sql"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
        File filename = new File(pathname); // 要读取以上路径的input。txt文件  
        InputStreamReader reader;
		try {
			reader = new InputStreamReader(  
			        new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
			String line = "";  
			line = br.readLine();  
			List<SystemLog> logList = new ArrayList<SystemLog>();
			while (line != null) {  
				String[] logs = line.split(",");
				SystemLog log = new SystemLog();
				log.setEntityId(logs[0]);
				log.setId(logs[1]);
				log.setEntityType(logs[2]);
				log.setOperateMessage(logs[3]);
//				SystemLogDisruptor.processLog(log);
				logList.add(log);
				line = br.readLine(); // 一次读入一行数据
			}
			for (int i = 0; i < logList.size(); i++) {
				SystemLogDisruptor.processLog(logList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} // 建立一个输入流对象reader  

		
		
//		for (int i = 0; i < 5000; i++)
//		{
//			SystemLogDisruptor.processLog(log);
//		}
//		SystemLogDisruptor.processLog(log);
		
	}

}
