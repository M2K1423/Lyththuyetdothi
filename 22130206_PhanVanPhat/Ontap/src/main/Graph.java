package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class Graph {
	protected int num;
	protected int [][] matrix;
	protected boolean[] checked;
	protected static String filePath = "D:\\Java\\LTDT\\Ontap\\Duong_Di_Ngan_Nhat.txt";
	protected int[] path;
	protected int[] parent;
	
	public Graph() {
		
	}
	
	//Cau1 - Viết phương thức đọc ma trận kề từ một file 
	public boolean loadGraph(String pathFile) throws IOException {
		File file = new File(pathFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String firstLine = br.readLine();
		
		this.num = Integer.parseInt(firstLine);
		this.checked = new boolean[num];
		this.matrix = new int[num][num];
		this.path = new int[num];
		this.parent = new int[num];
		String line = "";
		int indexRow = 0;
		
		while( (line=br.readLine()) != null) {
			String[] arrLine = line.split("\\s+");
			for (int i = 0; i < num; i++) {
				this.matrix[indexRow][i] = Integer.parseInt(arrLine[i]);
			}
			indexRow++;
		}
		br.close();
		return true;
	}
	
	//Cau2 - Viet phuong thuc in ra ma tran ke cua do thi
	public void printMatrix() {
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//Cau 3 - Viet phuong thuc kiem tra do thi co hop ly khong ( Co huong hoc vo huong )
	public boolean checkValid() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[i][j] < 0 || matrix[i][j] != matrix[j][i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Cau 4 - Kiểm tra đồ thị có phải vô hướng hay không
	public abstract boolean checkkUnGraph();
	
	//Câu 5 - Viết phương thức thêm một cạnh vào đồ thị
	public abstract void addEdge(int[][] matrix, int v1, int v2); 
	
	//Cau 6- Viet phuong thuc xoa mot canh do thi
	public abstract void removeEdge(int[][] matrix, int v1, int v2); 
	
	
	//Câu 7 - Tinh tông bac cua moi dinh
	public abstract int deg(int v);
	
	//Câu 8 - Viet phuong thuc tinh tong bac cua do thi
	public abstract int sumDeg();
	
	//Câu 9
	public int numVertexs() {
		return num;
	}
	
	//Cau 10
	public abstract int numEdges();
	
	//Cau11
	public abstract boolean checkConnect();

	public boolean checkConnect(int start) {
		// TODO Auto-generated method stub
		return false;
	}

	public int countConnect() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Câu 13
	public abstract void BFSGraph(int startVexs);
	
	//Câu 14
	public abstract void DFSGraph(int startVexs);
	
	//
	public abstract void checkEuler();

	public abstract void findEulerCycle(int v);
	
	//Cau 22
	public abstract boolean isHamiltonGraph();
	
	public void printMatrixTree(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//Cau29 - Pthuc xd cay bao trum có chu trinh hay khong
	public abstract boolean checkCycle(int v1, int v2);
	
	//Cau 30 - Phthuc tim cay bao trum co trong so nho nhat bang thuat toan Kruskal
	public abstract int[][] SpanningTreeByKruskal();
	
	//Cau 31 - Pthuc tim cay bao trum co trong so nho nhat bang thuat toan Prim
	public abstract int[][] SpanningTreeByPrim(int v);
	
	class Edge {
		int u,v,w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
		public Edge() {
			
		}
	}
	
	
	class SortW implements Comparator<Edge> {
	    @Override
	    public int compare(Edge o1, Edge o2) {
	        return Integer.compare(o1.w, o2.w);
	    }
	}


}
