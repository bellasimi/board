const box = document.getElementById('messageBox');
const closeMsgBtn = document.getElementById('closeMsgBtn');

closeMsgBtn.addEventListener('click',()=>{
    messageBox.setAttribute('style','visibility:hidden');
});