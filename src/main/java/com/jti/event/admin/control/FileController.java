package com.jti.event.admin.control;

import com.jti.event.common.model.FileModel;
import com.jti.event.util.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("adm/file")
public class FileController {

	private final Log log = LogFactory.getLog(FileController.class);

	@Value("${upload.path}")
	private String uploadPath;
	
	@Resource(name="fileUtils") 
	private FileUtils fileUtils;


	/*
	 * 파일 업로드
	 * 
	 * @param req
	 * @param res
	 * @return FileModel
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/images")
	public FileModel images(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		log.debug("/adm/file/images");
		FileModel Result = fileUtils.parseInsertFileInfo(req);
		return Result;
	}
	
	/*
	 * 파일 업로드
	 * 
	 * @param req
	 * @param res
	 * @return FileModel
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/content")
	public FileModel content(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		log.debug("/adm/file/content");
		FileModel Result = fileUtils.SetContent(req);
		return Result;
	}
	
	
	

}