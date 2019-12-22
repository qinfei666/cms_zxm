package com.qinfei.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qinfei.entity.Article;
import com.qinfei.entity.Channel;
import com.qinfei.entity.category;

public interface ArticleService {

	/**
	 * 根据用户列出文章
	 * @param id
	 * @param pageNum
	 * @return
	 */
	PageInfo<Article> listByUser(Integer id, int pageNum);

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	int delArtice(int id);

	/**
	 * 获取所有的栏目
	 * @return
	 */
	List<Channel> getChannel();
	
	List<category>getCategoryisByCid(int cid);

	Article getById(int id);
	/*
	 * 获取文章的简要信息,判断文章的存在性
	 */
	Article getInfoById(int id);
	/**
	 * 发布文章
	 * @param article
	 */
	int add(Article article);
	/**
	 * 
	 * @param article
	 * @param id
	 * @return
	 */
	int updated(Article article, Integer id);

	/**
	 * 获取文章列表
	 * @param status
	 * @param pageNum
	 * @return
	 */
	PageInfo<Article> list(int status, int pageNum);

	int setHot(int id, int status);

	int setCheckStatus(int id, int status);

}
