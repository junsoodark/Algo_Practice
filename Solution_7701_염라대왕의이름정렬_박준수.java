import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_7701_염라대왕의이름정렬_박준수 {
	static PriorityQueue<str> pq = new PriorityQueue<str>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append('\n');
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				pq.offer(new str(br.readLine()));
			}
			String s = "";
			while (!pq.isEmpty()) {
				String tmps = pq.poll().str;
				if (tmps.equals(s)) {
					continue;
				}
				s = tmps;
				sb.append(tmps).append('\n');
			}
		}
		System.out.println(sb);
	}

	public static class str implements Comparable<str> {
		String str;
		int len;

		str(String str) {
			this.str = str;
			this.len = str.length();
		}

		@Override
		public int compareTo(str str) {
			if (this.len == str.len) {
				for (int i = 0; i < this.len; i++) {
					if (str.str.charAt(i) == this.str.charAt(i)) {
						continue;
					}
					if (str.str.charAt(i) < this.str.charAt(i)) {
						return 1;
					}
					return -1;
				}
			} else {
				if (this.len < str.len) {
					return -1;
				}
				return 1;
			}
			return 0;
		}
	}
}
