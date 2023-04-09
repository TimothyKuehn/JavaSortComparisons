package edu.iastate.cs228.hw2;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters
{
	/**
	 * 
	 * @author Tim Kuehn
	 * 
	 */
	
	/**
	 * 
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{
		Random rand = new Random();
		
		Scanner sc = new Scanner(System.in);
		
		int trialNumber = 1;
		int numPoints = 0;
		boolean runAgain = true;
		boolean validInput = true;
		PointScanner[] scanners = new PointScanner[4];
		
		while (runAgain)
		{
			System.out.print("keys: 1 (random integers) 2 (file input) 3 (exit) \nTrial " + trialNumber +": ");
			String userInput = sc.next();
			
			switch (userInput)
			{
			case "1":
				validInput = true;
				runAgain = true;
				System.out.print("Enter number of random points: ");
				numPoints = sc.nextInt();
				Point[] sort = generateRandomPoints(numPoints, rand);
				scanners[0] = new PointScanner(sort, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(sort, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(sort, Algorithm.MergeSort);
				scanners[3] = new PointScanner(sort, Algorithm.QuickSort);
				break;

			case "2":
				validInput = true;
				runAgain = true;
				System.out.print("Points from a file\nFile name: ");
				String filename = sc.next();
				scanners[0] = new PointScanner(filename, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(filename, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(filename, Algorithm.MergeSort);
				scanners[3] = new PointScanner(filename, Algorithm.QuickSort);
				break;
			case "3":
				runAgain = false;
				return;
			default:
				validInput = false;
				System.out.println("Input not accepted");
				break;
			}
			
			if(validInput)
			{
			for (int i = 0; i < scanners.length; i++)
			{
				scanners[i].scan();
			}
			System.out.println("algorithm\tsize time (ns)\r\n"+ "----------------------------------");
			for (int i = 0; i < scanners.length; i++)
			{
				System.out.println(scanners[i].stats());
				
			}
			System.out.println("----------------------------------\n");

			}
		}
		
		

		

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{
		if (numPts < 1)
		{
			throw new IllegalArgumentException();
		}
		Point[] returnArray = new Point[numPts];

		for (int i = 0; i < numPts; i++)
		{
			Point temp = new Point(rand.nextInt(101)-50, rand.nextInt(101)-50);
			returnArray[i] = temp;
		}
		return returnArray;

	}

}
