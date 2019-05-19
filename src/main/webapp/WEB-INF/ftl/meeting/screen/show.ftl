<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${basePath}/screen/dist/css/barrager.css">
    <link type="text/css" rel="stylesheet" href="${basePath}/lx/css/styles.css"/>
    <script type="text/javascript" src="${basePath}/lx/comonjs/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/lx/comonjs/modernizr-2.6.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/lx/js/starbg.js"></script>
    <script type="text/javascript" src="${basePath}/screen/static/js/tinycolor-0.9.15.min.js"></script>
    <script type="text/javascript" src="${basePath}/screen/dist/js/jquery.barrager.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        canvas {
            display: block;
            margin: 0;
            width: 100%;
            height: 100%;
            position: fixed;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            z-index: -1;
        }

        .content {
            width: 1144px;
            margin: 0 auto;
            padding-top: 200px;
        }

        .rabitBg {
            position: fixed;
            left: 0;
            top: 48%;
        }

        .logo1 {
            position: fixed;
            left: 39.5%;
            top: 8%;
        }
        .ziti{
            position: fixed;
            left: 15%;
            top: 8%;
        }
    </style>
</head>
<body class="bb-js">
<canvas id="fullstarbg">你的浏览器不支持canvas标签</canvas>
<div class="content">
    <img src="${basePath}/lx/logo1.png" class="logo1" onclick="javascript:stop();"/>
    <img src="${basePath}/lx/xyp1.png" class="rabitBg" onclick="javascript:start();"/>
    <img src="${basePath}/lx/z.png" class="ziti"/>

</div>

<script type="text/javascript">
    //每条弹幕发送间隔
    var looper_time = 5 * 1000;
    //是否首次执行
    var run_once = true;
    var count = 10;
    var itemlist = [];
    var looper;
    var looper2;

//        $(function () {
//
//            do_barrager();
//        });

    function start() {
        do_barrager();
    }

    function stop() {
        clearInterval(looper);
        looper = 0;
        clearInterval(looper2);
        looper2 = 0;
        run_once = true;
    }

    function clear_barrage() {
        $.fn.barrager.removeAll();
    }

    function do_barrager() {

        if (run_once) {
            //如果是首次执行,则设置一个定时器,并且把首次执行置为false
            looper = setInterval(do_barrager, looper_time);
            looper2 = setInterval(showimg, 500);
            run_once = false;
        }

//        speed = Math.floor(Math.random() * 10);

        $.get('${basePath}/meeting/getscreen.shtml', {count: count}, function (result) {
            obj = eval(result);
            if (result && result.status != 200) {
                return;
            }

            if (obj != undefined) {
                itemlist = [];
                var len = obj.entity.length;
                for (var k = 0; k < len; k++) {
                    var strinfo = obj.entity[k].info;
                    var speed = 10;
                    if (strinfo.length > 15) {
                        speed = 9;
                    }
                    var item = {
                        'info': strinfo,
                        close: false,
                        speed: speed,
                        color: '#f6fe24',
                        old_ie_color: '#f6fe24'
                    };
                    itemlist.push(item);
                }
            }
        });
    }

    function showimg() {
        if (itemlist.length > 0) {
            for (var i = 0; i < itemlist.length;) {
                itemlist.splice(i, 1);
                $('body').barrager(itemlist[i]);
                break;
            }
        }
    }
</script>
</body>
</html>