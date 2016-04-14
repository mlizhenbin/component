# Component
1.0.0-SNAPSHOT 

Java业务组件组件

## 一、excel
Java导入导出excel文件业务组件

### 1、export
调用ExportExcelFacade接口的方法，然后实现接口参数内部类

注入到Spring工厂中，调用方式：

    @Autoware
    private ExportExcelFacade exportExcelFacade

@see MultipleCreateTest

@see SimpleCreateTest

    
### 2、import
调用ExportExcelFacade接口的方法，然后实现接口参数内部类

注入到Spring工厂中，调用方式：

    @Autoware
    private ImportedExcelFacade importedExcelFacade
    
@see ImportTest


## 二、webapp lunch
Java Web项目集成应用web容器，自动化启动本地项目，调用WebAppTestServer接口start()方法

### 1、Jetty
    public static void main(String[] args) {
        WebAppTestServer server = new JettyWebAppTestServer();
        server.start();
    }

### 2、Tomcat
    public static void main(String[] args) {
        WebAppTestServer server = new TomcatWebAppTestServer();
        server.start();
    }

## 三、core
组件core部分，包含分页，配置字典，JDBC...

## 四、common-lang
常用工具类、帮助类等


