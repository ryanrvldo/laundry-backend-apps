package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.data.entity.Item;
import com.lawencon.laundry.service.LaundryItemService;
import com.lawencon.laundry.service.impl.LaundryItemServiceImpl;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddLaundryItemView extends BaseView {

	private final LaundryItemService service = new LaundryItemServiceImpl();
	private final List<Item> itemList = new ArrayList<>();

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add Laundry Item ======");
		addItem(onViewFinished);
	}

	private void addItem(OnViewFinished onViewFinished) {
		System.out.print("Item code: ");
		String code = readFromInput(String.class);
		System.out.print("Item name: ");
		String name = readFromInput(String.class);
		Item item = new Item(code.toUpperCase(), name);
		itemList.add(item);

		System.out.print("Want to add again [y/n]? ");
		String yesOrNoStr = readFromInput(String.class);
		if (yesOrNoStr.equalsIgnoreCase("y")) {
			this.addItem(onViewFinished);
			return;
		} else if (!yesOrNoStr.equalsIgnoreCase("n") && !yesOrNoStr.equalsIgnoreCase("y")) {
			showInvalidMessage();
		}
		addItemListToDatabase();
		onViewFinished.onFinished();
	}

	private void addItemListToDatabase() {
		try {
			service.addAllItem(this.itemList);
			this.itemList.clear();
			System.out.println("Success added all items. Thank you.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
