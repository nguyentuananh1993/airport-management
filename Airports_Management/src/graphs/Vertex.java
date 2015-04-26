package graphs;

import fundamentals.MyComparable;

public class Vertex implements MyComparable<Vertex> {

	private String name;
	
	public Vertex(String n) {
		name = n; }
	
	public String getName() {
		return name; }

	public String toString() {
		return name.toString();	}
	
	@Override
	public int compareTo(Vertex v) {
		return name.compareTo(v.name); }

	@Override
	public int compareTo(Vertex v, int option) {
    	throw new UnsupportedOperationException(); }

	@Override
	public Double value(int option) {
		throw new UnsupportedOperationException(); }

}
