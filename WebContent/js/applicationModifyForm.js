initDate();

function initDate() {
	if (isSettedDate) {
		setFebOfStartYear(startYear);
		setFebOfEndYear(endYear);
		setDaysOfStratYear(startMonth);
		setDaysOfEndYear(endMonth);

		selectYear(document.getElementById("startYear"), startYear - 2018);
		selectYear(document.getElementById("startMonth"), startMonth - 1);
		selectYear(document.getElementById("startDay"), startDay - 1);
		selectYear(document.getElementById("startHour"), startHour);
		selectYear(document.getElementById("startMin"), startMin / 5);
		selectYear(document.getElementById("endYear"), endYear - 2018);
		selectYear(document.getElementById("endMonth"), endMonth - 1);
		selectYear(document.getElementById("endDay"), endDay - 1);
		selectYear(document.getElementById("endHour"), endHour);
		selectYear(document.getElementById("endMin"), endMin / 5);
	} else {
		setTimeout(function() {
			initDate();
		}, 500);
	}
}

function selectYear(node, selected) {
	node.children[Number(selected)].setAttribute('selected', 'selected');
};

document.getElementById("modifyBtn").addEventListener("click", function() {

	if (areNotNull() && areRightFormat()) {
		setElectionDate();
		setUploadFile();
		form.submit();
	}
}, false);

function setUploadFile() {
	var uploadFiles = document.getElementsByClassName("uploadBtn");
	console.log(uploadFiles);
	console.log(uploadFiles.length);

	for (var i = 0; i < uploadFiles.length; i++) {
		try {
			if (uploadFiles[i].files[0].size > 0) {
				continue;
			}
		} catch (e) {
			uploadFiles[i].setAttribute('type', 'text');
		}
	}
}