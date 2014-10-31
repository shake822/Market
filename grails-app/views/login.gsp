<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Welcome to Grails</title>
<style type="text/css" media="screen">
</style>
</head>
<body>
	<div class="content scaffold-edit" role="main">
		<h1>系统登录</h1>

		<g:form url="[action:'login',controller:'login']" method="POST">
			<g:if test="${flash.message}">
				<div class="message" role="status">
					${flash.message}
				</div>
			</g:if>
			<div class="fieldcontain required">
				<label for="account"> <g:message code="login.account.label"
						default="账号" />

				</label>
				<g:textField name="account" value="" />

			</div>

			<div class="fieldcontain required">
				<label for="password"> <g:message
						code="login.password.label" default="密码" />

				</label>
				<g:passwordField name="password" type="password" value="" />

			</div>
			<div style="height: 10px"></div>
			<fieldset class="buttons">
				<g:actionSubmit class="save"
					onclick="if(!checkData()) return false;" action="login" value="登录" />
			</fieldset>
		</g:form>
	</div>

	<script type="text/javascript"
		src="${createLink(uri: '/js/jquery-1.7.2.min.js')}"></script>
	<script type="text/javascript">
		function checkData() {
			var $account = $("#account");
			var $password = $("#password");
			var flag = true;
			if ($account.val() === "") {
				$account.parent("div").addClass("error");
				flag = false;
			}
			if ($password.val() === "") {
				$password.parent("div").addClass("error");
				flag = false;
			}
			return flag;
		}
	</script>
</body>
</html>
