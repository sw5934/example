<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>page1</title>
</head>
<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
	  <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">공지사항</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">notice</a></li>
              <li class="breadcrumb-item active">list</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

		<!-- Main content -->
		<div class="content">
			<div class="container-fluid">
				<div class="row" style="position: relative">
					<iframe id="if_list" name="if_list" frameborder="0" scrolling="0"
						src="<%=request.getContextPath()%>/board/notice/admin/listSearch"
						style="position: absolute; top: 0px; left: 0px; bottom: 0px; height: 744px; width: 100%; border: 0px;">
					</iframe>
				</div>
			</div>
		</div>
	</div>
</body>