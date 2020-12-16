package com.lawencon.laundry.view.cashier;

import com.lawencon.laundry.util.OnViewFinished;
import com.lawencon.laundry.view.BaseView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class CashierView extends BaseView {

	private final AddLaundryView addLaundryView = new AddLaundryView();

	@Override
	public void show(OnViewFinished onViewFinished) {
		System.out.print(buildMenuOf(
				"\n=============== Cashier ===============\n",
				"1. Add Laundry\n",
				"2. Logout\n",
				"Choose menu: "));
		Byte chosenMenu = readFromInput(Byte.class);
		if (chosenMenu == null) {
			onViewFinished.onFinished();
		} else if (chosenMenu == 1) {
			addLaundryView.show(() -> this.show(onViewFinished));
		} else {
			onViewFinished.onFinished();
		}
	}

}
