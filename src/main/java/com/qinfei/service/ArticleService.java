package com.qinfei.service;

import java.util.List;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.qinfei.entity.Article;
import com.qinfei.entity.Channel;
import com.qinfei.entity.Comment;
import com.qinfei.entity.Complain;
import com.qinfei.entity.Slide;
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

	/**
	 * 获取热门文章
	 * @param page
	 * @return
	 */
	PageInfo<Article> hotList(int page);

	/**
	 * 获取最新文章NAG
	 * @return
	 */
	
	List<Article> lastList();

	/**
	 * 获取轮播图
	 * @return
	 */
	List<Slide> getSlides();

	/**
	 * 获取栏目下的文章
	 * @param channleId
	 * @param catId
	 * @param page
	 * @return
	 */
	PageInfo<Article> getArtcles(int channleId, int catId, int page);

	/**
	 * 获取栏目下的分类
	 * @param channleId
	 * @return
	 */
	List<category> getCategoriesByChannelId(int channleId);

	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	int addComment(Comment comment);

	/**
	 * 根绝文章id获取评论
	 * @param id
	 * @param page
	 * @return
	 */
	PageInfo<Comment> getComments(int articleid, int page);

	int addComplian(Complain complain);

}
