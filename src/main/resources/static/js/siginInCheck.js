const yesBtn = document.querySelector('.btn-yes');
const form = document.getElementById('memberForm');

const yourId = document.getElementById('yourId');
const yourPw = document.getElementById('yourPw');
const yourName = document.getElementById('yourName');
const yourEmail = document.getElementById('yourEmail');
const yourTel = document.getElementById('yourTel');

const idReg = /^[a-zA-Z0-9]{4,12}$/;
const pwReg = /\d{4,8}/;
const emailReg = /^[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const telReg =/^d{9,11}/;

yesBtn.addEventListener('click',()=>{
    if(!idReg.test(yourId.value)){
        alert("id는 영문숫자혼합 4-12자로 입력해주세요!");
        yourId.select();
        yourId.focus();
        return false;
    }
    if(!pwReg.test(yourPw.value)){
        alert("비밀번호는 숫자 4-8자로 입력해주세요!");
        console.log(yourPw.value)
        yourPw.select();
        yourPw.focus();
        return false;
    }
    if(!emailReg.test(yourEmail.value)){
        alert("이메일은 www@www.www형식으로 입력해주세요!");
        yourEmail.select();
        yourEmail.focus();
        return false;
    }
    if(!telReg.test(yourTel.value)){
        alert("전화번호는 -를 빼고 입력해주세요!");
        yourTel.select();
        yourTel.focus();
        return false;
    }

        form.submit();
});