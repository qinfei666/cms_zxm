package com.qinfei.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.qinfei.cms.utils.FileUtils;
import com.qinfei.cms.utils.HtmlUtils;
import com.qinfei.cms.utils.StringUtils;
import com.qinfei.common.Contantant;
import com.qinfei.entity.Article;
import com.qinfei.entity.Channel;
import com.qinfei.entity.User;
import com.qinfei.entity.category;
import com.qinfei.service.ArticleService;
import com.qinfei.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Value("${upload.path}")
	String picRootPath;
	@Value("${pic.path}")
	String picUrl;
	
	@Autowired
	UserService service;
	@Autowired
	ArticleService articleService;

	@RequestMapping("home")
	public String home(){
		return "user/home";
	}
	/**
	 * 跳转到注册页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request){
		User user = new User();
		request.setAttribute("user", user);
		return "user/register";
	}
	/**
	 * 从注册页面发过来的请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(HttpServletRequest request,
			@Valid @ModelAttribute("user") User user,
			BindingResult result
			){
		if (result.hasErrors()) {
			return "user/register";
		}
		User existuser = service.getUserByUsername(user.getUsername());
		if (existuser!=null) {
			result.rejectValue("username", "", "用户名已经存在");
			return "user/register";
		}
		if (StringUtils.isNumber(user.getPassword())) {
			result.rejectValue("password", "", "密码不能全是数字");
			return "user/register";
		}
		int result1 = service.register(user);
		if (result1<1) {
			request.setAttribute("eror", "注册失败,稍后再试");
			return "user/register";
		}
		
		return "regirect:login";
	}
	/**
	 * 跳转到登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		return "user/login";
	}
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user){
		User u = service.login(user);
		if (u==null) {
			request.setAttribute("error", "用户名密码错误");
			return "/user/login";
		}
		request.getSession().setAttribute(Contantant.USER_KEY, u);
		//进入管理页面
		if (u.getRole()==Contantant.USER_ROLE_ADMIN) {
			return "redirect:/admin/index";
		}
		//进入个人中心
		return "redirect:/user/home";
	}
	@RequestMapping("articles")
	public String articles(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNum){
		
		User loginUser = (User) request.getSession().getAttribute(Contantant.USER_KEY);
		
		PageInfo<Article> page = articleService.listByUser(loginUser.getId(),pageNum);
		
		page.getPages();
		
		request.setAttribute("page", page);
		
		return "/user/articles/list";
		
		
		
		
		
		
	}
	@RequestMapping("comments")
	public String comments(){
		return "/user/comment/list";
	}
	
	
	@RequestMapping("delArtice")
	@ResponseBody
	public boolean delArtice(int id){
		System.out.println(id);
		int result = articleService.delArtice(id);
		return result>0;
	}
	/**
	 * 转发到发表文章页面
	 * @param request
	 * @return
	 */
	@RequestMapping("postArticle")
	public String postArticle(HttpServletRequest request){
		List<Channel> channels=articleService.getChannel();
		request.setAttribute("channels", channels);
		return "/user/articles/post";
	}
	
	/**
	 * 获取分类
	 */
	@RequestMapping("getCategoris")
	@ResponseBody
	public List<category> getCategoryisByCid(int cid){
		List<category> categoryisByCid = articleService.getCategoryisByCid(cid);
		return categoryisByCid;
	}
	
	
	@RequestMapping(value="postArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean postArticle(HttpServletRequest request,Article article,
			MultipartFile file
			) {
		String picUrl;
		try {
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User LoginUser = (User) request.getSession().getAttribute(Contantant.USER_KEY);
		article.setUserId(LoginUser.getId());
		return articleService.add(article)>0;
	}
	
	
	private String processFile(MultipartFile file) throws IllegalStateException, IOException{
		//判断目标时间是否存在
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		
		File path = new File(picRootPath+"/"+subPath);
		if (!path.exists()) {
			//路径不存在则创建
			path.mkdirs();
		}
		//计算新的文件名称
		String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		//随机生成文件名
		String fileName = UUID.randomUUID().toString()+suffixName;
	
		file.transferTo(new File(picRootPath+"/"+subPath+"/"+fileName));
		
		return subPath+"/"+fileName;
	}
	
	/**
	 * 跳转到修改文章的页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updateArticle",method=RequestMethod.GET)
	public String updateArticle(HttpServletRequest request,int id){
		//获取栏目
		List<Channel> channels=articleService.getChannel();
		request.setAttribute("channels", channels);
		//获取文章
		Article article = articleService.getById(id);
		
		User loginUser = (User) request.getSession().getAttribute(Contantant.USER_KEY);
		if (loginUser.getId()!=article.getUserId()) {
			// todo 准备做异常处理
		}
		request.setAttribute("article", article);
		request.setAttribute("content1",  HtmlUtils.htmlspecialchars(article.getContent()));
		
		
		
		return "/user/articles/update";
	}
	/**
	 * 接收修改文章页面提交的数据
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updateArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean updateArticle(HttpServletRequest request,Article article,MultipartFile file){
		String picUrl;
		try {
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User LoginUser = (User) request.getSession().getAttribute(Contantant.USER_KEY);
//		article.setUserId(LoginUser.getId());
		int updateResult = articleService.updated(article,LoginUser.getId());
		return updateResult>0;
	}
	
}
