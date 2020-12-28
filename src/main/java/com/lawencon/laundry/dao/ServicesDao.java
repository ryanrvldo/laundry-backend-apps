package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.Services;

/**
 * @author Rian Rivaldo
 */

public interface ServicesDao extends BaseDao<Services> {

  Services findById(Long id) throws Exception;

  Services findByCode(String code) throws Exception;
}
