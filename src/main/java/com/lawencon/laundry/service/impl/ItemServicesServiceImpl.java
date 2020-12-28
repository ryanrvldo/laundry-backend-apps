package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.ServicesDao;
import com.lawencon.laundry.entity.Services;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.ItemServicesService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class ItemServicesServiceImpl implements ItemServicesService {

  @Autowired
  private ValidationUtils validationUtils;

  @Autowired
  @Qualifier("service-nq")
  private ServicesDao servicesDao;

  @Override
  public void createService(Services service) throws Exception {
	Objects.requireNonNull(service, () -> "Service data must be submitted.");
	validationUtils.validateString(service.getCode(), service.getName());
	if (service.getHourDuration() <= 0) {
	  throw new IllegalArgumentException("Service duration must be greater than 0.");
	}
	servicesDao.insert(service);
  }

  @Transactional
  @Override
  public void updateService(Services service) throws Exception {
	Long id = Optional.ofNullable(service)
	    .map(i -> i.getId())
	    .orElseThrow(() -> new NullPointerException("Service id must be submitted"));
	validationUtils.validateEntityId(id, () -> getServiceById(id));
	validationUtils.validateString(service.getCode(), service.getName());
	if (service.getHourDuration() <= 0) {
	  throw new IllegalArgumentException("Service duration must be greater than 0.");
	}
	servicesDao.update(service);
  }

  @Transactional
  @Override
  public void deleteServiceById(Long id) throws Exception {
	Objects.requireNonNull(id, () -> "Service id must be submitted.");
	Services service = validationUtils.validateEntityId(id, () -> getServiceById(id));
	servicesDao.delete(service);
  }

  @Override
  public List<Services> getAllService() throws Exception {
	List<Services> serviceList = servicesDao.findAll();
	if (serviceList.isEmpty()) {
	  throw new NullPointerException("Service data is empty.");
	}
	return serviceList;
  }

  @Override
  public Services getServiceById(Long id) throws Exception {
	Objects.requireNonNull(id, () -> "Service id must be submitted.");
	validationUtils.validateEntityId(id, null);
	return Optional.ofNullable(validationUtils.validateGet(() -> servicesDao.findById(id)))
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  @Override
  public Services getServiceByCode(String code) throws Exception {
	Objects.requireNonNull(code, () -> "Service code must be submitted.");
	return Optional.ofNullable(validationUtils.validateGet(() -> servicesDao.findByCode(code)))
	    .orElseThrow(() -> new DataIsNotExistsException(code));
  }

}
