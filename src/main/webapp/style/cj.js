var hxinm = [];
var hid = [];
var hcount = 0;
var ids = [];
var nms = [];

var xinm = [];
var uid = [];
var pcount = 0;//参加人数
var runing = true;
var td = 0;//，名额
var num = 0;
var t;
var baseUrl = $("script[baseUrl]").attr('baseUrl');
var index = 0;//当前第几个人
var audio;
$(function () {
    audio = document.getElementById('music1');
    td = Number($('.tnum').val())- Number($('.tcount').val());
    index = Number($('.tcount').val());

    $.get(baseUrl + '/meeting/getCuser.shtml', function (result) {
        var obj = eval(result.info);
        for (var i = 0; i < obj.length; i++) {
            xinm.push(obj[i].name)
            uid.push(obj[i].id);

        }
        pcount = xinm.length;
    });

    $.get(baseUrl + '/meeting/getHCuser.shtml', {flg: $(".ctypeid").val()}, function (result) {
        var obj = eval(result.info);
        for (var i = 0; i < obj.length; i++) {
            hxinm.push(obj[i].name);
            hid.push(obj[i].id);
        }
        hcount = hxinm.length;
    });

});

function goprize() {
    if (runing == true) {
        window.location.href = baseUrl + "/meeting/prizes.shtml";
    }
}


function goback() {
    if (runing == true) {
        if (!audio.paused) {
            audio.pause();
        }
        window.location.href = baseUrl + "/meeting/index.shtml";
    }
}
//开始停止
function start() {
    if (runing) {
        if (td <= 0) {
            return layer.msg('投票结束。'), !1;
        }
        if (xinm.length > 0) {
            ids = [];
            nms = [];
            runing = false;
            $('#btntxt').removeClass('start').addClass('stop');
            $('#btntxt').html('停止');
            startNum();
        }
    }
    else {
        if (xinm.length > 0) {
            runing = true;
            $('#btntxt').removeClass('stop').addClass('start');
            $('#btntxt').html('开始');
            stop();
            zd();
            u();
        }
    }
    //音乐播放暂停
    if (audio.paused) {
        audio.play();//audio.play();// 播放
    }
    else {
        //audio.pause();// 暂停
        audio.pause();
    }
}
//循环参加名单
function startNum() {
    num=RandomNum(0,pcount);
    $('.name').html(xinm[num]);
    t = setTimeout(startNum, 100);
}

//min ≤ r < max
function RandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    var num = Min + Math.floor(Rand * Range); //舍去
    return num;
}

//停止跳动
function stop() {
    clearInterval(t);
    t = 0;
}

//打印列表
function zd() {
    if (td <= 0) {
        alert("投票结束");
    } else {
        if(hcount>0){
            $('.name').html(hxinm[0]);
            ids.push(hid[0]);
            nms.push(hxinm[0]);

             td = td - 1;

            //将已中奖者从数组中"删除",防止二次中奖
            xinm.splice($.inArray(nms[0], xinm), 1);
            hxinm.splice($.inArray(nms[0], hxinm), 1);
            hid.splice($.inArray(ids[0], hid), 1);

            index = index + 1;
            $('.c1').html(index);
        }
        pcount = xinm.length;
        hcount = hxinm.length;
    }
}

//更新数据
function u() {
    var load = layer.load();
    $.post(baseUrl + '/meeting.shtml', {ids: ids.join(','), flg:$(".ctypeid").val()}
        , function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message), !1;
            }
        }, 'json');
}


//音乐
function a() {
    //window.parent.location.reload();
}
