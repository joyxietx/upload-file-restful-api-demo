package com.demo.uploadfile.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Uploaded_Files")
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="file_id")
	public Long id;
		
	@Column(name="file_name")
	private String name;
	
	@Column(name="file_size")
	private Long size;
	
	@Column(name="file_type")
	private String type;
	
	@Column(name="uploaded_location")
	private String uploadedLocation;

	public FileEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	

	public String getUploadedLocation() {
		return uploadedLocation;
	}

	public void setUploadedLocation(String uploadedLocation) {
		this.uploadedLocation = uploadedLocation;
	}
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}
