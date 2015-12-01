package com.lzbruby.webapp.lunch;

import com.lzbruby.webapp.WebAppTestServer;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * 默认的本地Tomcat测试服务器
 *
 * @author lizhenbin
 */
public class TomcatWebAppTestServer extends WebAppLunchConfigureLoader implements WebAppTestServer {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TomcatWebAppTestServer.class);

    /**
     * Tomcat服务器
     */
    private Tomcat tomcat;

    /**
     * 启动jetty
     */
    public void start() {
        try {

            String path = TomcatWebAppTestServer.class.getResource("/").getPath();
            String webAppDirLocation = path + getProperties().getProperty(WEB_APP_DIR_LOCATION);
            tomcat = new Tomcat();

            //The port that we should run on can be set into an environment variable
            //Look for that variable and default to 8080 if it isn't there.
            String webPort = System.getenv("PORT");
            if (webPort == null || webPort.isEmpty()) {
                webPort = getProperties().getProperty(WEB_APP_PORT);
            }

            // tomcat temp file base path
            String baseDir = path + getProperties().getProperty(TOMCAT_BASE_DIR) + webPort;
            if (StringUtils.isNotBlank(baseDir)) {
                tomcat.setBaseDir(baseDir);
            }
            tomcat.setPort(Integer.valueOf(webPort));

            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webAppDirLocation).getAbsolutePath());
            LOGGER.info("configuring app with basedir: " + new File("./" + webAppDirLocation).getAbsolutePath());

            // Declare an alternative location for your "WEB-INF/classes" dir
            // Servlet 3.0 annotation will work
            String webApp = getProperties().getProperty(WEB_APP_PATH);
            File additionWebInfClasses = new File(webApp);
            WebResourceRoot resources = new StandardRoot(ctx);
            String contextPath = getProperties().getProperty(CONTEXT_PATH);
            String webAppClassPath = getProperties().getProperty(WEB_APP_CLASS_LOCATION);
            resources.addPreResources(new DirResourceSet(resources, webAppClassPath, additionWebInfClasses.getAbsolutePath(), contextPath));
            ctx.setResources(resources);

            tomcat.start();
            tomcat.getServer().await();

            LOGGER.info("启动tomcat web服务成功.");
        } catch (Exception e) {
            throw new RuntimeException("启动tomcat web服务出现异常", e);
        }
    }

}
