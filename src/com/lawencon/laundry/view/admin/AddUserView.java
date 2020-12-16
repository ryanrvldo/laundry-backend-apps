package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.constant.Roles;
import com.lawencon.laundry.data.entity.Profile;
import com.lawencon.laundry.data.entity.Role;
import com.lawencon.laundry.data.entity.User;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.service.impl.UserServiceImpl;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddUserView extends BaseView {

	private final UserService service = new UserServiceImpl();

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("====== Add New User ======");
		Profile profile = setProfile();
		User user = setUser(onViewFinished);
		if (user == null) {
			onViewFinished.onFinished();
			return;
		}
		user.setProfile(profile);

		try {
			service.addNewUser(user);
			System.out.printf("%nSuccess added %s to database.%n", user.getUsername());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		onViewFinished.onFinished();
	}

	private Profile setProfile() {
		System.out.print("Full Name: ");
		String fullName = readFromInput(String.class);
		System.out.print("Phone number: ");
		String phoneNumber = readFromInput(String.class);
		System.out.print("Email: ");
		String email = readFromInput(String.class);
		System.out.print("Address: ");
		String address = readFromInput(String.class);
		return new Profile(fullName, phoneNumber, email, address);
	}

	private User setUser(OnViewFinished onViewFinished) {
		System.out.print("Username: ");
		String username = readFromInput(String.class);
		System.out.print("Password: ");
		String password = readFromInput(String.class);
		System.out.print("What is user role?\n1. Admin\n2. Cashier\nChoose user role: ");
		Byte chosenRole = readFromInput(Byte.class);
		String roleCode;
		if (chosenRole == null) {
			onViewFinished.onFinished();
			return null;
		} else if (chosenRole == 1) {
			roleCode = Roles.ADMINISTRATOR.getCode();
		} else if (chosenRole == 2) {
			roleCode = Roles.CASHIER.getCode();
		} else {
			return null;
		}

		Role role = new Role();
		role.setCode(roleCode);
		try {
			role = service.getRole(role);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		User user = new User(username, password);
		user.setRole(role);
		return user;
	}

}
