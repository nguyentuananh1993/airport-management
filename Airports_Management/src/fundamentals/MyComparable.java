package fundamentals;

public interface MyComparable<I> extends java.lang.Comparable<I> {
	public int compareTo(I i, int option);
	public Double value(int option);
}
