package two;

import java.util.Scanner;

public class Find {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String line = in.nextLine();
		int c = in.nextInt();

		in.close();
		String[] ar = line.split(" ");
		int len = ar.length;

		int[] array = new int[len];

		for (int i = 0; i < len; i++) {
			array[i] = Integer.parseInt(ar[i]);
		}
		sort(array);

		System.out.println(array[len - c]);

	}

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int lo, int hi) {

		if (hi <= lo) {
			return;
		}
		int index = findIndex(arr, lo, hi);

		sort(arr, lo, index - 1);
		sort(arr, index + 1, hi);
	}

	public static int findIndex(int[] arr, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int first = arr[lo];

		while (true) {
			while (Tools.less(first, arr[--j])) {
				if (j == lo) {
					break;
				}
			}
			while (Tools.less(arr[++i], first)) {
				if (i == hi) {
					break;
				}
			}

			if (i >= j) {
				break;
			}
			exch(arr, i, j);
		}
		exch(arr, lo, j);
		return j;
	}

	public static boolean less(int i, int j) {
		return i < j;
	}

	public static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
