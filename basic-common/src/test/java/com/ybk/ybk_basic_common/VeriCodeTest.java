package com.ybk.ybk_basic_common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ybk.basic.file.ReadFromFile;
import org.ybk.basic.util.FileUtil;
import org.ybk.basic.util.Util;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class VeriCodeTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public VeriCodeTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(VeriCodeTest.class);
	}

	public void atestCage() {
		OutputStream os = null;
		try {
			Cage cage = new GCage();
			//String code = cage.getTokenGenerator().next();
			String code = Util.genDigiCodeStr(6);
			System.out.println("[code]" + code);
			os = new FileOutputStream("D:/temp/vericode/captcha.jpg", false);
			cage.draw(code, os);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try{
				if (os != null)
					os.close();
			}catch(Exception e){}
		}
		assertTrue(true);
	}

	public void testDemo() {
		JiebaSegmenter segmenter = new JiebaSegmenter();
	    String sentence = "isn’tit";
	    System.out.println(segmenter.process(sentence, SegMode.SEARCH).toString());
	    System.out.println(segmenter.sentenceProcess(sentence).toString());
	    
	}
	
	public void testTransferDic() {
	    String file = "E:/docs/题库大王/技术/dict_eng.txt";
	    String destFile = "E:/docs/题库大王/技术/dict_eng_jieba.txt";
	    List<String> lines = ReadFromFile.readFileAllLines(file,"GBK");
	    try{
		    for(String line: lines){
		    	FileUtil.appendText(destFile, line+" 100 m\r\n");
		    }
	    }catch(IOException ioe){
	    	ioe.printStackTrace();
	    }
	}
	
	public void atestWordSeq(){
		
		List<String> list = new ArrayList<String>();
		list.add("xab");
		list.add("ac");
		list.add("ab");
		list.add("ab");
		list.add("fi");

		//升序
		Collections.sort(list,Collator.getInstance(java.util.Locale.ENGLISH));
		for(int i=0;i<list.size();i++){
		    System.out.println(list.get(i));
		}
		System.out.println("");
	}
}
