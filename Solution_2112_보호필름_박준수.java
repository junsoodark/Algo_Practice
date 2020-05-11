package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름_박준수 {
	static int r, c, k, cnt;
	static boolean arr[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new boolean[r][c];
			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < c; j++) {
					if (st.nextToken().equals("1")) {
						arr[i][j] = true;
					}
				}
			}
			cnt = 100000;
			if (k == 1) {
				sb.append('#').append(t).append(' ').append(0).append('\n');
				continue;
			}
			if (isGood()) {
				sb.append('#').append(t).append(' ').append(0).append('\n');
				continue;
			}
			check(0, 0);
			if (cnt == 100000) {
				sb.append('#').append(t).append(' ').append(k).append('\n');
				continue;
			}
			sb.append('#').append(t).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}

	private static void check(int a, int d) {
		if (isGood()) {
			if (cnt > d) {
				cnt = d;
			}
		}
		if (d > k) {
			return;
		}
		if (a == r) {
			return;
		}
		check(a + 1, d);
		boolean tmp[] = new boolean[c];
		for (int i = 0; i < c; i++) {
			tmp[i] = arr[a][i];
		}
		for (int i = 0; i < c; i++) {
			arr[a][i] = true;
		}
		check(a + 1, d + 1);
		for (int i = 0; i < c; i++) {
			arr[a][i] = false;
		}
		check(a + 1, d + 1);
		for (int i = 0; i < c; i++) {
			arr[a][i] = tmp[i];
		}
	}

	private static boolean isGood() {
		for (int i = 0; i < c; i++) {
			int tmp = 1;
			for (int j = 0; j < r; j++) {
				if (j == 0) {
					continue;
				}
				if (arr[j][i] == arr[j - 1][i]) {
					tmp++;
				} else {
					tmp = 1;
				}
				if (tmp == k) {
					break;
				}
				if (j == (r - 1)) {
					return false;
				}
			}
		}
		return true;
	}
}
