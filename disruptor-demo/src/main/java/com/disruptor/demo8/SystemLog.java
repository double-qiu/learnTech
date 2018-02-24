package org.disrupto.demo8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>Description: 系统日志</p>
 * @since 2014-9-17
 * @author jim
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2014</p>
 */
public class SystemLog {
	
	/**全局日志类型常量：1操作日志，2异常日志*/
	public static int OPERATION_LOG_TYPE = 1;
	public static int EXCEPTION_LOG_TYPE = 2;
	
	private String id;
	
	private String creator;
	
	private Date createTime;
	
	private String entityId;
	
	private String entityType;
	
	/**日志类型：1操作日志，2异常日志*/
	private int logType;

	/**操作信息*/
	private String operateMessage;
	
	/**异常信息*/
	private String errorMessage;
	
	/**原数据*/
	private List<Map<String, Object>> origData = new ArrayList<Map<String, Object>>();
	
	/**新数据*/
	private List<Map<String, Object>> newData = new ArrayList<Map<String, Object>>();
	
	/**机构id*/
	private String institutionId;
	
	/**机构类型id*/
	private String institutionTypeId;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getInstitutionTypeId() {
		return institutionTypeId;
	}

	public void setInstitutionTypeId(String institutionTypeId) {
		this.institutionTypeId = institutionTypeId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getOperateMessage() {
		return operateMessage;
	}

	public void setOperateMessage(String operateMessage) {
		this.operateMessage = operateMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Map<String, Object>> getOrigData() {
		return origData;
	}

	public void setOrigData(List<Map<String, Object>> origData) {
		this.origData = origData;
	}

	public List<Map<String, Object>> getNewData() {
		return newData;
	}

	public void setNewData(List<Map<String, Object>> newData) {
		this.newData = newData;
	}
	
	public String getEntityType() {
		return entityType;
	}
	
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", creator=" + creator + ", createTime="
				+ createTime + ", entityId=" + entityId + ", entityType="
				+ entityType + ", logType=" + logType + ", operateMessage="
				+ operateMessage + ", errorMessage=" + errorMessage
				+ ", origData=" + origData + ", newData=" + newData
				+ ", institutionId=" + institutionId + ", institutionTypeId="
				+ institutionTypeId + "]";
	}

}
