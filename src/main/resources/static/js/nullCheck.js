const yesBtn = document.querySelector('.btn-yes');

const id = document.getElementById('id');
const pw = document.getElementById('pw');
const name = document.getElementById('name');
const email = document.getElementById('email');
const tel = document.getElementById('tel');

yesBtn.addEventListener('click',()=>{
    if(id.value == ""){
        id.nextElementSibling.classList.add('warning');
        id.focus();
        setTimeout(function() {
            id.nextElementSibling.classList.remove('warning');
        },1500);
        return false;
    }
    if(pw.value == ""){
        pw.nextElementSibling.classList.add('warning');
        pw.focus();
        setTimeout(function() {
            pw.nextElementSibling.classList.remove('warning');
        },1500);
        return false;
    }
    if(name.value == ""){
        name.nextElementSibling.classList.add('warning');
        setTimeout(function() {
            name.nextElementSibling.classList.remove('warning');
        },1500);
        name.focus();
        return false;
    }
    if(email.value == ""){
        email.nextElementSibling.classList.add('warning');
        setTimeout(function() {
            email.nextElementSibling.classList.remove('warning');
        },1500);
        email.focus();
        return false;
    }
    if(tel.value == ""){
        tel.nextElementSibling.classList.add('warning');
        tel.focus();
        setTimeout(function() {
            tel.nextElementSibling.classList.remove('warning');
        },1500);
        return false;
    }

});//yesBtn 클릭 함수