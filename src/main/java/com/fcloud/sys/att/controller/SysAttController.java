package com.fcloud.sys.att.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.appengine.api.store.StoreService;
import com.alibaba.appengine.api.store.StoreServiceFactory;
import com.fcloud.core.controller.ActionController;
import com.fcloud.sys.att.model.SysAtt;
import com.fcloud.sys.att.repository.SysAttRepository;
import com.fcloud.util.DateUtil;
import com.fcloud.util.FileOptUtil;
import com.fcloud.util.IdGenerator;
import com.fcloud.util.StringUtil;
import com.fcloud.weservice.rule.model.WeRuleReplyPictext;
import com.fcloud.weservice.rule.model.WeRuleReplyPictextson;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextRepository;
import com.fcloud.weservice.rule.repository.WeRuleReplyPictextsonRepository;

@Controller
@RequestMapping("/sys/att")
public class SysAttController extends
		ActionController<SysAtt, SysAttRepository> {
	@Value("#{fcloud['fcloudhost']}")
	private String fcloudhost;

	@Resource
	private WeRuleReplyPictextRepository weRuleReplyPictextRepository;

	@Resource
	private WeRuleReplyPictextsonRepository weRuleReplyPictextsonRepository;

	@RequestMapping("/fileUpload")
	public ModelAndView fileUpload(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

				Iterator<String> iter = multiRequest.getFileNames();
				String fileSaveName = "";
				MultipartFile myfile = null;
				if (iter.hasNext()) {
					myfile = multiRequest.getFile((String) iter.next());
					if (myfile.isEmpty()) {
						out.print("附件不能为空");
						out.flush();
						return null;
					} else {
						String originalFilename = myfile.getOriginalFilename();
						try {
							// 重新上传新的图片
							StoreService storeService = StoreServiceFactory.getStoreService();
							SysAtt sysAtt = null;
							if (request.getParameter("fileId").toString()
									.endsWith("false")) {
								sysAtt = new SysAtt();
								String id = IdGenerator.newId();
								sysAtt.setId(id);
							} else {
								sysAtt = getRepository().findOne(
										request.getParameter("fileId"));
								if (sysAtt == null) {
									sysAtt = new SysAtt();
									String id = IdGenerator.newId();
									sysAtt.setId(id);
								} else {
									System.out.println(sysAtt.getFileUrl());
									String delPath = sysAtt.getFileUrl();
									storeService.deleteFile(delPath);
								}
							}
							String datefilePath = DateUtil.convertDateToString(
									"yyyyMMdd", new Date());
							byte[] fileBytes= input2byte(myfile.getInputStream());
							String path =  "fcloudtest/"+datefilePath+"/"+sysAtt.getId();
							storeService.saveBinaryFile(fileBytes,path);
							
							
							sysAtt.setFileName(originalFilename);
							sysAtt.setFileUrl(path);
							sysAtt.setPicUrl(path);

							getRepository().save(sysAtt);
							JSONObject result = new JSONObject();
							result.put("attId", sysAtt.getId());
							String pathUrl = fcloudhost;
//							if (request.getLocalPort() != 80) {
//								pathUrl = StringUtil.linkString(pathUrl, ":",
//										String.valueOf(request.getLocalPort()));
//							}
							pathUrl = StringUtil.linkString(pathUrl, "/",
									request.getContextPath());
							result.put("picUrl", pathUrl +"/sys/att/showPic?file_id="+sysAtt.getId());
							// 返回相关请求的信息
							out.print(result.toString());
						} catch (Exception e) {
							System.out.println("文件" + originalFilename
									+ "]上传错误");
							e.printStackTrace();
							out.print("1上传错误");
							return null;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}
	
	@RequestMapping("/fileUpload1")
	public ModelAndView fileUpload1(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

				Iterator<String> iter = multiRequest.getFileNames();
				String fileSaveName = "";
				MultipartFile myfile = null;
				if (iter.hasNext()) {
					myfile = multiRequest.getFile((String) iter.next());
					if (myfile.isEmpty()) {
						out.print("附件不能为空");
						out.flush();
						return null;
					} else {
						String originalFilename = myfile.getOriginalFilename();
						try {
							// 重新上传新的图片

							SysAtt sysAtt = null;
							if (request.getParameter("fileId").toString()
									.endsWith("false")) {
								sysAtt = new SysAtt();
								String id = IdGenerator.newId();
								sysAtt.setId(id);
							} else {
								sysAtt = getRepository().findOne(
										request.getParameter("fileId"));
								if (sysAtt == null) {
									sysAtt = new SysAtt();
									String id = IdGenerator.newId();
									sysAtt.setId(id);
								} else {
									System.out.println(sysAtt.getFileUrl());
									String delPath = sysAtt.getFileUrl();
									File delFiele = new File(delPath);
									if (delFiele.isFile() && delFiele.exists()) {
										delFiele.delete();
									}
								}
							}
							String realPath = request.getSession()
									.getServletContext().getRealPath("/upload");
							realPath = realPath.replaceAll("\\\\", "/");
							String datefilePath = DateUtil.convertDateToString(
									"yyyyMMdd", new Date());
							String path = StringUtil.linkString(realPath, "/",
									datefilePath);
							while (!FileOptUtil.checkFilePath(path)) {
								FileOptUtil.createFilePaths(path);
							}

							String formFilePath = StringUtil.linkString(path,
									"/", sysAtt.getId() + "-"
											+ originalFilename);
							String picFilePath = "/" + datefilePath + "/"
									+ sysAtt.getId() + "-" + originalFilename;
							if (!FileOptUtil.checkFilePath(formFilePath)) {
								File localFile = new File(formFilePath);
								myfile.transferTo(localFile);
							}
							sysAtt.setFileName(originalFilename);
							sysAtt.setFileUrl(formFilePath);
							sysAtt.setPicUrl(picFilePath);

							getRepository().save(sysAtt);
							JSONObject result = new JSONObject();
							result.put("attId", sysAtt.getId());
							String pathUrl = fcloudhost;
							if (request.getLocalPort() != 80) {
								pathUrl = StringUtil.linkString(pathUrl, ":",
										String.valueOf(request.getLocalPort()));
							}
							pathUrl = StringUtil.linkString(pathUrl, "/",
									request.getContextPath());
							pathUrl = pathUrl + "/upload";
							result.put("picUrl", pathUrl + picFilePath);
							// 返回相关请求的信息
							out.print(result.toString());
						} catch (Exception e) {
							System.out.println("文件" + originalFilename
									+ "]上传错误");
							e.printStackTrace();
							out.print("1上传错误");
							return null;
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	@RequestMapping("/deleteById")
	public ModelAndView deleteById(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String fileId = request.getParameter("fileId");
			String type = request.getParameter("type");
			SysAtt att = getRepository().findOne(fileId);
			if (att != null) {
				if ("0".equals(type)) {
					List<WeRuleReplyPictext> pictexts = weRuleReplyPictextRepository
							.getDao().queryBuilder().where()
							.eq("att_id", att.getId()).query();
					if (!pictexts.isEmpty()) {
						WeRuleReplyPictext pictext = pictexts.get(0);
						pictext.setAttId("");
						pictext.setFdPic("");
						weRuleReplyPictextRepository.save(pictext);
					}
				} else {
					List<WeRuleReplyPictextson> pictexts = weRuleReplyPictextsonRepository
							.getDao().queryBuilder().where()
							.eq("att_id", att.getId()).query();
					if (!pictexts.isEmpty()) {
						WeRuleReplyPictextson pictextson = pictexts.get(0);
						pictextson.setAttId("");
						pictextson.setFdPic("");
						weRuleReplyPictextsonRepository.save(pictextson);
					}
				}
				getRepository().deleteByAttId(att.getId());
				out.print("code:1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return null;
	}

	@RequestMapping("/showPic")
	public ModelAndView showPic(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);

		String attId = request.getParameter("file_id");
		OutputStream out = null;
		try {
			if (StringUtil.isNotNull(attId)) {
				SysAtt sysAtt = getRepository().findOne(attId);
				if (sysAtt != null) {
					StoreService storeService = StoreServiceFactory.getStoreService();
					if (storeService.isFileExist(sysAtt.getFileUrl())) {
						out = new BufferedOutputStream(
								response.getOutputStream());
						byte b[] = storeService.getBinaryFile(sysAtt.getFileUrl());
						out.write(b);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    }  
  
}
