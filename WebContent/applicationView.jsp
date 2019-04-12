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
<link rel="stylesheet" href="/kvoting/css/common.css">
<link rel="stylesheet" href="/kvoting/css/applicationForm.css">
<style>
main table td {
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
									<li><a href="/kvoting/application/add">이용신청서 작성</a></li>
									<li><a href="/kvoting/application/list">이용신청서 조회(수정)</a></li>
								</ul>
							</li>
							<li>
								<ul>
									<li>공지사항</li>
									<li>자료실</li>
									<li><a href="/kvoting/qnaBoard/add">질문과 답변</a></li>
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
			<div class="contTitle">1. 아파트 정보</div>
			<table>
				<tbody>
					<tr>
						<th>아파트명</th>
						<td colspan="3" class="necessary" errmsg="아파트명을 입력해 주세요">
							${apply.aptName }</td>
					</tr>
					<tr>
						<th>아파트 구분</th>
						<td colspan="3" class="necessary" errmsg="아파트 구분을 선택해 주세요"><input
							class="inputradio" type="radio" name="house" value="1" disabled>
							<span class="radiolabel">일반주택</span> <input class="inputradio"
							type="radio" name="house" value="2" disabled> <span
							class="radiolabel">LH 임대 주택</span></td>
					</tr>
					<tr>
						<th>사업자 등록번호</th>
						<td colspan="3" class="necessary" errmsg="사업자 등록번호 인증을 완료해 주세요">
							${apply.BNumber }</td>
					</tr>
					<tr>
						<th>대표자 구분</th>
						<td colspan="3" class="necessary" errmsg="대표자 구분을 선택해 주세요"><input
							class="inputradio" type="radio" name="president" value="1"
							disabled> <span class="radiolabel">선관위원장</span> <input
							class="inputradio" type="radio" name="president" value="2"
							disabled> <span class="radiolabel">입대의 회장</span></td>
					</tr>
					<tr>
						<th>대표자 성명</th>
						<td colspan="3" class="necessary" errmsg="대표자 성명을 입력해 주세요">
							${apply.preName }</td>
					</tr>
					<tr>
						<th>아파트연락처</th>
						<td class="necessary" errmsg="아파트 연락처를 입력해 주세요">
							${apply.aptTel }</td>
						<th>팩스</th>
						<td>${apply.aptFax }</td>
					</tr>
					<tr>
						<th>아파트 소재지</th>
						<td colspan="3" class="necessary" errmsg="아파트 소재지를 입력해 주세요">
							<p>우편번호 ${apply.zipcode }</p>
							<p>${apply.address }&nbsp${apply.detailAddress}</p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="contents">
			<div class="contTitle">2. 관리자 정보</div>
			<table>
				<tbody>
					<tr>
						<th>관리자 구분</th>
						<td colspan="3" class="necessary" errmsg="관리자 구분을 선택해 주세요"><input
							class="inputradio" type="radio" name="manager" value="1" disabled>
							<span class="radiolabel">입대의 회장</span> <input class="inputradio"
							type="radio" name="manager" value="2" disabled> <span
							class="radiolabel">입대의 구성원</span> <input class="inputradio"
							type="radio" name="manager" value="3" disabled> <span
							class="radiolabel">관리소장</span> <input class="inputradio"
							type="radio" name="manager" value="4" disabled> <span
							class="radiolabel">관리소 직원</span></td>
					</tr>
					<tr>
						<th>관리자 성명</th>
						<td class="necessary" errmsg="관리자 성명을 입력해 주세요">
							${apply.manName }</td>
						<th>직위</th>
						<td>${apply.manSpot }</td>
					</tr>
					<tr>
						<th>연락처 (유선)</th>
						<td class="necessary" errmsg="관리자 연락처(유선)를 입력해 주세요">
							${apply.aptPhone }</td>
						<th>연락처 (무선)</th>
						<td class="necessary" errmsg="관리자 연락처(무선)을 입력해 주세요">
							${apply.aptMobile }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="contents">
			<div class="contTitle">3. 투표 정보</div>
			<table>
				<tbody>
					<tr>
						<th>투표 제목(대표 안건명)</th>
						<td colspan="3" class="necessary" errmsg="투표 제목을 입력해 주세요">
							${apply.elecTitle }</td>
					</tr>
					<tr>
						<th>투표 유형</th>
						<td colspan="3" class="necessary" errmsg="투표 유형을 선택해 주세요"><input
							class="inputradio" type="radio" name="elecType" value="1"
							disabled> <span class="radiolabel">동대표 선거</span> <input
							class="inputradio" type="radio" name="elecType" value="2"
							disabled> <span class="radiolabel">임원 투표</span> <input
							class="inputradio" type="radio" name="elecType" value="3"
							disabled> <span class="radiolabel">기타 안건 투표</span></td>
					</tr>

					<tr>
						<th>예상 투표자 수</th>
						<td colspan="3" class="necessary" errmsg="예상 투표자 수를 입력해 주세요">
							${apply.elecNumber }&nbsp명</td>
					</tr>
					<tr>
						<th>투표 기간</th>
						<td colspan="3" class="necessary" errmsg="투표 기간을 입력해 주세요"
							id="electionDate"><fmt:formatDate
								value="${apply.startDate }" pattern="yyyy년  MM월  dd일  HH시  mm분" />
							&nbsp~&nbsp <fmt:formatDate value="${apply.endDate }"
								pattern="yyyy년  MM월  dd일  HH시  mm분" /></td>
					</tr>
					<tr>
						<th>투표 매체</th>
						<td colspan="3" class="necessary" errmsg="투표 매체를 선택해 주세요"><input
							class="inputradio" type="checkbox" name="elecMedia" value="pc"
							disabled> <span class="radiolabel">PC(인터넷)</span> <input
							class="inputradio" type="checkbox" name="elecMedia" value="phone"
							disabled> <span class="radiolabel">스마트폰(인터넷)</span> <input
							class="inputradio" type="checkbox" name="elecMedia" value="text"
							disabled> <span class="radiolabel">문자투표</span> <input
							class="inputradio" type="checkbox" name="elecMedia" value="local"
							disabled> <span class="radiolabel">현장투표소</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="contents">
			<div class="contTitle">4. 첨부 서류</div>
			<table>
				<tbody>
					<tr>
						<th style="width: 260px">고유번호증(사업자등록증)</th>
						<td colspan="3">
							<div class="fileBox">
								<c:if test="${apply.businessCert != null}">
									<a href="./uploadFiles/${apply.businessCert }" class="btn_file">고유번호증(사업자등록증)</a>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>투표신청 근거서류(회의록 등)</th>
						<td colspan="3">
							<div class="fileBox">
								<c:if test="${apply.supportingDoc != null}">
									<a href="./uploadFiles/${apply.supportingDoc }"
										class="btn_file">투표신청 근거서류(회의록 등)</a>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>관리소장 배치 확인 증명서</th>
						<td colspan="3">
							<div class="fileBox">
								<c:if test="${apply.confirmCert != null}">
									<a href="./uploadFiles/${apply.confirmCert }" class="btn_file">관리소장
										배치 확인 증명서</a>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>개인정보수집 동의서</th>
						<td colspan="3">
							<div class="fileBox">
								<c:if test="${apply.personalInfoAgree != null}">
									<a href="./uploadFiles/${apply.personalInfoAgree }"
										class="btn_file">개인정보수집 동의서</a>
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<th>이용협약서</th>
						<td colspan="3">
							<div class="fileBox">
								<c:if test="${apply.usageAgree != null}">
									<a href="./uploadFiles/${apply.usageAgree }" class="btn_file">이용협약서</a>
								</c:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="lastCont">
			<p>위 공동주택은 신의성실의 원칙을 준수하여 ${apply.elecTitle }투표에서 중앙공동주택관리지원센터가
				제공하는 아파트 e투표 사용을 신청합니다</p>
			<p id="today" style="font-size: 18px;"></p>
			<div style="font-size: 18px;">
				<div style="display: inline-block; width: 50%; text-align: left">
					중앙공동주택관리지원센터 귀중</div>
				<div style="display: inline-block; width: 49%; text-align: right">
					${apply.aptName } 아파트 대표자 ${apply.preName } (서명)</div>
			</div>
		</div>
		<hr>
		<div class="nextBtnWrap">
			<button onclick="location.href='./modify?applyNo=${apply.applyNo }'"
				class="modifyBtn normBtn">수 정</button>
			<button onclick="location.href='./remove?applyNo=${apply.applyNo }'"
				class="normBtn">신청취소</button>
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

	<script src="/kvoting/js/applicationForm.js"></script>
	<script src="/kvoting/js/check.js"></script>
	<script>
		<c:forEach var="elecMedia" items="${elecMedias}">
			check(document.getElementsByName("elecMedia"), ${elecMedia}-1);
		</c:forEach>
		check(document.getElementsByName("house"), ${apply.house}-1);
		check(document.getElementsByName("president"), ${apply.president}-1);
		check(document.getElementsByName("manager"), ${apply.manager}-1);
		check(document.getElementsByName("elecType"), ${apply.elecType}-1);		
	</script>
</body>

</html>