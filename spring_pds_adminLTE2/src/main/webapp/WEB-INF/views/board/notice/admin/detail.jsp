<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
<title>공지사항 상세보기</title>
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
									name='title' placeholder="제목을 쓰세요" value="${notice.title }">
							</div>
							<div class="form-group row">
								<label class="col-sm-2 control-label">게시기간</label>
								<input class="col-sm-10 form-control" type="text" 
									value="
										<fmt:formatDate value="${notice.startDate }" pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${notice.endDate }" pattern="yyyy-MM-dd"/>
										"/>					
							</div>
							<div class="form-group row">
										<label class="col-sm-2 control" for="writer">작성자</label> 
										<input class="col-sm-10 form-control" type="text" id="writer" readonly
											name="id" value="${notice.id}">
									</div>
									<div class="form-group row">
										<label class="col-sm-2">중요도</label>
										<div class="col-sm-10 row">
											<div class="col-sm-2">
												<label for="ge">일반</label>
												<input type="radio" id="ge" name="imp" value="0" ${notice.imp eq 0 ? 'checked' : '' } />
											</div>
											<div class="col-sm-6">
												<label for="im">중요</label>
												<input type="radio" id="ge" name="imp" value="1" ${notice.imp eq 1 ? 'checked' : '' }/>
											</div>
										</div>
									</div>									
									
							<div class="col-md-12 card">
								<div class="card-body" id="contentDiv" >${notice.content }</div>
							</div>														
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-warning" id="modifyBtn" onclick="onModify();">수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-danger" id="deleteBtn" onclick="onRemove();">삭 제</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-secondary" id="closeBtn" onclick="CloseWindow();">닫 기</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->

<script>
	function onModify(){
		self.location.href="<%=request.getContextPath() %>/board/notice/admin/modify/${notice.nno}";
	}
	function onRemove(){
		self.location.href="<%=request.getContextPath() %>/board/notice/admin/delete/${notice.nno}";				
	}
	window.onload=function(){
		$('input').attr('readonly',true);
		$('#contentDiv').attr("contentEditable",false);
		$(':radio:not(:checked)').attr('disabled',true);
	}
</script>			
</body>


