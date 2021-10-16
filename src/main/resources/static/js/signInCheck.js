
const form = document.getElementById('signinForm');

const idReg = /^[a-zA-Z0-9]{4,12}$/;
const pwReg = /\d{4,8}/;
const emailReg = /^[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const telReg =/\d{9,11}/;

yesBtn.addEventListener('click',()=>{
    if(!idReg.test(id.value)){
        alert("id는 영문숫자혼합 4-12자로 입력해주세요!");
        id.select();
        id.focus();
        return false;
    }
    if(!pwReg.test(pw.value)){
        alert("비밀번호는 숫자 4-8자로 입력해주세요!");
        console.log(pw.value)
        pw.select();
        pw.focus();
        return false;
    }
    if(!emailReg.test(email.value)){
        alert("이메일은 www@www.www형식으로 입력해주세요!");
        email.select();
        email.focus();
        return false;
    }
    if(!telReg.test(tel.value)){
        alert("전화번호는 -를 빼고 입력해주세요!");
        tel.select();
        tel.focus();
        return false;
    }

        form.submit();
});