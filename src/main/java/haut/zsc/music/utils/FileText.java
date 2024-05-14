package haut.zsc.music.utils;

import java.io.File;
import java.io.FileFilter;
 
public class FileText {
	public static void main(String[] args) {
		String path = "F:\\zsc";		//要遍历的路径
		File file = new File(path);		//获取其file对象
		func(file);
	}
	
	private static void func(File file){
		File[] fs = file.listFiles();
		for(File f:fs){
			if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
				func(f);
			if(f.isFile())		//若是文件，直接打印
				System.out.println(f);
		}
	}
}
