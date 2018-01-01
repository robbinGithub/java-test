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
//		tmp.put(null, "cdc"); Comparator����Ϊ��ʱ��Key�ſ��Ե���null
		Iterator<String> iterator_2 = tmp.keySet().iterator();
		while (iterator_2.hasNext()) {
			Object key = iterator_2.next();
			System.out.println("tmp.get(key) is :" + tmp.get(key));
		}
		
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		tmap.put(1, "����");
		tmap.put(3, "Ӣ��");
		tmap.put(2, "��ѧ");
		tmap.put(4, "����");
		tmap.put(5, "��ʷ");
		tmap.put(6, "����");
		tmap.put(7, "����");
		tmap.put(8, "��ѧ");
		for(Entry<Integer, String> entry : tmap.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
	}

}
