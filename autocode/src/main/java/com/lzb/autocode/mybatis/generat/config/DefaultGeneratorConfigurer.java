package com.lzb.autocode.mybatis.generat.config;

import com.google.common.collect.Lists;
import com.lzb.autocode.mybatis.generat.generator.context.PackageConfigType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * 功能描述：基本的配置类实现
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/12 Time：22:57
 */
public class DefaultGeneratorConfigurer implements GeneratorConfigurer {

    /**
     * 打印DefaultGeneratorConfigurer.java日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGeneratorConfigurer.class);

    private static Properties properties;

    public Properties getProperties() {
        synchronized (DefaultGeneratorConfigurer.class) {
            if (null == properties) {
                loadProperties();
            }
            return properties;
        }
    }

    /**
     * 设置默认值配置
     */
    public void initConfigParams() {
        initPackage();
        initProjectName();
        initTablePrefix();
        initPrecision();
        initDomain();
        initLayers();
        initLocation();
        initJavaSrc();
        initSuffixEmail();
        initOrg();
    }

    protected void initPackage() {
        String value = (String) properties.get("generator.package");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.package", GENERATOR_PACKAGE);
    }

    protected void initProjectName() {
        String value = (String) properties.get("generator.project.name");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.project.name", GENERATOR_PROJECT_NAME);
    }

    protected void initTablePrefix() {
        String value = (String) properties.get("generator.tablePrefix");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.tablePrefix", GENERATOR_TABLEPREFIX);
    }

    protected void initPrecision() {
        String value = (String) properties.get("generator.precision");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.precision", GENERATOR_PRECISION);
    }

    protected void initDomain() {
        String value = (String) properties.get("generator.domain");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.domain", GENERATOR_DOMAIN);
    }


    protected void initLayers() {
        String value = (String) properties.get("generator.layers");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.layers", PackageConfigType.getDefaultConfigLayer());
    }

    protected void initLocation() {
        String value = (String) properties.get("generator.location");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("generator.location", GENERATOR_LOCATION);
    }

    protected void initJavaSrc() {
        String value = (String) properties.get("java.src");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("java.src", JAVA_SRC);
    }

    protected void initOrg() {
        String value = (String) properties.get("class.org");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("class.org", CLASS_ORG);
    }

    protected void initSuffixEmail() {
        String value = (String) properties.get("class.email.suffix");
        if (StringUtils.isNotBlank(value)) {
            return;
        }
        properties.setProperty("class.email.suffix", CLASS_SUFFIX);
    }

    /**
     * 加载读取配置
     */
    protected void loadProperties() {
        List<String> dirAllFiles = listProjectDirAllFiles(System.getProperties().getProperty("user.dir"));
        if (CollectionUtils.isEmpty(dirAllFiles)) {
            throw new RuntimeException("读取工程目录下的文件为空.");
        }

        String configFilePath = null;
        for (String dirAllFile : dirAllFiles) {
            if (dirAllFile.endsWith(CONFIG_GENERATOR_PATH)) {
                configFilePath = dirAllFile;
                break;
            }
        }

        DefaultGeneratorConfigurer.properties = new Properties();
        InputStream input = null;
        try {
            LOGGER.info("加载配置文件" + configFilePath);
            input = new FileInputStream(configFilePath);
            DefaultGeneratorConfigurer.properties.load(input);
        } catch (Exception e) {
            LOGGER.warn("加载配置文件出现异常，读取默认配置" + LOCAL_GENERATOR_PATH);
            try {
                DefaultGeneratorConfigurer.properties = PropertiesLoaderUtils.loadAllProperties(LOCAL_GENERATOR_PATH);
            } catch (IOException ex) {
                throw new RuntimeException("读取配置文件失败.", e);
            }
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 扫描整个workspace的配置文件
     *
     * @param projectPath
     * @return
     */
    private static List<String> listProjectDirAllFiles(String projectPath) {
        List<String> fileNames = Lists.newArrayList();
        Vector<String> vector = new Vector<String>();
        vector.add(projectPath);
        while (vector.size() > 0) {
            File[] files = new File(vector.get(0).toString()).listFiles();
            vector.remove(0);

            int len = files.length;
            for (int i = 0; i < len; i++) {
                String tmpDir = files[i].getAbsolutePath();
                if (files[i].isDirectory()) {
                    vector.add(tmpDir);
                } else if (tmpDir.endsWith(".properties") && !tmpDir.contains("class")) {
                    fileNames.add(tmpDir);
                }
            }
        }
        return fileNames;
    }
}
