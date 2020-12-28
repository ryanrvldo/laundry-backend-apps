package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
public interface ProfileDao extends BaseDao<Profile> {

  Profile findById(Long id) throws Exception;

  Profile findByEmail(String email) throws Exception;

}
