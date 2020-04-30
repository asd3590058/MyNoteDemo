package com.example.mydemo;

/**
 * 作者：Created by 武泓吉 on 2020/4/26.
 */
public class ArrayObjec {

	int a;
	private ArrayObjec objec;

	public ArrayObjec() {
	}

	public ArrayObjec(int a) {
		this.a = a;
	}



	/**
	 * 一个数组内找出两个和为target的下标
	 * @param a
	 * @param target
	 * @return
	 */
	public int[] ArraySolution(int [] a, int target){
		for (int i=0;i<a.length-1;i++) {
			for (int j=i+1;j<a.length;j++) {
				if (a[i] + a[j] == target) {
					return new int[]{i,j};
				}
			}
		}
		throw new IllegalArgumentException("发生异常！！！");
	}


	/**
	 * 冒泡排序从小到大
	 * @param a
	 * @return
	 */
	public  int [] Bubble(int [] a){
		for (int i=0;i<a.length-1;i++) {
 			for (int j=0;j<a.length-1-i;j++) {
				if (a[j]>a[j+1]) {
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		return a;

	}


	public ArrayObjec reverseList(ArrayObjec head){
		String aaa= "aaa";
		ArrayObjec next=null;
		ArrayObjec pre=null;
		if (head==null||head.objec==null) {
			return head;
		}
		while (head!=null){
			pre=head.objec;
			head.objec=next;
			next=head;
			head=pre;
		}
		return next;
	}
}
