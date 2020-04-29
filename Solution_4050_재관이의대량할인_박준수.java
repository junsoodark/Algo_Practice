package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_4050_재관이의대량할인_박준수 {
	static PriorityQueue<Integer> q;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_4050.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			q = new PriorityQueue<Integer>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					if (o1 > o2) {
						return -1;
					} else if (o1 < o2) {
						return 1;
					}
					return 0;
				}
			});
			result = 0;
			sb.append('#').append(t).append(' ');
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(st.nextToken());
				result = result + a;
				q.add(a);
			}
			int cnt = 0;
			while (!q.isEmpty()) {
				cnt++;
				if (cnt % 3 == 0) {
					result = result - q.poll();
				} else {
					q.poll();
				}
			}

			sb.append(result).append('\n');
		}
		System.out.println(sb);

	}

}
