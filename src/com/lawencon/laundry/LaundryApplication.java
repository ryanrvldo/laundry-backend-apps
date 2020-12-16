package com.lawencon.laundry;

import com.lawencon.laundry.view.MainView;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LaundryApplication {

	public static void main(String[] args) {
		new MainView(() -> {
			System.out.println("Thank you! See you again.");
			System.exit(1);
		});
	}

}
