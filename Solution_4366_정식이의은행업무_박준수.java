package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution_4366_정식이의은행업무_박준수 {
	static HashSet<Integer> h;
	static int result;
	static char three[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_4366.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			h = new HashSet<Integer>();
			sb.append('#').append(t).append(' ');
			String tmptwo = br.readLine();
			int lentwo = tmptwo.length();
			int two = Integer.parseInt(tmptwo, 2);
			three = br.readLine().toCharArray();
			inputTwo(two, lentwo);
			result = inputThree(three);

			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static int inputThree(char[] three) {
		int len = three.length;
		int tmp = 0;
		for (int i = 0; i < len; i++) {
			if (three[i] == '0') {
				three[i] = '1';
				tmp = toInt(three);
				if (!h.add(tmp)) {
					return tmp;
				}
				three[i] = '2';
				tmp = toInt(three);
				if (!h.add(tmp)) {
					return tmp;
				}
				three[i] = '0';

			} else if (three[i] == '1') {
				three[i] = '0';
				tmp = toInt(three);
				if (!h.add(tmp)) {
					return tmp;
				}
				three[i] = '2';
				tmp = toInt(three);
				if (!h.add(tmp)) {
					return tmp;
				}
				three[i] = '1';
			} else {
				three[i] = '0';
				tmp = toInt(three);
				if (!h.add(tmp)) {
					return tmp;
				}
				three[i] = '1';
				tmp = toInt(three);
				if (!h.add(tmp)) {
					return tmp;
				}
				three[i] = '2';
			}
		}
		return 0;
	}

	private static int toInt(char[] three) {
		int len = three.length;
		int tmp = 0;
		for (int i = 0; i < len; i++) {
			tmp = tmp + (three[i] - '0') * (int) Math.pow(3, len - i - 1);
		}
		return tmp;
	}

	private static void inputTwo(int two, int len) {
		int tmp = 0;
		for (int i = 0; i < len; i++) {
			int a = 1 << i;
			if ((two & a) == 0) {
				tmp = two | a;
				h.add(tmp);
			} else {
				tmp = two - a;
				h.add(tmp);
			}
		}
	}
}
