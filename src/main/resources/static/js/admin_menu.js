/*타임스크립트 값 가능? */
const id =[[${id}]];
const admin_menu = document.querySelectorAll('.admin_menu');

for(let i=0;i<admin_menu.length;i++){
    if(id == "admin"){
       admin_menu[i].setAttribute('style','visibility: visible');
    }
}
