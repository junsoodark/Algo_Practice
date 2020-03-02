import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_최적경로_박준수_재귀 {
	static int N;
	static int min, CX, CY, HX, HY;// min : 최소이동거리, CX, CY : 회사좌표, HY,HX : 집좌표
	static int[][] customers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			customers = new int[N][2];// [i][0] : x 좌표, [i][1] : y 좌표
			min = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			CX = Integer.parseInt(st.nextToken());
			CY = Integer.parseInt(st.nextToken());
			HX = Integer.parseInt(st.nextToken());
			HY = Integer.parseInt(st.nextToken());

			// 고객집 좌표
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			go(0, 0, CX, CY, 0);
			System.out.println("#" + t + " " + min);
		}
	}

	private static void go(int cnt, int visit, int bx, int by, int result) {
		// 가지치기
		if (result >= min) {
			return;
		}
		// 기저조건
		if (cnt == N) {
			result += Math.abs(bx - HX) + Math.abs(by - HY);
			if (min > result) {
				min = result;
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((visit & 1 << i) == 0) { // i 고객집이 기존 순열에 처리되었는지 확인 : 0 -> 처리 안됨
				// 기존 순열 상태에 i 고객집 추가
				go(cnt + 1, visit | (1 << i), customers[i][0], customers[i][1],
						result + Math.abs(bx - customers[i][0]) + Math.abs(by - customers[i][1]));
			}
		}
	}
}
