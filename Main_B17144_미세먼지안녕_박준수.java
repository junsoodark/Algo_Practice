package algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17144_미세먼지안녕_박준수 {
	static int map1[][], map2[][];
	static int I, J, T;
	static int c1i = -1, c2i = -1, total = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/input_B_17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		I = Integer.parseInt(st.nextToken());
		J = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map1 = new int[I][J];
		map2 = new int[I][J];
		for (int i = 0; i < I; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < J; j++) {
				map1[i][j] = Integer.parseInt(st.nextToken());
				total += map1[i][j];
				if (map1[i][j] == -1) {
					map2[i][j] = -1;
					if (c1i == -1) {
						c1i = i;
					} else {
						c2i = i;
					}
				}
			}
		}
		total = total + 2;
		for (int i = 0; i < T; i++) {
			if (i % 2 == 0) {
				spread1();
				clean2();
//				for (int j = 0; j < I; j++) {
//					System.out.println(Arrays.toString(map2[j]));
//				}
//				System.out.println();
			} else {
				spread2();
				clean1();
//				for (int j = 0; j < I; j++) {
//					System.out.println(Arrays.toString(map1[j]));
//				}
//				System.out.println();
			}
		}
		System.out.println(total);
	}

	static int di[] = { 0, 1, 0, -1 };
	static int dj[] = { 1, 0, -1, 0 };

	public static void spread1() {
		for (int i = 0; i < I; i++) {
			for (int j = 0; j < J; j++) {
				if (map1[i][j] > 0) {
					int tmp = map1[i][j] / 5;
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int ni = i + di[k];
						int nj = j + dj[k];
						if (ni < 0 || nj < 0 || ni >= I || nj >= J || map1[ni][nj] == -1) {
							continue;
						} else {
							cnt++;
							map2[ni][nj] += tmp;
						}
					}
					map2[i][j] += map1[i][j] - (cnt * tmp);
				}
			}
		}
		for (int i = 0; i < I; i++) {
			for (int j = 0; j < J; j++) {
				if (map1[i][j] != -1) {
					map1[i][j] = 0;
				}
			}
		}
	}

	public static void spread2() {
		for (int i = 0; i < I; i++) {
			for (int j = 0; j < J; j++) {
				if (map2[i][j] > 0) {
					int tmp = map2[i][j] / 5;
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int ni = i + di[k];
						int nj = j + dj[k];
						if (ni < 0 || nj < 0 || ni >= I || nj >= J || map2[ni][nj] == -1) {
							continue;
						} else {
							cnt++;
							map1[ni][nj] += tmp;
						}
					}
					map1[i][j] += map2[i][j] - (cnt * tmp);
				}
			}
		}
		for (int i = 0; i < I; i++) {
			for (int j = 0; j < J; j++) {
				if (map2[i][j] != -1) {
					map2[i][j] = 0;
				}
			}
		}
	}

	public static void clean1() {
		total -= map1[c1i - 1][0];
		for (int i = 1; i < c1i; i++) {
			map1[c1i - i][0] = map1[c1i - i - 1][0];
		}
		for (int i = 0; i < J - 1; i++) {
			map1[0][i] = map1[0][i + 1];
		}
		for (int i = 0; i < c1i; i++) {
			map1[i][J - 1] = map1[i + 1][J - 1];
		}
		for (int i = 0; i < J - 2; i++) {
			map1[c1i][J - 1 - i] = map1[c1i][J - 2 - i];
		}
		map1[c1i][1] = 0;
		total -= map1[c2i + 1][0];
		for (int i = 1; i < I - c2i - 1; i++) {
			map1[c2i + i][0] = map1[c2i + i + 1][0];
		}
		for (int i = 0; i < J - 1; i++) {
			map1[I - 1][i] = map1[I - 1][i + 1];
		}
		for (int i = 0; i < I - c2i - 1; i++) {
			map1[I - 1 - i][J - 1] = map1[I - 2 - i][J - 1];
		}
		for (int i = 0; i < J - 2; i++) {
			map1[c2i][J - 1 - i] = map1[c2i][J - 2 - i];
		}
		map1[c2i][1] = 0;
	}

	public static void clean2() {
		total -= map2[c1i - 1][0];
		for (int i = 1; i < c1i; i++) {
			map2[c1i - i][0] = map2[c1i - i - 1][0];
		}
		for (int i = 0; i < J - 1; i++) {
			map2[0][i] = map2[0][i + 1];
		}
		for (int i = 0; i < c1i; i++) {
			map2[i][J - 1] = map2[i + 1][J - 1];
		}
		for (int i = 0; i < J - 2; i++) {
			map2[c1i][J - 1 - i] = map2[c1i][J - 2 - i];
		}
		map2[c1i][1] = 0;
		total -= map2[c2i + 1][0];
		for (int i = 1; i < I - c2i - 1; i++) {
			map2[c2i + i][0] = map2[c2i + i + 1][0];
		}
		for (int i = 0; i < J - 1; i++) {
			map2[I - 1][i] = map2[I - 1][i + 1];
		}
		for (int i = 0; i < I - c2i - 1; i++) {
			map2[I - 1 - i][J - 1] = map2[I - 2 - i][J - 1];
		}
		for (int i = 0; i < J - 2; i++) {
			map2[c2i][J - 1 - i] = map2[c2i][J - 2 - i];
		}
		map2[c2i][1] = 0;
	}
}
