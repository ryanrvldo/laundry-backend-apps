package com.lawencon.laundry;

import com.lawencon.laundry.view.MainView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Rian Rivaldo
 */
public class LaundryApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("main.xml");
		MainView mainView = context.getBean("mainView", MainView.class);
		mainView.show(() -> {
			System.out.println("Thank you! See you again.");
			context.close();
			System.exit(1);
		});
	}

}
