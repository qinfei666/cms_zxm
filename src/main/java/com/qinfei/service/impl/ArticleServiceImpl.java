package com.qinfei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinfei.common.Contantant;
import com.qinfei.dao.ArticleMapper;
import com.qinfei.entity.Article;
import com.qinfei.entity.Channel;
import com.qinfei.entity.category;
import com.qinfei.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;
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
}
