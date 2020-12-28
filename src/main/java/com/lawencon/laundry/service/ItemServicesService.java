package com.lawencon.laundry.service;

import java.util.List;

import com.lawencon.laundry.entity.Services;

/**
 * @author Rian Rivaldo
 */

public interface ItemServicesService {

  void createService(Services service) throws Exception;

  void updateService(Services service) throws Exception;

  void deleteServiceById(Long id) throws Exception;

  List<Services> getAllService() throws Exception;

  Services getServiceById(Long id) throws Exception;

  Services getServiceByCode(String code) throws Exception;

}
