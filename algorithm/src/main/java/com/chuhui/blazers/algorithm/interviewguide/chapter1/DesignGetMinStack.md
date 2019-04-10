# 设计一个有getMin功能的栈

**题目**

实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作

**要求**
1. pop、push、getMin操作的时间复杂度都是O(1)
2. 设计的栈类型可以使用现成的栈结构


## 自己的方式
   
   这个题目乍一看很简单，只需要继承`java.util.Stack`，并且限制类的泛型为`Comparable`的子类，还需要在自定义类里面设置一个接受最小值的泛型类引用`minItem`，在自定义的类里面重写`push`方法，第一次入栈，如果栈为空，则入栈，将第一个入栈的值赋给`minItem`。接下来每次入栈都需要和已经存在的`minItem`使用`compareTo`进行比较，较小者赋给`minItem`，以保证当前所有已经入栈的元素的最小元素为`minItem`。
   
   该方式存在的问题很明显，如果有元素出栈了，如何保证`minItem`还是最小的？
   
   可以这样做，在每次出栈一个元素后，从新遍历栈，获取最小值。
   
   空间复杂度达到O(1)，但是每次出栈的时间复杂度达到O(n) 
   
   参见[`DesignGetMinStack`中`DesignGetMinStack1`类](DesignGetMinStack.java)
   
   
## 书上解答

可以使用两个栈，一个用来保存当前栈中的元素，记为stackData，另一个用于保存每一步的最小值，记为stackMin。

### 第一种方案

* **压入数据规则**
    假设当前数据为item，现将其压入stackData，然后判断stackMin是否为空：
    
    * 如果为空，则item也压入stackMin中
    * 如果不为空，则比较item和stackMin栈顶元素哪一个更小
    * 如果item更小或两者相等，则item也压入stackMin
    * 如果stackMin栈顶元素小，则stackMin不压入任何内容。
    
    stackMin始终保存着和当前stackData栈顶小于或等于的元素
    
    ![](../../../../../../resources/chapter1/1-1.PNG)
    
* **弹出数据规则**

    先弹出stackData的栈顶元素。如果弹出的元素和stackMin栈顶的元素相等，则stackMin弹出也弹出栈顶元素。
    
* **查询当前栈中最小元素值**
    
    由弹出规则和压入规则可知，stackMin的栈顶始终记录着stackData中的最小值。
    
    参见[`DesignGetMinStack`中`DesignGetMinStack2`类](DesignGetMinStack.java)
    
### 第二种方案

* **压入数据规则**
    
    假设当前数据为item，先将其压入stackData中，然后判断stackMin是否为空。
    
    如果为空，则item也压入stackMin中；如果不为空，则判断item和stackMin的栈顶元素哪一个更小。如果item更小或者相等，则item也压入栈中。如果stackMin栈顶的元素小，则重复将stackMin的栈顶元素压入stackMin。
    
    ![](../../../../../../resources/chapter1/1-2.PNG)

* **弹出数据规则**
    
    根据压入规则可以知道，当前stackMin的大小和stackData的大小一致，弹出stackData的同时，亦弹出stackMin。

* **查询当前栈中最小元素值**
    
    当前stackMin的栈顶始终为当前stackData的最小值。

参见[`DesignGetMinStack`中`DesignGetMinStack3`类](DesignGetMinStack.java)


   
    



  

