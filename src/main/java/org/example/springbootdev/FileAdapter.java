package org.example.springbootdev;

import org.example.springbootdev.file.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

public class FileAdapter {

    public static FileDto uploadFile(MultipartFile file) throws Exception {
        if(file.isEmpty()) throw new Exception("파일이 존재하지 않습니다.");
        int thisYear = LocalDate.now().getYear();
        File folder = new File("/Users/leejam/Desktop/test/" + thisYear);

        if(!folder.exists()) {
            try{
                folder.mkdirs(); // 폴더생성
                System.out.println("폴더생성");
            } catch(Exception e) {
                e.getStackTrace(); // 에러발생
            }
        }
        FileDto fileDto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
        Path savePath = Paths.get("/Users/leejam/Desktop/test/" + thisYear + File.separator + fileDto.getUuid() + "_" + fileDto.getFileName());
        file.transferTo(savePath);
        return fileDto;
    }
}
