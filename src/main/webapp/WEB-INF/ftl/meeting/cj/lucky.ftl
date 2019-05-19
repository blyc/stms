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
    <script baseUrl="${basePath}" src="${basePath}/style/cj.js"></script>
</head>

<body>
<div class="box">
    <div class="jz">
        <span class="name">${ctype.tname}</span><br/>
        <input type="hidden" class="tnum" value="${ctype.tnum}">
        <input type="hidden" class="ctypeid" value="${ctype.id}">
        <input type="hidden" class="tcount" value="${ctype.tcount}">
        <audio src="${basePath}/style/music/cjmusic.mp3" controls="controls" preload loop id="music1" hidden>
        </audio>
        <div class="start" id="btntxt" onclick="start()">开始</div>
        <div class="goback" onclick="goback()">返回</div>
        <div class="goprize"></div>
    </div>

    <div class="zjmd">
        <div class="list">
            已抽：<span class="c1">${ctype.tcount}</span>人 / ${ctype.tnum}人 &nbsp;&nbsp;&nbsp;&nbsp; 当前：<span
                class="c1">${ctype.tcount}</span>人
        </div>
    </div>
</div>

</body>

</html>