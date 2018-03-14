package com.demo.uploadfile.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils; 
import org.springframework.web.multipart.MultipartFile;

import com.demo.uploadfile.model.FileEntity;
import com.demo.uploadfile.repository.FileRepository;

@Service
public class UploadFileServiceImpl implements UploadFileService {
	
	private String storeFolderPath;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	public UploadFileServiceImpl(@Value("${storeLocation}") String storeFolderPath) {
		this.storeFolderPath = storeFolderPath;
		//this.fileRepository = fileRepository;
	}

	public void setStoreFolderPath(String storeFolderPath) {
		this.storeFolderPath = storeFolderPath;
	}

	@Override
	public FileEntity save(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		// upload file to file system
		Files.copy(file.getInputStream(), Paths.get(storeFolderPath).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println(Paths.get(storeFolderPath).resolve(fileName).toString());
		
		// save meta data to in-memory database
		FileEntity fileEntity = new FileEntity();
		fileEntity.setName(fileName);
		fileEntity.setSize(file.getSize());
		fileEntity.setUploadedLocation(Paths.get(storeFolderPath).toAbsolutePath().toString());
		fileEntity.setType(file.getContentType());
		
		FileEntity fe = fileRepository.save(fileEntity);
		
		return fe;
	}
	
	@Override
	@Transactional
	public List<FileEntity> listAllUploadedFiles() {
		return fileRepository.findAll();
	}
	
	@Override
	@Transactional
	public FileEntity findById(Long id) {
		return fileRepository.findById(id);
	}
}
