var checkGuide = document.getElementById("checkGuide");
var checkPolicy = document.getElementById("checkPolicy");
var nextBtn = document.getElementById("nextBtn");

nextBtn.addEventListener("click", function() {
    console.log(checkGuide);
    if (!checkGuide.checked) {
        alert("확인사항은 필수 동의사항입니다");
    } else if (!checkPolicy.checked) {
        alert("개인정보처리방침은 필수 동의사항입니다");
    } else {
        location.href="applicationAddForm.html";
    }
});