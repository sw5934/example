<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<head>
<title>공지사항 수정</title>

<!-- daterange picker -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/daterangepicker/daterangepicker.css">

 <!-- summernote -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
</head>
<body>

	<div class="col-md-12">
				<div class="card ">
					<div class="card-header">
						<h4>공지 수정</h4>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="regist" name="registForm">

								<input type="hidden" name="nno" value="${notice.nno }"/>
								<input type="hidden" name="startDate" value="<fmt:formatDate value='${notice.startDate }' pattern='yyyy-MM-dd'/>"/> 
								<input type="hidden" name="endDate" value="<fmt:formatDate value='${notice.endDate }' pattern='yyyy-MM-dd'/>"/>
								
							<div class="form-group row">
								<label class="col-sm-2 control-label" for="title">제 목</label> 
								<input class="col-sm-10 form-control" type="text" id="title"
									name='title' placeholder="제목을 쓰세요" value="${notice.title }">
							</div>
							<div class="form-group row">
								<label class="col-sm-2 control-label">게시기간</label>
								<input class="col-sm-10 form-control" type="text" id="reservation"/>					
							</div>
							<div class="form-group row">
										<label class="col-sm-2 control" for="writer">작성자</label> 
										<input class="col-sm-10 form-control" type="text" id="writer" readonly
											name="id" value="${notice.id }">
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
									
							<div class="col-md-12">
								<textarea name="content" id="content"
										placeholder="1000자 내외로 작성하세요.">${notice.content }</textarea>
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

<script>

var setReservationTime = function(){
		//alert($(this).val());
		var date = $('input#reservation').val().split("-");
		$('input[name="startDate"]').val(date[0].replace(/\//gi,"-").trim());
		$('input[name="endDate"]').val(date[1].replace(/\//gi,"-").trim());
		
	}

</script>			
		
<script>
window.onload=function(){
	//Date range Picker
	$('#reservation').daterangepicker({
		startDate: moment(${notice.startDate.time}),
		endDate  : moment(${notice.endDate.time}),
		locale: {
			format: 'YYYY/MM/DD'
		}
	});
	
	$('#reservation').on("change",setReservationTime);
	
}
</script>

</body>


