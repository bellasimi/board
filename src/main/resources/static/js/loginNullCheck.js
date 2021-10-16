const yesBtn = document.querySelector('.btn-yes');
const form = document.getElementById('loginForm');

const id = document.getElementById('id');
const pw = document.getElementById('pw');


yesBtn.addEventListener('click',()=>{

    if(id.value == ""){
        id.nextElementSibling.classList.add('warning');
        setTimeout(function(){
            id.nextElementSibling.classList.remove('warning');
        },1500);
        id.focus();
        return false;
    }
    if(pw.value == ""){
        pw.nextElementSibling.classList.add('warning');
        setTimeout(function(){
            pw.nextElementSibling.classList.remove('warning');
        },1500);
        pw.focus();
        return false;
    }

    form.submit();

});//yesBtn 클릭 함수