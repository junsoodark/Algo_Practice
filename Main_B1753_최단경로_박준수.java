package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B1753_최단경로_박준수 {
	static final int INF = Integer.MAX_VALUE;
	static boolean visit[];
	static int dis[];
	static List<List<node>> list;

	static class node implements Comparable<node> {
		int index, distance;

		public node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		public int compareTo(node n) {
			return this.distance - n.distance;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_1753.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(br.readLine());
		list = new ArrayList<List<node>>();
		for (int i = 0; i < v + 1; i++) {
			list.add(new ArrayList<node>());
		}
		visit = new boolean[v + 1];
		dis = new int[v + 1];
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new node(b, c));
		}
		Arrays.fill(dis, INF);
		dis[s] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.add(new node(s, 0));

		while (!pq.isEmpty()) {
			int now = pq.poll().index;
			if (visit[now]) {
				continue;
			}
			visit[now] = true;
			for (node node : list.get(now)) {
				if (dis[node.index] > dis[now] + node.distance) {
					dis[node.index] = dis[now] + node.distance;
					pq.add(new node(node.index, dis[node.index]));
				}
			}
		}
		for (int i = 1; i < v + 1; i++) {
			if (dis[i] == INF) {
				sb.append("INF\n");
			} else {
				sb.append(dis[i]).append('\n');
			}
		}
		System.out.println(sb);
	}
}
