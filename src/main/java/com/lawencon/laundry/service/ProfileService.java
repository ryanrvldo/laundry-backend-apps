package com.lawencon.laundry.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.laundry.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@Service
public interface ProfileService {

  void createProfile(Profile profile) throws Exception;

  Profile getProfileById(Long id) throws Exception;

  Profile getProfileByEmail(String email) throws Exception;

  List<Profile> getAllProfile() throws Exception;

  void updateProfile(Profile profile) throws Exception;

  void deleteProfileById(Long id) throws Exception;

}
