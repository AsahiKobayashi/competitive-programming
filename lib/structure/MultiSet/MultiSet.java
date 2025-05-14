class MultiSet<T> extends HashMap<T,Long> {

    private long size  ;

    public MultiSet() { 
        this.size = 0; 
    }

    public void add(T element , long count) {
        this.put(element , this.getOrDefault(element, 0L) + count);
        size += count;
        if(this.get(element) < 0L) 
            this.remove(element);
    }

    public void remove(T element , long count) {
        add(element, -count);
    }

    public void addOne(T element) {
        add(element , 1L);
    }

    public void removeOne(T element) {
        add(element , -1L);
    }

    public long size(boolean isUnq) {
        return isUnq ? this.size() : this.size;
    }

}
