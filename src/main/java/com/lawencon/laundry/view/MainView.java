package com.lawencon.laundry.view;

import com.lawencon.laundry.constant.Roles;
import com.lawencon.laundry.entity.Role;
import com.lawencon.laundry.entity.User;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.admin.AdminView;
import com.lawencon.laundry.view.cashier.CashierView;

import java.util.function.Consumer;

/**
 * @author Rian Rivaldo
 */
public class MainView extends BaseView {

	private final UserService userService;
	private final AdminView adminView;
	private final CashierView cashierView;

	public MainView(UserService userService, AdminView adminView, CashierView cashierView) {
		this.userService = userService;
		this.adminView = adminView;
		this.cashierView = cashierView;
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
		checkIsAUserExists(onViewFinished);
		System.out.print(buildMenuOf(
				"\n============= Laundry Apps =============\n",
				"1. Login\n",
				"2. Exit\n",
				"Choose menu: ")
		);
		Byte chosenMenu = readFromInput(Byte.class);
		if (chosenMenu == null || chosenMenu < 1 || chosenMenu > 2) {
			showInvalidMessage();
			show(onViewFinished);
		} else if (chosenMenu == 1) {
			login(onViewFinished, role -> {
				if (role.getCode().equals(Roles.ADMINISTRATOR.getCode())) {
					adminView.show(() -> this.show(onViewFinished));
				} else {
					cashierView.show(() -> this.show(onViewFinished));
				}
			});
		} else {
			onViewFinished.onFinished();
		}
	}

	private void checkIsAUserExists(OnViewFinished onViewFinished) {
		try {
			userService.checkAdminIsExist();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			onViewFinished.onFinished();
		}
	}

	private void login(OnViewFinished onViewFinished, Consumer<Role> roleConsumer) {
		System.out.print("Username: ");
		String username = readFromInput(String.class);
		System.out.print("Password: ");
		String password = readFromInput(String.class);

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		try {
			Role role = userService.validateLogin(user);
			roleConsumer.accept(role);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.show(onViewFinished);
		}
	}

}

