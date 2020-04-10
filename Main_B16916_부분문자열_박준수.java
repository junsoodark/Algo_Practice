package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B16916_부분문자열_박준수 {
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
					System.out.println(1);
					return;
				} else {
					j++;
				}
			}
		}
		System.out.println(0);

	}

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("input/input_B_16916.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		kmp(a, b);
	}
}
