<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>아파트 e투표</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/applicationForm.css">
<style>
main table th {
	text-align: center !important;
	height: 30px !important;
	padding-right: 30px;
}

main table td {
	text-align: center !important;
	height: 30px;
}
</style>
</head>

<body>
	<nav>
		<div class="menuWrap limitWidth">
			<div class="logo">
				<a href="#"></a>
			</div>
			<div class="menu">
				<ul>
					<li>서비스 소개</li>
					<li>이용방법</li>
					<li>이용신청</li>
					<li>고객센터</li>
				</ul>
				<div class="dropDownMenuWrap">
					<div class="dropDownMenu">
						<ul>
							<li>
								<ul>
									<li>서비스 안내</li>
								</ul>
							</li>
							<li>
								<ul>
									<li>이용 절차</li>
									<li>이용수수료</li>
								</ul>
							</li>
							<li>
								<ul>
									<li><a href="/kvoting/application/applicationApplyPolicy.html">이용신청서 작성</a></li>
									<li><a href="/kvoting/application/applicationRequestForm.html">이용신청서 조회(수정)</a></li>
								</ul>
							</li>
							<li>
								<ul>
									<li>공지사항</li>
									<li>자료실</li>
									<li>질문과 답변</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="loginIconWrap">
				<div class="loginIcon"></div>
			</div>
		</div>
		<div class="navbg">
			<div class="limitWidth verticalCenter">
				<div class="navTxt">
					<div class="verticalCenter">
						<h2>이용신청서 조회</h2>
						<p style="font-size: 13px;">신청한 이용신청서를 확인하실 수 있습니다</p>
					</div>
				</div>
				<div class="navImg"></div>
			</div>
		</div>
	</nav>

	<main>
	<div class="limitWidth">
		<div class="contents">
			<table>
				<thead>
					<tr>
						<th>관리자</th>
						<th>투표 제목</th>
						<th>아파트</th>
						<th>주소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="apply" items="${list}">
						<tr>
							<td>${apply.manName }</td>
							<td><a href="./view?applyNo=${apply.applyNo }">${apply.elecTitle }</a></td>
							<td>${apply.aptName }</td>
							<td>${apply.address }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	</main>

	<footer>
		<hr>
		<div class="limitWidth">
			<div class="contents">
				<div class="footerLogo">
					<img src="images/f_logo.png" alt="중앙공동주택관리지원센터">
				</div>
				<div class="footerTxt">
					<div class="policyLink">
						<a href="#">이용에 관한 확인사항</a> &nbsp&nbsp&nbsp&nbsp <a href="#">개인정보처리(취급)방침</a>
					</div>
					<div class="copyright">
						경기도 성남시 분당구 성남대로 54번길3(구미동 175) / 문의전화 : 1600-7004 /<br>
						Copyright ⓒ Korea Land and Housing Corporation All Right Reserved.
					</div>
				</div>
			</div>
		</div>
	</footer>
</body>

</html>