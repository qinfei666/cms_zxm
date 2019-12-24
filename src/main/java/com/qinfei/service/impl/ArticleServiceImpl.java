package com.qinfei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinfei.common.Contantant;
import com.qinfei.dao.ArticleMapper;
import com.qinfei.dao.SlideMapper;
import com.qinfei.entity.Article;
import com.qinfei.entity.Channel;
import com.qinfei.entity.Comment;
import com.qinfei.entity.Complain;
import com.qinfei.entity.Slide;
import com.qinfei.entity.category;
import com.qinfei.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	SlideMapper slideMapper;
	@Override
	public PageInfo<Article> listByUser(Integer id, int pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, Contantant.PAGE_SIZE);
		
		PageInfo<Article> pageInfo = new PageInfo<Article>(articleMapper.listByUser(id)); 
		return pageInfo;
	}
	@Override
	public int delArtice(int id) {
		// TODO Auto-generated method stub
		
		return articleMapper.updateStatus(id,Contantant.ARTICLE_STATUS_DEL);
	}

	@Override
	public List<Channel> getChannel() {
		// TODO Auto-generated method stub
		return articleMapper.getAllChannel();
	}
	
	@Override
	public List<category> getCategoryisByCid(int cid) {
		// TODO Auto-generated method stub
		return articleMapper.getCategoryisByCid(cid);
	}
	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return articleMapper.getById(id);
	}
	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.add(article);
	}
	@Override
	public int updated(Article article, Integer userId) {
		// TODO Auto-generated method stub
		Article articleSrc = this.getById(article.getId());
		if (articleSrc.getUserId()!=userId) {
			//todo 如果不是自己的文章  需要.....
		}
		
		return articleMapper.update(article);
	}
	@Override
	public PageInfo<Article> list(int status, int pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, Contantant.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.list(status));
	}
	
	@Override
	public Article getInfoById(int id) {
		// TODO Auto-generated method stub
		return articleMapper.getInfoById(id);
	}
	
	@Override
	public int setCheckStatus(int id, int status) {
		// TODO Auto-generated method stub
		return articleMapper.setCheckStatus(id,status);
	}
	
	@Override
	public int setHot(int id, int status) {
		// TODO Auto-generated method stub
		return articleMapper.setHot(id,status);
	}
	@Override
	public List<Slide> getSlides() {
		// TODO Auto-generated method stub
		return slideMapper.list();
	}
	@Override
	public PageInfo<Article> hotList(int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, Contantant.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.hotList());
	}
	@Override
	public List<Article> lastList() {
		// TODO Auto-generated method stub
		return articleMapper.lastList(Contantant.PAGE_SIZE);
	}
	@Override
	public PageInfo<Article> getArtcles(int channleId, int catId, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, Contantant.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.getArtcles(channleId,catId));
	}
	@Override
	public List<category> getCategoriesByChannelId(int channleId) {
		// TODO Auto-generated method stub
		return articleMapper.getCategoriesByChannelId(channleId);
	}
	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		int result = articleMapper.addComment(comment);
		if (result>0) 
			 articleMapper.increaseCommentCnt(comment.getArticleId());
		return result;
	}
	@Override
	public PageInfo<Comment> getComments(int articleid, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, Contantant.PAGE_SIZE);
		return new PageInfo<Comment>(articleMapper.getComments(articleid));
	}
	@Override
	public int addComplian(Complain complain) {
		// TODO Auto-generated method stub
		int result = articleMapper.addCoplain(complain);
		if (result>0) {
			articleMapper.increaseComplainCnt(complain.getArticleId());
		}
		return 0;
	}
}
