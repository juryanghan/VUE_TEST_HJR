package com.jti.event.admin.control;

import com.jti.event.common.model.FileModel;
import com.jti.event.util.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("adm/file")
public class FileController {

	private final Log log = LogFactory.getLog(FileController.class);

	@Value("${upload.path}")
	private String uploadPath;
	
	@Resource(name="fileUtils") 
	private FileUtils fileUtils;

	private static final String FILE_SERVER_PATH = "C:/test";


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

	@RequestMapping("/download")
	public ModelAndView download(@RequestParam HashMap<Object, Object> params, ModelAndView mv) {
		String fileName = (String) params.get("fileName");
		String fullPath = FILE_SERVER_PATH + "/" + fileName;
		File file = new File(fullPath);

		mv.setViewName("downloadView");
		mv.addObject("downloadFile", file);
		return mv;
	}


	

}