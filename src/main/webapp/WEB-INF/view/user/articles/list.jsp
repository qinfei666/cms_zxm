<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <div>
 	<table class="table">
 			
  		 <thead>
          <tr>
            <th>id</th>
            <th>标题</th>
            <th>栏目</th>
            <th>分类</th>
            <th>发布时间</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
       </thead>
       <tbody>
       		<c:forEach items="${page.list }" var="demo">
				<tr>
					<td>${demo.id }</td>				
					<td>${demo.title }</td>				
					<td>${demo.channel.name }</td>				
					<td>${demo.category.name }</td>				
					<td>
						<fmt:formatDate value="${demo.created }" pattern="yyyy年MM月dd日"/>
					</td>				
					<td>
						<c:choose>
							<c:when test="${demo.status==0 }">待审核</c:when>
							<c:when test="${demo.status==1 }">审核通过</c:when>
							<c:when test="${demo.status==2 }">审核杯具</c:when>
							<c:otherwise>
								未知
							</c:otherwise>
						</c:choose>
					</td>				
					<td>
						<input type="button" value="删除" class="btn btn-danger" onclick="del(${demo.id})">
						<input type="button" value="修改" class="btn btn-warning" onclick="upd(${demo.id})">
					</td>				
				</tr>       		
       		</c:forEach>
  		</tbody>
  		
 	</table>
 	<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach begin="1" end="${page.pages}" varStatus="count">
    <li class="page-item"><a class="page-link" href="#" onclick="gopage(${count.index })">${count.index }</a></li>
    </c:forEach>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
 </div>
 <script type="text/javascript">
function del(id) {
	if (confirm("你确定要删除吗?")) {
		$.post("/user/delArtice",{id:id},function(obj){
			if(obj==true){
				alert("删除成功")
				$("#workcontent").load("/user/articles");
			}else{
				alert("删除失败")
			}
		},"json")
	}
	
}
function gopage(page) {
	$("#workcontent").load("/user/articles?pageNum="+page)
}
function upd(id) {
	$("#workcontent").load("/user/updateArticle?id="+id);
}
</script>
   
    