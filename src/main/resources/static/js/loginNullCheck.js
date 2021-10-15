const yesBtn = document.querySelector('.btn-yes');
const form = document.getElementById('loginForm');

const id = document.getElementById('id');
const pw = document.getElementById('pw');


yesBtn.addEventListener('click',()=>{
    if(id.value == ""){
        id.nextElementSibling.classList.toggle('warning');
        alert("id는 영문숫자혼합 4-12자로 입력해주세요!");
        id.focus();
        return false;
    }
    if(pw.value == ""){
        pw.nextElementSibling.classList.toggle('warning');
        alert("비밀번호는 숫자 4-8자로 입력해주세요!");
        pw.focus();
        return false;
    }

    form.submit();

});//yesBtn 클릭 함수