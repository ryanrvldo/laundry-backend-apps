package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.data.entity.Customer;
import com.lawencon.laundry.data.entity.Member;
import com.lawencon.laundry.data.entity.Membership;
import com.lawencon.laundry.data.entity.Profile;
import com.lawencon.laundry.service.MemberService;
import com.lawencon.laundry.service.impl.MemberServiceImpl;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddNewMemberView extends BaseView {

	private final MemberService service = new MemberServiceImpl();

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add New Member ======");
		System.out.print("Choose from available member [y/n]? ");
		String yesOrNo = readFromInput(String.class);
		if (yesOrNo.equalsIgnoreCase("y")) {
			showAvailableCustomer(this::addNewCustomer);
		} else {
			addNewCustomer();
		}
		onViewFinished.onFinished();
	}

	private void showAvailableCustomer(OnViewFinished onViewFinished) {
		List<Customer> customerList;
		try {
			customerList = service.getCustomerList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			onViewFinished.onFinished();
			return;
		}

		int totalCustomer = customerList.size();
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.printf("| %3s | %-30s | %-20s | %-30s |%n", "No.", "Name", "Phone", "Email");
		System.out.println("------------------------------------------------------------------------------------------------");
		for (int i = 0; i < totalCustomer; i++) {
			Profile profile = customerList.get(i).getProfile();
			System.out.printf("| %-3d | %-30s | %-20s | %-30s |%n", i + 1, profile.getFullName(), profile.getPhone(), profile.getEmail());
			System.out.println("------------------------------------------------------------------------------------------------");
		}
		System.out.print("Choose available customer: ");
		Integer chosenIdx = readFromInput(Integer.class);
		Customer chosenCustomer = customerList.get(chosenIdx - 1);
		addNewMember(chosenCustomer);
	}

	private void addNewCustomer() {
		System.out.print("\nCustomer name: ");
		String name = readFromInput(String.class);
		System.out.print("Customer phone: ");
		String phone = readFromInput(String.class);
		System.out.print("Customer email: ");
		String email = readFromInput(String.class);
		System.out.print("Customer address: ");
		String address = readFromInput(String.class);
		Customer customer = new Customer();
		customer.setProfile(new Profile(name, phone, email, address));

		try {
			Customer addedCustomer = service.addNewCustomer(customer);
			addNewMember(addedCustomer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void addNewMember(Customer customer) {
		List<Membership> membershipList;
		try {
			membershipList = service.getMembershipList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		int totalMembership = membershipList.size();
		for (int i = 0; i < totalMembership; i++) {
			Membership membership = membershipList.get(i);
			System.out.printf("%d. %s (%s)%n", i + 1, membership.getPackageName(), membership.getCode());
		}
		System.out.print("Choose membership: ");
		Byte chosenIdx = readFromInput(Byte.class);
		Membership membership = membershipList.get(chosenIdx - 1);

		System.out.print("Member expired [yyyy-MM-dd]: ");
		String dateStr = readFromInput(String.class);
		LocalDate expiredDate;
		try {
			expiredDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			service.addNewMember(new Member(customer, membership, expiredDate));
			System.out.println("Success added new member!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
