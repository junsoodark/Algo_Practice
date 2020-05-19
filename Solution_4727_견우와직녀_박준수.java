package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4727_견우와직녀_박준수 {
	static int arr[][], result[][];
	static int N, M;
	static int max = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_4727.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = new int[N][N];
			boolean visit[][] = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(result[i], max);
			}
			boolean cloud = true;
			int dir = 4;
			bfs(0, 0, visit, 0, cloud, dir);
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(result[i]));
//			}
			sb.append('#').append(t).append(' ').append(result[N - 1][N - 1]).append('\n');
		}
		System.out.println(sb);
	}

	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };

	private static void bfs(int i, int j, boolean[][] visit, int time, boolean cloud, int dir) {
		visit[i][j] = true;
		for (int k = 0; k < 4; k++) {
			if (dir != 4) {
				if (dir != k) {
					continue;
				}
			}
			dir = 4;
			int ni = i + di[k];
			int nj = j + dj[k];
			if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
				continue;
			}
			if (!visit[ni][nj]) {
				if (arr[ni][nj] == 1) {
					if (result[ni][nj] > (time + 1)) {
						result[ni][nj] = time + 1;
						bfs(ni, nj, visit, time + 1, cloud, dir);
					}
				} else if (arr[i][j] == 1 && arr[ni][nj] > 1) {
					int tmp = 0;
					while ((time + 1) % arr[ni][nj] != 0) {
						time++;
						tmp++;
					}
					if (result[ni][nj] > (time + 1)) {
						result[ni][nj] = time + 1;
						bfs(ni, nj, visit, time + 1, cloud, dir);
					}
					time = time - tmp;
				} else if (arr[i][j] == 1 && arr[ni][nj] == 0 && cloud) {
					int tmp = 0;
					while ((time + 1) % M != 0) {
						time++;
						tmp++;
					}
					if (result[ni][nj] > (time + 1)) {
						result[ni][nj] = time + 1;
						cloud = false;
						dir = k;
						bfs(ni, nj, visit, time + 1, cloud, dir);
						dir = 4;
						cloud = true;
					}
					time = time - tmp;
				}
			}
		}
		visit[i][j] = false;

	}
}