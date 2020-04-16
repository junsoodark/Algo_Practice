package DP_0416;

import java.util.Arrays;

public class 플로이드워샬 {
	public static void main(String[] args) {
		final int m = Integer.MAX_VALUE;
		int[][] d = { { 0, m, 2, 3 }, { 4, 0, 1, 8 }, { 2, 5, 0, m }, { m, 9, 6, 0 } };
		for (int k = 0; k < d.length; k++) {
			for (int i = 0; i < d.length; i++) {
				if (k == i) {
					continue;
				}
				for (int j = 0; j < d.length; j++) {
					if (k == j || i == j) {
						continue;
					}
					if (d[i][k] != m && d[k][j] != m && d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}
		for (int i = 0; i < d.length; i++) {
			System.out.println(Arrays.toString(d[i]));
		}

	} // end of main
} // end of class
