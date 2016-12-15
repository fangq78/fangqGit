package com.luysoft.wlfedu.controller.admin;

import java.util.ArrayList;
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

import com.luysoft.wlfedu.constants.SessionKey;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.Depot;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.dto.Team;
import com.luysoft.wlfedu.dto.Workshop;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DepotService;
import com.luysoft.wlfedu.service.TeamService;
import com.luysoft.wlfedu.service.TeamUserService;
import com.luysoft.wlfedu.service.WorkshopService;

/**
 * 班组
 */
@Controller
@RequestMapping(value = "/admin/team")
public class TeamController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Resource
	private TeamService teamService;
	@Resource
	private WorkshopService workshopService;
	@Resource
	private DepotService depotService;
	@Resource
	private TeamUserService teamUserService;

	/**
	 * 班组列表。
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView mv, HttpServletRequest request) {
		try {
			Team cond = new Team();
			Integer workshopId = null;
			Integer depotId = null;
			if (request.getParameter("depotId")!=null) {
				depotId=Integer.valueOf(request.getParameter("depotId"));
				mv.addObject("depotId", depotId);
				cond.setDepotId(depotId);
				if (request.getParameter("workshopId")!=null) {
					workshopId=Integer.valueOf(request.getParameter("workshopId"));
					mv.addObject("workshopId", workshopId);
					cond.setWorkshopId(workshopId);
					Workshop wcond = new Workshop();
					wcond.setDepotId(depotId);
					List<Workshop> workshops = workshopService.list(wcond);
					mv.addObject("workshops", workshops);
				}
			}
			// 查询班组列表
			List<Team> teams = teamService.list(cond);
			mv.addObject("teams", teams);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			logger.info("teams count: " + teams.size());
			
			mv.setViewName("/admin/team/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增班组页面。
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(ModelAndView mv) {
		try {
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("/admin/team/add");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增班组
	 */
	@RequestMapping(value = "/doAdd")
	public ModelAndView doAdd(Team team, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 新增班组
			teamService.add(team, user.getId());
			// 查询车间列表
			List<Team> teams = teamService.list(new Team());
			mv.addObject("teams", teams);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("redirect:/admin/team/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * ajax取得班组列表
	 * 
	 * @param id 段Id
	 */
	@RequestMapping("/ajaxTeams")
	@ResponseBody
	public Map<String, Object> ajaxTeams(Integer workshopId) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 取得取得车间列表
			List<Team> teams = null;
			if (workshopId!=null && workshopId>0) {
				Team cond = new Team();
				cond.setWorkshopId(workshopId);
			    teams = teamService.list(cond);
			} else {
				teams = new ArrayList<Team>();
			}
			ret.put("status", true);
			ret.put("teams", teams);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	/**
	 * ajax取得车间列表
	 * 
	 * @param id 段Id
	 */
	@RequestMapping("/ajaxWorkshops")
	@ResponseBody
	public Map<String, Object> ajaxWorkshops(Integer depotId) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 取得取得车间列表
			List<Workshop> workshops = null;
			if (depotId!=null && depotId>0) {
				Workshop cond = new Workshop();
				cond.setDepotId(depotId);
			    workshops = workshopService.list(cond);
			} else {
				workshops = new ArrayList<Workshop>();
			}
			ret.put("status", true);
			ret.put("workshops", workshops);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	/**
	 * ajax取得段列表
	 * 
	 */
	@RequestMapping("/ajaxDepots")
	@ResponseBody
	public Map<String, Object> ajaxDepots() {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 取得取得车间列表
			List<Depot> depots = depotService.list();
			ret.put("status", true);
			ret.put("depots", depots);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	/**
	 * 删除班组
	 * 
	 * @param id 班组Id
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id")Integer id) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			//删除班组人员
			teamUserService.deleteByTeamId(id);
			// 删除班组
			teamService.delete(id);

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 修改班组页面。
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id")Integer id, ModelAndView mv) {
		try {
			// 查询班组
			Team team = teamService.select(id);
			mv.addObject("team", team);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			Workshop wcond = new Workshop();
			if (team.getDepotId()!=null) {
				wcond.setDepotId(team.getDepotId());
				List<Workshop> workshops = workshopService.list(wcond);
				mv.addObject("workshops", workshops);
			}
			mv.setViewName("/admin/team/edit");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改班组
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(Team team, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 修改班组
			teamService.edit(team, user.getId());

			// 查询班组列表
			List<Team> teams = teamService.list(new Team());
			mv.addObject("teams", teams);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("redirect:/admin/team/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
}
