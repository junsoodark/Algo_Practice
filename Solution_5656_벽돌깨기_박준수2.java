import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기_박준수2 {
	static StringBuilder sb = new StringBuilder();
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int TC, N, C, R;
	static int brickCnt;
	static int answer;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			brickCnt = 0;
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] != 0) {
						brickCnt++;
					}
				}
			}
			answer = brickCnt;
			dropMarble(N, brickCnt, map);
			sb.append(answer);

			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static void dropMarble(int r, int brickCnt, int[][] map) {
		if (r == 0) {
			answer = Math.min(answer, brickCnt);
			return;
		}
		for (int c = 0; c < C; c++) {
			int[][] cloned = clonedMap(map);
			Brick first = getFirstBrick(c, cloned);
			if (first == null) {
				continue;
			}
			int broken = crash(first, cloned);

			if (broken >= brickCnt) {
				answer = 0;
				return;
			}
			cleanMap(cloned);

			dropMarble(r - 1, brickCnt - broken, cloned);

		}
	}

	private static void cleanMap(int[][] map) {
		for (int c = 0; c < C; c++) {
			for (int r = R - 1, nr = R - 1; r >= 0; r--) {
				if (map[r][c] != 0) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[nr--][c] = temp;
					System.out.println(temp);

//					map[nr--][c] = map[r][c];
//					map[r][c] = 0;

				}
			}
		}
	}

	private static int crash(Brick first, int[][] map) {
		int broken = 0;
		map[first.row][first.col] = 0;
		broken++;
		for (int p = 1; p < first.pow; p++) {
			for (int d = 0; d < dirs.length; d++) {
				int nr = first.row + dirs[d][0] * p;
				int nc = first.col + dirs[d][1] * p;
				if (isIn(nr, nc) && map[nr][nc] != 0) {
					broken += crash(new Brick(nr, nc, map[nr][nc]), map);
				}
			}
		}
		return broken;
	}

	private static boolean isIn(int r, int c) {
		return 0 <= r && 0 <= c && r < R && c < C;
	}

	private static Brick getFirstBrick(int c, int[][] map) {
		for (int r = 0; r < R; r++) {
			if (map[r][c] != 0) {
				return new Brick(r, c, map[r][c]);
			}
		}
		return null;
	}

	private static int[][] clonedMap(int[][] map) {
		int[][] temp = new int[R][C];
		for (int r = 0; r < R; r++) {
			temp[r] = map[r].clone();

		}
		return temp;
	}

	static class Brick {
		int row, col, pow;

		public Brick(int row, int col, int pow) {
			this.row = row;
			this.col = col;
			this.pow = pow;
		}

		@Override
		public String toString() {
			return "[" + row + ", " + col + ", " + pow + "]";
		}
	}
}
