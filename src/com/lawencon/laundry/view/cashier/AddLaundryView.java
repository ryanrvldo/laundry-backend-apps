package com.lawencon.laundry.view.cashier;

import com.lawencon.laundry.constant.LaundryTypes;
import com.lawencon.laundry.data.entity.*;
import com.lawencon.laundry.service.LaundryTransactionService;
import com.lawencon.laundry.service.impl.LaundryTransactionServiceImpl;
import com.lawencon.laundry.util.DateTimeUtils;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddLaundryView extends BaseView {

	private final LaundryTransactionService service = new LaundryTransactionServiceImpl();
	private final List<DetailTransaction> detailTransactionList = new ArrayList<>();
	private LaundryType laundryType;

	private Integer totalItems = 0;
	private BigDecimal totalCost = BigDecimal.ZERO;

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add Laundry ======");
		System.out.print("Laundry type code: ");
		String typeCode = readFromInput(String.class);

		try {
			this.laundryType = service.getLaundryTypeByCode(typeCode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			show(onViewFinished);
			return;
		}

		addItems();
		onViewFinished.onFinished();
	}

	private void addItems() {
		System.out.print("Laundry item code: ");
		String itemCode = readFromInput(String.class);

		Item item;
		try {
			item = service.getLaundryItemByCode(itemCode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addItems();
			return;
		}

		System.out.print("Item quantity: ");
		Integer quantity = readFromInput(Integer.class);
		totalItems += quantity;

		BigDecimal itemCost = BigDecimal.ZERO;
		if (laundryType.getCode().equalsIgnoreCase(LaundryTypes.UNIT_LAUNDRY.getCode())) {
			System.out.print("Item cost: ");
			itemCost = readFromInput(BigDecimal.class);
			totalCost = totalCost.add(itemCost.multiply(BigDecimal.valueOf(quantity)));
		}
		DetailTransaction detailTransaction = new DetailTransaction(null, item, quantity, itemCost, laundryType);
		detailTransactionList.add(detailTransaction);

		System.out.print("Want to add again [y/n]? ");
		String yesOrNoStr = readFromInput(String.class);
		if (yesOrNoStr.equalsIgnoreCase("y")) {
			this.addItems();
			return;
		} else if (!yesOrNoStr.equalsIgnoreCase("n") && !yesOrNoStr.equalsIgnoreCase("y")) {
			showInvalidMessage();
		}
		addTransaction();
	}

	private void addTransaction() {
		System.out.println("\nPlease input customer information first.");
		Profile profile = setProfile();
		Customer customer = new Customer(null, profile);

		System.out.printf("Finish date and time [%s]: ", DateTimeUtils.DATE_TIME_FORMAT);
		String dateTimeStr = readFromInput(String.class);
		LocalDateTime finishDateTime;
		try {
			finishDateTime = LocalDateTime.parse(dateTimeStr, DateTimeUtils.formatter);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addTransaction();
			return;
		}

		Double totalWeight = 0D;
		if (laundryType.getCode().equalsIgnoreCase(LaundryTypes.KILOS_LAUNDRY.getCode())) {
			System.out.print("Total cost: ");
			totalCost = readFromInput(BigDecimal.class);
			System.out.print("Total weight [kg]: ");
			totalWeight = readFromInput(Double.class);
		}

		String receiptNumber = String.format("TRX%d", System.currentTimeMillis());
		HeaderTransaction headerTransaction = new HeaderTransaction(receiptNumber, customer, LocalDateTime.now(),
				finishDateTime, totalCost, totalWeight);

		try {
			service.addLaundryTransaction(headerTransaction, detailTransactionList);
			printInvoice(receiptNumber, profile.getFullName());
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
		return new Profile(fullName, phoneNumber, email, address);
	}

	private void printInvoice(String trxNumber, String customerName) {
		System.out.println("\n----------------------------------------------------------");
		System.out.printf("Transaction number: %s%n", trxNumber);
		System.out.printf("Customer Name: %s%n", customerName);
		System.out.printf("Laundry type: %s%n", laundryType.getName());
		System.out.printf("Total item: %d%n", totalItems);
		System.out.printf("Total cost : %.2f%n", totalCost.doubleValue());
		System.out.printf("Success. Thank you.%n");
		System.out.println("----------------------------------------------------------\n");
	}

}
