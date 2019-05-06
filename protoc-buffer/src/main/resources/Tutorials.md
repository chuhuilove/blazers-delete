# Protocol Buffer Basic:Java



通过创建一个简单的示例应用程序,它会告诉您如何操作

* 在`.proto`文件中定义消息格式
* 使用protocol buffer编译
* 使用protocol buffer的Java API读取和写入消息

这不是在Java中使用protocol buffer的详细指南.更多详细参考信息,请看[Protocol Buffer Language Guide](https://developers.google.com/protocol-buffers/docs/proto?hl=zh-CN),[Java API Reference](https://developers.google.com/protocol-buffers/docs/reference/java/?hl=zh-CN),[Java Generated Code Guide](https://developers.google.com/protocol-buffers/docs/reference/java-generated?hl=zh-CN)和[Encoding Reference](https://developers.google.com/protocol-buffers/docs/encoding?hl=zh-CN)


## <span id="whyUserProBuff">为什么使用Protocol Buffers?</span>

我们将要使用的示例是一个非常简单的"address book"应用程序,可以在文件中读取和写入人员的联系人详细信息.每个人员在地址簿都有一个名字,一个id,一个email地址和连续电话号码.

你如何序列化和检索这样的结构化数据?有几种方法可以解决这个问题:

* 使用Java序列化.这是默认的方法,因为它内置在该语言中,但是它有很多众所周知的问题(参见Josh Bloch的《Effective Java》第213页),而且如果需要与用C或Python编写的应用程序共享数据,它也不能很好地工作.

* 您可以发明一种特殊的方法将数据项编码为单个字符串,例如将4个整数编码为"12:3:-23:67".这是一种简单而灵活的方法,尽管它确实需要编写一次性编码和解析代码,而且解析会增加少量运行时成本.这对于编码非常简单的数据最有效.

* 序列化数据到XML.这种方法非常有吸引力,因为XML(某种程度上)是人类可读的,而且有许多语言的解析库.如果您想与其他应用程序/项目共享数据,这是一个很好的选择.然而,XML是出了名的空间密集型,对它进行编码/解码会给应用程序带来巨大的性能损失.此外,导航XML DOM树比通常在类中导航简单字段要复杂得多.


protocol buffer是一种灵活、高效、自动化的解决方案,可以准确地解决上述问题.使用协议缓冲区,可以编写要存储的数据结构的.proto描述.然后,protocol buffer编译器创建一个类,该类使用有效的二进制格式实现协议缓冲区数据的自动编码和解析.生成的类为组成协议缓冲区的字段提供getter和setter方法,并负责作为一个单元读写协议缓冲区的细节.重要的是,协议缓冲区格式支持随着时间的推移扩展格式的想法,使得代码仍然可以读取用旧格式编码的数据.

## 去哪找示例代码

示例代码被包含在源码包中,"examples"目录下面.[点击这里下载](https://developers.google.com/protocol-buffers/docs/downloads?hl=zh-CN)

## 定义你的协议格式

要创建地址簿应用程序,需要从`.proto`文件开始..proto文件中的定义很简单:为要序列化的每个数据结构添加一条*消息*,然后为*消息*中的每个字段指定一个名称和类型.这是定义消息的`.proto`文件addressbook.proto.

[addressbook.proto](#./addressbook.proto)


如您所见,语法类似于C ++或Java.让我们浏览文件的每个部分,看看它的作用.

proto文件以一个`package`声明开始,这有助于防止不同项目之间的命名冲突.在Java中,包名称用作Java包,除非您已经明确指定了`java_package`,就像我们在这里一样.即使你确实提供了`java_package`,你仍然应该定义一个普通的`package`,以避免在Protocol Buffers名称空间和非Java语言中发生名称冲突.

在`package`声明之后,您可以看到两个特定于Java的选项:`java_package`和`java_outer_classname`.`java_package`指定生成的类应该以什么Java包名称存在.如果没有明确指定它,它只是匹配`package`声明给出的包名,但这些名称通常不是合适的Java包名（因为它们通常不以域名开头）.`java_outer_classname`选项定义了类名,它应该包含这个文件中的所有类.如果没有显式地给出`java_outer_classname`,则将通过将文件名转换为驼峰大小写生成它.例如,默认情况下,"my_proto.proto"将使用"MyProto"作为外部类名.

接下来,您有了消息定义.消息只是包含一组类型化字段的聚合.许多标准的简单数据类型都可用作字段类型,包括`bool`,`int32`,`float`,`double`和`string`.您还可以使用其他消息类型作为字段类型向消息中添加更多结构-在上面的示例中,`Person`消息包含`PhoneNumber`消息,而`AddressBook`消息包含`Person`消息.您甚至可以定义嵌套在其他消息中的消息类型-如您所见,`PhoneNumber`类型在`Person`中定义.如果您希望其中一个字段具有预定义的值列表之一,您还可以定义`enum`类型-此处您要指定电话号码可以是`MOBILE`,`HOME`或`WORK`之一.


每个元素上的"=1","=2"标记标识该字段在二进制编码中使用的唯一"标记".标记号1-15需要的编码字节比编号高的少一个,因此,作为一种优化,您可以决定对常用或重复的元素使用这些标记,而对不常用的可选元素使用标记16或更高.重复字段中的每个元素都需要重新编码标记号,因此重复字段特别适合进行这种优化.

每个字段必须用下列修饰符之一进行注释:

* **`required`**:必须为该字段提供一个值,否则该消息将被视为"未初始化".尝试构建一个未初始化的消息将抛出`RuntimeException`.解析一个未初始化的消息将抛出`IOException`.除此之外,必填字段的行为与可选字段完全相同.
* **`optional`**:字段可以设置,也可以不设置.如果未设置可选字段值,则使用默认值.对于简单类型,您可以指定自己的默认值,就像我们在示例中为电话号码**类型**所做的那样.否则,将使用系统默认值:数值类型为0,字符串为空字符串,bools为false.对于嵌入式消息,默认值总是消息的"默认实例"或"原型",它没有设置任何字段.调用访问器获取未显式设置的可选(或必需)字段的值总是返回该字段的默认值.
* **`repeated`**:该字段可以重复任意次数(包括0次).重复值的顺序将保留在协议缓冲区中.将重复字段看作动态大小的数组.

> 必填永远是必须的,你应该非常小心地按照要求标记字段.


## 编译你的Protocol Buffers

既然你有一个`.proto`,你需要做的下一件事是生成你需要读取和写入`AddressBook`(以及`Person`和`PhoneNumber`)消息所需的类.为此,您需要在.proto上运行协议缓冲区编译器protoc:

1. 如果你没有安装编译器,[下载软件包](https://developers.google.com/protocol-buffers/docs/downloads?hl=zh-CN)并遵循README文件中的说明.
2. 现在运行编译器,指定源目录(应用程序源代码所在的位置——如果不提供值,则使用当前目录)、目标目录(希望生成的代码所在的位置;通常与`$SRC_DIR`相同),以及`.proto`的路径.在这种情况下,你……

```shell
protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto
```
因为需要Java类,所以使用`java_out`选项.

这将在指定的目标目录中生成`com/example/tutorial/AddressBookProtos.java`.

## Protocol Buffer API

让我们看看生成的一些代码,看看编译器为您创建了哪些类和方法.如果你查看`AddressBookProtos.java`,你会发现它定义了一个名为`AddressBookProtos`的类,嵌套在其中的是您在`addressbook.proto`中指定的每个消息的类.每个类都有自己的`Builder`,用于创建该类的实例.您可以在下面的["Builder vs Message"](#builderVsMessages)部分中找到有关构建器的更多信息.

消息和构造器都为消息的每个字段自动生成访问器方法;消息只有getter,而构建器同时具有getter和setter.下面是Person类的一些访问器(为简洁起见省略了实现):

```java
// required string name = 1;
public boolean hasName();
public String getName();

// required int32 id = 2;
public boolean hasId();
public int getId();

// optional string email = 3;
public boolean hasEmail();
public String getEmail();

// repeated .tutorial.Person.PhoneNumber phones = 4;
public List<PhoneNumber> getPhonesList();
public int getPhonesCount();
public PhoneNumber getPhones(int index);
```
同时,`Person.Builder`拥有相同的getter加setter:
```java
// required string name = 1;
public boolean hasName();
public java.lang.String getName();
public Builder setName(String value);
public Builder clearName();

// required int32 id = 2;
public boolean hasId();
public int getId();
public Builder setId(int value);
public Builder clearId();

// optional string email = 3;
public boolean hasEmail();
public String getEmail();
public Builder setEmail(String value);
public Builder clearEmail();

// repeated .tutorial.Person.PhoneNumber phones = 4;
public List<PhoneNumber> getPhonesList();
public int getPhonesCount();
public PhoneNumber getPhones(int index);
public Builder setPhones(int index, PhoneNumber value);
public Builder addPhones(PhoneNumber value);
public Builder addAllPhones(Iterable<PhoneNumber> value);
public Builder clearPhones();
```
如您所见,每个字段都有简单的JavaBeans样式的getter和setter.对于每个单独的字段,也有getter方法,如果设置了该字段,则返回true.最后,每个字段都有一个`clear`方法,可以将字段取消设置为空状态.

重复字段有一些额外的方法——`Count`方法(也就是缩写列表的大小),getter和setter方法获取或设置一个特定的元素列表的索引,一个方法添加一个新元素添加到列表,以及一个`addAll`方法将整个容器的元素添加到列表中. 

注意这些访问器方法如何使用驼峰式命名,即使`.proto`文件使用带下划线的小写.此转换由协议缓冲区编译器自动完成,以便生成的类与标准Java样式约定匹配.在.proto文件中,字段名应该始终使用小写下划线;这确保了在所有生成的语言中都有良好的命名习惯.
   

### <span id="enumAndNested">枚举和嵌套类
生成的代码包含一个嵌套在`Person`中的`PhoneType` Java 5枚举:
```java
public static enum PhoneType {
  MOBILE(0, 0),
  HOME(1, 1),
  WORK(2, 2),
  ;
  ...
}
```
正如您所期望的那样,生成嵌套类型`Person.PhoneNumber`,作为`Person`中的嵌套类.



### <span id="builderVsMessages">Builders vs. Messages
协议缓冲区编译器生成的消息类都是不可变的.一旦构造了消息对象,就不能像修改Java`String`一样修改它.要构造消息,必须首先构造一个构造器,将希望设置的任何字段设置为所选的值,然后调用构造器的`build()`方法.

您可能已经注意到构建器的每个修改消息的方法都会返回另一个构建器.返回的对象实际上是调用方法的相同构造器.返回它是为了方便,这样您就可以在一行代码中将几个setter串在一起.

下面是一个如何创建`Person`实例的例子:
```java
Person john =
  Person.newBuilder()
    .setId(1234)
    .setName("John Doe")
    .setEmail("jdoe@example.com")
    .addPhones(
      Person.PhoneNumber.newBuilder()
        .setNumber("555-4321")
        .setType(Person.PhoneType.HOME))
    .build();
```

### Standard Message Methods

每个message和builder类还包含许多其他方法,允许您检查或操作整个消息,包括:

* `isInitialized()`:检查是否设置了所有必需的字段.
* `toString()`:返回消息的可读表示形式,对调试特别有用.
* `mergeFrom(Message other)`:（仅限构建器）将其他内容合并到此消息中,覆盖单个标量字段,合并复合字段以及连接重复字段.
* `clear()`:（仅限构建器）将所有字段清除回空状态.
这些方法实现了所有Java消息和构建器共享的`Message`和`Message.Builder`接口.更多信息,[complete API documentation for `Message`](https://developers.google.com/protocol-buffers/docs/reference/java/com/google/protobuf/Message?hl=zh-CN)
### Parsing and Serialization
最后,每个协议缓冲区类都有使用协议缓冲区[二进制格式](https://developers.google.com/protocol-buffers/docs/encoding?hl=zh-CN)编写和读取所选类型的消息的方法.这些包括:

* `byte[] toByteArray();`:序列化消息并返回包含其原始字节的字节数组.
* `static Person parseFrom(byte[] data);`:解析来自给定字节数组的消息.
* `void writeTo(OutputStream output);`:序列化消息并将其写入`OutputStream`.
* `static Person parseFrom(InputStream input);`:从`InputStream`读取和解析消息.

这只是为解析和序列化提供的几个选项.请参阅[Message API参考](https://developers.google.com/protocol-buffers/docs/reference/java/com/google/protobuf/Message?hl=zh-CN)以获取完整列表.

> 














## 写消息

现在,尝试使用你的protocol buffer类.您希望地址簿应用程序能够做的第一件事是将个人详细信息写入地址簿文件.为此,您需要创建并填充协议缓冲区类的实例,然后将它们写入输出流.

这是一个从文件读取`AddressBook`的程序,根据用户输入向其添加一个新`Person`,并将新的`AddressBook`再次写回文件.
```java

import com.example.tutorial.AddressBookProtos.AddressBook;
import com.example.tutorial.AddressBookProtos.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

class AddPerson {
  // This function fills in a Person message based on user input.
  static Person PromptForAddress(BufferedReader stdin,
                                 PrintStream stdout) throws IOException {
    Person.Builder person = Person.newBuilder();

    stdout.print("Enter person ID: ");
    person.setId(Integer.valueOf(stdin.readLine()));

    stdout.print("Enter name: ");
    person.setName(stdin.readLine());

    stdout.print("Enter email address (blank for none): ");
    String email = stdin.readLine();
    if (email.length() > 0) {
      person.setEmail(email);
    }

    while (true) {
      stdout.print("Enter a phone number (or leave blank to finish): ");
      String number = stdin.readLine();
      if (number.length() == 0) {
        break;
      }

      Person.PhoneNumber.Builder phoneNumber =
        Person.PhoneNumber.newBuilder().setNumber(number);

      stdout.print("Is this a mobile, home, or work phone? ");
      String type = stdin.readLine();
      if (type.equals("mobile")) {
        phoneNumber.setType(Person.PhoneType.MOBILE);
      } else if (type.equals("home")) {
        phoneNumber.setType(Person.PhoneType.HOME);
      } else if (type.equals("work")) {
        phoneNumber.setType(Person.PhoneType.WORK);
      } else {
        stdout.println("Unknown phone type.  Using default.");
      }

      person.addPhones(phoneNumber);
    }

    return person.build();
  }

  // Main function:  Reads the entire address book from a file,
  //   adds one person based on user input, then writes it back out to the same
  //   file.
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
      System.exit(-1);
    }

    AddressBook.Builder addressBook = AddressBook.newBuilder();

    // Read the existing address book.
    try {
      addressBook.mergeFrom(new FileInputStream(args[0]));
    } catch (FileNotFoundException e) {
      System.out.println(args[0] + ": File not found.  Creating a new file.");
    }

    // Add an address.
    addressBook.addPeople(
      PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                       System.out));

    // Write the new address book back to disk.
    FileOutputStream output = new FileOutputStream(args[0]);
    addressBook.build().writeTo(output);
    output.close();
  }
}

```


## 读消息

当然,如果您无法从中获取任何信息,那么地址簿就不会有多大用处！这个例子读取上面例子创建的文件并打印其中的所有信息.
```java
import com.example.tutorial.AddressBookProtos.AddressBook;
import com.example.tutorial.AddressBookProtos.Person;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

class ListPeople {
  // Iterates though all people in the AddressBook and prints info about them.
  static void Print(AddressBook addressBook) {
    for (Person person: addressBook.getPeopleList()) {
      System.out.println("Person ID: " + person.getId());
      System.out.println("  Name: " + person.getName());
      if (person.hasEmail()) {
        System.out.println("  E-mail address: " + person.getEmail());
      }

      for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
        switch (phoneNumber.getType()) {
          case MOBILE:
            System.out.print("  Mobile phone #: ");
            break;
          case HOME:
            System.out.print("  Home phone #: ");
            break;
          case WORK:
            System.out.print("  Work phone #: ");
            break;
        }
        System.out.println(phoneNumber.getNumber());
      }
    }
  }

  // Main function:  Reads the entire address book from a file and prints all
  //   the information inside.
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
      System.exit(-1);
    }

    // Read the existing address book.
    AddressBook addressBook =
      AddressBook.parseFrom(new FileInputStream(args[0]));

    Print(addressBook);
  }
}



```


## 扩展Protocol Buffer

在发布使用协议缓冲区的代码之后,您迟早会希望"改善"协议缓冲区的定义.如果您希望新的缓冲区向后兼容,而旧的缓冲区向前兼容(您几乎肯定希望这样),那么您需要遵循一些规则.在新版本的协议缓冲区中:

* *不得*更改任何现有字段的标记号.
* *不得*添加或删除任何必填字段.
* *可以*删除可选或重复的字段.
* *可以*添加新的可选或重复字段,但必须使用新标签号(即标记号从未在此协议缓冲区中使用,甚至不包括已删除的字段).

（这些规则有[一些例外](https://developers.google.com/protocol-buffers/docs/proto?hl=zh-CN#updating),但它们很少使用.）

如果您遵循这些规则,旧代码将很高兴地阅读新消息,并简单地忽略任何新字段.对于旧代码,已删除的可选字段将只具有其默认值,删除的重复字段将为空.新代码也将透明地读取旧消息.但是,请记住,新的可选字段不会出现在旧消息中,因此您需要显式地检查它们是否用`has_`设置,或者在`.proto`文件中提供一个合理的默认值,在标记号后面加上`[default = value]`.如果未为可选元素指定默认值,则使用特定于类型的默认值:比如String,其默认值就是一个字符串.比如boolean,其默认值为false,数值类型,其默认值是0.还请注意,如果您添加了一个新的重复字段,您的新代码将无法判断它是空的(根据新代码)还是从未设置过(根据旧代码),因为它没有`has_`标志.



## 高级用法

协议缓冲区的用途不仅仅是简单的访问器和序列化.请务必浏览[Java API参考](https://developers.google.com/protocol-buffers/docs/reference/java/?hl=zh-CN),以了解您可以使用它们做些什么.

协议消息类提供的一个关键特性是反射.您可以迭代消息的字段并操纵它们的值,而无需针对任何特定的消息类型编写代码.您可以迭代消息的字段并操纵它们的值,而无需针对任何特定的消息类型编写代码.使用反射的一种非常有用的方法是将协议消息转换为与其他编码（例如XML或JSON）之间的转换.反射更高级的用途可能是查找同一类型的两个消息之间的差异,或者开发一种“协议消息正则表达式”,您可以在其中编写匹配特定消息内容的表达式.如果您运用自己的想象力,可以将协议缓冲区应用于比您最初预期更广泛的问题！

反射作为`Message`和`Message.Builder`接口的一部分提供.
