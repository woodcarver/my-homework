# java 项目构建过程

## 单个文件的编译
HelloWorld.java
```
import java.util.*;
public class HelloWorld //the class name must the same as file name case sensitivly
{
    public static void main(String[] args)
    {
        System.out.print("hello world!\n");
        for(int i=0; i<10; i++)
            System.out.print(i+"\n");
    }
}
```

shell>javac HelloWorld.java       产生HelloWorld.class文件
shell>java HelloWorld             执行HelloWorld.class

## 多个文件的编译
不过实际中只有一个文件的项目几乎不会出现，现在我们衍生到2个文件的组织和编译。
- 先看第一种情况：所有文件都放在同一个文件夹中，即所有java文件都是平级的。这种编译和一个文件编译方法是一样的，直接编译入口java文件（包含main函数）即可。编译器会自动在当前目录寻找引入类的定义。

HelloWorld.java
```
import java.util.*;
public class HelloWorld{
    public static void main(String[] args){
        DogWorld dg = new DogWorld();
        dg.setname("Mr Steph");
        String dgname=dg.getname();
        System.out.printf("hello %s!\n",dgname);
    }
}
```
DogWorld.java
```
public class DogWorld
{
    private String dogname;
    public void setname(String name)
    {
        this.dogname=name;
    }
    public String getname()
    {
        return this.dogname;
    }
}
```

shell>javac HelloWorld.java     ``产生HelloWorld.class文件``
shell>java HelloWorld           ``执行HelloWorld.class``

- 第二种：当工程的文件达到上千个，放在同一个文件有开始有损工程可读性了，这是开始使用子文件夹来对代码文件模块化组织。

现在我们把DogWorld放入到一个animal文件夹中，工程文文件目录如下：
- HelloWorld.java
- animal
- DogWorld.java

HelloWorld.java
```
import java.util.*;
import animal.*;
public class HelloWorld{
    public static void main(String[] args){
        DogWorld dg = new DogWorld();
        dg.setname("Mr Steph");
        String dgname=dg.getname();
        System.out.printf("hello %s!\n",dgname);
    }
}
```
注意HelloWorld.java多了一行import animal.*代码，用于指定要引入的package。

animal/DogWorld.java
```
package animal;
public class DogWorld
{
    private String dogname;
    public void setname(String name)
    {
        this.dogname=name;
    }
    public String getname()
    {
        return this.dogname;
    }
}
```
DogWorld.java也多了一行package animal，指定把这个类打入到animal package中，否则DogWorld将被打入默认package中。

一下子多出来package和import的概念，我们还是先来探索package，这个给文件指定路径的机制。

**我们先规定一个概念：工程根目录——这个目录的定义是包含main函数的文件的package指定的目录，如果入口文件没有package那么默认就是文件当前所在目录。例如我们的HelloWorld.java没有指定package， 那么根目录就是./**

  当java文件中没有使用package显示指定它属于那个包的时候，它默认是属于根目录的包的，而且不管这个java文件是放在哪里。而显示指定package的时候，它又一定规则，即报名必须和文件的放的位置匹配。如果package animal，那么这个java文件就必须放入到相对于根目录的animal目录下。这样做的一个用处——源文件和中间文件路径可以分离。
  javac的寻找类级别是先寻找xxx.class文件，如果没有那么再寻找xxx.java文件现编译xxx.class。而package其实指定xxx.class文件的路径的，而不是xxx.java文件。

例如上面的例子，你也可以这样编译：
shell>cd animal; javac DogWorld.java  ``用来生产DogWorld.class文件``
shell>cd ../     ``到工程根目录``
shell>javac HelloWorld.java
shell>java HelloWorld

假如你把DogWorld.java的package去掉，那么相当于你是想表达把DogWorld.class放到根目录上去。执行步骤是这样的。
shell>cd animal; javac DogWorld.java  ``用来生产DogWorld.class文件``
shell> mv DogWorld.class ../   ``把DogWorld.class移动到工程根目录去``
shell>cd ../
shell>javac HelloWorld.java
shell>java HelloWorld

而import是用来引入class文件的。它很简单，import xxx，完全就按照xxx来寻找。xxx的写法规则是用.来表示文件的层次。如com.animal那么就再相对于工程根目录的com/animal文件夹。

有趣的是package和import的都是相对于根目录的路径，和代码文件本身所在目录完全无关，只和工程根目录有关。

这些引用规则和c中#include使用大为不同。

## 虚拟机是如何定位类
接下来我们来仔细探讨下虚拟机的定位规则。
