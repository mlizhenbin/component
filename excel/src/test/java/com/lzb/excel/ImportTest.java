package com.lzb.excel;


import com.lzb.excel.imported.ImportedExcelFacade;
import com.lzb.excel.imported.context.ExcelPathContext;
import com.lzb.excel.imported.impl.DefaultImportedExcelImpl;

import java.util.List;

/**
 * 功能描述：
 *
 * @author: Zhenbin.Li
 * email： zhenbin.li@okhqb.com
 * company：华强北在线
 * Date: 2015/1/10 Time：10:34
 */
public class ImportTest {

    public static void main(String[] args) {
        ExcelPathContext test = new ExcelPathContext(TestEntity.class, "e://test.xlsx");
        ImportedExcelFacade importedExcelFacade = new DefaultImportedExcelImpl();
        List<TestEntity> read = importedExcelFacade.read(test);
        System.out.println(read);
    }


}
