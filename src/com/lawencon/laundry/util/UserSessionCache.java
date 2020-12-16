package com.lawencon.laundry.util;

import com.lawencon.laundry.data.entity.User;

/**
 * @author Rian Rivaldo Rumapea
 */
public class UserSessionCache {

  private static UserSessionCache instance = null;
  private User activeUser;

  public static UserSessionCache getInstance() {
    if (instance == null) {
      synchronized (UserSessionCache.class) {
        if (instance == null) {
          instance = new UserSessionCache();
        }
      }
    }
    return instance;
  }

  public User getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(User activeUser) {
    this.activeUser = activeUser;
  }
}
