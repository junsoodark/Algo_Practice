package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_B2665_미로만들기_박준수 {
	static int N;
	static int arr[][], visit[][];
	static PriorityQueue<point> pq = new PriorityQueue<point>(new Comparator<point>() {

		@Override
		public int compare(point o1, point o2) {
			if (o1.cnt < o2.cnt) {
				return -1;
			} else if (o1.cnt == o2.cnt) {
				return 0;
			}
			return 1;
		}
	});

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_B_2665.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], 10000);
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		visit[0][0] = 0;
		pq.offer(new point(0, 0, 0));
		bfs();
		System.out.println(visit[N - 1][N - 1]);
	}

	static class point {
		int i;
		int j;
		int cnt;

		public point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

	}

	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };

	static void bfs() {
		while (!pq.isEmpty()) {
			point p = pq.poll();
			int i = p.i;
			int j = p.j;
			int cnt = p.cnt;
			for (int j2 = 0; j2 < 4; j2++) {
				int ni = i + di[j2];
				int nj = j + dj[j2];
				if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
					continue;
				}
				if (arr[ni][nj] == 0) {
					int tmp = cnt + 1;
					if (visit[ni][nj] > tmp) {
						visit[ni][nj] = tmp;
						pq.offer(new point(ni, nj, tmp));
					}
				} else {
					if (visit[ni][nj] > cnt) {
						visit[ni][nj] = cnt;
						pq.offer(new point(ni, nj, cnt));
					}
				}
			}
		}

	}
}
