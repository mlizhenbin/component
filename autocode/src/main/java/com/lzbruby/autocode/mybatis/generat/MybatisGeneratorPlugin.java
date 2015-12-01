package com.lzbruby.autocode.mybatis.generat;

import com.lzbruby.autocode.mybatis.generat.start.GeneratorStarter;
import com.lzbruby.autocode.mybatis.generat.start.PluginGeneratorStarter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @goal create
 */
public class MybatisGeneratorPlugin extends AbstractMojo {

    /**
     * 打印MybatisGeneratorPlugin.java日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisGeneratorPlugin.class);

    public void execute() throws MojoExecutionException, MojoFailureException {
        GeneratorStarter starter = new PluginGeneratorStarter();
        starter.start();
        LOGGER.info("auto plugin Generator code finish...");
    }
}
