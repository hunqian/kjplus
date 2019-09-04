package org.ybk.basic.util;

import java.io.File;
import java.util.*;

public class SysUtil {

	public static void printSysEnv() {
		Properties props = System.getProperties();
		Enumeration keys = props.keys();
		String k = null;
		while(keys.hasMoreElements()){
			k = keys.nextElement().toString();
			System.out.println("["+k+"]" + System.getProperty(k));
		}
	}
		
	//jarFileName=true,只截取名字
	public static List<String> listSysJars(boolean jarFileName){
		String fileSep = System.getProperty("file.separator");
		int pos = 0;
		String paths = System.getProperty("java.class.path");
		List<String> jars = new ArrayList<String>();
		if(Util.isNull(paths))
			return jars;
		List<String> preJars = Util.str2Array(paths, ";");
		for(String j: preJars){
			if(Util.isNull(j))
				continue;
			if(!j.contains(".jar"))
				continue;
			if(jarFileName){
				pos = j.lastIndexOf(fileSep);
				jars.add(j.substring(pos+1));
			}else
				jars.add(j);
			
		}
		return jars;
	}

	public static void comparePath(String tomcatPath){
		List<String> testSysJars = SysUtil.listSysJars(true);
		//String tomcatPath = "E:/devSpaces/apache-tomcat-7.0.59-mall4/webapps/kejun/WEB-INF/lib";
		File tomcatFiles = new File(tomcatPath);
		String[] tfs = tomcatFiles.list();
		Map<String,String> tfHash = new HashMap<String,String>(); 
		for(String tf:tfs){
			tfHash.put(tf, "Y");
		}
		//String fileSep = System.getProperty("file.separator");
		//int pos = 0;
		String sf = null;
		for(int i=0; i<testSysJars.size();){
			sf = testSysJars.get(i);
			if(tfHash.containsKey(sf)){
				testSysJars.remove(i);
				tfHash.remove(sf);
			}else
				i++;
		}
		System.out.println("[Sys]");
		for(String tf:testSysJars){
			System.out.println("\t" + tf);
		}
		System.out.println("[Tom]");
		System.out.println("\t" + tfHash.toString());
	}
		
	public static void main(String argcp[]){
		printSysEnv();
		/*List<String> preJars = listSysJars(false);
		for(String j: preJars)
			System.out.println(j);*/
		
		//String tomcatPath = "E:/devSpaces/apache-tomcat-7.0.59-mall4/webapps/kejun/WEB-INF/lib";
		//comparePath(tomcatPath);
		
	}
}
