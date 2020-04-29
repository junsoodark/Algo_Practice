package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6109_추억의2048게임_박준수 {
	static int[][] map, result;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_6109.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append('\n');
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			map = new int[N][N];
			result = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			delZero(dir);
			doSum(dir);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

	private static void delZero(String dir) {
		switch (dir) {
		case "up":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; k < N; j++) {
					if (j >= N) {
						map[k][i] = 0;
						k++;
					} else if (map[j][i] == 0) {
						continue;
					} else {
						map[k][i] = map[j][i];
						k++;
					}
				}
			}
			break;
		case "down":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; k < N; j++) {
					if (j >= N) {
						map[N - 1 - k][i] = 0;
						k++;
					} else if (map[N - 1 - j][i] == 0) {
						continue;
					} else {
						map[N - 1 - k][i] = map[N - 1 - j][i];
						k++;
					}
				}
			}
			break;
		case "left":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; k < N; j++) {
					if (j >= N) {
						map[i][k] = 0;
						k++;
					} else if (map[i][j] == 0) {
						continue;
					} else {
						map[i][k] = map[i][j];
						k++;
					}
				}
			}
			break;
		case "right":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; k < N; j++) {
					if (j >= N) {
						map[i][N - 1 - k] = 0;
						k++;
					} else if (map[i][N - 1 - j] == 0) {
						continue;
					} else {
						map[i][N - 1 - k] = map[i][N - 1 - j];
						k++;
					}
				}
			}
			break;
		}
	}

	private static void doSum(String dir) {
		switch (dir) {
		case "up":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; j < N; j++) {
					if (j < N - 1) {
						if (map[j][i] == map[j + 1][i]) {
							result[k][i] = 2 * map[j][i];
							j++;
							k++;
						} else {
							result[k][i] = map[j][i];
							k++;
						}
					} else {
						result[k][i] = map[j][i];
					}
				}
			}
			break;
		case "down":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; j < N; j++) {
					if (j < N - 1) {
						if (map[N - 1 - j][i] == map[N - 1 - j - 1][i]) {
							result[N - 1 - k][i] = 2 * map[N - 1 - j][i];
							j++;
							k++;
						} else {
							result[N - 1 - k][i] = map[N - 1 - j][i];
							k++;
						}
					} else {
						result[N - 1 - k][i] = map[N - 1 - j][i];
					}
				}
			}
			break;
		case "left":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; j < N; j++) {
					if (j < N - 1) {
						if (map[i][j] == map[i][j + 1]) {
							result[i][k] = 2 * map[i][j];
							j++;
							k++;
						} else {
							result[i][k] = map[i][j];
							k++;
						}
					} else {
						result[i][k] = map[i][j];
					}
				}
			}
			break;
		case "right":
			for (int i = 0; i < N; i++) {
				for (int j = 0, k = 0; j < N; j++) {
					if (j < N - 1) {
						if (map[i][N - 1 - j] == map[i][N - 1 - j - 1]) {
							result[i][N - 1 - k] = 2 * map[i][N - 1 - j];
							j++;
							k++;
						} else {
							result[i][N - 1 - k] = map[i][N - 1 - j];
							k++;
						}
					} else {
						result[i][N - 1 - k] = map[i][N - 1 - j];
					}
				}
			}
			break;
		}
	}
}
