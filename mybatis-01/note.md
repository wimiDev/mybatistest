## 新建maven程序

## pom.xml配置

### 导入mybatis

```xml 
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.5.6</version>
	</dependency>
```

### 导入mysql
```xml
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.21</version>
	</dependency>
```

### 导入junit
```xml

	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.12</version>
		<scope>test</scope>
	</dependency>
```

## 在resources 目录下 新建 mybatis-config.xml

在导入配置的时候需要主要版本之间的差异，不同的版本的配置不一样。

### 问题
```bash
Loading class com.mysql.jdbc.Driver'. This is deprecated. The new driver class iscom.mysql.cj.jdbc.Driver’. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
```
出现这个问题的原因是数据库的版本和所用代码版本不一致导致的，不同版本的特性也不相同，参见[这里](https://blog.csdn.net/qq_41943867/article/details/90574135)

### 附录

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/test?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8&amp;serverTimezone=GMT%2B8"/>
        <property name="username" value="testadmini"/>
        <property name="password" value="123456"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="com/aluosu/dao/UserMapper.xml"/>
  </mappers>
</configuration>
```
## 开始编码

* 用mybatis连接数据库
* 编写实体类
* 编写接口和实现
* 编写mapper

### 用mybatis连接数据库
核心代码是这句,然

```java
	String resource = "org/mybatis/example/mybatis-config.xml";
	InputStream inputStream = Resources.getResourceAsStream(resource);
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
```
### 实体类
这个要根据数据表的属性来写

```java
package com.aluosu.pojo;

public class User {
	private int id;
	private String name;
	private String code;
	private String phone;
	private String email;
	private String addr;
	private String nearlogin;
	private String submissionDate;
	
	//一堆的get/set
}
```
### 编写接口和实现
编写dao，userdao

### 编写mapper
这个mapper是执行sql语句的打工人

### 在test下新建测试目录
新建UserTest.java,用来执行测试代码

### 实现数据插入
#### 把UserDao 改成 UserMapper
	之所以上个步骤要把UserMapper写成UserDao,是因为UserMapper的作用类似于userDao
#### 在UserMapper增加Insert接口
```java
	public interface UserMapper {
	public List<User> getUserList();
	
	public int addUser(User user);
}
```
#### 在UserMapper.xml中写好SQL语句

```xml
  <insert id="addUser" parameterType="com.aluosu.pojo.User" >
  	insert into test.user(id, name, code, phone, email, addr) values(#{id}, #{name}, #{code}, #{phone}, #{email}, #{addr})
  </insert>
```

### 在UserTest中添加测试方法
```java
	public void insertTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(4, "fuck","1004","12345678910", "4@luosu.com", "2222222");
		int result = userMap.addUser(nUser);
		System.out.printf("插入一个用户 result: %d\n", result);
		
		session.commit();
		session.close();
	}
```

### 删除一列数据
* 在UserMapper增加delUpdate接口
```java
public int delUser(int id);
```
* 在UserMapper.xml中写好SQL语句
```xml
	<delete id="delUser" >
		delete from test.user where id = #{id}
	</delete>
```
* UserTest添加测试方法
```java
	@Test
	public void deleteTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		int result = userMap.delUser(4);
		System.out.printf("删除一个用户 result: %d\n", result);
		session.commit();
		session.close();
	}
```

### 改操作和查操是类似的我看官方文档有，我就不写了
### 注意事项
* 主要是操作完session一定要commit。
* session结束之后一定要关闭```java session.close(); ```.


### 附录
```java
package com.aluosu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.aluosu.pojo.User;
import com.aluosu.utils.MyBatisUtil;

public class UserTest {
	@Test
	public void test() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userDao = session.getMapper(UserMapper.class);
		List<User> userList = userDao.getUserList();
		
		for(User e : userList) {
			System.out.printf("%s\n",e);
		}
		session.close();
	}
	
	@Test
	public void insertTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(4, "fuck","1004","12345678910", "4@luosu.com", "2222222");
		int result = userMap.addUser(nUser);
		System.out.printf("插入一个用户 result: %d\n", result);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void deleteTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		int result = userMap.delUser(4);
		System.out.printf("删除一个用户 result: %d\n", result);
		session.commit();
		session.close();
	}
	
	@Test
	public void updateTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMap = session.getMapper(UserMapper.class);
		User nUser = new User(4, "goodjob","1004","0987654321", "what email?", "no house");
		int result = userMap.updateUser(nUser);
		System.out.printf("更新一个用户 result: %d\n", result);
		session.commit();
		session.close();
	}
}
```