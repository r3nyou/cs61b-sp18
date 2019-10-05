public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        private Node (T x, Node n, Node p) {
            item = x;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i<size; ++i) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        Node remove = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (!isEmpty()) {
            size--;
        }

        return remove.item;
    }

    public T removeLast() {
        Node remove = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (!isEmpty()) {
            size--;
        }

        return remove.item;
    }

    public T get(int index) {
        Node p = sentinel.next;
        for (int i = 0; i < index; ++i) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(--index, p.next);
    }
}
