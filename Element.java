
import java.util.Comparator;

	public class Element {
	public Integer key;
	public Double distance;
	public Element(int key, Double distance) {
	    this.key = key;
	    this.distance = distance;

	}
	public Element() {
	}
	public int getkey(){
	    return key;
	}    
	public Double getdistance() {
	    return distance;
	}

}
	class ElementComparator implements Comparator<Element>{
		public int compare(Element o1, Element o2) {
		    Element a1 = (Element)o1;
		    Element a2 = (Element)o2;
		    if(a1.distance>a2.distance) {
		        return 1;
		    }
		    else if(a1.distance<a2.distance)
		    	return -1; 
		    return 0;

		}
	}
