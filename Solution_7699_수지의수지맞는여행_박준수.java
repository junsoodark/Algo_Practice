import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_7699_수지의수지맞는여행_박준수 {
	static int R, C, result, map[][], maxnum;
	static boolean alphabet[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			maxnum = 0;
			alphabet = new boolean[26];
			result = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = (int) str.charAt(j) - 65;
					if (!alphabet[map[i][j]]) {
						alphabet[map[i][j]] = true;
						maxnum++;
					}
				}
			}
			Arrays.fill(alphabet, false);
			alphabet[map[0][0]] = true;
			dfs(0, 0, 1);
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	static int di[] = { 0, 0, 1, -1 };
	static int dj[] = { -1, 1, 0, 0 };

	private static void dfs(int i, int j, int cnt) {
		if (result < cnt) {
			result = cnt;
		}
		if (result == maxnum) {
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nexti = i + di[k];
			int nextj = j + dj[k];
			if (nexti < 0 || nexti >= R || nextj < 0 || nextj >= C) {
				continue;
			}
			if (!alphabet[map[nexti][nextj]]) {
				alphabet[map[nexti][nextj]] = true;
				dfs(nexti, nextj, cnt + 1);
				alphabet[map[nexti][nextj]] = false;
			}
		}
	}
}