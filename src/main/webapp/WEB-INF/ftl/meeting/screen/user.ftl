<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${basePath}/mui/js/mui.min.js"></script>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <link href="${basePath}/mui/css/mui.min.css" rel="stylesheet"/>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
</head>
<style type="text/css">
    .mui-content {
        position: absolute;
        width: 100%;
        height: 100%;
        background: url(${basePath}/mui/img/bg2.jpg) no-repeat;
        background-size: 100% 100%;

    }

    #form1 {
        margin: 15% 10% 20px 10%;
        BACKGROUND-COLOR: transparent;
    }

    #text1 {
        line-height: 50px !important;
    }

    .mui-input-group .mui-input-row {
        height: 50px;
        line-height: 50px
    }

    #btn1 {
        margin-left: 10%;
        margin-right: 10%;
        width: 80%;
        height: 40px;
        opacity: 0.7;
        font-size: 18px;
    }

    #logo {
        width: 70%;
        margin-top: 30%;
        margin-left: 10%;
    }
</style>
<body>
<div id="divMain" class="mui-content">
    <img id="logo" src="${basePath}/mui/img/logo.png"/>
    <form id="form1" class="mui-input-group">
        <div class="mui-input-row">
            <input id="text1" type="text" class="mui-input-clear" placeholder="请输入弹幕文字信息" required maxlength="20">
        </div>
    </form>
    <button id="btn1" type="button" class="mui-btn mui-btn-blue">发送</button>
</div>
<script type="text/javascript">
    var btn1 = document.getElementById('btn1');
    btn1.addEventListener('tap', function () {

        var info = $.trim($("#text1").val());

        if ($.trim(info) == '') {
            mui.alert("弹幕信息不能玩为空", "错误");
            return;
        }

        if (info.length > 20) {
            mui.alert("信息不能超过20个字符", "错误");
            return;
        }

        mui.confirm("确定发送", "提示", ['确定', '取消'], function (e) {
            if (e.index == 1) {
                return;
            }

            $("#text1").val("");

            var load = layer.load();

            $.post("${basePath}/meeting/addscreen.shtml", {
                        info: info
                    },
                    function (result) {
                        layer.close(load);
                        if (result && result.status != 200) {
                            mui.alert(result.message, "错误");
                            return;
                        }
                        mui.alert("保存成功");
                    }, 'json');
        });
    })
</script>
</body>
</html>