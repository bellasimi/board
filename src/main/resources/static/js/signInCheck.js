const yesBtn = document.querySelector('.btn-yes');
const form = document.getElementById('signinForm');

const idReg = /^[a-zA-Z0-9]{4,12}$/;
const pwReg = /\d{4,8}/;
const emailReg = /^[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const telReg =/\d{9,11}/;
const nameReg =/^\w$/;

const elem = document.querySelectorAll('name');

const regMap = {
    id: {rule: idReg, msg:'id는 영문숫자혼합 4-12자로 입력해주세요!'},
    pw: {rule: pwReg, msg:'비밀번호는 숫자 4-8자로 입력해주세요!'},
    name: {rule: nameReg, msg:'이름은 문자로 입력해주세요!'},
    email: {rule: emailReg, msg:'이메일은 www@www.www형식으로 입력해주세요!'},
    tel: {rule:telReg, msg: '전화번호는 -를 빼고 입력해주세요!'}
};

const nullCheck = (elem) => {
    const value = elem.value;
    console.log(value);
    const checkEmpty = value.trim() === "";
    if(checkEmpty){
        elem.nextElementSibling.classList.add('warning');
        elem.focus();
        setTimeout(function(){
            elem.nextElementSibling.classList.remove('warning');
        },1500);
    }
    return checkEmpty;
};

const showError = (failed) => {
    console.log(failed);
};

yesBtn.addEventListener('click',() =>{
    const formElems = document.querySelectorAll('#signinForm input[name]');
        const failed = {};

        for(let i=0;i<formElems.length;i++){
            const elem = formElems[i];
            const reg = regMap[elem.name];
                if(nullCheck(elem) || !reg.rule.test(elem.value)){
                    alert(reg.msg);
                    elem.focus();
                    elem.select();
                    failed[i]={key:elem.value,msg:reg.msg};
                    break;
                }
        }

        if(failed.length === 0) {
            form.submit();
        }
        else{
            showError(failed[0]);
        }

});