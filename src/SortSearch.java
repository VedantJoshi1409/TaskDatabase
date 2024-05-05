/*
Class: SortSearch
Author: Vedant Joshi
Date: Jan 21 2023
School: AY Jackson SS
Purpose: The SortSearch class provides static methods for sorting and searching operations.
 */
import java.util.ArrayList;

public class SortSearch {

    /**
     * Sorts an array of Pair objects using the bubble sort algorithm.
     *
     * @param pairs The array of pairs to be sorted.
     */
    public static void bubbleSort(Pair[] pairs) {
        // Flag to check if any swaps are made in a pass
        boolean swap = true;

        // Outer loop for each pass
        for (int i = pairs.length - 1; i > 0 && swap; i--) {
            swap = false;

            // Inner loop for comparisons and swaps
            for (int j = 0; j < i; j++) {
                // Comparing and swapping if needed
                if (pairs[j].compareTo(pairs[j + 1])) {
                    swap = true;
                    Pair temp = pairs[j];
                    pairs[j] = pairs[j + 1];
                    pairs[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts an array of Pair objects using the selection sort algorithm.
     *
     * @param pairs The array of pairs to be sorted.
     */
    public static void selectionSort(Pair[] pairs) {
        int minIndex;

        // Outer loop for each pass
        for (int i = 0; i < pairs.length; i++) {
            minIndex = i;

            // Inner loop for finding the minimum element
            for (int j = i; j < pairs.length; j++) {
                // Updating minIndex if a smaller element is found
                if (pairs[minIndex].compareTo(pairs[j])) {
                    minIndex = j;
                }
            }

            // Swapping if the minimum element is not at the initial position
            if (minIndex != i) {
                Pair temp = pairs[minIndex];
                pairs[minIndex] = pairs[i];
                pairs[i] = temp;
            }
        }
    }

    /**
     * Performs a binary search on an ArrayList of persons based on their IDs.
     *
     * @param persons The ArrayList of persons to be searched.
     * @param value   The ID value to be searched.
     * @return The person object with the specified ID, or null if not found.
     */
    public static Person binarySearch(ArrayList<Person> persons, int value) {
        return binarySearch(persons, value, 0, persons.size() - 1);
    }

    /**
     * Recursive helper method for binary search on an ArrayList of persons.
     *
     * @param persons The ArrayList of persons to be searched.
     * @param value   The ID value to be searched.
     * @param start   The starting index for the search.
     * @param end     The ending index for the search.
     * @return The person object with the specified ID, or null if not found.
     */
    private static Person binarySearch(ArrayList<Person> persons, int value, int start, int end) {
        // Base case: start index is greater than end index
        if (start > end) {
            return null;
        }

        // Calculate middle index
        int middle = (start + end) / 2;

        // Adjusting start and end based on the comparison
        if (persons.get(middle).getId() > value) {
            end = middle - 1;
        } else if (persons.get(middle).getId() < value) {
            start = middle + 1;
        } else {
            // Found the person with the specified ID
            return persons.get(middle);
        }

        // Recursive call with adjusted start and end
        return binarySearch(persons, value, start, end);
    }
}
