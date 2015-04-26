package graphs;

import java.util.TreeMap;

import fundamentals.MyComparable;
import fundamentals.MyLinkedList;

public class Graph<VertexLabel extends MyComparable<VertexLabel>, EdgeLabel extends MyComparable<EdgeLabel>> {

	private int V = 0;
	private int E = 0;
	private TreeMap<Integer, VertexLabel> vertexlabel = new TreeMap<Integer, VertexLabel>();
	protected TreeMap<Integer, TreeMap<Integer, EdgeLabel>> graph
		= new TreeMap<Integer, TreeMap<Integer, EdgeLabel>>();	
	
	public int Vertex() {
		return V; }
	
	public int Edge() {
		return E; }
	
	public VertexLabel getVertexLabel(int v) {
		return vertexlabel.get(v); }
	
	public Iterable<Integer> searchVertex(VertexLabel l) {
		MyLinkedList<Integer> q = new MyLinkedList<Integer>();
		for(int v:vertexlabel.keySet()) {
			if(vertexlabel.get(v).compareTo(l) == 0) q.addLast(v); }  
		return q; }
	
	public void addVertex(int v, VertexLabel l) {/* v != 0 */
		if(!vertexlabel.containsKey(v)) {
			V++;
			graph.put(v, new TreeMap<Integer, EdgeLabel>()); } 
		vertexlabel.put(v, l); }
		
	public VertexLabel removeVertex(int v) {
		TreeMap<Integer, EdgeLabel> sub;
		if(vertexlabel.containsKey(v)) V--;
		VertexLabel l = vertexlabel.get(v);
		vertexlabel.remove(v);
		for(int i:graph.keySet()) {
			sub = graph.get(i);
			if(sub.containsKey(v)) {
				E--;
				sub.remove(v); } }
		sub = graph.get(v);
		if(sub != null) {
			E = E - sub.size();
			graph.remove(v); }
		return l; }

	public void setEdge(int v1, int v2, EdgeLabel weight) {
		if(vertexlabel.containsKey(v1) && vertexlabel.containsKey(v2)) {
			if(!graph.get(v1).containsKey(v2)) E++;
			graph.get(v1).put(v2, weight); } }
	
	@SuppressWarnings("unchecked")
	public void setEdge(int v1, Object ... list) {
		int i = 1;
		int v = 0;
		for(Object u:list) {
			if(i % 2 == 1) v = (Integer)u;
			else if(i % 2 == 0) setEdge(v1, v, (EdgeLabel)u);
			i++; } }
	
	public void setDoubleEdge(int v1, int v2, EdgeLabel weight) {
		setEdge(v1, v2, weight);
		setEdge(v2, v1, weight); }

	@SuppressWarnings("unchecked")
	public void setDoubleEdge(int v1, Object ... list) {
		int i = 1;
		int v = 0;
		for(Object u:list) {
			if(i % 2 == 1) v = (Integer)u;
			else if(i % 2 == 0) setDoubleEdge(v1, v, (EdgeLabel)u);
			i++; } }
	
	public void deleteEdge(int v1, int v2) {
		TreeMap<Integer, EdgeLabel> sub;
		sub = graph.get(v1);
		if(sub != null) 
			if(sub.containsKey(v2)) {
				sub.remove(v2);
				E--; } }
	
	public void deleteEdge(int v1, int ... list) {
		for(int u:list) deleteEdge(v1, u); }
	
	public void deleteDoubleEdge(int v1, int v2) {
		deleteEdge(v1, v2);
		deleteEdge(v2, v1); }

	public void deleteDoubleEdge(int v1, int ... list) {
		for(int u:list) deleteDoubleEdge(v1, u); }
	
	public EdgeLabel weightEdge(int v1, int v2) {
		TreeMap<Integer, EdgeLabel> sub;
		sub = graph.get(v1);
		if(sub == null) return null;
		else if(sub.containsKey(v2)) return sub.get(v2); 
		else if(v1 == v2) return null;
		else return null; }
	
	public Iterable<Integer> Outdegree(int v) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		if(graph.containsKey(v))
			for(int i:graph.get(v).keySet()) list.addLast(i);
		return list; }
	
	public Iterable<Integer> Indegree(int v) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		if(graph.containsKey(v)) {
			for(int i:graph.keySet()) {
				if(graph.get(i).containsKey(v)) list.addLast(i); } }
		return list; }
	
	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
		TreeMap<Integer, EdgeLabel> sub;
		
		for(int i:graph.keySet()) {
			s.append(i + ": ");
			sub = graph.get(i);
			for(int j:sub.keySet()) {
				s.append(j + " " + sub.get(j));
				if(j != sub.lastKey()) s.append(", "); }
			if(i != graph.lastKey()) s.append(NEWLINE); }
		return s.toString(); }

}
