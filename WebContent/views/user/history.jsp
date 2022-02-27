<%@ page pageEncoding="utf-8"%>
<%@ include file="/views/general/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>G5-Movie</title>
<%@ include file="/views/general/head.jsp"%>
</head>
<body>
	<%@ include file="/views/general/header.jsp"%>

	<div class="container-fluid">
		<div id="content" class="mx-auto tm-content-container">
			<main>
			<div class="row">
				<div class="col-12">
					<h2 class="tm-page-title mb-4">History</h2>
				</div>
			</div>

			<div class="row tm-catalog-item-list">
				<c:forEach items="${videos}" var="video">
					<div class="col-lg-4 col-md-6 col-sm-12 tm-catalog-item">
						<div class="tm-bg-gray tm-catalog-item-description">
							<h3 class="tm-text-primary mb-3 tm-catalog-item-title">${video.title}</h3>
						</div>
						<div class="position-relative tm-thumbnail-container">
							<img src="<c:url value='views/user/img/${video.poster}'/>"
								alt="Image" class="img-fluid tm-catalog-item-img"> <a
								href="<c:url value='/detail?action=watch&id=${video.href}'/>" class="position-absolute tm-img-overlay">
								<i class="fas fa-play tm-overlay-icon"></i>
							</a>
						</div>
						<p class="tm-catalog-item-text">${video.views} views</p>
					</div>
				</c:forEach>
			</div>

			<!-- Catalog Paging Buttons -->
			<div>
				<ul class="nav tm-paging-links"
					style="display: flex; justify-content: center;">
					<li class="nav-item active"><a href="#"
						class="nav-link tm-paging-link">1</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link tm-paging-link">2</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link tm-paging-link">3</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link tm-paging-link">4</a></li>
					<li class="nav-item"><a href="#"
						class="nav-link tm-paging-link">></a></li>
				</ul>
			</div>
			</main>

			<%@ include file="/views/general/footer.jsp"%>
</body>
</html>