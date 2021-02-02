//Kaylee Pham, Comp482 section# 17094 (5pm), Project #3

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project3 {
    private static Scanner scan;

    public static void main(String[] args) throws FileNotFoundException {
        // Open the file
        File input3 = new File("C:\\Users\\kayle\\Documents\\Java\\input3.text");

        scan = new Scanner(input3);
        int[] array0 = new int[50];
        int ele = 0;

        // Store all values into one array
        for (ele = 0; scan.hasNextInt(); ele++) {
            array0[ele] = scan.nextInt();
        }

        int ele2 = ele / 2;
        int[] array1 = new int[ele2];
        int[] array2 = new int[ele2];

        // Split into two equal arrays, arrays are sorted
        for (int i = 0, j = ele2; i < ele2; i++, j++) {
            array1[i] = array0[i];
            array2[i] = array0[j];
        }

        // find and printout median of two arrays
        Project3 p = new Project3();
        float median = p.findMedian(array1, 0, ele2 - 1, array2, 0, ele2 - 1);
        System.out.println(median);

        // counting inversions of two arrays merge into one
        int mark = ele2, count = 0;
        int a = 0, k = 0;
        for (int i = 0; i < (ele2 + 2); i++) {
            if (array1[a] > array2[k]) {
                count = count + mark;
                k++;
            } else if (array1[a] < array2[k]) {
                mark--;
                a++;
            }
        }
        System.out.println(count);
        // Close the scanner object
        scan.close();
    }

    // findMedian function
    public float findMedian(int[] array1, int begin1, int end1, int[] array2, int begin2, int end2) {
        // calculate median for arrays size 2
        if (end1 - begin1 + 1 == 2 && end2 - begin2 + 1 == 2) {
            float m1 = Math.max(array1[begin1], array2[begin2]);
            float m2 = Math.min(array1[end1], array2[end2]);
            return (m1 + m2) / 2;
        }

        // calculate median for arrays size > 2
        float median1 = OddOrEven(array1, begin1, end1);
        float median2 = OddOrEven(array2, begin2, end2);

        int mid1 = (begin1 + end1) / 2;
        int mid2 = (begin2 + end2) / 2;

        if (median1 > median2) {
            return findMedian(array1, begin1, mid1, array2, mid2, end2);
        } else {
            return findMedian(array1, mid1, end1, array2, begin2, mid2);
        }

    }

    // Return median for array size odd or even
    public float OddOrEven(int[] array, int begin, int end) {
        int size = end - begin + 1;
        if (size % 2 == 0) {
            float m1 = array[begin + (size / 2)];
            float m2 = array[begin + (size - 1) / 2];
            return (m1 + m2) / 2;
        } else {
            return array[begin + (size - 1) / 2];
        }
    }

}