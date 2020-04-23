package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트_박준수_완탐 {
	static int T, N, L;
	static int greed[][];
	static int MAX;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_S_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int a = 0; a < T; a++) {
			MAX = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			greed = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				greed[i][0] = Integer.parseInt(st.nextToken());// score
				greed[i][1] = Integer.parseInt(st.nextToken());// calorie
			}
			print(0, 0, 0);
			System.out.println("#" + (a + 1) + " " + MAX);
		}

	}

	private static void print(int n, int sumS, int sumC) {
		if (n == N && sumC <= L) {
			if (MAX < sumS) {
				MAX = sumS;
			}
			return;
		} else if (n == N) {
			return;
		} else if (sumC > L) {
			return;
		}
		print(n + 1, sumS + greed[n][0], sumC + greed[n][1]);
		print(n + 1, sumS, sumC);
		return;
	}

}
