package com.lawencon.laundry.dao;

import com.lawencon.laundry.entity.ItemDetails;

/**
 * @author Rian Rivaldo
 */

public interface ItemDetailsDao extends BaseDao<ItemDetails> {

  ItemDetails findById(Long id) throws Exception;

  ItemDetails findByCode(String code) throws Exception;

}
