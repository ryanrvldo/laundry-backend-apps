package com.lawencon.laundry.view;

import com.lawencon.laundry.constant.Roles;
import com.lawencon.laundry.data.entity.User;
import com.lawencon.laundry.service.UserService;
import com.lawencon.laundry.service.impl.UserServiceImpl;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.admin.AdminView;
import com.lawencon.laundry.view.cashier.CashierView;

import java.util.function.Consumer;

/**
 * @author Rian Rivaldo Rumapea
 */
public class MainView extends BaseView {

	private final UserService userService = new UserServiceImpl();
	private final AdminView adminView = new AdminView();
	CashierView cashierView = new CashierView();

	public MainView(OnViewFinished onViewFinished) {
		this.show(onViewFinished);
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
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
			login(onViewFinished, user -> {
				if (user.getRole().getCode().equals(Roles.ADMINISTRATOR.getCode())) {
					adminView.show(() -> this.show(onViewFinished));
				} else {
					cashierView.show(() -> this.show(onViewFinished));
				}
			});
		} else {
			onViewFinished.onFinished();
		}
	}

	private void login(OnViewFinished onViewFinished, Consumer<User> userConsumer) {
		System.out.print("Username: ");
		String username = readFromInput(String.class);
		System.out.print("Password: ");
		String password = readFromInput(String.class);
		try {
			User user = userService.validateLogin(new User(username, password));
			userConsumer.accept(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.show(onViewFinished);
		}
	}


}

