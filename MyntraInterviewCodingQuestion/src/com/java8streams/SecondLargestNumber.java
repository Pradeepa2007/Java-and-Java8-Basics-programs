package com.java8streams;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SecondLargestNumber {
	
	// This method  is to print only there is no duplicate entries in the map
	public static Map.Entry<String, Integer> find2NdLargetsNumber(int num, Map<String, Integer> map1) {
		return map1.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toList())
				.get(num-1);
	}
	
	// This method is to print the original value to the user when there is the duplicate entries in the map
	public static Map.Entry<Integer, List<String>> find2NdLargetsNumber1(int num, Map<String, Integer> map2) {
		return map2.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getValue, 
						Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
				.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
				.collect(Collectors.toList())
				.get(num-1);
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("Pradeep", 1000);
		map.put("Varsha", 1200);
		map.put("AJ", 1000);
		map.put("Guna", 1100);
		map.put("Adi", 1200);
		map.put("Umna", 1100);
		map.put("Malik", 1100);
		map.put("Kavya", 1000);
		System.out.println(find2NdLargetsNumber1(2, map));

	}
	
}

