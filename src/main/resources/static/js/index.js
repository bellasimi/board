const toggleBtn = document.querySelector('#nav_Btn');
const menu = document.querySelector('.nav_menu');

toggleBtn.addEventListener('click',() => {
    menu.classList.toggle('active');
});