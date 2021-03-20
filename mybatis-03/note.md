## 分页查询
* 导入pagehelper到pom.xml
```xml
<dependency>
	    <groupId>com.github.pagehelper</groupId>
	    <artifactId>pagehelper</artifactId>
	    <version>5.2.0--版本号用最新的就好，但是得保证你的其他配件也是最新版本--</version>
</dependency>
```
* 在mybatis-config.xml中添加插件plugins,注意属性的顺序，顺序不对会报错。这个步骤主要是为了配置pagehelper，配置需要看这个[文档](https://pagehelper.github.io/docs/howtouse/)

```xml
<plugins>
		<plugin interceptor="com.github.pagehelper.PageInterceptor">
		        <property name="autoRuntimeDialect" value="true"/>
		</plugin>
</plugins>
```
* 在代码中使用

```java
	@Test
	public void getUserByLimit(){
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		PageHelper.startPage(3, 3);
		List<User> userList = userMapper.getUserList();
		for(User e : userList) {
			System.out.printf("%s, %s\n",e.getId(), e.getN());
		}
		session.close();
	}
```