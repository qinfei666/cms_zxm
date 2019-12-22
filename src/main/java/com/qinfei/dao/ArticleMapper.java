package com.qinfei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
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

	@Insert("INSERT INTO cms_article(title,content,picture,channel_id,category_id,user_id,hits,hot,status,deleted,created,updated,commentCnt,articleType)"
			+ " VALUES(#{title},#{content},#{picture},#{channelId},#{categoryId},#{userId},0,0,0,0,now(),now(),0,#{articleType})")
	int add(Article article);

	@Update("UPDATE cms_article SET title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
			+ " category_id=#{categoryId},status=0,"
			+ "updated=now() WHERE id=#{id} ")
	int update(Article article);




	/**
	 * 文章列表
	 * @param status
	 */
	List<Article> list(int status);


	/**
	 * 
	 */
	@Select("SELECT id,title,channel_id channelId , category_id categoryId,status ,hot "
			+ " FROM cms_article WHERE id = #{value} ")
	Article getInfoById(int id);

	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("UPDATE cms_article SET hot=#{hot} where id=#{id}")
	int setHot(@Param("id")int id, @Param("hot")int status);

	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("UPDATE cms_article SET status=#{status} where id=#{id}")
	int setCheckStatus(@Param("id")int id, @Param("status")int status);

}
