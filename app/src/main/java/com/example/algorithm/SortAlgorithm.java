package com.example.algorithm;

/**
 * 作者：Created by 武泓吉 on 2020/4/28.
 */
public class SortAlgorithm {
	/**
	 * 冒泡排序 从小到大
	 */

	public int[] bubbleSort(int[] a){
		for (int i=0;i<a.length-1;i++) {
			for (int j=0;j<a.length-1-i;j++) {
				if (a[j]>a[j+1]) {
					int tem=a[j];
					a[j]=a[j+1];
					a[j+1]=tem;
				}
			}
		}
		return a;
	}
}
