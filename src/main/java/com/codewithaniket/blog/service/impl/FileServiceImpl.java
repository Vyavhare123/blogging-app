package com.codewithaniket.blog.service.impl;

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

import com.codewithaniket.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// file name

		String name = file.getOriginalFilename();
		
		// random name generate file
		String randomId=UUID.randomUUID().toString();
		String filePath1=randomId.concat(name.substring(name.lastIndexOf(".")));

		// full path

		String filePath = path + File.separator + filePath1;

		// create folder if not created

		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();
		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filePath));

		// TODO Auto-generated method stub
		return filePath1;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		
		String fullPath=path+File.separator+filename;
		
		InputStream is=new FileInputStream(fullPath);
		
		
		return is;
	}


}
