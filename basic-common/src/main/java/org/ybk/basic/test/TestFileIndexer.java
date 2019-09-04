package org.ybk.basic.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestFileIndexer {
	public static void main(String[] args) throws Exception {
		/* 指明要索引文件夹的位置,这里是C盘的source文件夹下 */
		File fileDir = new File("d:/temp/source ");
		/* 这里放索引文件的位置 */
		File indexDir = new File("d:/temp/index");
		Directory dir = FSDirectory.open(indexDir);// 将索引存放在磁盘上
		Analyzer lucenAnalyzer = new StandardAnalyzer(Version.LUCENE_36);// 分析器
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, lucenAnalyzer);
		iwc.setOpenMode(OpenMode.CREATE);// 创建新的索引文件create 表示创建或追加到已有索引库
		IndexWriter indexWriter = new IndexWriter(dir, iwc);// 把文档写入到索引库
		File[] textFiles = fileDir.listFiles();// 得到索引文件夹下所有文件
		long startTime = new Date().getTime();
		// 增加document到检索去
		for (int i = 0; i < textFiles.length; i++) {
			// if (textFiles[i].isFile()&&
			// textFiles[i].getName().endsWith(".txt")) {
			System.out.println(":;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
			System.out.println("File" + textFiles[i].getCanonicalPath() + "正在被索引...");
			String temp = readAllFiles(textFiles[i].getCanonicalPath(), "GBK");
			System.out.println(temp);
			Document document = new Document();
			Field fieldPath = new Field("path", textFiles[i].getPath(), Field.Store.YES, Field.Index.NO);
			Field fieldBody = new Field("body", temp, Field.Store.YES, Field.Index.ANALYZED,
					Field.TermVector.WITH_POSITIONS_OFFSETS);
			NumericField modifiField = new NumericField("modified");// 所以key为modified
			modifiField.setLongValue(fileDir.lastModified());
			document.add(fieldPath);
			document.add(fieldBody);
			document.add(modifiField);
			indexWriter.addDocument(document);
			// }
		}
		indexWriter.close();
		// 计算一下索引的时间
		long endTime = new Date().getTime();
		System.out.println("花了" + (endTime - startTime) + "毫秒把文档添加到索引里面去" + fileDir.getPath());
	}

	public static String readAllFiles(String FileName, String charset) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
		String line = new String();
		String temp = new String();
		while ((line = reader.readLine()) != null) {
			temp += line;
		}
		reader.close();
		return temp;
	}
}
