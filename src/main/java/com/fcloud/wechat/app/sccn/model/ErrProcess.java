package com.fcloud.wechat.app.sccn.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 故障处理
 * 
 * @author 573
 * @date 2013-11-18
 */
@DatabaseTable(tableName = "sccn_errprocess")
@TableOrder(10000)
public class ErrProcess extends Entity {

    @DatabaseField(columnName = "errReport")//所属故障
    private String errReport;

    @DatabaseField(columnName = "type")//故障分类
    private String type;

    @DatabaseField(columnName = "recovery_time")//故障恢复时间
    private String recovery_time;
    
    @DatabaseField(columnName = "reason")//故障原因
    private String reason;
    
    @DatabaseField(columnName = "process_record")//处理过程
    private String process_record;
    
    @DatabaseField(columnName = "openid")//录入人
    private String openid;

	public String getErrReport() {
		return errReport;
	}

	public void setErrReport(String errReport) {
		this.errReport = errReport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecovery_time() {
		return recovery_time;
	}

	public void setRecovery_time(String recovery_time) {
		this.recovery_time = recovery_time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProcess_record() {
		return process_record;
	}

	public void setProcess_record(String process_record) {
		this.process_record = process_record;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
    
    
    
}
