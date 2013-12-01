package com.fcloud.weservice.repository;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.support.SimpleRepository;
import com.fcloud.weservice.model.WePublicLogNews;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 回复图文消息
 *
 * @author
 * @version 1.0 2013-11-12
 */
@Repository
public class WePublicLogNewsRepository extends SimpleRepository<WePublicLogNews> {

}
