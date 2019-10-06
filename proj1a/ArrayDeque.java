public class ArrayDeque<T> {
    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        item = (T[]) new Object[8];
    }

    private int plusOne(int index) {
        return (index + 1) % item.length;
    }

    private int minusOne(int index) {
        return (index - 1 + item.length) % item.length;
    }

    private boolean isFull() {
        return size == item.length;
    }

    private boolean isSparse() {
        return size >= 16 && size < (item.length / 4);
    }

    private void resize(int capacity) {
        T[] newItem = (T[]) new Object[capacity];
        int oldIdx = plusOne(nextFirst);

        for (int newIdx = 0; newIdx < capacity; newIdx++) {
            newItem[newIdx] = item[oldIdx];
            oldIdx = plusOne(oldIdx);
        }

        item = newItem;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private void upSize() {
        resize(size * 2);
    }

    private void downSize() {
        resize(size / 2);
    }

    public void addFirst(T x) {
        if (isFull()) {
            upSize();
        }
        item[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T x) {
        if (isFull()) {
            upSize();
        }
        item[nextLast] = x;
        nextLast = plusOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isSparse()) {
            downSize();
        }

        nextFirst = plusOne(nextFirst);

        if (!isEmpty()) {
            size--;
        }

        return item[nextFirst];
    }

    public T removeLast() {
        if (isSparse()) {
            downSize();
        }

        nextLast = minusOne(nextLast);

        if (!isEmpty()) {
            size--;
        }

        return item[nextLast];
    }

    public T get(int index) {
        int realIdx = (plusOne(nextFirst) + index) % item.length;
        return item[realIdx];
    }
}
