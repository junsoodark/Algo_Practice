package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B9251_LCS_박준수 {
	static BufferedReader br;
	static char[] arr1, arr2;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/input_B_9251.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		arr1 = br.readLine().toCharArray();
		arr2 = br.readLine().toCharArray();
		arr = new int[arr2.length + 1][arr1.length + 1];
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr1.length; j++) {
				if (arr1[j] == arr2[i]) {
					arr[i + 1][j + 1] = arr[i][j] + 1;
				} else {
					arr[i + 1][j + 1] = Math.max(arr[i + 1][j], arr[i][j + 1]);
				}
			}
		}
		System.out.println(arr[arr2.length][arr1.length]);
	}
}
