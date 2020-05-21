package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B9376_탈옥_박준수 {
	static char[][] arr;
	// visit을 int 형으로 선언하여 죄수 1은 1 죄수 2는 2 상근이는 4로 처리하여 재방문을 막고 10이상의 자리에 열었던 문의 갯수
	// 저장
	static int[][] visit;
	static int di[] = { -1, 0, 1, 0 }, dj[] = { 0, 1, 0, -1 };
	static Queue<Integer> q1[] = new Queue[2];
	static Queue<Integer> q2[] = new Queue[2];
	static Queue<Integer> q3[] = new Queue[2];
	static int M, N;
	static boolean b1;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_B_9376.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			cnt = 0;
			int i1, i2, j1, j2;
			b1 = false;
			// q1: 첫번째 죄수의 bfs 탐색을 위한 배열
			// 배열로 큐를 두개 선언한 이유는 벽을 만나면 벽을 따로 저장하여 나중에 돌리기 위해
			q1[0] = new LinkedList<Integer>();
			q1[1] = new LinkedList<Integer>();
			q2[0] = new LinkedList<Integer>();
			q2[1] = new LinkedList<Integer>();
			q3[0] = new LinkedList<Integer>();
			q3[1] = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			arr = new char[M + 2][N + 2];
			visit = new int[M + 2][N + 2];
			q3[0].offer(0);
			for (int j = 0; j <= M + 1; j++) {
				if (j == 0 || j == M + 1) {
					for (int k = 0; k <= N + 1; k++) {
						arr[j][k] = '.';
					}
				} else {
					String str = br.readLine();
					for (int k = 0; k <= N + 1; k++) {
						if (k == 0 || k == N + 1) {
							arr[j][k] = '.';
						} else {
							arr[j][k] = str.charAt(k - 1);
							// 죄수두명의 큐를 돌리기 위해 각각 큐에 저장
							if (arr[j][k] == '$') {
								if (cnt == 0) {
									i1 = j;
									j1 = k;
									q1[0].offer(i1 * 1000 + j1);
								} else {
									i2 = j;
									j2 = k;
									q2[0].offer(i2 * 1000 + j2);
								}
								cnt++;

							}
						}
					}
				}
			}
			// 죄수 두명과 상근이의 bfs 탐색
			cnt = -1;
			while (!b1) {
				cnt++;
				if (!b1) {
					if (cnt == 0) {
						int tmp = q1[0].peek();
						int a = tmp / 1000;
						int b = tmp % 1000;
						visit[a][b] = visit[a][b] + 1;
					}
					bfs1();
				}
				if (!b1) {
					if (cnt == 0) {
						int tmp = q2[0].peek();
						int a = tmp / 1000;
						int b = tmp % 1000;
						visit[a][b] = visit[a][b] + 2;
					}
					bfs2();
				}
				if (!b1) {
					bfs3();
				}
			}
		}
	}

	// 아래의 메소드는 모두가 만나는 부분인 7(1+2+4)이 생길때까지 탐색하는 메소드

	private static void bfs1() {
		while (!q1[cnt % 2].isEmpty()) {
			int tmp = q1[cnt % 2].poll();
			int i = tmp / 1000;
			int j = tmp % 1000;
			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];
				if ((ni < 0 || ni > M + 1 || nj < 0 || nj > N + 1)) {
					continue;
				}
				if (arr[ni][nj] == '*') {
					continue;
				}
				if (visit[ni][nj] % 10 == 1 || visit[ni][nj] % 10 == 3 || visit[ni][nj] % 10 == 5) {
					continue;
				}
				if (arr[ni][nj] == '.' || arr[ni][nj] == '$') {
					q1[cnt % 2].offer(ni * 1000 + nj);
					visit[ni][nj] = visit[ni][nj] + 1 + (cnt * 10);
				}
				if (arr[ni][nj] == '#') {
					q1[(cnt + 1) % 2].offer(ni * 1000 + nj);
					visit[ni][nj] = visit[ni][nj] + 1 + ((cnt + 1) * 10);
				}
				if (visit[ni][nj] % 10 == 7) {
					b1 = true;
					if (arr[ni][nj] == '#') {
						System.out.println((visit[ni][nj] / 10) - 2);
					} else {
						System.out.println(visit[ni][nj] / 10);
					}
					return;
				}
			}
		}
	}

	private static void bfs2() {
		while (!q2[cnt % 2].isEmpty()) {
			int tmp = q2[cnt % 2].poll();
			int i = tmp / 1000;
			int j = tmp % 1000;
			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];
				if ((ni < 0 || ni > M + 1 || nj < 0 || nj > N + 1)) {
					continue;
				}
				if (arr[ni][nj] == '*') {
					continue;
				}
				if (visit[ni][nj] % 10 == 2 || visit[ni][nj] % 10 == 3 || visit[ni][nj] % 10 == 6) {
					continue;
				}
				if (arr[ni][nj] == '.' || arr[ni][nj] == '$') {
					q2[cnt % 2].offer(ni * 1000 + nj);
					visit[ni][nj] = visit[ni][nj] + 2 + (cnt * 10);
				}
				if (arr[ni][nj] == '#') {
					q2[(cnt + 1) % 2].offer(ni * 1000 + nj);
					visit[ni][nj] = visit[ni][nj] + 2 + ((cnt + 1) * 10);
				}
				if (visit[ni][nj] % 10 == 7) {
					b1 = true;
					if (arr[ni][nj] == '#') {
						System.out.println((visit[ni][nj] / 10) - 2);
					} else {
						System.out.println(visit[ni][nj] / 10);
					}
					return;
				}
			}
		}
	}

	private static void bfs3() {
		while (!q3[cnt % 2].isEmpty()) {
			int tmp = q3[cnt % 2].poll();
			int i = tmp / 1000;
			int j = tmp % 1000;
			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];
				if ((ni < 0 || ni > M + 1 || nj < 0 || nj > N + 1)) {
					continue;
				}
				if (arr[ni][nj] == '*') {
					continue;
				}
				if (visit[ni][nj] % 10 == 4 || visit[ni][nj] % 10 == 5 || visit[ni][nj] % 10 == 6) {
					continue;
				}
				if (arr[ni][nj] == '.' || arr[ni][nj] == '$') {
					q3[cnt % 2].offer(ni * 1000 + nj);
					visit[ni][nj] = visit[ni][nj] + 4 + (cnt * 10);
				}
				if (arr[ni][nj] == '#') {
					q3[(cnt + 1) % 2].offer(ni * 1000 + nj);
					visit[ni][nj] = visit[ni][nj] + 4 + ((cnt + 1) * 10);
				}
				if (visit[ni][nj] % 10 == 7) {
					b1 = true;
					if (arr[ni][nj] == '#') {
						System.out.println((visit[ni][nj] / 10) - 2);
					} else {
						System.out.println(visit[ni][nj] / 10);
					}
					return;
				}
			}
		}
	}
}
