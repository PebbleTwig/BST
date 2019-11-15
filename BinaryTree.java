import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<E> {
    protected Node<E> root;

    /**
     * Constructor - initializes the tree to an empty tree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a new Binary tree with data in its root;
     * leftTree as its left sub tree and rightTree as its right
     * subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null)
            root.left = leftTree.root;
        else root.left = null;

        if (rightTree != null)
            root.right = rightTree.root;
        else
            root.right = null;
    }

    /**
     * prints the elements of the tree rooted at this
     * node in the inorder fashion
     */
    private void inorderPrint(Node<E> aNode) {
        if (aNode == null) return;
        if (aNode.left != null)
            inorderPrint(aNode.left);
        System.out.println(aNode.data);
        if (aNode.right != null)
            inorderPrint(aNode.right);
    }

    /**
     * prints the elements of this tree in the
     * inorder format
     */
    public void inorderPrint() {
        inorderPrint(root);
    }

    /**
     * prints the elements of the tree rooted
     * at this node in the preorder fashion
     */
    private void preorderPrint(Node<E> aNode) {
        if (aNode == null) return;
        System.out.println(aNode.data);
        if (aNode.left != null)
            preorderPrint(aNode.left);
        if (aNode.right != null)
            preorderPrint(aNode.right);
    }

    /**
     * prints the elements of this tree in the
     * preorder format
     */
    public void preorderPrint() {
        preorderPrint(root);
    }

    /**
     * prints the elements of the tree rooted at this
     * node in the postorder fashion
     */
    private void postorderPrint(Node<E> aNode) {
        if (aNode == null) return;
        if (aNode.left != null)
            postorderPrint(aNode.left);
        if (aNode.right != null)
            postorderPrint(aNode.right);
        System.out.println(aNode.data);
    }

    /**
     * prints the elements of this tree in the
     * postorder format
     */
    public void postorderPrint() {
        postorderPrint(root);
    }

    /**
     * Compute the number of nodes in a binary tree
     *
     * @return the number of elements in the tree with the given root
     */
    public int size() {
        return size(root);
    }

    private int size(Node<E> aNode) {
        if (aNode == null) return (0);
        else {
            return (size(aNode.left) + 1 + size(aNode.right));
        }
    }

    /**
     * Compute the height of a binary tree
     *
     * @return the height of the tree with the given root.  If the
     * tree is null, -1 is returned.
     */
    public int height() {
        return height(root);
    }

    private int height(Node<E> aNode) {
        if (aNode == null) {
            return -1;
        }

        int lefth = height(aNode.left);
        int righth = height(aNode.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }

    public Iterator<E> iterator() {
        return new DepthFirstIterator();
    }

    private class DepthFirstIterator implements Iterator<E> {
        private java.util.Stack<Node<E>> fringe = new java.util.Stack<Node<E>>();

        public DepthFirstIterator() {
            if (root != null)
                fringe.push(root);
        }

        public boolean hasNext() {
            return !fringe.empty();
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("tree ran out of elements");

            Node<E> node = fringe.pop();
            if (node.right != null)
                fringe.push(node.right);

            if (node.left != null)
                fringe.push(node.left);

            return node.data;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    protected static class Node<E> {
        // invariant of the Node class:
        // 1.  Each node has data value
        // 2.  The instance variables left and right are references to
        //     node's children
        protected E data;
        protected Node<E> left, right;

        /**
         * Constructor
         *
         * @param initialData data for this node
         */
        public Node(E initialData) {
            data = initialData;
            left = null;
            right = null;
        }

        /**
         * Constructor
         *
         * @param initialData  data for this node
         * @param initialLeft  left child of this node
         * @param initialRight right child of this node
         */
        public Node(E initialData, Node<E> initialLeft, Node<E> initialRight) {
            data = initialData;
            left = initialLeft;
            right = initialRight;
        }

        /**
         * Accessor method to determine whether a node is a leaf
         *
         * @return true if this node is a leaf; false otherwise
         */
        public boolean isLeaf() {
            return ((left == null) && (right == null));
        }


        /**
         * Accessor method to get the data from the leftmost
         * node of the tree below this node.
         *
         * @return the data from the deepest node that can be reached
         * from this node following left links.
         */
        public E getLeftMostData() {
            if (left == null) return data;
            else return left.getLeftMostData();
        }

        /**
         * Accessor method to get the data from the rightmost
         * node of the tree below this node.
         *
         * @return the data from the deepest node that can be reached
         * from this node following right links.
         */
        public E getRightMostData() {
            if (right == null) return data;
            else return right.getRightMostData();
        }

        /**
         * removes the leftmost node of the tree with this
         * node as its root
         *
         * @postcondition The tree starting at this node has
         * had its leftmost node removed (i.e., the deepest
         * node that can be reached by following left links).
         * The return value is a reference to the root of the
         * new (smaller) tree. This return value could be null
         * if the original tree had only node (since that one
         * node has now being removed).
         * <p>
         * Example: If n is a reference to a node in a tree and
         * n has a right child, then we can remove the leftmost
         * node of n's right subtree with this statement
         * <p>
         * n.setRight(n.getRight().removeLeftmost());
         */
        public Node<E> removeLeftMost() {
            if (left == null)
                // the leftmost node is at the root because
                // there is no left child.
                return right;
            else {
                // a recursive call removes the leftmost node from
                // my own left child
                left = left.removeLeftMost();
                return this;
            }
        }

        /**
         * removes the rightmost node of the tree with this
         * node as its root
         *
         * @postcondition The tree starting at this node has
         * had its rightmost node removed (i.e., the deepest
         * node that can be reached by following right links).
         * The return value is a reference to the root of the
         * new (smaller) tree. This return value could be null
         * if the original tree had only node (since that one
         * node has now being removed).
         * <p>
         * Example: If n is a reference to a node in a tree and
         * n has a left child, then we can remove the rightmost
         * node of n's left subtree with this statement
         * n.setLeft(n.getLeft().removeRightmost());
         */
        public Node<E> removeRightMost() {
            if (right == null)
                // the rightmost node is at the root because
                // there is no right child
                return left;
            else {
                // a recursive call removes the rightmost node
                // from my own right child
                right = right.removeRightMost();
                return this;
            }
        }
    }
}

