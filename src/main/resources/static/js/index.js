const toggleBtn = document.querySelector('#nav_Btn');

const menu = document.querySelector('.nav_menu');
let admin_menu = document.querySelectorAll('.nav_menu .admin_menu');

toggleBtn.addEventListener('click',() => {
    menu.classList.toggle('active');
});

/*관리자메뉴 active 추가 */
for(let i=0;i<admin_menu.length;i++) {
    toggleBtn.addEventListener('click', () => {
        admin_menu[i].classList.toggle('active');
    });

}
