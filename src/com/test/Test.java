package com.test;

import java.util.HashMap;
import java.util.Map;

public class Test
{

	public static void main(String[] args)
	{
		// int[] twoSum = twoSum(new int[] { 3, 2, 3 }, 6);
		// int[] twoSum = twoSumNew(new int[] { 1, 9, 10, -12, 3, 2, 7, 11, 15 }, 9);
		// System.out.println("[" + twoSum[0] + ", " + twoSum[1] + "]");

		// boolean happy = isHappy(1111111);
		// System.out.println("main\t" + happy);

		ListNode ln = createListFromStr("9");
		printList(ln);
		
		ListNode ln1 = createListFromStr("9999999991");
		printList(ln1);

		ListNode addTwoNumbers = addTwoNumbers(ln, ln1);
		printList(addTwoNumbers);
	}

	private static ListNode createListFromStr(String data)
	{
		ListNode ref = null;
		ListNode lst = null;
		String revStr = new StringBuffer(data).reverse().toString();
		for (char ch : revStr.toCharArray())
		{
			int val = Integer.parseInt(String.valueOf(ch));
			if (lst == null)
			{
				lst = new ListNode(val);
				lst.next = null;
				ref = lst;
			}
			else
			{
				ListNode tempLst = new ListNode(val);
				tempLst.next = null;
				lst.next = tempLst;
				lst = lst.next;
			}
		}

		return ref;
	}

	private static ListNode createList(int i)
	{
		ListNode ln = null;
		ListNode ref = null;
		while ((i / 10) > 0)
		{
			if (ln == null)
			{
				ln = new ListNode(i % 10);
				ln.next = null;
				ref = ln;
			}
			else
			{
				ListNode x = new ListNode(i % 10);
				x.next = null;
				ln.next = x;
				ln = ln.next;
			}
			i = i / 10;
		}
		ListNode x = new ListNode(i);
		x.next = null;
		if (ln != null)
		{
			ln.next = x;
			ln = ln.next;
		}
		else
		{
			ref = ln = x;
		}

		return ref;
	}

	private static void printList(ListNode list)
	{
		while (list.next != null)
		{
			System.out.print(list.val + " -> ");
			list = list.next;
		}
		System.out.print(list.val);
		System.out.println("");
	}

	public static int[] twoSum(int[] nums, int target)
	{
		int[] indexes = new int[2];

		for (int i = 0; i < nums.length; i++)
		{
			if ((i + 1) >= nums.length)
			{
				break;
			}
			for (int j = (i + 1); j < nums.length; j++)
			{
				if (nums[i] + nums[j] == target)
				{
					indexes = new int[2];
					indexes[0] = i;
					indexes[1] = j;

					break;
				}
			}
		}

		return indexes;
	}

	public static int[] twoSumNew(int[] nums, int target)
	{
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);

		for (int i = 0; i < nums.length; i++)
		{
			if (map.containsKey(target - nums[i]))
			{
				return new int[] { map.get(target - nums[i]), i };
			}
			map.put(nums[i], i);
		}

		return new int[2];
	}

	public static boolean isHappy(int n)
	{
		// Map<Integer, Integer> map = new HashMap<Integer, Integer>(9);
		if (n == 1)
		{
			return true;
		}
		if (n < 10)
		{
			n = n * n;
		}
		int sum = 0;
		while ((n / 10) > 0)
		{
			if (n <= 9)
			{
				break;
			}
			sum += (n % 10) * (n % 10);
			n = n / 10;
			if (n / 10 == 0)
			{
				sum += n * n;
				n = sum;
				if (sum > 9)
				{
					sum = 0;
				}
			}
		}
		if (sum == 1)
		{
			return true;
		}
		return false;
	}

	public static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x)
		{
			val = x;
		}
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		if (l1 == null && l2 == null)
		{
			return new ListNode(0);
		}

		if (l1 == null)
		{
			return l2;
		}
		else if (l2 == null)
		{
			return l1;
		}

		ListNode list = null;
		ListNode ref = null;
		boolean hasCarryOver = false;
		while (l1 != null || l2 != null)
		{
			if (list == null)
			{
				ListNodeResult sumNode = getSumNode(l1, l2, hasCarryOver);
				list = sumNode.result;
				hasCarryOver = sumNode.hasCarryOver;
				ref = list;
			}
			else
			{
				int sum = 0;
				ListNodeResult listT = getSumNode(l1, l2, hasCarryOver);
				list.next = listT.result;
				list = listT.result;
				hasCarryOver = listT.hasCarryOver;
			}

			if (l1 != null)
			{
				l1 = l1.next;
			}
			if (l2 != null)
			{
				l2 = l2.next;
			}
		}

		if (list != null && hasCarryOver)
		{
			ListNode co = new ListNode(1);
			list.next = co;
			co.next = null;
		}
		else if (list != null)
		{
			list.next = null;
		}

		return ref;
	}

	static class ListNodeResult
	{
		public ListNode result;
		public boolean hasCarryOver;
	}

	private static ListNodeResult getSumNode(ListNode l1, ListNode l2, boolean hasCarryOver)
	{
		ListNodeResult listNodeResult = new ListNodeResult();
		ListNode listT = null;
		int sum = 0;
		boolean hasCR = false;

		if (l1 != null && l2 != null)
		{
			sum = l1.val + l2.val;
		}
		else if (l1 == null)
		{
			sum = l2.val;
		}
		else
		{
			sum = l1.val;
		}
		if (hasCarryOver)
		{
			sum += 1;
		}

		if (sum > 9)
		{
			hasCR = true;
			listT = new ListNode(sum % 10);
		}
		else
		{
			listT = new ListNode(sum);
			hasCR = false;
		}
		listT.next = null;

		listNodeResult.result = listT;
		listNodeResult.hasCarryOver = hasCR;

		return listNodeResult;
	}
}
