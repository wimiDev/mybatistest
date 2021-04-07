## 一对多
有两种方式可以实现这个功能，一个是联表查询，另一个是用子查询的方式

### 1、联表查询
* 核心思想是同时查两个表，然后把要查的老师的id匹配teacher表的id，且找出student表中tid==id的记录，再通过集合的方式返回
* 附录

```xml
<select id="getTeacher" resultMap="TeacherStudent">
  	select t.id tid, t.name tname, s.id sid, s.name sname, s.tid stid from ${DATABASE}.${STUDENT} s, ${DATABASE}.${TEACHER} t where s.tid=t.id and t.id=#{id};
  </select>
  <resultMap type="Teacher" id="TeacherStudent">
  	<id property="id" column="tid"/>
  	<result property="name" column="tname"/>
  	<collection property="students" ofType="Student">
  		<id property="id" column="sid"/>
  		<result property="name" column="sname"/>
  		<result property="tid" column="stid"/>
  	</collection>
  </resultMap>
  
  <select id="getTeacher2" resultMap="TeacherStudent2">
  	select * from ${DATABASE}.${TEACHER} where id=#{id};
  </select>
```

### 2、子查询

* 核心思想是，先查teacher表，再构件一个子查询，查student表中tid=id的记录，并放到集合中返回回去
* 附录

```xml
<select id="getTeacher2" resultMap="TeacherStudent2">
  	select * from ${DATABASE}.${TEACHER} where id=#{id};
  </select>
  
  <resultMap type="Teacher" id="TeacherStudent2">
  	<id property="id" column="id"/>
  	<result property="name" column="name"/>
  	<collection property="students" javaType="ArrayList" column="id" ofType="Student" select="selectStudent2">
	</collection>
  </resultMap>
  <select id="selectStudent2" resultType="Student">
  	select * from ${DATABASE}.${STUDENT} where tid=#{id};
  </select>
```