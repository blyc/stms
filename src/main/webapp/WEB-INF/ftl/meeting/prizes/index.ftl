<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" href="${basePath}/prizes/css/rotate.css"/>
    <script type="text/javascript" src="${basePath}/prizes/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/prizes/js/rem.js"></script>
    <!--页面抽奖流程相关js-->
    <script type="text/javascript" src="${basePath}/prizes/js/rotate.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .jpBox {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            font-size: 0.375rem;
            color: #333333;
            white-space: nowrap;
        }

        .title {
            text-align: center;
            font-size: 16px;
            margin-top: 230px;
            height: 30px;
            margin-bottom: 30px;
        }

        body {
            background-image: url(${basePath}/prizes/img/timg1.jpg);
            background-size: 100%;
            background-repeat: no-repeat;

        }
        .img1:hover{
            transform: scale(1.2);
            transition-duration: 1s;

        }
    </style>
</head>
<body style="max-width: 640px;display: block;margin: auto">
<!--一来就将牌设置为反面状态-->
<div class="title"><input type="hidden" value="${prizescount}" id="prizescount"></div>
<div id="allParent2" class="Y-content">
<#if prizes_0?exists>
    <div class="item i1">
        <div class="parent">
            <div class="face">
                <div class="jpBox">
                    <img src="${basePath}/prizes/img/jp/${prizes_0}" style="width: 100%;height: 100%">
                </div>
            </div>
            <div class="back"><img src="${basePath}/prizes/img/1-1.png" class="img1" style="width: 100%;height: 100%;" ></div>
        </div>
    </div>
</#if>
<#if prizes_1?exists>
    <div class="item i2">
        <div class="parent">
            <div class="face">
                <div class="jpBox">
                    <img src="${basePath}/prizes/img/jp/${prizes_1}" style="width: 100%;height: 100%">
                </div>
            </div>
            <div class="back"><img src="${basePath}/prizes/img/1-1.png" class="img1" style="width: 100%;height: 100%;" ></div>
        </div>
    </div>
</#if>
<#if prizes_2?exists>
    <div class="item i3">
        <div class="parent">
            <div class="face">
                <div class="jpBox">
                    <img src="${basePath}/prizes/img/jp/${prizes_2}" style="width: 100%;height: 100%">
                </div>
            </div>
            <div class="back"><img src="${basePath}/prizes/img/1-1.png" class="img1" style="width: 100%;height: 100%;" ></div>
        </div>
    </div>
</#if>
<#if prizes_3?exists>
    <div class="item i4">
        <div class="parent">
            <div class="face">
                <div class="jpBox">
                    <img src="${basePath}/prizes/img/jp/${prizes_3}" style="width: 100%;height: 100%">
                </div>

            </div>
            <div class="back"><img src="${basePath}/prizes/img/1-1.png" class="img1" style="width: 100%;height: 100%;" ></div>
        </div>
    </div>
</#if>
<#if prizes_4?exists>
    <div class="item i5">
        <div class="parent">
            <div class="face">
                <div class="jpBox">
                    <img src="${basePath}/prizes/img/jp/${prizes_4}" style="width: 100%;height: 100%">
                </div>
            </div>
            <div class="back"><img src="${basePath}/prizes/img/1-1.png" class="img1" style="width: 100%;height: 100%;" ></div>
        </div>
    </div>
</#if>
    <div class="selectBox" id="">
        <div class="parent">
            <div class="face">
                <div class="jpBox">
                    <a href="">点我抽奖</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:goback();">返回</a>
                </div>
            </div>
            <div class="back"></div>
        </div>
    </div>
</div>
<script type="text/javascript">

    function goback() {
        window.location.href = "cj/j5.shtml";
    }
//    //提示框
//    function showTool(str) {
//        var ele = document.getElementById("tooltip");
//        ele.innerHTML = str;
//        ele.style.display = "";
//        setTimeout(hideTool, 1000);
//    }
//    function hideTool(str) {
//        var ele = document.getElementById("tooltip");
//        ele.innerHTML = str;
//        ele.style.display = "none";
//    }
    window.onload = function () {

        if ($("#prizescount").val() == 0) {
            //showTool("抽奖结束");
            return;
        }
        //一来就播放动画且设置能翻牌的数量
        var obj2 = $("#allParent2").rotateEx({
            maxNum: 5,
            maxNumCall: function () {
//                showTool("翻到了最大的数量啦");
            },
            clickAmtStart: function (o1, o2, o3) {
                $(".jpBox").css("display", "block");
                //showTool("恭喜你");
            },
            changeAmtCall: function (o1, o2) {
                //随机修改奖品的位置
                obj2.reset();
            }
        });
        obj2.rotate.allBack();
    }
</script>
</body>
</html>
