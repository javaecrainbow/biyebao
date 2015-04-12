package com.rose4j.biyebao.common;

import java.io.File;
import java.io.FileOutputStream;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

public class ExcelTools extends TestCase {
 public void testExcel() throws Exception
	    {
//	        ExcelFileGenerator generator = new ExcelFileGenerator(new ArrayList<String>("1,2,3"),new ArrayList<String>("aa","bb","cc"),);
	        ExcelFileGenerator generator = new ExcelFileGenerator(Lists.newArrayList("耗时","词语","Fql"), 
	        		Lists.newArrayList(Lists.newArrayList("11","bb","cc"), Lists.newArrayList("22","ee","ff"),Lists.newArrayList("33","gg","ff"),Lists.newArrayList("44","hh","ff"),Lists.newArrayList("55","ll","ff")));
	        generator.exportExcel(new FileOutputStream(new File("D:\\a.xls")));
	    }
}
