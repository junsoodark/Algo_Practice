import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_최적경로_박준수_NP {
	static int n;// N : 고개집 수, p : 고객집의 순서를 만들 순열용 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] customers = new int[N][2];// N명의 고객집 좌표
			int[][] distance = new int[N + 2][2];// 회사, N명의 고객좌표, 집의 좌표
			int p[] = new int[N];
			int min = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			distance[0][0] = Integer.parseInt(st.nextToken());// 회사
			distance[0][1] = Integer.parseInt(st.nextToken());
			distance[N + 1][0] = Integer.parseInt(st.nextToken());// 집
			distance[N + 1][1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
				p[i] = i + 1;
			}
			do {
				for (int i = 0; i < N; i++) {
					distance[p[i]] = customers[i];
				}
				int tmp = 0;
				for (int i = 0; i <= N; i++) {
					tmp += Math.abs(distance[i][0] - distance[i + 1][0]);
					tmp += Math.abs(distance[i][1] - distance[i + 1][1]);
					if (tmp >= min) {
						break;
					}

				}
				if (min > tmp) {
					min = tmp;
				}

			} while (np(p));
			sb.append('#').append(t).append(' ').append(min).append('\n');

		}
		System.out.println(sb);
	}

	private static boolean np(int[] p) {
		int n = p.length;
		int i = n - 1;
		while (i > 0 && p[i - 1] >= p[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}

		int j = n - 1;
		while (p[i - 1] >= p[j]) {
			--j;
		}
		int tmp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = tmp;

		int k = n - 1;
		while (k > i) {
			tmp = p[k];
			p[k] = p[i];
			p[i] = tmp;
			k--;
			i++;
		}

		return true;
	}

}
