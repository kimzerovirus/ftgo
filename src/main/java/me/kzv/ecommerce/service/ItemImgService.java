package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.domain.itemimg.ItemImg;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ItemImgService {
    private final FileService fileService;

    public ItemImg saveItemImg(ItemImg itemImg, MultipartFile file) throws Exception {
        String imgNm = fileService.saveUploadImg(file);
        String imgUrl = "/images/item/";
        itemImg.update(imgNm, imgUrl, itemImg.getIsRepImg()); // 대표이미지 여부는 그대로
        return itemImg;
    }
}
