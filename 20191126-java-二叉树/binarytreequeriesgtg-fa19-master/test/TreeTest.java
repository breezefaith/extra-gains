
import net.datastructures.*;
import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TreeTest {

    public TreeTest() {
    }

    @Test
    public void testInsertionAndtoArray() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        Object[] actual = AbstractTree.toList(bt.breadthfirst()).toArray();
        Object[] expected = {50, 2, 34, 19, 6, 22};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testInsertionAndtoArray2() {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        bt.insertNode("a");
        bt.insertNode("b");
        bt.insertNode("c");
        bt.insertNode("d");
        bt.insertNode("e");
        bt.insertNode("f");
        Object[] actual = AbstractTree.toList(bt.breadthfirst()).toArray();
        Object[] expected = {"a", "b", "c", "d", "e", "f"};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void sumAtDepthTest1() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        Integer sum = TreeFunctions.sumAtDepthIterative(bt, 2);
        assertEquals(Integer.valueOf(47), sum);
        // Student: try at different depths!!
        sum = TreeFunctions.sumAtDepthIterative(bt, 9);
        assertEquals(Integer.valueOf(0), sum);
    }

    @Test
    public void sumAtDepthTest2() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        Integer sum = TreeFunctions.sumAtDepthRecursive(bt, 2);
        assertEquals(Integer.valueOf(47), sum);
        // Student: try at different depths!!
        sum = TreeFunctions.sumAtDepthRecursive(bt, 9);
        assertEquals(Integer.valueOf(0), sum);
    }

    @Test
    public void sumAtDepthTreeWithHolesTest1() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Position<Integer> root = bt.addRoot(50);
        Position<Integer> right = bt.addRight(root, 2);
        Position<Integer> rightRight = bt.addRight(right, 34);
        Position<Integer> rightRightLeft = bt.addLeft(rightRight, 10);
        Position<Integer> rightLeft = bt.addLeft(right, 100);
        Position<Integer> rightLeftLeft = bt.addLeft(rightLeft, 16);
        Position<Integer> rightLeftLeftLeft = bt.addLeft(rightLeftLeft, 71);
        Position<Integer> rightLeftRight = bt.addRight(rightLeft, 77);
        Position<Integer> rightLeftRightRight = bt.addRight(rightLeftRight, 13);
        Position<Integer> left = bt.addLeft(root, 44);
        Position<Integer> leftLeft = bt.addLeft(left, 300);
        Integer sum = TreeFunctions.sumAtDepthIterative(bt, 2);
        assertEquals(Integer.valueOf(434), sum);
        // Student: try at different depths!!
        sum = TreeFunctions.sumAtDepthIterative(bt, 9);
        assertEquals(Integer.valueOf(0), sum);
    }

    @Test
    public void sumAtDepthTreeWithHolesTest2() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Position<Integer> root = bt.addRoot(50);
        Position<Integer> right = bt.addRight(root, 2);
        Position<Integer> rightRight = bt.addRight(right, 34);
        Position<Integer> rightRightLeft = bt.addLeft(rightRight, 10);
        Position<Integer> rightLeft = bt.addLeft(right, 100);
        Position<Integer> rightLeftLeft = bt.addLeft(rightLeft, 16);
        Position<Integer> rightLeftLeftLeft = bt.addLeft(rightLeftLeft, 71);
        Position<Integer> rightLeftRight = bt.addRight(rightLeft, 77);
        Position<Integer> rightLeftRightRight = bt.addRight(rightLeftRight, 13);
        Position<Integer> left = bt.addLeft(root, 44);
        Position<Integer> leftLeft = bt.addLeft(left, 300);
        Integer sum = TreeFunctions.sumAtDepthRecursive(bt, 2);
        assertEquals(Integer.valueOf(434), sum);
        // Student: try at different depths!!
        sum = TreeFunctions.sumAtDepthIterative(bt, 9);
        assertEquals(Integer.valueOf(0), sum);
    }

    @Test
    public void reduceAtDepthTest1() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        int sum = bt.reduceAtDepthIterative(2, new IntegerSum(), 0);
        assertEquals(47, sum);
        // Student: try at different depths!!
        sum = bt.reduceAtDepthIterative(9, new IntegerSum(), 0);
        assertEquals(0, sum);
    }

    @Test
    public void reduceAtDepthTest2() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        int sum = bt.reduceAtDepthRecursive(2, new IntegerSum(), 0);
        assertEquals(47, sum);
        // Student: try at different depths!!
        sum = bt.reduceAtDepthRecursive(9, new IntegerSum(), 0);
        assertEquals(0, sum);
    }

    @Test
    public void reduceAtDepthTreeWithHolesTest1() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Position<Integer> root = bt.addRoot(50);
        Position<Integer> right = bt.addRight(root, 2);
        Position<Integer> rightRight = bt.addRight(right, 34);
        Position<Integer> rightRightLeft = bt.addLeft(rightRight, 10);
        Position<Integer> rightLeft = bt.addLeft(right, 100);
        Position<Integer> rightLeftLeft = bt.addLeft(rightLeft, 16);
        Position<Integer> rightLeftLeftLeft = bt.addLeft(rightLeftLeft, 71);
        Position<Integer> rightLeftRight = bt.addRight(rightLeft, 77);
        Position<Integer> rightLeftRightRight = bt.addRight(rightLeftRight, 13);
        Position<Integer> left = bt.addLeft(root, 44);
        Position<Integer> leftLeft = bt.addLeft(left, 300);
        Integer sum = bt.reduceAtDepthIterative(2, new IntegerSum(), 0);
        assertEquals(Integer.valueOf(434), sum);
        // Student: try at different depths!!
        sum = bt.reduceAtDepthIterative(9, new IntegerSum(), 0);
        assertEquals(Integer.valueOf(0), sum);
    }

    @Test
    public void reduceAtDepthTreeWithHolesTest2() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Position<Integer> root = bt.addRoot(50);
        Position<Integer> right = bt.addRight(root, 2);
        Position<Integer> rightRight = bt.addRight(right, 34);
        Position<Integer> rightRightLeft = bt.addLeft(rightRight, 10);
        Position<Integer> rightLeft = bt.addLeft(right, 100);
        Position<Integer> rightLeftLeft = bt.addLeft(rightLeft, 16);
        Position<Integer> rightLeftLeftLeft = bt.addLeft(rightLeftLeft, 71);
        Position<Integer> rightLeftRight = bt.addRight(rightLeft, 77);
        Position<Integer> rightLeftRightRight = bt.addRight(rightLeftRight, 13);
        Position<Integer> left = bt.addLeft(root, 44);
        Position<Integer> leftLeft = bt.addLeft(left, 300);
        Integer sum = bt.reduceAtDepthRecursive(2, new IntegerSum(), 0);
        assertEquals(Integer.valueOf(434), sum);
        // Student: try at different depths!!
        sum = bt.reduceAtDepthRecursive(9, new IntegerSum(), 0);
        assertEquals(Integer.valueOf(0), sum);
    }

    @Test
    public void suchThatIterativeTest() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        Integer[] expected = {50, 2};
        List<Integer> answers = bt.suchThatIterative(new HasTwoChildren());
        assertArrayEquals(expected, answers.toArray());
        expected = new Integer[]{50, 2, 34, 6, 22};
        answers = bt.suchThatIterative(new IsEven());
        assertArrayEquals(expected, answers.toArray());
    }

    @Test
    public void suchThatIterativeTreeWithHolesTest() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Position<Integer> root = bt.addRoot(50);
        Position<Integer> right = bt.addRight(root, 2);
        Position<Integer> rightRight = bt.addRight(right, 34);
        Position<Integer> rightRightLeft = bt.addLeft(rightRight, 10);
        Position<Integer> rightLeft = bt.addLeft(right, 100);
        Position<Integer> rightLeftLeft = bt.addLeft(rightLeft, 16);
        Position<Integer> rightLeftLeftLeft = bt.addLeft(rightLeftLeft, 71);
        Position<Integer> rightLeftRight = bt.addRight(rightLeft, 77);
        Position<Integer> rightLeftRightRight = bt.addRight(rightLeftRight, 13);
        Position<Integer> left = bt.addLeft(root, 44);
        Position<Integer> leftLeft = bt.addLeft(left, 300);
        Integer[] expected = {50, 2, 100};
        List<Integer> answers = bt.suchThatIterative(new HasTwoChildren());
        assertArrayEquals(expected, answers.toArray());
		expected = new Integer[]{50, 44, 2, 300, 100, 34, 16, 10};
		answers = bt.suchThatIterative(new IsEven());
		assertArrayEquals(expected, answers.toArray());
    }

    @Test
    public void suchThatRecursiveTest() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.insertNode(50);
        bt.insertNode(2);
        bt.insertNode(34);
        bt.insertNode(19);
        bt.insertNode(6);
        bt.insertNode(22);
        Integer[] expected = {50, 2};
        List<Integer> answers = bt.suchThatRecursive(new HasTwoChildren());
        assertArrayEquals(expected, answers.toArray());
//		System.out.println(bt.suchThatRecursive(new IsEven()));
		expected = new Integer[]{50, 2, 6, 34, 22};
		answers = bt.suchThatRecursive(new IsEven());
		assertArrayEquals(expected, answers.toArray());
    }

    @Test
    public void suchThatRecursiveTreeWithHolesTest() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        Position<Integer> root = bt.addRoot(50);
        Position<Integer> right = bt.addRight(root, 2);
        Position<Integer> rightRight = bt.addRight(right, 34);
        Position<Integer> rightRightLeft = bt.addLeft(rightRight, 10);
        Position<Integer> rightLeft = bt.addLeft(right, 100);
        Position<Integer> rightLeftLeft = bt.addLeft(rightLeft, 16);
        Position<Integer> rightLeftLeftLeft = bt.addLeft(rightLeftLeft, 71);
        Position<Integer> rightLeftRight = bt.addRight(rightLeft, 77);
        Position<Integer> rightLeftRightRight = bt.addRight(rightLeftRight, 13);
        Position<Integer> left = bt.addLeft(root, 44);
        Position<Integer> leftLeft = bt.addLeft(left, 300);
        Integer[] expected = {50, 2, 100};
        List<Integer> answers = bt.suchThatRecursive(new HasTwoChildren());
        assertArrayEquals(expected, answers.toArray());
		expected = new Integer[]{50, 44, 300, 2, 100, 16, 34, 10};
		answers = bt.suchThatRecursive(new IsEven());
		assertArrayEquals(expected, answers.toArray());
    }


    // Example implementation of BiFunction used by the given test
    private static class IntegerSum implements BiFunction<Integer, Integer, Integer> {
        @Override
        public Integer apply(Integer soFar, Integer x) {
            return soFar + x;
        }
    }

    // Example implementation of BiFunction used by the given test
    private static class HasTwoChildren implements BiFunction<BinaryTree<Integer>, Position<Integer>, Boolean> {
        @Override
        public Boolean apply(BinaryTree<Integer> t, Position<Integer> p) {
            return t.numChildren(p) == 2;
        }
    }

		/* The staff will run your code on several additional JUnit tests of our own.
		   You must write additional tests below to provide more evidence that your
		   method implementations are correct. 
		
		   This test code is part of the assignment, just like the other code.

		   If you write a new test and it fails, GREAT! That means you are making
		   progress. Either the test is wrong and you just need to fix it, or it means you found
		   a bug in your LinkedBinaryTree code and now you can go fix it. Don't remove good tests just
		   because they fail.
		 */

    // write your new tests below here, using the @Test methods above as an example.
    // PART 3: testing
	@Test
	public void reduceAtDepthForDoubleMaxTest() {
		LinkedBinaryTree<Double> bt = new LinkedBinaryTree<>();
		bt.insertNode(50.0);
		bt.insertNode(2.0);
		bt.insertNode(34.0);
		bt.insertNode(19.0);
		bt.insertNode(6.0);
		bt.insertNode(22.0);
		double max = bt.reduceAtDepthIterative(2, new DoubleMax(), Double.MIN_VALUE);
		assertEquals(22.0, max, 0.000001);
		// Student: try at different depths!!
		max = bt.reduceAtDepthIterative(9, new DoubleMax(), Double.MIN_VALUE);
		assertEquals(Double.MIN_VALUE, max, 0.000001);
	}

	@Test
	public void reduceAtDepthForDoubleMaxTest2() {
		LinkedBinaryTree<Double> bt = new LinkedBinaryTree<>();
		bt.insertNode(50.0);
		bt.insertNode(2.0);
		bt.insertNode(34.0);
		bt.insertNode(19.0);
		bt.insertNode(6.0);
		bt.insertNode(22.0);
		double max = bt.reduceAtDepthRecursive(2, new DoubleMax(), Double.MIN_VALUE);
		assertEquals(22.0, max, 0.000001);
		// Student: try at different depths!!
		max = bt.reduceAtDepthRecursive(9, new DoubleMax(), Double.MIN_VALUE);
		assertEquals(Double.MIN_VALUE, max, 0.000001);
	}

	// PART 5: testing
    @Test
    public void suchThatIterativeForInOrderTest() {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        bt.insertNode("50");
        bt.insertNode("2");
        bt.insertNode("34");
        bt.insertNode("19");
        bt.insertNode("6");
        bt.insertNode("22");

        String[] expected = {"2", "34", "19", "6", "22"};
        List<String> answers = bt.suchThatIterative(new InOrder());
        assertArrayEquals(expected, answers.toArray());
    }

    @Test
    public void mySuchThatRecursiveForInOrderTest() {
		LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
		bt.insertNode("50");
		bt.insertNode("2");
		bt.insertNode("34");
		bt.insertNode("19");
		bt.insertNode("6");
		bt.insertNode("22");

		String[] expected = {"2", "34", "19", "6", "22"};
		List<String> answers = bt.suchThatIterative(new InOrder());
		assertArrayEquals(expected, answers.toArray());
    }

    // you must use this BiFunction in tests that you write for reduceAtDepth/reduceAtDepthIterative (PART 3)
    private static class DoubleMax implements BiFunction<Double, Double, Double> {
        @Override
        public Double apply(Double soFar, Double x) {
            return Math.max(soFar, x);
        }
    }

    // you must use this BiFunction in tests that you write for suchThatRecursive/suchThatIterative (PART 5)
    private static class InOrder implements BiFunction<BinaryTree<String>, Position<String>, Boolean> {
        @Override
        public Boolean apply(BinaryTree<String> t, Position<String> p) {
            Position<String> l = t.left(p);
            Position<String> r = t.right(p);
            if (l != null && l.getElement().compareTo(p.getElement()) > 0)
                return false;
            else if (r != null && r.getElement().compareTo(p.getElement()) < 0)
                return false;
            else
                return true;
        }
    }

    // you must use this BiFunction in tests that you write for suchThatRecursive/suchThatIterative (PART 5)
    private static class IsEven implements BiFunction<BinaryTree<Integer>, Position<Integer>, Boolean> {
        @Override
        public Boolean apply(BinaryTree<Integer> t, Position<Integer> p) {
            return p.getElement() % 2 == 0;
        }
    }
}
