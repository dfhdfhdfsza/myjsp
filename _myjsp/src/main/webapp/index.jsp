<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/login.css">
</head>
<body>
	<h1>Index Page</h1>
	<br>
	<c:if test="${ses eq null }">
		<form action="/mem/login">
			<div class='box'>
				<div class='box-form'>
					<div class='box-login-tab'></div>
					<div class='box-login-title'>
						<div class='i i-login'></div>
						<h2>LOGIN</h2>
					</div>
					<div class='box-login'>
						<div class='fieldset-body' id='login_form'>
							<button onclick="openLoginInfo();" class='b b-form i i-more'
								title='Mais Informações'></button>
							<p class='field'>
								<label for='user'>ID</label> <input type='text' id='user'name='id' title='Username' /> 
								<span id='valida' class='i i-warning'></span>
							</p>
							<p class='field'>
								<label for='pass'>PASSWORD</label> <input type='password'
									id='pass' name='pw' title='Password' /> <span id='valida'
									class='i i-close'></span>
							</p>

							<label class='checkbox'> <input type='checkbox'
								value='TRUE' title='Keep me Signed in' /> Keep me Signed in
							</label> <input type='submit' id='do_login' value='GET STARTED'
								title='Get Started' />
						</div>
					</div>
				</div>
				<div class='box-info'>
					<p>
						<button onclick="closeLoginInfo();" class='b b-info i i-left'
							title='Back to Sign In'></button>
					<h3>Need Help?</h3>
					</p>
					<div class='line-wh'></div>
					<button onclick="" class='b-support' title='Forgot Password?'>
						Forgot Password?</button>
					<button onclick="" class='b-support' title='Contact Support'>
						Contact Support</button>
					<div class='line-wh'></div>
					<button onclick="" class='b-cta' title='Sign up now!'>
						CREATE ACCOUNT</button>
				</div>
			</div>

		</form>
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

	<script src="/resources/login.js"></script>
</body>
</html>