package com.fcloud.wechat.app.cihezh.model;

import com.fcloud.core.model.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 参展商
 * 
 * @author 573
 * @date 2013-11-18
 */
@DatabaseTable(tableName = "cihezh_czs")
public class Canzhanshang extends Entity {

    @DatabaseField(columnName = "name")//公司名称
    private String name;
	
    @DatabaseField(columnName = "lxfs")//联系方式
    private String lxfs;
	
    @DatabaseField(columnName = "type")//公司类型
    private String type;
    
    @DatabaseField(columnName = "czsp")//参展产品
    private String czsp;
    
    @DatabaseField(columnName = "czmd")//参展目的
    private String czmd;

    @DatabaseField(columnName = "hkgg")//会刊广告
    private String hkgg;
   
    @DatabaseField(columnName = "zwyd")//展位预定
    private String zwyd;
    
    @DatabaseField(columnName = "mp")//门票等印刷品
    private String mp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCzsp() {
		return czsp;
	}

	public void setCzsp(String czsp) {
		this.czsp = czsp;
	}

	public String getCzmd() {
		return czmd;
	}

	public void setCzmd(String czmd) {
		this.czmd = czmd;
	}

	public String getHkgg() {
		return hkgg;
	}

	public void setHkgg(String hkgg) {
		this.hkgg = hkgg;
	}

	public String getZwyd() {
		return zwyd;
	}

	public void setZwyd(String zwyd) {
		this.zwyd = zwyd;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

   
}
