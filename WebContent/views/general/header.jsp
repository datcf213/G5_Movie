<%@ page pageEncoding="utf8"%>
<div class="tm-page-wrap mx-auto">
	<div class="position-relative">
		<div class="position-absolute tm-site-header">
			<div class="container-fluid position-relative">
				<div class="row">
					<div class="col-7 col-md-4">
						<a href="index" class="tm-bg-black text-center tm-logo-container"
							style="margin-left: -125px; margin-top: -45px;"> <i
							class="fas fa-video tm-site-logo mb-3"></i>
							<h1 class="tm-site-name">G5-Movie</h1>
						</a>
					</div>
					<div class="col-5 col-md-8 ml-auto mr-0">
						<div class="tm-site-nav">
							<nav class="navbar navbar-expand-lg mr-0 ml-auto"
								id="tm-main-nav">
								<button
									class="navbar-toggler tm-bg-black py-2 px-3 mr-0 ml-auto collapsed"
									type="button" data-toggle="collapse" data-target="#navbar-nav"
									aria-controls="navbar-nav" aria-expanded="false"
									aria-label="Toggle navigation">
									<span> <i class="fas fa-bars tm-menu-closed-icon"></i> <i
										class="fas fa-times tm-menu-opened-icon"></i>
									</span>
								</button>
								<div class="collapse navbar-collapse tm-nav" id="navbar-nav"
									style="margin: -55px;">
									<ul class="navbar-nav">
										<c:choose>
											<c:when test="${not empty sessionScope.currentUser}">
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="index">Home</a></li>
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="favorite">Favorite</a></li>
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="history">History</a></li>
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="logout">Logout</a></li>
											</c:when>
											<c:otherwise>
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="login">Login</a></li>
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="register">Register</a></li>
												<li class="nav-item"><a class="nav-link tm-nav-link"
													href="forgetpassword">Forget pasword</a></li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="tm-welcome-container text-center text-white">
			<div class="tm-welcome-container-inner">
				<p class="tm-welcome-text mb-1 text-white"></p>
				<p class="tm-welcome-text mb-5 text-white"></p>
				<a href="#content"
					class="btn tm-btn-animate tm-btn-cta tm-icon-down"
					style="margin-top: 180px;"> <span>Discover</span>
				</a>
			</div>
		</div>

		<img id="tm-fixed-header-bg"
			src="<c:url value='/views/user/img/poster.jpg'/>"> </img>
	</div>