package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17070_파이프옮기기1_박준수 {
	static int N, sum = 0;
	static int arr[][];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/input_B_17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move(0, 1, 0);
		System.out.println(sum);
	}

	private static void move(int i, int j, int dir) {
		if (i == N - 1 && j == N - 1) {
			sum++;
			return;
		}

		if (dir == 0) {
			int ni = i;
			int nj = j + 1;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0) {
					move(ni, nj, 0);
				}
			}
			ni = i + 1;
			nj = j + 1;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0 && arr[ni - 1][nj] == 0 && arr[ni][nj - 1] == 0) {
					move(ni, nj, 1);
				}
			}
		} else if (dir == 1) {
			int ni = i;
			int nj = j + 1;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0) {
					move(ni, nj, 0);
				}
			}
			ni = i + 1;
			nj = j;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0) {
					move(ni, nj, 2);
				}
			}
			ni = i + 1;
			nj = j + 1;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0 && arr[ni - 1][nj] == 0 && arr[ni][nj - 1] == 0) {
					move(ni, nj, 1);
				}
			}
		} else if (dir == 2) {
			int ni = i + 1;
			int nj = j;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0) {
					move(ni, nj, 2);
				}
			}
			ni = i + 1;
			nj = j + 1;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if (arr[ni][nj] == 0 && arr[ni - 1][nj] == 0 && arr[ni][nj - 1] == 0) {
					move(ni, nj, 1);
				}
			}
		}
	}
}
