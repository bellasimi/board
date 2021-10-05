const yesBtn = document.querySelector('.btn-yes');

const id = document.querySelector('#id');
const pw = document.querySelector('#pw');

const yourId = document.querySelector('#yourId');
const yourPw = document.querySelector('#yourId');
const name = document.querySelector('#yourName');
const tel = document.querySelector('#yourTel');


yesBtn.addEventListener('click',() => {

    console.log("3"+id.valueOf());
    if(id.val() == ''){
        id.nextElementSibling.classList.toggle('warning');
    }
    else if(pw == ''){
        pw.nextElementSibling.classList.toggle('warning');
    }
    else if(yourId == ''){
        yourId.nextElementSibling.classList.toggle('warning');
    }
    else if(yourPw == ''){
        yourPw.nextElementSibling.classList.toggle('warning');
    }
    else if(name == ''){
        name.nextElementSibling.classList.toggle('warning');
    }
    else if(tel == ''){
        tel.nextElementSibling.classList.toggle('warning');
    }

});


