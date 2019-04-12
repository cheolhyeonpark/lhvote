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
						<h2>이용신청서 작성</h2>
						<p style="font-size: 13px;">아래의 항목들을 작성 후 우측하단의 신청하기를 누르시면
							중앙공동주택관리지원센터 담당자가 신청 내역을 확인할 수 있습니다.</p>
					</div>
				</div>
				<div class="navImg"></div>
			</div>
		</div>
	</nav>

	<main>
	<div class="limitWidth">
		<form method="POST" action="./modify" id="form"
			enctype="multipart/form-data">
			<div class="contents">
				<div class="contTitle">1. 아파트 정보</div>
				<input type="text" name="applyNo" id="applyNo"
					value="${apply.applyNo }" style="display: none">
				<table>
					<tbody>
						<tr>
							<th>아파트명</th>
							<td colspan="3" class="necessary" errmsg="아파트명을 입력해 주세요"><input
								type="text" name="aptName" id="aptName" style="width: 96%"
								value="${apply.aptName }"></td>
						</tr>
						<tr>
							<th>아파트 구분</th>
							<td colspan="3" class="necessary" errmsg="아파트 구분을 선택해 주세요">
								<input class="inputradio" type="radio" name="house" value="1">
								<span class="radiolabel">일반주택</span> <input class="inputradio"
								type="radio" name="house" value="2"> <span
								class="radiolabel">LH 임대 주택</span>
							</td>
						</tr>
						<tr>
							<th>사업자 등록번호</th>
							<td colspan="3" class="necessary" errmsg="사업자 등록번호 인증을 완료해 주세요">
								<input type="text" name="bNumber" id="bNumber"
								style="width: 40%; margin-right: 15px;"
								value="${apply.BNumber }">
								<button type="button" class="formBtn" id="certBtn">인증</button>
							</td>
						</tr>
						<tr>
							<th>대표자 구분</th>
							<td colspan="3" class="necessary" errmsg="대표자 구분을 선택해 주세요">
								<input class="inputradio" type="radio" name="president"
								value="1"> <span class="radiolabel">선관위원장</span>
								<input class="inputradio" type="radio" name="president"
								value="2"> <span class="radiolabel">입대의
									회장</span>
							</td>
						</tr>
						<tr>
							<th>대표자 성명</th>
							<td colspan="3" class="necessary" errmsg="대표자 성명을 입력해 주세요">
								<input type="text" name="preName" id="preName"
								style="width: 96%" value="${apply.preName }">
							</td>
						</tr>
						<tr>
							<th>아파트연락처</th>
							<td class="necessary" errmsg="아파트 연락처를 입력해 주세요"><input
								type="tel" name="aptTel" id="aptTel" style="width: 96%"
								class="telFormat" errmsg="아파트 연락처를 정확히 입력해 주세요"
								value="${apply.aptTel }"></td>
							<th>팩스</th>
							<td><input type="tel" name="aptFax" id="aptFax"
								style="width: 96%" class="telFormat"
								errmsg="아파트 팩스 번호를 정확히 입력해 주세요" value="${apply.aptFax }">
							</td>
						</tr>
						<tr>
							<th>아파트 소재지</th>
							<td colspan="3" class="necessary" errmsg="아파트 소재지를 입력해 주세요">
								<input type="number" name="zipcode" id="zipcode"
								style="width: 20%; margin-bottom: 10px; margin-right: 10px;"
								value="${apply.zipcode }">
								<button type="button" class="formBtn" onclick="findZipcode()">우편번호
									찾기</button> <br> <input type="text" name="address" id="address"
								style="width: 96%; margin-bottom: 10px;"
								value="${apply.address }"><br> <input type="text"
								name="detailAddress" id="detailAddress" style="width: 96%"
								value="${apply.detailAddress }">
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
							<td colspan="3" class="necessary" errmsg="관리자 구분을 선택해 주세요">
								<input class="inputradio" type="radio" name="manager" value="1">
								<span class="radiolabel">입대의 회장</span> <input class="inputradio"
								type="radio" name="manager" value="2"> <span
								class="radiolabel">입대의 구성원</span> <input class="inputradio"
								type="radio" name="manager" value="3"> <span
								class="radiolabel">관리소장</span> <input class="inputradio"
								type="radio" name="manager" value="4">
								<span class="radiolabel">관리소 직원</span>
							</td>
						</tr>
						<tr>
							<th>관리자 성명</th>
							<td class="necessary" errmsg="관리자 성명을 입력해 주세요"><input
								type="text" name="manName" id="manName" style="width: 96%"
								value="${apply.manName }"></td>
							<th>직위</th>
							<td><input type="text" name="manSpot" id="manSpot"
								style="width: 96%" value="${apply.manSpot }"></td>
						</tr>
						<tr>
							<th>연락처 (유선)</th>
							<td class="necessary" errmsg="관리자 연락처(유선)를 입력해 주세요"><input
								type="tel" name="aptPhone" id="aptPhone" style="width: 96%"
								class="telFormat" errmsg="관리자 연락처(유선)를 정확히 입력해 주세요"
								value="${apply.aptPhone }"></td>
							<th>연락처 (무선)</th>
							<td class="necessary" errmsg="관리자 연락처(무선)을 입력해 주세요"><input
								type="tel" name="aptMobile" id="aptMobile" style="width: 96%"
								class="telFormat" errmsg="관리자 연락처(무선)를 정확히 입력해 주세요"
								value="${apply.aptMobile }"></td>
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
							<td colspan="3" class="necessary" errmsg="투표 제목을 입력해 주세요"><input
								type="text" name="elecTitle" id="elecTitle" style="width: 96%"
								value="${apply.elecTitle }"></td>
						</tr>
						<tr>
							<th>투표 유형</th>
							<td colspan="3" class="necessary" errmsg="투표 유형을 선택해 주세요"><input
								class="inputradio" type="radio" name="elecType" value="1">
								<span class="radiolabel">동대표 선거</span> <input class="inputradio"
								type="radio" name="elecType" value="2"> <span
								class="radiolabel">임원 투표</span> <input class="inputradio"
								type="radio" name="elecType" value="3"> <span
								class="radiolabel">기타 안건 투표</span></td>
						</tr>

						<tr>
							<th>예상 투표자 수</th>
							<td colspan="3" class="necessary" errmsg="예상 투표자 수를 입력해 주세요">
								<input type="number" name="elecNumber" id="elecNumber"
								style="width: 20%;" value="${apply.elecNumber }">&nbsp명
							</td>
						</tr>
						<tr>
							<th>투표 기간</th>
							<td colspan="3" class="necessary" errmsg="투표 기간을 입력해 주세요"
								id="electionDate"><select name="startYear" id="startYear"
								onchange="setFebOfStartYear()" class="elecDate">
									<option value="2018">2018</option>
									<option value="2019">2019</option>
							</select>년 <select name="startMonth" id="startMonth"
								onchange="setDaysOfStratYear()" class="elecDate"></select>월 <select
								name="startDay" id="startDay" class="elecDate"></select>일 <select
								name="startHour" id="startHour" class="elecDate"></select>시 <select
								name="startMin" id="startMin" class="elecDate"></select>분
								&nbsp~&nbsp <select name="endYear" id="endYear"
								onchange="setFebOfEndYear()" class="elecDate">
									<option value="2018">2018</option>
									<option value="2019">2019</option>
							</select>년 <select name="endMonth" id="endMonth"
								onchange="setDaysOfEndYear()" class="elecDate"></select>월 <select
								name="endDay" id="endDay" class="elecDate"></select>일 <select
								name="endHour" id="endHour" class="elecDate"></select>시 <select
								name="endMin" id="endMin" class="elecDate"></select>분</td>
						</tr>
						<tr>
							<th>투표 매체</th>
							<td colspan="3" class="necessary" errmsg="투표 매체를 선택해 주세요"><input
								class="inputradio" type="checkbox" name="elecMedia" value="pc">
								<span class="radiolabel">PC(인터넷)</span> <input
								class="inputradio" type="checkbox" name="elecMedia"
								value="phone"> <span class="radiolabel">스마트폰(인터넷)</span>
								<input class="inputradio" type="checkbox" name="elecMedia"
								value="text"> <span class="radiolabel">문자투표</span> <input
								class="inputradio" type="checkbox" name="elecMedia"
								value="local"> <span class="radiolabel">현장투표소</span></td>
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
									<label for="businessCert" class="btn_file">고유번호증(사업자등록증)</label>
									<input type="file" name="businessCert" id="businessCert"
										class="uploadBtn" value="${apply.businessCert }">
								</div>
							</td>
						</tr>
						<tr>
							<th>투표신청 근거서류(회의록 등)</th>
							<td colspan="3">
								<div class="fileBox">
									<label for="supportingDoc" class="btn_file">투표신청
										근거서류(회의록 등)</label> <input type="file" name="supportingDoc"
										id="supportingDoc" class="uploadBtn"
										value="${apply.supportingDoc }">
								</div>
							</td>
						</tr>
						<tr>
							<th>관리소장 배치 확인 증명서</th>
							<td colspan="3">
								<div class="fileBox">
									<label for="confirmCert" class="btn_file">관리소장 배치 확인
										증명서</label> <input type="file" name="confirmCert" id="confirmCert"
										class="uploadBtn" value="${apply.confirmCert }">
								</div>
							</td>
						</tr>
						<tr>
							<th>개인정보수집 동의서</th>
							<td colspan="3">
								<button class="formBtn">샘플 다운로드</button>
								<div class="fileBox">
									<label for="personalInfoAgree" class="btn_file">개인정보수집
										동의서</label> <input type="file" name="personalInfoAgree"
										id="personalInfoAgree" class="uploadBtn"
										value="${apply.personalInfoAgree }">
								</div>
							</td>
						</tr>
						<tr>
							<th>이용협약서</th>
							<td colspan="3">
								<button class="formBtn">샘플 다운로드</button>
								<div class="fileBox">
									<label for="usageAgree" class="btn_file">이용협약서</label> <input
										type="file" name="usageAgree" id="usageAgree"
										class="uploadBtn" value="${apply.usageAgree }">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="lastCont">
				<p>위 공동주택은 신의성실의 원칙을 준수하여 (OOOOO)투표에서 중앙공동주택관리지원센터가 제공하는 아파트 e투표
					사용을 신청합니다</p>
				<p id="today" style="font-size: 18px;"></p>
				<div style="font-size: 18px;">
					<div style="display: inline-block; width: 50%; text-align: left">
						중앙공동주택관리지원센터 귀중</div>
					<div style="display: inline-block; width: 49%; text-align: right">
						OOOO 아파트 대표자 OOO (서명)</div>
				</div>
			</div>
			<hr>
			<div class="nextBtnWrap">
				<button type="button" class="nextBtn normBtn" id="applyBtn"
					style="display: none"></button>
				<button type="button" class="nextBtn normBtn">인 쇄</button>
				<button type="button" class="nextBtn normBtn" id="modifyBtn">
					수정하기</button>
			</div>
		</form>
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

	<script src="js/application	Form.js"></script>
	<script src="js/applicationModifyForm.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="js/zipcode.js"></script>
	<script src="js/check.js"></script>
	<script>	
		var startDate = "<fmt:formatDate value="${apply.startDate }" pattern="yyyyMMddHHmm" />";
		var endDate = "<fmt:formatDate value="${apply.endDate }" pattern="yyyyMMddHHmm" />";
		var startYear = startDate.substr(0, 4);
		var startMonth = startDate.substr(4, 2);
		var startDay = startDate.substr(6, 2);
		var startHour = startDate.substr(8, 2);
		var startMin = startDate.substr(10, 2);
		var endYear = endDate.substr(0, 4);
		var endMonth = endDate.substr(4, 2);
		var endDay = endDate.substr(6, 2);
		var endHour = endDate.substr(8, 2);
		var endMin = endDate.substr(10, 2);
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