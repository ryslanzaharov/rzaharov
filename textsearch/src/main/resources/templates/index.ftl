<!DOCTYPE html>
<html lang="en"
	  xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns:form="http://www.w3.org/1999/html"
	  xmlns:th="http://www.w3.org/1999/xhtml" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<!--[if lt IE 9]><script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script><![endif]-->
	<title></title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link href="css/style.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.10.2.js"
			type="text/javascript"></script>
	<script src="js/app-ajax.js" type="text/javascript"></script>
</head>

<body>

<div class="wrapper">

	<header class="header">
		<form th:action="@{/index}" method="post" >
			<div><label> Текст для поиска : <input type="text" name="search_text"/> </label></div>
			<div><label> Расширение файла : <input type="text" name="type_file"/> </label></div>
			<div><label> Путь для поиска : <input type="text" name="path"/> </label></div>
			<input type="submit" value="Искать">
		</form>
	</header><!-- .header-->

		<div class="middle">
		<div class="container">
			<main class="content">

				<p><div id="ajaxGetUserServletResponse" ></div></p>
			</main><!-- .content -->
		</div><!-- .container-->

		<aside class="left-sidebar">
				<#if paths??>

					<#list paths as path>
						<form th:action="@{/ajax}" method="post">
							<tr>
								<td><input type="text" id="userName" value="${path}"></td>
								<td><button type="button" onclick="ajax()">Открыть</button></td>
							</tr>
						</form>
					</#list>
				<#else></#if>
		</aside><!-- .left-sidebar -->


	</div><!-- .middle-->

</div><!-- .wrapper -->

</body>
<html/>