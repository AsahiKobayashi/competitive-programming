package lib.structure.MultiSet;

import java.util.HashMap;

/**
 * MultiSet : 多重集合
 */
class MultiSet<T> extends HashMap<T,Long> {

        private int size ;

        MultiSet() {
                this.size = 0;
        }
        
        MultiSet(T [] array) {
                this();
                for (T element : array)
                        addOne(element);
        }

        /**
         * @description 複数追加
         * @order 1
         * @param element
         */
        public void add(T element , long count) {
                this.put(element , this.getOrDefault(element, 0L) + count);
                size += count;
                if(this.get(element) < 0L) 
                        this.remove(element);
        }

        /**
         * @description 複数削除
         * @order 1
         * @param element
         */
        public void remove(T element , long count) {
                add(element, -count);
        }

        /**
         * @description 単一追加
         * @order 1
         * @param element
         */
        public void addOne(T element) {
                add(element , 1L);
        }

        /**
         * @description 単一削除
         * @order 1
         * @param element
         */
        public void removeOne(T element) {
                add(element , -1L);
        }

        /**
         * @description この多重集合に含まれる要素の大きさを返します isUnq=true: 種類数 , isUnq=false : 要素数   
         * @order 1 
         * @param isUnq
         * @return
         */
        public long size(boolean isUnq) {
                return isUnq ? this.size() : this.size;
        }

}
