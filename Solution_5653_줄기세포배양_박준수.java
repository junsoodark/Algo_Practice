package SWExpert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5653_줄기세포배양_박준수 {
	static boolean visit[][];// 400*400으로 항상 초기화
	static PriorityQueue<Cell> pq;
	static Queue<Cell> q;
	static Queue<Cell> tmpq;
	static Queue<Cell> rq;
	static int k;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input/input_S_5653.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			visit = new boolean[400][400];
			pq = new PriorityQueue<Cell>();
			q = new LinkedList<Cell>();
			tmpq = new LinkedList<Cell>();
			rq = new LinkedList<Cell>();
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			for (int i = 160; i < 160 + n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 160; j < 160 + m; j++) {
					int a = Integer.parseInt(st.nextToken());
					if (a != 0) {
						q.add(new Cell(i, j, a, 0));
						visit[i][j] = true;
					}
				}
			}
			for (int i = 0; i < k; i++) {
				doQ();
				doRq();
			}

			sb.append(q.size() + pq.size() + tmpq.size() + rq.size()).append('\n');
		}
		System.out.println(sb);
	}

	private static void doRq() {
		int size = rq.size();
		for (int i = 0; i < size; i++) {
			Cell c = rq.poll();
			c.time++;
			if (c.life >= c.time) {
				rq.add(c);
			}
		}

	}

	private static void doQ() {
		doPq();
		int size = q.size();
		for (int i = 0; i < size; i++) {
			Cell c = q.poll();
			c.time++;
			if (c.life == c.time) {
				pq.add(c);
			} else {
				q.add(c);
			}
		}
		size = tmpq.size();
		for (int i = 0; i < size; i++) {
			Cell c = tmpq.poll();
			q.add(c);
		}
	}

	private static void doPq() {
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			Cell c = pq.poll();
			c.time = 1;
			rq.add(c);
			for (int j = 0; j < 4; j++) {
				int ni = c.i + di[j];
				int nj = c.j + dj[j];
				if (!visit[ni][nj]) {
					visit[ni][nj] = true;
					tmpq.add(new Cell(ni, nj, c.life, 0));
				}
			}
		}

	}

	static int di[] = { -1, 0, 1, 0 };
	static int dj[] = { 0, 1, 0, -1 };

	public static class Cell implements Comparable<Cell> {
		public int i, j, life, time;// 좌표 : i, j, 생명력 : life, 지난 시간 : time

		public Cell(int i, int j, int life, int time) {
			this.i = i;
			this.j = j;
			this.life = life;
			this.time = time;
		}

		@Override
		public int compareTo(Cell o) {
			if (this.life > o.life) {
				return -1;
			} else if (this.life < o.life) {
				return 1;
			}
			return 0;
		}

	}
}
