package com.jti.event.front.service.common;

import com.jti.event.front.model.common.ImageModel;
import com.jti.event.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ImageService {
    /**
     * 파일 업로드 객체
     */
    @Autowired
    private Upload upload;

    public List<ImageModel> doImageInsertProcess(HttpServletRequest req, HttpServletResponse res, String fileFormName){

        List<ImageModel> listImageFile = null;
        try {
            listImageFile = upload.imageContentUpload(req, fileFormName);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return listImageFile;
    }
}
