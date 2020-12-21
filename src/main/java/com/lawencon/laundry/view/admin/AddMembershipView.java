package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.entity.Membership;
import com.lawencon.laundry.service.MembershipService;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class AddMembershipView extends BaseView {

	private final MembershipService service;
	private final List<Membership> membershipList = new ArrayList<>();

	public AddMembershipView(MembershipService service) {
		this.service = service;
	}

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add New Membership ======");
		addMemberships(onViewFinished);
	}

	private void addMemberships(OnViewFinished onViewFinished) {
		System.out.print("Membership code: ");
		String code = readFromInput(String.class).toUpperCase();
		System.out.print("Membership package name: ");
		String name = readFromInput(String.class);
		System.out.print("Membership discount: ");
		Double discount = readFromInput(Double.class);
		Membership membership = new Membership(null, code, name, discount);
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
