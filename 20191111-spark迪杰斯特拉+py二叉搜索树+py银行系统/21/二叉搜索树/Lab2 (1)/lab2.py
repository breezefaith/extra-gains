class Node(object):
    def __init__(self, data):
        self.parent = None
        self.left = None
        self.right = None
        self.data = data


class Tree(object):
    # Binary Search Tree
    # class constants
    PREORDER = 1
    INORDER = 2
    POSTORDER = 3

    def __init__(self):
        # Do not create any other private variables.
        # You may create more helper methods as needed.
        self.root = None

    def print(self):
        # Print the data of all nodes in order
        self.__print(self.root)

    def __print(self, curr_node):
        # Recursively print a subtree (in order), rooted at curr_node
        if curr_node is not None:
            self.__print(curr_node.left)
            self.__print(curr_node.right)
            print(str(curr_node.data), end=' ')  # save space

    def __inner_insert(self, root, data):
        if root is None:
            root = Node(data)
        elif data < root.data:
            root.left = self.__inner_insert(root.left, data)
            root.left.parent = root;
        elif data > root.data:
            root.right = self.__inner_insert(root.right, data)
            root.right.parent = root;

        return root

    def insert(self, data):
        # Find the right spot in the tree for the new node
        # Make sure to check if anything is in the tree
        # Hint: if a node n is null, calling n.getData() will cause an error
        self.root = self.__inner_insert(self.root, data)

    def __inner_min(self, root):
        if root.left:
            return self.__inner_min(root.left)
        else:
            return root

    def min(self):
        # Returns the minimum dataue held in the tree
        # Returns None if the tree is empty
        if self.root is None:
            return None
        return self.__inner_min(self.root)

    def __inner_max(self, root):
        if root.right:
            return self.__inner_max(root.right)
        else:
            return root

    def max(self):
        # Returns the maximum dataue held in the tree
        # Returns None if the tree is empty
        if self.root is None:
            return None
        return self.__inner_max(self.root)

    def __inner_query(self, root, data):
        if root is None:
            return None
        if root.data == data:
            return root
        elif data < root.data:
            return self.__inner_query(root.left, data)
        elif data > root.data:
            return self.__inner_query(root.right, data)

    def __find_node(self, data):
        # returns the node with that particular data dataue else returns None
        return self.__inner_query(self.root, data)

    def contains(self, data):
        # return True of node containing data is present in the tree.
        # otherwise, return False.
        # you may use a helper method __find_node() to find a particular node with the data dataue and return that node
        return self.__find_node(data) is not None

    def __iter__(self):
        # iterate over the nodes with inorder traversal using a for loop
        return self.inorder()

    def inorder(self):
        # inorder traversal : (LEFT, ROOT, RIGHT)
        return self.__traverse(self.root, Tree.INORDER)

    def preorder(self):
        # preorder traversal : (ROOT, LEFT, RIGHT)
        return self.__traverse(self.root, Tree.PREORDER)

    def postorder(self):
        # postorder traversal : (LEFT, RIGHT, ROOT)
        return self.__traverse(self.root, Tree.POSTORDER)

    def __inner_inorder(self, root, l):
        if root is None:
            return l
        else:
            l = self.__inner_inorder(root.left, l)
            l.append(root.data)
            l = self.__inner_inorder(root.right, l)
            return l

    def __inner_preorder(self, root, l):
        if root is None:
            return l
        else:
            l.append(root.data)
            l = self.__inner_preorder(root.left, l)
            l = self.__inner_preorder(root.right, l)
            return l

    def __inner_postorder(self, root, l):
        if root is None:
            return l
        else:
            l = self.__inner_postorder(root.left, l)
            l = self.__inner_postorder(root.right, l)
            l.append(root.data)
            return l

    def __traverse(self, curr_node, traversal_type):
        # helper method implemented using generators
        # all the traversals can be implemented using a single method
        l = []
        if traversal_type == self.PREORDER:
            return iter(self.__inner_preorder(curr_node, l))
        elif traversal_type == self.INORDER:
            return iter(self.__inner_inorder(curr_node, l))
        elif traversal_type == self.POSTORDER:
            return iter(self.__inner_postorder(curr_node, l))
        # return self

    def find_successor(self, data):
        # helper method to implement the delete method but may be called on its own
        # if the right subtree of the node is nonempty,then the successor is just 
        # the leftmost node in the right subtree.
        # If the right subtree of the node is empty,then go up the tree until a node that is
        # the left child of its parent is encountered.
        node = self.__find_node(data)

        if node is None:
            return None
        elif node.right is not None:
            return self.__inner_min(node.right)
        elif node.right is None:
            parent = node.parent
            while parent is not None and node == parent.right:
                node = parent
                parent = node.parent
            return parent

    def __inner_del(self, root):
        if root.right is None and root.left is None:
            if root.parent.left == root:
                root.parent.left = None
            if root.parent.right == root:
                root.parent.right = None
        elif root.right is None and root.left is not None:
            if root.parent.left == root:
                root.parent.left = root.left
            if root.parent.right == root:
                root.parent.right = root.left
            root.left.parent = root.parent
        elif root.right is not None and root.left is None:
            if root.parent.left == root:
                root.parent.left = root.right
            if root.parent.right == root:
                root.parent.right = root.right
            root.right.parent = root.parent
        else:
            temp = self.find_successor(root.data)
            if temp is None:
                return
            root.data = temp.data
            self.__inner_del(temp)

    def delete(self, data):
        # Find the node to delete.
        # If the dataue specified by delete does not exist in the tree, then don't change the tree.
        # If you find the node and ...
        #  a) The node has no children, just set it's parent's pointer to Null.
        #  b) The node has one child, make the nodes parent point to its child.
        #  c) The node has two children, replace it with its successor, and remove
        #       successor from its previous location.
        # Recall: The successor of a node is the left-most node in the node's right subtree.
        # Hint: you may want to write a new method, findSuccessor() to find the successor when there are two children
        if self.root is None:
            raise KeyError
        node = self.__find_node(data)
        if node is not None:
            self.__inner_del(node)
