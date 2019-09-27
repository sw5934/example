<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="pdsList" value="${dataMap.pdsList }"/>
<c:set var="pageMaker" value="${dataMap.pageMaker }"/>

<head>
<title>자료실 리스트</title>

</head>
<body> 
		<!-- Main content -->
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="card card-default">
							<div class="card-header">
								<!-- 상단 글등록 버튼 -->
								<button id="newBtn" class="btn btn-primary" onclick="OpenWindow('regist','',600,400);">자료 등록</button>
		
								<!-- 검색바  -->
									<div class="float-right">
										<div class="row">
											<select class="form-control col-sm-4" name="searchType" id="searchType">
												<option value="tcw" ${pageMaker.cri.searchType eq 'tcw' ? 'selected':'' }>전 체</option>
												<option value="t" ${pageMaker.cri.searchType eq 't' ? 'selected':'' }>제 목</option>
												<option value="w" ${pageMaker.cri.searchType eq 'w' ? 'selected':'' }>작성자</option>
												<option value="c" ${pageMaker.cri.searchType eq 'c' ? 'selected':'' }>내 용</option>
												<option value="tc" ${pageMaker.cri.searchType eq 'tc' ? 'selected':'' }>제목+내용</option>
												<option value="wc" ${pageMaker.cri.searchType eq 'cw' ? 'selected':'' }>작성자+내용</option>
											</select>
										<input class="form-control col-sm-6" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword }"/>
										<span class="input-group-btn col-sm-2">
											<button class="btn btn-info" type="button" id="searchBtn">
												<i class="fa fa-fw fa-search"></i>
											</button>
										</span>
									</div>
								</div>								
							</div>
							<div class="card-body">
								<table class="table table-bordered" id="boardTable">
									<tr>
										<th style="text-align: center; width: 80px;">번 호</th>
										<th style="text-align: center;">제 목</th>
										<th style="text-align: center; width: 150px;">작성자</th>
										<th style="text-align: center; width: 150px;">등록일</th>
										<th style="text-align: center; width: 100px;">첨부</th>
										<th style="text-align: center; width: 100px;">조회수</th>
									</tr>
									<c:if test="${empty pdsList }">
										<tr>
											<td colspan="5" class="text-center"><strong>해당 내용이 없습니다.</strong></td>
										</tr>
									</c:if>
									<c:if test="${!empty pdsList }">
										<c:forEach items="${pdsList }" var="pds">
											<tr>
												<td class="text-center">${pds.pno }</td>
												<td>
													<a href="#" data-name="title" onclick="OpenWindow('detail?pno=${pds.pno }')">
														${pds.title }
														
														<%--c:if test="${board.replycnt > 0 }">
														--(${board.replycnt })
														</c:if>  --%>
													</a>
												</td>
												<td class="text-center">${pds.writer }</td>
												<td class="text-center">
													<fmt:formatDate value="${pds.regDate }" pattern="yyyy-MM-dd" /></td>
												<td class="text-center">
													<c:if test="${!empty pds.attachList}">
														<i class="ace-icon fa fa-paperclip" title="첨부있음"></i>
													</c:if></td>
												<td class="text-center">${pds.viewCnt }</td>
											</tr>
										</c:forEach>
									</c:if>
								</table>
							</div>
							<div class="card-footer">
								<div class="text=center">
									<ul class="pagination">
										<li class="page-item">
											<a class="page-link" href="list${pageMaker.makeQuery(1) }">&lt;&lt;</a></li>
										
										<!-- 10페이지 앞으로 가기 -->
										<li class="page-item">
											<a class="page-link" href="list
												<c:if test="${pageMaker.prev}">
													${pageMaker.makeQuery(pageMaker.startPage-1) }
												</c:if>
											">&lt;</a>
										</li>
										<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
										
										<!-- 현재 페이지 번호표시 -->
										<li class="page-item
											<c:out value="${pageMaker.cri.page == pageNum?'active':'' }"/>">
											<a class="page-link" href="list${pageMaker.makeQuery(pageNum) }">
												${pageNum }
											</a>
										</li>
										</c:forEach>
										
										<!-- 10페이지 뒤로 넘기기 -->
										<li class="page-item">
											<a class="page-link" href= "list
												<c:if test="${pageMaker.next }">
													${pageMaker.makeQuery(pageMaker.endPage+1) }
												</c:if>
												<c:if test="${!pageMaker.next }">
													${pageMaker.makeQuery(pageMaker.cri.page) }
												</c:if>	
												">&gt;</a>
										</li>
										
										<!-- 페이지 맨 뒤로 가기 -->
										<li class="page-item">
											<a class="page-link" href="list${pageMaker.makeQuery(pageMaker.realEndPage) }">
												&gt;&gt;
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
<script>
	window.onload=function(){
		$('a[data-name="title"]').on('click',function(event){
			event.preventDefault();
		});
	}
</script>		
</body>


