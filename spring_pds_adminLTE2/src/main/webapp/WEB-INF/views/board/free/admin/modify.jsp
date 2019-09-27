<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<head>
<title>게시글 수정</title>

<!-- daterange picker -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/daterangepicker/daterangepicker.css">

 <!-- summernote -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
</head>
<body>

	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>게시글 수정</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="regist" name="registForm">

								<input type="hidden" name="bno" value="${board.bno }"/>
								
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="title"
									name='title' placeholder="제목을 쓰세요" value="${board.title }">
							</div>
							<div class="form-group row">
								<label class="col-sm-2 control" for="writer">작성자</label> 
								<input class="col-sm-10 form-control" type="text" id="writer" readonly
										name="writer" value="${board.writer }">
							</div>							
									
							<div class="col-md-12">
								<textarea name="content" id="content"
										placeholder="1000자 내외로 작성하세요.">${board.content }</textarea>
							</div>	
													
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-warning" id="modifyBtn" onclick="onSubmit('${category}',document.registForm,'modify','post');">수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="onCancel();">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->


</body>


