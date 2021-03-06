<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


修改文章
<form name="articleform" id="articlefrom">
<input type="hidden" name="id" value="${article.id }">
  <div class="form-group">
    <label for="title">标题</label>
    <input type="text" class="form-control" id="title" name="title" value="${article.title }" placeholder="请输入文章标题">
  </div>
  
  <div class="form-group">
    <label for="channel">栏目</label>
    <select class="form-control" id="channel" name="channelId">
      <option value="0">--请选择--</option>
      <c:forEach items="${channels }" var="cat">
      		<!-- 下拉框回显 -->
      		<option value="${cat.id }" ${article.channelId==cat.id?"selected":"" }>${cat.name }</option>
      </c:forEach>
    </select>
  </div>
   <div class="form-group">
    <label for="category">分类</label>
    <select class="form-control" id="category" name="categoryId">
      
    </select>
  </div>
  
  <div class="form-group">
    <label for="exampleFormControlFile1">文章图片</label>
    <input type="file" class="form-control-file" id="file" name="file">
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">文章内容</label>
    <textarea class="form-control" id="contentId" name="content1" rows="20" cols="300">${content1 }</textarea>
  </div>
  <div class="form-group">
  <input type="button" class="btn btn-primary mb-2" value="提交" onclick="commtiArticle()"/>
  </div>
</form>
<script type="text/javascript">
function channelChange(){

	console.log("选中的数据是:"+$("#channel").val())
	$.post("/user/getCategoris",{cid:$("#channel").val()},function(data){
		$("#category").empty();
		for ( var i in data) {
			if(data[i].id=='${article.categoryId}'){
				$("#category").append("<option selected value='"+data[i].id+"'>"+data[i].name+"</option>")
			}else{
				
				$("#category").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
			}
		}
	},"json")
}
$("#channel").change(function(){
	channelChange();
})

  $(document).ready( function(){
	  channelChange();
		KindEditor.ready(function(K) {
			//    textarea[name="content1"]
			editor = K.create('#contentId', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			//uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			uploadJson:'/file/upload', // 
			fileManagerJson:'/file/manager',
			//fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
      
	 }); 
		
		function query(){
			alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		} 
		//提交文章的修改
		function commtiArticle() {
			alert(editor.html())
			var formdata = new FormData($("#articlefrom")[0])
			formdata.set("content",editor.html())
			$.ajax({
				url:"updateArticle",
				dataType:"json",
				processData:false,
				contentType:false,
				data:formdata,
				type:"post",
				success:function(data){
					//$("#workcontent").load("/user/articles")
					showWork($("#postLink"),"/user/articles")
				}
			})
		}


</script>