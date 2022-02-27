<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/general/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Video</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/fontawesome-free/css/all.min.css'/>">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css'/>">
<!-- iCheck -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css'/>">
<!-- JQVMap -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/jqvmap/jqvmap.min.css'/>">
<!-- Theme style -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/dist/css/adminlte.min.css'/>">
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/overlayScrollbars/css/OverlayScrollbars.min.css'/>">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/daterangepicker/daterangepicker.css'/>">
<!-- summernote -->
<link rel="stylesheet"
	href="<c:url value='/views/admin/plugins/summernote/summernote-bs4.min.css'/>">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake"
				src="<c:url value='/templates/admin/dist/img/AdminLTELogo.png'/>"
				alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="index3.html" class="brand-link"> <img
				src="<c:url value='/views/admin/dist/img/AdminLTELogo.png'/>"
				alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
				style="opacity: .8"> <span
				class="brand-text font-weight-light">Admin G5-Movie</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img
							src="<c:url value='/views/admin/dist/img/user3-128x128.jpg'/>"
							class="img-circle elevation-2" alt="User Image">
					</div>
					<div class="info">
						<a href="#" class="d-block">${sessionScope.currentUser.username}</a>
					</div>
				</div>

				<!-- SidebarSearch Form -->
				<div class="form-inline">
					<div class="input-group" data-widget="sidebar-search">
						<input class="form-control form-control-sidebar" type="search"
							placeholder="Search" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-sidebar">
								<i class="fas fa-search fa-fw"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item menu-open"><a
							href="#"
							class="nav-link active"> <i class="nav-icon fas fa-copy"></i>
								<p>
									Videos <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a
									href="<c:url value='/admin/video?action=view'/>"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Manager</p>
								</a></li>
								<li class="nav-item"><a
									href="<c:url value='/admin/video?action=add'/>"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>Edit</p>
								</a></li>
							</ul></li>
						<li class="nav-item menu-open"><a href="#"
							class="nav-link active"> <i
								class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									User <i class="right fas fa-angle-left"></i>
								</p>
						</a> <c:url var="url" value="/admin" />
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="${url}/user-overview.jsp"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>Manage</p>
								</a></li>
								<li class="nav-item"><a href="${url}/user-edit.jsp"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Edit</p>
								</a></li>
							</ul></li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Add/Edit Video</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Add/Edit Video</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">Add/Edit Video</h3>

									<div class="card-tools">
										<button type="button" class="btn btn-tool"
											data-card-widget="collapse" title="Collapse">
											<i class="fas fa-minus"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<div class="form-group">
										<label for="title">Title</label> <input type="text" id="title"
											value="" class="form-control">
									</div>
									<div class="form-group">
										<label for="href">Href</label> <input type="text" id="href"
											value="" class="form-control">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-9">
												<div class="row">
													<div class="col-12 form-group">
														<label for="description">Description</label>
														<textarea id="description" class="form-control" rows="4"></textarea>
													</div>
													<div class="col-12 form-group">
														<label for="poster">Poster</label> <input type="text"
															id="poster" value="" class="form-control">
													</div>
												</div>
											</div>
											<div class="col-3">
												<label for="inputProjectLeader">Preview</label>
												<div
													style="width: 100%; height: 200px; border: 1px dotted gray">
													<img id="imgPreview" src="" height="100%" width="100%" />
												</div>
											</div>
										</div>

									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<div class="row" style="margin: auto auto;">
							<button type="button" id="resetBtn"
								class="btn bg-gradient-info btn-md" style="margin-right: 15px;">Reset</button>
							<button type="button" id="submitBtn"
								class="btn bg-gradient-success btn-md">Submit</button>
						</div>
						<center>
							<h5 id="message"></h5>
						</center>
					</div>
				</div>
			</section>
			<br /> <br />
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<strong>Copyright &copy; 2014-2021 <a
				href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 3.1.0
			</div>
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script
		src="<c:url value='/views/admin/plugins/jquery/jquery.min.js'/>"></script>
	<!-- jQuery UI 1.11.4 -->
	<script
		src="<c:url value='/views/admin/plugins/jquery-ui/jquery-ui.min.js'/>"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<!-- Bootstrap 4 -->
	<script
		src="<c:url value='/views/admin/plugins/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
	<!-- ChartJS -->
	<script
		src="<c:url value='/views/admin/plugins/chart.js/Chart.min.js'/>"></script>
	<!-- Sparkline -->
	<script
		src="<c:url value='/views/admin/plugins/sparklines/sparkline.js'/>"></script>
	<!-- JQVMap -->
	<script
		src="<c:url value='/views/admin/plugins/jqvmap/jquery.vmap.min.js'/>"></script>
	<script
		src="<c:url value='/views/admin/plugins/jqvmap/maps/jquery.vmap.usa.js'/>"></script>
	<!-- jQuery Knob Chart -->
	<script
		src="<c:url value='/views/admin/plugins/jquery-knob/jquery.knob.min.js'/>"></script>
	<!-- daterangepicker -->
	<script
		src="<c:url value='/views/admin/plugins/moment/moment.min.js'/>"></script>
	<script
		src="<c:url value='/views/admin/plugins/daterangepicker/daterangepicker.js'/>"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="<c:url value='/views/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js'/>"></script>
	<!-- Summernote -->
	<script
		src="<c:url value='/views/admin/plugins/summernote/summernote-bs4.min.js'/>"></script>
	<!-- overlayScrollbars -->
	<script
		src="<c:url value='/views/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js'/>"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value='/views/admin/dist/js/adminlte.js'/>"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<c:url value='/views/admin/dist/js/demo.js'/>"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="<c:url value='/views/admin/dist/js/pages/dashboard.js'/>"></script>
</body>
</html>