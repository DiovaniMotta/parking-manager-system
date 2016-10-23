package br.com.furb.programacao.parking.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

	public static boolean exists(String path){
		File file = new File(path);
		return file.exists();
	}
	
	public static void createDirectory(String path) throws IOException{
		Path p = Paths.get(path);
		Files.createDirectories(p);
	}
	
	public static void create(String path) throws IOException{
		Path p = Paths.get(path);
		Files.createFile(p);
	}
}
