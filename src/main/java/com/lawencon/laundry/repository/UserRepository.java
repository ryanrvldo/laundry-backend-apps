package com.lawencon.laundry.repository;

import com.lawencon.laundry.entity.User;

import java.util.List;

/**
 * @author Rian Rivaldo
 */
public interface UserRepository extends BaseRepository<User> {

	List<User> getAll() throws Exception;

}
