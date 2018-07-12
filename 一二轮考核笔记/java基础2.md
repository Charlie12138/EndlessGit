![image](https://note.youdao.com/favicon.ico)![image](https://note.youdao.com/favicon.ico)1.
+ 以类的名称调用静态的方法：
```
eg：Math.min(88,86)；
```
+ 以引用变量的名称调用非静态的方法:
```
eg:Song t2 = new Song();
t2.play();
```
+ 静态的方法不能调用非静态的变量。
+ 静态的方法也不能调用非静态的方法。
+ 静态变量的值对于所有实例变量来说都相同。
 
2.java中常量是把变量同事标记为**static**和**final**的。

3.**catch**捕获异常的顺序：
如果异常之间有继承顺序,越顶层的类越放后面，或者直接省略多余的catch。

4.try, catch 语句执行顺序：
+ 先执行try，catch，给返回值赋值；
+ 在执行finally
+ return
+ 不管有木有出现异常，finally块中代码都会执行；
+ 当try和catch中有return时，finally仍然会执行；
+ finally是在return后面的表达式运算后执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，管finally中的代码怎么样，返回的值都不会改变，任然是之前保存的值），所以函数返回值是在finally执行前确定的；
+ finally中最好不要包含return，否则程序会提前退出，返回值不是try或catch中保存的返回值。

+ 一张表格对应一个类。

 **泛型**：
+ 泛型只能使用引用类型，不能使用基本类型。
+ 泛型使用时不能使用静态方法或静态属性。
+ 接口中泛型只能使用在方法中，不能使用在全局常量中。
+ 子类为泛型类，父类不指定类型，（泛型的擦除），使用object替换。
+ 要么同时擦除，要么子类大于等于父类类型，不能擦除子类，父类泛型。
+ >**属性类型：**
 
  >父类中，随父类而定。

  >子类中，随子类而定。
  
  >**方法重写：**
  
  >随父类而定。
  
  7.将String方法转换成primitive主数据类型

String |s = "2"|s = "420.24"|s = "true"
---|---|---|---
primitive |int x = Integer.parseInt(s); |Double.parseInt(s);|boolean b = new Boolean("true").booleanValue

### 8.容器：
```
graph TD
A(interface Collection)
B[interface Set   没有顺序不可重复] --> A
C[interface List 有顺序可重复] --> A
D[HashSet] --> B
E[LinkedList] --> C
F[ArrayList] --> C
b(interface Map)
a(HashMap)
a --> b
```
> ==如何选择数据结构：==

> Array:读快改慢

> List:读慢改快

> Hash:两者之间

> Map中的Key是不可重复的

#### Collection方法：
* boolean返回值 ：add(Object o) 和 addAll(Collection c)
* void clear():清楚集合里所有元素，将集合长度变为0；
* bollean contains(Object o):返回集合里是否包含指定元素；
*  bollean containsAll(Collection c)；
*  Iterator iterator();
*  boolean remove(Object o);
*  boolean ramoveAll(Collection c):减集合c
*  boolean retainAll(Collection c):交集
*  int size();
*  Object[] toArray():将集合转换成数组
* forEach(obj -> syso(obj)   ):遍历




9.对建立日志有帮助：
```
FileWriter fw = new FileWriter("d:\\bak\\logfile.log",true);
//true表示可以在后面继续在文件里追加内容，可以用来做日志。

public static void main(String[] args) {
		String s = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			FileWriter fw = new FileWriter("f:\\eclipes\\logfile.log",true);
			PrintWriter log = new PrintWriter(fw);
			while((s = br.readLine()) != null) {
				if(s.equalsIgnoreCase("exit"))break;
				System.out.println(s.toUpperCase());
				log.println("-----");
				log.println(s.toUpperCase());
				log.flush();
			}
			log.println("==="+new Date()+"===");
			log.flush();
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
```

10.==在swing中几乎所有的组件都能够安置其他的组件。==
**11.实例类方法和类方法**
一个方法,如果不加static关键字,那么这个方法是属于类实例的,意思就是他属于类的某个实例,通过这个实例调用它,对类的其他实例不产生影响.在方法前面加static关键字,就代表这个方法属于这个类本身,不属于他的任何实例,意思就是说,这个方法可以不通过实例调用,并且所有的实例都共享这一个方法,对方法的调用各个实例相互可见