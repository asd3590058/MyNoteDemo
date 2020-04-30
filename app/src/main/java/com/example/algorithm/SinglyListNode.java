package com.example.algorithm;

/**
 * 作者：Created by 武泓吉 on 2020/4/28.
 * 单链表相关算法
 */
public class SinglyListNode {



	/***
	 * 单链表反转
	 */
	public static class  ListNode{
		public int value;
		public ListNode next=null;

		public ListNode(int value) {
			this.value = value;
		}
	}

	public static ListNode revereNode(ListNode head){
		ListNode tem = null;
		ListNode next=null;

		if (head==null||head.next==null) {
			return head;
		}

		while (head!=null){
			tem =head.next;
			head.next=next;
			next=head;
			head=tem;
		}
		return tem;
	}

}
