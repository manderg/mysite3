<%@page import="java.io.Writer"%><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<style>
#left {text-align: left}

</style>
<script>

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board/" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="radio" name="category" value="title">제목
					<input type="radio" name="category" value="b.name">작성자
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${l}' />				
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>							
							<td>${count-status.index }</td>
							<td id="left">
								<c:forEach  begin="1" end="${vo.lvl}">
									<img src="${pageContext.request.contextPath}/assets/images/ico-reply.gif">
								</c:forEach>
								<a href="${pageContext.request.contextPath}/board/view?no=${vo.no}">${vo.title }</a>
							</td>
							<td>${vo.memberName }</td>
							<td>${vo.viewCount }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:choose>							
									<c:when test='${authUser.no == vo.memberNo }'>									
										<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no }" class="del">삭제</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
								</c:choose>										
							</td>
						</tr>
					</c:forEach>				
				</table><br>
				<table class="tbl-page">
					<tr>
					<c:forEach var="i" begin="1" end="${length}" >
					
						<th><a style="text-decoration:none"
						 href="${pageContext.request.contextPath}/board/?page=${i}&category=${category}&kwd=${kwd}">&nbsp;&nbsp;${i}&nbsp;&nbsp;</a></th>
					</c:forEach>	
				</table>	
					
				<div class="bottom">
					<c:if test='${not empty authUser }'>
						<a href="${pageContext.request.contextPath}/board/write" id="new-book">글쓰기</a>
					</c:if>
				</div>							
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>