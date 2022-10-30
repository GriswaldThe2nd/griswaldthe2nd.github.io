import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BTWork{
	private BinaryTree tree;
	private File file;

	public BTWork() throws FileNotFoundException {
		this.tree=new BinaryTree();
		this.file=new File("animalGuessing.txt");
		if (!file.exists()) {
			this.tree.add("Does you animal have fins?", "");
			this.tree.add("Dolphin", "Y");// Yes case
			this.tree.add("Lion", "N"); // No case
		}
		else {
			Scanner inp=new Scanner(file);
			while(inp.hasNextLine())
			{
				String line = inp.nextLine();
				String [] temp = line.split("]");
				String loc = temp[0].substring(1);
				String data = temp [1];
				this.tree.add(data, loc);
			}
			inp.close();
		}
	}
	public void decon()
	{
		/*
		 * Write tree to file
		 * Make it ok to leave
		 */
	}
}
