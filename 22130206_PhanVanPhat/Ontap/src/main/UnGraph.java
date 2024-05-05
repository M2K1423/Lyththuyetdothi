package main;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.management.Query;

public class UnGraph extends Graph {
	public static void main(String[] args) throws IOException {
		UnGraph ug = new UnGraph();
		Scanner sc = new Scanner(System.in);
		if (ug.loadGraph(filePath)) {
			ug.printMatrix();
			System.out.println("================");
//			ug.checkEuler();
//			ug.findEulerPath();
//			System.out.println();
//			ug.findEulerCycle(0);
//			ug.diTimCacDinhLienThong();
//			System.out.println(ug.isBipartite(0));
//			ug.hamilton();
//			ug.colorGraph();
//			ug.BFSTree(2);
//			ug.SpanningTreeByKruskal();
			ug.SpanningTreeByPrim(2);
//			while (true) {
//				System.out.println("1. Kiem tra do thi co phai la do thi vo huong khong");
//				System.out.println("2. Them canh vao do thi");
//				System.out.println("3. Xoa canh khoi do thi");
//				System.out.println("4. Tinh tong bac cua dinh");
//				System.out.println("5. Tinh tong bac cua do thi");
//				System.out.println("6. Tong so dinh cua do thi");
//				System.out.println("7. Kiem tra tinh lien thong cua do thi");
//				System.out.println("8. Duyet do thi bang BFS");
//				System.out.println("9. Duyet do thi bang DFS");
//				System.out.println("10. Thoat");
//
//				System.out.print("Chon chuc nang (1-9): ");
//				int choice = sc.nextInt();
//				switch (choice) {
//				case 1:
//					if (ug.checkkUnGraph())
//						System.out.println("Do thi vo huong.");
//					else
//						System.out.println("Do thi co huong.");
//					break;
//				case 2:
//					System.out.print("Nhap dinh  u: ");
//					int u = sc.nextInt();
//					System.out.print("Nhap dinh  v: ");
//					int v = sc.nextInt();
//					ug.addEde(ug.matrix, u, v);
//					ug.printMatrix();
//					break;
//				case 3:
//					System.out.print("Nhap dinh: ");
//					int u1 = sc.nextInt();
//					System.out.print("Nhap dinh v: ");
//					int v1 = sc.nextInt();
//					ug.removeEdge(ug.matrix, u1, v1);
//					ug.printMatrix();
//					break;
//				case 4:
//					System.out.print("Nhap dinh: ");
//					int vertex = sc.nextInt();
//					System.out.println("So bac cua dinh " + vertex + ": " + ug.deg(vertex));
//					break;
//				case 5:
//					System.out.println("Tong so bac cua do thi: " + ug.sumDeg());
//					break;
//				case 6:
//					System.out.println("Tong so dinh cua do thi: " + ug.numEdges());
//					break;
//				case 7:
//					if (ug.checkConnect())
//						System.out.println("Do thi lien thong.");
//					else
//						System.out.println("Do thi khong lien thong.");
//					break;
//				case 8:
//					System.out.print("Nhap dinh xuat phat: ");
//					int start = sc.nextInt();
//					ug.BFSGraph(start);
//					break;
//
//				case 9:
//					System.out.print("Nhap dinh xuat phat: ");
//					int start2 = sc.nextInt();
//					ug.DFSGraph(start2);
//
//					break;
//				case 10:
//					System.out.println("Da thoat .");
//					return;
//				default:
//					System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
//
//				}
//			}
		} else
			System.out.println("Uh Uh");
	}

	// - Kiem tra do thi co vo huong hay khong
	@Override
	public boolean checkkUnGraph() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] != matrix[j][i])
					return false;
			}
		}
		return true;
	}

	@Override
	public void addEdge(int[][] matrix, int v1, int v2) {
		if (v1 < 0 || v1 > matrix.length || v2 < 0 || v2 > matrix.length) {
			System.out.println("Dinh v1 hoac v2 k hop le");
		}

		matrix[v1][v2] = matrix[v2][v1] = 1;
	}

	@Override
	public void removeEdge(int[][] matrix, int v1, int v2) {
		if (v1 > matrix.length || v2 > matrix.length || v1 < 0 || v2 < 0) {
			System.out.println("Khong xoa duoc");
		} else if (matrix[v1 - 1][v2 - 1] == 0 && matrix[v2 - 1][v1 - 1] == 0) {
			System.out.println("Canh da duoc xoa roi");
		} else {
			matrix[v1 - 1][v2 - 1] = 0;
			matrix[v2 - 1][v1 - 1] = 0;
		}

	}

	@Override
	public int deg(int v) {
		int result = 0;
		for (int i = 0; i < num; i++) {
			if (matrix[v][i] == 1)
				result++;
		}

		return result;
	}

	@Override
	public int sumDeg() {
		int result = 0;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (matrix[i][j] == 1)
					result++;
			}
		}

		return result;
	}

	@Override
	public int numEdges() {
		int result = 0;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				result += matrix[i][j];
			}
		}

		return result / 2;
	}

	@Override
	public boolean checkConnect() {
		int count = 0;
		checked[0] = true;
		count++;
		for (int i = 0; i < num; i++) {
			if (checked[i]) {
				for (int j = 0; j < num; j++) {
					if (matrix[i][j] != 0 && checked[j] == false) {
						checked[j] = true;
						count++;
						if (count == num) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkConnect(int start) {
		int count = 0;
		checked[start] = true;
		count++;
		for (int i = start; i < num; i++) {
			if (checked[i] == true) {
				for (int j = 0; j < num; j++) {
					if (matrix[i][j] != 0 && checked[j] == false) {
						checked[j] = true;
						count++;
						if (count == num) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void diTimCacDinhLienThong() {
		int count = 1;
		boolean visited[] = new boolean[num];
		resetChecked();
		System.out.println("Tong so thanh phan lien thong: " + countConnect());
		for (int i = 0; i < num; i++) {
			if (!visited[i]) {
				System.out.println("Thanh phan lien thong thu " + count + ": ");
				printConnected(i, visited);
				System.out.println(); // Xuống dòng sau mỗi lần in ra một thành phần liên thông
				count++;
			}
		}
	}

	public void printConnected(int vertex, boolean[] visited) {
		visited[vertex] = true;
		System.out.print(vertex + " ");

		for (int i = 0; i < num; i++) {
			if (matrix[vertex][i] != 0 && !visited[i]) {
				printConnected(i, visited);
			}
		}
	}

	@Override
	public int countConnect() {
		int countConnect = 0;
		resetChecked();
		for (int i = 0; i < num; i++) {
			if (checked[i] == false) {
				countConnect++;
				checkConnect(i);
			}
		}

		return countConnect;

	}

	private void resetChecked() {
		for (int i = 0; i < num; i++) {
			checked[i] = false;
		}

	}

	@Override
	public void BFSGraph(int startVex) {
		Queue<Integer> open = new LinkedList<>();
		List<Integer> close = new ArrayList<>();

		if (!checkConnect(startVex)) {
			System.out.println("Do thi khong lien thong --> khong duyet bfs");
			return;
		}
		resetChecked();

		open.add(startVex);
		checked[startVex] = true;
		while (!open.isEmpty()) {
			int u = open.remove();
			close.add(u);
			for (int v = 0; v < num; v++) {
				if (matrix[u][v] == 1 && !checked[v]) {
					open.add(v);
					checked[v] = true;
				}
			}
		}

		for (int i = 0; i < close.size(); i++) {
			System.out.print(close.get(i) + 1 + " ");
		}
	}

	@Override
	public void DFSGraph(int startVex) {
		List<Integer> close = new ArrayList<>();
		resetChecked();
		recursiveDFS(startVex, close);

		for (int i = 0; i < close.size(); i++) {
			System.out.print(close.get(i) + 1 + " ");
		}
	}

	private void recursiveDFS(int vertex, List<Integer> close) {
		checked[vertex] = true;
		close.add(vertex);

		for (int v = 0; v < num; v++) {
			if (matrix[vertex][v] == 1 && !checked[v]) {
				recursiveDFS(v, close);
			}
		}
	}

	// Kiem tra chu trinh Euler
	public boolean checkCycleEuler() {
		if (checkConnect()) {
			resetChecked();
			for (int i = 0; i < num; i++) {
				if (deg(i) % 2 != 0) {
					return false;
				}
			}
			return true;
		} else {
			System.out.println("Do thi k lien thong");
			return false;
		}
	}

	// Kiem tra duong di Euler
	public boolean checkPathEuler() {
		if (checkConnect()) {
			resetChecked();
			int tmp1 = 0;
			int tmp2 = 0;
			for (int i = 0; i < num; i++) {
				if (deg(i) % 2 == 0) {
					tmp1++;
				} else {
					tmp2++;
				}
			}
			return (tmp2 == 2 && tmp1 == num - 2) ? true : false;
		} else {
			System.out.println("Do thi k lien thong");
			return false;
		}
	}

	@Override
	public void checkEuler() {
		if (checkConnect()) {
			resetChecked();
			if (checkCycleEuler()) {
				System.out.println("Do thi co chu trinh Euler.");
			} else if (checkPathEuler()) {
				System.out.println("Do thi co duong di Euler.");
			} else {
				System.out.println("Do thi k co chu trinh, khong co duong di Euler.");
			}
		} else {
			System.out.println("Do thi khong lien thong.");
		}
	}

	public void findEulerCycle() {
		if (!checkCycleEuler()) {
			System.out.println("Do thi khong co chu trinh Euler.");
			return;
		}

		Stack<Integer> stack = new Stack<>();
		List<Integer> eulerCycle = new ArrayList<>();

		// - Chọn một đỉnh bất kì làm đỉnh xuất phát -> mặc định là đỉnh thứ 1
		stack.push(0);
		while (!stack.isEmpty()) {
			// Tìm một đỉnh kề với đỉnh vừa cho vào stack
			int currentVex = stack.peek();
			int nextVex = -1;
			for (int i = 0; i < num; i++) {
				if (matrix[currentVex][i] != 0) {
					nextVex = i;
					break; // -> Dừng khi tìm đc 1 đỉnh kề
				}
			}

			// Thêm đỉnh đó vào stack và xóa khỏi đồ thị
			if (nextVex != -1) {
				stack.push(nextVex);
				matrix[currentVex][nextVex] = 0;
				matrix[nextVex][currentVex] = 0;
			} else {
				// Khi duyệt ra hết các đỉnh -> lần lượt thêm từng đỉnh vào list
				eulerCycle.add(stack.pop());
			}

		}

		System.out.println("Chu trinh Euler: ");
		for (Integer item : eulerCycle) {
			System.out.print(item + 1 + "  ");
		}

	}

	@Override
	public void findEulerCycle(int v) {
		if (checkCycleEuler()) {
			int[][] tempMatrix = matrix;
			Stack<Integer> list = new Stack<>();
			findCycleVer(v, tempMatrix, list);
			for (int i = 0; i < tempMatrix.length; i++) {
				for (int j = 0; j < tempMatrix[i].length; j++) {
					if (tempMatrix[i][j] != 0) {
						return;
					}
				}
			}
			System.out.println("Chu trinh Euler: ");
			for (int i = list.size() - 1; i >= 0; i--) {
				System.out.print(list.elementAt(i) + 1 + " ");
			}
		}
	}

	private void findCycleVer(int i, int temp[][], Stack<Integer> st) {
		for (int j = 0; j < temp.length; j++) {
			if (temp[i][j] != 0 && temp[j][i] != 0) {
				temp[i][j] = temp[j][i] = 0;
				findCycleVer(j, temp, st);
			}
		}
		st.push(i);
	}

	// Viết phương thức tìm đường đi euler
	public void findEulerPath() {
		if (!checkPathEuler()) {
			System.out.println("Do thi khong co duong di Euler");
			return;
		}

		Stack<Integer> stack = new Stack<>();
		List<Integer> eulerPath = new ArrayList<>();
		int startVertex = 0;

		// Tìm đỉnh xuất phát -> đỉnh bậc lẻ
		for (int i = 0; i < num; i++) {
			if (deg(i) % 2 != 0) {
				startVertex = i;
				break;
			}
		}

		stack.push(startVertex);
		while (!stack.isEmpty()) {
			int currentVex = stack.peek();
			int nextVex = -1;
			for (int i = 0; i < num; i++) {
				if (matrix[currentVex][i] != 0) {
					nextVex = i;
					break;
				}
			}

			// Them dinh vao stack va xoa canh
			if (nextVex != -1) {
				stack.push(nextVex);
				matrix[currentVex][nextVex] = 0;
				matrix[nextVex][currentVex] = 0;
			} else {
				eulerPath.add(stack.pop());
			}
		}

		System.out.println("Duong di Euler");
		for (Integer i : eulerPath) {
			System.out.print(i + 1 + " ");
		}
	}

	// Phuong thuc do thi luong phan bang BFS
	public boolean isBipartite(int v) {
		int colors[] = new int[num];
		if (!checkConnect()) {
			System.out.println("Do thi khong lien thong");
			return false;
		}

		for (int i = 0; i < num; i++) {
			colors[i] = -1;
		}

		Queue<Integer> q = new LinkedList<>();
		colors[v] = 1;
		q.add(v);
		while (!q.isEmpty()) {
			int u = q.remove();
			for (int i = 0; i < num; i++) {
				if (matrix[u][i] != 0) {
					if (colors[i] == -1) {
						colors[i] = 1 - colors[u];
						q.add(i);
					} else if (colors[u] == colors[i]) {
						System.out.println("Do thi khong luong phan");
						return false;
					}
				}
			}
		}
		System.out.println("Do thi luong phan");
		return true;
	}

	@Override
	public boolean isHamiltonGraph() {
		if (!checkConnect())
			return false;
		for (int i = 0; i < num; i++) {
			if (deg(i) < num / 2) {
				return false;
			}
		}
		return true;
	}

	// Tim chu trinh duong di Hamilton
	public void hamilton() {
		resetChecked();
		for (int i = 0; i < num; i++) {
			path[i] = -1;
		}

		path[0] = 0;
		checked[0] = true;
		expand(1);
	}

	private void expand(int i) {
		for (int j = 0; j < num; j++) {
			if (matrix[path[i - 1]][j] > 0 && checked[j] == false) {
				path[i] = j;
				if (i < num - 1) {
					checked[j] = true;
					expand(i + 1);
					checked[j] = false;
				} else {
					if (matrix[path[i]][0] != 0) {
						printCycleHamilton(path);
					} else {
						printPathHamilton(path);
					}
				}
			}
		}

	}

	private void printPathHamilton(int[] path) {
		System.out.println("Duong di Hamilton");
		for (int i = 0; i < path.length; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}

	private void printCycleHamilton(int[] path) {
		System.out.println("Chu trinh Hamilton");
		for (int i = 0; i < path.length; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.print(path[0]);
		System.out.println();
	}

	// To mau dô thi
	public void colorGraph() {
		int[] colors = new int[num];
		Arrays.fill(colors, 0);

		for (int i = 0; i < num; i++) {
			if (colors[i] == 0) {
				colors[i] = 1;
				Queue<Integer> queue = new LinkedList<>();
				queue.add(i);

				while (!queue.isEmpty()) {
					int u = queue.poll();

					for (int v = 0; v < num; v++) {
						if (matrix[u][v] == 1) {
							if (colors[v] == colors[u]) {
								colors[v] = colors[u] + 1;
							}
							if (colors[v] == 0) {
								colors[v] = colors[u] == 1 ? 2 : 1;
								queue.add(v);
							}
						}
					}
				}
			}
		}

		System.out.println("Mau cua cac dinh:");
		for (int i = 0; i < num; i++) {
			System.out.println("Dinh " + i + ": Mau " + colors[i]);
		}
	}

	public int soMauTo(int[][] m) {
		int[] colors = new int[num];
		Arrays.fill(colors, 0);

		for (int i = 0; i < num; i++) {
			if (colors[i] == 0) {
				colors[i] = 1;
				Queue<Integer> queue = new LinkedList<>();
				queue.add(i);

				while (!queue.isEmpty()) {
					int u = queue.poll();

					for (int v = 0; v < num; v++) {
						if (matrix[u][v] == 1) {
							if (colors[v] == colors[u]) {
								colors[v] = colors[u] + 1;
							}
							if (colors[v] == 0) {
								colors[v] = colors[u] == 1 ? 2 : 1;
								queue.add(v);
							}
						}
					}
				}
			}
		}
		return colors.length;
	}

	public int[][] BFSTree(int startVertex) {
		if (!checkConnect()) {
			System.out.println("Do thi khong lien thong");
			return null;
		}

		resetChecked();
		int count = 0;
		int[][] spanningTree = new int[num][num];
		Queue<Integer> queue = new LinkedList<Integer>();

		checked[startVertex] = true;
		count++;
		queue.add(startVertex);

		while (!queue.isEmpty() && count < num) {
			int currentVertex = queue.remove();
			for (int i = 0; i < num; i++) {
				if (matrix[currentVertex][i] != 0 && !checked[i]) {
					addEdge(spanningTree, currentVertex, i);
					checked[i] = true;
					queue.add(i);
					count++;
				}
			}
		}

		// Print the spanning tree
		System.out.println("Cay bao trum bat dau tu dinh " + startVertex + " (su dung bfs):");
		printMatrixTree(spanningTree);
		return spanningTree;
	}

	// De quy
	public int[][] SpanningTreeByDFS(int v) {
		resetChecked();
		if (!checkConnect(v)) {
			System.out.println("Do thi khong lien thong");
			return null;
		}

		int result[][] = new int[num][num];
		dfs(v, result);

		System.out.println("Cay bao trum bat dau tu dinh " + v);
		printMatrixTree(result);
		return result;
	}

	private void dfs(int u, int[][] result) {
		checked[u] = true;

		for (int i = 0; i < num; i++) {
			if (matrix[u][i] != 0 && !checked[i]) {
				addEdge(result, u, i);
				dfs(i, result);
			}
		}
	}

	// KhuDeQuy
	public int[][] SpanningTreeByDFS2(int v) {
		resetChecked();
		if (!checkConnect(v)) {
			System.out.println("Do thi khong lien thong");
			return null;
		}

		int result[][] = new int[num][num];
		Stack<Integer> stack = new Stack<>();
		stack.push(v);

		while (!stack.isEmpty()) {
			int u = stack.pop();

			if (!checked[u]) {
				checked[u] = true;

				for (int i = num - 1; i >= 0; i--) {
					if (matrix[u][i] != 0 && !checked[i]) {
						addEdge(result, u, i);
						stack.push(i);
					}
				}
			}
		}

		System.out.println("Cay bao trum bat dau tu dinh " + v);
		printMatrixTree(result);
		return result;
	}

	// Phuong thuc kiem tra cay chu trinh co chu trinh hay khong
	@Override
	public boolean checkCycle(int v1, int v2) {
		if (!checkConnect()) {
			System.out.println("Do thi khong lien thong");
			return false;
		}

		return hasPathDFS(v1, v2);
	}

	private boolean hasPathDFS(int v1, int v2) {
		resetChecked();
		if (v1 == v2)
			return true;
		checked[v1] = true;
		for (int i = 0; i < num; i++) {
			if (matrix[v1][i] == 1 && !checked[i]) {
				if (hasPathDFS(i, v2)) {
					return true;
				}
			}
		}

		return false;
	}

	// Cau 30
	public void makeSet() {
		for (int i = 0; i < num; i++) {
			parent[i] = i;
		}
	}

	public int find(int v) {
		if (v == parent[v])
			return v;
		return parent[v] = find(parent[v]);
	}

	@Override
	public int[][] SpanningTreeByKruskal() {
		int[][] result = new int[num][num];
		if (!checkConnect()) {
			System.out.println("Do thi k lien thong");
			return null;
		}

		List<Edge> list = new ArrayList<Edge>();
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (matrix[i][j] != 0) {
					list.add(new Edge(i, j, matrix[i][j]));
				}
			}
		}
		Collections.sort(list, new SortW());

		makeSet();
		int countEdge = 0;
		int indexList = 0;
		int sumTree = 0;

		while (countEdge < num && indexList < list.size()) {
			Edge e = list.get(indexList);
			int u = parent[e.u], v = parent[e.v];
			if (u != v) {
				countEdge++;
				result[e.u][e.v] = result[e.v][e.u] = e.w;
				System.out.printf("(%d, %d) - %d\n", e.u, e.v, e.w);
				sumTree += e.w;

				for (int i = 0; i < num; i++) {
					if (parent[i] == v) {
						parent[i] = u;
					}
				}
			}
			indexList++;
		}

		System.out.println("Trong so cua cay bao trum: " + sumTree);
		return result;
	}

	@Override
	public int[][] SpanningTreeByPrim(int v) {
		int[][] result = new int[num][num];
		if (!checkConnect()) {
			System.out.println("Do thi khong lien thong");
			return null;
		}

		resetChecked();

		Queue<Edge> pq = new LinkedList<>();

		checked[v] = true;

		for (int i = 0; i < num; i++) {
			if (matrix[v][i] != 0) {
				pq.add(new Edge(v, i, matrix[v][i]));
			}
		}

		int edgeCount = 0;
		int totalWeight = 0;

		while (!pq.isEmpty() && edgeCount < num - 1) {
			Edge e = pq.poll();

			int u = e.u;
			int w = e.v;
			int weight = e.w;

			// Nếu đỉnh đích chưa được thăm
			if (!checked[w]) {
				checked[w] = true;
				result[u][w] = result[w][u] = weight; // Cập nhật ma trận kết quả
				totalWeight += weight; // Tăng tổng trọng số
				edgeCount++;

				// Thêm tất cả các cạnh kề của đỉnh mới vào hàng đợi ưu tiên
				for (int i = 0; i < num; i++) {
					if (matrix[w][i] != 0 && !checked[i]) {
						pq.add(new Edge(w, i, matrix[w][i]));
					}
				}
			}
		}

		// In tổng trọng số của cây bao trùm
		System.out.println("Tong trong so cua cay bao trum: " + totalWeight);
		printSpanningTree(result);
		return result;
	}
	
	public void printSpanningTree(int[][] spanningTree) {
	    System.out.println("Cay bao trum:");
	    for (int i = 0; i < num; i++) {
	        for (int j = i + 1; j < num; j++) {
	            if (spanningTree[i][j] != 0) {
	                System.out.printf("(%d, %d) - Trong so: %d\n", i, j, spanningTree[i][j]);
	            }
	        }
	    }
	}
}
