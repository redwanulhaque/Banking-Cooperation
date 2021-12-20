class bstNode<T> {  // class bstNode holds the bstNode of the BST
		
	Comparable <T> data;  // data to generic type
	bstNode<T> leftChild, rightChild;

	bstNode(T data) {  // default constructor
		this.data = (Comparable<T>) data;
	}
}

public class myBST<T> {  // binary tree class
	
	 bstNode<T> root;  
	
	public boolean isEmpty() {  // if the BST is empty or NOT return null
        return root == null;
    }

	public void insert(T data) {  // call on the actual insert function from private
		root = insert(root, data);
	}

	private bstNode<T> insert(bstNode<T> root, T data) {  // actual insert method

		if (root == null) {  //instantiating a new bstNode with data
			return new bstNode<T>(data);
		}

		else if (((Comparable<T>) data).compareTo((T) root.data) < 0) {  //if the data is smaller than the root data, go to the left
			root.leftChild = insert(root.leftChild, data);
		}

		else if (((Comparable<T>) data).compareTo((T) root.data) > 0) {  //if the data is bigger than the root data, go to the right
			root.rightChild = insert(root.rightChild, data);
		}
		
		else {  // No same values are allowed
			;
		}

		return root;  // root of the tree returned
	}

	public void delete(T data) {  // calling on the actual delete method from the private method
		root = delete(root, data);
	}

	private bstNode<T> delete(bstNode<T> root, T data) {  //actual delete method
		
		if (root == null) {  // if there is no bstNode, then tree is empty
			return null;
		}

		else if (((Comparable<T>) data).compareTo((T) root.data) < 0 ) {  // if data is less, then go left
			root.leftChild = delete(root.leftChild, data);
		}

		else if (((Comparable<T>) data).compareTo((T) root.data) > 0) {  // if data is greater then go right
			root.rightChild = delete(root.rightChild, data);
		}

		else {  // data being searched for is equal to the current root

			if (root.leftChild == null && root.rightChild == null) {  // if there no bstNode then return null; 
				return null;
			}

			else if (root.rightChild == null) {  // if there is no right children then return left children
				return root.leftChild;
			}

			else if (root.leftChild == null) {  // if there is no left children then return right children
				return root.rightChild;
			}
		
			else {   //if bstNode is a parent bstNode, then find the max of the lowest so we can fix the BST
				root.data = (Comparable<T>) findMax(root.leftChild);
				root.leftChild = delete(root.leftChild, (T) root.data);
			}
		}

		return root;  // return the root
	}

	public T findMax(bstNode<T> root) {  // method to find the largest bstNode
		
		while (root.rightChild != null) {  //traverse all of the right bstNodes of BST to find the last one at the right.
			root = root.rightChild;
		}
		
		return (T) root.data;  // return the max
	}
	
	private T findMin(bstNode<T> root) {  // method to find the smallest bstNode
		
		while (root.leftChild != null) {  //traverse all of the left bstNodes of BST to find the last one at the left.
			root = root.leftChild;
		}
		
		return (T) root.data;  // return the min
	}

	public Customer search(T data) {  // calling on the actual search method
		return search(root, data);
	}

	Customer search(bstNode<T> root, T data) {  // the actual search method
		
		if (root == null) {  // if the tree is empty return false
			return null;
		}
		
		else if (((Comparable<T>) data).compareTo((T) root.data) < 0) {  // if the bstNode is less, search the left side
			return search(root.leftChild, data);
		}
		
		else if (((Comparable<T>) data).compareTo((T) root.data) > 0) {  // if the bstNode is greater search the right side
			return search(root.rightChild, data);
		}
		
		else {  // return true if search is equal to the value of current bstNode data
			return (Customer)(root.data);
		}
	}

	public void inorder() {  // calling on the inOrder method
		System.out.print("In-order Traversal:\n");
		inorder(root);
	}
	
	private void inorder(bstNode<T> root) {  // actual in order method
		if (root == null) return;  // if getting result based on root

		inorder(root.leftChild);  // getting started from the left side of the tree
		System.out.print(" " + root.data);
		inorder(root.rightChild);
	}
	
	public void preorder() {  // calling on the inOrder method
		System.out.print("\nPre-order Traversal:\n");
		preorder(root);
	}
	
	private void preorder(bstNode<T> root) {  // calling on the actual preOrder method
		if (root == null) return;  // if getting result based on root

		System.out.print(" " + root.data); 
		preorder(root.leftChild);
		preorder(root.rightChild);
	}
}