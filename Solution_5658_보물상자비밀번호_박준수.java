package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_5658_보물상자비밀번호_박준수 {
	static PriorityQueue<Integer> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_5658.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			q = new PriorityQueue<Integer>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return -1;
					} else if (o1 < o2) {
						return 1;
					}
					return 0;
				}
			});
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int len = n / 4;
			String str = br.readLine();
			for (int i = 0; i < n; i++) {
				if ((i + len) < n) {
					q.add(toTen(str.substring(i, i + len)));
				} else {
					q.add(toTen(str.substring(i, n) + str.substring(0, len - (n - i))));
				}
			}
			int before = Integer.MAX_VALUE;
			for (int i = 1; i <= k; i++) {
				int tmp = q.poll();
				if (before != tmp) {
					before = tmp;
					continue;
				}
				i--;
			}
			sb.append(before).append('\n');
		}
		System.out.println(sb);
	}

	private static int toTen(String str) {
		int len = str.length();
		int tmp = 0;
		for (int i = 0; i < len; i++) {
			tmp = tmp * 16;
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'F') {
				tmp = tmp + str.charAt(i) - 'A' + 10;
			} else {
				tmp = tmp + str.charAt(i) - '0';
			}
		}
		return tmp;
	}
}
