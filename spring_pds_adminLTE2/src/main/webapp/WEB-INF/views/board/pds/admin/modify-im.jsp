<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<title>게시글 등록</title>

<!-- daterange picker -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/daterangepicker/daterangepicker.css">

 <!-- summernote -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
</head>
<body>

	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>게시글 등록</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify" name="registForm" encType="multipart/form-data">
						
								
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
									
							<div class="col-md-12">
								<textarea name="content" id="content"
										placeholder="1000자 내외로 작성하세요.">${pds.content }</textarea>
							</div>	
							
							<div class="form-group">
								<div class="box box-success">
									<div class="box-header">
										<h3 style="display:inline;line-height:40px;">첨부파일 : </h3>
										&nbsp;&nbsp;
										<button class="btn btn-primary" type="button" id="addFileBtn">Add File</button>
									</div>
									<div class="box-footer fileInput">
										<div class="attached">
											<c:forEach items="${pds.attachList }" var="attach" >										
												<a attach-fileName="${attach.fileName }" attach-no="${attach.ano }"
												 href="<%=request.getContextPath()%>/attach/get?ano=${attach.ano }"
												 name="attachedFile" class="btn btn-social btn-dropbox">
													<i class="fa fa-folder-o"></i>
														${attach.fileName }&nbsp;&nbsp;
													<button type="button" style="border:0;outline:0;" class="badge bg-red">X</button>											
												</a>
											</c:forEach>
										</div>	
										<br/>		
									</div>
								</div>
							</div>
							
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="registBtn">등 록</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-warning" id="cancelBtn" onclick="CloseWindow()">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
<%@include file="./regist_js.jsp" %>
</body>


