package com.chuhui.blazers.smallexample.example.blog.reflectexam;

import java.lang.reflect.Field;

/**
 * Person
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 * <a href="https://chuhuilove.github.io/2019/08/02/java-tec/Java%E4%B8%AD%E7%9A%84%E6%B5%81%E6%B0%93%E6%8A%80%E6%9C%AF-%E5%8F%8D%E5%B0%84/">示例coding</a>
 * @author: 纯阳子
 * @Date: 2019/8/4
 * @Description:
 */
public class Person {
    private int age;
    private String address;
    private int sex;
    private Person mate;

    public Person() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Person getMate() {
        return mate;
    }

    public void setMate(Person mate) {
        this.mate = mate;
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", address='" + address + '\'' + ", sex=" + sex + ", mate=" + mate + '}';
    }

    public static void main(String[] args) {

        try {
            Class<?> personClazz = Class.forName("com.chuhui.blazers.smallexample.example.blog.reflectexam.Person");

            Object person = personClazz.newInstance();
            System.err.println("赋值之前:"+person);

            Field ageFiled = person.getClass().getDeclaredField("age");
            ageFiled.set(person,10);

            System.err.println("赋值之后:"+person);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

}
