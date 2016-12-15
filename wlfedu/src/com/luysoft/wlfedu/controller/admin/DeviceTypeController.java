package com.luysoft.wlfedu.controller.admin;

import java.util.ArrayList;
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

import com.luysoft.framework.init.cacheDataCenter;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.DeviceType;
import com.luysoft.wlfedu.dto.Team;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DeviceBasicService;
import com.luysoft.wlfedu.service.DeviceTypeService;

/**
 * 物资编码
 */
@Controller
@RequestMapping(value = "/admin/devicetype")
public class DeviceTypeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(DeviceTypeController.class);

	@Resource
	private DeviceTypeService deviceTypeService;
	
	@Resource
	private DeviceBasicService deviceBasicService;

	/**
	 * ajax取得物资编码
	 * 
	 * @param code 物资编码
	 */
	@RequestMapping("/ajaxDeviceTypes")
	@ResponseBody
	public Map<String, Object> ajaxDeviceTypes(String code,Integer limit) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 取得取得物资编码列表
			List<DeviceType> deviceTypes = null;
			if (code!=null) {
				DeviceType cond = new DeviceType();
				cond.setCode(code);
				deviceTypes = deviceTypeService.list(cond,limit);
			} else {
				deviceTypes = new ArrayList<DeviceType>();
			}
			ret.put("status", true);
			ret.put("deviceTypes", deviceTypes);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	/**
	 * 物资编码列表。
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(DeviceType cond,ModelAndView mv) {
		try {
			// 查询物资编码列表
			List<DeviceType> deviceTypes = deviceTypeService.list(cond,null);
//			List<DeviceType> deviceTypes = cacheDataCenter.DeviceTypeToList();
			mv.addObject("deviceTypes", deviceTypes);
			mv.addObject("cond", cond);
			
			logger.info("deviceTypes count: " + deviceTypes.size());
			
			mv.setViewName("/admin/devicetype/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增物资编码页面。
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(ModelAndView mv) {
		try {

			mv.setViewName("/admin/devicetype/add");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增物资编码
	 */
	@RequestMapping(value = "/doAdd")
	public ModelAndView doAdd(DeviceType deviceType, HttpSession session, ModelAndView mv) {
		try {
			
			// 新增物资编码
			deviceTypeService.add(deviceType);
			cacheDataCenter.putDeviceTypeMapData(deviceType);

			// 查询物资编码列表
//			List<DeviceType> devicetypes = deviceTypeService.list(deviceType);
			List<DeviceType> devicetypes = cacheDataCenter.DeviceTypeToList();
			mv.addObject("devicetypes", devicetypes);

			mv.setViewName("redirect:/admin/devicetype/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 删除物资编码
	 * 
	 * @param id 物资编码Id
	 */
	@RequestMapping("/delete/{code}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("code")String code) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 删除基本信息
			deviceBasicService.deleteByCode(code);
			// 删除物资编码
			deviceTypeService.delete(code);
			cacheDataCenter.removeDeviceType(code);
			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 修改物资编码页面。
	 */
	@RequestMapping(value = "/edit/{code}")
	public ModelAndView edit(@PathVariable("code")String code, ModelAndView mv) {
		try {
			// 查询物资编码
//			DeviceType deviceType = deviceTypeService.select(code);
			DeviceType deviceType = cacheDataCenter.getDeviceType(code);
			mv.addObject("deviceType", deviceType);

			mv.setViewName("/admin/devicetype/edit");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改物资编码
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(DeviceType deviceType, HttpSession session, ModelAndView mv) {
		try {
			
			// 修改物资编码
			deviceTypeService.edit(deviceType);
			cacheDataCenter.putDeviceTypeMapData(deviceType);
			mv.setViewName("redirect:/admin/devicetype/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * ajax检查物资编码是否重复
	 * 
	 */
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public Map<String, Object> ajaxCheckCode(String code) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
//			DeviceType type = deviceTypeService.select(code);
			DeviceType type = cacheDataCenter.getDeviceType(code);
			ret.put("status", true);
			ret.put("type", type);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
}
