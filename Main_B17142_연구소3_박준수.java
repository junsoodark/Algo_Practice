package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B17142_연구소3_박준수 {
	static int N, M;
	static int arr[][], cnt = 0, pick[];
	static int virus[][];
	static int vac = 0;
	static int time = Integer.MAX_VALUE;
	static boolean visit[][];
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/input_B_17142.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					cnt++;
				} else if (arr[i][j] == 0) {
					vac++;
				}
			}
		}
		if (vac != 0) {
			pick = new int[cnt];
			virus = new int[cnt][2];
			int now = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 2) {
						virus[now][0] = i;
						virus[now][1] = j;
						now++;
					}
				}
			}
			for (int i = 0; i < M; i++) {
				pick[cnt - 1 - i] = 1;
			}
			// 조합
			do {
//		pick[0] = 1;
//		pick[1] = 1;
//		pick[2] = 1;
//		pick[3] = 0;
//		pick[4] = 0;
				visit = new boolean[N][N];
				for (int i = 0; i < cnt; i++) {
					if (pick[i] == 1) {
//				System.out.println(virus[i][0]);
//				System.out.println(virus[i][1]);
						q.offer(virus[i][0] * 100 + virus[i][1]);
						visit[virus[i][0]][virus[i][1]] = true;
					}
				}
				int tmp = vac;
				bfs(tmp);
			} while (nextPermutation(pick));
		}
		if (time == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(time);
		}
	}

	static int di[] = { 1, 0, -1, 0 };
	static int dj[] = { 0, 1, 0, -1 };

	private static void bfs(int tmp) {
		int timeTmp = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			timeTmp++;
//			System.out.println(timeTmp + "timeTmp");
//			System.out.println(tmp + "tmp");
			for (int i = 0; i < size; i++) {
				int pos = q.poll();
				for (int j = 0; j < 4; j++) {
					int ni = pos / 100 + di[j];
					int nj = pos % 100 + dj[j];
					if (ni < 0 || nj < 0 || ni >= N || nj >= N || arr[ni][nj] == 1 || visit[ni][nj]) {
						continue;
					} else {
						if (arr[ni][nj] == 2) {
							q.offer(ni * 100 + nj);
							visit[ni][nj] = true;
						} else {
							q.offer(ni * 100 + nj);
							visit[ni][nj] = true;
							tmp--;
						}
					}
				}
			}

			if (timeTmp >= time) {
				while (!q.isEmpty()) {
					q.poll();
				}
				return;
			}
			if (tmp == 0) {
				break;
			}
		}
		while (!q.isEmpty()) {
			q.poll();
		}
		if (tmp == 0 && time > timeTmp) {
			time = timeTmp;
		}
	}

	public static boolean nextPermutation(int p[]) {
		int n = p.length;
		int i = n - 1;
		while (i > 0 && p[i - 1] >= p[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}

		int j = n - 1;
		while (p[i - 1] >= p[j]) {
			--j;
		}
		int tmp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = tmp;

		int k = n - 1;
		while (k > i) {
			tmp = p[k];
			p[k] = p[i];
			p[i] = tmp;
			k--;
			i++;
		}

		return true;
	}

}
