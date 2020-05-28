package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거_박준수 {
	static int di[] = { -1, 0, 1, 0 }, dj[] = { 0, 1, 0, -1 };
	static int map[][] = new int[50][50], N, M, R, C, L, result;
	static boolean visit[][];
	static Queue<Integer> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			q = new LinkedList<Integer>();
			result = 1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			visit = new boolean[N][M];
			q.offer(R * 100 + C);
			visit[R][C] = true;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 1; i < L; i++) {
				bfs();
			}
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs() {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			int tmp = q.poll();
			int r = tmp / 100;
			int c = tmp % 100;
			int pot1 = map[r][c];
			for (int j = 0; j < 4; j++) {
				int ni = r + di[j];
				int nj = c + dj[j];

				// 해당 좌표에서 나가는 방향이 없을 경우 continue
				if (j == 0) {
					if (pot1 == 3 || pot1 == 5 || pot1 == 6) {
						continue;
					}
				} else if (j == 1) {
					if (pot1 == 2 || pot1 == 7 || pot1 == 6) {
						continue;
					}
				} else if (j == 2) {
					if (pot1 == 3 || pot1 == 4 || pot1 == 7) {
						continue;
					}
				} else if (j == 3) {
					if (pot1 == 2 || pot1 == 4 || pot1 == 5) {
						continue;
					}
				}
				if (ni < 0 || ni >= N || nj < 0 || nj >= M) {
					continue;
				}
				if (map[ni][nj] == 0 || visit[ni][nj]) {
					continue;
				}
				int pot2 = map[ni][nj];
				// 해당 좌표로 들어오는 방향이 없을 경우 continue
				if (j == 0) {
					if (pot2 == 3 || pot2 == 4 || pot2 == 7) {
						continue;
					}
				} else if (j == 1) {
					if (pot2 == 2 || pot2 == 4 || pot2 == 5) {
						continue;
					}
				} else if (j == 2) {
					if (pot2 == 3 || pot2 == 5 || pot2 == 6) {
						continue;
					}
				} else if (j == 3) {
					if (pot2 == 2 || pot2 == 7 || pot2 == 6) {
						continue;
					}
				}
				visit[ni][nj] = true;
				result++;
				q.offer(ni * 100 + nj);
			}
		}

	}
}
