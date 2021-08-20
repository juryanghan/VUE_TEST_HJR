package com.jti.event.util;

import com.jti.event.common.model.FileModel;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Component("fileUtils")
public class FileUtils {

	@Value("${upload.path}")
	private String uploadPath;

	public FileModel parseInsertFileInfo(HttpServletRequest request) throws Exception {
		FileModel fileModel = new FileModel();
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String folderName = transFormat.format(from);
		
		String realUploadPath = uploadPath + "/product/" + folderName + "/";
		String uploadurl = "/upload/product/" + folderName + "/";
		
		File file = new File(realUploadPath);
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtil.getRandomString() + originalFileExtension;
				file = new File(realUploadPath + storedFileName);
				multipartFile.transferTo(file);

				fileModel.setFilename(storedFileName);
				fileModel.setOriFilename(originalFileName);
				fileModel.setFileSize(multipartFile.getSize());
				fileModel.setFileUrl(uploadurl + storedFileName);
			}
		}
		return fileModel;
	}

	public FileModel SetContent(HttpServletRequest request) throws Exception {
		FileModel fileModel = new FileModel();
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHH");
		String folderName = transFormat.format(from);

		String realUploadPath = uploadPath + "/productDetail/" + folderName + "/";
		String uploadurl = "/upload/productDetail/" + folderName + "/";

		File file = new File(realUploadPath);
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtil.getRandomString() + originalFileExtension;
				if (originalFileExtension.equals(".html") || originalFileExtension.equals(".htm")) {
					if (multipartFile.getSize() > 0) {
						InputStream inputStream = multipartFile.getInputStream();
						byte[] bytes = IOUtils.toByteArray(inputStream);
						String htmlContent = new String(bytes, "utf-8");

						Document doc = Jsoup.parse(htmlContent);
						Element body = doc.body();
						htmlContent = body.html();
						fileModel.setHtmlContent(htmlContent);
					}
				} else {
					file = new File(realUploadPath + storedFileName);
					multipartFile.transferTo(file);
					fileModel.setFileUrl(uploadurl + storedFileName);
				}
				
				fileModel.setFilename(originalFileName);
				fileModel.setOriFilename(originalFileName);
				fileModel.setFileSize(multipartFile.getSize());
				
			}
		}
		return fileModel;
	}
}
