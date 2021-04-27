let images = ['https://images5.alphacoders.com/670/670920.jpg',
    'https://images6.alphacoders.com/106/1065657.png',
    'https://nmbimg.fastmirror.org/image/2020-08-05/5f29edcc16151.jpg',
    'https://i.imgur.com/bKEDU7f.png',
    'https://w.wallhaven.cc/full/r2/wallhaven-r2my1m.png',
    'https://preview.redd.it/amj1nrmkuvg31.jpg?auto=webp&s=78b0d3ffbbefd2e2d5fec9f799b62616312c59c0',
    'https://wallpapercave.com/wp/wp5433339.jpg',
    'https://pic4.zhimg.com/v2-e8c5c3b5067dd9dd91e1e3ba7a55c188_r.jpg',
    'https://img5.goodfon.ru/wallpaper/nbig/3/f5/umbrella-anime-girls-anime-wallpapers-anime-girl-rain-drops.jpg',
    'https://wallpaperaccess.com/full/1982235.jpg',
    'https://images5.alphacoders.com/972/972578.jpg',
    'https://images2.alphacoders.com/920/920636.jpg',
    'https://cs8.pikabu.ru/post_img/big/2017/10/10/4/1507611614179071332.jpg',
    'https://winzoro.net/uploads/posts/2019-03/1551890292_highschool-dxd-rias-hd-live-wallpaper.jpg',
    'https://storge.pic2.me/c/1360x800/131/52ukxvr2449n.jpg'];

window.onload = function () {
    randomBackgroundImage();
};

function randomBackgroundImage() {
    let image = Math.floor(Math.random() * images.length) + 1;
    document.body.style.backgroundImage = 'url("'+ images[image] + '")';
}
