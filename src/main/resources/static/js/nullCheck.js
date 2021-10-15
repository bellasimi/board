const yesBtn = document.querySelector('.btn-yes');
const form = document.getElementById('memberForm');

const id = document.getElementById('id');
const pw = document.getElementById('pw');
const name = document.getElementById('name');
const yourEmail = document.getElementById('email');
const tel = document.getElementById('tel');


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
    if(name.value == ""){
        name.nextElementSibling.classList.toggle('warning');
        alert("이름을 입력해주세요!");
        name.focus();
        return false;
    }
    if(tel.value == ""){
        tel.nextElementSibling.classList.toggle('warning');
        alert("전화번호는 -를 빼고 입력해주세요!");
        tel.focus();
        return false;
    }
    form.submit();

});//yesBtn 클릭 함수