import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페_박준수_최적화 {
	static int map[][], result, n, count, starti, startj, arrowNum[];
	static boolean arrow[];
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		arrowNum = new int[4];
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
					dfs(i, j, 0, 0);
				}
			}
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	static int di[] = { 1, 1, -1, -1 };
	static int dj[] = { -1, 1, 1, -1 };

	private static void dfs(int i, int j, int dir, int cnt) {
		if (dir > 2 && (result / 2) > cnt) {
			return;
		}
		if (arrowNum[0] != 0 && arrowNum[2] > arrowNum[0]) {
			return;
		}
		for (int k = dir; k < dir + 2 && k < 4; k++) {
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
			visit[map[ni][nj]] = true;
			arrowNum[k]++;
			dfs(ni, nj, k, cnt + 1);
			arrowNum[k]--;
			visit[map[ni][nj]] = false;
		}
	}
}
