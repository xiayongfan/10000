# 10000
http://www.myexception.cn/software-architecture-design/1230793.html
HuQingmiao/mybatis-paginator
MySQL核心内幕
http://blog.jobbole.com/24006/
http://download.csdn.net/detail/dy357978912/5458065
mybatis 一对多 字段匹配原理 http://m.blog.csdn.net/blog/xzm_rainbow/15336933
mybatsi n+1问题http://m.blog.csdn.net/blog/legend_x/11887815
http://www.cnblogs.com/timlearn/p/4161567.html?utm_source=tuicool&utm_medium=referral mybatis源码浅析
MySQL Innodb日志机制深入分析http://tech.uc.cn/?p=716
eclipse debug breakpoing linenumberhttp://blog.csdn.net/wwbmyos/article/details/13503241
MySQL数据库InnoDB存储引擎Log漫游http://blog.163.com/zihuan_xuan/blog/static/1287942432012366293667/
http://www.mamicode.com/info-detail-864093.html%20myisam%20innodb%20%E6%AC%A1%E7%BA%A7%20%E7%B4%A2%E5%BC%95%E7%9A%84%E5%8C%BA%E5%88%AB
http://www.2cto.com/database/201306/219691.html InnoDB行格式剖析
http://wenku.baidu.com/link?url=Cbp06X6zozh7lTSMHD4hrCLyYfPBYX0CtPLbUaTh_mqBU7spYCzBncVHM7hUXHOgWvi9r9EwjhNCJ-bv9M0esTgSnDRX_tGSg3_f7vVwUbS MySQL源码分析.03.InnoDB 物理文件格式与数据恢复
http://wenku.baidu.com/link?url=4YCoZ11M5exLG2lqNyTqbJU5JvYmiqvFvDJnxnw_7NJyIETO_M3fnmsG-AkK1ZSEi0miLJymeZpUIIaYOOXhE60iCKMtlGb2Ap_1YxebPB3  innodb的锁和事务详解
MySQL Innodb表导致死锁日志情况分析与归纳  http://www.jb51.net/article/32651.htm http://www.jb51.net/article/51508.htm
http://hedengcheng.com/?p=844 一个最不可思议的MySQL死锁分析
MySQL 加锁处理分析 http://hedengcheng.com/?p=771

精选30道Java笔试题解答 http://www.cnblogs.com/lanxuezaipiao/p/3371224.html
mybatis如何防止sql注入
sql注入大家都不陌生，是一种常见的攻击方式，攻击者在界面的表单信息或url上输入一些奇怪的sql片段，例如“or ‘1’=’1’”这样的语句，有可能入侵参数校验不足的应用程序。所以在我们的应用中需要做一些工作，来防备这样的攻击方式。在一些安全性很高的应用中，比如银行软件，经常使用将sql语句全部替换为存储过程这样的方式，来防止sql注入，这当然是一种很安全的方式，但我们平时开发中，可能不需要这种死板的方式。

mybatis框架作为一款半自动化的持久层框架，其sql语句都要我们自己来手动编写，这个时候当然需要防止sql注入。其实Mybatis的sql是一个具有“输入+输出”功能，类似于函数的结构，如下：

<select id=“getBlogById“ resultType=“Blog“

parameterType=”int”>

       select id,title,author,content

       from blog where id=#{id}

    </select>

这里，parameterType标示了输入的参数类型，resultType标示了输出的参数类型。回应上文，如果我们想防止sql注入，理所当然地要在输入参数上下功夫。上面代码中高亮部分即输入参数在sql中拼接的部分，传入参数后，打印出执行的sql语句，会看到sql是这样的：

select

id,title,author,content from blog where id = ?

不管输入什么参数，打印出的sql都是这样的。这是因为mybatis启用了预编译功能，在sql执行前，会先将上面的sql发送给数据库进行编译，执行时，直接使用编译好的sql，替换占位符“？”就可以了。因为sql注入只能对编译过程起作用，所以这样的方式就很好地避免了sql注入的问题。

mybatis是如何做到sql预编译的呢？其实在框架底层，是jdbc中的PreparedStatement类在起作用，PreparedStatement是我们很熟悉的Statement的子类，它的对象包含了编译好的sql语句。这种“准备好”的方式不仅能提高安全性，而且在多次执行一个sql时，能够提高效率，原因是sql已编译好，再次执行时无需再编译。

话说回来，是否我们使用mybatis就一定可以防止sql注入呢？当然不是，请看下面的代码：

<select id=“orderBlog“ resultType=“Blog“

parameterType=”map”>

       select id,title,author,content

       from blog order by ${orderParam}

    </select>

仔细观察，内联参数的格式由“#{xxx}”变为了${xxx}。如果我们给参数“orderParam”赋值为”id”,将sql打印出来，是这样的：

select id,title,author,content from

blog order by id

     显然，这样是无法阻止sql注入的。在mybatis中，”${xxx}”这样格式的参数会直接参与sql编译，从而不能避免注入攻击。但涉及到动态表名和列名时，只能使用“${xxx}”这样的参数格式，所以，这样的参数需要我们在代码中手工进行处理来防止注入。

    结论：在编写mybatis的映射语句时，尽量采用“#{xxx}”这样的格式。若不得不使用“${xxx}”这样的参数，要手工地做好过滤工作，来防止sql注入攻击。

1、Mybatis基础: #{...} 和 ${...} 的区别
MyBatis将 #{…} 解释为JDBC prepared statement 的一个参数标记。而将 ${…} 解释为字符串替换。理解这两者的区别是很有用的, 因为在某些SQL语句中并不能使用参数标记(parameter markers)。


比如,我们不能在表名(table name)的位置使用参数标记。
假设有下面的代码:


01.Map<String, Object> parms = new HashMap<String, Object>();  
02.parms.put("table", "foo"); // 表名  
03.parms.put("criteria", 37); // 查询过滤条件  
04.List<Object> rows = mapper.generalSelect(parms);  


01.<select id="generalSelect" parameterType="map">  
02.  select * from ${table} where col1 = #{criteria}  
03.</select>  


MyBatis生成的SQL语句(prepared statement)如下所示:
01.select * from foo where col1 = ?  


重要提示: 请注意,使用$ {…} (字符串替换)时可能会有SQL注入攻击的风险。另外,字符串替换在处理复杂类型也可能常常发生问题,如日期类型。由于这些因素,我们建议您尽可能地使用 #{…} 这种方式。
要使用LIKE语句该怎么写?




2、有两种使用LIKE的方法。(推荐使用)第一种方法是,在Java代码中添加SQL通配符。
示例一:
01.String wildcardName = "%Smi%";  
02.List<Name> names = mapper.selectLike(wildcardName);  


01.<select id="selectLike">  
02.  select * from foo where bar like #{value}  
03.</select>  


第二种方式是在SQL语句中拼接通配符。这种方法相对来说安全性要低一些,因为可能会被SQL注入攻击。
示例二:
01.String wildcardName = "Smi";  
02.List<Name> names = mapper.selectLike(wildcardName); 


01.<select id="selectLike">  
02.  select * from foo where bar like '%' || '${value}' || '%'  
03.</select>  


重要提示: 请注意两种方式中 $ 和 # 的使用!




3、如何执行批量插入?


首先,创建一个简单的insert语句:
01.<insert id="insertName">  
02.  insert into names (name) values (#{value})  
03.</insert>  


然后在Java代码中像下面这样执行批处理插入:
01.List<String> names = new ArrayList<String>();  
02.names.add("Fred");  
03.names.add("Barney");  
04.names.add("Betty");  
05.names.add("Wilma");  
06.  
07.// 注意这里 ExecutorType.BATCH  
08.SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);  
09.try {  
10.  NameMapper mapper = sqlSession.getMapper(NameMapper.class);  
11.  for (String name : names) {  
12.    mapper.insertName(name);  
13.  }  
14.  sqlSession.commit();  
15.} finally {  
16.  sqlSession.close();  
17.}  




4、如何获取自动生成的(主)键值?


insert 方法总是返回一个int值 - 这个值代表的是插入的行数。而自动生成的键值在 insert 方法执行完后可以被设置到传入的参数对象中。
示例:
01.<insert id="insertName" useGeneratedKeys="true" keyProperty="id">  
02.  insert into names (name) values (#{name})  
03.</insert>  


01.Name name = new Name();  
02.name.setName("Fred");  
03.  
04.int rows = mapper.insertName(name);  
05.// 完成后,id已经被设置到对象中  
06.System.out.println("rows inserted = " + rows);  
07.System.out.println("generated key value = " + name.getId());  




5、在mapper中如何传递多个参数?
Java的反射机制并不能让框架获取到参数的名字(方法签名中只有参数类型,可以说是为了优化,也可以说设计就是如此,总之名字无意义), 所以MyBatis默认的命名为: param1,param2……
如果想给他们指定名称,可以使用 @param 注解:
01.import org.apache.ibatis.annotations.Param;  
02.public interface UserMapper {  
03.   User selectUser(@Param("username") String username,   
04.                   @Param("hashedPassword") String hashedPassword);  
05.}  


然后,就可以在xml像下面这样使用(推荐封装为一个Map,作为单个参数传递给Mapper):
01.<select id=”selectUser” resultType=”User”>  
02.  select id, username, hashedPassword  
03.  from some_table  
04.  where username = #{username}  
05.  and hashedPassword = #{hashedPassword}  
06.</select>  


1.09 Spring实例化bean的三个方式是甚么？

（1）无参构造方法：<bean id=””  class=””></bean>

（2）有参构造方法：

<bean id=””  class=””  init-method=”init”>

<property name=”” value=””/>

</bean>

（3）工厂方法

Calendar.getInstance();

<bean id=”cal”  class=”java.util.Calendar” 

factory-method=”getInstance”/>

1.10 Spring的IOC的作用是甚么？

IoC意味着将你设计好的类交给系统去控制，而不是在你的类内部控制。

1.11 Spring的AOP是如何实现的？

1.代理的两种方式：
静态代理：
◆针对每个具体类分别编写代理类；
◆针对一个接口编写一个代理类；
动态代理：
针对一个方面编写一个InvocationHandler，然后借用JDK反射包中的Proxy类为各种接口动态生成相应的代理类
2.AOP的主要原理：动态代理

AOP 让开发人员可以创建非行为性的关注点，称为横切关注点，并将它们插入到应用程序代码中。使用 AOP 后，公共服务(比如日志、持久性、事务等）就可以分解成方面并应用到域对象上，同时不会增加域对象的对象模型的复杂性。


1.13 spring的事务管理有几种方式实现，如何实现

Spring+Hibernate的实质：
就是把Hibernate用到的数据源Datasource，Hibernate的SessionFactory实例，事务管理器HibernateTransactionManager，都交给Spring管理。

实现方式共有两种：编码方式；声明式事务管理方式。

基于AOP技术实现的声明式事务管理，实质就是：在方法执行前后进行拦截，然后在目标方法开始之前创建并加入事务，执行完目标方法后根据执行情况提交或回滚事务。


1、overload和override的区别
overload：
方法的重载
只在方法之间发生
方法名相同，并且区分大小写
参数列表不同，具体是指参数的类型，个数，顺序不同
返回值类型没有要求，可以相同，也可以不同
override：
方法的覆盖
发生在父子类之间
方法名称，参数表，返回值都相同
修饰符一样或者更宽
静态方法只能被静态方法覆盖，没有多态
子类不能抛出比父类更多的异常<指的是范围>

2、接口和抽象类的区别
抽象类（abstract-class）:
1).抽象类不能创建对象，只可以声明引用
2).抽象类有构造函数
3).抽象类里面可以有抽象方法，也可以没有
4).如果一个类里面有抽象方法，则该类是抽象类
5).如果一个类继承抽象类，则该类不想成为抽象类，则必须实现抽象类里面的所有抽象方法
接口（interface）：
是一个特殊的抽象类
修饰属性：

3、string 和StringBuffer StringBuilder的区别
String的长度是不可变的； StringBuffer的长度是可变的， 如果你对字符串中的内容经常进行操作，特别是内容要修改时，那么使用StringBuffer，如果最后需要String，那么使用StringBuffer的toString()方法；线程安全；
StringBuilder是从 JDK 5 开始，为StringBuffer该类补充了一个单个线程使用的等价类；
通常应该优先使用 StringBuilder类，因为它支持所有相同的操作，但由于它不执行同步，所以速度更快。

4、arrayList linkedList vector 的区别
ArrayList 和 Vector 都是使用数组方式存储数据，此数组元素数大于实际存储的数据以便增加和插入元素，它们都允许直接按序号索引元
素，但是插入元素要涉及数组元素移动等内存操作，所以索引数据快而插入数据慢，Vector 由于使用了 synchronized 方法（线程安全），
通常性能上较 ArrayList 差，而 LinkedList 使用双向链表实现存储，按序号索引数据需要进行前向或后向遍历，但是插入数据时只需要记
录本项的前后项即可，所以插入速度较快。
LinkedList 也是线程不安全的，LinkedList 提供了一些方法，使得
LinkedList 可以被当作堆栈和队列来使用。

5、list和set 的区别
list:有序，有下标，可重复，实现类有三种：ArrayList、Vector、LinkedList，遍历方式有三种：for循环遍历、迭代器遍历、foreach循环遍历
ArrayList、Vector底层由数组实现，默认数组长度为0
set：无序，无下标，不可重复，实现类为HashSet，遍历方式有两种：迭代器遍历、foreach循环遍历

6、写一个singleton类。
单例模式 一个类只能有一个实例对象
a 饿汉式
b 懒汉式

package entity;
public class Test {
public static void main(String[] args) {
// Student s= new Student();
Student s1 = Student.getStudent();
Student s2 = Student.getStudent();
System.out.println(s1==s2);
}
}
//饿汉式 类加载时就被创建对象
//class Student{
// private static Student s = new Student();
// private Student(){}
// public static Student getStudent(){
// return s;
// }
//}
//懒汉式 锁类对象
class Student{
private static Student s;
private Student(){}
public synchronized static Student getStudent(){
if(s==null){
s = new Student();
}
return s;
}
}

7、数据库连接池的工作机制
J2EE 服务器启动时会建立一定数量的池连接，并一直维持不少于此数目的池连接。客户端程序需要连接时，池驱动程序会返回一个未使用的池连接并将其表 记为忙。如果当前没有空闲连接，池驱动程序就新建一定数量的连接，新建连接的数量有配置参数决定。当使用的池连接调用完成后，池驱动程序将此连接表
记为空闲，其他调用就可以使用这个连接。实现方式，返回的 Connection是原始Connection的代理，代理Connection的close方法不是真正关连接，而是把它
代理的 Connection 对象还回到连接池中。

8、说出servlet的生命周期
servlet是单例的，servlet是当第一次发出请求时实例化一个servlet。
servlet 有良好的生存期的定义，包括加载和实例化、初始化、处理请求以及服务结束。
这个生存期由 javax.servlet.Servlet 接口的 init,service 和 destroy 方法表达。Servlet 被服务器实例化后，容器运行其 init 方法，请求到达时运行其 service方法，service 方法自动派遣运行与请求对应的 doXXX 方法（doGet，doPost）等，当服务器决定将实例销毁的时候调用其 destroy 方法。web 容器加载 servlet，生命周期开始。通过调用 servlet 的 init()方法进行 servlet的初始化。通过调用 service()方法实现，根据请求的不同调用不同的 do***()方法。结束服务，web 容器调用 servlet 的 destroy()方法。

10、spring mvc的介绍
M(Model)层,就相当于后台，V(View),相当于前台，C(Controller)层，相当于控制页面跳转；MVC 模式的目的就是实现 Web 系统的职能分工。
Model层实现系统中的业务逻辑，通常可以用 JavaBean 或 EJB来实现。
View 层用于与用户的交互，通常用 JSP 来实现。 Controller层是Model与View之间沟通的桥梁，它可以分派用户的请求并选择恰当的视图以用于显示，同时它也可以解释用户的输入并将它们映射为模型层可执行的操作。
各个层的实现技术举例：
M(Model)javaBean 中 DAO+Service 构成 Model 层一般由hibernate/mybatis 技术实现。模型是应用程序的主体部分。模型表示业务数据，或者业务逻辑.
C(servlet/struts action/spring action) Struts/SpringMVC 等技术。控制器工作就是根据用户的输入，控制用户界面数据显示和更新 model对象状态。
V(View) jsp/FreeMarker 等技术，视图是应用程序中用户界面相关的部分，是用户看到并与之交互的界面。

MVC的好处：分层开发，便于系统的升级

对于一个新的框架，如何学习？
首先知道该框架是干嘛的，能解决什么问题。


12、spring 的主要作用。
SSH框架的流程：Jsp页面——struts——Service（业务逻辑处理类）——Hibernate（左到右）struts负责控制Service的声明周期，这样层与层之间的依赖性强，属于耦合。这时，使用spring框架就起到了控制Action和Service类的作用，由于上层不再控制下层的生命周期，层与层之间实现了完全脱耦，使程序运行起来效果更高，维护起来也方便。

总的来说，spring的作用是解耦合（IOC—翻转控制或者DI—依赖注入）和事务的处理（即AOP应用）
spring的IOC（控制反转和依赖注入）
控制反转：就是由容器控制程序之间的（依赖）关系，而非传统实现中，由程序员代码直接操控（由new关键字来生成对象）
依赖注入：组件之间的依赖关系由容器在运行期决定，由容器动态的将某种依赖注入到组件之中。

事务的处理： 在以往的JDBCTemplate中事务提交成功，异常处理都是通过Try/Catch来完成，而在Spring中，容器集成了TransactionTemplate，它封装了所有对事务处理的功能，包括异常时事务回滚，操作成功时数据提交等复杂业务功能。Hibernate中也有对事务的管理，Hibernate中事务管理是通过SessionFactory创建和维护session来完成，而Spring对SessionFactory也进行了整合，不需要再通过配置hibernate.cfg.xml来对SessionFactory进行设定。这样可以很好的利用Spring对事务管理的强大功能。避免了每次对数据操作都要获得Session实例来启动事务/提交/回滚事务还有繁琐的Try/Catch操作。这些也就是Spring中的AOP机制很好的应用。一方面使业务开发逻辑更清晰、专业分工更加容易进行。另一方面就是应用Spring-AOP隔离降低了程序的耦合性使我们可以在不同的应用中将各个切面结合起来使用，大大提高了代码重用度。

13、HashTable和HashMap的区别。
HashMap 是 Hashtable 的轻量级实现（非线程安全的实现），他们都完成了 Map 接口，主要区别在于 HashMap 允许空（null）键值（key）,由于非线程安全，在只有一个线程访问的情况下，效率要高于Hashtable。
HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。
HashMap 把 Hashtable 的 contains 方法去掉了，改成 containsvalue和 containsKey。因为 contains 方法容易让人引起误解。
Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface 的一个实现。
最大的不同是，Hashtable 的方法是 Synchronize 的，而 HashMap不是，在多个线程访问 Hashtable 时，不需要自己为它的方法实现同步，而 HashMap 就必须为之提供外同步。
Hashtable 和 HashMap 采用的 hash/rehash 算法都大概一样，所以性能不会有很大的差异。
就 HashMap 与 HashTable 主要从三方面来说：
一.历史原因:Hashtable 是基于陈旧的 Dictionary 类的，HashMap 是Java 1.2 引进的 Map 接口的一个实现
二.同步性:Hashtable是线程安全的，也就是说是同步的，而HashMap是线程序不安全的，不是同步的
三.值：只有 HashMap 可以让你将空值作为一个表的条目的 key 或value

14.SERVLETAPI 中 forward() 与 redirect()的区别？
forward：请求转发
redirect：请求重定向
前者仅是容器中控制权的转向，在客户端浏览器地址栏中不会显示出转向后的地址；后者则是完全的跳转，浏览器将会得到跳转的地址，并重新发送请求链接。这样，从浏览器的地址栏中可以看到跳转后的链接地址。所以，前者更加高效，在前者可以满足需要时，尽量使用 forward()方法，并且，这样也有助于隐
藏实际的链接。在有些情况下，比如，需要跳转到一个其它服务器上的资源，则必须使用sendRedirect()方法。

15.多态：编译时多态，运行时多态
方法的重载没有多态，是指运行时多态

16.mybatis n+1问题


经典32道 Java面试题及答案！
　　第一，谈谈final, finally, finalize的差别。
　　final?修饰符(关键字)如果一个类被声明为final，意味着他不能再派生出新的子类，不能作为父类被继承。因此一个类不能既被声明为abstract的，又被声明为final的。将变量或方法声明为final，能确保他们在使用中不被改动。被声明为final的变量必须在声明时给定初值，而在以后的引用中只能读取，不可修改。被声明为final的方法也同样只能使用，不能重载
　　finally?再异常处理时提供finally块来执行所有清除操作。如果抛出一个异常，那么相匹配的catch子句就会执行，然后控制就会进入finally块(如果有的话)。
　　finalize?方法名。Java技术允许使用finalize()方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没有被引用时对这个对象调用的。他是在Object类中定义的，因此所有的类都继承了他。子类覆盖finalize()方法以整理系统资源或执行其他清理工作。finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。
　　第二，Anonymous Inner Class (匿名内部类)是否能extends(继承)其他类，是否能implements(实现)interface(接口)?
　　匿名的内部类是没有名字的内部类。不能extends(继承)其他类，但一个内部类能作为一个接口，由另一个内部类实现。
       这里面有很详细的解释：http://topic.csdn.net/t/20051216/18/4464035.html 
　　第三，Static Nested Class和Inner Class的不同，说得越多越好(面试题有的非常笼统)。
　　Nested Class (一般是C++的说法)，Inner Class (一般是JAVA的说法)。Java内部类和C++嵌套类最大的不同就在于是否有指向外部的引用上。具体可见//www.frontfree.net/articles/services/view.asp?id=704&page=1
　　注：静态内部类(Inner Class)意味着1创建一个static内部类的对象，不必一个外部类对象，2不能从一个static内部类的一个对象访问一个外部类对象
　　第四，&和&&的差别。
　　&是位运算符。&&是布尔逻辑运算符。
　　第五，HashMap和Hashtable的差别。
　　都属于Map接口的类，实现了将惟一键映射到特定的值上。
　　HashMap类没有分类或排序。他允许一个null键和多个null值。
　　Hashtable类似于HashMap，不过不允许null键和null值。他也比HashMap慢，因为他是同步的。
　　第六，Collection和Collections的差别。
　　Collections是个java.util下的类，他包含有各种有关集合操作的静态方法。
　　Collection是个java.util下的接口，他是各种集合结构的父接口。
　　第七，什么时候用assert。
　　断言是个包含布尔表达式的语句，在执行这个语句时假定该表达式为true。如果表达式计算为false，那么系统会报告一个AssertionError。他用于调试目的：
assert(a > 0); // throws an AssertionError if a <= 0
断言能有两种形式：
assert Expression1 ;
assert Expression1 : Expression2 ;
Expression1应该总是产生一个布尔值。
　Expression2能是得出一个值的任意表达式。这个值用于生成显示更多调试信息的String消息。　断言在默认情况下是禁用的。要在编译时启用断言，需要使用source 1.4标记：
javac -source 1.4 Test.java
要在运行时启用断言，可使用-enableassertions或-ea标记。
要在运行时选择禁用断言，可使用-da或-disableassertions标记。
要系统类中启用断言，可使用-esa或-dsa标记。还能在包的基础上启用或禁用断言。
　　能在预计正常情况下不会到达的所有位置上放置断言。断言能用于验证传递给私有方法的参数。不过，断言不应该用于验证传递给公有方法的参数，因为不管是否启用了断言，公有方法都必须检查其参数。不过，既能在公有方法中，也能在非公有方法中利用断言测试后置条件。另外，断言不应该以所有方式改动程式的状态。
　　第八，GC是什么?为什么要有GC? (基础)。
　　GC是垃圾收集器。Java程式员不用担心内存管理，因为垃圾收集器会自动进行管理。要请求垃圾收集，能调用下面的方法之一：
　　System.gc()
　　Runtime.getRuntime().gc()
　　第九，String s = new String("xyz");创建了几个String Object?
　　两个对象，一个是“xyx”,一个是指向“xyx”的引用对象s。
　　第十，Math.round(11.5)等於多少? Math.round(-11.5)等於多少?
　　Math.round(11.5)返回(long)12，Math.round(-11.5)返回(long)-11;
　　第十一，short s1 = 1; s1 = s1 + 1;有什么错? short s1 = 1; s1 += 1;有什么错?
short s1 = 1; s1 = s1 + 1;有错，s1是short型，s1+1是int型,不能显式转化为short型。可修改为s1 =(short)(s1 + 1)。short s1 = 1; s1 += 1正确。
　第十二，sleep()和wait()有什么差别?搞线程的最爱
sleep()方法是使线程停止一段时间的方法。在sleep时间间隔期满后，线程不一定即时恢复执行。这是因为在那个时刻，其他线程可能正在运行而且没有被调度为放弃执行，除非(a)“醒来”的线程具有更高的优先级
　　(b)正在运行的线程因为其他原因而阻塞。
　　wait()是线程交互时，如果线程对一个同步对象x发出一个wait()调用，该线程会暂停执行，被调对象进入等待状态，直到被唤醒或等待时间到。
　　第十三，Java有没有goto?
Goto?java中的保留字，目前没有在java中使用。
　　第十四，数组有没有length()这个方法? String有没有length()这个方法？
数组没有length()这个方法，有length的属性。
String有有length()这个方法。
　　第十五，Overload和Override的差别。Overloaded的方法是否能改动返回值的类型?
方法的重写Overriding和重载Overloading是Java多态性的不同表现。重写Overriding是父类和子类之间多态性的一种表现，重载Overloading是个类中多态性的一种表现。如果在子类中定义某方法和其父类有相同的名称和参数，我们说该方法被重写(Overriding)。子类的对象使用这个方法时，将调用子类中的定义，对他而言，父类中的定义如同被“屏蔽”了。如果在一个类中定义了多个同名的方法，他们或有不同的参数个数或有不同的参数类型，则称为方法的重载(Overloading)。Overloaded的方法是能改动返回值的类型。
　　第十六，Set里的元素是不能重复的，那么用什么方法来区分重复和否呢?是用==还是equals()?他们有何差别?
　　Set里的元素是不能重复的，那么用iterator()方法来区分重复和否。equals()是判读两个Set是否相等。
　　equals()和==方法决定引用值是否指向同一对象equals()在类中被覆盖，为的是当两个分离的对象的内容和类型相配的话，返回真值。
　　第十七，给我一个你最常见到的runtime exception。
　　ArithmeticException, ArrayStoreException, BufferOverflowException, BufferUnderflowException, CannotRedoException, CannotUndoException, ClassCastException, CMMException, ConcurrentModificationException, DOMException, EmptyStackException, IllegalArgumentException, IllegalMonitorStateException, IllegalPathStateException, IllegalStateException,
　　ImagingOpException, IndexOutOfBoundsException, MissingResourceException, NegativeArraySizeException, NoSuchElementException, NullPointerException, ProfileDataException, ProviderException, RasterFormatException, SecurityException, SystemException, UndeclaredThrowableException, UnmodifiableSetException, UnsupportedOperationException
　　第十八，error和exception有什么差别?
　　error表示恢复不是不可能但非常困难的情况下的一种严重问题。比如说内存溢出。不可能指望程式能处理这样的情况。
　　exception表示一种设计或实现问题。也就是说，他表示如果程式运行正常，从不会发生的情况。
　　第十九，List, Set, Map是否继承自Collection接口?
　　List，Set是
　　Map不是
　　第二十，abstract class和interface有什么差别?
　　声明方法的存在而不去实现他的类被叫做抽象类(abstract class)，他用于要创建一个体现某些基本行为的类，并为该类声明方法，但不能在该类中实现该类的情况。不能创建abstract类的实例。然而能创建一个变量，其类型是个抽象类，并让他指向具体子类的一个实例。不能有抽象构造函数或抽象静态方法。Abstract类的子类为他们父类中的所有抽象方法提供实现，否则他们也是抽象类为。取而代之，在子类中实现该方法。知道其行为的其他类能在类中实现这些方法。
　　接口(interface)是抽象类的变体。在接口中，所有方法都是抽象的。多继承性可通过实现这样的接口而获得。接口中的所有方法都是抽象的，没有一个有程式体。接口只能定义static final成员变量。接口的实现和子类相似，除了该实现类不能从接口定义中继承行为。当类实现特别接口时，他定义(即将程式体给予)所有这种接口的方法。然后，他能在实现了该接口的类的所有对象上调用接口的方法。由于有抽象类，他允许使用接口名作为引用变量的类型。通常的动态联编将生效。引用能转换到接口类型或从接口类型转换，instanceof运算符能用来决定某对象的类是否实现了接口。
　　第二十一，abstract的method是否可同时是static,是否可同时是native，是否可同时是synchronized?
　　都不能
　　第二十二，接口是否可继承接口?抽象类是否可实现(implements)接口?抽象类是否可继承实体类(concrete class)?
　　接口能继承接口。抽象类能实现(implements)接口，抽象类是否可继承实体类，但前提是实体类必须有明确的构造函数。
　　第二十三，启动一个线程是用run()还是start()?
　　启动一个线程是调用start()方法，使线程所代表的虚拟处理机处于可运行状态，这意味着他能由JVM调度并执行。这并不意味着线程就会即时运行。run()方法能产生必须退出的标志来停止一个线程。
　　第二十四，构造器Constructor是否可被override?
　　构造器Constructor不能被继承，因此不能重写Overriding，但能被重载Overloading。
　　第二十五，是否能继承String类?
　　String类是final类故不能继承。
　　第二十六，当一个线程进入一个对象的一个synchronized方法后，其他线程是否可进入此对象的其他方法?
　　不能，一个对象的一个synchronized方法只能由一个线程访问。
　　第二十七，try {}里有一个return语句，那么紧跟在这个try后的finally {}里的code会不会被执行，什么时候被执行，在return前还是后?
　　会执行，在return前执行。
　　第二十八，编程题:用最有效率的方法算出2乘以8等於几?
　　有C背景的程式员特别喜欢问这种问题。
　　2 << 3
　　第二十九，两个对象值相同(x.equals(y) == true)，但却可有不同的hash code，这句话对不对?
　　不对，有相同的hash code。
　　第三十，当一个对象被当作参数传递到一个方法后，此方法可改动这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递?
　　是值传递。Java编程语言只由值传递参数。当一个对象实例作为一个参数被传递到方法中时，参数的值就是对该对象的引用。对象的内容能在被调用的方法中改动，但对象的引用是永远不会改动的。
　　第三十一，swtich是否能作用在byte上，是否能作用在long上，是否能作用在String上?
　　switch(expr1)中，expr1是个整数表达式。因此传递给switch和case语句的参数应该是int、short、char或byte。long,string都不能作用于swtich。
　　第三十二，编程题:写一个Singleton出来。
　　Singleton模式主要作用是确保在Java应用程式中，一个类Class只有一个实例存在。
　　一般Singleton模式通常有几种种形式:
　　第一种形式:定义一个类，他的构造函数为private的，他有一个static的private的该类变量，在类初始化时实例话，通过一个public的getInstance方法获取对他的引用,继而调用其中的方法。
　　public class Singleton {
　　private Singleton(){}
　　//在自己内部定义自己一个实例，是不是非常奇怪？
　　//注意这是private只供内部调用
　　private static Singleton instance = new Singleton();
　　//这里提供了一个供外部访问本class的静态方法，能直接访问
　　public static Singleton getInstance() {
　　return instance;　
　　　}
　　}
　　第二种形式:
　　public class Singleton {
　　private static Singleton instance = null;
　　public static synchronized Singleton getInstance() {
　　//这个方法比上面有所改进，不用每次都进行生成对象，只是第一次　
　　//使用时生成实例，提高了效率！
　　if (instance==null)
　　instance＝new Singleton();
　　return instance;　　}
　　}
　　其他形式:
　　定义一个类，他的构造函数为private的，所有方法为static的。
　　一般认为第一种形式要更加安全些
　　
　　
　　http://www.webhek.com/misc/comparison-sort 排序动画
　　
　　希尔排序一种分组插入排序。但就其实质就是逐步合并的过程。
先把序列分成5组，并组内排序：{9,38},{36,65},{13,76},{27,49},{4,11}
再将组分成3大组：{9,36,38,65},{13,27,49,76},{4,11}
然后分成2大组：{9,13,27,36,38,49,65,76},{4,11}
最后：{4,9,11,13,27,36,38,49,65,76}
每次合并都是最多O(n)的算法，合并的次数最多O(logn)，因此希尔排序理论上是O(nlogn)的算法，但由于需要大量的数组赋值，速度并不快。

http://www.yashazs.com/News/106713.html java堆栈基础图
http://blog.csdn.net/caiwenfeng_for_23/article/details/8496157 java 栈实现
