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

import com.luysoft.wlfedu.constants.SessionKey;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.Depot;
import com.luysoft.wlfedu.dto.ManagerUser;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DepotService;
import com.luysoft.wlfedu.service.ManagerUserService;
import com.luysoft.wlfedu.service.TeamService;
import com.luysoft.wlfedu.service.TeamUserService;
import com.luysoft.wlfedu.service.WorkshopService;

/**
 * 段
 */
@Controller
@RequestMapping(value = "/admin/depot")
public class DepotController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(DepotController.class);

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
	 * 段列表。
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView mv) {
		try {
			// 查询段列表
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			
			logger.info("depots count: " + depots.size());
			
			mv.setViewName("/admin/depot/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增段页面。
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(ModelAndView mv) {
		try {

			mv.setViewName("/admin/depot/add");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增段
	 */
	@RequestMapping(value = "/doAdd")
	public ModelAndView doAdd(Depot depot, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 新增段
			depotService.add(depot, user.getId());

			// 查询段列表
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);

			mv.setViewName("redirect:/admin/depot/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 删除段
	 * 
	 * @param id 段Id
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id")Integer id) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			//删除班组人员
			teamUserService.deleteByDepotId(id);
			//删除车间管理员
			managerUserService.deleteByDepotId(id);
			//删除班组
			teamService.deleteByDepotId(id);
			//删除车间
			workshopService.deleteByDepotId(id);
			// 删除段
			depotService.delete(id);
						
			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 修改段页面。
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id")Integer id, ModelAndView mv) {
		try {
			// 查询段
			Depot depot = depotService.select(id);
			mv.addObject("depot", depot);

			mv.setViewName("/admin/depot/edit");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改段
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(Depot depot, HttpSession session, ModelAndView mv) {
		try {
			// 登录系统用户
			ManagerUser user = (ManagerUser) session.getAttribute(SessionKey.SYSTEM_USER);
			
			// 修改段
			depotService.edit(depot, user.getId());

			// 查询段列表
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);

			mv.setViewName("redirect:/admin/depot/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
}
