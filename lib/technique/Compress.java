package lib.technique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Compress : 座標圧縮
 * 
 * メモリの量を超える値を扱う際に値を圧縮することでメモリ量を削減します
 */
@SuppressWarnings("unchecked")
class Compress<T extends Number & Comparable<T>> extends HashMap<T,Integer> {

      /**
       * @description Number型以下のクラスを対象にするため以下のプリミティブ配列に関してのみサポートしています。
       * @order N
       * @param array
       */
      Compress(Object array) {
            if (array instanceof int []) {
                  build((T[]) Arrays.stream((int[]) array).boxed().toArray(Integer[]::new));
            } else if (array instanceof long[]) {
                  build((T[]) Arrays.stream((long[]) array).boxed().toArray(Long[]::new));
            } else if (array instanceof double[]) {
                  build((T[]) Arrays.stream((double[]) array).boxed().toArray(Double[]::new));
            } else {
                  throw new IllegalArgumentException("サポートしていない型です。: " + array.getClass());
            }
      }

      /**
       * @description 各要素を昇順にidを振り分けます (0-indexed)
       * @order N
       * @param array
       */
      private void build(T [] array) {
            AtomicInteger id = new AtomicInteger();
            Arrays.stream(array).distinct().sorted().forEach(v -> this.put(v, id.getAndIncrement()));
      }

      /**
       * @description 指定した要素の圧縮後のidを取得
       * @order 1
       * @param value
       * @return
       */
      public int getId(T value) {
            return this.get(value);
      }

}
