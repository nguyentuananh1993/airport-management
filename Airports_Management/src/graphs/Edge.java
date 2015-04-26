package graphs;

import fundamentals.MyComparable;

public class Edge implements MyComparable<Edge> {

	private Double distance = 1.0;
	
	public Edge(Double d) {
		distance = d; }
	
	public Double weight(int option) {
		return distance; }
	
	public String toString() {
		return distance.toString(); }
	
	@Override
	public int compareTo(Edge e, int option) {
		if(distance > e.distance) return 1;
		else if(distance == e.distance) return 0;
		else return -1; }

	@Override
	public int compareTo(Edge v) {
		throw new UnsupportedOperationException(); }

	@Override
	public Double value(int choice) {
		return distance; }
	
}
