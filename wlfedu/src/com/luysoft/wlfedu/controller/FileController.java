package com.luysoft.wlfedu.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luysoft.wlfedu.config.PathConfig;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.message.Messages;

/**
 * 文件共通处理
 */
@Controller
@RequestMapping(value = "/file")
public class FileController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Resource
	private PathConfig pathConfig;

	@RequestMapping(value = "/temp")
	@ResponseBody
	public Map<String, Object> temp(@RequestParam MultipartFile[] files,
			@RequestParam Map<String, Object> params) {

		JSONArray jsonfiles = new JSONArray();
		Map<String, Object> ret = new HashMap<String, Object>();

		try {
			// 取得临时目录，不存在时作成。
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String tempPath = sdf.format(new Date());
			
			String tempRoot = pathConfig.getTempPath();
			File dic = new File(tempRoot + tempPath + "/");
			if (!dic.exists()) {
				dic.mkdirs();
			}

			// 将文件上传到临时目录下
			for (MultipartFile mpf : files ) {

				// 保存临时文件
				String filePath = tempPath + "/" + UUID.randomUUID().toString();
				mpf.transferTo(new File(tempRoot + filePath));

				JSONObject file = new JSONObject();
				file.put("path", filePath);
				file.put("name", mpf.getOriginalFilename());
				file.put("size", mpf.getSize());
				file.put("url", pathConfig.getTempUrl() + filePath);
				jsonfiles.add(file);
			}

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			
			JSONObject file = new JSONObject();
			file.put("error", getMessage(Messages.E_INTERNAL));
			jsonfiles.add(file);
		}

		ret.put("files", jsonfiles);
		return ret;
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request, @RequestParam MultipartFile[] files) {

		JSONArray jsonfiles = new JSONArray();
		Map<String, Object> ret = new HashMap<String, Object>();

		try {
			String uploadRoot = pathConfig.getUploadPath();

			// 将文件上传到临时目录下
			for (MultipartFile mpf : files ) {

				// 保存临时文件
				String fileName = UUID.randomUUID().toString();
				mpf.transferTo(new File(uploadRoot + fileName));

				JSONObject file = new JSONObject();
				
				file.put("url", request.getServletContext().getContextPath()
									+ pathConfig.getUploadUrl() + fileName);
				jsonfiles.add(file);
			}

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			
			JSONObject file = new JSONObject();
			file.put("error", getMessage(Messages.E_INTERNAL));
			jsonfiles.add(file);
		}

		ret.put("files", jsonfiles);
		return ret;
	}
}
