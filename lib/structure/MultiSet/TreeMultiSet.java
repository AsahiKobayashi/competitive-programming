class TreeMultiSet<T extends Comparable<T>> extends TreeMap<T, Long> {

    private long size;

    public TreeMultiSet() {
        super();
        this.size = 0;
    }

    public void add(T element, long count) {
        if (count == 0) return;
        long newCount = this.getOrDefault(element, 0L) + count;
        if (newCount <= 0L) {
            this.remove(element);
        } else {
            this.put(element, newCount);
        }
        size += count;
    }

    public void remove(T element, long count) {
        add(element, -count);
    }

    public void addOne(T element) {
        add(element, 1L);
    }

    public void removeOne(T element) {
        add(element, -1L);
    }

    public long size(boolean isUnq) {
        return isUnq ? super.size() : this.size;
    }

    public T first() {
        return isEmpty() ? null : firstKey();
    }

    public T last() {
        return isEmpty() ? null : lastKey();
    }

    public T pollFirst() {
        if (isEmpty()) return null;
        T first = firstKey();
        removeOne(first);
        return first;
    }

    public T pollLast() {
        if (isEmpty()) return null;
        T last = lastKey();
        removeOne(last);
        return last;
    }

    public T ceiling(T key) {
        return ceilingKey(key);
    }

    public T floor(T key) {
        return floorKey(key);
    }

    public T higher(T key) {
        return higherKey(key);
    }

    public T lower(T key) {
        return lowerKey(key);
    }

    public long count(T key) {
        return getOrDefault(key, 0L);
    }
    
}
