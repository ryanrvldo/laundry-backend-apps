package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.data.entity.Membership;
import com.lawencon.laundry.service.MembershipService;
import com.lawencon.laundry.service.impl.MembershipServiceImpl;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddNewMembershipView extends BaseView {

	private final MembershipService service = new MembershipServiceImpl();
	private final List<Membership> membershipList = new ArrayList<>();

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add New Membership ======");
		addMemberships(onViewFinished);
	}

	private void addMemberships(OnViewFinished onViewFinished) {
		System.out.print("Membership code: ");
		String code = readFromInput(String.class);
		System.out.print("Membership package name: ");
		String packageName = readFromInput(String.class);
		System.out.print("Membership discount: ");
		Integer discount = readFromInput(Integer.class);
		Membership membership = new Membership(code.toUpperCase(), packageName, discount);
		membershipList.add(membership);

		System.out.print("Want to add again [y/n]? ");
		String yesOrNoStr = readFromInput(String.class);
		if (yesOrNoStr.equalsIgnoreCase("y")) {
			this.addMemberships(onViewFinished);
			return;
		} else if (!yesOrNoStr.equalsIgnoreCase("n") && !yesOrNoStr.equalsIgnoreCase("y")) {
			showInvalidMessage();
		}
		addMembershipListToDatabase();
		onViewFinished.onFinished();
	}

	private void addMembershipListToDatabase() {
		try {
			service.addAllMemberships(this.membershipList);
			this.membershipList.clear();
			System.out.println("Success added all membership");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
