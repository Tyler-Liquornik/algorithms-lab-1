package LE6Q2;

import java.util.*;

public class Tyler_SortNameAndGrade
{
    // Header Method
    public static void myHeader(int labE_number, int q_number)
    {
        System.out.println("=======================================================");
        System.out.printf("Lab Exercise: %d-Q%d%n", labE_number, q_number);
        System.out.println("Prepared by: Tyler Liquornik");
        System.out.println("Student Number: 251271244");
        System.out.printf("Goal of this exercise: %s%n", "Sorting Students in various ways");
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
        // Call the header method
        myHeader(6, 2);

        // Three arrays with 8 first names, 8 last names, and 8 randomly
        // generated grades between 60 and 85 inclusive have been created below for the use
        String[] fnArray = {"Hermione", "Ron", "Harry", "Luna", "Ginny", "Draco", "Dean", "Fred"};
        String[] lnArray = {"Granger", "Weasley", "Potter", "Lovegood", "Weasley", "Malfoy", "Thomas", "Weasley"};
        Integer[] grd = {(int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26),
                (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26),
                (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26),
                (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26)};

        // Create a Vector<> class instance ‘sg’ with StudentGrade tag.
        Vector<StudentGrade> sg = new Vector<>();

        // Add each StudentGrade object to the Vector class with its add() method
        for (int i = 0; i < fnArray.length; i++)
        {
            sg.add(new StudentGrade(fnArray[i], lnArray[i], grd[i]));
        }

        // Print the unsorted ‘sg’ contents just by using toString() method
        System.out.println("The unsorted array:");
        for (StudentGrade student : sg)
        {
            System.out.println(student);
        }

        // Sort ‘sg’ using Collections’ sort method, which will sort the list based on the grade
        Collections.sort(sg);

        // Print the sorted content by using toString() method
        System.out.println("\nSorted by Grades:");
        for (StudentGrade student : sg)
        {
            System.out.println(student);
        }

        // Create an array of StudentGrade type with the length of the fnArray that has been created above.
        StudentGrade[] sgArray = new StudentGrade[fnArray.length];

        // With the help of Vector’s copyInto() method, copy ‘sg’ to StudentGrade array just created above
        sg.copyInto(sgArray);

        // Call the sort-method with the argument of StudentGrade array (created above) and key = 1 (first names).
        System.out.println("\nSorted by First Names:");
        sortStudentGradeArray(sgArray, 1);

        // Print the sorted array with the aid of the printArray() method
        printArray(sgArray);

        // Call the sort-method again with the argument of StudentGrade array (created above) and key = 2.
        sortStudentGradeArray(sgArray, 2);

        // Print the sorted array with the aid of the printArray() method
        System.out.println("\nSorted by Last Names:");
        printArray(sgArray);

        // Call the footer method here
        myFooter(6, 2);
    }

    // Sorting Method (chose merge sort)
    public static void sortStudentGradeArray(StudentGrade[] S, int key) {
        int n = S.length;
        if (n >= 2)
        {
            // divide
            int mid = n / 2;
            StudentGrade[] S1 = Arrays.copyOfRange(S, 0, mid); // copy of the first half
            StudentGrade[] S2 = Arrays.copyOfRange(S, mid, n); // copy of the second half

            // conquer (with recursion)
            sortStudentGradeArray(S1, key); // sort copy of the first half
            sortStudentGradeArray(S2, key); // sort copy of the second half

            // merge sorted halves back into the original based on the specified key
            int i = 0, j = 0;
            while (i + j < S.length)
            {
                if (j == S2.length || (i < S1.length && compareBy(S1[i], S2[j], key) < 0))
                    S[i + j] = S1[i++]; // copy ith element of S1 and increment i
                else
                    S[i + j] = S2[j++]; // copy jth element of S2 and increment j
            }
        }
    }

    // Helper method to compare StudentGrade objects based on the specified key
    private static int compareBy(StudentGrade s1, StudentGrade s2, int key)
    {
        return switch (key) {
            case 1 -> s1.getFirstName().compareTo(s2.getFirstName());
            case 2 -> s1.getLastName().compareTo(s2.getLastName());
            default -> throw new IllegalArgumentException("Invalid key: " + key);
        };
    }

    // Prints a formatted array of StudentGrade objects
    public static void printArray(StudentGrade[] array)
    {
        for (StudentGrade student : array)
        {
            System.out.println(student);
        }

        System.out.println(); // Add a newline for better formatting
    }
}

