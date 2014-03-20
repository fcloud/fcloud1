/**
 * 
 */
package com.fcloud.wechat.user.init;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fcloud.util.IdGenerator;
import com.fcloud.wechat.user.model.User;
import com.fcloud.wechat.user.repository.UserRepository;

/**
 * @author ruben
 *
 */
@Component
public class DataInitialization implements InitializingBean {
	
	@Autowired
	private UserRepository userRepository;
	
	private User getAdmin() {
//		String id = Md5Crypt.md5Crypt("user:admin:123".getBytes(Charsets.UTF_8));
//		if (id.length() > 32) {
//			id = id.substring(0, 32);
//		}
		String id = IdGenerator.newId();
		User admin = new User();
		admin.setId(id);
		admin.setName("admin");
		admin.setPassword("admin123");
		admin.setStatus(1);
		admin.setUserLevel(99);
		return admin;
	}

	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {
		User admin = userRepository.findByName("admin");
		if (admin == null) {
			admin = getAdmin();
			userRepository.save(admin);
			System.out.println("insert new admin: " + admin.getId());
		}
	}

}
