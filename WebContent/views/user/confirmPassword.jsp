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

	<main>
	<div class="container-fluid px-0">
		<div class="mx-auto tm-content-container">
			<div class="row mt-3">
				<div class="col-12">
					<center>
						<mark>${message}</mark>
					</center>
					<div class="mx-auto tm-about-text-container px-3">
						<h2 class="tm-page-title mb-4 tm-text-primary"
							style="text-align: center;">New password</h2>
					</div>
				</div>
			</div>
			<div class="mx-auto pb-3 tm-about-text-container px-3">
				<div class="row" style="display: grid; place-items: center;">
					<div class="col-lg-6 mb-5">
						<form id="contact-form" action="confirmpassword" method="POST"
							class="tm-contact-form">
							<div class="form-group">
								<input type="password" name="password"
									class="form-control rounded-0" placeholder="New password"
									required="" />
							</div>
							<div class="form-group mb-0">
								<button type="submit"
									class="btn btn-primary rounded-0 d-block ml-auto mr-0 tm-btn-animate tm-btn-submit tm-icon-submit">
									<span>Submit</span>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>

	<%@ include file="/views/general/footer.jsp"%>
</body>
</html>