const showPwBtn = document.getElementById('showPwBtn');

showPwBtn.addEventListener('mouseover',()=>{
    pw.setAttribute('type','text');
/*    setTimeout(function(){
        pw.setAttribute('type','password');
    },1000);*/

});

showPwBtn.addEventListener('mouseout',()=>{
    pw.setAttribute('type','password');
});