package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B2468_안전영역_박준수 {
	static int N, map[][], mapC[][], max = Integer.MIN_VALUE;
	static boolean visit[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_B_2468.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 100; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				Arrays.fill(visit[j], false);
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (map[j][k] <= i) {
						visit[j][k] = true;
					}
				}
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (!visit[j][k]) {
						dfs(j, k);
						cnt++;
					}
				}
			}
			if (max < cnt) {
				max = cnt;
			}
		}
		if (max < 0) {
			System.out.println(1);
		} else {
			System.out.println(max);
		}
	}

	static int di[] = { 0, 0, 1, -1 };
	static int dj[] = { 1, -1, 0, 0 };

	public static void dfs(int i, int j) {
		for (int j2 = 0; j2 < di.length; j2++) {
			int ni = i + di[j2];
			int nj = j + dj[j2];
			if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
				continue;
			}
			if (!visit[ni][nj]) {
				visit[ni][nj] = true;
				dfs(ni, nj);
			}
		}
	}
}
