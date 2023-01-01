package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${uploadPath}")
    private String uploadPath;

    public String saveUploadImg(MultipartFile multipartFile) throws Exception {
        if (multipartFile.isEmpty()){
            throw new FileNotFoundException("최소 1개 이상의 상품 사진이 필요합니다.");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String saveImgName = UUID.randomUUID() + extractExt(originalFilename);
        multipartFile.transferTo(new File(uploadPath + saveImgName));
        return saveImgName;
    }

    public void deleteUploadImg(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            log.info("파일 삭제: " + filePath);
        } else {
            log.info(filePath + "파일이 존재하지 않습니다.");
        }
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
