package com.luysoft.wlfedu.controller.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.luysoft.wlfedu.message.Messages;

public class BaseController {

	/**
	 * 根据消息ID获取消息内容.
	 * 
	 * @param msgId 消息ID
	 * @return 消息内容
	 */
	protected String getMessage(String msgId) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();

		RequestContext context = new RequestContext(request);
		return context.getMessage(msgId);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param msgId 消息ID
	 */
	protected void setResponseError(ModelAndView mv, String msgId) {
		mv.addObject("error", getMessage(msgId));
	}

	/**
	 * 跳转到错误页面
	 * 
	 */
	protected void gotoErrorPage(ModelAndView mv) {
		gotoErrorPage(mv, Messages.E_INTERNAL);
	}

	/**
	 * 跳转到错误页面
	 * 
	 * @param msgId 消息ID
	 */
	protected void gotoErrorPage(ModelAndView mv, String msgId) {
		mv.addObject("error", getMessage(msgId));
		mv.setViewName("/common/error");
	}

	/**
	 * 下载文件
	 * 
	 * @param filePath 下载文件资源路径
	 * @param fileName 下载文件表示名
	 * 
	 * @throws IOException
	 */
	protected void downloadFile(String filePath, String fileName, HttpServletResponse response) throws IOException {

		File file = new File(filePath);
		
		response.reset();
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setContentLength((int) file.length());

		byte buf[] = new byte[1024];
		int count;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = response.getOutputStream();
		while ((count = in.read(buf, 0, 1024)) != -1) {
			out.write(buf, 0, count);
		}
		in.close();
		out.close();
	}

	/**
	 * 获取客户端IP地址.<br>
	 * 支持多级反向代理
	 * 
	 * @return 客户端真实IP地址
	 */
	protected String getRemoteAddr() {

		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();

		String remoteAddr = request.getHeader("X-Forwarded-For");

		// 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
		if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
			String[] array = remoteAddr.split(",");
			for (String element : array) {
				if (isEffective(element)) {
					remoteAddr = element;
					break;
				}
			}
		}
		if (!isEffective(remoteAddr)) {
			remoteAddr = request.getHeader("X-Real-IP");
		}
		if (!isEffective(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		}
		return remoteAddr.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : remoteAddr;
	}
	
	/**
	 * 获取客户端源端口
	 * 
	 */
	protected Long getRemotePort(){

		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		
		String port = request.getHeader("remote-port");
		if( StringUtils.isEmpty(port)) {
			try{
				return Long.parseLong(port);
			}catch(NumberFormatException ex){
				return 0l;
			}
		}else{
			return 0l;
		}
	}

	/**
	 * 远程地址是否有效.
	 * 
	 * @param remoteAddr
	 *            远程地址
	 * @return true代表远程地址有效，false代表远程地址无效
	 */
	private boolean isEffective(final String remoteAddr) {
		boolean isEffective = false;
		if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
				&& (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
			isEffective = true;
		}
		return isEffective;
	}
}
