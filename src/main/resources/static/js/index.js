const toggleBtn = document.querySelector('#nav_Btn');

/*nav_menu active 추가*/
const menu = document.querySelector('.nav_menu');

toggleBtn.addEventListener('click',() => {
    menu.classList.toggle('active');
});

/*admin_menu active 추가 */
let admin_menu = document.querySelectorAll('.nav_menu .admin_menu');

for(let i=0;i<admin_menu.length;i++) {
    toggleBtn.addEventListener('click', () => {
        admin_menu[i].classList.toggle('active');
    });
}