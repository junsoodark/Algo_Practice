package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2931_가스관_박준수 {
	static char arr[][];
	static int r, c;
	static boolean ok = false;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_2931.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < r; i++) {
			if (ok) {
				break;
			}
			for (int j = 0; j < c; j++) {
				if (ok) {
					break;
				}
				check4(i, j);
			}
		}
	}

	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };

	static void check4(int i, int j) {
		if (arr[i][j] != '.') {
			return;
		}
		char dir[] = new char[4];
		boolean bdir[] = new boolean[4];
		for (int k = 0; k < dir.length; k++) {
			dir[k] = 'x';
		}
		for (int j2 = 0; j2 < 4; j2++) {
			int ni = i + di[j2];
			int nj = j + dj[j2];
			if (ni < 0 || ni >= r || nj < 0 || nj >= c) {
				continue;
			}
			if (arr[ni][nj] != '.') {
				dir[j2] = arr[ni][nj];
			}
		}
		for (int k = 0; k < bdir.length; k++) {
			if (k == 0 && (dir[k] == '|' || dir[k] == '+' || dir[k] == '1' || dir[k] == '4')) {
				bdir[k] = true;
			}
			if (k == 1 && (dir[k] == '-' || dir[k] == '+' || dir[k] == '3' || dir[k] == '4')) {
				bdir[k] = true;
			}
			if (k == 2 && (dir[k] == '|' || dir[k] == '+' || dir[k] == '2' || dir[k] == '3')) {
				bdir[k] = true;
			}
			if (k == 3 && (dir[k] == '-' || dir[k] == '+' || dir[k] == '1' || dir[k] == '2')) {
				bdir[k] = true;
			}
		}
		if (bdir[1] && bdir[3] && bdir[2] && bdir[0]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "+");
			ok = true;
		} else if (bdir[0] && bdir[2]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "|");
			ok = true;
		} else if (bdir[1] && bdir[3]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "-");
			ok = true;
		} else if (bdir[1] && bdir[2]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "1");
			ok = true;
		} else if (bdir[0] && bdir[1]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "2");
			ok = true;
		} else if (bdir[0] && bdir[3]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "3");
			ok = true;
		} else if (bdir[2] && bdir[3]) {
			System.out.println((i + 1) + " " + (j + 1) + " " + "4");
			ok = true;
		}

	}
}
