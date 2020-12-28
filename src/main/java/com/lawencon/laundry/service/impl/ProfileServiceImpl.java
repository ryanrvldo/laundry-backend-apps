package com.lawencon.laundry.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.laundry.dao.ProfileDao;
import com.lawencon.laundry.entity.Profile;
import com.lawencon.laundry.error.DataAlreadyExistsException;
import com.lawencon.laundry.error.DataIsNotExistsException;
import com.lawencon.laundry.service.ProfileService;
import com.lawencon.laundry.util.ValidationUtils;

/**
 * @author Rian Rivaldo
 */
@Service
public class ProfileServiceImpl implements ProfileService {

  @Autowired
  @Qualifier("profile-nq")
  private ProfileDao profileDao;

  @Autowired
  private ValidationUtils validationUtils;

  @Override
  public void createProfile(Profile profile) throws Exception {
	Objects.requireNonNull(profile, () -> "Profile data must be submitted.");
	validationUtils.validateString(profile.getFullName(), profile.getEmail(), profile.getPhone());

	Profile validatedProfile = validationUtils.validateGet(() -> profileDao.findByEmail(profile.getEmail()));
	if (validatedProfile != null) {
	  profile.setId(validatedProfile.getId());
	  throw new DataAlreadyExistsException(profile.getEmail());
	}
	profileDao.insert(profile);
  }

  @Override
  public Profile getProfileById(Long id) throws Exception {
	return Optional.ofNullable(validationUtils.validateGet(() -> profileDao.findById(id)))
	    .orElseThrow(() -> new DataIsNotExistsException(id));
  }

  @Override
  public Profile getProfileByEmail(String email) throws Exception {
	validationUtils.validateString(email);
	return Optional.ofNullable(validationUtils.validateGet(() -> profileDao.findByEmail(email)))
	    .orElseThrow(() -> new DataIsNotExistsException(email));
  }

  @Override
  public List<Profile> getAllProfile() throws Exception {
	List<Profile> profileList = profileDao.findAll();
	if (profileList.isEmpty()) {
	  throw new NullPointerException("Profiles data is empty.");
	}
	return profileList;
  }

  @Transactional
  @Override
  public void updateProfile(Profile profile) throws Exception {
	Long id = Optional.ofNullable(profile)
	    .map(p -> p.getId())
	    .orElseThrow(() -> new NullPointerException("Profile id must be submitted."));
	validationUtils.validateEntityId(id, () -> getProfileById(id));
	validationUtils.validateString(profile.getFullName(), profile.getEmail(), profile.getPhone());
	profileDao.update(profile);
  }

  @Transactional
  @Override
  public void deleteProfileById(Long id) throws Exception {
	validationUtils.validateEntityId(id, () -> getProfileById(id));
	Profile profile = new Profile();
	profile.setId(id);
	profileDao.delete(profile);
  }

}
