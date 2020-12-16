package com.lawencon.laundry.view.admin;

import com.lawencon.laundry.data.entity.LaundryType;
import com.lawencon.laundry.service.LaundryTypeService;
import com.lawencon.laundry.service.impl.LaundryTypeServiceImpl;
import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rian Rivaldo Rumapea
 */
public class AddLaundryTypeView extends BaseView {

	private final LaundryTypeService service = new LaundryTypeServiceImpl();
	private final List<LaundryType> typeList = new ArrayList<>();

	@Override
	protected void show(OnViewFinished onViewFinished) {
		System.out.println("\n====== Add Laundry Type ======");
		addType(onViewFinished);
	}

	private void addType(OnViewFinished onViewFinished) {
		System.out.print("Type code: ");
		String code = readFromInput(String.class);
		System.out.print("Type name: ");
		String name = readFromInput(String.class);

		System.out.print("Want to add again [y/n]? ");
		String yesOrNoStr = readFromInput(String.class);
		LaundryType type = new LaundryType(code.toUpperCase(), name);
		typeList.add(type);
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
			service.addAllType(this.typeList);
			this.typeList.clear();
			System.out.println("Success added all types. Thank you.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
