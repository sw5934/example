<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
<title>자유게시판 상세보기</title>
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
									name='title' placeholder="제목을 쓰세요" value="${board.title }">
							</div>							
							<div class="form-group row">
										<label class="col-sm-2 control" for="writer">작성자</label> 
										<input class="col-sm-10 form-control" type="text" id="writer" readonly
											name="id" value="${board.writer}">
									</div>																
									
							<div class="col-md-12 card">
								<div class="card-body" id="contentDiv" >${board.content }</div>
							</div>														
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-warning" id="modifyBtn" onclick='onModify("${category}","${board.bno}");'>수 정</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-danger" id="deleteBtn" onclick='onRemove("${category}","${board.bno}");'>삭 제</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-secondary" id="closeBtn" onclick="CloseWindow();">닫 기</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->
				<div class="card">
					<div class="card-header">
						<div class="box box-success">
							<div class="box-header">
								<h3 class="box-title">
									ADD NEW REPLY
								</h3>
							</div>
							<div class="box-body">
								<input class="form-control" type="hidden"
										id="newReplyWriter" readonly value="${loginUser.id}">
								<label for="newReplyText">Reply Text</label>
								<input class="form-control" type="text" placeholder="Reply Text"
										id="newReplyText">
							</div>
							<div class="box-footer">
								</br>
								<button type="button" class="btn btn-primary"
										id="replyAddBtn">ADD REPLY</button>
							</div>
						</div>
					</div>
					<div class="card-body">
						<!-- The time line -->
						<ul class="timeline color-pallete-set">
							<!-- timeline time label -->
							<li class="bg-green color-pallete row" id="repliesDiv">
								<span class="col-sm-12">Replies List</span>
							</li>
							
						</ul>
					</div>
					<div class="card-footer">
						<div class="text-center">
							<ul id="pagination" class="pagination pagination-sm no-margin">
							
							</ul>
						</div>
					</div>	
				</div>				
			</div><!-- end col-md-12 -->

	<div id="modifyModal" class="modal modal-default fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" style="display:none;"></h4>
					<button type="button" class="close" data-dismiss="modal">&times</button>
				</div>
				<div class="modal-body" data-rno>
					<p><input type="text" id="replyText" class="form-control"></p>				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
					<button type="button" class="btn btn-danger" id="replyDelBtn">Delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>



<script>
	function onModify(category,boardId){
		self.location.href="<%=request.getContextPath() %>/board/"+category+"/admin/modify?bno="+boardId;
	}
	function onRemove(category,boardId){
		self.location.href="<%=request.getContextPath() %>/board/"+category+"/admin/remove?bno="+boardId;				
	}
	window.onload=function(){
		$('form[name="registForm"] input').attr('readonly','readonly');
		$('form[name="registForm"] #contentDiv').attr("contentEditable",false);
	}
</script>	

<%@ include file="./detail_js.jsp" %>		
</body>


