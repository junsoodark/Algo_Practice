package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B16234_인구이동_박준수 {
	static int N, L, R, sum, count = -1, CNT;
	static int[][] arr;
	static boolean[][] visit;
	static int a[] = new int[2501];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		do {
			cnt = 0;
			count++;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					CNT = 0;
					sum = 0;
					if (!visit[i][j]) {
						dfs(i, j);
						int average = sum / CNT;
						for (int k = 0; k < CNT; k++) {
							arr[a[k] / 100][a[k] % 100] = average;
						}
						if (CNT > 1) {
							cnt++;
						}
					}

				}
			}

		} while (cnt != 0);
		System.out.println(count);
	}

	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };

	static void dfs(int i, int j) {
		a[CNT] = i * 100 + j;
		sum = sum + arr[i][j];
		CNT++;
		visit[i][j] = true;
		int nexti, nextj;
		for (int k = 0; k < 4; k++) {
			nexti = i + di[k];
			nextj = j + dj[k];
			if (nexti < 0 || nexti >= N || nextj < 0 || nextj >= N || visit[nexti][nextj]) {
				continue;
			}
			int tmp = abs(arr[i][j] - arr[nexti][nextj]);
			if (tmp >= L && tmp <= R) {
				dfs(nexti, nextj);
			}
		}
	}

	private static int abs(int i) {
		if (i > 0) {
			return i;
		}
		return -i;
	}

	public static class position {
		public int i, j;

		public position(int i, int j) {
			super();
			this.i = i;
			this.j = j;

		}

	}
}
