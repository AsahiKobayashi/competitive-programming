package lib.technique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  座標圧縮
 */
@SuppressWarnings("unchecked")
class Compress<T extends Number & Comparable<T>> extends HashMap<T,Integer> {

      /**
       * 前処理
       * Number型以下のクラスを対象にするため以下のプリミティブ配列に関してのみサポートしています。
       * 対象) int , long , double
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
       * 各要素を昇順にidを振り分けます (0-indexed)
       * @param array
       */
      private void build(T [] array) {
            AtomicInteger id = new AtomicInteger();
            Arrays.stream(array).distinct().sorted().forEach(v -> this.put(v, id.getAndIncrement()));
      }

      /**
       * 指定した要素の圧縮後のidを取得
       * @param value
       * @return
       */
      public int getId(T value) {
            return this.get(value);
      }

}
