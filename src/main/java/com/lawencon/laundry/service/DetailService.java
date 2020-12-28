package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.DetailTransaction;

/**
 * @author Rian Rivaldo
 */

public interface DetailService {

  void createDetail(DetailTransaction detail) throws Exception;

  List<DetailTransaction> getAllDetail() throws Exception;

  List<DetailTransaction> getAllDetailByHeaderId(Long id) throws Exception;

}
