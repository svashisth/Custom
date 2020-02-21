package com.test;

import java.util.Arrays;

public class Test2
{

	public static void main(String[] args)
	{
		/*
		int lengthOfLongestSubstring = lengthOfLongestSubstring("abcabcbb");
		System.out.println("main\t" + lengthOfLongestSubstring);

		lengthOfLongestSubstring = lengthOfLongestSubstring("bbbbb");
		System.out.println("main\t" + lengthOfLongestSubstring);

		lengthOfLongestSubstring = lengthOfLongestSubstring("pwwkew");
		System.out.println("main\t" + lengthOfLongestSubstring);

		lengthOfLongestSubstring = lengthOfLongestSubstring("aab");
		System.out.println("main\t" + lengthOfLongestSubstring);

		lengthOfLongestSubstring = lengthOfLongestSubstring("dvdf");
		System.out.println("main\t" + lengthOfLongestSubstring);

		lengthOfLongestSubstring = lengthOfLongestSubstring("aabaab!bb");
		System.out.println("main\t" + lengthOfLongestSubstring);
		 */
		
		int[] nums1 = new int[] { 1, 2 };
		int[] nums2 = new int[] { 3 };
		double median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 2.0);

		nums1 = new int[] { 1, 4 };
		nums2 = new int[] { 3 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 3.0);

		nums1 = new int[] { 2, 4 };
		nums2 = new int[] { 1 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 2.0);

		nums1 = new int[] { 1, 2 };
		nums2 = new int[] { 3, 5 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 2.5);

		nums1 = new int[] { 1, 2, 3 };
		nums2 = new int[] { 3, 5, 7 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 3.0);

		nums1 = new int[] { 5, 9, 11 };
		nums2 = new int[] { 1, 4 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 5.0);

		nums1 = new int[] { 2, 3, 4 };
		nums2 = new int[] { 1 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 2.5);

		nums1 = new int[] { 4 };
		nums2 = new int[] { 2, 3, 4 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 3.5);

		nums1 = new int[] { 4 };
		nums2 = new int[] { 1, 2, 3 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 2.5);

		nums1 = new int[] { 1 };
		nums2 = new int[] { 2, 3, 4, 5 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 3.0);

		nums1 = new int[] { 2, 3, 4, 5 };
		nums2 = new int[] { 1 };
		median = findMedianSortedArrays(nums1, nums2);
		System.out.println("main\t" + median + " expected : " + 3.0);

		}

	/**
	 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s)
	{
		StringBuilder built = new StringBuilder();
		int max = 0;
		for (char c : s.toCharArray())
		{
			String string = String.valueOf(c);
			if (built.toString().contains(string))
			{
				max = Math.max(max, built.length());
				built = new StringBuilder(built.substring(built.indexOf(string) + 1, built.length()));
			}
			built.append(string);
		}

		return Math.max(max, built.length());
	}

	/**
	 * https://leetcode.com/problems/median-of-two-sorted-arrays/
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2)
	{
		int arr1length = nums1.length;
		if (arr1length == 0)
		{
			return getArrayMedian(nums2, true);
		}
		int arr2length = nums2.length;
		if (arr2length == 0)
		{
			return getArrayMedian(nums1, true);
		}

		if (nums1[arr1length - 1] < nums2[0])
		{
			if ((arr1length + arr2length) % 2 == 0)
			{
				if ((arr1length + arr2length) / 2 > arr1length || (arr1length + arr2length) / 2 > arr2length)
				{
					return mergeAndSort(nums1, nums2, true);
				}
				return (double) (nums1[arr1length - 1] + nums2[0]) / 2;
			}
			else
			{
				if ((arr1length + arr2length) / 2 > arr1length || (arr1length + arr2length) / 2 > arr2length)
				{
					return mergeAndSort(nums1, nums2, true);
				}
				if (arr1length > arr2length)
				{
					return nums1[arr1length - 1];
				}
				else if (arr1length < arr2length)
				{
					return nums2[0];
				}
			}
		}
		else if (nums2[arr2length - 1] < nums1[0])
		{
			if ((arr1length + arr2length) % 2 == 0)
			{
				if ((arr1length + arr2length) / 2 > arr1length || (arr1length + arr2length) / 2 > arr2length)
				{
					return mergeAndSort(nums2, nums1, true);
				}
				return (double) (nums2[arr2length - 1] + nums1[0]) / 2;
			}
			else
			{
				if ((arr1length + arr2length) / 2 > arr1length || (arr1length + arr2length) / 2 > arr2length)
				{
					return mergeAndSort(nums2, nums1, true);
				}
				if (arr2length > arr1length)
				{
					return nums2[arr2length - 1];
				}
				else if (arr2length < arr1length)
				{
					return nums1[0];
				}
			}
		}
		int[] result = Arrays.copyOf(nums1, arr1length + arr2length);
		System.arraycopy(nums2, 0, result, arr1length, arr2length);

		return getArrayMedian(result, false);
	}

	private static double mergeAndSort(int[] nums1, int[] nums2, boolean isSorted)
	{
		int[] result = Arrays.copyOf(nums1, nums1.length + nums2.length);
		System.arraycopy(nums2, 0, result, nums1.length, nums2.length);

		return getArrayMedian(result, isSorted);
	}

	private static double getArrayMedian(int[] nums, boolean isSorted)
	{
		if (isSorted)
		{
			return getMedian(nums);
		}
		Arrays.sort(nums);

		return getMedian(nums);
	}

	private static double getMedian(int[] nums)
	{
		int arrLength = nums.length;
		if (arrLength % 2 == 0)
		{
			return (double) (nums[(arrLength / 2) - 1] + nums[(arrLength / 2)]) / 2;
		}
		else
		{
			return nums[(arrLength / 2)];
		}
	}
}
