const yesBtn = document.querySelector('.btn-yes');

/*const id = document.querySelector('#id');
const pw = document.querySelector('#pw');

const yourId = document.querySelector('#yourId');
const yourPw = document.querySelector('#yourId');
const name = document.querySelector('#yourName');
const tel = document.querySelector('#yourTel');*/

const id = document.getElementById('id');
const pw = document.getElementById('pw');

const yourId = document.getElementById('yourId');
const yourPw = document.getElementById('yourId');
const name = document.getElementById('yourName');
const tel = document.getElementById('yourTel');

yesBtn.addEventListener('click',() => {

  if(id.value == null){
        id.nextElementSibling.classList.toggle('warning');
    }
    else if(pw.value == null){
        pw.nextElementSibling.classList.toggle('warning');
    }
    else if(yourId.value == null){
        yourId.nextElementSibling.classList.toggle('warning');
    }
    else if(yourPw.value == null){
        yourPw.nextElementSibling.classList.toggle('warning');
    }
    else if(name.value == null){
        name.nextElementSibling.classList.toggle('warning');
    }
    else if(tel.value == null){
        tel.nextElementSibling.classList.toggle('warning');
    }

});


