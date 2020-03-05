import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페_박준수 {
	static int map[][], result, n, count, starti, startj;
	static boolean arrow[];
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = -1;
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					visit = new boolean[101];
					visit[map[i][j]] = true;
					arrow = new boolean[4];
					starti = i;
					startj = j;

					dfs(i, j, -1, 0);
				}
			}

			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	static int di[] = { 1, 1, -1, -1 };
	static int dj[] = { -1, 1, 1, -1 };

	private static void dfs(int i, int j, int dir, int cnt) {
		for (int k = 0; k < 4; k++) {
			if (arrow[k] || k == ((dir + 2) % 4)) {
				continue;
			}
			int ni = i + di[k];
			int nj = j + dj[k];
			if (ni == starti && nj == startj) {
				cnt++;
				if (result < cnt) {
					result = cnt;
				}
				return;
			}
			if (ni < 0 || ni >= n || nj < 0 || nj >= n || visit[map[ni][nj]]) {
				continue;
			}
			if (dir != -1 && dir != k) {
				arrow[dir] = true;
			}
			visit[map[ni][nj]] = true;
			dfs(ni, nj, k, cnt + 1);
			if (dir != -1 && dir != k) {
				arrow[dir] = false;
			}
			visit[map[ni][nj]] = false;
			if (i == starti && j == startj) {
				Arrays.fill(arrow, false);
			}

		}
	}
}
