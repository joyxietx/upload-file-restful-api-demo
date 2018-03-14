package com.demo.uploadfile.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.uploadfile.model.FileEntity;
import com.demo.uploadfile.repository.FileRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadFileServiceImplTest {
	
	@Autowired
	private UploadFileService uploadFileService;

	
	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();
	
	private File storeFolder;
	
	private Path uploadedPath;
	
	@Before
	// create the folder for store uploaded files 
	public void setUp() throws URISyntaxException, IOException {
		
		storeFolder = testFolder.newFolder("test-uploaded-files");
		
		uploadFileService.setStoreFolderPath(storeFolder.getAbsolutePath());
		
	}
	
    @Test
    public void saveFile() throws IOException {
    	
    	MockMultipartFile mmf = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "This is a test file".getBytes());
   
    	FileEntity fileEntity = uploadFileService.save(mmf);
    	
    	// get the absolute path of the uploaded file
    	uploadedPath = storeFolder.toPath().resolve("foo.txt");
    	
    	// read the uploaded file content
    	BufferedReader reader = Files.newBufferedReader(uploadedPath, Charset.forName("UTF-8"));   	
    	StringBuilder sb = new StringBuilder();
    	String currLine = null;
    	while((currLine = reader.readLine()) != null) {
    		sb.append(currLine);
    	}
    	
    	String uploadedFileContent = sb.toString();
    	
    	assertEquals(uploadedFileContent, "This is a test file");
    }

    
    @After
    // delete all dirs and files created for testing
    public void cleanUp() throws IOException {    	
    	testFolder.delete();
    }
	
}
