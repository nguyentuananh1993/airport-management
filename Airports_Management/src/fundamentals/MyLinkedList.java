package fundamentals;

public class MyLinkedList<Item extends java.lang.Comparable<Item>> extends java.util.LinkedList<Item>
implements java.lang.Comparable<MyLinkedList<Item>> {

	private static final long serialVersionUID = 1L;
	
	public String toString() {
		String s = new String();
		for(int i = 0; i <= size() -1 ; i++) {
			s = s + get(i);	
			if(i != size() - 1) s = s + " - ";}
		return s; }

	@Override
	public int compareTo(MyLinkedList<Item> l) {
    	Integer i;
    	Integer s1,s2;
    	Item i1,i2;
    	s1 = this.size();
    	s2 = l.size();
    	if(s1 != s2) {
    		return s1.compareTo(s2); }
    	else {
    		i = 0;
    		while(i <= s1 - 1) {
    			i1 = this.get(i);
    			i2 = l.get(i);
    			if(i1.compareTo(i2) != 0) {
    				return i1.compareTo(i2); }
    			i++; } }
    	return 0; }

	
}
