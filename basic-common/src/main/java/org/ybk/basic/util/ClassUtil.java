package org.ybk.basic.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {

	public static List<String> getClassPath(boolean jarOnly) {
		
		List<String> pathes = new ArrayList<String>(); 
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		// Get the URLs
		URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();
		String p = null;
		for (int i = 0; i < urls.length; i++) {
			p = urls[i].getFile();
			if(!jarOnly){
				pathes.add(p);
			}else{
				if(p.endsWith(".jar"))
					pathes.add(p);
			}
		}
		return pathes;
	}
	
	public static void main(String argcs[]){
		List<String> pathes = getClassPath(true);
		for(String p: pathes){
			System.out.println(p);
		}
	}
}
