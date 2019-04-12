function addClickListener() {
	var qnaTitles = document.getElementsByClassName("qnaTitleClick");			
	for (var i = 0; i < qnaTitles.length; i++) {
		qnaTitles[i].onclick = function(event) {
			var answerTrs = document.getElementsByClassName("answerTr");
			for (var i = 0; i < answerTrs.length; i++) {
				answerTrs[i].style.display = 'none';
			}
			var openRows = document.getElementsByClassName("openRow");
			for (var i = 0; i < openRows.length; i++) {
				openRows[i].classList.toggle('openRow');
			}
			event.target.parentNode.classList.toggle('openRow');
			document.getElementById('answerTr'+event.target.getAttribute('no')).style.display = 'table-row';
		}
	}
	
	var passwordBtns = document.getElementsByClassName("passwordBtn");
	for (var i = 0; i < passwordBtns.length; i++) {
		passwordBtns[i].onclick = function(event) {
			var targetNo = event.target.getAttribute('no');
			if(document.getElementById('questionPassword' + targetNo).value == list[targetNo].qnaPassword) {
				var html = '<td></td><td colspan="2" class="leftTd"><span class="qnaMark" style="color:white">Q</span><div class="answerTd">'
					+ list[targetNo].qnaQuestion + '</div>';
				if (list[targetNo].qnaAnswer != "null") {
					html += '<hr><span class="qnaMark" style="color:white">A</span><div class="answerTd">'
							+ list[targetNo].qnaAnswer + '</div></td>';
				}
				html += '<td><button class="formBtn answerBtn" no="'+targetNo+'" onclick="openAnswerModal('+targetNo+')">답변</button></td>';
				event.target.parentNode.parentNode.parentNode.innerHTML = html;
			}
		}
	}
}

var page = 1;
var list = null;
getList();

function getList() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/kvoting/qnaBoard/list?page='+page, true);
	xhr.send();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE
				&& xhr.status === 200) {
			var ret = eval("(" + xhr.responseText + ")");
			list = ret.list;
			printList();
		}
	};
}

var answerModal = document.getElementById('answerModal');
var answerClose = document.getElementById("answerClose");
var answerWriteBtn = document.getElementById("answerWriteBtn");

answerClose.onclick = function() {
	answerModal.style.display = "none";
}

answerWriteBtn.onclick = function() {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/kvoting/qnaBoard/modify', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("qnaNo="+list[answerIndex].qnaNo+"&qnaAnswer="+CKEDITOR.instances.qnaAnswer.getData().replace(/&nbsp;/gi," "));
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE
				&& xhr.status === 200) {
			location.href = "/kvoting/qnaBoard/add";
			alert("작성되었습니다.");
		}
	};
}

var answerIndex;
		
function openAnswerModal(index) {
	answerIndex = index;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/kvoting/qnaBoard/modify', true);
	xhr.send();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE
				&& xhr.status === 200) {
			var ret = xhr.responseText;
			answerModal.style.display = "block";
		}
	};
}
var isAdminId;
function printList() {
	isAdmin();
	printTable();
}

function printTable() {
	if (isAdminId === true || isAdminId === false) {
		var html = '';
		for (var i = 0; i < list.length; i++) {
			html += '<tr>';
			if (list[i].qnaIsAnswered == "true") {
				html += '<td>답변완료</td>';
			} else {
				html += '<td></td>';
			}
			html += '<td class="leftTd qnaTitleClick" no="'+i+'"><span class="qnaMark">Q</span>'
					+ list[i].qnaTitle
					+ '</td>'
					+ '<td>'
					+ list[i].qnaDate
					+ '</td>';
			if (list[i].qnaIsOpened == "true") {
				html += '<td>공개</td></tr><tr class="answerTr" id="answerTr'+i+'"><td></td><td colspan="2" class="leftTd"><span class="qnaMark" style="color:white">Q</span><div class="answerTd">'
						+ list[i].qnaQuestion + '</div>';
				if (list[i].qnaAnswer != "null") {
					html += '<hr><span class="qnaMark" style="color:white">A</span><div class="answerTd">'
							+ list[i].qnaAnswer + '</div></td>';
				}
				html += '<td><button class="formBtn answerBtn" no="'+i+'" onclick="openAnswerModal('+i+')">답변</button></td></tr>';
			} else if (isAdminId) {
				html += '<td>비공개</td></tr><tr class="answerTr" id="answerTr'+i+'"><td></td><td colspan="2" class="leftTd"><span class="qnaMark" style="color:white">Q</span><div class="answerTd">'
				+ list[i].qnaQuestion + '</div>';
				if (list[i].qnaAnswer != "null") {
					html += '<hr><span class="qnaMark" style="color:white">A</span><div class="answerTd">'
							+ list[i].qnaAnswer + '</div></td>';
				}
				html += '<td><button class="formBtn answerBtn" no="'+i+'" onclick="openAnswerModal('+i+')">답변</button></td></tr>';
			} else {
		
				html += '<td>비공개</td></tr><tr class="answerTr" id="answerTr'+i+'"><td></td>'
						+ '<td colspan="2" class="inputPw"><div> 비밀번호 : <input type="password" style="margin-right:10px;" class="questionPassword" id="questionPassword'+i+'">'
						+ '<button class="formBtn passwordBtn" no="'+i+'">입력</button></div></td><td></td></tr>';
			}
		}
		document.getElementById("qnaListTable").innerHTML = html;
		addClickListener();
	} else {
		setTimeout(function() {
			printTable();
		}, 500);
	}
	
}

var endPage = 0;
getEndPage();
printPageIndex();

function printPageIndex() {
	if (endPage != 0) {
		var html = '<div class="pageNumber" onclick="changePage('+Number(page-1)+')">&#60;</div>';
		var i;
		var firstPage = Math.floor((page - 1) / 10) * 10 + 1;
		var lastPage = firstPage + 9 < endPage ? firstPage + 9
				: endPage;
		for (i = firstPage; i <= lastPage; i++) {
			if (i == page) {
				html += '<div class="pageNumber active">' + i
						+ '</div>';
				continue;
			}
			html += '<div class="pageNumber" onclick="changePage('+i+')">'+i+'</div>';
		}
		html += '<div class="pageNumber" onclick="changePage('+Number(page+1)+')">&#62;</div>';
		document.getElementById("pageIndexDiv").innerHTML = html;
	} else {
		setTimeout(function() {
			printPageIndex();
		}, 500);
	}
}

function changePage(nextPage) {
	if (nextPage < 1 || nextPage > endPage) {
		return;
	}
	page = nextPage;
	printPageIndex();
	getList();
}

function isAdmin() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/kvoting/member/loginCheck', true);
	xhr.send();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE
				&& xhr.status === 200) {
			if (xhr.responseText == 'ok') {
				isAdminId = true;
				return;
			}
			isAdminId = false;
		}
	};
}

function getEndPage() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/kvoting/qnaBoard/endpage', true);
	xhr.send();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE
				&& xhr.status === 200) {
			endPage = xhr.responseText;
			return;
		}
		endPage = 1;
	};
}

var modal = document.getElementById('myModal');
var modalBtn = document.getElementById("myBtn");
var modalClose = document.getElementById("modalclose");
var writeBtn = document.getElementById("writeBtn");

modalBtn.onclick = function() {
	modal.style.display = "block";
}

modalClose.onclick = function() {
	modal.style.display = "none";
}

function refreshSecureCode() {
	document.getElementById("captchaImg").setAttribute("src",
			"/kvoting/captcha?id=" + Math.random());
}

CKEDITOR.replace('qnaQuestion');
CKEDITOR.replace('qnaAnswer');

writeBtn.onclick = function() {
	var qnaTitle = document.getElementById("qnaTitle").value;
	var qnaPassword = document.getElementById("qnaPassword").value;
	var qnaPasswordAgain = document.getElementById("qnaPasswordAgain").value;
	var secureCode = document.getElementById("secureCode").value;
	var qnaQuestion = CKEDITOR.instances.qnaQuestion.getData().replace(/&nbsp;/gi," ");
	var data = getData(qnaTitle, qnaPassword, qnaPasswordAgain, secureCode, qnaQuestion);
	if (data == null) {
		return;
	}
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/kvoting/qnaBoard/add', true);
	xhr.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xhr.send(data);
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState === XMLHttpRequest.DONE
				&& xhr.status === 200) {
			if (xhr.responseText == "incorrectSecureCode") {
				alert("보안코드를 정확하게 입력해 주세요!");
				return;
			}
			if (xhr.responseText == "error") {
				location.href = "/kvoting/error.html";
				return;
			}
			location.href = "/kvoting/qnaBoard/add";
			alert("작성되었습니다.");
		}
	};
}

function getData(qnaTitle, qnaPassword, qnaPasswordAgain, secureCode, qnaQuestion) {
	var query = null;
	if (isEveryInputNotNull()) {
		query = "qnaTitle=" + qnaTitle + "&secureCode=" + secureCode
				+ "&qnaQuestion=" + qnaQuestion;
		if (qnaPassword != '') {
			query += "&qnaPassword=" + qnaPassword;
		}
	}
	return query;
}

function isEveryInputNotNull() {
	if (qnaTitle == '') {
		alert("제목을 입력해 주세요");
		return false;
	}
	if (secureCode == '') {
		alert("보안 코드를 입력해 주세요");
		return false;
	}
	if (qnaQuestion == '') {
		alert("질문 내용을 입력해 주세요");
		return false;
	}
	return true;
}