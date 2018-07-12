# JUnit4

## 1、JUint的快速入门

​	xUint是一套基于测试驱动开发的测试框架。其中包括PythonUnit，CppUnit，JUnit。	

## 2、测试方法注意要点

* 测试方法上必须使用@Test进行修饰
* 测试方法必须使用public void 进行修饰，不能带任何参数
* 新建一个源代码目录来存放我们的测试代码
* 测试类的包应该和被测试类保持一致
* 测试单元中的每个方法必须可以独立测试，测试方法间不能有任何的依赖
* 测试类使用Test作为类名的后缀
* 测试方法使用test作为方法名的前缀

## 3、测试失败的两种情况

1. Failure一般由单元测试使用的断言方法判断失败所引起，这经表示测试点发现了问题，就是说程序输出的结果和我们预期的不一样
2. error是由代码异常引起的它，可以产生与测试代码本身的错误，也可以是被测试代码中的一个隐藏的bug。
3. 测试用例不是用来证明你是对的，而是用来证明你没有错。

## 4、JUnit运行流程

1. @BeforeClass修饰的方法会在所有方法被调用前被执行，而且改方法是静态的，所以当测试类被加载后接着就会运行它，而且在内存中他只会存在一份实例，它比较适合加载配置文件。
2. @AfterClass所修饰的方法通常用来对资源的清理，如关闭数据库的连接。
3. @Before和@After会在每个测试方法前后个执行一次。

## 5、JUunit中的常见注解

1. @Test：将一个普通的方法修饰成为一个测试方法。

   ​	@Test（expected=xxx（异常类）.class）:会抛出改异常。

   ​	@Test（timeout=毫秒）：设置执行的时间，用于结束死循环或是性能测试。

2. @BeforeClass：它会在所有的方法运行前被执行，static修饰。

3. @AfterClass：它会在所有的方法运行结束后被执行，static修饰。

4. @Before：会在每一个测试方法前运行一次。

5. @After：会在每一个测试方法后运行一次。

## 6、JUnit测试套件的使用

* 测试套件就是组织测试类一起运行的
  1. 写一个作为测试套件的入口类，这个类里不包含其他的方法
  2. 更改测试运行器Suite.class
  3. 将要测试的类作为数组传入到Suite.SuiteClasses（{}）

## 7、Junit的参数化测试

1. 更改默认的测试运行期为RunWith(Parameterized.class)
2. 声明变量来存放预期值和结果值
3. 声明一个返回值为Collection的公共静态方法，并使用@Parameters进行修饰
4. 为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值

## 8、小结

junit4的使用总结

1. @Test
2. @Test(timeout=毫秒),@Test(expected=异常类)
3. @Ignore
4. @RunWith
5. @BeforeClass @AfterClass @Before @After
6. 测试套件@RunWith（Suite.class）
7. 参数化设置@RunWith（Parameterized.class）