# Component
1.0.0-SNAPSHOT 

Java业务组件组件

## 一、Mybatis Autocode
通过JDBC连接Mysql， 读取MyMsql表结构的信息，自动化生成java业务代码

### 1、Maven Plugin方式：

在autocode工程目录下，执行命令生成

    mvn autocode:create

### 2、main()方法方式：

创建一个包含main方法的类，调用GeneratorStarter的start()方法

eg：

    public static void main(String[] args) {
        GeneratorStarter starter = new DefaultGeneratorStarter();
        starter.start();
    }
    


## 二、Excel
Java导入导出excel文件业务组件

### 1、Export Excel
调用ExportExcelFacade接口的方法，然后实现接口参数内部类

eg：

注入到Spring工厂中，调用方式：

    @Autoware
    private ExportExcelFacade exportExcelFacade

@see com.lzb.excel.MultipleCreateTest

@see com.lzb.excel.SimpleCreateTest

    
### 2、Import Excel
调用ExportExcelFacade接口的方法，然后实现接口参数内部类

eg：

注入到Spring工厂中，调用方式：

    @Autoware
    private ImportedExcelFacade importedExcelFacade
    
@see com.lzb.excel.ImportTest


## 三、WebApp Lunch Starter
webapp项目集成web容器，自动化启动本地项目


## 四、core

## 五、common-lang
