<%@page import="com.spring.utils.MediaUtils"%>
<%@page import="com.spring.utils.FileFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<title>자료 수정</title>  

<!-- daterange picker -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/daterangepicker/daterangepicker.css">

<!-- summernote -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
<style>
	.text-overflow{
		width:120px;
		overflow:hidden; 
		text-overflow:ellipsis; 
		white-space:nowrap;
	}
</style>  
</head>

<body>

	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<h4>자료 수정</h4>
			</div><!--end card-header  -->
			<div class="card-body">
				<form role="form" method="post" action="modify" name="registForm" enctype="multipart/form-data">
					<input type="hidden" name="pno" value="${pds.pno }">
				
					<div class="form-group row" name="title">
						<label class="col-sm-2 control-label" for="title">제 목</label> 
						<input class="col-sm-10 form-control" type="text" id="title"
							name='title' placeholder="제목을 쓰세요" value="${pds.title }">
					</div>						
					<div class="form-group row">
						<label class="col-sm-2 control-label" for="writer">작성자</label> 
						<input class="col-sm-10 form-control" type="text" id="writer" readonly	name="writer" value="${pds.writer }">								
					</div>
					
					<div class="col-md-12">								
						<textArea id="content" name="content"  
							      placeholder="Place some text here">${pds.content }</textArea>					              			             						              
					</div>
					
					<div class="form-group">								
						<div class="box box-success">
							<div class="box-header">
								<h3 style="display:inline;line-height:40px;">첨부파일 : </h3>
								&nbsp;&nbsp;
								<button class="btn btn-primary"	type="button" id="addFileBtn">Add File</button>
							</div>		
							<br/>							
							<div class="box-footer fileInput">
								<div class="attached row">
									<c:forEach items="${pds.attachList }" var="attach" >									
									<div class="col-sm-4 inputRow" style="cursor:pointer;"
											 onclick="location.href='<%=request.getContextPath()%>/attach/get?ano=${attach.ano }';"
											 attach-fileName="${attach.fileName }" attach-no="${attach.ano }"
											 href="<%=request.getContextPath()%>/attach/get?ano=${attach.ano }"
											 name="attachedFile" class="btn btn-social btn-dropbox">
											<div class="info-box uploadedList">
																																		
												<span class="info-box-icon" >
													<c:if test="${empty MediaUtils.getMediaType(attach.fileType) }" >
														<img src="<%=request.getContextPath() %>/resources/images/icon/${FileFormat.generate(attach.fileType)}.png" />
													</c:if>													
													<c:if test="${!empty MediaUtils.getMediaType(attach.fileType) }" >
														<img src="<%=request.getContextPath() %>/attach/thum?ano=${attach.ano }" />
													</c:if>													
												</span>												
												<div class="info-box-content">
													<span class ="info-box-text">
													<fmt:formatDate value="${attach.regDate }" pattern="yyyy-MM-dd" />												
													</span>
													
													<span class ="info-box-number text-overflow">														 
														<i class="fa fa-folder-o"></i>														
															${attach.fileName }																																																				
													</span>													
												</div>
												<button type="button" style="border:0;outline:0;" class="badge bg-red float-right" >X</button>
												
											</div>
										</div>
									</c:forEach>
								</div>	
								<br/>		
							</div>
						</div>
					</div>							
				</form>
			</div><!--end card-body  -->
			<div class="card-footer">
				<button type="button" class="btn btn-primary" id="modifyBtn" >수 정</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-warning" id="cancelBtn" onclick="CloseWindow();">닫 기</button>
			</div><!--end card-footer  -->
		</div><!-- end card -->				
	</div><!-- end col-md-12 -->

<%@ include file="./modify_js.jsp" %>

</body>







