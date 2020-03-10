import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7793_오나의여신님_박준수 {
	static int result;
	static char map[][];
	static Queue<Integer> qs, qd;
	static int di, dj, si, sj;
	static boolean visits[][], visitd[][];
	static int m, n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			result = 0;
			qs = new LinkedList<Integer>();
			qd = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			map = new char[m][n];
			visits = new boolean[m][n];
			visitd = new boolean[m][n];
			for (int i = 0; i < m; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					if ((map[i][j] = str.charAt(j)) == '*') {
						qd.add(i * 100 + j);
						visitd[i][j] = true;
					} else if (map[i][j] == 'S') {
						si = i;
						sj = j;
					}
				}
			}
			bfs();

			sb.append('#').append(t).append(' ');
			if (result < 1) {
				sb.append("GAME OVER").append('\n');
			} else {
				sb.append(result).append('\n');
			}
		}
		System.out.println(sb);
	}

	static int ai[] = { 0, 0, 1, -1 };
	static int aj[] = { 1, -1, 0, 0 };

	public static void bfs() {
		visits[si][sj] = true;
		qs.add(si * 100 + sj);
		while (!qs.isEmpty()) {
			result++;
			int size;
			if (!qd.isEmpty()) {
				size = qd.size();
				for (int i = 0; i < size; i++) {
					int tmp = qd.poll();
					for (int j = 0; j < 4; j++) {
						int ni = tmp / 100;
						int nj = tmp % 100;
						ni = ni + ai[j];
						nj = nj + aj[j];
						if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
							continue;
						}
						if (map[ni][nj] != 'X' && map[ni][nj] != 'D' && !visitd[ni][nj]) {
							qd.add(ni * 100 + nj);
							visitd[ni][nj] = true;
						}
					}
				}
			}

			size = qs.size();
			for (int i = 0; i < size; i++) {
				int tmp = qs.poll();
				for (int j = 0; j < 4; j++) {
					int ni = tmp / 100;
					int nj = tmp % 100;
					ni = ni + ai[j];
					nj = nj + aj[j];
					if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
						continue;
					}
					if (map[ni][nj] == 'D') {
						return;
					}
					if (map[ni][nj] != 'X' && !visitd[ni][nj] && !visits[ni][nj]) {
						qs.add(ni * 100 + nj);
						visits[ni][nj] = true;
					}

				}
			}

		}
		result = -1;
		return;
	}
}
