package com.jti.event.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component("DownloadView")
public class DownloadView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        File file = (File)model.get("downloadFile");
        String fileName = (String)model.get("downloadFileName");
        if(file != null) {
            String userAgent = request.getHeader("User-Agent");

            if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1){
                fileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");;
            }else {
                fileName = new String(fileName.getBytes("utf-8"));
            }
            response.setContentType(getContentType());
            response.setContentLength((int)file.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            OutputStream out = response.getOutputStream();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                if(fis != null){
                    try{
                        fis.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                if(out != null) {
                    out.flush();
                }
            }

        }
    }
}
