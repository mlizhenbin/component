package com.lzbruby.autocode.mybatis.generat.generator.impl;

import java.util.List;
import java.util.Map;

import com.lzbruby.autocode.mybatis.generat.connect.Connector;
import com.lzbruby.autocode.mybatis.generat.generator.context.GeneratorContext;
import com.lzbruby.autocode.mybatis.generat.generator.context.PackageConfigType;
import com.lzbruby.autocode.mybatis.generat.utils.GeneratorStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

/**
 * 功能描述：Mapper.xml代码生成
 *
 * @author: Zhenbin.Li
 * email： lizhenbin@oneplus.cn
 * company：一加科技
 * Date: 15/6/13 Time：00:51
 */
public class MapperGenerator extends BaseGenerator {

    @Override
    public void initVelocityContext(VelocityContext velocityContext, GeneratorContext generatorContext) {
        super.initVelocityContext(velocityContext, generatorContext);

        String tableName = generatorContext.getTableName();
        Connector connector = (Connector) generatorContext.getAttribute("connector");
        Map<String, String> columnNameTypeMap = connector.mapColumnNameType(tableName);
        List<String> allIndexs = connector.listAllIndex(tableName);

        List<String> resultMapColumns = Lists.newArrayList();
        List<String> whereConditions = Lists.newArrayList();
        List<String> columns = Lists.newArrayList();
        List<String> insertValueConditions = Lists.newArrayList();
        List<String> insertColsConditions = Lists.newArrayList();
        List<String> updateConditions = Lists.newArrayList();

        String pk = (String) velocityContext.get("normalPrimaryKey");
        for (String col : columnNameTypeMap.keySet()) {
            String field = GeneratorStringUtils.format(col);
            columns.add(tableName + "." + col);
            StringBuilder cloumBf = new StringBuilder();
            cloumBf.append("<result property=\"").append(field).append("\" column=\"").append(col).append("\"/>");
            resultMapColumns.add(cloumBf.toString());

            if (columnNameTypeMap.get(col).equals("Date")) {
                StringBuilder conditionBfs = new StringBuilder();
                conditionBfs.append("<if test=\"").append(field).append("!=null and ''!=").append(field).append("\">\n")
                        .append("\t\t\t\tAND ").append(tableName).append(".").append(col).append(" &gt;= #{").append("dynamicFileds_").append(col).append("}\n")
                        .append("\t\t\t</if>");
                whereConditions.add(conditionBfs.toString());
                StringBuilder conditionBfe = new StringBuilder();
                conditionBfe.append("<if test=\"").append(field).append("!=null and ''!=").append(field).append("\">\n")
                        .append("\t\t\t\tAND ").append(tableName).append(".").append(col).append(" &lt; #{").append("dynamicFileds_").append(col).append("}\n")
                        .append("\t\t\t</if>");
                whereConditions.add(conditionBfe.toString());
            } else {
                StringBuilder conditionBf = new StringBuilder();
                conditionBf.append("<if test=\"").append(field).append("!=null and ''!=").append(field).append("\">\n")
                        .append("\t\t\t\tAND ").append(tableName).append(".").append(col).append(" = #{").append(field).append("}\n")
                        .append("\t\t\t</if>");
                whereConditions.add(conditionBf.toString());


                boolean isKey = false;
                if (!CollectionUtils.isEmpty(allIndexs)) {
                    for (String allIndex : allIndexs) {
                        if (StringUtils.contains(allIndex, col)) {
                            isKey = true;
                            break;
                        }
                    }
                }
                if ((StringUtils.equals(field, pk) || isKey) && !StringUtils.equals(field, "id")) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("<if test=\"").append(field).append("s").append("!=null and ''!=").append(field).append("s").append("\">\n")
                            .append("\t\t\t\tAND ").append(tableName).append(".").append(col).append(" IN\n")
                            .append("\t\t\t\t<foreach collection=\"").append(field).append("s\" item=\"").append(field).append("\" open=\"(\" close=\")\" separator=\",\">\n")
                            .append("\t\t\t\t\t").append("#{").append(field).append("}\n")
                            .append("\t\t\t\t</foreach>\n")
                            .append("\t\t\t</if>");
                    whereConditions.add(builder.toString());
                }
            }

            if (col.startsWith("gmt")) {
                insertValueConditions.add("now(),");
            } else {
                StringBuilder conditionValueBf = new StringBuilder();
                conditionValueBf.append("<if test=\"").append(field).append("!=null and ''!=").append(field).append("\">\n")
                        .append("\t\t\t\t").append("#{").append(field).append("},\n")
                        .append("\t\t\t</if>");
                insertValueConditions.add(conditionValueBf.toString());
            }
            StringBuilder conditionColBf = new StringBuilder();
            conditionColBf.append("<if test=\"").append(field).append("!=null and ''!=").append(field).append("\">\n")
                    .append("\t\t\t\t").append(col).append(",\n")
                    .append("\t\t\t</if>");
            insertColsConditions.add(conditionColBf.toString());

            if (!StringUtils.equals(pk, field)) {
                StringBuilder upBf = new StringBuilder();
                upBf.append("<if test=\"").append(field).append("!=null and ''!=").append(field).append("\">\n")
                        .append("\t\t\t\t").append(tableName).append(".").append(col).append(" = #{").append(field).append("},\n")
                        .append("\t\t\t</if>");
                updateConditions.add(upBf.toString());
            }
        }

        velocityContext.put("resultMapColumns", resultMapColumns);
        velocityContext.put("whereConditions", whereConditions);
        velocityContext.put("insertValueConditions", insertValueConditions);
        velocityContext.put("insertColsConditions", insertColsConditions);
        velocityContext.put("updateConditions", updateConditions);
        for (int i = 0; i < columns.size() - 1; i++) {
            String tempCol = columns.get(i) + ",";
            columns.set(i, tempCol);
        }
        velocityContext.put("columns", columns);
        velocityContext.put("columnPrimaryKey", generatorContext.getAttribute("columnPrimaryKey"));
    }

    @Override
    protected PackageConfigType getPackageConfigType() {
        return PackageConfigType.mapper;
    }

    @Override
    protected String getDescription() {
        return " Dao Mapper";
    }


}
