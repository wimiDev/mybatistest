太大了，还是分P吧
## 结果映射
* 在XXXmapper.xml文件中设置mapper，property是mapper中的属性，column是数据库中的属性，如果数据库中的属性和mapper的属性对不上就会报错.
通常mybatis会自动映射mapper和数据库中的属性，只要保证他们名字对上就不需要手动的配置，但是如果世界总是这么简单就好了，有些复杂的情况下是不能对应起来就需要手动的配置。
```xml
<resultMap id="userResultMap" type="User">
  <result property="n" column="name"/>
</resultMap>
```
* 动作的resultMap要设置一下
```xml
<select id="getUserById" **resultMap**=**"userResultMap"**>
	select * from ${DATABASE}.${USER} where id = #{id}
</select>
```
## 使用log4j
* pom.xml下导入log4j依赖，让maven去下载。[全版本地址](https://mvnrepository.com/artifact/log4j/log4j)
```xml
<dependency>
	<groupId>log4j</groupId>
	<artifactId>log4j</artifactId>
	<version>1.2.17</version>
</dependency>
```
* 在resource目录下新建文件 log4j.properties 文件名字一定要是这个，约定大于配置，如果不是这个他就不识别
* 然后根据需求去配置log4j
```ini
# global log config
log4j.rootLogger=DEBUG, stdout
# MyBatis log config
log4j.logger.org.mybatis.example.BlogMapper=TRACE
# console output
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```