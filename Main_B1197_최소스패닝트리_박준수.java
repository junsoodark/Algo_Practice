package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B1197_최소스패닝트리_박준수 {
	static int parents[];
	static int edges[][];

	static PriorityQueue<edge> pq = new PriorityQueue<edge>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_1197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		parents = new int[v + 1];
		Arrays.fill(parents, -1);
		edges = new int[3][e];
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[0][i] = a;
			edges[1][i] = b;
			edges[2][i] = c;
			pq.offer(new edge(a, b, c));
		}
		long result = 0;
		while (!pq.isEmpty()) {
			edge edge = pq.poll();
			if (findset(edge.from) == findset(edge.to)) {
				continue;
			}
			result += edge.dis;
			union(edge.from, edge.to);

		}
		System.out.println(result);
	}

	private static int findset(int a) {
		if (parents[a] < 0) {
			return a;
		}
		return parents[a] = findset(parents[a]);
	}

	private static void union(int a, int b) {
		int roota = findset(a);
		int rootb = findset(b);
		if (roota < rootb) {
			parents[roota] = parents[roota] + parents[rootb];
			parents[rootb] = roota;
		} else {
			parents[rootb] = parents[roota] + parents[rootb];
			parents[roota] = rootb;
		}
	}

	static class edge implements Comparable<edge> {
		int from, to, dis;

		public edge() {
		};

		public edge(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(edge o) {
			if ((this.dis - o.dis) < 0) {
				return -1;
			} else {
				return 1;
			}
		}

	}
}
