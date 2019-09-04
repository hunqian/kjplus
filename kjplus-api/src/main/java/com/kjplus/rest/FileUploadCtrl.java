package com.kjplus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.ybk.basic.util.Util;

import com.kjplus.constant.ShareConstant;
import com.kjplus.constant.UploadConstant;
import com.kjplus.eto.FileRepoEto;
import com.kjplus.model.FollowupMainEbo;
import com.kjplus.service.IFileRepoService;
import com.kjplus.service.IFollowupService;
import com.mq.util.DateUtil;

@Api(tags = "文件上传", description = "关于文件上传的接口")
@Controller
// 文件上传控制器类
public class FileUploadCtrl implements ServletContextAware { // 实现ServletContextAware接口，获取本地路径

	// private static final int BUFFER_SIZE = 10 * 1024;

	@Autowired
	private IFollowupService followupService;
	@Autowired
	private IFileRepoService fileRepoService;

	private static Logger logger = Logger.getLogger(FileUploadCtrl.class);

	@SuppressWarnings("unused")
	private ServletContext servletContext;

	// 实现接口中的setServletContext方法
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private static Hashtable<String, String> DIRS = new Hashtable<String, String>();

	// 上传普通图片文件,将文件上传请求映射到该方法
	@ApiOperation(value = "文件上传")
	@ApiImplicitParams({ @ApiImplicitParam(name = "imgfile", value = "文件", required = true, dataType = "file", paramType = "form") })
	@RequestMapping(value = "/uploadimgfile.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadimgfile(@RequestParam("imgfile") CommonsMultipartFile mFile, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		mFile.getSize();
		String filename = mFile.getOriginalFilename();
		int pos = filename.lastIndexOf(".");
		String postfix = "";
		String fileType;
		String tofilename = null;
		if (pos > 0) {
			UUID uuid = UUID.randomUUID();
			tofilename = uuid.toString();
			postfix = filename.substring(pos);
			fileType = filename.substring(pos + 1).toLowerCase();
			filename = filename.substring(0, pos);
		} else {
			tofilename = filename;
			fileType = "未知";
		}
		if (UploadConstant.ALLOW_UPLOAD_PIC.indexOf(fileType) == -1) {
			map.put("msg", "上传图片格式不正确!");
			map.put("success", false);
			return map;
		}

		String path = UploadConstant.IMAGE_FILE_DIR + "imgfile/" + DateUtil.currDay() + "/";
		path = Util.replaceAll(path, "\\", "/");
		String toSrc = path + tofilename + postfix;
		String toUrl = UploadConstant.IMAGE_SERVER_URL + "imgfile/" + DateUtil.currDay() + "/" + tofilename + postfix;
		checkDir(path);
		try {
			mFile.getFileItem().write(new File(toSrc));
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("url", toUrl);
			// map2.put("path", toSrc);
			map.put("data", data);
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "上传成功！");
		} catch (Exception e) {
			logger.error(e);
			// map.put("failure", true);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	// 上传测试
	@ApiOperation(value = "上传测试")
	@ApiImplicitParams({ @ApiImplicitParam(name = "upload", value = "返回上传测试页面", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/uploadform.html", method = RequestMethod.POST)
	public ModelAndView uploadForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("upload");
		return mav;
	}

	// 上传测试
	@ApiOperation(value = "上传测试")
	@ApiImplicitParams({ @ApiImplicitParam(name = "upload_flpe", value = "返回上传文件测试页面", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/uploadfileform.html", method = RequestMethod.POST)
	public ModelAndView uploadFileForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("upload_flpe");
		return mav;
	}

	// app端上传普通图片文件,将文件上传请求映射到该方法 随访上传图片等
	@ApiOperation(value = "app端上传普通图片文件,将文件上传请求映射到该方法 随访上传图片等")
	@ApiImplicitParams({ @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file", paramType = "form"),
			@ApiImplicitParam(name = "fpleid", value = "文件ID", required = true, dataType = "Integer", paramType = "body") })
	@RequestMapping(value = "/uploadflpefile.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadFollowFile(@RequestParam("file") CommonsMultipartFile mFile, @RequestParam("fpleid") String flpeId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		if (mFile.getSize() == 0) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请选择上传文件");
			return map;
		}

		try {
			FollowupMainEbo followUp = followupService.getFollowupMainByIdOrCode(Util.parseNumVl(flpeId, 0), null);
			if (followUp == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该随访记录不存在!");
				return map;
			}

			String filename = mFile.getOriginalFilename();
			int pos = filename.lastIndexOf(".");
			String mFileType;
			String postfix = "";
			String tofilename = null;

			if (pos < 0) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该指定文件后缀!");
				return map;
			}

			UUID uuid = UUID.randomUUID();
			tofilename = uuid.toString();
			postfix = filename.substring(pos);
			mFileType = filename.substring(pos + 1).toLowerCase();
			filename = filename.substring(0, pos);
			String path = "";
			// 判断文件类型
			if (UploadConstant.ALLOW_UPLOAD_PIC.indexOf(mFileType) < 0 && UploadConstant.ALLOW_UPLOAD_VIDEO.indexOf(mFileType) < 0 && UploadConstant.ALLOW_UPLOAD_VOICE.indexOf(mFileType) < 0) {
				map.put("msg", "上传图片格式不正确!");
				map.put("success", false);
				return map;
			}
			String filetype = null;
			List<String> ftps = null;
			if (Util.isNull(filetype)) {
				ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_PIC, ";");
				for (String ftp : ftps) {
					if (ftp.equalsIgnoreCase(mFileType)) {
						filetype = ShareConstant.UPLOAD_TYPE_IMAGE;
						break;
					}
				}
			}

			if (Util.isNull(filetype)) {
				ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_VIDEO, ";");
				for (String ftp : ftps) {
					if (ftp.equalsIgnoreCase(mFileType)) {
						filetype = ShareConstant.UPLOAD_TYPE_VIDEO;
						break;
					}
				}
			}
			if (Util.isNull(filetype)) {
				ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_VOICE, ";");
				for (String ftp : ftps) {
					if (ftp.equalsIgnoreCase(mFileType)) {
						filetype = ShareConstant.UPLOAD_TYPE_VOICE;
						break;
					}
				}
			}
			path = UploadConstant.APP_FILE_DIR + "upload/" + DateUtil.currDay() + "/";

			path = Util.replaceAll(path, "\\", "/");
			String toSrc = path + tofilename + postfix;

			checkDir(path);
			mFile.getFileItem().write(new File(toSrc));

			FileRepoEto fileRepoEto = new FileRepoEto();
			fileRepoEto.setFilePath(toSrc);
			fileRepoEto.setFileType(filetype);
			fileRepoEto.setMainId(followUp.getId());
			fileRepoEto.setMainType(ShareConstant.MAIN_TYPE_FLP);
			fileRepoService.addFileRepoEbo(fileRepoEto);
			// 开始保存
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "上传成功！");
		} catch (Exception e) {
			logger.error(e);
			// map.put("failure", true);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

	private void checkDir(String dir) {
		if (DIRS.containsKey(dir))
			return;
		File f = new File(dir);
		if (!f.exists())
			f.mkdirs();
		DIRS.put(dir, "Y");
		return;
	}

	// 多图片页面上传 页面跳转
	@ApiOperation(value = "多图片页面上传 页面跳转")
	@ApiImplicitParams({ @ApiImplicitParam(name = "upload_morepicture", value = "返回多图片上传页面", required = true, dataType = "String", paramType = "body") })
	@RequestMapping(value = "/uploadmorefileform.html", method = RequestMethod.POST)
	public ModelAndView uploadMoreFileForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("upload_morepicture");
		return mav;

	}

	// 多图片页面上传
	@ApiOperation(value = "多图片页面上传 页面跳转")
	@ApiImplicitParams({ @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file", paramType = "form") })
	@RequestMapping(value = "/uploadimgmorefile.html", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadimgmorefile(@RequestParam(value = "file", required = false) CommonsMultipartFile[] mFile, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", ShareConstant.CONNECT_STATUS);

		if (mFile.length == 0) {
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", "请选择上传文件");
			return map;
		}

		try {
			String flpId = "2";
			FollowupMainEbo followUp = followupService.getFollowupMainByIdOrCode(Util.parseNumVl(flpId, 0), null);
			if (followUp == null) {
				map.put("result", ShareConstant.RES_ERROR);
				map.put("message", "该随访记录不存在!");
				return map;
			}
			int length = mFile.length;
			for (int i = 0; i < length; i++) {

				String filename = mFile[i].getOriginalFilename();
				int pos = filename.lastIndexOf(".");
				String mFileType;
				String postfix = "";
				String tofilename = null;

				if (pos < 0) {
					map.put("result", ShareConstant.RES_ERROR);
					map.put("message", "该指定文件后缀!");
					return map;
				}

				UUID uuid = UUID.randomUUID();
				tofilename = uuid.toString();
				postfix = filename.substring(pos);
				mFileType = filename.substring(pos + 1).toLowerCase();
				filename = filename.substring(0, pos);
				String path = "";
				// 判断文件类型
				if (UploadConstant.ALLOW_UPLOAD_PIC.indexOf(mFileType) < 0 && UploadConstant.ALLOW_UPLOAD_VIDEO.indexOf(mFileType) < 0 && UploadConstant.ALLOW_UPLOAD_VOICE.indexOf(mFileType) < 0) {
					map.put("msg", "上传图片格式不正确!");
					map.put("success", false);
					return map;
				}
				String filetype = null;
				List<String> ftps = null;
				if (Util.isNull(filetype)) {
					ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_PIC, ";");
					for (String ftp : ftps) {
						if (ftp.equalsIgnoreCase(mFileType)) {
							filetype = ShareConstant.UPLOAD_TYPE_IMAGE;
							break;
						}
					}
				}

				if (Util.isNull(filetype)) {
					ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_VIDEO, ";");
					for (String ftp : ftps) {
						if (ftp.equalsIgnoreCase(mFileType)) {
							filetype = ShareConstant.UPLOAD_TYPE_VIDEO;
							break;
						}
					}
				}
				if (Util.isNull(filetype)) {
					ftps = Util.str2Array(UploadConstant.ALLOW_UPLOAD_VOICE, ";");
					for (String ftp : ftps) {
						if (ftp.equalsIgnoreCase(mFileType)) {
							filetype = ShareConstant.UPLOAD_TYPE_VOICE;
							break;
						}
					}
				}
				path = UploadConstant.APP_FILE_DIR + "upload/" + DateUtil.currDay() + "/";

				path = Util.replaceAll(path, "\\", "/");
				String toSrc = path + tofilename + postfix;

				checkDir(path);
				mFile[i].getFileItem().write(new File(toSrc));

				FileRepoEto fileRepoEto = new FileRepoEto();
				fileRepoEto.setFilePath(toSrc);
				fileRepoEto.setFileType(filetype);
				fileRepoEto.setMainId(followUp.getId());
				fileRepoEto.setMainType(ShareConstant.MAIN_TYPE_FLP);
				fileRepoService.addFileRepoEbo(fileRepoEto);

			}

			// 开始保存
			map.put("result", ShareConstant.RES_OK);
			map.put("message", "上传成功！");
		} catch (Exception e) {
			logger.error(e);
			// map.put("failure", true);
			map.put("result", ShareConstant.RES_ERROR);
			map.put("message", e.getMessage());
		}
		return map;
	}

}