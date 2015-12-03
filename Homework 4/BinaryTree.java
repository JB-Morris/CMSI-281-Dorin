public class BinaryTree implements java.lang.Iterable{
	private Node root;
	private Node cursor;
	private int size;

	// constructs an empty tree
	public BinaryTree(){
		this.root = null;
		this.cursor = this.root;
		this.size = 0;
	}

	// constructs a tree with just a root node decorated with (i.e., referring to) obj
	public BinaryTree(Object obj){
		this.root = new Node(obj);
		this.cursor = this.root;
		this.size = 1;
	}

	// returns true iff the tree contains an object equivalent to obj
	public boolean contains(Object obj){
		for(Object o : this){
			if(obj.equals(o)){
				return true;
			}
		}
		return false;
		
	}

	// returns true iff obj is a similar binary tree- i.e., obj must have identical structure (only)
	public boolean similar(Object obj){
		if(obj instanceof BinaryTree){
			BinaryTree tree = (BinaryTree)obj;
			return compareStructure(this.root, tree.root);
		}
		return false;
		// make stack, use stack to keep track of any un-traversed nodes and return to them and apply the method (recursive??)
	}

	private boolean compareStructure(Node n1, Node n2){
		if(n1.hasLeftSon() && n1.hasRightSon() && n2.hasLeftSon() && n2.hasRightSon()){
			return true && compareStructure(n1.getLeftSon(), n2.getLeftSon()) && compareStructure(n1.getRightSon(), n2.getRightSon());
		}else if(n1.hasLeftSon() && n2.hasLeftSon()){
			return true && compareStructure(n1.getLeftSon(), n2.getLeftSon());
		}else if(n1.hasRightSon() && n2.hasRightSon()){
			return true && compareStructure(n1.getRightSon(), n2.getRightSon());
		}else if(!(n1.hasLeftSon()) && !(n1.hasRightSon()) && !(n2.hasLeftSon()) && !(n2.hasRightSon())){
			return true;
		}else {
			return false;
		}
	}

	// returns true iff obj is an equivalent binary tree- i.e., obj must have identical structure and equivalent objects
	public boolean equals(Object obj){
		if(obj instanceof BinaryTree){
			BinaryTree tree = (BinaryTree) obj;
			return checkEquality(this.root, tree.root);
		}
		return false;
	}

	private boolean checkEquality(Node n1, Node n2){
		if((n1.getData().equals(n2.getData()))){
			if(n1.hasLeftSon() && n1.hasRightSon() && n2.hasLeftSon() && n2.hasRightSon()){
				return true && checkEquality(n1.getLeftSon(), n2.getLeftSon()) && checkEquality(n1.getRightSon(), n2.getRightSon());
			}else if(n1.hasLeftSon() && n2.hasLeftSon()){
				return true && checkEquality(n1.getLeftSon(), n2.getLeftSon());
			}else if(n1.hasRightSon() && n2.hasRightSon()){
				return true && checkEquality(n1.getRightSon(), n2.getRightSon());
			}else if(!(n1.hasLeftSon()) && !(n1.hasRightSon()) && !(n2.hasLeftSon()) && !(n2.hasRightSon())){
				return true;
			}else {
				return false;
			}
		}return false;		
	}

	// should do the obvious thing- and the same for public int size() and public int hashCode()
	public boolean isEmpty(){
		if(this.size == 0){
			return true;
		}
		return false;
	}

	public int size(){
		return this.size;
	}

	public int hashCode(){
		return super.hashCode();
	}

	// should return a preorder iterator over the tree, whereas public Iterator inOrder() should return an inorder iterator over the tree
	public PreIterator iterator(){
		return new PreIterator(this);
	}

	public InIterator inOrder(){
		return new InIterator(this);
	}

	public boolean putCursorAtRoot(){
		if(this.root == null){
			return false;
		}
		this.cursor = this.root;
		return true;
	}

	public boolean putCursorAtLeftSon(){
		if(this.cursor.getLeftSon() != null){
			this.cursor = this.cursor.getLeftSon();	
			return true;
		}
		return false;
	}

	public boolean putCursorAtRightSon(){
		if(this.cursor.getRightSon() != null){
			this.cursor = this.cursor.getRightSon();
			return true;
		}
		return false;
	}

	public boolean putCursorAtFather(){
		if(this.cursor.getFather() != null){
			this.cursor = this.cursor.getFather();
			return true;	
		}
		return false;
		// throw new UnsupportedOperationException("No! That's not true! That's impossible!");
	}

	// returns false if a left son already exists
	public boolean attachLeftSonAtCursor(Object obj){
		if(this.root == null){
			this.root = new Node(obj);
			this.cursor = this.root;
			return true;
		}
		if(!(this.cursor.hasLeftSon())){
			this.cursor.setLeftSon(obj);
			this.size++;
			return true;
		}
		return false;
	}

	// returns false if a right son already exists
	public boolean attachRightSonAtCursor(Object obj){
		if(this.root == null){
			this.root = new Node(obj);
			this.cursor = this.root;
			return true;
		}
		if(!(this.cursor.hasRightSon())){
			this.cursor.setRightSon(obj);
			this.size++;
			return true;
		}
		return false;
	}

	// removes everything that descends from the cursor, including the node to which the cursor refers, then relocates the cursor to the root node, returning true iff something (including the cursor) changed
	public boolean pruneFromCursor(){
		Node current = this.cursor;
		this.cursor = this.cursor.getFather();
		int subtract = countFromCursor(current);
		current.clear();
		this.size = this.size - subtract;
		return true;
	}

	private int countFromCursor(Node n){
		if(n.hasLeftSon() && n.hasRightSon()){
			return 1 + countFromCursor(n.getLeftSon()) + countFromCursor(n.getRightSon());
		}else if(n.hasLeftSon()){
			return 1 + countFromCursor(n.getLeftSon());
		}else if(n.hasRightSon()){
			return 1 + countFromCursor(n.getRightSon());
		}else{
			return 1;
		}
	}

	private static void test(boolean b){
		if(b){
			System.out.println("Pass");
			return;
		}
		System.out.println("FAIL");
		return;
	}

	public static void main(String args[]){
		BinaryTree testTree = new BinaryTree();
		BinaryTree testTree2 = new BinaryTree(0);
		int[] testArr = new int[1];
		int testCounter = 0;


		System.out.println("Testing iterator (basic) and constructor");
		for(Object o : testTree2){
			testArr[testCounter] = (Integer)o;
			testCounter++;
		}
		int[] testArr2 = new int[1];
		testArr2[0] = 0;
		testCounter = 0;
		test(java.util.Arrays.equals(testArr, testArr2));

		System.out.println("Testing isEmpty()");

		test(testTree.isEmpty());

		test(!(testTree2.isEmpty()));

		System.out.println("Testing contains()");

		test(testTree2.contains(0));

		System.out.println("Testing testing similar() and equals()");

		testTree.attachLeftSonAtCursor(0);
		testTree.attachLeftSonAtCursor(1);
		testTree.attachRightSonAtCursor(2);
		testTree2.attachLeftSonAtCursor(1);
		testTree2.attachRightSonAtCursor(2);

		test(testTree.similar(testTree2));

		test(testTree.equals(testTree2));

		testTree.putCursorAtLeftSon();
		testTree.attachLeftSonAtCursor(3);
		testTree2.putCursorAtLeftSon();
		testTree2.attachRightSonAtCursor(3);

		test(!(testTree.similar(testTree2)));
		test(!(testTree.equals(testTree2)));

		System.out.println("Testing testing pre order iterator");

		testTree.attachRightSonAtCursor(4);
		testTree2.attachLeftSonAtCursor(4);
		boolean testBool = true;
		int[] preOrderArray = new int[]{0,1,3,4,2};
		for(Object o : testTree){
			if(!(o.equals(preOrderArray[testCounter]))){
				testBool = false;
			}
			testCounter++;
		}
		test(testBool && (testCounter == 5));
		testBool = true;
		testCounter = 0;
		preOrderArray = new int[]{0,1,4,3,2};

		for(Object o : testTree2){
			if(!(o.equals(preOrderArray[testCounter]))){
				testBool = false;
			}
			testCounter++;
		}
		test(testBool && (testCounter == 5));
		testBool = true;
		testCounter = 0;
		
		System.out.println("Testing testing in order iterator");

		int[] inOrderArray = new int[]{3,1,4,0,2};
		InIterator iNterator = testTree.inOrder();

		while(iNterator.hasNext()){
			// System.out.println(iNterator.next());
			if(!(iNterator.next().equals(inOrderArray[testCounter]))){
				testBool = false;
			}
			testCounter++;
		}
		test(testBool && (testCounter == 5));
		testCounter = 0;
		testBool = true;
		iNterator = testTree2.inOrder();
		inOrderArray = new int[]{4,1,3,0,2};

		while(iNterator.hasNext()){
			if(!(iNterator.next().equals(inOrderArray[testCounter]))){
				testBool = false;
			}
			testCounter++;	
		}
		test(testBool && (testCounter == 5));


	}

	private class Node{
		private Node father;
		private Node rightSon;
		private Node leftSon;
		private Object data;

		public Node(Object obj){
			this.data = obj;
			this.father = null;
			this.rightSon = null;
			this.leftSon = null;
		}

		public void setFather(Node f){
			this.father = f;
		}

		public void setRightSon(Node rs){
			this.rightSon = rs;
			rs.setFather(this);
		}

		public void setRightSon(Object obj){
			this.rightSon = new Node(obj);
			this.rightSon.setFather(this);
		}

		public void setLeftSon(Node ls){
			this.leftSon = ls;
			ls.setFather(this);
		}

		public void setLeftSon(Object obj){
			this.leftSon = new Node(obj);
			this.leftSon.setFather(this);
		}

		public void clear(){
			Node f = this.getFather();
			if(f.getLeftSon() == this){
				f.clearLeftSon();
			}else {
				f.clearRightSon();
			}
			this.leftSon = null;
			this.rightSon = null;
			this.father = null;
		}

		public void clearFather(){
			this.father = null;
		}

		public void clearRightSon(){
			this.rightSon = null;
		}

		public void clearLeftSon(){
			this.leftSon = null;
		}

		public boolean hasFather(){
			if(this.father == null){
				return false;
			}
			return true;
		}

		public boolean hasRightSon(){
			if(this.rightSon == null){
				return false;
			}
			return true;
		}

		public boolean hasLeftSon(){
			if(this.leftSon == null){
				return false;
			}
			return true;
		}

		public Object getData(){
			return this.data;
		}

		public Node getFather(){
			return this.father;
		}

		public Node getRightSon(){
			return this.rightSon;
		}

		public Node getLeftSon(){
			return this.leftSon;
		}

	}

	private class PreIterator implements java.util.Iterator{
		// PREorder traversal
		private java.util.Stack<Node> stack = new java.util.Stack<Node>();

		public PreIterator(BinaryTree tree){
			stack.push(tree.root);
			if(tree.root.equals(null)){
				throw new IllegalStateException("Tree is empty.");
			}
		}
		public boolean hasNext(){
			// if(this.current.hasLeftSon() || this.current.hasRightSon() || !(this.stack.isEmpty())) {
			// 	return true;
			// }
			// return false;

			// if(tree.size() - iterations <= 0){
			// 	return false;
			// }
			// return true;

			return !stack.isEmpty();

			// if p has two subtrees, push p right son onto stack
			// if p has only left (right) subtree, p = p left son (respectively p = p right son)
			// p has no subtrees, if stack is not empty, p = stack.pop()
		}

		public Object next(){
			if(!(this.hasNext())){
				throw new IllegalStateException("No more nodes");
			}

			Node result = stack.pop();
			if(result.hasRightSon()){
				stack.push(result.getRightSon());
			}
			if(result.hasLeftSon()){
				stack.push(result.getLeftSon());
			}
			return result.getData();



			// Node next = this.current;
			// if(this.current.hasLeftSon() && this.current.hasRightSon()){
			// 	stack.push(current.getRightSon());
			// 	this.current = current.getLeftSon();
			// }else if(this.current.hasLeftSon()){
			// 	this.current = this.current.getLeftSon();
			// }else if(this.current.hasRightSon()){
			// 	this.current = this.current.getRightSon();
			// 	// possible infinite loop, must include way to mark nodes as traversed
			// }else{
			// 	if(stack.empty()){
			// 		this.current = stack.pop();
			// 	}
			// }
			// return next;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}

	}

	// private class InIterator implements java.util.Iterator{
	// 	// INorder traversal (symetric order)
	// 	private Node next;

	// 	public InIterator(BinaryTree tree){
	// 		this.next = tree.root;
	// 		if(this.next)
			
	// 	}

	// 	public boolean hasNext(){
	// 		return next != null;
	// 	}

	// 	public Object next(){
			
	// 	}

	// 	public void remove(){
	// 		throw new UnsupportedOperationException();
	// 	}

	// }

	private class InIterator implements java.util.Iterator{
		// INorder traversal (symetric order)
		private Node next;

		public InIterator(BinaryTree tree){
			this.next = tree.root;
			if(this.next == null){
				return;
			}
			while(this.next.hasLeftSon()){
				next = next.getLeftSon();
			}
		}

		public boolean hasNext(){
			return next != null;
		}

		public Object next(){
			if(!(hasNext())){
				throw new IllegalStateException("No more nodes.");
			}
			Node n = this.next;
			if(this.next.hasRightSon()){
				this.next = this.next.getRightSon();
				while(next.hasLeftSon()){
					this.next = this.next.getLeftSon();
				}
				return n.getData();
			}else while(true){
				if(!(next.hasFather())){
					this.next = null;
					return n.getData();
				}
				if(next.getFather().getLeftSon() == next){
					this.next = this.next.getFather();
					return n.getData();
				}
				if(!(this.next.hasLeftSon()) && !(this.next.hasRightSon())){
					n = this.next;
					while(this.next.getFather().getRightSon() == this.next){
						if(!(this.next.hasFather())){
							this.next = null;
							return n.getData();
						}
						if(!(this.next.getFather().hasFather())){
							this.next = null;
							return n.getData();
						}
						this.next = this.next.getFather();
					}
				}
			}
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}

	}
}

// questions:
// should the iterator be incorporated into the class itself?
// 