import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취_박준수2 {
	static int n, m, c; // 행렬 크기 : n, 벌통 선택 개수 : m, 벌통 최대 개수 : c
	static int sel1[], sel2[];// 해당 열에서 1개 혹은 2개를 선택 할때의 최대값
	static int arr[][], maxarr[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			maxarr = new int[n][n - m + 1];
			sel1 = new int[n];
			sel2 = new int[n];
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < n; j2++) {
					arr[j][j2] = Integer.parseInt(st.nextToken());
				}
			}
			makeMaxMap();

			System.out.println("#" + i + " " + getMaxBenefit());
		}
	}// end main

	private static void makeMaxMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n - m; j++) {
				makeMaxSubset(i, j, 0, 0, 0);
			}
		}
	}

	// i : 행위치, j : 열위치, cnt : 고려한 원소수
	// sum : 부분집합에 속한 원소의 합, powSum :부분집합에 속한 원소의 이익
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
		if (sum > c) {
			return;
		}
		if (cnt == m) {
			if (maxarr[i][j - m] < powSum) {
				maxarr[i][j - m] = powSum;
			}
			return;
		}
		// 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + arr[i][j], powSum + arr[i][j] * arr[i][j]);
		// 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum);
	}

	private static int getMaxBenefit() {
		int max = 0, temp = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n - m; j++) {
				// 같은행 기준 선택
				for (int j2 = j + m; j2 < n - m; j2++) {
					temp = maxarr[i][j] + maxarr[i][j2];
					if (max < temp) {
						max = temp;
					}
				}

				// 다음행부터 마지막행까지 선택
				for (int j2 = i + 1; j2 < n; j2++) {
					for (int k = 0; k <= n - m; k++) {
						temp = maxarr[i][j] + maxarr[j2][k];
						if (max < temp) {
							max = temp;
						}
					}
				}
			}

		}

		return max;
	}

}
