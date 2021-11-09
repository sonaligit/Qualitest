package com.reusable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class sortByValue {

	// function to sort hashmap by values using stream collectors
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> testhashmap) {
		HashMap<String, Integer> tempMap = testhashmap.entrySet().stream()
				.sorted((i1, i2) -> i1.getValue().compareTo(i2.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return tempMap;
	}

}
