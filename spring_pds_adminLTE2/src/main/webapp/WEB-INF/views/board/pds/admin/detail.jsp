<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.spring.utils.MediaUtils" %>
<%@ page import="com.spring.utils.FileFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>자료 상세보기</title>
<style>
	.text-overflow{
		width:100px;
		overflow:hidden;
		text-overflow:ellipsis;
		white-space:nowrap
	}
</style>
</head>
<body>

	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>상세보기</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="regist" name="registForm">
								
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="title"
									name='title' placeholder="제목을 쓰세요" value="${pds.title }">
							</div>							
							<div class="form-group row">
										<label class="col-sm-2 control" for="writer">작성자</label> 
										<input class="col-sm-10 form-control" type="text" id="writer" readonly
											name="writer" value="${pds.writer}">
									</div>																
									
							<div class="col-md-12 card">
								<div class="card-body" id="contentDiv" >${pds.content }</div>
							</div>														
						</form>
					</div><!--end card-body  -->
					<div class="form-group col-sm-12">
								<div class="box box-success">
									<div class="box-header">
										<h3>첨부파일</h3>
									</div>			
									<div class="box-footer row">
										<c:forEach items="${pds.attachList }" var="attach">
										<div class="col-md-4 col-sm-6 col-xs-12" 
											 onclick="location.href='<%=request.getContextPath()%>/attach/get?ano=${attach.ano }';" >
											<div class="info-box uploadList "style="cursor:pointer; height:75px;">	
												<span class="info-box-icon bg-gray">
													<c:if test="${empty MediaUtils.getMediaType(attach.fileType) }" >
														<img src="<%=request.getContextPath()%>/resources/images/icon/${attach.fileType}.png">
													</c:if>
																										
													<c:if test="${!empty MediaUtils.getMediaType(attach.fileType) }" >
														<img src="<%=request.getContextPath()%>/attach/thum?ano=${attach.ano}">
													</c:if>
													
												</span>
												<div class="info-box-content">
													<span class ="info-box-text">
													<fmt:formatDate value="${attach.regDate }" pattern="yyyy-MM-dd" />												
													</span>
													<span class ="info-box-number text-overflow">${attach.fileName }</span>
												</div>
											</div>
										</div>
										</c:forEach>
									</div>				
								</div>
							</div>	
					
					<div class="card-footer">
						<button type="button" class="btn btn-warning" id="modifyBtn" onclick="onModify('${category}','${pds.pno}');">수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-danger" id="deleteBtn" onclick="onRemove('${category}','${pds.pno}');">삭 제</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-secondary" id="closeBtn" onclick="CloseWindow();">닫 기</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->
			</div><!-- end col-md-12 -->




<script>
	function onModify(category,boardId){
		self.location.href="<%=request.getContextPath() %>/board/"+category+"/admin/modify?pno="+boardId;
	}
	function onRemove(category,boardId){
		self.location.href="<%=request.getContextPath() %>/board/"+category+"/admin/remove?pno="+boardId;				
	}
	window.onload=function(){
		$('form[name="registForm"] input').attr('readonly','readonly');
		$('form[name="registForm"] #contentDiv').attr("contentEditable",false);
	}
	

</script>	
		
</body>


