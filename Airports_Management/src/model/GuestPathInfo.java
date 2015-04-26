package model;

import fundamentals.MyLinkedList;
import graphs.SearchGraph;
import graphs.SearchGraph.Node;
import graphs.Vertex;

public class GuestPathInfo implements Comparable<GuestPathInfo>{
	private Double length;
	private MyLinkedList<SearchGraph.Node<Vertex>> path;
	private MyLinkedList<String> pathstring;
	
	public GuestPathInfo(Double l, MyLinkedList<Node<Vertex>> p) {
		setLength(l);
		setPath(p); }
	
	public void setLength(Double l) {
		length = l;	}
	
	public void setPath(MyLinkedList<Node<Vertex>> p) {
		path = p; }
	
	public void setPathstring() {
		if(pathstring == null) {
			pathstring = new MyLinkedList<String>();
			for(Node<Vertex> i : path) {
				pathstring.addLast(i.getVertexLabel().getName()); } } }
	
	public Double getLength() {
		return length; }
	
	public MyLinkedList<Node<Vertex>> getPath() {
		return path; }
	
	public MyLinkedList<String> getPathstring() {
		setPathstring();
		return pathstring; }

	@Override
	public int compareTo(GuestPathInfo p) {
		int c = getPath().compareTo(p.getPath());
		if(c != 0) return c;
		else return path.compareTo(p.getPath()); }
	
}