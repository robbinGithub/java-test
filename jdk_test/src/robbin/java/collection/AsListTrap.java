package robbin.java.collection;

import java.util.Arrays;
import java.util.List;

public class AsListTrap {

	public static void main1(String[] args) {
		int[] ints = { 1, 2, 3, 4, 5 };
		List list = Arrays.asList(ints);
		System.out.println("list'size：" + list.size());
	}
	
	// ------------------------------------
	// outPut：
	// list'size：1
	
	public static void main(String[] args) {
        Integer[] ints = {1,2,3,4,5};
        List list = Arrays.asList(ints);
        System.out.println("list'size：" + list.size());
        System.out.println("list.get(0) 的类型:" + list.get(0).getClass());
        System.out.println("list.get(0) == ints[0]：" + list.get(0).equals(ints[0]));
    }
//    ----------------------------------------
//    outPut:
//    list'size：5
//    list.get(0) 的类型:class java.lang.Integer
//    list.get(0) == ints[0]：true
    
}
