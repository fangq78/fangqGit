package com.luysoft.wlfedu.controller.admin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.luysoft.wlfedu.common.Tools;
import com.luysoft.wlfedu.config.PathConfig;
import com.luysoft.wlfedu.controller.common.BaseController;
import com.luysoft.wlfedu.dto.Depot;
import com.luysoft.wlfedu.dto.Device;
import com.luysoft.wlfedu.dto.DeviceBasic;
import com.luysoft.wlfedu.dto.DeviceUsingDetail;
import com.luysoft.wlfedu.dto.Team;
import com.luysoft.wlfedu.dto.Workshop;
import com.luysoft.wlfedu.message.Messages;
import com.luysoft.wlfedu.service.DepotService;
import com.luysoft.wlfedu.service.DeviceBasicService;
import com.luysoft.wlfedu.service.DeviceService;
import com.luysoft.wlfedu.service.DeviceUsingDetailService;
import com.luysoft.wlfedu.service.TeamService;
import com.luysoft.wlfedu.service.WorkshopService;

/**
 * 工具信息
 */
@Controller
@RequestMapping(value = "/admin/device")
public class DeviceController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Resource
	private DeviceBasicService deviceBasicService;
	
	@Resource
	private DeviceService deviceService;
	
	@Resource
	private DeviceUsingDetailService usingDetailService;
	
	@Resource
	private DepotService depotService;
	
	@Resource
	private WorkshopService workshopService;
	
	@Resource
	private TeamService teamService;
	
	@Resource
	private PathConfig pathConfig;

	/**
	 * ajax取得工具列表
	 * 
	 */
	@RequestMapping("/ajaxDownloadDevices")
	@ResponseBody
	public Map<String, Object> ajaxDownloadDevices() {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 取得设备列表
			Device cond = new Device();
			List<Device> devices = deviceService.list(cond);
			ret.put("status", "ok");
			ret.put("devices", devices);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", "error");
		}
		return ret;
	}
	
	private List<DeviceUsingDetail> json2UsingDeviceDetailList(String jsonString) throws JSONException {
        List<DeviceUsingDetail> details = new ArrayList<DeviceUsingDetail>();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("usingDetails");
        for(int i=0;i<jsonArray.length();i++) {
        	DeviceUsingDetail detail = new DeviceUsingDetail();
            JSONObject jo=jsonArray.getJSONObject(i);
            detail.setId(jo.getInt("id"));
            detail.setDeviceId(jo.getInt("deviceId"));
            detail.setUserId(jo.getInt("userId"));
            detail.setUserName(jo.getString("userName"));
            detail.setUseFlag(jo.getInt("useFlag"));
            detail.setUseDatetime(jo.getString("useDateTime"));
            detail.setPadName(jo.getString("padName"));
            details.add(detail);
        }
        return details;
    }
	private List<Device> json2UsingInfoList(String jsonString) throws JSONException {
        List<Device> usingInfoList = new ArrayList<Device>();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("usingInfo");
        for(int i=0;i<jsonArray.length();i++) {
        	Device info = new Device();
            JSONObject jo=jsonArray.getJSONObject(i);
            info.setId(jo.getInt("id"));
            info.setLastestUserId(jo.getInt("lastestUserId"));
            info.setLastestUserName(jo.getString("lastestUserName"));
            info.setUseFlag(jo.getInt("useFlag"));
            info.setLastestDateTime(jo.getString("lastestDateTime"));
            info.setPadName(jo.getString("padName"));
            usingInfoList.add(info);
        }
        return usingInfoList;
    }
	/**
	 * ajax增加设备使用情报
	 * 
	 */
	@RequestMapping("/ajaxUploadDeviceUsingDetail")
	@ResponseBody
	public Map<String, Object> ajaxUploadDeviceUsingDetail( HttpServletRequest request) {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		String usingInfoJson = request.getParameter("usingInfoJson");
		String detailsJson = request.getParameter("detailsJson");
		try {
			List<Device> usingInfoList = json2UsingInfoList(usingInfoJson);
			for (Device info:usingInfoList) {
				deviceService.updateUsingInfo(info);
			}
			
			List<DeviceUsingDetail> details = json2UsingDeviceDetailList(detailsJson);
			for (DeviceUsingDetail detail : details) {
				usingDetailService.insert(detail);
			}
			ret.put("status", "ok");
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", "error");
		}
		return ret;
	}
	
	/**
	 * ajax取得工具出工详细信息
	 * 
	 */
	@RequestMapping("/ajaxUsingDetails")
	@ResponseBody
	public Map<String, Object> ajaxUsingDetails(HttpServletRequest request) {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			String deviceId = request.getParameter("deviceId");
			Device device = deviceService.select(Integer.valueOf(deviceId));
			// 查询工具信息列表
			DeviceUsingDetail cond = new DeviceUsingDetail();
			cond.setDeviceId(Integer.valueOf(deviceId));
			List<DeviceUsingDetail> details = usingDetailService.search(cond);
			ret.put("status", "ok");
			ret.put("details", details);
			ret.put("device", device);
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", "error");
		}
		return ret;
	}
	/**
	 * 工具信息列表。
	 */
	@RequestMapping(value = "/usingDetails")
	public ModelAndView usingDetails(DeviceUsingDetail cond,ModelAndView mv) {
		try {
			
			// 查询工具信息列表
			List<DeviceUsingDetail> details = usingDetailService.search(cond);
			mv.addObject("details", details);
			
			logger.info("details count: " + details.size());
			
			mv.setViewName("/admin/device/usingDetails");
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 工具信息列表。
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Device cond,String from,String searchCode,String searchFactory ,ModelAndView mv) {
		try {
			mv.addObject("cond", cond);
			DeviceBasic bcond =  deviceBasicService.select(cond.getBasicId());
			mv.addObject("bcond", bcond);
			mv.addObject("from",from);
			mv.addObject("searchCode",searchCode);
			mv.addObject("searchFactory",searchFactory);
			// 查询工具信息列表
			List<Device> devices = deviceService.list(cond);
			mv.addObject("devices", devices);
			
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			
			if (cond.getDepotId()!=null && cond.getDepotId()>0) {
				Workshop wcond = new Workshop();
				wcond.setDepotId(cond.getDepotId());
				List<Workshop> workshops = workshopService.list(wcond);
				mv.addObject("workshops", workshops);
			}
			
			if (cond.getWorkshopId()!=null && cond.getWorkshopId()>0) {
				Team tcond = new Team();
				tcond.setWorkshopId(cond.getWorkshopId());
				List<Team> teams = teamService.list(tcond);
				mv.addObject("teams", teams);
			}
			
			logger.info("devices count: " + devices.size());
			
			mv.setViewName("/admin/device/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 工具使用列表。
	 */
	@RequestMapping(value = "/worklist")
	public ModelAndView worklist(Device cond ,ModelAndView mv) {
		try {
			if (cond.getId()!=null && cond.getId()<0) {
				cond.setId(null);
				mv.addObject("cond", cond);
			} else {
				mv.addObject("cond", cond);
				// 查询工具信息列表
				List<Device> devices = deviceService.list(cond);
				mv.addObject("devices", devices);
				logger.info("devices count: " + devices.size());
			}
			
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			
			if (cond.getDepotId()!=null && cond.getDepotId()>0) {
				Workshop wcond = new Workshop();
				wcond.setDepotId(cond.getDepotId());
				List<Workshop> workshops = workshopService.list(wcond);
				mv.addObject("workshops", workshops);
			}
			
			if (cond.getWorkshopId()!=null && cond.getWorkshopId()>0) {
				Team tcond = new Team();
				tcond.setWorkshopId(cond.getWorkshopId());
				List<Team> teams = teamService.list(tcond);
				mv.addObject("teams", teams);
			}
			
			mv.setViewName("/admin/device/worklist");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 新增工具信息页面。
	 */
	@RequestMapping(value = "/batchAdd")
	public ModelAndView batchAdd(Integer basicId,String from,ModelAndView mv) {
		try {
			mv.addObject("from", from);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			DeviceBasic basic = deviceBasicService.select(basicId);
			mv.addObject("basic", basic);
			mv.setViewName("/admin/device/add");
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	
	private void makeGenCodeAndImg(Device device,String workshopName,String teamName,String basicCode) throws  Exception {
		String strId = Tools.formatString(device.getId(),8);    
		device.setGenCode("["+workshopName+"]-["+teamName+"]-["+basicCode+"]-["+strId+"]");
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(device.getGenCode(), BarcodeFormat.QR_CODE, 100, 100, hints);  
        int a = device.getId()/1000;
        String idPath = Tools.formatString(a,4) + "/";
		String basePath = pathConfig.getDevicePath() + idPath;
		String baseUrl = pathConfig.getDeviceUrl() + idPath;

		File file = new File(basePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 二维码图片
		String imagePath = basePath + "code" + strId;
		device.setGenImagePath(imagePath);
		device.setGenImageUrl(baseUrl + "code" + strId);
        // 生成二维码  
		Path path=Paths.get(imagePath);  
        MatrixToImageWriter.writeToPath(bitMatrix, "gif", path);
	}
	/**
	 * 新增工具信息
	 */
	@RequestMapping(value = "/doBatchAdd")
	public ModelAndView doBatchAdd(Device device, String from,Integer count,HttpSession session, ModelAndView mv) {
		try {
			
			DeviceBasic bcond = deviceBasicService.select(device.getBasicId());
			for (int i=0;i<count;i++) {
				// 新增工具信息
				deviceService.add(device);
				Workshop workshop = workshopService.select(device.getWorkshopId());
				Team team = teamService.select(device.getTeamId());
				makeGenCodeAndImg(device,workshop.getName(),team.getName(),bcond.getCode());
		        deviceService.edit(device);
			}
			mv.addObject("from", from);
			mv.addObject("bcond", bcond);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			// 查询工具信息列表
			Device cond = new Device();
			cond.setBasicId(device.getBasicId());
			List<Device> devices = deviceService.list(cond);
			mv.addObject("devices", devices);
			logger.info("devices count: " + devices.size());
//			mv.setViewName("redirect:/admin/device/list");
			mv.setViewName("/admin/device/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 删除工具信息
	 * 
	 * @param id 工具信息Id
	 */
	@RequestMapping("/delete/{ids}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("ids")String ids) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			//删除班组人员
			for (String id:ids.split(",")) {
				Device device=deviceService.select(Integer.parseInt(id));
				if (device!=null) {
					deviceService.delete(Integer.parseInt(id));
					File file = new File(device.getGenImagePath());
					if (file.exists()) {
						file.delete();
					}
				}
			}

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}
	
	/**
	 * 删除工具信息
	 * 
	 * @param basicId 工具基本信息Id
	 */
	@RequestMapping("/deleteByBasicId/{basicId}")
	@ResponseBody
	public Map<String, Object> deleteByBasicId(@PathVariable("basicId")Integer basicId) {

		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 删除工具信息
			deviceService.deleteByBasicId(basicId);

			ret.put("status", true);

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			ret.put("status", false);
		}
		return ret;
	}

	/**
	 * 修改工具信息页面。
	 */
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable("id")Integer id, String from,ModelAndView mv) {
		try {
			mv.addObject("from", from);
			mv.addObject("editIds", id);
			Device device = deviceService.select(id);
			mv.addObject("device", device);
			DeviceBasic basic = deviceBasicService.select(device.getBasicId()); 
			mv.addObject("basic", basic);
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			Workshop wcond = new Workshop();
			wcond.setDepotId(device.getDepotId());
			List<Workshop> workshops = workshopService.list(wcond);
			mv.addObject("workshops", workshops);
			Team tcond = new Team();
			tcond.setWorkshopId(device.getWorkshopId());
			List<Team> teams = teamService.list(tcond);
			mv.addObject("teams", teams);
			mv.setViewName("/admin/device/edit");
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
	
	/**
	 * 批量修改工具信息页面。
	 */
	@RequestMapping(value = "/batchEdit")
	public ModelAndView batchEdit(Integer basicId,String from ,String strIds, ModelAndView mv) {
		try {
			List<Depot> depots = depotService.list();
			mv.addObject("depots", depots);
			DeviceBasic basic = deviceBasicService.select(basicId);
			mv.addObject("basic", basic);
			mv.addObject("from", from);
			mv.addObject("editIds", strIds);
			mv.setViewName("/admin/device/edit");
		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}

	/**
	 * 修改工具信息
	 */
	@RequestMapping(value = "/doEdit")
	public ModelAndView doEdit(Device device ,String strIds,HttpSession session, ModelAndView mv) {
		try {
			DeviceBasic basic =  deviceBasicService.select(device.getBasicId());
			mv.addObject("bcond", basic);
			Workshop workshop = workshopService.select(device.getWorkshopId());
			Team team = teamService.select(device.getTeamId());
			for (String sId:strIds.split(",")) {
				Device dbDevice = deviceService.select(Integer.parseInt(sId));
				dbDevice.setTeamId(device.getTeamId());
				dbDevice.setName(device.getName());
				// 修改工具信息
				makeGenCodeAndImg(dbDevice,workshop.getName(),team.getName(),basic.getCode());
				deviceService.edit(dbDevice);
			}
			
			// 查询工具信息列表
			Device cond = new Device();
			cond.setBasicId(device.getBasicId());
			List<Device> devices = deviceService.list(cond);
			mv.addObject("devices", devices);
			logger.info("devices count: " + devices.size());
			
			mv.setViewName("admin/device/list");

		} catch (Exception e) {
			logger.error(getMessage(Messages.E_INTERNAL), e);
			gotoErrorPage(mv);
		}
		return mv;
	}
}
