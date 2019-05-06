package com.chuhui.blazers.algorithm.redblacktree;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashTreeNode {


    public static void main(String[] args) {

        Map<HashMapKeyModel, Integer> map = new HashMap<>();

        System.err.println("start map put ");

        for (int i = 0; i < 60; i++) {

            map.put(new HashMapKeyModel("xcc" + i, i), i);


        }


    }


    public static class HashMapKeyModel {

        private String name;
        private int age;

        public HashMapKeyModel(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            HashMapKeyModel that = (HashMapKeyModel) o;
            return age == that.age &&
                    Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return age%1;
        }
    }


}
