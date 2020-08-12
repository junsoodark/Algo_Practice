package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B9251_LCS_박준수 {
	static BufferedReader br;
	static char[] aArr;
	static char[] bArr;
	static int dpArr[][];

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		aArr = br.readLine().toCharArray();
		bArr = br.readLine().toCharArray();
		dp();
		System.out.println(dpArr[aArr.length][bArr.length]);
	}

	public static void dp() {

		for (int i = 1; i <= aArr.length; i++) {
			for (int j = 1; j <= bArr.length; j++) {
				if (aArr[i - 1] == bArr[j - 1]) {
					dpArr[i][j] = dpArr[i - 1][j - 1] + 1;
				} else {
					dpArr[i][j] = Math.max(dpArr[i - 1][j], dpArr[i][j - 1]);
				}
			}
		}
	}
}