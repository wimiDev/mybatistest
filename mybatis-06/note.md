## 使用注解开发
* 修改mybatis-config。xml的mapper, 改成注解用的class。同时删掉UserMapper.xml里面和注解中重复的语句，我刚开始没删，结果报错。删掉就正常了
```xml
	<mappers>
	  <mapper class="com.aluosu.dao.UserMapper"/>
	</mappers>
```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.aluosu.dao.UserMapper">
  
</mapper>
```
* 直接在UserMapper或者其他Mapper的接口加上MyBatis的注解

```java
public interface UserMapper {
	@Select("SELECT * FROM ${DATABASE}.${USER} where id = #{id}")
	public User selectUser(@Param("id") int id);
	
	@Insert("INSERT into ${DATABASE}.${USER}(id, name, code, phone, email, addr) values(#{id}, #{name}, #{code}, #{phone}, #{email}, #{addr})")
	public int addUser(@Param("id") int id, @Param("name")String name, @Param("code")String code, @Param("phone")String phone, @Param("email")String email, @Param("addr")String addr);
	
	@Update("update ${DATABASE}.${USER} set \r\n"
			+ "  	name = #{name},\r\n"
			+ "  	code = #{code},\r\n"
			+ "  	phone = #{phone},\r\n"
			+ "  	email = #{email},\r\n"
			+ "  	addr = #{addr}\r\n"
			+ "  	where id = #{id}")
	public int updateUser(@Param("id") int id, @Param("name")String name, @Param("code")String code, @Param("phone")String phone, @Param("email")String email, @Param("addr")String addr);
	
	@Delete("delete from ${DATABASE}.${USER} where id = #{id}")
	public int deleteUser(@Param("id") int id);
}
	
```
* @Param("id")
参数注解是推荐加上的，MyBatis注解中的参数会对应@Param("id")里的参数，如果这个参数不对应也是不能正常执行的。

* 在test中调用接口

```java
public class UserTest {
	@Test
	public void test() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userDao = session.getMapper(UserMapper.class);
		List<User> userList = new ArrayList<User>();
		userList.add(userDao.selectUser(1));
		
		for(User e : userList) {
			System.out.printf("%s: %s\n", e.getId(),e.getName());
		}
		session.close();
	}
	
	@Test
	public void insertTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.printf("新增一个用户： %s", userMapper.addUser(6, "赵国富", "l006", "12334556797", "6@qq.com" ,"xcvsgsdfs"));
		session.close();
	}
	
	@Test
	public void updateTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.printf("更新一个用户： %s", userMapper.updateUser(6, "赵富", "l006", "0987652341", "6@qq.com" ,"sdfwsfsfw"));
		session.close();
	}
	
	@Test
	public void deleteTest() {
		SqlSession session = MyBatisUtil.getInstance().getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.printf("删除一个用户： %s", userMapper.deleteUser(6));
		session.close();
	}
}
```
## 使用lombok开发
* eclipse安装 https://projectlombok.org/setup/eclipse
* 安装maven插件 https://projectlombok.org/setup/maven，同时删掉<scope>provided</scope>

```xml
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<version>1.18.16</version>
</dependency>
```

* API 接口及用法https://projectlombok.org/api/lombok/package-summary.html

```java
	
@Data
@RequiredArgsConstructor
public class User {
	private int id;
	private String name;
	private String code;
	private String phone;
	private String email;
	private String addr;
	};
```
