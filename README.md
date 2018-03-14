# upload-file-restful-api-demo
This is a upload file restful api demo based on spring boot

## API List

| Function | URL format|  Method | 
| :------:| :---------: |  :---------: |
| Choose a file to upload | http://localhost:12345/upload | POST |
| List all uploaded files' metadata | http://localhost:12345/files | GET |
| Get a file's metadata by file ID | http://localhost:12345/file/{id} | GET |

**NOTE**: 

+ To change port number (default value 12345):

Change value of `server.port` in `src/main/resources/application.properties`

+ To set the upload folder in file system:

Change value of `storeLocation` in `src/main/resources/application.properties`

## Postman screenshots

+ Choose a file to upload:

![](https://github.com/joyxietx/upload-file-restful-api-demo/blob/master/PostmanScreenshots/upload.png)

+ List all uploaded files' metadata

![](https://github.com/joyxietx/upload-file-restful-api-demo/blob/master/PostmanScreenshots/list_all_upload_file_metadata.png)

+ Get a file's metadata by file ID

![](https://github.com/joyxietx/upload-file-restful-api-demo/blob/master/PostmanScreenshots/get_one_file_metadata_by_id.png)


