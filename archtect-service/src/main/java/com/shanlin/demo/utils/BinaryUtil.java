/**
 * 
 */
package com.shanlin.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shanlin
 *
 */
public class BinaryUtil {
	public static final int type0 = 0x0001;
	public static final int type1 = 0x0002;
	public static final int type2 = 0x0004;
	public static final int type3 = 0x0008;
	public static final int type4 = 0x0010;
	public static final int type5 = 0x0020;
	public static final int type6 = 0x0040;
	public static final int type7 = 0x0080;
	public static final int type8 = 0x0100;
	
	public static List<Integer> getCombination(int mix){
		List<Integer> list = new ArrayList<Integer>();
		String bin = Integer.toBinaryString(mix);
		
		char c;
		char[]  bins = bin.toCharArray();
		System.out.println(bin);
		for (int i = 0; i <bins.length; i++) {
			c = bins[i];
			if (c == '1') {
				list.add(2<<(bins.length-i-2));
			}
		}
		
		return list;
	}
	
		private BinaryUtil(){
		
	}
	/**
	 * 拆分，如：26=16+8+2
	 * 
	 * @param target 大于0的整数
	 * @return
	 */
	public static List<Integer> split(int target){
		Assert.isTrue(target>0);
		
		return getNext(new ArrayList<Integer>(), target, 0);
	}
	/**
	 * 转换
	 * 
	 * @param list
	 * @param target
	 * @param pos
	 * @return
	 */
	private static List<Integer> getNext(List<Integer> list,int target,int pos){
		int quotient = target/2;
		int modulo = target%2;
		
		if (modulo == 1) {
			list.add((int)Math.pow(2, pos));
		}
		
		if (quotient != 0) {
			list=getNext(list,quotient, ++pos);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(getCombination(type1|type7|type8));
	}
}
