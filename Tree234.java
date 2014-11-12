/* Tree234.java */

package dict;

import java.awt.RenderingHints.Key;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
    // Fill in your solution here.
	  if(!find(key)){
		  if(root == null){	//if there is no node
			  Tree234Node n = new Tree234Node(null,key);
			  root = n;
			  return;
		  }
		  Tree234Node position = root;
		  // loop until position has no child
		  while(true){
			  // if countered a 3-keys node,we break if down
			  if(position.keys == 3){
				  position = TreeBalance(position,key);
			  }
			  // if we countered a 1-key node
			  if(position.keys == 1){
				  if(key < position.key1){
					  if(position.child1!=null){	
						  position = position.child1;
					  }
					  else{
						  plug(position, key);	 
						  return;
					  }
				  }
				  else if(key > position.key1){
					  if(position.child2!=null){	
						  position = position.child2;
					  }
					  else{
						  plug(position, key);	
						  return;
					  }
				  }
	
			  }
			  //if we countered a 2-keys node
			  if(position.keys == 2){
				  if(key < position.key1){
					  if(position.child1!=null){	
						  position = position.child1;
					  }
					  else{
						  plug(position, key);
						  return;
					  }
				  }
				  else if(key > position.key1 && key <position.key2){
					  if(position.child2!=null){	
						  position = position.child2;
					  }
					  else{
						  plug(position, key);	
						  return;
					  }
				  }
				  else if(key > position.key2){
					  if(position.child1!=null){	
						  position = position.child3;
					  }
					  else{
						  plug(position, key);		
						  return;
					  }
				  }
			  }
		  }
		  //until we face a node with no child
		  /*
		  if(position.keys == 1){
			  position.keys++;
			  if(key<position.key1){
				  position.key2 = position.key1;
				  position.key1 = key;
			  }
			  else if(key>position.key1){
				  position.key2 = key;
			  }
		  }
		  else if(position.keys == 2){
			  position.keys++;
			  if(key<position.key1){
				  position.key2 = position.key1;
				  position.key1 = key;
			  }
			  else if(key>position.key2){
				  position.key3 = position.key2;
				  position.key2 = key;
			  }
			  else{
				  p
			  }
		  }
		  */
		  
	  
	  }
}
public int insertion(Tree234Node n,int key){
		if(n.keys==1){
			n.keys++;
			if(key < n.key1){
				n.key2 = n.key1;
				n.key1 = key;
				return 1;
			}
			else{
				n.key2 = key;
				return 2;
			}
		}
		if(n.keys==2){
			n.keys++;
			if(key < n.key1){
				n.key3 = n.key2;
				n.key2 = n.key1;
				n.key1 = key;
				return 1;
			}
			else if(key< n.key2){
				n.key3 = n.key2;
				n.key2 = key;
				return 2;
			}
			else{
				n.key3 = key;
				return 3;
			}
		}
		return 0;
	}
public void plug(Tree234Node n,int key){
	if(n.keys==1){
		n.keys++;
		if(key < n.key1){
			n.key2 = n.key1;
			n.key1 = key;
			if(n.child2!=null){
				n.child3 = n.child2;
				n.child2 = null;
				
			}
			return;
		}
		else{
			n.key2 = key;
			return;
		}
	}
	if(n.keys==2){
			n.keys++;
			if(key < n.key1){
				n.key3 = n.key2;
				n.key2 = n.key1;
				n.key1 = key;
				if(n.child3!=null){
					n.child4 = n.child3;
					n.child3 = null;
				}
				if(n.child2!=null){
					n.child3 = n.child2;
					n.child2 = null;
				}
				return;
			}
			else if(key< n.key2){
				n.key3 = n.key2;
				n.key2 = key;
				if(n.child3!=null){
					n.child4 = n.child3;
				}
				return;
			}
			else{
				n.key3 = key;
				return;
			}
		}
	}

public Tree234Node TreeBalance(Tree234Node position,int key){

	  Tree234Node n1 = new Tree234Node(position.parent,position.key1);
	  Tree234Node n2 = new Tree234Node(position.parent,position.key3);
	  if(position.child1!=null){	
		  n1.child1 = position.child1;
		  position.child1.parent = n1;
		  }
	  if(position.child2!=null){	
		  n1.child2 = position.child2;
		  position.child2.parent = n1;
		  }
	  if(position.child3!=null){	
		  n2.child1 = position.child3;
		  position.child3.parent = n2;
		  }
	  if(position.child4!=null){	
		  n2.child2 = position.child4;
		  position.child4.parent = n2;
		  }
	  if(position!=root){
		  int index = insertion(position.parent, position.key2);
		  if(index==1){
			  if(position.parent.child3!=null){
				  position.parent.child4 = position.parent.child3;
			  }
			  position.parent.child3 = position.parent.child2;
			  position.parent.child1 = n1;
			  position.parent.child2 = n2;
		  }
		  if(index==2){
			  if(position.parent.child3!=null){
				  position.parent.child4 = position.parent.child3;
			  }
			  position.parent.child3 = n2;
			  position.parent.child2 = n1;
		  }
		  if(index==3){
			  position.parent.child4 = n2;
			  position.parent.child3 = n1;
		  }
		  if(key<position.key2){
			  return n1;
		  }
		  else{
			  return n2;
		  }
	  }
	  else{
		  Tree234Node n3 = new Tree234Node(null, position.key2);
		  n1.parent = n3;
		  n2.parent = n3;
		  n3.child1 = n1;
		  n3.child2 = n2;
		  root = n3;
		  if(key<position.key2){
			  return n1;
		  }
		  else{
			  return n2;
		  }
	  }
	  
}

  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
