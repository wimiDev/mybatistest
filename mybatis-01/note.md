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
## [属性配置](https://mybatis.org/mybatis-3/zh/configuration.html#properties)
### properties
这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置。
设置好的属性可以在整个配置文件中用来替换需要动态配置的属性值。mapper中也可以用。

#### 在外部文件配置
* 在resource目录下新建db.properties
* 编写配置
* 在mybatis-config.xml中添加属性标签,```xml <properties resource="db.properties"></properties> ```
#### 在mybatis-config.xml中配置
* 在mybatis-config.xml中添加属性标签,```xml <properties resource="db.properties"></properties> ```
* 然后在子标签中添加属性，如附录所示

附录：
```xml
	<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 属性写在内部文件 -->
	<!-- 
	<properties>
		<property name="username" value="boy-1"/>
		<property name="pwd" value="84a84-C046c"/>
		<property name="driver" value="com.mysql.cj.jdbc.Driver"/>
		<property name="devhost" value="81.70.153.203"/>
		<property name="DATABASE" value="RAINSTORM"/>
		<property name="USER" value="user"/>
	</properties>
	 -->
	
	<properties resource="db.properties"></properties>
	...省略了其他配置
</configuration>
```
## [类型别名](https://mybatis.org/mybatis-3/zh/configuration.html#typeAliases)
类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写

* 在mybatis-config.xml中添加属性标签,注意标签的顺序，mybatis对顺序是有要求的
```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
</typeAliases>
```
* 设置完别名之后就可以在配置文件中使用别名，而不用带复杂的全局包名。
