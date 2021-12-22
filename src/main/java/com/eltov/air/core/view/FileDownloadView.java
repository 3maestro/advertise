package com.eltov.air.core.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentDisposition;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.eltov.air.core.code.ExceptionCode;


@Component("FileDownloadView")
public class FileDownloadView extends AbstractView {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadView.class);

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String filePath = (String)model.getOrDefault("file", "");
		String fileName = (String)model.getOrDefault("fileName", "");
		
		File file = new File(filePath);
		
		if(!file.isFile() || fileName.equals("")) {
			logger.error("ERR_CODE: {} ===> Message: {} ", ExceptionCode.E204_FILE_NOT_FOUND ,file.toString());
			throw new Exception("파일 없음");
		}
		
		response.setContentLength((int)file.length());
		response.setContentType("application/octet-stream; charset=UTF-8");
		
		ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(fileName, StandardCharsets.UTF_8).build();
		
		response.setHeader("Content-Disposition", contentDisposition.toString());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pargma", "no-cache");
		response.setHeader("Expires", "-1");
		
		try(
			OutputStream outResource = response.getOutputStream();
			FileInputStream fis = new FileInputStream(file)
		) {
			FileCopyUtils.copy(fis, outResource);
			outResource.flush();
		}catch(Exception e) {
			logger.error("ERROR : {} ", e);
		}
	}
	
	
}
