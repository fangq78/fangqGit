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

import com.luysoft.wlfedu.constants.SessionKey;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.Depot;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.dto.Workshop;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DepotService;
import com.luysoft.wlfedu.service.ManagerUserService;
import com.luysoft.wlfedu.service.TeamService;
import com.luysoft.wlfedu.service.TeamUserService;
import com.luysoft.wlfedu.service.WorkshopService;

/**
 * 车间
 */
@Controller
@RequestMapping(value = "/admin/workshop")
public class WorkshopController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(WorkshopController.class);

	@Resource
	private DepotService depotService;
	
	@Resource
	private WorkshopService workshopService;

	@Resource
	private TeamService teamService;
	
	@Resource
	private ManagerUserService managerUserService;
	
	@Resource
	TeamUserService teamUserService;

	/**
	 * 车间列表。
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView mv, HttpServletRequest request) {
		try {
			Workshop cond = new Workshop();
			Integer depotId = null;
			if (request.getParameter("depotId")!=null) {
				depotId=Integer.valueOf(request.getParameter("depotId"));
				mv.addObject("depotId", depotId);
			}
			cond.setDepotId(depotId);
			// 查询段列表
			List<Workshop> workshops = workshopService.list(cond);
			mv.addObject("workshops", workshops);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			logger.info("workshops count: " + workshops.size());
			
			mv.setViewName("/admin/workshop/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增车间页面。
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(ModelAndView mv) {
		try {
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("/admin/workshop/add");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增车间
	 */
	@RequestMapping(value = "/doAdd")
	public ModelAndView doAdd(Workshop workshop, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 新增车间
			workshopService.add(workshop, user.getId());
			// 查询段列表
			Workshop cond = new Workshop();
			List<Workshop> workshops = workshopService.list(cond);
			mv.addObject("workshops", workshops);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("redirect:/admin/workshop/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 删除车间
	 * 
	 * @param id 车间Id
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id")Integer id) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {

			//删除班组人员
			teamUserService.deleteByWorkshopId(id);
			//删除车间管理员
			managerUserService.deleteByWorkshopId(id);
			//删除班组
			teamService.deleteByWorkshopId(id);
			// 删除车间
			workshopService.delete(id);
			
			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 修改车间页面。
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id")Integer id, ModelAndView mv) {
		try {
			// 查询车间
			Workshop workshop = workshopService.select(id);
			mv.addObject("workshop", workshop);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("/admin/workshop/edit");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改车间
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(Workshop workshop, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 修改车间
			workshopService.edit(workshop, user.getId());

			// 查询车间列表
			Workshop cond = new Workshop();
			List<Workshop> workshops = workshopService.list(cond);
			mv.addObject("workshops", workshops);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			mv.setViewName("redirect:/admin/workshop/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
}
