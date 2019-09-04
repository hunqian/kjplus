package org.ybk.basic.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SysMachineUtil {

	public static String getCPUProcessorid() {
		return getSerial("cpu","processorid");
	}
	
	public static String getBoardSerial() {
		return getSerial("BaseBoard","SerialNumber");
	}
	
	public static String getDiskDeviceid() {
		return getSerial("DiskDrive","deviceid");
	}
	
	public static String getDiskPNPDeviceid() {
		return getSerial("DiskDrive","PNPDeviceID");
	}
	
	public static String getCsproduct() {
		return getSerial("csproduct","IdentifyingNumber");
	}
	
	public static String getSerial(String device,String id) {
		Scanner sc = null;
		try{
			long start = System.currentTimeMillis();
			Process process = Runtime.getRuntime().exec(new String[] { "wmic", device, "get", id });
			process.getOutputStream().close();
			sc = new Scanner(process.getInputStream());
			String property = sc.next();
			String serial = sc.next();
			//System.out.println(property + ": " + serial);
			//System.out.println("time:" + (System.currentTimeMillis() - start));
			return serial;
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(sc != null)
				sc.close();
		}
		return null;
	}

	public static void main(String argcp[]){
		String serial = getCPUProcessorid();
		getBoardSerial();
		getDiskDeviceid();
		getDiskPNPDeviceid();
		getCsproduct();
		//System.out.println();
	}
}
