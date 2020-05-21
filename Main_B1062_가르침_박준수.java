package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1062_가르침_박준수 {

	static int N, K, result = 0;
	static String[] str;
	static boolean[] alphabet = new boolean[26];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_1062.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}
		alphabet[(int) ('a' - 'a')] = true;
		alphabet[(int) ('n' - 'a')] = true;
		alphabet[(int) ('t' - 'a')] = true;
		alphabet[(int) ('i' - 'a')] = true;
		alphabet[(int) ('c' - 'a')] = true;
		if (K < 5) {
			System.out.println(result);
		} else {
			count(0, 0);
			System.out.println(result);
		}
	}

	private static void count(int cnt, int k) {
		if (cnt == K - 5) {
			int c = 0;
			for (int i = 0; i < str.length; i++) {
				boolean b = true;
				int len = str[i].length();
				for (int j = 4; j < len - 4; j++) {
					if (!alphabet[(int) (str[i].charAt(j) - 'a')]) {
						b = false;
						break;
					}
				}
				if (b == true)
					c++;
			}
			if (result < c)
				result = c;
			return;
		}
		if (k == 26) {
			return;
		}
		for (int i = k; i < 26; i++) {
			if (!alphabet[i]) {
				alphabet[i] = true;
				count(cnt + 1, i + 1);
				alphabet[i] = false;
			}
		}
	}

}