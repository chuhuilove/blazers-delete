# MAP



## HashMap

[HashMap jdk1.8.0_171](http://note.youdao.com/noteshare?id=e06fb8cddf3e1a7cba2e45756b13b0bb&sub=FD83BD7A7A70451C97D92F62E68D2B16)

[沉淀再出发：java中的HashMap、ConcurrentHashMap和Hashtable的认识](http://www.cnblogs.com/zyrblog/p/9881958.html)


###  构造函数

```java
 
    /**
    * 用指定的初始化容量和指定的负载因子构造一个HashMap 
    * 
    */
    
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * 用指定的初始化容量和默认的负载因子(0.75)构造一个空的HashMap
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 用默认的初始化容量和默认的负载因子构造一个空的HashMap
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     *
     * @param   m the map whose mappings are to be placed in this map
     * @throws  NullPointerException if the specified map is null
     * 
     * 
     * 
     * 
     */
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
```