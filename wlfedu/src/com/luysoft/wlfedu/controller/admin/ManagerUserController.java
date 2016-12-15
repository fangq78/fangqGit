package com.luysoft.wlfedu.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luysoft.framework.crypto.MD5;
import com.luysoft.wlfedu.constants.SessionKey;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.Depot;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.dto.Workshop;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DepotService;
import com.luysoft.wlfedu.service.ManagerUserService;
import com.luysoft.wlfedu.service.WorkshopService;

/**
 * 管理员
 */
@Controller
@RequestMapping(value = "/admin/manageruser")
public class ManagerUserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(ManagerUserController.class);

	@Resource
	private ManagerUserService managerUserService;
	@Resource
	private DepotService depotService;
	@Resource
	private WorkshopService workshopService;

	/**
	 * 管理员列表
	 * 
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(ManagerUser cond, ModelAndView mv) {
		try {
			// 查询条件
			if (cond.getType()==null) {
				cond.setType(-1);
			}
			mv.addObject("cond", cond);
			if (cond.getType()>0) {
				List<Depot> depots = depotService.list();
				mv.addObject("depots", depots);
				if (cond.getDepotId()!=null && cond.getDepotId()>0) {
					Workshop wcond = new Workshop();
					wcond.setDepotId(cond.getDepotId());
					List<Workshop> workshops = workshopService.list(wcond);
					mv.addObject("workshops", workshops);
				}
			}
			
			// 学员列表
			List<ManagerUser> users = managerUserService.list(cond);
			mv.addObject("users", users);

			mv.setViewName("/admin/manageruser/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 删除系统用户
	 * 
	 * @param id 系统用户Id
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id")Integer id, HttpSession session) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);

			if (user.getId() == id) {
				//不能删除当前系统用户
				ret.put("status", true);
				ret.put("duplicate", true);
				return ret;
			}
			// 删除系统用户
			managerUserService.delete(id, user.getId());

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 新增系统用户页面。
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(ModelAndView mv) {
		try {
			ManagerUser user = new ManagerUser();
			mv.addObject("user", user);
			user.setType(0);
			mv.setViewName("/admin/manageruser/add");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 新增系统用户
	 */
	@RequestMapping(value = "/doAdd")
	public ModelAndView doAdd(ManagerUser user, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser loginUser = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 新增系统用户
			user.setPassword(MD5.digest(user.getPassword()));
			int result = managerUserService.add(user, loginUser.getId());
			if (1 == result) {
				setResponseError(mv, Messages.E_ADMIN_SYSTEMUSER_NAME_DUPLICATE);
				mv.addObject("user", user);
				mv.setViewName("/admin/manageruser/add");
				return mv;
			}

			return new ModelAndView("redirect:/admin/manageruser/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改系统用户页面。
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id")Integer id, ModelAndView mv) {
		try {
			// 查询礼品
			ManagerUser user = managerUserService.select(id);
			mv.addObject("user", user);
			if (user.getType()>0) {
				List<Depot> depots = depotService.list();
				mv.addObject("depots", depots);
				if (user.getDepotId()>0) {
					Workshop cond = new Workshop();
					cond.setDepotId(user.getDepotId());
					List<Workshop> workshops = workshopService.list(cond);
					mv.addObject("workshops", workshops);
				}
			}
			mv.setViewName("/admin/manageruser/edit");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改系统用户
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(ManagerUser user, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser loginUser = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 修改课程
			managerUserService.edit(user, loginUser.getId());

			return new ModelAndView("redirect:/admin/manageruser/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * ajax检查用户名是否重复
	 * 
	 */
	@RequestMapping("/ajaxCheckName")
	@ResponseBody
	public Map<String, Object> ajaxCheckName(String name) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			int has = managerUserService.hasDuplicateName(name);
			ret.put("status", true);
			ret.put("has", has);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	@RequestMapping("/password")
	@ResponseBody
	public Map<String, Object> password(Integer id, String password, HttpSession session) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);

			// 删除系统用户
			managerUserService.password(id, MD5.digest(password), user.getId());

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
}
