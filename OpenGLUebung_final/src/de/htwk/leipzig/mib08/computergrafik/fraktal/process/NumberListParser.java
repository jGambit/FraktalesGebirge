package de.htwk.leipzig.mib08.computergrafik.fraktal.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberListParser {
	
	private static final String SEPERATOR_REGEX = "[^\\d.,-]";
	private final String value;
	
	public NumberListParser(String input) {
		this(Arrays.asList(input == null ? "" : input));
	}
	
	public NumberListParser(List<String> input) {
		input = input == null ? Arrays.asList("") : input;
		value = concatenate(input, " ");
	}

	private String concatenate(List<String> input, String seperator) {
		StringBuilder concatenator = new StringBuilder();
		for (String value : input) {
			concatenator.append(value).append(seperator);
		}
		return concatenator.toString();
	}
	
	public List<Number> getNumberList() {
		String[] numbers = parseInput();
		List<Number> result = new ArrayList<>();
		
		for (String number : numbers) {
			Float f = convertToNumber(number);
			if (f != null) {
				result.add(f);
			}
		}
		
		return result;
	}

	private Float convertToNumber(String number) {
		Float result = null;
		try {
			result = Float.valueOf(number);
		} catch (NumberFormatException e) {
			result = null;
		}
		return result;
	}

	private String[] parseInput() {
		return value.split(SEPERATOR_REGEX);
	}
	
}
