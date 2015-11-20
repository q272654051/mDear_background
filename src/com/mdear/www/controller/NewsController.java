package com.mdear.www.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mdear.www.commons.annotation.SystemControllerLog;
import com.mdear.www.commons.enumeration.News_scrolling;
import com.mdear.www.commons.enumeration.News_status;
import com.mdear.www.commons.util.FormatDateUtil;
import com.mdear.www.commons.util.JsonUtil;
import com.mdear.www.commons.util.Pager;
import com.mdear.www.commons.util.WebConstants;
import com.mdear.www.service.ICityService;
import com.mdear.www.service.ICodeService;
import com.mdear.www.service.IColumnTableService;
import com.mdear.www.service.ILableService;
import com.mdear.www.service.IMessageService;
import com.mdear.www.service.INewsService;
import com.mdear.www.service.IUserService;
import com.mdear.www.vo.CityTable;
import com.mdear.www.vo.Code;
import com.mdear.www.vo.ColumnTable;
import com.mdear.www.vo.Labletable;
import com.mdear.www.vo.Messagevo;
import com.mdear.www.vo.News;
import com.mdear.www.vo.User;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

/**
 * @author dengbojing
 * @date 2015-8-12
 * @description
 */
@Controller
@RequestMapping(value = "newsController")
public class NewsController {

	@Resource
	private INewsService newsService;
	@Resource
	private IColumnTableService colService;
	@Resource
	private ICodeService codeService;
	@Resource
	private ILableService lableService;
	@Resource
	private ICityService cityService;
	@Resource
	private IMessageService messageService;
	@Resource
	private IUserService userService;
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 转向添加资讯页面
	 */
	@RequestMapping(value = "/toAdd")
	@SystemControllerLog(description = "转向新闻添加页面或者修改新闻")
	public ModelAndView toAdd(HttpServletRequest request,
			HttpServletResponse response, News news) {
		List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable c where c.status=1 and c.pid =1");
		List<Labletable> lable_list = lableService.findByHQLQuery("from Labletable");
		List<CityTable> city_list = cityService.findByHQLQuery("from CityTable");
		if (news.getId() != null) {
			news = newsService.findNewsById(news.getId());
		}
		return new ModelAndView("news/news_add")
				.addObject("col_list", col_list)
				.addObject("lable_list", lable_list)
				.addObject("city_list", city_list)
				.addObject("news", news);
	}

	/**
	 * @description 添加新闻
	 * @param request
	 * @param response
	 * @param writer
	 * @param news
	 * 新闻对象,页面参数名字只需对应实体类的属性名字一致,就会自动装配新闻对象
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/saveOrUpdate")
	@SystemControllerLog(description = "添加或者更新资讯")
	public void saveOrUpdateNews(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter, News news) throws ParseException {
		Map<String, Object> result_map = new HashMap<String, Object>();
		User user = WebConstants.getUser(request);
		if(news.getId() != null && news.getId() > 0){
			news.setEditdate(FormatDateUtil.DateToShortStr(new Date()));
		}else{
			news.setCreatedate(FormatDateUtil.DateToShortStr(new Date()));
		    Messagevo message = new Messagevo();
		    message.setMessage(user.getUserName() + "发布了" + news.getTitle());
		    message.setMessageStatus(0);
		    message.setShijian(new Date());
		    message.setStatus(news.getStatus());
		    message.setUserid(news.getUserid());
		    message.setShenherenid(news.getShenheid());
		    message.setNewsid(news.getId());
		    messageService.saveOrUpdate(message);
		}
		news.setUserid(user.getId());
		boolean bool = newsService.saveOrUpdate(news);
		result_map.put("success", bool);
		printWriter.write(JsonUtil.jsonObject(result_map, null, null));
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * 批量更新资讯
	 * @param request
	 * @param response
	 * @param writer
	 * @param ids
	 */
	@RequestMapping(value = "/updateAll")
	@SystemControllerLog(description = "批量提交资讯到等待审核")
	public void updateAll(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter, String ids[]) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		String status = request.getParameter("status");
		// String[] ids = request.getParameterValues("id");
		int i = 0;
		String idValue = "";
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				News news = new News();
				news.setId(Integer.parseInt(id));
				news = newsService.findById(news.getId());
				if (null != news) {
					news.setStatus(Integer.parseInt(status));
					boolean f = newsService.saveOrUpdate(news);
					if (f) {
						i++;
					} else {
						idValue += id + ",";
					}
				} else {
					idValue += id + ",";
				}
				result_map.put("success", true);
			}
		}
		result_map.put(i + "", idValue);
		printWriter.write(JsonUtil.jsonObject(result_map, null, null));
		printWriter.flush();
        printWriter.close();
	}

	/**
	 * @description 删除新闻
	 * @param request
	 * @param response
	 * @param writer
	 * @param news
	 *            新闻对象,页面参数名字只需对应实体类的属性名字一致,就会自动装配新闻对象
	 */
	@SystemControllerLog(description = "删除资讯")
	@RequestMapping(value = "/delNews")
	public void delNews(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter, News news) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		if (news.getId() != null) {
			boolean f = newsService.deleteNews(news.getId());
			result_map.put("success", f);
		} else {
			result_map.put("success", "false");
		}
		printWriter.write(JsonUtil.jsonObject(result_map, null, null));
		printWriter.flush();
        printWriter.close();
	}

	/**
	 * @description 删除新闻
	 * @param request
	 * @param response
	 * @param writer
	 */
	@SystemControllerLog(description = "批量删除资讯")
	@RequestMapping(value = "/delNewsByList")
	public void delNewsByList(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter, String[] ids) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		// String[] ids = request.getParameterValues("id");
		int i = 0;
		String idValue = "";
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				boolean f = newsService.deleteNews(Integer.parseInt(id));
				if (f) {
					i++;
				} else {
					idValue += id + ",";
				}
			}
			result_map.put("success", true);
		} else {
			result_map.put("success", false);
		}
		result_map.put(i + "", idValue);
		printWriter.write(JsonUtil.jsonObject(result_map, null, null));
		printWriter.flush();
        printWriter.close();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param writer
	 * @param news
	 *            新闻对象,页面参数名字只需对应实体类的属性名字一致,就会自动装配新闻对象
	 * @return
	 */
	@SystemControllerLog(description = "获取资讯详细信息")
	@RequestMapping(value = "/getNews")
	public ModelAndView newsDetail(HttpServletRequest request,HttpServletResponse response, PrintWriter writer, News news) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		if (news.getId() != null) {
			news = newsService.findById(news.getId());
		}
		result_map.put("news", news);
		return new ModelAndView("news/news_detail")
		.addAllObjects(result_map);
	}

	/**
	 * @description 预览新闻
	 * @param request
	 * @param response
	 * @param writer
	 * @param news
	 * @return
	 */
	@SystemControllerLog(description = "预览资讯")
	@RequestMapping(value = "/previewNews")
	public ModelAndView previewNews(HttpServletRequest request,HttpServletResponse response, PrintWriter writer, News news) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		if (news.getId() != null) {
			news = newsService.findById(news.getId());
		}
		result_map.put("news", news);
		return new ModelAndView("news/news_preview").addAllObjects(result_map);
	}

	/**
	 * 分页获取资讯 没有条件，获取全部咨询——————>咨询中心
	 * 
	 * @param request
	 * @param response
	 * @param writer
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getNewsList")
	public ModelAndView getNewsList(HttpServletRequest request,HttpServletResponse response, PrintWriter writer, News news) {
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1001");
		List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable");
		Map<Integer, String> code_map = new HashMap<Integer, String>();
		Map<String, String> column_map = new HashMap<String, String>();
		for (Code c : code_list) {
			code_map.put(c.getDmz(), c.getDmsm());
		}
		for (ColumnTable table : col_list) {
			column_map.put(table.getId() + "", table.getColumnName());
		}
		String curPage, pageSize;
		curPage = request.getParameter("curPage");
		pageSize = request.getParameter("pageSize");
		pageSize = "100000";

		Pager pager = new Pager();
		if (StringUtils.isNotBlank(curPage)) {
			pager.setCurPage(Integer.parseInt(curPage));
		}
		if (StringUtils.isNotBlank(pageSize)) {
			pager.setPageSize(Integer.parseInt(pageSize));
		}
		User user = WebConstants.getUser(request);

		String title = request.getParameter("title");
		news.setTitle(title);
		String status = request.getParameter("status");
		if (null != status) {
			news.setStatus(Integer.parseInt(status));
		}
		String type = request.getParameter("type");
		news.setType(type);
		String startTime = request.getParameter("startTime");
		news.setStartTime(startTime);
		String endTime = request.getParameter("endTime");
		news.setEndTime(endTime);
		List<News> news_list = newsService.findByHQLQuery(news, pager).getList();
		return new ModelAndView("news/news_centre")
				.addObject("news_list", news_list)
				.addObject("code_list", code_list)
				.addObject("code_map", code_map)
				.addObject("col_list", col_list)
				.addObject("column_map", column_map);
	}

	/**
	 * 分页获取我的资讯 根据登陆人id 获取相应的咨询
	 * 
	 * 我的资讯
	 * @param request
	 * @param response
	 * @param writer
	 * @param curPage
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getNewsListByUser")
	public ModelAndView getNewsListByUser(HttpServletRequest request, HttpServletResponse response, News news) {
		String title=request.getParameter("title");//获取标题
		String status=request.getParameter("status");//获取状态
		String type=request.getParameter("type");//获取类型
		String startTime=request.getParameter("startTime");//获取开始时间
		if (StringUtils.isNotBlank(startTime)){
			startTime = StringUtils.trim(startTime.replace("/", "-"));
		}
		String endTime=request.getParameter("endTime");//获取截至时间
		if (StringUtils.isNotBlank(endTime)){
			endTime = StringUtils.trim(endTime.replace("/", "-"));
		}
		List<Code> code_list = codeService .findByHQLQuery("from Code c where c.dmid = 1001");
		List<ColumnTable> col_list = colService .findByHQLQuery("from ColumnTable");
		Map<Integer, String> code_map = new HashMap<Integer, String>();
		Map<String, String> column_map = new HashMap<String, String>();
		for (Code c : code_list) {
			code_map.put(c.getDmz(), c.getDmsm());
		}
		for (ColumnTable table : col_list) {
			column_map.put(table.getId() + "", table.getColumnName());
		}
		String curPage, pageSize;
		curPage = request.getParameter("curPage");
		pageSize = request.getParameter("pageSize");
		pageSize = "100000";

		Pager pager = new Pager();
		if (StringUtils.isNotBlank(curPage)) {
			pager.setCurPage(Integer.parseInt(curPage));
		}
		if (StringUtils.isNotBlank(pageSize)) {
			pager.setPageSize(Integer.parseInt(pageSize));
		}
		User user = WebConstants.getUser(request);
		String userid = request.getSession().getAttribute("userId").toString();
		news.setUserid(Integer.parseInt(userid));
	/*	String title = request.getParameter("title");
		news.setTitle(title);
		String status = request.getParameter("status");
		if (null != status) {
			news.setStatus(Integer.parseInt(status));
		}
		String type = request.getParameter("type");
		news.setType(type);
		String startTime = request.getParameter("startTime");
		news.setStartTime(startTime);
		String endTime = request.getParameter("endTime");
		news.setEndTime(endTime);*/
		String sql=null;
		if(StringUtils.isNotBlank(title)&&StringUtils.isNotBlank(status)&&StringUtils.isNotBlank(type)&&StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
			sql="from News n where n.fabudate BETWEEN '"+startTime+"' AND '"+endTime+"' and n.title LIKE '%"+title+"%' and n.status='"+status+"' and n.smalltype='"+type+"'";
		}else {
			sql="from News where userid=" + userid;
		}
		List<News> news_list = newsService.findByHQLQuery(sql);
		return new ModelAndView("news/my_news")
				.addObject("news_list", news_list)
				.addObject("code_list", code_list)
				.addObject("code_map", code_map)
				.addObject("col_list", col_list)
				.addObject("column_map", column_map);
	}

	/**
	 * 分页获取我的审核—————>审核中心
	 * 
	 * @param request
	 * @param response
	 * @param writer
	 * @param news
	 * @return
	 */
	@RequestMapping(value = "/getAuditCentre")
	public ModelAndView getAuditCentre(HttpServletRequest request,
			HttpServletResponse response, PrintWriter writer, News news) {
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1001");
		List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable");
		Map<Integer, String> code_map = new HashMap<Integer, String>();
		Map<String, String> column_map = new HashMap<String, String>();
		for (Code c : code_list) {
			code_map.put(c.getDmz(), c.getDmsm());
		}
		for (ColumnTable table : col_list) {
			column_map.put(table.getId() + "", table.getColumnName());
		}
		String curPage, pageSize;
		curPage = request.getParameter("curPage");
		pageSize = request.getParameter("pageSize");
		pageSize = "100000";

		Pager pager = new Pager();
		if (StringUtils.isNotBlank(curPage)) {
			pager.setCurPage(Integer.parseInt(curPage));
		}
		if (StringUtils.isNotBlank(pageSize)) {
			pager.setPageSize(Integer.parseInt(pageSize));
		}
		List<News> news_list = newsService.findByHQLQuery(news, pager).getList();
		return new ModelAndView("news/audit_centre")
				.addObject("news_list", news_list)
				.addObject("code_list", code_list)
				.addObject("code_map", code_map)
				.addObject("col_list", col_list)
				.addObject("column_map", column_map);
	}

	/**
	 * 分页获取我的发布
	 * 
	 * 我的发布
	 * @param request
	 * @param response
	 * @param writer
	 * @param curPage
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getPublishedListByUser")
	public ModelAndView getPublishedListByUser(HttpServletRequest request,HttpServletResponse response, News news) {
		String title=request.getParameter("title");//获取标题
		String zhuanxierenid=request.getParameter("zhuanxierenid");//获取审核人
		String startTime=request.getParameter("startTime");//获取开始时间
		if (StringUtils.isNotBlank(startTime)){
			startTime = StringUtils.trim(startTime.replace("/", "-"));
		}
		String endTime=request.getParameter("endTime");//获取截至时间
		if (StringUtils.isNotBlank(endTime)){
			endTime = StringUtils.trim(endTime.replace("/", "-"));
		}
		List<Code> code_list = codeService.findByHQLQuery("from Code c where c.dmid = 1001");
		List<ColumnTable> col_list = colService.findByHQLQuery("from ColumnTable");
		Map<Integer, String> code_map = new HashMap<Integer, String>();
		Map<String, String> column_map = new HashMap<String, String>();
		for (Code c : code_list) {
			code_map.put(c.getDmz(), c.getDmsm());
		}
		for (ColumnTable table : col_list) {
			column_map.put(table.getId() + "", table.getColumnName());
		}
		User user = WebConstants.getUser(request);
		String userid = request.getSession().getAttribute("userId").toString();
		String sql=null;
		if(StringUtils.isNotBlank(title)&&StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)&&StringUtils.isNotBlank(zhuanxierenid)){
			sql="from News n where n.fabudate BETWEEN '"+startTime+"' AND '"+endTime+"' and n.userid='"+zhuanxierenid+"' and n.title LIKE '%"+title+"%'";
		}else {
			sql="from News where userid=" + userid;
		}
		List<News> news_list = newsService.findByHQLQuery(sql);
		return new ModelAndView("news/my_published")
				.addObject("news_list", news_list)
				.addObject("code_list", code_list)
				.addObject("code_map", code_map)
				.addObject("col_list", col_list)
				.addObject("column_map", column_map);
	}
	/**
	 * 条件查询获取我的发布
	 * @param request
	 * @param response
	 * @param news
	 */
	@RequestMapping(value = "/findPublishedList")
	public void findPublishedList(HttpServletRequest request,HttpServletResponse response, PrintWriter writer, News news){
		Map<String, Object> resultMap=new HashMap<String,Object>();
		String title=request.getParameter("title");//获取标题
		String status=request.getParameter("status");//获取状态
		String type=request.getParameter("type");//获取类型
		String zhuanxierenid=request.getParameter("zhuanxierenid");//获取审核人
		String startTime=request.getParameter("startTime");//获取开始时间
		String endTime=request.getParameter("endTime");//获取截至时间
		String sql=null;
		if(StringUtils.isNotBlank(title)&&StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)&&StringUtils.isNotBlank(zhuanxierenid)){
			sql="from News n where n.fabudate BETWEEN str_to_date('"+startTime+"','%Y-%m-%d') AND str_to_date('"+endTime+"','%Y-%m-%d') and n.userid='"+zhuanxierenid+"' and n.title='"+title+"'";
		}else if(StringUtils.isNotBlank(title)&&StringUtils.isNotBlank(status)&&StringUtils.isNotBlank(type)&&StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
			sql="from News n where n.fabudate BETWEEN str_to_date('"+startTime+"','%Y-%m-%d') AND str_to_date('"+endTime+"','%Y-%m-%d') and n.title='"+title+"' and n.status='"+status+"' and n.type='"+type+"'";
		}
		List<News> list_news=newsService.findByHQLQuery(sql);
		if(list_news!=null){
			resultMap.put("success", list_news);
		}else{
			resultMap.put("msg", "false");
		}
		writer.write(JsonUtil.jsonObject(resultMap, null, null));
		writer.flush();
		writer.close();
	}

	/**
	 * 单条审批新闻
	 * 
	 * @param request
	 * @param response
	 * @param writer
	 * @param news
	 */
	@SystemControllerLog(description = "审批新闻,发布或者退回")
	@RequestMapping(value = "/reviewNews")
	public void reviewNews(HttpServletRequest request, HttpServletResponse response, PrintWriter writer, News news) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		int status = news.getStatus();
		int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		User user = userService.findById(userid);
		news = newsService.findById(news.getId());
		if (news != null) {
			Messagevo message=new Messagevo();
			message.setMessageStatus(0);
			message.setUserid(news.getUserid());
			message.setShijian(new Date());
			message.setStatus(news.getStatus());
			message.setShenherenid(news.getShenheid());
			message.setNewsid(news.getId());
			boolean bool = false;
			if(status==News_status.REVIEW_BACK.status()){
				message.setMessage(user.getUserName()+"退回了"+news.getTitle());
				String tuihuiyuanyin=request.getParameter("tuihuiyuanyin");
				news.setTuihuiyuanyin(tuihuiyuanyin);
			}else if(status==News_status.PUBLISHED.status()){
				message.setMessage(user.getUserName()+"发布了"+news.getTitle());
			}
			bool = messageService.saveOrUpdate(message);
			news.setStatus(status);
			boolean b = false;
			if(bool){
				b = newsService.saveOrUpdate(news);
			}
			result_map.put("success", bool);
			result_map.put("news publis", b);
		}
		writer.write(JsonUtil.jsonObject(result_map, null, null));
		writer.flush();
		writer.close();
	}

	/**
	 * @descrption 批量审批新闻
	 * @param request
	 * @param response
	 * @param writer
	 * @param ids
	 *            待审核新闻Id数组
	 */
	@SystemControllerLog(description = "批量审批,发布资讯")
	@RequestMapping(value = "/reviewNewsList")
	public void reviewNewsList(HttpServletRequest request,HttpServletResponse response, PrintWriter printWriter, String[] ids) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		String isPass = request.getParameter("isPass");
		// String[] ids = request.getParameterValues("id");
		int i = 0;
		String idValue = "";
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				News news = new News();
				news.setId(Integer.parseInt(id));
				news = newsService.findById(news.getId());
				if (news != null) {
					news.setStatus(Integer.parseInt(isPass));
					boolean f = newsService.saveOrUpdate(news);
					if (f) {
						i++;
					} else {
						idValue += id + ",";
					}
				}
			}
		}
		result_map.put(i + "", idValue);
		printWriter.write(JsonUtil.jsonObject(result_map, null, null));
		printWriter.flush();
        printWriter.close();
	}

	/**
	 * @description 设置房估估市场解读页面轮播图片,上限3
	 * @param request
	 * @param response
	 * @param writer
	 * @param ids
	 */
	@SystemControllerLog(description = "设置为轮播")
	@RequestMapping(value = "/setScrolling")
	public void setScrolling(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter,News news) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		news.setIflunbo(News_scrolling.SCROLLING.status());
		// 查询已经轮播的图片
		List<News> news_list = newsService.findByHQLQuery(news, new Pager()).getList();
		boolean c = true;
		// 判断轮播图片数量是否达到上限
		if (news_list != null) {
			int f = news_list.size() + 1;
			if (f > 3) {
				result_map.put("success", "false");
				result_map.put("msg", "轮播数量超过上限");
				c = false;
			}
		}
		int id = Integer.parseInt(request.getParameter("id"));
		// 如果没有达到上限,则添加
		if (c) {
			news=newsService.findById(id);
			news.setIflunbo(News_scrolling.SCROLLING.status());
			c = newsService.updateEntity(news);
			if (c) {
				result_map.put("msg", "设置成功");
			}
			result_map.put("success", c);
		}
		printWriter.write(JsonUtil.jsonObject(result_map, null, null));
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * @description 取消轮播
	 * @param request
	 * @param response
	 * @param writer
	 * @param ids
	 */
	@SystemControllerLog(description = "取消轮播")
	@RequestMapping(value = "/cancelScrolling")
	public void cancelScrolling(HttpServletRequest request, HttpServletResponse response, PrintWriter writer,News news) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		int id=Integer.parseInt(request.getParameter("id"));
		boolean bool = true;
		news = newsService.findById(id);
		news.setIflunbo(News_scrolling.NO_SROLLING.status());
		bool = newsService.updateEntity(news);
		if(bool){
			result_map.put("success", bool);
			result_map.put("msg", "已取消");
		}
		writer.write(JsonUtil.jsonObject(result_map, null, null));
		writer.flush();
		writer.close();
	}

}
