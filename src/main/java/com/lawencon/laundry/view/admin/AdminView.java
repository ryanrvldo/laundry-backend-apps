package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

/**
 * @author Rian Rivaldo
 */
public class AdminView extends BaseView {

	private final AddUserView addUserView;
	private final AddItemView addItemView;
	private final AddLaundryServiceView addLaundryServiceView;
	private final AddMemberView addMemberView;
	private final AddMembershipView addMembershipView;

	public AdminView(AddUserView addUserView, AddItemView addItemView, AddLaundryServiceView addLaundryServiceView,
	                 AddMemberView addMemberView, AddMembershipView addMembershipView) {
		this.addUserView = addUserView;
		this.addItemView = addItemView;
		this.addLaundryServiceView = addLaundryServiceView;
		this.addMemberView = addMemberView;
		this.addMembershipView = addMembershipView;
	}

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print(buildMenuOf(
				"\n=============== Admin ===============\n",
				"1. Add User\n",
				"2. Add Laundry Item\n",
				"3. Add New Membership\n",
				"4. Add New Member\n",
				"5. Add Laundry Service\n",
				"0. Logout\n",
				"Choose menu: "));
		Byte chosenMenu = readFromInput(Byte.class);
		if (chosenMenu == null) {
			onViewFinished.onFinished();
			return;
		}

		OnViewFinished viewCallback = () -> this.show(onViewFinished);
		switch (chosenMenu) {
			case 1:
				addUserView.show(viewCallback);
				break;
			case 2:
				addItemView.show(viewCallback);
				break;
			case 3:
				addMembershipView.show(viewCallback);
				break;
			case 4:
				addMemberView.show(viewCallback);
				break;
			case 5:
				addLaundryServiceView.show(viewCallback);
				break;
			case 0:
			default:
				break;
		}
		onViewFinished.onFinished();
	}

}
