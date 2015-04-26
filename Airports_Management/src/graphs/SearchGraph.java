package graphs;

import java.util.TreeMap;

import fundamentals.MyComparable;
import fundamentals.Global;
import fundamentals.MyLinkedList;

public class SearchGraph<VertexLabel extends MyComparable<VertexLabel>, EdgeLabel extends MyComparable<EdgeLabel>>
extends Graph<VertexLabel, EdgeLabel> {

	private Double length[];
	protected TreeMap<Integer, MyLinkedList<Integer>> mark;
	private int choice = 0;
	protected int flag = 0;
	protected int verticeslimit = 2;
	private int degree[];
	
	public static class Node<VertexLabel> implements java.lang.Comparable<Node<VertexLabel>> {
		private Integer vertex;
		private VertexLabel vertexlabel;
		
		public Node(int v, VertexLabel vl) {
			setVertex(v);
			setVertexLabel(vl);	}
		
		public void setVertex(int v) {
			vertex = v;	}
		
		public void setVertexLabel(VertexLabel vl) {
			vertexlabel = vl; }
		
		public Integer getVertex() {
			return vertex; }
		
		public VertexLabel getVertexLabel() {
			return vertexlabel;	}

		@Override
		public int compareTo(Node<VertexLabel> vl) {
			return getVertex().compareTo(vl.getVertex()); }
	}
	
	public static class Path<VertexLabel> implements java.lang.Comparable<Path<VertexLabel>> {
		private Double length;
		private MyLinkedList<Node<VertexLabel>> path;
		
		public Path() {
			path = new MyLinkedList<Node<VertexLabel>>();	}
		
		public Path(Double l, MyLinkedList<Node<VertexLabel>> p) {
			setLength(l);
			setPath(p); }
		
		public void setLength(Double l) {
			length = l; }
		
		public void setPath(MyLinkedList<Node<VertexLabel>> p) {
			path = p; }
		
		public Double getLength() {
			return length; }
		
		public MyLinkedList<Node<VertexLabel>> getPath() {
			return path; }

		@Override
		public int compareTo(Path<VertexLabel> p) {
			return getLength().compareTo(p.getLength()); }
	}


	public void setChoice(int c) {
		choice = c; }

	public void setVerticesLimit(int v) {
		if(v <= Global.VERTICES_LIMIT) verticeslimit = v; 
		else verticeslimit = Global.VERTICES_LIMIT; }

	@Override
	public EdgeLabel weightEdge(int v1, int v2) {
		TreeMap<Integer, EdgeLabel> sub;
		sub = graph.get(v1);
		if(sub == null) return null; 
		else if(sub.containsKey(v2)) return sub.get(v2);
		else return null; }
	
	protected Double toDoubleWeight(EdgeLabel e) {
		if(e == null) return Global.INFINITY;
		else return e.value(choice); }
	
	@SuppressWarnings("unchecked")
	protected int Path(int vertex, int select) {
		MyLinkedList<Integer> indegree[], found, parents, queue;
		int flag, i, dem;
		indegree = new MyLinkedList[Global.REF_LIMIT];
		int visited[] = new int[Global.REF_LIMIT];
		length = new Double[Global.REF_LIMIT];
		degree = new int[Global.REF_LIMIT];
		mark = new TreeMap<Integer, MyLinkedList<Integer>>();
		queue = new MyLinkedList<Integer>();
		for(int v : graph.keySet())
			indegree[v] = (MyLinkedList<Integer>) Indegree(v);

		if(!graph.containsKey(vertex)) return 0;
		mark.put(vertex, new MyLinkedList<Integer>());
		degree[vertex] = 1;
		visited[vertex] = 2; length[vertex] = 0.0; dem = 1;
		
		while(true) {
			for(int v : mark.keySet()) {
				found = (MyLinkedList<Integer>) Outdegree(v);
				if(found == null) continue;
				for(int u : found) {
					if(indegree[u].contains(v) && u != vertex) {
						queue.addLast(u);
						if(degree[u] == 0) degree[u] = degree[v] + 1;
						if(visited[u] == 0) visited[u] = 1; } } }
			if(queue.isEmpty()) break;
			flag = 0;
			for(int v : queue) {
				for(int u : mark.keySet()) {
					if(toDoubleWeight(weightEdge(u, v)) < Global.INFINITY) {
						if(flag != 0) {
							flag = 1; } } } }
			
			int v;
			while(!queue.isEmpty()) {
				v = queue.getFirst();
				queue.pollFirst();
				if(visited[v] != 2) parents = new MyLinkedList<Integer>();
				else parents = mark.get(v);
				for(int u : mark.keySet()) {
					if(toDoubleWeight(weightEdge(u, v)) < Global.INFINITY) {
						if(indegree[v].contains(u)) {
							indegree[v].remove(new Integer(u));
							parents.addLast(u); } } }
				if(parents.isEmpty()) parents = null;
				else {
					if(visited[v] != 2) mark.put(v, parents);
					visited[v] = 2; dem++; } }
			for(i = 0; i <= Global.REF_LIMIT-1; i++) if(visited[i] == 1) visited[i] = 0; }
		
		return dem;	}
	
	@Deprecated
	protected int Dijkstra(int vertex) throws CloneNotSupportedException {
		MyLinkedList<Integer> notvisit, wait, parents;
		int dem, i;
		int visited[] = new int[Global.REF_LIMIT];
		Double min;
		length = new Double[Global.REF_LIMIT];
		mark = new TreeMap<Integer, MyLinkedList<Integer>>(); notvisit = new MyLinkedList<Integer>(); dem = 1;
		
		for(int v : graph.keySet()) {
			if(v == vertex) {
				length[v] = 0.0;
				mark.put(v, new MyLinkedList<Integer>());
				visited[v] = 1; }
			else {
				length[v] = toDoubleWeight(weightEdge(vertex, v));
				parents = new MyLinkedList<Integer>(); parents.addLast(vertex);
				mark.put(v, parents);
				notvisit.addLast(v); } }

		while(!notvisit.isEmpty()) {
			min = Global.INFINITY; wait = new MyLinkedList<Integer>();
			int v;
			i = 0;
			while(i <= notvisit.size() - 1) {
				v = notvisit.get(i);
				if(length[v] < min) {
					for(int w : wait)
						notvisit.addLast(w);
					wait = new MyLinkedList<Integer>();
					min = length[v];
					wait.addLast(v);
					notvisit.remove(i);
					i--; }
				else if(length[v] == min && length[v] < Global.INFINITY) {
					wait.addLast(v);
					notvisit.remove(i);
					i--; }
				i++; }

			if(wait.isEmpty()) {
				i = 0;
				while(i <= notvisit.size() - 1) {
					v = notvisit.get(i);
					mark.remove(v);	
					i++; }
				break; }
			else {
				while(!wait.isEmpty()) {
					visited[wait.getFirst()] = 1;
					wait.pollFirst();
					dem++; }
				i = 0;
				while(i <= notvisit.size() - 1) {
					v = notvisit.get(i);
					parents = mark.get(v);
					for(int u : mark.keySet()) {
						if(visited[u] != 0 && toDoubleWeight(weightEdge(u, v)) < Global.INFINITY) {
							if(length[v] > length[u] + toDoubleWeight(weightEdge(u, v))) {
								parents = new MyLinkedList<Integer>();
								length[v] = length[u] + toDoubleWeight(weightEdge(u, v));
								parents.addLast(u);
								mark.put(v, parents); }
							else if(length[v] == length[u] + toDoubleWeight(weightEdge(u, v)))
								if(!parents.contains(u)) parents.addLast(u); } }
					i++; } } }

		return dem; }

	protected void FindPath
	(int v1, int v2, Path<VertexLabel> path, int visited[], EdgeLabel weight, Double length, 
			MyLinkedList<Path<VertexLabel>> list, int select) throws CloneNotSupportedException {
		MyLinkedList<Integer> parents;
		Path<VertexLabel> newpath;
		int v;
		EdgeLabel w;
		
		parents = new MyLinkedList<Integer>();
		if(flag == 0) {
			mark = new TreeMap<Integer, MyLinkedList<Integer>>();
			if(select >= 0) {if(Path(v1, select) == 0) return; }
			else if(select == -1) {if(Dijkstra(v1) == 0) return; }
			flag = 1; }
		else {}
		if(!mark.containsKey(v2)) return;
		parents.addAll(mark.get(v2));
		
		if(path.getPath().isEmpty()) {
			path.getPath().addFirst(new Node<VertexLabel>(v2,getVertexLabel(v2)));
			visited[v2] = 1; }
		if(weight != null) length += toDoubleWeight(weight);
		if(path.getPath().size() <= verticeslimit - 1) {
			while(!parents.isEmpty()) {
				v = parents.getFirst();
				parents.pollFirst();
				if(visited[v] == 1) continue; //???
				path.getPath().addFirst(new Node<VertexLabel>(v,getVertexLabel(v)));
				visited[v] = 1;
				w = ((EdgeLabel)weightEdge(v, v2));
				deleteEdge(v, v2);
				FindPath(v1, v, path, visited, w, length, list, select); } }
		
		if(path.getPath().getFirst().getVertex() == v1) {
			newpath = new Path<VertexLabel>();
			for(Node<VertexLabel> n : path.getPath()) { 
				newpath.getPath().addLast(n); }
			newpath.setLength(length);
			list.addLast(newpath); }
		if(weight != null) {
			if(toDoubleWeight(weight) > 0) {
				length -= toDoubleWeight(weight);
				setEdge(path.getPath().getFirst().getVertex(), path.getPath().get(1).getVertex(), weight); } }
		visited[path.getPath().getFirst().getVertex()] = 0;
		path.getPath().pollFirst(); }

	@Deprecated
	public MyLinkedList<Path<VertexLabel>> ListofDijkstra(int v1, int v2) 
			throws CloneNotSupportedException {
		MyLinkedList<Path<VertexLabel>> list;
		Path<VertexLabel> path;
		int visited[] = new int[Global.REF_LIMIT];
		Double length = 0.0;
		list = new MyLinkedList<Path<VertexLabel>>(); 
		path = new Path<VertexLabel>();
		flag = 0;
		FindPath(v1, v2, path, visited, null, length, list, -1);
		return list; }
	
	public MyLinkedList<Path<VertexLabel>> ListofPath(int v1, int v2, int select) 
			throws CloneNotSupportedException {
		MyLinkedList<Path<VertexLabel>> list;
		Path<VertexLabel> path; 
		int visited[] = new int[Global.REF_LIMIT];
		Double length = 0.0;
		list = new MyLinkedList<Path<VertexLabel>>();
		path = new Path<VertexLabel>();
		flag = 0;
		FindPath(v1, v2, path, visited, null, length, list, select);
		return list; }
	
	public MyLinkedList<Path<VertexLabel>> ListofPath(int v1, int v2) 
			throws CloneNotSupportedException {
		return ListofPath(v1, v2, 0); }


}
