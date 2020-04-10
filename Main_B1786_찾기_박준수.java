package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B1786_찾기_박준수 {
	static StringBuilder sb = new StringBuilder();
	static int cnt = 0;

	static int[] getpi(String pattern) {
		int pi[] = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pi.length; i++) {
			while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
				j = pi[j - 1];
			}
			if (pattern.charAt(j) == pattern.charAt(i)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	static void kmp(String str, String pattern) {
		int[] pi = getpi(pattern);
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (str.charAt(i) == pattern.charAt(j)) {
				if (j == pi.length - 1) {
					cnt++;
					int tmp = i - pattern.length() + 2;
					sb.append(tmp).append(' ');
					j = pi[j];
				} else {
					j++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("input/input_B_1786.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		kmp(a, b);
		System.out.println(cnt);
		System.out.println(sb);
	}
}
