package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2252_줄세우기_박준수 {

	static int N, M;
	static int cnt[];
	static ArrayList<ArrayList<Integer>> graph;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/input_B_2252.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = new int[N + 1];
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			cnt[b]++;
		}
		print();
		System.out.println(sb);

	}

	private static void print() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i < N + 1; i++) {
			if (cnt[i] == 0) {
				q.add(i);
			}
		}
		for (int i = 0; i < N; i++) {
			int tmp = q.poll();
			sb.append(tmp).append(' ');
			for (int nextTmp : graph.get(tmp)) {
				int t = --cnt[nextTmp];
				if (t == 0) {
					q.add(nextTmp);
				}
			}
		}

	}
}
