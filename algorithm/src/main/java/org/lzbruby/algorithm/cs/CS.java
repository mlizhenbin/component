package org.lzbruby.algorithm.cs;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： lizhenbin08@sina.com
 * company：org.lzbruby
 * Date: 2019/3/10 Time：13:35
 */
public class CS {
    private String data;
    private String pName;
    private String pDateTime;
    private String cName;
    private String cDateTime;

    public CS(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDateTime() {
        return pDateTime;
    }

    public void setpDateTime(String pDateTime) {
        this.pDateTime = pDateTime;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDateTime() {
        return cDateTime;
    }

    public void setcDateTime(String cDateTime) {
        this.cDateTime = cDateTime;
    }

    @Override
    public String toString() {
        return "CS{" +
                "data='" + data + '\'' +
                ", pName='" + pName + '\'' +
//                ", pDateTime='" + pDateTime + '\'' +
                ", cName='" + cName + '\'' +
//                ", cDateTime='" + cDateTime + '\'' +
                '}';
    }
}
