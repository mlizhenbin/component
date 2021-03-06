package webapp.satrt;

import com.lzbruby.webapp.WebAppTestServer;
import com.lzbruby.webapp.lunch.JettyWebAppTestServer;

/**
 * 功能描述：jetty测试启动器
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.cn
 * company：org.lzbruby
 * Date: 15/8/24 Time: 16:15
 */
public class JettyWebAppTestStarter {

    public static void main(String[] args) {
        WebAppTestServer server = new JettyWebAppTestServer();
        server.start();
    }
}
