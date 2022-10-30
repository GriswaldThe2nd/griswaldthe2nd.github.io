
public class BinaryTree
{
	private class Node{
		public String data;
		public Node yes;
		public Node no;

		public Node(String data, Node yes, Node no) {
			this.data = data;
			this.yes = yes;
			this.no = no;
		}
	}

	private Node root;

	public BinaryTree()
	{
		root = null;
	}

	public String get(String location)
	{
		if(location.equals(""))
			return root.data;
		Node ptr = root;
		for(char c : location.toCharArray())
		{
			if(c == 'y' || c == 'Y')
				ptr = ptr.yes;
			else
				ptr = ptr.no;
		}
		return ptr.data;
	}
	
	public void add(String data, String directions) {
		if(root == null || directions.isEmpty()) { //directions.equals("")
			root = new Node(data, null, null);
		}
		else {
			Node ptr = root;
			String parentPart = directions.substring(0, directions.length()-1);
			String childPart = directions.substring(directions.length()-1);
			for(char c:parentPart.toCharArray()) {
				if(c == 'Y' || c =='y')
					ptr = ptr.yes;

				else if(c == 'N' || c == 'n' )
					ptr=ptr.no;
			}
			if (childPart.charAt(0)=='y' || childPart.charAt(0)=='Y') {
				ptr.yes=new Node(data, null, null);
			}
			else {
			}
		}
	}
}
