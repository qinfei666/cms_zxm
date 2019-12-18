package com.qinfei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qinfei.entity.Article;
import com.qinfei.entity.Channel;
import com.qinfei.entity.category;

public interface ArticleMapper {

	
	List<Article> listByUser(Integer id);

	
	@Update("update cms_article set deleted=#{status} where id=#{id}")
	int updateStatus(@Param("id")int id, @Param("status")int status);

	/**
	 * 获取所有栏目的方法
	 * @return
	 */
	@Select("select id,name from cms_channel")
	List<Channel> getAllChannel();
	/**
	 * 获取所有栏目的方法
	 * @return
	 */
	@Select("select id,name from cms_category where channel_id=#{value}")
	List<category> getCategoryisByCid(int cid);


	Article getById(int id);

}
