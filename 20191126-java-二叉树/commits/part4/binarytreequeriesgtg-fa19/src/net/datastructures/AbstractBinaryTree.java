/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An abstract base class providing some functionality of the BinaryTree interface.
 *
 * The following five methods remain abstract, and must be implemented
 * by a concrete subclass: size, root, parent, left, right.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E>
                                             implements BinaryTree<E> {

  /**
   * Returns the Position of p's sibling (or null if no sibling exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the sibling (or null if no sibling exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> sibling(Position<E> p) {
    Position<E> parent = parent(p);
    if (parent == null) return null;                  // p must be the root
    if (p == left(parent))                            // p is a left child
      return right(parent);                           // (right child might be null)
    else                                              // p is a right child
      return left(parent);                            // (left child might be null)
  }

  /**
   * Returns the number of children of Position p.
   *
   * @param p    A valid Position within the tree
   * @return number of children of Position p
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public int numChildren(Position<E> p) {
    int count=0;
    if (left(p) != null)
      count++;
    if (right(p) != null)
      count++;
    return count;
  }

  /**
   * Returns an iterable collection of the Positions representing p's children.
   *
   * @param p    A valid Position within the tree
   * @return iterable collection of the Positions of p's children
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public Iterable<Position<E>> children(Position<E> p) {
    List<Position<E>> snapshot = new ArrayList<>(2);    // max capacity of 2
    if (left(p) != null)
      snapshot.add(left(p));
    if (right(p) != null)
      snapshot.add(right(p));
    return snapshot;
  }

  /**
   * Adds positions of the subtree rooted at Position p to the given
   * snapshot using an inorder traversal
   * @param p       Position serving as the root of a subtree
   * @param snapshot  a list to which results are appended
   */
  private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    if (left(p) != null)
      inorderSubtree(left(p), snapshot);
    snapshot.add(p);
    if (right(p) != null)
      inorderSubtree(right(p), snapshot);
  }

  /**
   * Returns an iterable collection of positions of the tree, reported in inorder.
   * @return iterable collection of the tree's positions reported in inorder
   */
  public Iterable<Position<E>> inorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      inorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return snapshot;
  }

  /**
   * Returns an iterable collection of the positions of the tree using inorder traversal
   * @return iterable collection of the tree's positions using inorder traversal
   */
  @Override
  public Iterable<Position<E>> positions() {
    return inorder();
  }

  /* Returns the result of combining all elements at the given depth
  using the given BiFunction and initialValue
   */
  public E reduceAtDepthRecursive(int depth, BiFunction<E, E, E> f, E initialValue) {
      return reduceAtDepthRecursive(root(), depth, 0, f, initialValue);
  }

  private E reduceAtDepthRecursive(Position<E> root, int targetDepth, int curDepth, BiFunction<E, E, E> f, E initialValue){
    if (root == null || curDepth>targetDepth) {
      return initialValue;
    }
    if (targetDepth == curDepth) {
      return root.getElement();
    }
    E res = initialValue;
    for (Position<E> node:this.children(root)) {
      res = f.apply(res, reduceAtDepthRecursive(node, targetDepth, curDepth + 1, f, initialValue));
    }
    return res;
  }

  /* Returns the result of combining all elements at the given depth
  using the given BiFunction and initialValue
   */
  public E reduceAtDepthIterative(int depth, BiFunction<E, E, E> f, E initialValue) {
    LinkedList<Position<E>> list = new LinkedList<>();
    E res = initialValue;
    int curDepth = 0;
    list.push(root());

    while (curDepth < depth && list.isEmpty() == false) {
      LinkedList<Position<E>> tmp = new LinkedList<>();

      for (Position<E> node : list) {
        for (Position<E> child:children(node)) {
          tmp.push(child);
        }
      }

      curDepth++;
      list = tmp;
    }

    for (Position<E> node : list) {
      res = f.apply(res, node.getElement());
    }

    return res;
  }

  /* Returns the List of elements for which the given BiFunction returns true
   */
  public List<E> suchThatIterative(BiFunction<BinaryTree<E>, Position<E>, Boolean> p) {
    return null;
  }

  /* Returns the List of elements for which the given BiFunction returns true
   */
  public List<E> suchThatRecursive(BiFunction<BinaryTree<E>, Position<E>, Boolean> p) {
    // this list will be filled by the helper method
    List<E> ll = new LinkedList<>();
    if (!this.isEmpty()) suchThatRecursiveHelper(p, ll, this.root());
    return ll;
  }

  private void suchThatRecursiveHelper(BiFunction<BinaryTree<E>, Position<E>, Boolean> p, List<E> results, Position<E> n) {
    if (p.apply(this, n)) {
      results.add(n.getElement());
    }
    if (left(n) != null) {
        suchThatRecursiveHelper(p, results, left(n));
    }
    if (right(n) != null) {
      suchThatRecursiveHelper(p, results, right(n));
    }
  }


}
