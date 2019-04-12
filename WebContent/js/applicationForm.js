var applyBtn = document.getElementById("applyBtn");
var certBtn = document.getElementById("certBtn");
var form = document.getElementById("form");
var startDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var endDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var elecDate = document.getElementsByClassName("elecDate");
var isCertificated = false;
var isSettedDate = false;

window.onload = function () {
    elecDate[1].innerHTML = getHtml(1, 12, 1);
    elecDate[6].innerHTML = getHtml(1, 12, 1);
    elecDate[2].innerHTML = getHtml(1, 31, 1);
    elecDate[7].innerHTML = getHtml(1, 31, 1);
    elecDate[3].innerHTML = getHtml(0, 23, 1);
    elecDate[8].innerHTML = getHtml(0, 23, 1);
    elecDate[4].innerHTML = getHtml(0, 59, 5);
    elecDate[9].innerHTML = getHtml(0, 59, 5);
    setToday();
    isSettedDate = true;
}

function setToday() {
    var today = document.getElementById("today");
    var date = new Date();
    today.innerHTML = date.getFullYear() + '. ' + (date.getMonth() + 1) + '. ' + date.getDate() + '. ';
}

function isLeapYear(year) {
    return year % 400 == 0 ? true : (year % 4 == 0 ? (year % 100 ? false : true) : false);
}

function setFebOfStartYear(selectedYear) {
    if (isLeapYear(selectedYear)) {
        startDays[1] = 29;
    }
}

function setFebOfEndYear(selectedYear) {
    if (isLeapYear(selectedYear)) {
        endDays[1] = 29;
    }
}

function getHtml(start, end, term) {
    var html = '';
    for (var i = start; i <= end; i += term) {
        html += '<option value=' + i + '>' + i + '</option>';
    }
    return html;
}

function setDaysOfStratYear(selectedMonth) {
    elecDate[2].innerHTML = getHtml(1, startDays[selectedMonth - 1], 1);
}

function setDaysOfEndYear(selectedMonth) {
    elecDate[7].innerHTML = getHtml(1, endDays[selectedMonth - 1], 1);
}

function getCheckedVal(object) {
    for (var i = 0; i < object.length; i++) {
        if (object[i].checked) {
            return object[i].value;
        }
    }
    return null;
}

function areNotNull() {
    var type;
    var name;
    var nullcheck;
    var necessaries = document.getElementsByClassName("necessary");
    for (var i = 0; i < necessaries.length; i++) {
        type = necessaries[i].firstElementChild.getAttribute('type');
        name = necessaries[i].firstElementChild.getAttribute('name');
        if (type == null) {
            continue;
        } else if (type == 'radio' || type == 'checkbox') {
            nullcheck = getCheckedVal(document.getElementsByName(name));
        } else {
            if (name == 'bNumber') {
                nullcheck = isCertificated ? necessaries[i].firstElementChild.value : null;
            } else {
                nullcheck = necessaries[i].firstElementChild.value;
            }
        }
        if (nullcheck == null || nullcheck == '') {
            alert(necessaries[i].getAttribute('errmsg'));
            return false;
        } else if (name == 'zipcode' && document.getElementById("address").value == '') {
            alert(necessaries[i].getAttribute('errmsg'));
            return false;
        }
    }
    return true;
}

function areRightFormat() {
	var telFormats = document.getElementsByClassName("telFormat");
	for (var i = 0; i < telFormats.length; i++) {
		if (telFormats[i].value != '' && !telFormats[i].value.match(/^\d{2,3}-\d{3,4}-\d{4}$/)) {
			alert(telFormats[i].getAttribute('errmsg'));
			return false;
		}		
	}
	return true;
}

function getDate(year, month, day, hour, min) {
    return year.options[year.selectedIndex].value + '-' +
            month.options[month.selectedIndex].value + '-' +
            day.options[day.selectedIndex].value + ' ' +
            hour.options[hour.selectedIndex].value + ':' +
            min.options[min.selectedIndex].value + ':' + "00";
}

function setElectionDate() {
	var electionDate = document.getElementById("electionDate");
    var startDate = getDate(elecDate[0],elecDate[1],elecDate[2],elecDate[3],elecDate[4]);
    var endDate = getDate(elecDate[5],elecDate[6],elecDate[7],elecDate[8],elecDate[9]);
    electionDate.innerHTML = '<input type="text" name="startDate" id="startDate" value="'+startDate+'"><input type="text" name="endDate" id="endDate" value="'+endDate+'">';
}

certBtn.addEventListener("click", function () {
    var bNumber = document.getElementById('bNumber').value;
    var taxCode = bNumber.substr(0, 3);
    var corpCode = bNumber.substr(3, 2);
    var serialNumber = bNumber.substr(5, 4);
    var verifCode = bNumber.substr(9, 1);
    if (bNumber.length == 10 && taxCode >= 101 && taxCode <= 999 && corpCode >= 1 && corpCode <= 99 && serialNumber >= 1 && serialNumber <= 9999 && verifCode >= 0 && verifCode <= 9) {
        isCertificated = true;
        certBtn.style.display = 'none';
        alert("인증 완료 되었습니다");
    } else {
        alert("인증 번호를 다시 확인해 주세요");
    }
});

applyBtn.addEventListener("click", function () {
    if (areNotNull() && areRightFormat() && areRightDate()) {
        setElectionDate();
        form.submit();
    }
}, false);

function areRightDate() {
    var startDate = new Date(elecDate[0].value,elecDate[1].value-1,elecDate[2].value);
    var endDate = new Date(elecDate[5].value,elecDate[6].value-1,elecDate[7].value);
    var today = new Date();
    if (today.getTime() > startDate.getTime() || today.getTime() > endDate.getTime()) {
    	alert("투표일이 오늘 이전 입니다.");
    	return false;
    } else if (startDate.getTime() > endDate.getTime()) {
    	alert("투표 종료일이 시작일 이전 입니다.");
    	return false;
    } else if (startDate.getTime() == endDate.getTime()) {
    	var startTime = elecDate[3].value*60 + elecDate[4];
    	var endTime = elecDate[8].value*60 + elecDate[9];
    	if (startTime > endTime) {
    		alert("투표 종료시간이 시작시간 이전입니다.");
    	}
    }
	return true;
}