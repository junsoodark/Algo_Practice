package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1868_파핑파핑지뢰찾기_박준수 {
	static int N, ans;
	static char map[][];
	static boolean visit[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_1868.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '.') {
						continue;
					}
					if (count(i, j) == 0) {
						ans++;
						dfs(i, j);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						ans++;
					}
				}
			}

			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	private static void dfs(int i, int j) {
		visit[i][j] = true;
		int mine = count(i, j);
		map[i][j] = (char) (mine + '0');
		if (map[i][j] != '0') {
			return;
		}
		for (int d = 0; d < 8; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (0 <= ni && ni < N && 0 <= nj && nj < N && map[ni][nj] == '.' && !visit[ni][nj]) {
				dfs(ni, nj);
			}
		}
	}

	private static int count(int i, int j) {
		int mine = 0;
		for (int d = 0; d < 8; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (0 <= ni && ni < N && 0 <= nj && nj < N && map[ni][nj] == '*') {
				mine++;
			}
		}
		return mine;
	}

	static int di[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dj[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

}
