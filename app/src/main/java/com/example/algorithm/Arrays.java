package com.example.algorithm;

/**
 * 作者：Created by 武泓吉 on 2020/4/28.
 * 数组相关
 */
public class Arrays {


	/**
	 * 在一个数组中给定一个目标值，数组中两个数相加得到目标值返回数组下标
	 */

	public int[] getIndex(int [] a,int target){
		for (int i=0;i<a.length;i++) {
			for (int j=i+1;j<a.length;j++) {
				if (a[i]+a[j]==target) {
					return new int[]{i,j};
				}
			}
		}
		return null;
	}



}
