const toggleBtn = document.querySelector('#nav_Btn');
const menu = document.querySelector('.nav_menu');
let admin_menu = document.querySelectorAll('.admin_menu');
console.log("관리자 class"+ admin_menu[0]);
console.log("관리자 class"+ admin_menu[1]);
toggleBtn.addEventListener('click',() => {
    menu.classList.toggle('active');
    admin_menu.classList.toggle('active')
});
/*관리자메뉴 active 추가 */
for(const admin_menu in admin_menu) {
    toggleBtn.addEventListener('click',() => {
        admin_menu.classList.toggle('active');
    });

}
