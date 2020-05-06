package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B6987_¿ùµåÄÅ_¹ÚÁØ¼ö {
	static int arr[][];
	static boolean ok;
	static int[] a = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] b = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_6987.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < 4; t++) {
			ok = false;
			arr = new int[6][3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if (!checkGame()) {
				sb.append('0').append(' ');
				continue;
			} else if (!checkDraw()) {
				sb.append('0').append(' ');
				continue;
			}
			solve(0);
			if (ok) {
				sb.append('1').append(' ');
			} else {
				sb.append('0').append(' ');
			}
		}
		System.out.println(sb);
	}

	private static boolean checkGame() {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 3; j++) {
				sum = sum + arr[i][j];
			}
		}
		if (sum != 30) {
			return false;
		}
		return true;
	}

	private static boolean checkDraw() {
		int sum = 0;
		int cnt = 0;
		int max = -1;
		for (int i = 0; i < arr.length; i++) {
			int tmp = arr[i][1];
			if (max < tmp && tmp > 0) {
				max = tmp;
			}
			if (tmp > 0) {
				cnt++;
			}
			if (tmp > 5) {
				return false;
			}
			sum = sum + tmp;
		}
		if (max >= cnt) {
			return false;
		}
		if (sum % 2 != 0) {
			return false;
		}
		return true;
	}

	static void solve(int game) {
		if (ok)
			return;
		if (game == 15) {
			ok = true;
			return;
		}
		int t1 = a[game];
		int t2 = b[game];

		// t1 ½Â¸®
		if (arr[t1][0] > 0 && arr[t2][2] > 0) {
			arr[t1][0]--;
			arr[t2][2]--;
			solve(game + 1);
			arr[t1][0]++;
			arr[t2][2]++;
		}
		// t2 ½Â¸®
		if (arr[t1][2] > 0 && arr[t2][0] > 0) {
			arr[t1][2]--;
			arr[t2][0]--;
			solve(game + 1);
			arr[t1][2]++;
			arr[t2][0]++;
		}
		// ¹«½ÂºÎ
		if (arr[t1][1] > 0 && arr[t2][1] > 0) {
			arr[t1][1]--;
			arr[t2][1]--;
			solve(game + 1);
			arr[t1][1]++;
			arr[t2][1]++;
		}
	}
}
