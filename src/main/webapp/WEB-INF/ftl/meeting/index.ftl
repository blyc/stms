<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>2018年会抽奖</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/style/style.css">
    <link rel="stylesheet" href="${basePath}/style/my.css"/>
    <script src="${basePath}/style/jquery.min.js"></script>
    <script src="${basePath}/style/covervid.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <#--<script baseUrl="${basePath}" src="${basePath}/style/cj.js"></script>-->

    <script>
        function j1() {
            window.location.href = "cj/j1.shtml";
        }
        function j2() {
            window.location.href = "cj/j2.shtml";
        }
        function j3() {
            window.location.href = "cj/j3.shtml";
        }
        function j4() {
            window.location.href = "cj/j4.shtml";
        }
        function j5() {
            window.location.href = "cj/j5.shtml";
        }
    </script>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        body {
            text-align: center;
            background-image: url(${basePath}/style/images/bg_index.png);
            background-size: 100%;
            height: auto;
            background-repeat: no-repeat;
        }
    </style>
</head>

<body class="demo2">
<div style="width:100%; height:300px;position: absolute;top:230px;left:0;right:0;">
    <div style="width:23%; height:38%;position: absolute;top:0;left:23%;right:0;cursor: pointer;"
         onclick="javascript:j1();"></div>
    <div style="width:23%; height:38%;position: absolute;top:0;left:54%;right:0;cursor: pointer;"
         onclick="javascript:j2();"></div>
    <div style="width:23%; height:38%;position: absolute;top:170px;left:7%;right:0;cursor: pointer;"
         onclick="javascript:j3();"></div>
    <div style="width:23%; height:38%;position: absolute;top:170px;left:38%;right:0;cursor: pointer;"
         onclick="javascript:j4();"></div>
    <div style="width:23%; height:38%;position: absolute;top:170px;left:69%;right:0;cursor: pointer;"
         onclick="javascript:j5();"></div>
</div>

</body>
</html>