import java.io.*;

public class BinarySearchTree<E extends Comparable<E>>
        extends BinaryTree<E> implements SearchTree<E> {
    // this version uses iteration instead of retempsion in search,
    // insert and delete

    // denotes whether the addition is successful
    // protected boolean addReturn;

    // denotes whether the deletion is successful
    // protected boolean deleteReturn;

    /**
     * Constructor - initializes the tree to an empty tree
     */
    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(E data, BinarySearchTree<E> left, BinarySearchTree<E> right) {
        super(data, left, right);
    }

    /**
     * Searches for a given element in the binary search tree
     *
     * @param someElement element to be searched
     * @return true - if someElement is found in the tree; false
     * otherwise
     */
    // Complexity: O(h) - where h is the height of the tree.
    // In the worst case it could be O(n).  But on average
    // we can expect a complexity of O(log n)
    public boolean contains(E someElement) {
        Node<E> temp = root;
        while (temp.left != null && temp.right != null) {
            if (temp.data.equals(someElement)) {
                return true;
            } else {
                if (temp.left != null && temp.right != null) {
                    if (someElement.compareTo(temp.left.data) < someElement.compareTo(temp.right.data)) {
                        temp = temp.left;
                    } else {
                        temp = temp.right;
                    }
                } else if (someElement.compareTo(temp.data) < temp.data.compareTo(someElement)) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
        }
        return false;
    }

    /**
     * Searches for a given element in the binary search tree
     *
     * @param someElement element to be searched
     * @param count       keeps track of the number of comparisons
     *                    for this search
     * @return E - if someElement is found in the tree; null
     * otherwise
     */
    // Complexity: O(h) - where h is the height of the tree.
    // In the worst case it could be O(n).  But on average
    // we can expect a complexity of O(log n)
    public E find(E someElement, IntObject count) {
        return find(someElement, count, (Node<Book>) root);
    }

    private E find(E someElement, IntObject count, Node<Book> temp) {
        if(temp == null){
            return null;
        }
        Book temp2 = new Book();
        temp2.setIsbn(((String) temp.data.getIsbn()));
        if (temp2.getIsbn().equals(someElement)) {
            return (E) temp.data;
        }
        count.setData(count.getData() + 1);
        if (someElement.compareTo((E) temp.data) < temp.data.compareTo((Book) someElement)) {
            count.setData(count.getData() + 1);
            find(someElement, count, temp.left);
        } else {
            count.setData(count.getData() + 1);
            find(someElement, count, temp.right);
        }
        return null;
    }

    /**
     * inserts an element into a BST
     *
     * @param someElement element that needs to be inserted
     * @param //count     keeps track of the number of comparisons
     *                    done for this insert
     * @return true if the insertion is successful, false otherwise
     */
    // Complexity: O(h) - where h is the height of the tree.
    // In the worst case it could be O(n).  But on average
    // we can expect a complexity of O(log n)
    public boolean add(E someElement) {
        IntObject count = new IntObject(0);
        return add(someElement, count);
    }

    private boolean add(E someElement, IntObject count) {
        Node<E> temp = root;
        Node<E> a = new Node<E>(someElement);
        if (size() == 0) {
            root = a;
        } else {
            boolean done = false;
            while (done != true) {
                if (someElement.compareTo(temp.data) < temp.data.compareTo(someElement)) {
                    if (temp.left != null) {
                        temp = temp.left;
                        count.setData(count.getData() + 1);
                    } else {
                        temp.left = a;
                        return true;
                    }
                } else {
                    if (temp.right != null) {
                        temp = temp.right;
                        count.setData(count.getData() + 1);
                    } else {
                        temp.right = a;
                        return true;
                    }
                }
            }
            if (someElement.compareTo(temp.data) < temp.data.compareTo(someElement)) {
                temp.left = a;
                count.setData(count.getData() + 1);
            } else {
                temp.right = a;
                count.setData(count.getData() + 1);
            }
        }
        return true;
    }

    /**
     * removes an element from a BST
     *
     * @param someElement element that needs to be deleted
     * @return returns true if someElement is found in the tree and is
     * successfully deleted; returns false if someElement is
     * not found in the tree.
     */
    // Complexity: O(h) - where h is the height of the tree.
    // In the worst case it could be O(n).  But on average
    // we can expect a complexity of O(log n)
    public boolean remove(E someElement) {
        return remove(someElement, root);
    }

    private boolean remove(E someElement, Node<E> node) {
        if (contains(someElement) == false) {
            return false;
        } else {
            Node<E> temp = node;
            while (temp != null) {
                if (someElement.compareTo(temp.data) < temp.data.compareTo(someElement)) {
                    if (temp.left != null && temp.left.data.equals(someElement)) {
                        remove(someElement, temp.left);
                        break;
                    }
                    temp = temp.left;
                } else if (someElement.compareTo(temp.data) > temp.data.compareTo(someElement)) {
                    if (temp.right != null && temp.right.data.equals(someElement)) {
                        remove(someElement, temp.right);
                        break;
                    }
                    temp = temp.right;
                    //} else {
                    //  remove(someElement, temp);
                    //break;
                }
            }
        }
        return true;
    } // delete

    /**
     * @return the minimum element in the Set
     */
// Complexity: O(h) - where h is the height of the tree.
// In the worst case it could be O(n).  But on average
// we can expect a complexity of O(log n)
    public E first() {
        Node<E> temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.data;
    }

    /**
     * @return the maximum element in the Set
     */
// Complexity: O(h) - where h is the height of the tree.
// In the worst case it could be O(n).  But on average
// we can expect a complexity of O(log n)
    public E last() {
        Node<E> temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.data;
    }

    public E delete(E someElement) {
        remove(someElement);
        return someElement;
    }

}


