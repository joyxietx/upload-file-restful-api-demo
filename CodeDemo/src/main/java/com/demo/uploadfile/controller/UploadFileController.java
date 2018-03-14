package com.demo.uploadfile.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.uploadfile.model.FileEntity;
import com.demo.uploadfile.service.UploadFileService;

@RestController
public class UploadFileController {
	
	public static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
	
	@Autowired
	private UploadFileService uploadFileService;
	
	
	@PostMapping(value="/upload") 
	public FileEntity uploadFile(MultipartFile file) {
		FileEntity fe = null;
		try {
			logger.info("Trying to upload file.");
			fe = uploadFileService.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fe;
	}

	@GetMapping("/files")
	public List<FileEntity> listAllUploadedFiles() {
		return uploadFileService.listAllUploadedFiles();
	}
	
	@GetMapping("/file/{id}")
	public FileEntity getFileByName(@PathVariable(value="id") Long id) {
		return uploadFileService.findById(id);
	}
}
