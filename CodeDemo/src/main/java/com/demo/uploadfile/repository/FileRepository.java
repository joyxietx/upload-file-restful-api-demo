package com.demo.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.uploadfile.model.FileEntity;


public interface FileRepository extends JpaRepository<FileEntity, Long>{
	
	public FileEntity findById(Long id);
	
}
