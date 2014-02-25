/**
 * 
 */
package com.fcloud.wechat.auth.model;

import com.fcloud.core.model.Persistable;
import com.fcloud.core.repository.TableOrder;
import com.fcloud.wechat.user.model.User;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author ruben
 *
 */
@SuppressWarnings("serial")
@DatabaseTable(tableName = "we_auth_module_access_map")
@TableOrder(19)
public class ModuleAccessMapping implements Persistable {
	
	public static final String buildId(User user, ModuleAccess ma) {
		return user.getId() + "x" + ma.getId();
	}
	
	public ModuleAccessMapping() {}
	
	public ModuleAccessMapping(User user, ModuleAccess ma) {
		this.user = user;
		this.module = ma;
		this.id = buildId(user, ma);
	}

    @DatabaseField(id = true, columnName = "id", width = 73, canBeNull = false)
	private String id;

	@Override
	public String getId() {
		if (id == null && module != null && user != null) {
			id = buildId(user, module);
		}
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}
	
	@DatabaseField(columnName = "user_id", width = 36, index = true, canBeNull = false, foreign = true)
	private User user;
	
	@DatabaseField(columnName = "module_id", width = 36, index = true, canBeNull = false, foreign = true)
	private ModuleAccess module;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ModuleAccess getModule() {
		return module;
	}

	public void setModule(ModuleAccess module) {
		this.module = module;
	}

}
