layui.use(['table', 'form', 'layer', 'jquery'], function () {
    var table = layui.table,
        $ = layui.$,
        form = layui.form;
    let userType = window.sessionStorage.getItem("userType");
    let enterpriseCols=[
        {field: 'id', title: 'id', hide: true},
        {field: 'orgName', title: '企业名称', align: 'center'},
        {field: 'name', title: '岗位名称', align: 'center'},
        {field: 'welfare', title: '福利待遇', align: 'center'},
        {field: 'introduce', title: '公司简介', align: 'center'},
        {field: 'telephone', title: '联系电话', align: 'center'},
        {field: 'ask', title: '任职要求', align: 'center'},
        {title: '操作', align: 'center', fixed: 'right', toolbar: '#userListOpt'}
    ]
    tableIns = table.render({
        elem: '#userList',
        page: true,//开启分页
        url: '/employment/list',
        cellMinWidth: 95,
        height: 'full-25',
        limit: 20,//每页条数
        limits: [10, 15, 20, 25],//可切换的每页条数
        autoSort: false,
        id: 'userList',
        toolbar: '#usersToolBar',
        method: "get",
        cols: [enterpriseCols]
    });
    if (userType === '2') {
        console.log("学生不能编辑招聘信息，执行了删除表格操作")
        enterpriseCols.pop( {title: '操作', align: 'center', fixed: 'right', toolbar: '#userListOpt'})
    }
    form.on("submit(doSearch)", function (data) {
        tableIns.reload({
            where: data.field,
            page: {
                curr: 1
            }
        });
        return false;
    });

    //监控表格工具条事件
    table.on("toolbar(userList)", function (obj) {
        switch (obj.event) {
            case 'userAdd':
                openAddLayer(0);
                break;
            case 'userBatchDel':
                batchDelete();
                break;
        }
        ;
    });

    //监控行工具条事件
    table.on("tool(userList)", function (obj) {
        //获取当前行数据
        var data = obj.data;
        switch (obj.event) {
            case 'userEdit':
                userEdit(data);
                break;
            case 'userDel':
                userDel(data.id);
                break;
        }
        ;
    });

    //添加企业
    form.on("submit(userSubmitBtn)", function (data) {
        var url = "/employment/addEnterprise";
        if (data.field.id != "") {
            url = "/employment/updateEnterprise";
        }
        $.post(url, data.field, function (result) {
            if (result.code == 0) {
                tableIns.reload();
            }
            layer.msg(result.msg);
            layer.close(mainIndex);
        });
    });

    var mainIndex;

    //弹出添加页面
    function openAddLayer(userType) {
        mainIndex = layer.open({
            type: 1,
            content: $("#addOrUpdateUser"),
            area: ['800px', '550px'],
            title: '添加企业',
            success: function () {
                $("#addOrUpdateForm")[0].reset();
                //设置下拉树中父节点的值为空
                $("[name='id']").val("");
            }
        });
    }

    function userEdit(data) {
        mainIndex = layer.open({
            type: 1,
            content: $("#addOrUpdateUser"),
            area: ['800px', '550px'],
            title: '编辑企业信息',
            success: function () {
                $("#addOrUpdateForm")[0].reset();
                //装载新的数据
                form.val("addOrUpdateForm", data);
            }
        });
    }

    function userDel(id) {
        layer.confirm('确定删除吗？', {icon: 3, title: '提示'}, function (index) {
            $.post('/employment/del/' + id, function (result) {
                if (result.code == 0) {
                    tableIns.reload();
                }
                layer.msg(result.msg);
            });
        });
    }
});
