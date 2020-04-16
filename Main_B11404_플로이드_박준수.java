package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B11404_플로이드_박준수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_B_11404.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		final int INF = 10000000;
		int n = Integer.parseInt(br.readLine());
		int[][] d = new int[n + 1][n + 1];
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d.length; j++) {
				if (i != j) {
					d[i][j] = INF;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (d[a][b] > c) {
				d[a][b] = c;
			}
		}

		for (int k = 0; k < d.length; k++) {
			for (int i = 0; i < d.length; i++) {
				if (k == i) {
					continue;
				}
				for (int j = 0; j < d.length; j++) {
					if (k == j || i == j) {
						continue;
					}
					if (d[i][j] > d[i][k] + d[k][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}
		for (int i = 1; i < d.length; i++) {
			for (int j = 1; j < d.length; j++) {
				if (d[i][j] >= INF) {
					d[i][j] = 0;
				}
				sb.append(d[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
