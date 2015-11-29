package com.lzb.autocode.mybatis.generat;

import com.lzb.autocode.mybatis.generat.start.DefaultGeneratorStarter;
import com.lzb.autocode.mybatis.generat.start.GeneratorStarter;

/**
 * 功能描述：自动化生成代码启动入口
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/12 Time：23:42
 */
public class MybatisGeneratorStarter {

    public static void main(String[] args) {
        GeneratorStarter starter = new DefaultGeneratorStarter();
        starter.start();
    }
}
