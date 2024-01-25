package LE6Q1;

import java.util.*;

public class TylersTestingSortingMethods
{
    // Generic Bubble Sort
    public static <T extends Comparable<? super T>> long bubbleSort(T[] a)
    {
        long initialTime = System.nanoTime();

        for (int i = 1; i < a.length ; i++)
        {
            for (int j = 0; j < a.length - i; j++)
            {
                if (a[j].compareTo(a[j + 1]) > 0)
                {
                    swap(a, j, j+1);
                }
            }
        }
        return (System.nanoTime() - initialTime);
    }

    // Generic Selection Sort
    public static <T extends Comparable <? super T>> long selectionSort(T[] a)
    {
        long initialTime = System.nanoTime();

        // Loop from the beginning of the array to the second to last item
        // After selecting and sorting everything the last item will be sorted already
        for (int currentIndex = 0; currentIndex < a.length - 1; currentIndex++)
        {
            // Save a copy of the currentIndex as our smallest index
            int minIndex = currentIndex;

            // Loop through all indices that proceed the currentIndex
            for (int i = currentIndex + 1; i < a.length; i++)
            {
                // Check if the value is lower than the current minimum
                if (a[i].compareTo(a[minIndex]) < 0)
                    minIndex = i; // Update the min index
            }
            // If we have found a new lowest value, swap in into place
            if (minIndex != currentIndex)
                swap(a, currentIndex, minIndex);
        }

        return (System.nanoTime() - initialTime);
    }

    // Generic Bucket Sort with Helper Methods

    // Get the nth digit from the left (n >= 1)
    // ie. n = 1 : ones, n = 2 : tens, n = 3 : hundreds...
    private static int getDigit(int number, int digit)
    {
        return (number / (int) Math.pow(10, digit - 1)) % 10;
    }

    // Generic Bucket Sort
    public static long bucketSort(Integer[] a, int first, int last, int maxDigits)
    {
        long initialTime = System.nanoTime();

        for (int digit = 1; digit <= maxDigits; digit++)
        {
            Vector<Vector<Integer>> buckets = new Vector<>(10);

            for (int i = 0; i < 10; i++)
            {
                buckets.add(new Vector<>());
            }

            // Distribute elements into buckets based on the current digit
            for (int i = first; i <= last; i++)
            {
                int number = a[i];
                int digitValue = getDigit(number, digit);
                buckets.get(digitValue).add(number);
            }

            // Collect elements from the buckets
            int index = first;
            for (Vector<Integer> bucket : buckets)
            {
                for (int number : bucket)
                {
                    a[index++] = number;
                }
            }
        }

        return (System.nanoTime() - initialTime);
    }

    // Generic Merge Sort
    public static <T extends Comparable<? super T>> long mergeSort(T[] S) {
        long initialTime = System.nanoTime();

        int n = S.length;
        if (n >= 2)
        {
            // divide
            int mid = n / 2;
            T[] S1 = Arrays.copyOfRange(S, 0, mid); // copy of the first half
            T[] S2 = Arrays.copyOfRange(S, mid, n); // copy of the second half

            // conquer (with recursion)
            mergeSort(S1); // sort copy of the first half
            mergeSort(S2); // sort copy of the second half

            // merge sorted halves back into the original
            int i = 0, j = 0;
            while (i + j < S.length) {
                if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0))
                    S[i + j] = S1[i++]; // copy ith element of S1 and increment i
                else
                    S[i + j] = S2[j++]; // copy jth element of S2 and increment j
            }
        }

        return (System.nanoTime() - initialTime);
    }

    // Generic Insertion Sort
    public static <T extends Comparable <? super T>> long insertionSort(T[] a)
    {
        long initialTime = System.nanoTime();

        for (int i = 1; i < a.length; i++)
        {
            // Start from i, since the array is sorted before i
            int currentIndex = i;

            // Loop to insert the value at the current index. Stop when it is in the correct spot
            while (currentIndex > 0 && a[currentIndex - 1].compareTo(a[currentIndex]) > 0)
            {
                swap(a, currentIndex, currentIndex - 1); // Move the value back one
                currentIndex--; // Look back one more index
            }
        }

        return (System.nanoTime() - initialTime);
    }

    // Generic Quicksort with helper methods

    // Reorders two array elements in ascending order
    private static <T extends Comparable <? super T>> void order(T[] a, int i, int j)
    {
        if (a[i].compareTo(a[j]) > 0)
            swap (a, i, j);
    }

    // Sort the first, middle, and last elements of an array
    private static <T extends Comparable <? super T>>
    void sortFirstMiddleLast (T [] a, int first, int mid, int last)
    {
        order(a, first, mid); // make a[first] <= a[mid]
        order(a, mid, last); // make a[mid] <= a[last]
        order(a, first, mid); // make a[first] <= a[mid]
    }

    // Swaps two array elements (also used extensively throughout other sorting methods)
    private static <T> void swap(T[] array, int i, int j)
    {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Partition an array with a pivot, so that smaller objects type T are before the partition
    // and larger objects type T are after, returning the partition index
    private static <T extends Comparable <? super T>> int partition(T [] a, int first, int last)
    {
        // Get the median of first, middle, last, and move it to the second last index
        // Note: Last is already sorted after this, so we don't need to worry about it
        int mid = (first + last) / 2;
        sortFirstMiddleLast(a, first, mid, last);
        swap(a, mid, last - 1);
        int pivotIndex = last - 1;
        T pivot = a[pivotIndex];

        // Initial pointers
        int indexFromLeft = first + 1;
        int indexFromRight = last - 2;

        // Loop until fully partitioned
        boolean done = false;
        while (!done)
        {
            // Increment towards middle if less than pivot (correctly placed on LHS already)
            while (a[indexFromLeft].compareTo(pivot) < 0)
                indexFromLeft++;

            // Increment towards middle if greater than pivot (correctly placed on RHS already)
            while (a[indexFromRight].compareTo(pivot) > 0)
                indexFromRight--;

            // Assertion that the values the left and right indices point to are correctly placed
            assert a[indexFromLeft].compareTo(pivot) >= 0 && a[indexFromRight].compareTo(pivot) <= 0;

            // Execute a swap and move towards middle
            if (indexFromLeft < indexFromRight)
            {
                swap(a, indexFromLeft, indexFromRight);
                indexFromLeft++;
                indexFromRight--;
            }

            // When the left index has passed the right index, we've covered the whole array and are done
            else
                done = true;
        }

        // Place the pivot in the middle, and return its index value
        swap(a, pivotIndex, indexFromLeft);
        pivotIndex = indexFromLeft;
        return pivotIndex;
    }

    // Setting the size (somewhat arbitrary small number) that we switch to
    // insertion sort because it's more efficient for small arrays
    public static final int MIN_SIZE = 50;

    public static <T extends Comparable <? super T>> long quickSort(T [] s, int a, int b)
    {
        long initialTime = System.nanoTime();

        // For small arrays use insertion sort
        if (b - a + 1 < MIN_SIZE)
        {
            insertionSort(s);
        }
        else
        {
            // Create the Partition: Smaller | Pivot | Larger
            int pivotIndex = partition(s, a, b);

            // Conquer the LHS and RHS with recursion
            quickSort(s, a, pivotIndex - 1);
            quickSort(s, pivotIndex + 1, b);
        }

        return (System.nanoTime() - initialTime);
    }

    // Header Method
    public static void myHeader(int labE_number, int q_number)
    {
        System.out.println("=======================================================");
        System.out.printf("Lab Exercise: %d-Q%d%n", labE_number, q_number);
        System.out.println("Prepared by: Tyler Liquornik");
        System.out.println("Student Number: 251271244");
        System.out.printf("Goal of this exercise: %s%n", "Timing Sorting Algorithms");
        System.out.println("=======================================================");
    }

    // Footer Method
    public static void myFooter(int labE_number, int q_number)
    {
        System.out.println("=======================================================");
        System.out.printf("Completion of Lab Exercise %d-Q%d is successful!%n", labE_number, q_number);
        System.out.println("Signing off - Tyler");
        System.out.println("=======================================================");
    }

    public static void main(String[] args)
    {
        myHeader(6, 1);

        // Data array, and backup
        int sz = 50000;
        Integer[] numbers = new Integer[sz];
        Integer[] backup = new Integer[sz];

        // Random number from 13 to 93 inclusive
        for (int i = 0; i < sz; i++)
        {
            numbers[i] = (int) Math.floor(Math.random() * 81) + 13;
        }

        // Copy original array to backup array
        System.arraycopy(numbers, 0, backup, 0, sz);

        // Converting to a list to take advantage of toString()
        List<Integer> l = new ArrayList<>(Arrays.asList(numbers));

        // Prompt for user info
        System.out.printf("Testing execution time of different sorting methods for sorting %d random numbers: %n", sz);

        // Timing default Collections library sort (TimSort)
        // System.out.println("\nThe unsorted list: " + l);

        // Timing before and after sorting
        long initialTime = System.nanoTime();
        Collections.sort(l);
        double finalTime = ((double) (System.nanoTime() - initialTime)) / 1000000;

        // Print final output
        System.out.printf("Collections' Sorting Time: %.2f ms", finalTime);
        // System.out.printf("%nThe sorted list using Collections' sort method: %s%n", l);

        // Recopy
        System.arraycopy(backup, 0, numbers, 0, sz);


        // Timing selection sort
        // System.out.println("\nThe unsorted list: " + Arrays.toString(numbers));

        // Timing before and after sorting
        double finalTime0 = ((double) selectionSort(numbers)) / 1000000;

        // Print final output
        System.out.printf("\nMy Selection-Sort Time: %.2f ms", finalTime0);
        // System.out.printf("%nThe sorted list using selection-sort: %s%n", Arrays.toString(numbers));

        // Recopy
        System.arraycopy(backup, 0, numbers, 0, sz);


        // Timing bubble sort
        // System.out.println("\nThe unsorted list: " + Arrays.toString(numbers));

        // Timing before and after sorting
        double finalTime1 = ((double) bubbleSort(numbers)) / 1000000;

        // Print final output
        System.out.printf("\nMy Bubble-Sort Time: %.2f ms", finalTime1);
        // System.out.printf("%nThe sorted list using bubble-sort: %s%n", Arrays.toString(numbers));

        // Recopy
        System.arraycopy(backup, 0, numbers, 0, sz);



        // Timing merge sort
        // System.out.println("\nThe unsorted list: " + Arrays.toString(numbers));

        // Timing before and after sorting
        double finalTime3 = ((double) mergeSort(numbers)) / 1000000;

        // Print final output
        System.out.printf("\nMy Merge-Sort Time: %.2f ms", finalTime3);
        // System.out.printf("%nThe sorted list using merge-sort: %s%n", Arrays.toString(numbers));

        // Recopy
        System.arraycopy(backup, 0, numbers, 0, sz);


        // Timing quick sort
        // System.out.println("\nThe unsorted list: " + Arrays.toString(numbers));

        // Timing before and after sorting
        double finalTime4 = ((double) quickSort(numbers, 0, sz - 1)) / 1000000;

        // Print final output
        System.out.printf("\nMy Quick-Sort Time: %.2f ms", finalTime4);
        // System.out.printf("%nThe sorted list using quick-sort: %s%n", Arrays.toString(numbers));

        // Recopy
        System.arraycopy(backup, 0, numbers, 0, sz);


        // Timing bucket sort
        // System.out.println("\nThe unsorted list: " + Arrays.toString(numbers));

        // Timing before and after sorting
        double finalTime5 = ((double) bucketSort(numbers, 0, sz - 1, 2)) / 1000000;

        // Print final output
        System.out.printf("\nMy Bucket-Sort Time: %.2f ms", finalTime5);
        // System.out.printf("%nThe sorted list using bucket-sort: %s%n", Arrays.toString(numbers));

        // Recopy
        System.arraycopy(backup, 0, numbers, 0, sz);

        System.out.println();
        myFooter(6, 1);
    }
}
