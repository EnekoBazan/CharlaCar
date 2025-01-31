package domainLN;

import java.util.ArrayList;
import java.util.Comparator;

//FUENTE-EXTERNA
//URL: https://www.geeksforgeeks.org/merge-sort/?ref=gcse_outind
//SIN-CAMBIOS ó ADAPTADO (las modificaciones hechas son para que la ordenacin se pudiera dar en arrais de Objetos)

public class OrdenacionRecursiva<T extends Comparable<? super T>> {

	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	private void merge(ArrayList<T> arr, int l, int m, int r, Comparator<T> comp ) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		// Create temp arrays
		ArrayList<T> L = new ArrayList<>(n1);
		ArrayList<T> R = new ArrayList<>(n2);

		// Copy data to temp arrays
		for (int i = 0; i < n1; ++i)
			L.add(arr.get(l + i));
		for (int j = 0; j < n2; ++j)
			R.add(arr.get(m + 1 + j));

		// Merge the temp arrays

		// Initial indices of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2) {
			// if (L.get(i).compareTo( R.get(j) ) < 0 ) {
			if (comp.compare(L.get(i), R.get(j)) < 0) {

				arr.set(k, L.get(i));
				i++;
			} else {
				arr.set(k, R.get(j));
				j++;
			}
			k++;
		}

		// Copy remaining elements of L[] if any
		while (i < n1) {
			arr.set(k, L.get(i));
			i++;
			k++;
		}

		// Copy remaining elements of R[] if any
		while (j < n2) {
			arr.set(k, R.get(j));
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	public void sort(ArrayList<T> arr, int l, int r, Comparator<T> comp) {
		if (l < r) {

			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(arr, l, m, comp);
			sort(arr, m + 1, r, comp);

			// Merge the sorted halves
			merge(arr, l, m, r, comp );
		}
	}

	// A utility function to print array of size n
	public void printArray(ArrayList<T> arr) {
		int n = arr.size();
		for (int i = 0; i < n; ++i)
			System.out.print(arr.get(i) + " ");
		System.out.println();
	}

}
