package com.luysoft.wlfedu.controller.admin;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.luysoft.framework.crypto.MD5;
import com.luysoft.wlfedu.constants.CookiesKey;
import com.luysoft.wlfedu.constants.SessionKey;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.ManagerUserService;

/**
 * 系统用户登录
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource
	private ManagerUserService ManagerUserService;
	

	/**
	 * 系统管理员用户登录页面。
	 */
	@RequestMapping(value = "/login/system")
	public String system() {
		return "admin/login/system";
	}

	/**
	 * 社区管理员登录页面。
	 */
//	@RequestMapping(value = "/login/community")
//	public ModelAndView community(ModelAndView mv) {
//		try {
//			// 查询社区列表
//			List<Community> communityList = communityService.search(null, null, null);
//			mv.addObject("communityList", communityList);
//
//			mv.setViewName("admin/login/community");
//
//		} catch (Exception e) {
//			logger.error(getMessage(Messages.E_INTERNAL), e);
//			gotoErrorPage(mv);
//		}
//		return mv;
//	}

	/**
	 * 系统用户登录处理
	 * 
	 * @param name 用户名
	 * @param password 登录密码
	 */
	@RequestMapping(value = "/login/doSystem")
	public ModelAndView doSystem(String name, String password, 
			HttpServletRequest request, ModelAndView mv, HttpServletResponse response, HttpSession session) {

		try {
			// 查询系统用户
			ManagerUser user = ManagerUserService.selectManagerUser(name, MD5.digest(password));
			if (null != user) {

				// 保存用户信息到Session
				session.setAttribute(SessionKey.SYSTEM_USER, user);

				// 保存系统用户类型到Cookie
				Cookie cookie = new Cookie(CookiesKey.SYSTEM_MANAGER, "1");
				cookie.setPath(CookiesKey.PATH);
				response.addCookie(cookie);

				// 跳转页面
				mv.setViewName("/admin/system_main");

			} else {
				
				// 登录失败
				setResponseError(mv, Messages.E_ADMIN_LOGIN_USER_NOT_EXISTS);

				mv.addObject("name", name);	// 用户名
				mv.setViewName("/admin/login/system");
			}
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 社区管理用户登录处理
	 * 
	 * @param community 社区id
	 * @param name 用户名
	 * @param password 登录密码
	 */
//	@RequestMapping(value = "/login/doCommunity")
//	public ModelAndView doCommunity(Integer community, String name, String password, 
//			HttpServletRequest request, ModelAndView mv, HttpServletResponse response, HttpSession session) {
//
//		try {
//			// 查询系统用户
//			SystemUser user = ManagerUserService.selectCommunityUser(community, name, MD5.digest(password));
//			if (null != user) {
//
//				// 保存用户信息到Session
//				session.setAttribute(SessionKey.SYSTEM_USER, user);
//
//				// 保存系统用户类型到Cookie
//				Cookie cookie = new Cookie(CookiesKey.SYSTEM_MANAGER, "0");
//				cookie.setPath(CookiesKey.PATH);
//				response.addCookie(cookie);
//
//				// 跳转页面
//				mv.setViewName("/admin/community_main");
//
//			} else {
//				
//				// 登录失败
//				setResponseError(mv, Messages.E_ADMIN_LOGIN_USER_NOT_EXISTS);
//				
//				List<Community> communityList = new ArrayList<Community>();
//				communityList = communityService.search(null, null, null);
//				mv.addObject("communityList", communityList);
//				mv.addObject("name", name);	// 用户名
//				mv.addObject("communityId", community);	// 社区ID
//				mv.setViewName("/admin/login/community");
//			}
//		} catch (Exception e) {
//			logger.error(getMessage(Messages.E_INTERNAL), e);
//			gotoErrorPage(mv);
//		}
//		return mv;
//	}
	
	@RequestMapping(value = "/signout")
	public ModelAndView signout(HttpServletRequest request, ModelAndView mv, HttpSession session) {

		try {
			// 删除Session内容
			session.removeAttribute(SessionKey.SYSTEM_USER);

			String type = "0";	// 系统管理员标志(1:系统管理员  1以外:社区管理员)
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (CookiesKey.SYSTEM_MANAGER.equals(cookie.getName())) {
					type = cookie.getValue();
				}
			}

			if ("0".equals(type)) {
				// 社区管理登录
				mv.setViewName("redirect:/admin/login/community");
			} else {
				// 系统管理登录
				mv.setViewName("redirect:/admin/login/system");
			}

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	} 
}
