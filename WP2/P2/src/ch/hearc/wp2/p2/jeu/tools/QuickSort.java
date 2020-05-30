package ch.hearc.wp2.p2.jeu.tools;

public class QuickSort {
	private int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				int temp = arr[++i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[++i];
		arr[i] = arr[high];
		arr[high] = temp;

		return i;
	}

	private void sort(int arr[], int low, int high) {
		if (low < high) {
			int pivot = partition(arr, low, high);
			sort(arr, low, pivot - 1);
			sort(arr, pivot + 1, high);
		}
	}

	public static int[] useSort(int arr[]) {
		QuickSort ob = new QuickSort();
		ob.sort(arr, 0, arr.length - 1);
		return arr;
	}

}
