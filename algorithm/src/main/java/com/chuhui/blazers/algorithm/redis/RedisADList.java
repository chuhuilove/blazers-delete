package com.chuhui.blazers.algorithm.redis;


import sun.rmi.log.LogInputStream;

import java.util.*;

/**
 * RedisADList
 * <p>
 * 吾辈既务斯业,便当专心用功;
 * 以后名扬四海,根据即在年轻.
 *
 * @author: 纯阳子
 * @Date: 2019/6/28 0028
 * @Description: redis中通用双向链表的实现
 */
public class RedisADList<E> {


    private ListNode<E> head;
    private ListNode<E> tail;

    private volatile long len;


    static class ListNode<E> {
        private ListNode<E> prev;
        private ListNode<E> next;
        private E value;

        public ListNode() {
        }

        public ListNode(E value) {
            this.value = value;
        }
    }

    public RedisADList() {
        head = null;
        tail = null;
        len = 0;
    }

    public void listEmpty() {
        if (len != 0) {
            ListNode prevNode = head;

            while (prevNode != null) {
                prevNode = prevNode.next;
                len--;
            }
            head = null;
            tail = null;
        }
    }

    /**
     * 将元素添加到{@link RedisADList#head}的前面,作为新的{@link RedisADList#head}
     *
     * @param value
     */
    public void listAddNodeHead(E value) {

        ListNode<E> node = new ListNode<>();
        node.value = value;

        // 如果当前没有元素
        if (len == 0) {
            head = tail = node;
            node.prev = node.next = null;
        } else {
            head.prev = node;
            node.prev = null;
            node.next = head;
            head = node;
        }
        len++;
    }

    /**
     * 添加节点到链表尾,取代现有的{@link RedisADList#tail},添加的节点会成为新的{@link RedisADList#tail}
     *
     * @param value
     */
    public void listAddNodeTail(E value) {

        ListNode<E> node = new ListNode<>(value);
        if (len == 0) {
            head = tail = node;
            node.prev = node.next = null;
        } else {
            node.next = null;
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        len++;
    }


    public static void main(String[] args) {


        RedisADList<Integer> list1 = new RedisADList();

        list1.listAddNodeHead(6);
        list1.listAddNodeHead(7);
        list1.listAddNodeHead(8);
        list1.listAddNodeHead(9);
        list1.listAddNodeHead(10);

        list1.listRotate();

        System.err.println(list1);
        System.err.println(list1);

        RedisADList<Integer> list2 = new RedisADList();

        list2.listAddNodeTail(5);
        list2.listAddNodeTail(4);
        list2.listAddNodeTail(3);
        list2.listAddNodeTail(2);
        list2.listAddNodeTail(1);

        RedisADList list = RedisADList.listJoin(list1, list2);

        System.err.println(list);
        System.err.println(list);

        list.listRotate();



    }

    /**
     * 深拷贝
     * FIXME 深拷贝,思考一下,如何做
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return clone(this);
    }

    private RedisADList<E> clone(RedisADList<E> list) {

        RedisADList<E> clone = new RedisADList<>();
        clone.head = new ListNode<>(head.value);
        clone.tail = new ListNode<>(tail.value);

        ListNode<E> headNext = head.next;
        ListNode<E> newHeadNext = head.next;
        while (headNext != null) {
            newHeadNext = new ListNode<>(headNext.value);
            // FIXME 如何做深拷贝呢
        }

        return clone;
    }

    RedisADList listDup(RedisADList list) {
        return clone(list);
    }

    /**
     * 连接两个链表
     *
     * @param l
     * @param o
     * @return
     */
    public static RedisADList listJoin(RedisADList l, RedisADList o) {

        o.head.prev = l.tail;
        l.tail.next = o.head;
        l.len = l.len + o.len;
        l.tail = o.tail;

        o.head = o.tail = null;
        o.len = 0;
        return l;
    }

    /**
     * 链表自旋转,head和tail相互交换
     * FIXME 2019年6月28日15:00:19 理解错意思了?
     */
    public void listRotate() {

        if (len > 1) {

            ListNode<E> newTail = tail;


            tail = newTail.prev;
            tail.next = null;

            head.prev = newTail;
            newTail.prev = null;
            newTail.next = head;
            head = newTail;

        }

    }

    ListNode<E> listSearchKey( E key){

        return null;
    }
    ListNode<E>  listIndex(long index){
        return null;
    }
    void listDelNode(  ListNode<E> node){

    }

    //    list *listCreate(void);
//    void listRelease(list *list);
//    void listEmpty(list *list);


//    list *listInsertNode(list *list, listNode *old_node, void *value, int after);
//    void listDelNode(list *list, listNode *node);
//    listIter *listGetIterator(list *list, int direction);
//    listNode *listNext(listIter *iter);
//    void listReleaseIterator(listIter *iter);
//    list *listDup(list *orig);
//    listNode *listSearchKey(list *list, void *key);
//    listNode *listIndex(list *list, long index);
//    void listRewind(list *list, listIter *li);
//    void listRewindTail(list *list, listIter *li);
//    void listRotate(list *list);
//    void listJoin(list *l, list *o);


//    typedef struct listNode {
//        struct listNode *prev;
//        struct listNode *next;
//        void *value;
//    } listNode;
//
//    typedef struct listIter {
//        listNode *next;
//        int direction;
//    } listIter;
//
//    typedef struct list {
//        listNode *head; /*头指针*/
//        listNode *tail; /*尾指针*/
//        void *(*dup)(void *ptr);
//    void (*free)(void *ptr); /*节点自己释放,类似于面向对象语言的多态*/
//    int (*match)(void *ptr, void *key);
//        unsigned long len; /*链表的长度*/
//    } list;


}
