layui.use('jquery', function () {
    let $ = layui.$;
    let userType = window.sessionStorage.getItem("type");
    let userName = window.sessionStorage.getItem("userName");
    // if (userType != 0) {
    //     $('.user-manage-item').remove();
    // }
    console.log("用户权限：" + userType);
    switch (userType) {
        case '0':
            break;
        case '1':
            $('.user-manage-item').remove();
            break;
        case '2':
            $('.user-manage-item,.employment-statistic-item,.employment-announcement-item').remove();
            break;
        case '3':
            $('.employment-info-item,.employment-statistic-item,.user-manage-item').remove();
            break;
        default:
            console.log("删除多余导航栏失败");
    }
    $('.user-name').text(userName);

    $('.logout-btn').on('click', function () {
        window.sessionStorage.clear();
        location.href = "/employment";
    });
    element.render('nav', 'demo-filter-nav');
});
