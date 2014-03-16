package com.fcloud.sys.att.repository;

import java.io.BufferedOutputStream;
import java.io.File;

import org.springframework.stereotype.Repository;

import com.alibaba.appengine.api.store.StoreService;
import com.alibaba.appengine.api.store.StoreServiceFactory;
import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.sys.att.model.SysAtt;
@Repository
public class SysAttRepository extends SimpleRepository<SysAtt>{

	public void deleteByAttId(String attId) throws Exception{
		SysAtt sysAtt = findOne(attId);
		if(sysAtt != null){
			StoreService storeService = StoreServiceFactory.getStoreService();
			if (storeService.isFileExist(sysAtt.getFileUrl())) {
				storeService.deleteFile(sysAtt.getFileUrl());
			}
		}
		delete(sysAtt);
	}
}
