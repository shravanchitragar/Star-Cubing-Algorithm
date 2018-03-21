import java.util.ArrayList;

public class StarTree {
	String attribute="";
	int count=0;
	boolean isLeaf=false;
	boolean hasSibling=false;
	ArrayList<StarTree> children =new ArrayList<StarTree>();
	
	public StarTree() {
		//super();
	}

	public StarTree(int count) {
		//super();
		this.count = count;
	}

	public StarTree(String attribute, int count) {
		//super();
		this.attribute = attribute;
		this.count = count;
	}
	
	
	
	
	
}
