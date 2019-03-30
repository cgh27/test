<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据采集任务--${site.name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
    <style>
        .layui-table-cell {
            overflow: hidden;
        }

        .layui-table-view .layui-table {
            width: -webkit-fill-available;
        }
    </style>


</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <div class="layui-field-box">
        <div class="layui-form">
            <form class="layui-form">

                <p>
                <div class="layui-inline">
                    <a style="background-color: #0061aa;" class="layui-btn" data-type="addTask">去结算</a>
                </div>
                </p>

                <div class="layui-inline">
                    <input type="text" value="" name="s_BName" placeholder="输入书籍名称" class="layui-input search_input">
                </div>
                <div class="layui-inline">
                    <a style="background-color: #0061aa;" class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                </div>
            </form>
        </div>
    </div>
</fieldset>
<table class="layui-table" id="test" lay-filter="demo"></table>


<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">加入购物车</a>
</script>
<script>
    layui.use(['layer', 'form', 'table'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            t;                  //表格数据变量
        t = {
            elem: '#test',
            url: '${base}/user/showBooklist',
            method: 'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [10, 50],
                theme: '#0061aa'
            },
            width: '100%',
            cols: [[
                {field: 'uuid', title: '序号', width: 70},
                {
                    field: 'BName',
                    title: '书籍名称',
                    width: 100,
                    templet: '<div><span title="{{d.BName}}">{{d.BName}}</span></div>'
                },
                {field: 'BAtr', title: '书籍作者', width: 150},
                {field: 'BTp', title: '书籍类型', width: 90},
                {field: 'BPrc', title: '书籍价格', width: 90},
                {field: 'SfDate', title: '上架时间', width: 150},
                {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 200}

            ]]
        };
        table.render(t);


        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var editIndex = layer.open({
                    title: "编辑任务",
                    type: 2,
                    content: "${base}/jxzg/sjcjrw/edit?id=" + data.ID,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layer.tips('点击此处返回任务列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if (obj.event === "del") {
                layer.confirm("你确定要删除该任务吗？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        console.log(data);
                        $.ajax({
                            type: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({id: data.ID}),
                            url: '/jxzg/sjcjrw/delete',
                            success: function (res) {
                                if (res.success) {
                                    layer.msg("删除成功", {time: 1000}, function () {
                                        table.reload('test', t);
                                    });
                                } else {
                                    layer.msg(res.message);
                                }
                            }
                        })
                    }
                )
            }
        });


        //功能按钮
        var active = {
            addTask: function () {
                var addIndex = layer.open({
                    title: "添加任务",
                    type: 2,
                    content: "${base}/jxzg/sjcjrw/add",
                    success: function (layero, addIndex) {
                        setTimeout(function () {
                            layer.tips('点击此处返回任务列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function () {
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            }
            //搜索

        };


        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        form.on("submit(searchForm)", function (data) {
            console.log(data.field);
            t.where = data.field;
            table.reload("test", t);
            // $.ajax({
            //     type:'POST',
            //     contentType:'application/json',
            //     data:JSON.stringify({keyword: $("input[name='s_key']").val()}),
            //     url:'/jxzg/sjcjrw/search',
            //     success:function (res) {
            //         if (res.success) {
            //                 table.reload('test', t);
            //             }
            //         else {
            //             layer.msg(res.message);
            //         }
            //     }
            // });
            return false;
        });


    });
</script>
</body>
</html>