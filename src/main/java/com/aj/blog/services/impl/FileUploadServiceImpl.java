package com.aj.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aj.blog.services.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	 

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//file name
		String name = file.getOriginalFilename();
		
		//randomName generate file
		String randomId = UUID.randomUUID().toString();
		String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//full path
		String filePath = path + File.separator + fileName;
		
		//create folder if not created
		File f=new File(path);
		
		if(!f.exists())
		{
			f.mkdir();
		}
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullpath = path + File.separator + fileName;
		
		InputStream is=new FileInputStream(fullpath);
		
		return is;
	}

}
