let images = ['https://images6.alphacoders.com/106/1065657.png',
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
    'https://storge.pic2.me/c/1360x800/131/52ukxvr2449n.jpg',
    'https://krot.info/uploads/posts/2020-03/1585066342_38-p-shkolnie-krutie-foni-108.jpg',
    'https://www.wallpaperup.com/uploads/wallpapers/2015/01/10/580859/bb21c98166ba0e918416a83b36fa19c0.jpg',
    'https://images3.alphacoders.com/266/thumb-1920-266127.jpg',
    'https://images5.alphacoders.com/735/thumb-1920-735185.png',
    'https://ccute.cc/wp-content/uploads/2018/07/5469-9.jpg',
    'https://kartinkinaden.ru/uploads/posts/2021-02/1612323268_16-p-anime-devushka-lezhit-art-kartinki-20.jpg',
    'https://i.pinimg.com/originals/d8/a7/04/d8a70406c09b9c0705058c286306595d.jpg',
    'https://images4.alphacoders.com/858/thumb-1920-858176.jpg',
    'https://static4.hentai-img.com/upload/20170516/301/307466/48.jpg',
    'https://www.wallpaperup.com/uploads/wallpapers/2013/11/23/178627/72fb79573791883a4aabbe5bce813e4e.jpg',
    'https://www.desktopbackground.org/download/2880x1800/2013/07/05/602283_kazuharu-kina-computer-wallpapers-desktop-backgrounds_4299x3022_h.jpg',
    'https://static.zerochan.net/IA.full.1486414.jpg',
    'https://nfg-sofun.s3.amazonaws.com/uploads/redactor_rails/picture/data/54025/birds_summer_camp.002.jpeg',
    'https://www.wallpaperup.com/uploads/wallpapers/2015/01/06/575962/2824eb664aaae97fbea252008d3ae5c8.jpg',
    'https://i.pinimg.com/originals/5d/5d/b9/5d5db9926deee79f59ea26af7f4fd2af.jpg',
    'https://avatars.mds.yandex.net/get-zen_doc/2746214/pub_5efd9690b84d394691ce74b2_5efda914145e72165cc94478/scale_1200'
];

window.onload = randomBackgroundImage();

function randomBackgroundImage() {
    let image = Math.floor(Math.random() * images.length) + 1;
    document.body.style.backgroundImage = 'url("' + images[image] + '")';
}

$(document).ajaxComplete(function (e, xhr, settings) {
    console.log(xhr)
    let status = xhr.status;
    if (status != null) {
        if (status >= 300 && status <= 399) {
            alert('Here is the redirect. Need to fix logic\n' + result)
            document.location.href = result.value;
            e.preventDefault();
        } else if (status === 403) {
            alert('Access denied');
            e.preventDefault();
        }
    }
});

function formAsJSON(form) {
    const array = jQuery(form).serializeArray();
    const json = {};

    jQuery.each(array, function () {
        json[this.name] = this.value || '';
    });

    return json;
}

function formAsJson(form) {
    const o = {};
    const a = form.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}