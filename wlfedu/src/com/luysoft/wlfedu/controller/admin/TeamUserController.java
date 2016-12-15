package com.luysoft.wlfedu.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.luysoft.wlfedu.dto.Team;
import com.luysoft.wlfedu.dto.TeamUser;
import com.luysoft.wlfedu.dto.Workshop;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DepotService;
import com.luysoft.wlfedu.service.TeamService;
import com.luysoft.wlfedu.service.TeamUserService;
import com.luysoft.wlfedu.service.WorkshopService;

/**
 * 班组管理员
 */
@Controller
@RequestMapping(value = "/admin/teamuser")
public class TeamUserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(TeamUserController.class);

	@Resource
	private TeamUserService teamUserService;
	@Resource
	private DepotService depotService;
	@Resource
	private WorkshopService workshopService;
	
	@Resource
	private TeamService teamService;

	/**
	 * 管理员列表
	 * 
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(TeamUser cond, ModelAndView mv) {
		try {
			
			mv.addObject("cond", cond);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			if (cond.getDepotId()!=null && cond.getDepotId()>0) {
				Workshop wcond = new Workshop();
				wcond.setDepotId(cond.getDepotId());
				List<Workshop> workshops = workshopService.list(wcond);
				mv.addObject("workshops", workshops);
				if (cond.getWorkshopId()!=null && cond.getWorkshopId()>0) {
					Team tcond = new Team();
					tcond.setWorkshopId(cond.getWorkshopId());
					List<Team> teams = teamService.list(tcond);
					mv.addObject("teams", teams);
				}
			}
			
			// 学员列表
			List<TeamUser> users = teamUserService.list(cond);
			mv.addObject("users", users);

			mv.setViewName("/admin/teamuser/list");

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

			// 删除系统用户
			teamUserService.delete(id, user.getId());

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
			TeamUser user = new TeamUser();
			mv.addObject("user", user);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("/admin/teamuser/add");

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
	public ModelAndView doAdd(TeamUser user, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser loginUser = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 新增系统用户
			user.setPassword(MD5.digest(user.getPassword()));
			int result = teamUserService.add(user, loginUser.getId());
			if (1 == result) {
				setResponseError(mv, Messages.E_ADMIN_SYSTEMUSER_NAME_DUPLICATE);
				mv.addObject("user", user);
				mv.setViewName("/admin/teamuser/add");
				return mv;
			}

			return new ModelAndView("redirect:/admin/teamuser/list");

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
			// 查询用户
			TeamUser user = teamUserService.select(id);
			mv.addObject("user", user);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			Workshop wcond = new Workshop();
			wcond.setDepotId(user.getDepotId());
			List<Workshop> workshops = workshopService.list(wcond);
			mv.addObject("workshops", workshops);
			Team tcond = new Team();
			tcond.setWorkshopId(user.getWorkshopId());
			List<Team> teams = teamService.list(tcond);
			mv.addObject("teams", teams);
			mv.setViewName("/admin/teamuser/edit");
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
	public ModelAndView doEdit(TeamUser user, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser loginUser = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 修改课程
			teamUserService.edit(user, loginUser.getId());

			return new ModelAndView("redirect:/admin/teamuser/list");

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
			int has = teamUserService.hasDuplicateName(name);
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
			teamUserService.password(id, MD5.digest(password), user.getId());

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	/**
	 * ajax取得班组用户列表
	 * 
	 */
	@RequestMapping("/ajaxTeamUsers")
	@ResponseBody
	public Map<String, Object> ajaxTeamUsers() {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 取得取得车间列表
			TeamUser cond = new TeamUser();
			List<TeamUser> users = teamUserService.list(cond);
			ret.put("status", "success");
			ret.put("users", users);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", "error");
		}
		return ret;
	}
	
}
