package com.test;

/**
 * https://algorithms.tutorialhorizon.com/dynamic-programming-longest-common-substring/
 * https://i2.wp.com/algorithms.tutorialhorizon.com/files/2015/06/Longest-Common-Substring-Matrix.png
 * 
 *
 */
public class LongestCommonSubString
{

	public static int find(char[] A, char[] B)
	{
		int[][] LCS = new int[A.length + 1][B.length + 1];
		int result = -1;

		// fill the rest of the matrix
		for (int i = 1; i <= A.length; i++)
		{
			for (int j = 1; j <= B.length; j++)
			{
				if (A[i - 1] == B[j - 1])
				{
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
					result = Math.max(result, LCS[i][j]);
				}
				else
				{
					LCS[i][j] = 0;
				}
			}
		}

		return result;
	}
	
	public static void main(String[] args)
	{
		String A = "tutorialshorizon";
		String B = "dynamictutorialsProgramming";
		System.out.println("LCS length : " + find(A.toCharArray(), B.toCharArray()));

		A = "Programming is";
		B = "Programming is fun";
		System.out.println("LCS length : " + find(A.toCharArray(), B.toCharArray()));
	}
}