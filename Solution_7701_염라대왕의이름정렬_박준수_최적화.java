import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Solution_7701_염라대왕의이름정렬_박준수_최적화 {
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<String> pq[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		pq = new PriorityQueue[51];
		for (int i = 1; i <= 50; i++) {
			pq[i] = new PriorityQueue<String>();
		}
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append('\n');
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				String tmpstr = br.readLine();
				pq[tmpstr.length()].offer(tmpstr);
			}
			for (int i = 1; i <= 50; i++) {
				String s = "";
				while (!pq[i].isEmpty()) {
					String tmps = pq[i].poll().toString();
					if (tmps.equals(s)) {
						continue;
					}
					s = tmps;
					sb.append(tmps).append('\n');
				}
			}
		}
		System.out.println(sb);
	}
}
