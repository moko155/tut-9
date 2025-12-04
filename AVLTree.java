
/**
 * Author: Linxiao Wang
 */

import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<T extends Comparable<T>> implements Iterable<T> {

	private AVLNode<T> root; // the root node
	private int count; // unique entries in the tree

	public AVLTree() {
		count = 0;
		root = new AVLNode<>(null, null, null, null);
	}

	public AVLNode<T> getRoot() {
		return root;
	}

	public int size() {
		return count;
	}

	public int height() {
		return root.getHeight();
	}

	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}
		return false;
	}

	public AVLNode<T> find(T data) {
		// Left as a programming project
		// Implement this iteratively
		return null;
	}

	private boolean isBalanced(AVLNode<T> node) {
		int heightDiff = node.getLeft().getHeight() - node.getRight().getHeight();
		if (heightDiff <= 1 && heightDiff >= -1) {
			return true;
		}
		return false;
	}

	private void replaceParent(AVLNode<T> child, AVLNode<T> newChild) {
		AVLNode<T> parent = child.getParent();
		newChild.setParent(parent);
		if (parent != null) {
			if (parent.getLeft() == child) {
				parent.setLeft(newChild);
			} else {
				parent.setRight(newChild);
			}
		} else {
			root = newChild;
		}
	}

	private void RR(AVLNode<T> node) {
		AVLNode<T> newRoot = node.getRight();
		replaceParent(node, newRoot);
		node.setRight(newRoot.getLeft());
		node.getRight().setParent(node);
		newRoot.setLeft(node);
		node.setParent(newRoot);
	}

	private void LL(AVLNode<T> node) {
		// Left as a programming project
		// Refer to the code above in RR
	}

	private void RL(AVLNode<T> node) {
		AVLNode<T> newRoot = node.getRight().getLeft();
		AVLNode<T> otherNode = node.getRight();
		replaceParent(node, newRoot);
		otherNode.setLeft(newRoot.getRight());
		otherNode.getLeft().setParent(otherNode);
		node.setRight(newRoot.getLeft());
		node.getRight().setParent(node);

		newRoot.setLeft(node);
		node.setParent(newRoot);

		newRoot.setRight(otherNode);
		otherNode.setParent(newRoot);
	}

	private void LR(AVLNode<T> node) {
		// Left as a programming project
		// Refer to the code above in RL
	}

	private void balance(AVLNode<T> node) {
		if (node.getLeft().getHeight() < node.getRight().getHeight()) {
			AVLNode<T> taller = node.getRight();
			if (taller.getLeft().getHeight() > taller.getRight().getHeight()) {
				RL(node);
			} else {
				RR(node);
			}
		} else {
			AVLNode<T> taller = node.getLeft();
			if (taller.getLeft().getHeight() < taller.getRight().getHeight()) {
				LR(node);
			} else {
				LL(node);
			}
		}
	}

	// return false if data is already in the tree
	// return true if not and data is put into the tree
	public boolean put(T data) {
		// Left as a programming project
		// Refer to the tutorial description or the lecture notes
		return true;
	}

	private AVLNode<T> successor(AVLNode<T> node) {
		// Left as a programming project
		return null;
	}

	private boolean isExternal(AVLNode<T> node) {
		if (node.getLeft() == null && node.getRight() == null) {
			return true;
		}
		return false;
	}

	// return false if data is not in the tree
	// return true if data is in the tree and get removed
	public boolean remove(T data) {
		// Left as a programming project
		// Refer to the tutorial description or the lecture notes
		return true;
	}

	public Iterator<T> iterator() {
		ArrayList<T> list = new ArrayList<>();
		inorder(root, list);
		return list.iterator();

	}

	private void inorder(AVLNode<T> r, ArrayList<T> list) {
		if (r.getValue() != null) {
			inorder(r.getLeft(), list);
			list.add(r.getValue());
			inorder(r.getRight(), list);
		}
	}

}