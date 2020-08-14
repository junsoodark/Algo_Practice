package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B16637_괄호추가하기_박준수2 {
	static int N;
	static char[] arr;
	static int Max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/input_B_16637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();
		cal(0, 0);
		System.out.println(Max);
	}

	private static void cal(int sum, int i) {
		if (i == N) {
			if (Max < sum) {
				Max = sum;
			}
			return;
		}
		if (i == 0) {
			int a = arr[0] - '0';
			cal(a, 1);
			if (N >= 3) {
				int b = arr[2] - '0';
				if (arr[1] == '+') {
					cal(a + b, 3);
				} else if (arr[1] == '-') {
					cal(a - b, 3);
				} else {
					cal(a * b, 3);
				}
			}
		} else {
			int a = arr[i + 1] - '0';
			if (arr[i] == '+') {
				cal(sum + a, i + 2);
			} else if (arr[i] == '-') {
				cal(sum - a, i + 2);
			} else {
				cal(sum * a, i + 2);
			}
			if (i + 4 <= N) {
				int b = arr[i + 3] - '0';
				int tmp;
				if (arr[i + 2] == '+') {
					tmp = a + b;
				} else if (arr[i + 2] == '-') {
					tmp = a - b;
				} else {
					tmp = a * b;
				}
				if (arr[i] == '+') {
					cal(sum + tmp, i + 4);
				} else if (arr[i] == '-') {
					cal(sum - tmp, i + 4);
				} else {
					cal(sum * tmp, i + 4);
				}
			}
		}

	}
}
