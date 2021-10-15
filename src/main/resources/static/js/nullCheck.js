const yesBtn = document.querySelector('.btn-yes');
const form = document.getElementById('memberForm');

const id = document.getElementById('id');
const pw = document.getElementById('pw');
const name = document.getElementById('name');
const yourEmail = document.getElementById('email');
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
    else if(pw.value == ""){
        pw.nextElementSibling.classList.add('warning');
        pw.focus();
        setTimeout(function() {
            pw.nextElementSibling.classList.remove('warning');
        },1500);
        return false;
    }
    else if(name.value == ""){
        name.nextElementSibling.classList.add('warning');
        setTimeout(function() {
            name.nextElementSibling.classList.remove('warning');
        },1500);
        name.focus();
        return false;
    }
    else if(tel.value == ""){
        tel.nextElementSibling.classList.add('warning');
        tel.focus();
        setTimeout(function() {
            tel.nextElementSibling.classList.remove('warning');
        },1500);
        return false;
    }

});//yesBtn 클릭 함수