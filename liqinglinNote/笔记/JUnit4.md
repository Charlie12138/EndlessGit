# JUnit4

#### 在idea中使用junit4:

* 安装junit插件：

  * Unit插件步骤File-->settings-->Plguins-->Browse repositories-->输入JUnit-->选择JUnit Generator V2.0安装

* 使用JUnit插件：
  * 在需要进行单元测试的类中，使用快捷键alt+insert，选择JUnit test，选择JUnit4。 
*  配置生成的test类位置 ：
  * Output Path: ${SOURCEPATH}/[target directory]/${PACKAGE--你待测试类的包名}/${FILENAME}
* Setting-->Orther Settings-->JUnit Generator
  * 点击JUnit4，可以编辑模板 **package test.$entry.packageName;** 可以更改生成的测试类的包名
* 测试由@Test注释来识别
* JUnit4允许我们使用 @Before和@After来注释多个方法（替代JUnit3的setUp() 和tearDown() 方法），这些方法在每个测试方法执行前和执行后都要运行一次，完成初始化字段或准备数据和清除工作。 
* passed 说明没有错误
* @Ignore：忽略的测试方法，标注的含义就是“某些方法尚未完成，暂不参与此次测试”;这样的话测试结果就会提示你有几个测试被忽略，而不是失败。一旦你完成了相应函数，只需要把@Ignore标注删去，就可以进行正常的测试。 
* @Test(timeout=毫秒) 允许程序运行的时间。 
* @Test(excepted=XX.class) 在运行时忽略某个异常。 
