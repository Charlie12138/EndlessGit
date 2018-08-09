# JSON

##### 3种方式创建json对象，

 1、使用JSONObject对象put方法来构建；

 2、使用HashMap及其子类如treeMap，Map的put方法来构建； 

3、使用JavaBean来构建JSONObject对象。 

```
public class JsonObjectExample {

	public static void main(String[] args) {
//		jsonObject();
//		createJsonByMap();
		createJsonByBean();
	}
	
	
	//使用JSONObject对象put方法来构建
	private static void jsonObject() {
			JSONObject wangxiaoer =new JSONObject();
			Object nullObj=null;
			wangxiaoer.put("name","王二丫");
			wangxiaoer.put("age",25);
			wangxiaoer.put("birthday","1995-99-99");
					wangxiaoer.put("school","南翔");
			wangxiaoer.put("major",new String[]{"造飞机","打坦克"});
			wangxiaoer.put("has_girlfriend",false);
			wangxiaoer.put("car",nullObj);
			wangxiaoer.put("house",nullObj);
			wangxiaoer.put("comment","哈哈哈");
			System.out.println(wangxiaoer.toString());
	}


	//使用HashMap及其子类如Map的put方法来构建
	private static void createJsonByMap(){
		Map<String, Object> wangxiaoer=new HashMap<String,Object>();
		Object nullObj = null;
		wangxiaoer.put("name", "王小二");
		wangxiaoer.put("age", 25.2);
		wangxiaoer.put("birthday", "1990-01-01");
		wangxiaoer.put("school", "蓝翔");
		wangxiaoer.put("major", new String[] { "理发", "挖掘机" });
		wangxiaoer.put("has_girlfriend", false);
		wangxiaoer.put("car", nullObj);
		wangxiaoer.put("house", nullObj);
		wangxiaoer.put("comment", "这是一个注释");
		System.out.println(new JSONObject(wangxiaoer).toString());
	}

	//使用JavaBean来构建JSONObject对象
	private static void createJsonByBean()
	{
		DaShen terence=new DaShen();
		terence.setAge(25.9);
		terence.setBirthday("1990-5-9");
		terence.setSchool("HDU");
		terence.setMajor(new String[]{"Computer","qiqiqiqi"});
		terence.setHas_girlfriend(false);
		terence.setComment("sha,sha,sha,sha……");
		terence.setCar(null);
		terence.setHouse(null);
		System.out.println(new JSONObject(terence));
	}
}
```

Maven依赖： 

```
<dependency> 
	<groupId>org.json</groupId> 
	<artifactId>json</artifactId> 
	<version>20160810</version>
</dependency> 
```

##### 使用Eolinker[进行接口测试](https://blog.csdn.net/GGGGdragon/article/details/78403691)

