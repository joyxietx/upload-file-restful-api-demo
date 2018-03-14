package com.demo.uploadfile.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.demo.uploadfile.model.FileEntity;

public interface UploadFileService {
	
	//public void setLocation();
	
	public FileEntity save(MultipartFile file) throws IOException;
	
	public List<FileEntity> listAllUploadedFiles();
	
	public FileEntity findById(Long id);
	
}
