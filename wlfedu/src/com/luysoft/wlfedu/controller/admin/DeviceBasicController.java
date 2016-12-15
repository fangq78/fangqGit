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

import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.DeviceBasic;
import com.luysoft.wlfedu.dto.DeviceType;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DeviceBasicService;
import com.luysoft.wlfedu.service.DeviceTypeService;

/**
 * 工具基本信息
 */
@Controller
@RequestMapping(value = "/admin/devicebasic")
public class DeviceBasicController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(DeviceBasicController.class);

	@Resource
	private DeviceBasicService deviceBasicService;
	
	@Resource
	private DeviceTypeService deviceTypeService;

	/**
	 * 工具基本信息列表。
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(DeviceBasic cond,ModelAndView mv) {
		try {
			mv.addObject("cond", cond);
			
			// 查询工具基本信息列表
			List<DeviceBasic> basics = deviceBasicService.list(cond);
			mv.addObject("basics", basics);
			logger.info("devicebasics count: " + basics.size());
			
			mv.setViewName("/admin/devicebasic/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增工具基本信息页面。
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(ModelAndView mv) {
		try {
			DeviceType cond = new DeviceType();
			List<DeviceType> types = deviceTypeService.list(cond,null);
			mv.addObject("types", types);
			mv.setViewName("/admin/devicebasic/add");
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增工具基本信息
	 */
	@RequestMapping(value = "/doAdd")
	public ModelAndView doAdd(DeviceBasic basic, HttpSession session, ModelAndView mv) {
		try {
			// 新增工具基本信息
			deviceBasicService.add(basic);
			// 查询段列表
			DeviceBasic cond = new DeviceBasic();
			List<DeviceBasic> basics = deviceBasicService.list(cond);
			mv.addObject("basics", basics);
			mv.setViewName("redirect:/admin/devicebasic/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 删除工具基本信息
	 * 
	 * @param id 工具基本信息Id
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id")Integer id) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 删除工具基本信息
			deviceBasicService.delete(id);

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 修改工具基本信息页面。
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id")Integer id, ModelAndView mv) {
		try {
			// 查询工具基本信息
			DeviceType cond = new DeviceType();
			List<DeviceType> types = deviceTypeService.list(cond,null);
			mv.addObject("types", types);
			
			DeviceBasic basic = deviceBasicService.select(id);
			mv.addObject("basic", basic);
			mv.setViewName("/admin/devicebasic/edit");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改工具基本信息
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(DeviceBasic basic, HttpSession session, ModelAndView mv) {
		try {
			
			// 修改工具基本信息
			deviceBasicService.edit(basic);
			DeviceType tcond = new DeviceType();
			List<DeviceType> types = deviceTypeService.list(tcond,null);
			mv.addObject("types", types);
			
			// 查询工具基本信息列表
			DeviceBasic cond = new DeviceBasic();
			List<DeviceBasic> basics = deviceBasicService.list(cond);
			mv.addObject("basic", basics);
			
			mv.setViewName("redirect:/admin/devicebasic/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
}
