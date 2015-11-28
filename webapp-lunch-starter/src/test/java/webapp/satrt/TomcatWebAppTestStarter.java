package webapp.satrt;


import com.lzb.webapp.WebAppTestServer;
import com.lzb.webapp.lunch.TomcatWebAppTestServer;

/**
 * 功能描述：tomcat测试启动器
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/8/24 Time: 16:15
 */
public class TomcatWebAppTestStarter {

    public static void main(String[] args) {
        WebAppTestServer server = new TomcatWebAppTestServer();
        server.start();
    }
}
