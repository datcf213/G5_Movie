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
		<div class="mx-auto tm-content-container">
			<main>
			<div class="row mb-5 pb-4">
				<div class="col-12" style="max-width: 66%;">
					<!-- Video player 1422x800 -->
					<iframe src="https://www.youtube.com/embed/${video.href}"></iframe>
				</div>
			</div>
			<div class="row mb-5 pb-5">
				<div class="col-xl-8 col-lg-7">
					<!-- Video description -->
					<div class="tm-video-description-box">
						<h2 class="mb-5 tm-video-title"
							style="margin-top: -55px; margin-bottom: 10px !important; color: #3399CC; font-weight: bold;">${video.title}</h2>
						<p class="mb-4">${video.description}</p>
					</div>
					<input id="videoHidden" type="hidden" value="${video.href}">
				</div>
				<div class="col-xl-4 col-lg-5">
					<!-- Share box -->
					<div class="tm-bg-gray tm-share-box"
						style="position: absolute; margin-top: -510px; border-style: none none none groove;">
						<h6 class="tm-share-box-title mb-4">Share this video</h6>
						<div class="mb-5 d-flex">
							<a href="" class="tm-bg-white tm-share-button"><i
								class="fab fa-facebook"></i></a> <a href=""
								class="tm-bg-white tm-share-button"><i
								class="fab fa-twitter"></i></a> <a href=""
								class="tm-bg-white tm-share-button"><i
								class="fab fa-pinterest"></i></a> <a href=""
								class="tm-bg-white tm-share-button"><i
								class="far fa-envelope"></i></a>
						</div>
						<c:if test="${not empty sessionScope.currentUser}">
							<button id="likeOrUnlike"
								class="tm-bg-white px-5 mb-4 d-inline-block tm-text-primary tm-likes-box tm-liked"
								style="text-align: center;"> <i
								class="fas fa-heart mr-3 tm-liked-icon"></i> <i
								class="far fa-heart mr-3 tm-not-liked-icon"></i> 
								<span id="tm-likes-count">
									<c:choose>
										<c:when test="${flagLike != true}">
											Like
										</c:when>
										<c:otherwise>
											Unlike
										</c:otherwise>
									</c:choose>
								</span>
							</button>
						</c:if>
						<div>
							<button
								class="btn btn-primary p-0 tm-btn-animate tm-btn-download tm-icon-download">
								<span>Download Video</span>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row pt-4 pb-5">
				<div class="col-12">
					<h2 class="mb-5 tm-text-primary">Related Videos for You</h2>
					<div class="row tm-catalog-item-list">
						<c:forEach items="${videos}" var="video1">
							<div class="col-lg-4 col-md-6 col-sm-12 tm-catalog-item">
								<div class="position-relative tm-thumbnail-container">
									<img src="<c:url value='views/user/img/${video1.poster}'/>"
										alt="Image" class="img-fluid tm-catalog-item-img"> <a
										href="<c:url value='/detail?action=watch&id=${video1.href}'/>"
										class="position-absolute tm-img-overlay"> <i
										class="fas fa-play tm-overlay-icon"></i>
									</a>
								</div>
								<div class="p-3 tm-catalog-item-description">
									<h3 class="tm-text-gray text-center tm-catalog-item-title">${video1.title}</h3>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			</main>
			<%@ include file="/views/general/footer.jsp"%>
			<script>
				$('#likeOrUnlike').click(function(){
					var videoId = $('#videoHidden').val();
					$.ajax({
						url: 'detail?action=like&id=' + videoId
					}).then(function(data) {
						var text = $('#likeOrUnlike').text();
						if(text.indexOf('Like') != -1){
							$('#likeOrUnlike').text('Unlike');
						}else{
							$('#likeOrUnlike').text('Like');
						}
					}).fail(function(error) {
						alert('Error!!');
					})
				});
			</script>
</body>
</html>