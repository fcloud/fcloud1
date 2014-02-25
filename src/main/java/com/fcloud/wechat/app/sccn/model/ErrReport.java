package com.fcloud.wechat.app.sccn.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 故障上报
 * 
 * @author 573
 * @date 2013-11-18
 */
@DatabaseTable(tableName = "sccn_errreport")
@TableOrder(10001)
public class ErrReport extends Entity {

    @DatabaseField(columnName = "unit")//故障单位
    private String unit;
	
    @DatabaseField(columnName = "type")//故障分类
    private String type;
	
    @DatabaseField(columnName = "text")//故障内容
    private String text;
    
    @DatabaseField(columnName = "datetime")//故障发生时间
    private String datetime;
    
    @DatabaseField(columnName = "property")//故障性质
    private String property;

    @DatabaseField(columnName = "openid")//录入人
    private String openid;
    
    
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


   
}
