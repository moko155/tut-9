/**
 * Author: Linxiao Wang
 */

public class AVLNode<T extends Comparable<T>> {

	private AVLNode<T> parent; // reference to the parent node
	private AVLNode<T> left; // reference to the left child
	private AVLNode<T> right; // reference to the right child
	private T value; // reference to the entry stored at the node

	public AVLNode(T inputValue, AVLNode<T> inputParent, AVLNode<T> inputLeft, AVLNode<T> inputRight) {
		value = inputValue;
		parent = inputParent;
		left = inputLeft;
		right = inputRight;
	}

	public AVLNode<T> getParent() {
		return parent;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public int getHeight() {
		if (value == null) {
			return 0;
		}
		return 1 + Math.max(left.getHeight(), right.getHeight());
	}

	public T getValue() {
		return value;
	}

	public void setParent(AVLNode<T> newParent) {
		parent = newParent;
	}

	public void setLeft(AVLNode<T> newLeft) {
		left = newLeft;
	}

	public void setRight(AVLNode<T> newRight) {
		right = newRight;
	}

	public void setValue(T newValue) {
		value = newValue;
	}
}