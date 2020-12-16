package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AdminView extends BaseView {

	private final AddUserView addUserView = new AddUserView();
	private final AddLaundryItemView addLaundryItemView = new AddLaundryItemView();
	private final AddLaundryTypeView addLaundryTypeView = new AddLaundryTypeView();
	private final AddNewMemberView addNewMemberView = new AddNewMemberView();
	private final AddNewMembershipView addNewMembershipView = new AddNewMembershipView();

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print(buildMenuOf(
				"\n=============== Admin ===============\n",
				"1. Add User\n",
				"2. Add Laundry Item\n",
				"3. Add New Membership\n",
				"4. Add New Member\n",
				"5. Add Laundry Type\n",
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
				addLaundryItemView.show(viewCallback);
				break;
			case 3:
				addNewMembershipView.show(viewCallback);
				break;
			case 4:
				addNewMemberView.show(viewCallback);
				break;
			case 5:
				addLaundryTypeView.show(viewCallback);
				break;
			case 0:
			default:
				break;
		}
		onViewFinished.onFinished();
	}

}
