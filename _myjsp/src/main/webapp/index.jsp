<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/login2.css">
</head>
<body>
	<h1>Index Page</h1>
	<br>
	<c:if test="${ses eq null }">
		<div class="container" id="container">
			<div class="form-container sign-up-container">
				<form action="/mem/join">
					<h1>Create Account</h1>
					<div class="social-container">
						<a href="#" class="social"><i class="fab fa-facebook-f"></i></a> <a
							href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
						<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
					</div>
					<span>or use your email for registration</span> 
					<input type="text" placeholder="Email" name="id"/>
					<input type="text" placeholder="Password" name="pw"/>
					<button type="submit">Create Account</button>
				</form>
			</div>
			<div class="form-container sign-in-container">
				<form action="/mem/login" method="post">
					<h1>Login</h1>
					<div class="social-container">
						<a href="#" class="social"><i class="fab fa-facebook-f"></i></a> <a
							href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
						<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
					</div>
					<span>or use your account</span> 
					<input type="text"placeholder="ID" name="id"/> 
					<input type="text" placeholder="Password" name="pw"/> 
					<a href="#">Forgot your password?</a>
					<button type="submit">Login</button>
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay">
					<div class="overlay-panel overlay-left">
						<h1>Welcome Back!</h1>
						<p>To keep connected with us please login with your personal
							info</p>
						<button class="ghost" id="signIn">Sign In</button>
					</div>
					<div class="overlay-panel overlay-right">
						<h1>Hello, Friend!</h1>
						<p>Enter your personal details and start journey with us</p>
						<button class="ghost" id="signUp">Sign Up</button>
					</div>
				</div>
			</div>
		</div>
		<br>
		<hr>
	</c:if>
	
	<div>
		<c:if test="${ses ne null}">
			${ses.id}님 로그인 되었습니다. <br>
			계정생성일:${ses.regdate} <br>
			마지막로그인:${ses.last_login} <br>
		</c:if>
	</div>

	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}"/>`;
		if (msg_login == '0') {
			alert("로그인 정보가 일치하지 않습니다.");
		}
	</script>
	<hr>

	<a href="/mem/register"><button>회원가입</button></a>
	<a href="/mem/logout"><button>로그아웃</button></a>	
	<a href="/mem/list"><button>회원 리스트</button></a>
	<a href="/mem/modify"><button>회원 정보 수정</button></a>

	<br>
	<a href="/brd/register">게시글 쓰기로 이동...</a>
	<br>
	<a href="/brd/pageList">게시글 목록으로 이동...</a>
	<br>

	<script src="/resources/login2.js"></script>
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
</body>
</html>