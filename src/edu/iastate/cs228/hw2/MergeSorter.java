package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 * 
 * @author Tim Kuehn
 * 
 */
/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter
{

	/**
	 *
	 * 
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts)
	{

		super(pts);
		super.algorithm = "merge sort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort()
	{
		long startTime = System.nanoTime();
		mergeSortRec(this.points);
		runTime = System.nanoTime() - startTime;

	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length < 2)
		{
			return;
		}
		int midpoint = pts.length / 2;
		Point[] left = new Point[midpoint];
		Point[] right = new Point[pts.length - midpoint];
		for (int i = 0; i < midpoint; i++)
		{
			left[i] = pts[i];
		}
		for (int i = midpoint; i < pts.length; i++)
		{
			right[i - midpoint] = pts[i];
		}
		mergeSortRec(left);
		mergeSortRec(right);
		merge(pts, left, right);

	}
/**
 * 
 * @param pts original Point[] array
 * @param left is left half that is sorted
 * @param right is right half that is sorted
 * Combines the arrays in a sorted manner for the mergesort method
 */
	private void merge(Point[] pts, Point[] left, Point[] right)
	{
		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length)
		{
			if (pointComparator.compare(left[i], right[j]) < 0)
			{
				pts[k] = left[i];
				i++;
			}
			else
			{
				pts[k] = right[j];
				j++;
			}
			k++;

		}
		while (i < left.length)
		{
			pts[k] = left[i];
			i++;
			k++;
		}
		while (j < right.length)
		{
			pts[k] = right[j];
			j++;
			k++;
		}
	}

}
