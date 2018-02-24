package org.disrupto.demo6;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 事件对象（日志事件）
 * @author Administrator
 *
 */
public class LogEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6764935277845586755L;
	private long logId;
	private String content;
	private Date date;
	
	public LogEvent(){
		
	}
	
	public LogEvent(long logId, String content, Date date){
		this.logId = logId;
		this.content = content;
		this.date = date;
	}

	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString(){
		return JSONObject.toJSONString(this);
	}
	
}