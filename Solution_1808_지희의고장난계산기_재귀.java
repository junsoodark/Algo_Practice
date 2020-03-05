import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1808_지희의고장난계산기_재귀 {
	static boolean[] btn;
	static int min, X;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			btn = new boolean[10];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num;
			for (int i = 0; i < 10; i++) {
				num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					btn[i] = true;
				}

			}
			X = Integer.parseInt(br.readLine());
			find(X, 0);
			min = min == Integer.MAX_VALUE ? -1 : min;
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	private static int find(int x, int cnt) {
		if (isMake(x + "")) {

			if (cnt == 0) {
				min = len(x) + 1;
			}
			return len(x) + 1;
		}
		int result = -1;
		for (int i = 2, end = (int) Math.sqrt(x) + 1; i < end; i++) {
			if (x % i == 0 && isMake(i + "")) {
				int len1 = len(i) + 1;
				int len2 = find(x / i, cnt + 1);
				if (len2 > -1) {
					result = len1 + len2;
					if (result < min && x == X) {
						min = result;
					}
				}

			}

		}
		return result;

	}

	private static int len(int x) {
		return (int) Math.log10(x) + 1;
	}

	private static boolean isMake(String x) {
		for (char c : x.toCharArray()) {
			if (!btn[c - '0']) {
				return false;
			}
		}
		return true;
	}
}
