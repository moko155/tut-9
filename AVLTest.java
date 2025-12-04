/**
 * Author: Linxiao Wang
 */

public class AVLTest {

	public static void main(String[] args) {

		boolean testPassed;

		// Test 1. AVLNode
		testPassed = true;

		try {

			AVLNode<Integer> nodeP = new AVLNode<>(1, null, null, null);
			AVLNode<Integer> nodeL = new AVLNode<>(2, null, null, null);
			AVLNode<Integer> nodeR = new AVLNode<>(3, null, null, null);

			if (!nodeL.getValue().equals(2))
				testPassed = false;

			nodeL.setValue(3);

			if (!nodeL.getValue().equals(3))
				testPassed = false;

			nodeP.setLeft(nodeL);
			nodeP.setRight(nodeR);
			nodeL.setParent(nodeP);
			nodeR.setParent(nodeP);

			if (nodeP.getLeft() != nodeL || nodeP.getRight() != nodeR || nodeL.getParent() != nodeP
					|| nodeR.getParent() != nodeP)
				testPassed = false;

			if (testPassed)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");

		} catch (Exception e) {

			System.out.println("Test 1 failed");
			e.printStackTrace();

		}

		// Test 2. AVLTree: getRoot & put
		testPassed = true;

		try {

			AVLTree<String> tree = new AVLTree<>();

			if (tree.getRoot().getValue() != null)
				testPassed = false;

			tree.put("Andrew");

			if (!tree.getRoot().getValue().equals("Andrew"))
				testPassed = false;

			tree.put("Bita");
			tree.put("Charles");
			tree.put("Erik");
			tree.put("David");

			if (!tree.getRoot().getValue().equals("Bita"))
				testPassed = false;

			if (!tree.getRoot().getRight().getValue().equals("David"))
				testPassed = false;

			if (!tree.getRoot().getRight().getLeft().getValue().equals("Charles"))
				testPassed = false;

			if (testPassed)
				System.out.println("Test 2 passed");
			else
				System.out.println("Test 2 failed");

		} catch (Exception e) {

			System.out.println("Test 2 failed");
			e.printStackTrace();

		}

		// Test 3. AVLTree: remove
		testPassed = true;

		try {

			AVLTree<Integer> tree = new AVLTree<>();
			tree.put(44);

			tree.put(17);
			tree.put(62);
			tree.put(10);
			tree.put(54);
			tree.put(78);
			tree.put(50);
			tree.put(88);

			tree.remove(10);

			if (!tree.getRoot().getValue().equals(62)) {
				testPassed = false;
			}
			tree.remove(62);
			if (!tree.getRoot().getValue().equals(54)) {
				testPassed = false;
			}

			if (testPassed)
				System.out.println("Test 3 passed");
			else
				System.out.println("Test 3 failed");

		} catch (Exception e) {

			System.out.println("Test 3 failed");
			e.printStackTrace();

		}
	}
}