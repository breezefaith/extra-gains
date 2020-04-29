import java.util.Iterator;

public class TestBinarySearchTree {

	private static BinarySearchTree<Integer> t;
	private static BinarySearchTree<String> t2;
	private static BinarySearchTree<BinaryTreeNode<Integer>> t3;

	private static int[] tests;
	private static boolean testPassed;
	private static int passedCount = 0;

	private static int[] readTests(String[] args, int numTests) {

		int[] tests = new int[numTests];
		int value;

		if (args.length == 0) {

			for (int i = 0; i < numTests; ++i) {

				tests[i] = i + 1;

			} // end for

		} // end if

		else {

			for (int i = 0; i < args.length; ++i) {

				value = Integer.parseInt(args[i]);

				if (value >= 1 && value <= numTests) {

					tests[value - 1] = value;

				} // end if

				else {

					System.out.println("ERROR: Test " + value + "does not exist");

				} // end else

			} // end for

		} // end else

		return tests;

	} // end restTests

	private static void test1() {

		if (tests[0] == 1) {

			try {

				BinaryTreeNode<Integer> val = t.getRoot();

				if (val == null) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 1 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 1 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 1 failed");

			} // end else

		} // end if

	} // end test1

	private static void test2() {

		if (tests[1] == 2) {

			try {

				boolean val = t.isEmpty();

				if (val == true) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 2 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 2 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 2 failed");

			} // end else

		} // end if

	} // end test2

	private static void test3() {

		if (tests[2] == 3) {

			try {

				int val = t.size();

				if (val == 0) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 3 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 3 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 3 failed");

			} // end else

		} // end if

	} // end test3

	private static void test4() {

		if (tests[3] == 4) {

			try {

				boolean val = t.contains(0);

				if (val == false) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (NonExistentKeyException e) {

				testPassed = true;

			} catch (Exception e) {
				
				testPassed = false;
				System.out.println("Test 3 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 4 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 4 failed");

			} // end else

		} // end if

	} // end test4

	private static void test5() {

		if (tests[4] == 5) {

			try {

				Iterator<Integer> it = t.iterator();

				if (!it.hasNext()) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 5 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 5 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 5 failed");

			} // end else

		} // end if

	} // end test5

	private static void test6() {

		if (tests[5] == 6) {

			try {

				String s = t.toString();

				if (s.equals("")) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 6 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 6 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 6 failed");

			} // end else

		} // end if

	} // end test6

	private static void test7() {

		if (tests[6] == 7) {

			try {

				t.add(t.getRoot(), 10);

				if (t.getRoot().getElement() == 10) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 7 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 7 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 7 failed");

			} // end else

		} // end if

	} // end test7

	private static void test8() {

		if (tests[7] == 8) {

			try {

				t.add(t.getRoot(), 10);

				if (t.getRoot().getElement() == 10) {
					System.out.println("false 1");

					testPassed = false;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (DuplicatedKeyException e) {
				System.out.println("failed 2 ");

				testPassed = true;

			} // end catch

			if (testPassed) {

				System.out.println("Test 8 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 8 failed");

			} // end else

		} // end if

	} // end test8

	private static void test9() {

		if (tests[8] == 9) {

			try {

				boolean val = t.isEmpty();

				if (val == false) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 9 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 9 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 9 failed");

			} // end else

		} // end if

	} // end test9

	private static void test10() {

		if (tests[9] == 10) {

			try {

				int val = t.size();

				if (val == 1) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 10 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 10 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 10 failed");

			} // end else

		} // end if

	} // end test10

	private static void test11() {

		if (tests[10] == 11) {

			try {

				boolean val = t.contains(10);

				if (val == true) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 11 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 11 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 11 failed");

			} // end else

		} // end if

	} // end test11

	private static void test12() {

		if (tests[11] == 12) {

			try {

				Iterator<Integer> it = t.iterator();

				if (it.hasNext()) {

					if (it.next() == 10) {

						testPassed = true;

					} // end if

					else {

						testPassed = false;

					} // end else

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 12 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 12 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 12 failed");

			} // end else

		} // end if

	} // end test12

	private static void test13() {

		if (tests[12] == 13) {

			try {

				String s = t.toString();

				if (s.equals("10\n")) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 13 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 13 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 13 failed");

			} // end else

		} // end if

	} // end test13

	private static void test14() {

		if (tests[13] == 14) {

			try {

				t.add(t.getRoot(), 5);
				t.add(t.getRoot(), 3);
				t.add(t.getRoot(), 1);
				t.add(t.getRoot(), 4);
				t.add(t.getRoot(), 7);
				t.add(t.getRoot(), 9);
				t.add(t.getRoot(), 15);
				t.add(t.getRoot(), 13);

				BinaryTreeNode<Integer> n10 = t.getRoot();
				BinaryTreeNode<Integer> n5 = n10.getLeft();
				BinaryTreeNode<Integer> n3 = n5.getLeft();
				BinaryTreeNode<Integer> n1 = n3.getLeft();
				BinaryTreeNode<Integer> n4 = n3.getRight();
				BinaryTreeNode<Integer> n7 = n5.getRight();
				BinaryTreeNode<Integer> n9 = n7.getRight();
				BinaryTreeNode<Integer> n15 = n10.getRight();
				BinaryTreeNode<Integer> n13 = n15.getLeft();

				if (n10.getElement() == 10 && n5.getElement() == 5 && n3.getElement() == 3 && n1.getElement() == 1
						&& n4.getElement() == 4 && n7.getElement() == 7 && n9.getElement() == 9
						&& n15.getElement() == 15 && n13.getElement() == 13) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 14 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 14 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 14 failed");

			} // end else

		} // end if

	} // end test14

	private static void test15() {

		if (tests[14] == 15) {

			try {

				int val = t.size();

				if (val == 9) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 15 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 15 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 15 failed");

			} // end else

		} // end if

	} // end test15

	private static void test16() {

		if (tests[15] == 16) {

			try {

				boolean val = (t.contains(10) && t.contains(5) && t.contains(3) && t.contains(1) && t.contains(4)
						&& t.contains(7) && t.contains(9) && t.contains(15) && t.contains(13));

				if (val == true) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 16 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 16 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 16 failed");

			} // end else

		} // end if

	} // end test16

	private static void test17() {

		if (tests[16] == 17) {

			try {

				Iterator<Integer> it = t.iterator();

				int val = 0;

				while (it.hasNext()) {

					val = it.next();

				} // end while

				if (val == 15) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 17 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 17 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 17 failed");

			} // end else

		} // end if

	} // end test17

	private static void test18() {

		if (tests[17] == 18) {

			try {

				String s = t.toString();

				if (s.equals("1\n3\n4\n5\n7\n9\n10\n13\n15\n")) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 18 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 18 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 18 failed");

			} // end else

		} // end if

	} // end test18

	private static void test19() {

		if (tests[18] == 19) {

			try {

				t2.add(t2.getRoot(), "J");
				t2.add(t2.getRoot(), "E");
				t2.add(t2.getRoot(), "C");
				t2.add(t2.getRoot(), "A");
				t2.add(t2.getRoot(), "D");
				t2.add(t2.getRoot(), "G");
				t2.add(t2.getRoot(), "I");
				t2.add(t2.getRoot(), "O");
				t2.add(t2.getRoot(), "M");

				BinaryTreeNode<String> nJ = t2.getRoot();
				BinaryTreeNode<String> nE = nJ.getLeft();
				BinaryTreeNode<String> nC = nE.getLeft();
				BinaryTreeNode<String> nA = nC.getLeft();
				BinaryTreeNode<String> nD = nC.getRight();
				BinaryTreeNode<String> nG = nE.getRight();
				BinaryTreeNode<String> nI = nG.getRight();
				BinaryTreeNode<String> nO = nJ.getRight();
				BinaryTreeNode<String> nM = nO.getLeft();

				if (nJ.getElement().equals("J") && nE.getElement().equals("E") && nC.getElement().equals("C")
						&& nA.getElement().equals("A") && nD.getElement().equals("D") && nG.getElement().equals("G")
						&& nI.getElement().equals("I") && nO.getElement().equals("O") && nM.getElement().equals("M")) {

					testPassed = true;

				} // end if

				else {

					testPassed = false;

				} // end else

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 19 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 19 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 19 failed");

			} // end else

		} // end if

	} // end test19

	private static void test20() {

		if (tests[19] == 20) {

			try {

				BinaryTreeNode<Integer> node = new BinaryTreeNode<>(5);
				t3.add(t3.getRoot(), node);
				t3.contains(node);
				testPassed = true;

			} catch (Exception e) {

				testPassed = false;
				System.out.println("Test 20 failed: " + e);

			} // end catch

			if (testPassed) {

				System.out.println("Test 20 passed");
				passedCount++;

			} // end if

			else {

				System.out.println("Test 20 failed");

			} // end else

		} // end if

	} // end test20

	public static void main(String[] args) {

		tests = readTests(args, 20);

		t = new BinarySearchTree<>();
		t2 = new BinarySearchTree<>();
		t3 = new BinarySearchTree<>();

		System.out.println("TESTS FOR CLASS BinarySearchTree");
		System.out.println("==========================\n");

		/**
		 * Empty tree tests.
		 */
		// Test 1. Test getRoot on an empty tree.
		test1();

		// Test 2. Test isEmpty on an empty tree.
		test2();

		// Test 3. Test size on an empty tree.
		test3();

		// Test 4. Test contains on an empty tree.
		test4();

		// Test 5. Test iterator on an empty tree.
		test5();

		// Test 6. Test toString on an empty tree.
		test6();

		/**
		 * Single element tests.
		 */

		// Test 7. Test add with a single element (also tests getRoot with a single
		// element)
		test7();

		// Test 8. Test add with a duplicate element.
		test8();

		// Test 9. Test isEmpty with a single element.
		test9();

		// Test 10. Test size with a single element.
		test10();

		// Test 11. Test contains with a single element.
		test11();

		// Test 12. Test iterator with a single element.
		test12();

		// Test 13. Test toString with a single element.
		test13();

		/**
		 * Multiple elements tests.
		 */

		// Test 14. Test add with multiple elements.
		test14();

		// Test 15. Test size with multiple elements.
		test15();

		// Test 16. Test contains with multiple elements.
		test16();

		// Test 17. Test iterator with multiple elements.
		test17();

		// Test 18. Test toString with multiple elements.
		test18();

		/**
		 * Generics tests.
		 */

		// Test 19. Test add with multiple Strings.
		test19();

		// Test 20. Test for ClassCastException handling.
		test20();

		System.out.println("\nScore: " + passedCount + " / 20");

	} // end main

} // end TestBinarySearchTree (class)