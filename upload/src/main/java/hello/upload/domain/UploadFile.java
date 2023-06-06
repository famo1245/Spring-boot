package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    // 사용자가 같은 이름으로 파일을 업로드할 수 있기 때문
    private String storeFileName;

}
