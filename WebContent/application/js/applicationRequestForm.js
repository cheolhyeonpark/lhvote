var isCertificated = false;
        
document.getElementById("viewBtn").addEventListener("click", function() {
    if (!isCertificated) {
        alert("휴대전화 인증을 완료해주세요");
        return;
    }

    var nessaries = document.getElementsByClassName("necessary");
    for (var i = 0; i < nessaries.length; i++) {
        if(nessaries[i].value == '') {
            alert(nessaries[i].parentElement.getAttribute("errmsg"));
            return;
        }
    }

    var phone1 = document.getElementById("phone1");
    var phoneNumber = phone1.options[phone1.selectedIndex].value + "-" + document.getElementById("phone2").value + "-" + document.getElementById("phone3").value;
    var phone = document.getElementById("phone");
    phone.innerHTML = '<input type="text" name="aptMobile" id="aptMobile" value="' + phoneNumber + '">';
    document.getElementById("form").submit();
});

document.getElementById("certBtn").addEventListener("click", function() {
    alert("인증 번호가 발송되었습니다.");
    isCertificated = true;
});