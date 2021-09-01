package com.jti.event.util;


import com.jti.event.front.model.common.ImageModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UploadHelper {

    private final Log log = LogFactory.getLog(UploadHelper.class);

    @Value("${upload.main.path}")
    private String uploadPath;

    private long currentTime;

    public UploadHelper(){
        currentTime = System.currentTimeMillis();
    }

    public List<ImageModel> imageContentUpload(MultipartFile mf){

        // 실제 저장 경로(월단위저장)
        String yyyyMM = DateUtil.getDate("yyyyMM");
        String savePath = uploadPath + yyyyMM+"/";


        List<ImageModel> listImageModel = new ArrayList();
        UUID uuid = UUID.randomUUID();
        String saveName = uuid.toString() + "_" + currentTime;
        String realName = mf.getOriginalFilename();
        String ext = realName.substring(realName.lastIndexOf("."));

        String saveFile = savePath + saveName + ext;
        System.out.println("savePath:"+savePath);
        System.out.println("savefile:"+saveFile);
        ImageModel imageModel = null;


        try{
            imageModel = new ImageModel();

            File file = new File(savePath);
            if (file.exists() == false) {
                file.mkdirs();
            }

            if (mf.isEmpty() == false) {

                imageModel.setOriginName(realName);
                imageModel.setImageSize(mf.getSize());
                imageModel.setImagePath(saveFile);
                imageModel.setImageUrl(saveFile.replace(uploadPath, "/upload/"));


                Image srcImg = null;
                String suffix = ext.toLowerCase();

                if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif") || suffix.equals("jpg")) {
                    srcImg = ImageIO.read(new File(saveFile));
                } else {
                    srcImg = new ImageIcon(saveFile.toString()).getImage();
                }

                if(null != srcImg){
                    imageModel.setImageWidth(srcImg.getWidth(null));
                    imageModel.setImageHeight(srcImg.getHeight(null));
                }

                file = new File(saveFile);
                System.out.println("file:"+file);
                mf.transferTo(file);

                listImageModel.add(imageModel);

                // 이벤트 목록 썸네일 생성
                /*
                if(tableKind.equals(Constant.ImageType.tableKind.EVENT)){
                    listImageModel.add(createThumbnail(320, 149, "thumnail_320_149", tableKind, saveName, savePath, saveFile, realName));
                    listImageModel.add(createThumbnail(502, 250, "thumnail_502_250", tableKind, saveName, savePath, saveFile, realName));
                }
                */
            }



        }catch(Exception e){
            log.error(">>> fileupload save error : " + realName, e);
            imageModel = null;
        }
        return listImageModel;
    }
}
