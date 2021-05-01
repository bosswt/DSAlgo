import java.util.Arrays;

public class BSTRecursive {

	BSTNode root;
	int size;

	public BSTRecursive(BSTNode root, int size) {
		this.root = root;
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(BSTNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(BSTNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, BSTNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new TreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public BSTNode insert(int v) {
		root = insert(v, root, null);
		return root;
	}

	// return the node n after v was added into the tree
	public BSTNode insert(int v, BSTNode n, BSTNode parent) {
		if (n == null) {
			n = new BSTNode(v, null, null, parent);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		return n;
	}

	public BSTNode remove(int v) {
		root = remove(v, root, null);
		return root;
	}

	// return the node n after v was removed from the tree
	public BSTNode remove(int v, BSTNode n, BSTNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n.parent = null; // disconnect from above
				n = null; // disconnect from below
				size--;
			} else if (n.left != null && n.right == null) {
				BSTNode n2 = n.left;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.left = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else if (n.right != null && n.left == null) {
				BSTNode n2 = n.right;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.right = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else {
				TreeIterator i = (TreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		return n;
	}

	private int height(BSTNode n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	/**
	 * Calculate maximum number of nodes that the tree can contain based on its
	 * current height (starting from node n).
	 * 
	 * @return the number of nodes in a fully balanced binary tree.
	 */
	public int maxNumNodes(BSTNode n) {
		return (int) Math.pow(2, height(n) + 1) - 1;
	}

	/**
	 * Method to find the number of nodes in our tree.
	 */
	public int numNodes() {
		return numNodes(root);
	}

	/**
	 * Method to find the number of leaves in our tree
	 */
	public int numLeaves() {
		return numLeaves(root);
	}

	/**
	 * Method to find a tree that stores values from this. But the values must all
	 * be greater than v.
	 */
	public BSTRecursive greaterThan(int v) {
		BSTRecursive result = new BSTRecursive(null, 0);
		result = greaterThan(root, v, result);
		return result;
	}

	/**
	 * Method to convert a tree into a string representation of in-order traversal
	 * of the tree. Nodes are separated by a single space.
	 * 
	 * @return String that represents all data in the tree (sorted).
	 * 
	 */
	public String toStringInOrder() {
		return toStringInOrder(root);
	}

	/**
	 * Method to check if a tree is really a binary search tree.
	 * 
	 * @return true if our tree is a binary search tree, false otherwise.
	 * 
	 */
	public boolean isBST() {
		return isBST(root);
	}

	/**
	 * Method to find parent of a given node without following parent link from that
	 * node.
	 * 
	 * @param d the node that we want to find its parent node.
	 * 
	 * @return parent of d
	 * 
	 */
	public BSTNode findParentForwardDirection(BSTNode d) {
		return findParentForwardDirection(root, d, null);
	}

	/**************************************************************************************************/
	// Edit only the following methods below.
	/**************************************************************************************************/

	/**
	 * Internal method to find the number of nodes in a given subtree using a
	 * recursive method.
	 * 
	 * @param n the root of a given subtree
	 * @return the number of nodes that the subtree contains
	 */
	private int numNodes(BSTNode n) {
		if (n == null)
			return 0;
		int count = 1;
		count += numNodes(n.left);
		count += numNodes(n.right);
		return count;

	}

	/**
	 * Internal method to count the number of leaves in a given subtree
	 * (recursively).
	 * 
	 * @param n root node of a given subtree
	 * @return number of leaves in the subtree
	 */
	private int numLeaves(BSTNode n) {
		if (n == null)
			return 0;
		int count = 0;
		if (n.left == null && n.right == null)
			count += 1;
		count += numLeaves(n.left);
		count += numLeaves(n.right);
		return count;
	}

	/**
	 * Get a Binary search tree that has all values from the tree that has n as its
	 * root , but with all values smaller than or equal to v removed. This method
	 * must be implemented using recursion.
	 * 
	 * @param n      root node of a given subtree
	 * @param v      the result tree must store only values that are greater than v.
	 * @param result a resulting tree (passed into the method so we can keep working
	 *               on it recursively).
	 * 
	 * @return the binary search tree that has all values from the tree that has n
	 *         as its root , but with all values smaller than or equal to v removed.
	 */
	private BSTRecursive greaterThan(BSTNode n, int v, BSTRecursive result) {
		if (n == null)
			return result;
		if (n.data <= v) {
			greaterThan(n.left, v, result);
			greaterThan(n.right, v, result);
		} else {
			result.insert(n.data);
			greaterThan(n.left, v, result);
			greaterThan(n.right, v, result);
		}
		return result;

	}

	/**
	 * Internal method to convert a subtree into a string representation of in-order
	 * traversal of the tree. In the string, data from each node is separated by a
	 * single space. Note - you must implement this method using recursion. - an
	 * empty tree gives "" as its result.
	 * 
	 * @param n the node that roots the subtree.
	 * @return String that represents all data in the subtree (sorted). Use trim()
	 *         on the string if needed.
	 * 
	 */
	private String toStringInOrder(BSTNode n) {
		if (n == null)
			return "";
		return (toStringInOrder(n.left) + " " + n.data + " " + toStringInOrder(n.right)).trim();
	}

	/**
	 * Internal method to check if a subtree is really a binary search tree. Note -
	 * you must implement this method using recursion. - an empty tree is a binary
	 * search tree.
	 * 
	 * @param n the node that roots the subtree.
	 * @return true if the given subtree is a binary search tree, false otherwise.
	 * 
	 */
	private boolean isBST(BSTNode n) {
		if (n == null)
			return true;
		if (!isBST(n.left))
			return false;
		else if (!isBST(n.right))
			return false;
		else if (!allLessThan(n.left, n) || !allGreaterThan(n.right, n))
			return false;
		return true;

	}

	// helper method for isBST. You can call this method without violating the
	// recursive requirement for this assignment.
	private boolean allLessThan(BSTNode l, BSTNode n) {
		// default result when l is null.
		if (l == null)
			return true;

		// First, create a string from subtree l.
		// This string is not sorted if the tree is not BST.
		String s = toStringInOrder(l);

		// Then put each data in array of String.
		String[] a = s.split("\\s");

		// convert to integer array.
		int[] a2 = Arrays.stream(a).mapToInt(Integer::parseInt).toArray();

		int data = n.data;
		for (int i = 0; i < a2.length; i++) {
			if (a2[i] > data)
				return false;
		}
		return true;
	}

	// helper method for isBST. You can call this method without violating the
	// recursive requirement for this assignment.
	private boolean allGreaterThan(BSTNode r, BSTNode n) {
		// default result when l is null.
		if (r == null)
			return true;

		// First, create a string from subtree r.
		// This string is not sorted if the tree is not BST.
		String s = toStringInOrder(r);

		// Then put each data in array of String.
		String[] a = s.split("\\s");

		// convert to integer array.
		int[] a2 = Arrays.stream(a).mapToInt(Integer::parseInt).toArray();

		int data = n.data;
		for (int i = 0; i < a2.length; i++) {
			if (a2[i] < data)
				return false;
		}
		return true;
	}

	/**
	 * Internal method to find parent of a given node (in a subtree) without
	 * following parent link up from that node. Note - you must implement this
	 * method using recursion.
	 * 
	 * @param n      the node that roots the subtree.
	 * @param d      the node that we want to find its parent node.
	 * @param parent our temporary node that will move down the tree. , its final
	 *               value will be our return value.
	 * @return parent of d
	 * 
	 */
	private BSTNode findParentForwardDirection(BSTNode n, BSTNode d, BSTNode parent) {
		if (n == null)
			return null;
		if (d == root)
			return null;
		parent = n;
		if (d.data < n.data) {
			if (parent.left == d)
				return parent;
			parent = n.left;
			return findParentForwardDirection(n.left, d, parent);
		} else if (d.data > n.data) {
			if (parent.right == d)
				return parent;
			parent = n.right;
			return findParentForwardDirection(n.right, d, parent);
		}
		return parent;
	}

	// main is not necessary.
	public static void main(String[] args) {

	}

}
