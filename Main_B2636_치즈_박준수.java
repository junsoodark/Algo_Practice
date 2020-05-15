package BJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_B2636_치즈_박준수 {
	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };
	static boolean arr[][], visit[][];
	static int N, M, cnt = 0, hour = 0;
	static Set<Integer> s = new HashSet<Integer>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_2636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new boolean[N + 2][M + 2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (st.nextToken().equals("1")) {
					arr[i + 1][j + 1] = true;
					cnt++;
				}
			}
		}
		while (true) {
			s.clear();
			visit = new boolean[N + 2][M + 2];
			if (cnt == 0) {
				System.out.println(0);
				System.out.println(0);
				break;
			}
			int cnttmp = cnt;
			dfs(0, 0);
			hour++;
			for (int tmp : s) {
				int a = tmp / 1000;
				int b = tmp % 1000;
				arr[a][b] = false;
			}
			if (cnt == 0) {
				System.out.println(hour);
				System.out.println(cnttmp);
				break;
			}
		}
	}

	private static void dfs(int i, int j) {
		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];
			if (ni < 0 || ni >= (N + 2) || nj < 0 || nj >= (M + 2) || visit[ni][nj]) {
				continue;
			}
			visit[ni][nj] = true;
			if (arr[ni][nj] && s.add(ni * 1000 + nj)) {
				cnt--;
			} else if (!arr[ni][nj]) {
				dfs(ni, nj);
			}

		}
	}
}
