package com.lawencon.laundry.view.cashier;

import com.lawencon.laundry.constant.LaundryServices;
import com.lawencon.laundry.entity.*;
import com.lawencon.laundry.service.LaundryTransactionService;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class AddLaundryView extends BaseView {

	private final LaundryTransactionService service;
	private final List<DetailTransaction> detailTransactionList = new ArrayList<>();
	private LaundryServices laundryServices;

	public AddLaundryView(LaundryTransactionService service) {
		this.service = service;
	}

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add Laundry ======");
		System.out.print("Laundry service code: ");
		String laundryServiceCode = readFromInput(String.class);

		try {
			this.laundryServices = service.getLaundryServiceByCode(laundryServiceCode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			onViewFinished.onFinished();
			return;
		}

		addItems();
		onViewFinished.onFinished();
	}

	private void addItems() {
		System.out.print("Item service code: ");
		String serviceCode = readFromInput(String.class);

		ItemService itemService;
		try {
			itemService = service.getItemServiceByCode(serviceCode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addItems();
			return;
		}
		System.out.print("Item code: ");
		String itemCode = readFromInput(String.class);

		Item item = new Item();
		item.setCode(itemCode);
		item.setItemService(itemService);
		try {
			item = service.getLaundryItemByCode(item);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addItems();
			return;
		}

		System.out.print("Item quantity: ");
		Integer quantity = readFromInput(Integer.class);
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime finishDate = startDate.plusHours(itemService.getHourDuration());

		DetailTransaction detailTransaction = new DetailTransaction();
		detailTransaction.setQuantity(quantity);
		detailTransaction.setItem(item);
		detailTransaction.setFinishDate(finishDate);
		detailTransactionList.add(detailTransaction);

		System.out.print("Want to add again [y/n]? ");
		String yesOrNoStr = readFromInput(String.class);
		if (yesOrNoStr.equalsIgnoreCase("y")) {
			this.addItems();
			return;
		} else if (!yesOrNoStr.equalsIgnoreCase("n") && !yesOrNoStr.equalsIgnoreCase("y")) {
			showInvalidMessage();
		}
		addTransaction(startDate);
	}

	private void addTransaction(LocalDateTime startDate) {
		System.out.println("\nPlease input customer information first.");
		Profile profile = setProfile();
		Customer customer = new Customer(null, profile, LocalDateTime.now());

		BigDecimal totalCost = BigDecimal.ZERO;
		Double totalWeight = 0D;
		if (this.laundryServices.equals(LaundryServices.KILOS_LAUNDRY)) {
			System.out.print("Total cost: ");
			totalCost = readFromInput(BigDecimal.class);
			System.out.print("Total weight [kg]: ");
			totalWeight = readFromInput(Double.class);
		}

		HeaderTransaction headerTransaction = new HeaderTransaction();
		headerTransaction.setCustomer(customer);
		headerTransaction.setStartDate(startDate);
		headerTransaction.setTotalCost(totalCost);
		headerTransaction.setTotalWeight(totalWeight);

		try {
			service.addLaundryTransaction(headerTransaction, detailTransactionList);
			printInvoice(headerTransaction);
			this.detailTransactionList.clear();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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
		return new Profile(null, fullName, phoneNumber, email, address);
	}

	private void printInvoice(HeaderTransaction headerTransaction) {
		System.out.println("\n----------------------------------------------------------");
		System.out.printf("Transaction number: %s%n", headerTransaction.getReceiptNumber());
		System.out.printf("Customer Name: %s%n", headerTransaction.getCustomer().getProfile().getFullName());
		System.out.printf("Laundry type: %s%n", laundryServices);
		System.out.printf("Total cost : %.2f%n", headerTransaction.getTotalCost());
		System.out.printf("Success. Thank you.%n");
		System.out.println("----------------------------------------------------------\n");
	}

}
