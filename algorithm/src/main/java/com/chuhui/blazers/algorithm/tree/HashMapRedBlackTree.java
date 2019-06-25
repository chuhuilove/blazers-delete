package com.chuhui.blazers.algorithm.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * HashMapRedBlackTree
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/6/25 0025
 * @Description: HashMap中的红黑树
 */
public class HashMapRedBlackTree {


    public static void main(String[] args) {


        HashMap<CustomerModel, Integer> map = new HashMap<>(16);

        //跟踪代码,查看红黑树的构造
        Arrays.stream(new Integer[]{45, 12, 38, 49, 10, 37, 9, 19, 22, 83, 67, 70, 11})
                .forEach(e -> map.put(new CustomerModel(e),e));


        System.err.println(map);
        System.err.println(map);



    }


   static protected class CustomerModel {

        private Integer key;

        public CustomerModel(Integer key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CustomerModel that = (CustomerModel) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

}
