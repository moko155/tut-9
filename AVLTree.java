
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
    AVLNode<T> current = root;
    
    // If tree is empty, return root
    if (current.getValue() == null) {
        return current;
    }
    
    // Traverse the tree
    while (true) {
        int comparison = data.compareTo(current.getValue());
        
        if (comparison == 0) {
            // Found the data
            return current;
        } else if (comparison < 0) {
            // Go left
            if (current.getLeft().getValue() == null) {
                // This is where data would belong
                return current;
            }
            current = current.getLeft();
        } else {
            // Go right
            if (current.getRight().getValue() == null) {
                // This is where data would belong
                return current;
            }
            current = current.getRight();
        }
    }
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
    AVLNode<T> newRoot = node.getLeft();
    replaceParent(node, newRoot);
    node.setLeft(newRoot.getRight());
    node.getLeft().setParent(node);
    newRoot.setRight(node);
    node.setParent(newRoot);
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
    AVLNode<T> newRoot = node.getLeft().getRight();
    AVLNode<T> otherNode = node.getLeft();
    replaceParent(node, newRoot);
    otherNode.setRight(newRoot.getLeft());
    otherNode.getRight().setParent(otherNode);
    node.setLeft(newRoot.getRight());
    node.getLeft().setParent(node);

    newRoot.setRight(node);
    node.setParent(newRoot);

    newRoot.setLeft(otherNode);
    otherNode.setParent(newRoot);
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
	//put
	public boolean put(T data) {
    AVLNode<T> p = find(data);
    
    // Check if data already exists
    if (p.getValue() != null && p.getValue().equals(data)) {
        return false;
    }
    
    // Create new node with external children
    AVLNode<T> newNode = new AVLNode<>(data, null, 
        new AVLNode<>(null, null, null, null),
        new AVLNode<>(null, null, null, null));
    newNode.getLeft().setParent(newNode);
    newNode.getRight().setParent(newNode);
    
    // Insert into tree
    if (p.getValue() == null) {
        // Tree was empty, replace root
        root = newNode;
    } else {
        // Insert as child of p
        newNode.setParent(p);
        int comparison = data.compareTo(p.getValue());
        if (comparison < 0) {
            p.setLeft(newNode);
        } else {
            p.setRight(newNode);
        }
    }
    
    count++;
    
    // Rebalance from inserted node up to root
    AVLNode<T> current = newNode.getParent();
    while (current != null) {
        if (!isBalanced(current)) {
            balance(current);
        }
        current = current.getParent();
    }
    
    return true;
}

	private AVLNode<T> successor(AVLNode<T> node) {
    // Go to right child
    AVLNode<T> current = node.getRight();
    
    // Go as far left as possible
    while (current.getLeft().getValue() != null) {
        current = current.getLeft();
    }
    
    return current;
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
    AVLNode<T> p = find(data);
    
    // Check if data exists in tree
    if (p.getValue() == null || !p.getValue().equals(data)) {
        return false;
    }
    
    AVLNode<T> removedNodeParent;
    
    // BST removal logic
    if (isExternal(p.getLeft()) && isExternal(p.getRight())) {
        // Case 1: Both children are external (leaf node)
        removedNodeParent = p.getParent();
        replaceParent(p, p.getLeft());
    } else if (isExternal(p.getLeft())) {
        // Case 2: Left child is external
        removedNodeParent = p.getParent();
        replaceParent(p, p.getRight());
    } else if (isExternal(p.getRight())) {
        // Case 3: Right child is external
        removedNodeParent = p.getParent();
        replaceParent(p, p.getLeft());
    } else {
        // Case 4: Both children are internal
        AVLNode<T> succ = successor(p);
        p.setValue(succ.getValue());
        removedNodeParent = succ.getParent();
        replaceParent(succ, succ.getRight());
    }
    
    count--;
    
    // Rebalance from removed node's parent up to root
    AVLNode<T> current = removedNodeParent;
    while (current != null) {
        if (!isBalanced(current)) {
            balance(current);
        }
        current = current.getParent();
    }
    
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