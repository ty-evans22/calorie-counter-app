package coms309.backend.roundtrip.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	 
	//directory path;
	private final String directoryPath="/uploads/";
	 
	//create directory if doesn't exist
	private void createDirIfNotExist() {
	        //create directory to save the files
	        File directory = new File(directoryPath);
	        if (! directory.exists()){
	            directory.mkdir();
	        }
	    }
	
	//store file on server
	public String storeFile(MultipartFile file) {
		try {
            createDirIfNotExist();

            // read and write the file to the local folder
            byte[] bytes = new byte[0];
            Path p;
            try {
                bytes = file.getBytes();
                p = Paths.get(directoryPath + file.getOriginalFilename());
                Files.write(p, bytes);
            } catch (IOException e) {
            	throw new RuntimeException("Upload failed. Try again!", e);
            };

            return p.toString();

        } catch (Exception e) {
        	throw new RuntimeException("Upload failed. Try again!", e);
        }
	}
	
	//to load the file
	public Resource getResource(String path) throws Exception{
		Path p = Path.of(path);
		return new UrlResource(p.toUri());
	}
}