package com.dd.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ConvertRequestMapToMap {

	public static Map<String, String> convert(Map<String, String[]> requestParams) {
		Map<String, String> params = new HashMap<>();
		for (Entry<String, String[]> entry : requestParams.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			if (value != null && value.length != 0 && !value[0].isEmpty()) {
				params.put(key, value[0]);
			}
		}
		return params;
	}
	
}
