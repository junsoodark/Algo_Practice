package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B2579_계단오르기_박준수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_B_2579.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int stair[] = new int[n + 1];
		int dp[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		if (n == 1) {
			System.out.println(stair[1]);
		} else if (n == 2) {
			System.out.println(stair[1] + stair[2]);
		} else if (n == 3) {
			System.out.println(Math.max(dp[1] + stair[3], stair[2] + stair[3]));
		} else {
			dp[1] = stair[1];
			dp[2] = stair[1] + stair[2];
			dp[3] = Math.max(dp[1] + stair[3], stair[2] + stair[3]);
			for (int i = 4; i <= n; i++) {
				dp[i] = Math.max(dp[i - 2] + stair[i], stair[i - 1] + stair[i] + dp[i - 3]);
			}
			System.out.println(dp[n]);
		}
	}
}
