package com.lawencon.laundry.dao.jpaImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.UserDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.repository.UserRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("user-jpa")
public class UserDaoJpaImpl implements UserDao {

  @Autowired
  private UserRepository repository;

  @Override
  public void insert(User data) throws Exception {
	repository.save(data);
  }

  @Override
  public void update(User data) throws Exception {
	repository.save(data);
  }

  @Override
  public void delete(User data) throws Exception {
	repository.deleteById(data.getId());
  }

  @Override
  public List<User> findAll() throws Exception {
	List<User> userList = new ArrayList<>();
	repository.findAllWithProfile()
	    .forEach(objArr -> userList.add(setupUser(objArr)));
	return userList;
  }

  @Override
  public User findByUsername(String username) throws Exception {
	Object obj = repository.findByUsername(username);
	return setupUser((Object[]) obj);
  }

  @Override
  public User findById(Long id) throws Exception {
	Object obj = repository.findUserById(id);
	return setupUser((Object[]) obj);
  }

  @Override
  public void updateActiveStatus(User data) throws Exception {
	repository.updateActiveStatus(data.getIsActive(), data.getId());
  }

  @Override
  public void updatePassword(User data) throws Exception {
	repository.updatePassword(data.getPassword(), data.getId());
  }

  private User setupUser(Object[] objArr) {
	User user = new User();
	user.setId(Long.parseLong(objArr[0].toString()));
	user.setUsername((String) objArr[1]);
	user.setPassword((String) objArr[2]);
	user.setIsActive((Boolean) objArr[3]);

	Profile profile = new Profile();
	profile.setId(Long.parseLong(objArr[4].toString()));
	profile.setFullName((String) objArr[5]);
	profile.setEmail((String) objArr[6]);
	profile.setPhone((String) objArr[7]);
	profile.setAddress((String) objArr[8]);
	user.setProfile(profile);

	Role role = new Role();
	role.setId(Long.parseLong(objArr[9].toString()));
	role.setCode((String) objArr[10]);
	role.setName((String) objArr[11]);
	user.setRole(role);
	return user;
  }

}
