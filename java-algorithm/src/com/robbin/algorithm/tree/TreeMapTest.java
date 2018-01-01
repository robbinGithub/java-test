package com.robbin.algorithm.tree;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {
    
	@Test
	public void test_01() {
		TreeMap<String, String> tmp = new TreeMap<String, String>();
		tmp.put("a", "aaa");
		tmp.put("b", "bbb");
		tmp.put("c", "ccc");
		tmp.put("d", "cdc");
//		tmp.put(null, "cdc"); Comparator不能为空时，Key才可以等于null
		Iterator<String> iterator_2 = tmp.keySet().iterator();
		while (iterator_2.hasNext()) {
			Object key = iterator_2.next();
			System.out.println("tmp.get(key) is :" + tmp.get(key));
		}
		
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		tmap.put(1, "语文");
		tmap.put(3, "英语");
		tmap.put(2, "数学");
		tmap.put(4, "政治");
		tmap.put(5, "历史");
		tmap.put(6, "地理");
		tmap.put(7, "生物");
		tmap.put(8, "化学");
		for(Entry<Integer, String> entry : tmap.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
	}

}
