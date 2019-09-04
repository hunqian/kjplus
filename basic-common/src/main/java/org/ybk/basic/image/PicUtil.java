package org.ybk.basic.image;

import java.io.File;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;  
import java.util.List;
import java.util.UUID;

import com.drew.imaging.jpeg.JpegMetadataReader;  
import com.drew.metadata.Directory;  
import com.drew.metadata.Metadata;  
import com.drew.metadata.Tag;   

public class PicUtil {
	
	private static String[][] PARAMS = {
			{"JPEG#Image Height","ImageHeight"}
			,{"JPEG#Image Width","ImageWidth"}
			,{"Exif IFD0#Make","Make"}
			,{"Exif IFD0#Model","Model"}
			,{"Exif IFD0#Date/Time","Time"}
			,{"Exif SubIFD#F-Number","F-Number"}
			//,{"Exif SubIFD#Exposure Program",""}
			//,{"Exif SubIFD#ISO Speed Ratings",""}
			//,{"Exif SubIFD#Date/Time Original",""}
			//,{"Exif SubIFD#Date/Time Digitized",""}
			,{"Exif SubIFD#Shutter Speed Value","SpeedValue"}
			,{"Exif SubIFD#Lens Model","LensModel"}
			,{"IPTC#Coded Character Set","ENCODING"}
			,{"File#File Name","FileName"}
			,{"File#File Size","FileSize"}
			,{"File#File Modified Date","ModifiedDate"}
	};
	
	//列出所需的内容
	public static List<TagInfo> listPicInfos(String file) {
		
		Hashtable<String,String> PARAMS_HASH = new Hashtable<String,String>();
		for(int i=0;i<PARAMS.length;i++){
			PARAMS_HASH.put(PARAMS[i][0], PARAMS[i][1]);
		}
		List<TagInfo> infos = new ArrayList<TagInfo>();
		
		try {
			File jpegFile = new File(file);
			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
			Iterator<Directory> dirs = metadata.getDirectories().iterator();
			String paramKey = null;
			while(dirs.hasNext()){
				Directory d = dirs.next();
				//System.out.println("name:" + d.getName() + "=" );
				Iterator<Tag> tags = d.getTags().iterator();
				while(tags.hasNext()){
					Tag t = tags.next();
					paramKey = d.getName() + "#" + t.getTagName();
					if(PARAMS_HASH.containsKey(paramKey)){
						infos.add(new TagInfo(PARAMS_HASH.get(paramKey),d.getString(t.getTagType())));
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return infos;
	}	
	
	public static void showPicInfo(String file) {
		
		try {
			File jpegFile = new File(file);
			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
			int total = metadata.getDirectoryCount();
			Iterator<Directory> dirs = metadata.getDirectories().iterator();
			while(dirs.hasNext()){
				Directory d = dirs.next();
				//System.out.println("name:" + d.getName() + "=" );
				Iterator<Tag> tags = d.getTags().iterator();
				while(tags.hasNext()){
					Tag t = tags.next();
					System.out.println(d.getName() + "#" + t.getTagName() + "=" + d.getString(t.getTagType()));
					//System.out.println("\tTag:" + t.getTagName() + "=" + d.getString(t.getTagType()));
				}
			}
			/*Directory exif = metadata.getDirectory(ExifDirectory.class);
			Iterator tags = exif.getTagIterator();
			while (tags.hasNext()) {
				Tag tag = (Tag) tags.next();
				System.out.println(tag);
			}*/
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	public static void main(String argc[]){
		String file = "D:/temp/IMG_5976.jpg";
		//showPicInfo(file);
		List<TagInfo> infos = listPicInfos(file);
		//System.out.println(infos);
		for(TagInfo info:infos)
			System.out.println(info);
		
		 UUID uuid = UUID.randomUUID();
	        System.out.println(uuid);
	}
}
