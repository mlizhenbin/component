package com.lzb.webapp.lunch;


import com.lzb.webapp.WebAppTestServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的本地Jetty测试服务器
 *
 * @author lizhenbin
 */
public class JettyWebAppTestServer extends WebAppLunchConfigureLoader implements WebAppTestServer {

    /**
     * sl4j
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JettyWebAppTestServer.class);


    /**
     * Jetty服务器
     */
    private Server jettyServer;

    /**
     * 启动jetty
     */
    public void start() {
        try {

            jettyServer = new Server();

            int port = Integer.parseInt(getProperties().getProperty(WEB_APP_PORT));
            ServerConnector connector = new ServerConnector(jettyServer);
            connector.setPort(port);
            jettyServer.addConnector(connector);

            String contextPath = getProperties().getProperty(CONTEXT_PATH);
            String webApp = getProperties().getProperty(WEB_APP_PATH);

            WebAppContext waContext = new WebAppContext(webApp, contextPath);
            waContext.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
            jettyServer.setStopAtShutdown(true);
            jettyServer.setHandler(waContext);

            jettyServer.start();
            jettyServer.join();

            LOGGER.info("启动jetty web服务成功.");

        } catch (Exception e) {
            throw new RuntimeException("启动jetty web服务出现异常", e);
        }
    }
}
