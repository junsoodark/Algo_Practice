import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_하나로_박준수 {
	static int parents[], N;
	static double land[][];
	static double E;
	static double resultdis;
	static PriorityQueue<edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			resultdis = 0;
			N = Integer.parseInt(br.readLine());
			parents = new int[N];
			Arrays.fill(parents, -1);
			land = new double[2][N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				land[0][i] = Double.parseDouble(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				land[1][i] = Double.parseDouble(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					pq.offer(new edge(i, j,
							Math.pow(land[0][i] - land[0][j], 2) + Math.pow(land[1][i] - land[1][j], 2)));
				}
			}
			while (!pq.isEmpty()) {
				edge edge = pq.poll();
				if (findset(edge.from) != findset(edge.to)) {
					union(edge.from, edge.to);
					resultdis += edge.dis;
				}
			}
			sb.append('#').append(t).append(' ');
			resultdis = resultdis * E;
			if (resultdis * 10 % 10 >= 5) {
				resultdis = resultdis + 1;
			}
			sb.append((long) resultdis).append('\n');

		}
		System.out.println(sb);
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
		int from, to;
		double dis;

		public edge(int from, int to, double dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(edge edge) {
			if ((this.dis - edge.dis) < 0) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
