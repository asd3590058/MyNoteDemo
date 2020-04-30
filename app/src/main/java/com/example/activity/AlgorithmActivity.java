package com.example.activity;

import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.example.algorithm.SinglyListNode;
import com.example.algorithm.SortAlgorithm;
import com.example.mydemo.R;

import androidx.appcompat.app.AppCompatActivity;

/***
 * 算法页面
 */
public class AlgorithmActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_algorithm);
		SinglyListNodeReverse();//单链表反转
		bubbleSort();//冒泡排序
	}

	private void bubbleSort() {
		int []a=new int[]{9,1,8,2,7,3,6,4,5};
		SortAlgorithm sortAlgorithm=new SortAlgorithm();
		sortAlgorithm.bubbleSort(a);
		LogUtils.i(a);
	}


	/****
	 * 单链表反转  遍历
	 */
	private void SinglyListNodeReverse() {
		SinglyListNode.ListNode node1=new SinglyListNode.ListNode(1);
		SinglyListNode.ListNode node2=new SinglyListNode.ListNode(2);
		SinglyListNode.ListNode node3=new SinglyListNode.ListNode(3);
		SinglyListNode.ListNode node4=new SinglyListNode.ListNode(4);
		SinglyListNode.ListNode node5=new SinglyListNode.ListNode(5);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;

		SinglyListNode.ListNode reverseNode = SinglyListNode.revereNode(node1);
		while (node5 != null){
			LogUtils.i(node5.value);
			node5 = node5.next;
		}
	}

	/**
	 * 冒泡排序
	 */
}
