package com.fcloud.wechat.app.cihezh.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 展会反馈
 * 
 * @author 573
 * @date 2013-11-18
 */
@DatabaseTable(tableName = "cihezh_zhfk")
@TableOrder(10001)
public class Zhanhfk extends Entity {

    @DatabaseField(columnName = "fknr")//反馈内容
    private String fknr;
	
    @DatabaseField(columnName = "tel")//手机号
    private String tel;
	
    @DatabaseField(columnName = "wenum")//微信号
    private String wenum;

	public String getFknr() {
		return fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWenum() {
		return wenum;
	}

	public void setWenum(String wenum) {
		this.wenum = wenum;
	}
    

   
}
