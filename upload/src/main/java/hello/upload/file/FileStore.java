package hello.upload.file;


import org.springframework.beans.factory.annotation.Value;

public class FileStore {

    @Value("${file.dir}")
    private String filePath;
}
