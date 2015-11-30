# Component
1.0.0-SNAPSHOT 

JAVA业务组件组件

# 一、Java Autocode
通过JDBC连接Mysql， 读取MyMsql表结构的信息，自动化生成java业务代码

1、Maven Plugin方式：

在autocode工程目录下，执行mvn autocode:create

2、main()方法方式：

创建一个包含main方法的类，调用GeneratorStarter的start()方法

eg：

public static void main(String[] args) {

    GeneratorStarter starter = new DefaultGeneratorStarter();

    starter.start();

}

# 二、Excel
java导入导出excel组件


# 三、WebApp Lunch Starter
webapp项目集成web容器，自动化启动本地项目


# 四、core

# 五、common-lang
