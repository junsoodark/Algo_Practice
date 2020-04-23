package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트_박준수_DP {
	static int N, L;
	static int dp[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_S_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int a = 1; a <= T; a++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			dp = new int[N + 1][L + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());// score
				int cal = Integer.parseInt(st.nextToken());// calorie
				cal(score, cal, i);
			}
			sb.append('#').append(a).append(' ').append(dp[N][L]).append('\n');
		}
		System.out.println(sb);
	}

	private static void cal(int score, int cal, int num) {
		for (int i = 0; i <= L; i++) {
			if (i >= cal) {
				dp[num][i] = max(dp[num - 1][i], dp[num - 1][i - cal] + score);
			} else {
				dp[num][i] = dp[num - 1][i];
			}
		}
	}

	private static int max(int i, int j) {
		if (i > j) {
			return i;
		}
		return j;
	}
}
