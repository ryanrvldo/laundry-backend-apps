package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.entity.ItemService;
import com.lawencon.laundry.service.ItemSrvService;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo
 */
public class AddLaundryServiceView extends BaseView {

	private final ItemSrvService service;
	private final List<ItemService> serviceList = new ArrayList<>();

	public AddLaundryServiceView(ItemSrvService service) {
		this.service = service;
	}

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add Laundry Service ======");
		addType(onViewFinished);
	}

	private void addType(OnViewFinished onViewFinished) {
		System.out.print("Service code: ");
		String code = readFromInput(String.class);
		System.out.print("Service name: ");
		String name = readFromInput(String.class);
		System.out.print("Service cost: ");
		BigDecimal cost = readFromInput(BigDecimal.class);
		System.out.print("Service duration [hour]: ");
		Long hourDuration = readFromInput(Long.class);

		System.out.print("Want to add again [y/n]? ");
		String yesOrNoStr = readFromInput(String.class);
		ItemService service = new ItemService(null, code.toUpperCase(), name, cost, hourDuration);
		serviceList.add(service);

		if (yesOrNoStr.equalsIgnoreCase("y")) {
			this.addType(onViewFinished);
			return;
		} else if (!yesOrNoStr.equalsIgnoreCase("n") && !yesOrNoStr.equalsIgnoreCase("y")) {
			showInvalidMessage();
		}
		addTypeListToDatabase();
		onViewFinished.onFinished();
	}

	private void addTypeListToDatabase() {
		try {
			service.addAllService(this.serviceList);
			this.serviceList.clear();
			System.out.println("Success added all types. Thank you.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
