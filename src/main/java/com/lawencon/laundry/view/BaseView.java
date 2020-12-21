package com.lawencon.laundry.view;

import com.lawencon.laundry.util.OnViewFinished;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Rian Rivaldo
 */
public abstract class BaseView {

	private final Scanner scanner = new Scanner(System.in);

	protected abstract void show(OnViewFinished onViewFinished);

	protected String buildMenuOf(String... menus) {
		if (menus.length == 0) return "";

		StringBuilder builder = new StringBuilder();
		for (String str : menus) {
			builder.append(str);
		}
		return builder.toString();
	}

	protected <T> T readFromInput(Class<T> inputClass) {
		try {
			if (inputClass.equals(String.class)) {
				String input = scanner.nextLine();
				if (input.isEmpty() || input.isBlank()) {
					throw new IllegalArgumentException("Input must be not empty string!");
				}
				return inputClass.cast(input);
			}

			Number input;
			IllegalArgumentException negativeException = new IllegalArgumentException("Input must be non negative number!");
			if (inputClass.equals(Byte.class)) {
				input = Byte.parseByte(scanner.nextLine());
				if (input.byteValue() < 0) throw negativeException;
			} else if (inputClass.equals(Integer.class)) {
				input = Integer.parseInt(scanner.nextLine());
				if (input.intValue() < 0) throw negativeException;
			} else if (inputClass.equals(BigDecimal.class)) {
				input = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
				if (input.doubleValue() < 0) throw negativeException;
			} else if (inputClass.equals(Double.class)) {
				input = Double.parseDouble(scanner.nextLine());
				if (input.doubleValue() < 0) throw negativeException;
			} else if (inputClass.equals(Long.class)) {
				input = Long.parseLong(scanner.nextLine());
				if (input.doubleValue() < 0) throw negativeException;
			} else {
				throw new ClassCastException("Not a valid input class.");
			}
			return inputClass.cast(input);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	protected void showInvalidMessage() {
		System.out.println("Invalid input. Please try again.");
	}

}
