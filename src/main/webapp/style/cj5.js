//两个数组的坐标对应，后期可以直接连数据库 读取数据
// var xinm = ["姜昆","姜昆","姜昆","姜昆","姜昆","姜昆","姜昆","陈一","陈二","陈三","陈四","胡宝国","孟哲","吕昌峰","马遥遥","孙勇豪","丁益龙","杨同凯","隋学斌","徐文杰","曹成萌","张燕","柳苗苗","巩召凯","李百静","王娟","王康振","张益","张政",
//             "韩翠莲","霍名扬","范苗苗","王帅帅","张思邈","张露","付婷婷","曹春熙","武保谦","康泽新","焦方涛","满志鹏","王继康","张鹤亭","穆雄雄","孙浩","陈立亮","李晓",
//             "李昂","张瑞乾","马有坤","孙中天","金仲冠","石少强","娄帅","尹毅","韩俊江","许召旺",
//             "赵延华","石恩豪","侯胜文","刘敏","陈涛","王鑫","郭建师","杜湘仪","张浩","王西波","赵佳乐","孙山耕","孙昊","黄业兵","闫腾飞","王格","周葳","汪富冉","林涵","刘新","孔祥云","刘群山","李德雨","马晓晨","储连豪","李耀","孙明辉",
//             "万业福","董书鑫","路广","赵红星","王荣昌","李书啸","朱晓岩","贾奔","赵德新","张梓杰","冯国益","赵彦瑾","张浩","尹家琦","孙召洋","刘振","刘志浩","宋永森","刘保康","乔广强",
//             "刘腾飞","朱翠莲","王春雨","殷永泽","赵瑞泽","付茂其","李凤祥","金恒元","郑大山","刘纪超","彭庆翔","胡文超","赵耀","耿小华","张瑞中","张鑫","李善强","孙斌","周秦汉","曲廷鑫","刘涛","主雪勇","王芝桐","刘慧鑫","徐桂臣","房茂畅",
//             "韩茂恒","许言琪","刘金波","孔钦海","阚运成","贾振刚","郑之龙","赵福鑫","秦  静","刘佳佳","刘文达","赵福勇","尹  平","刘  震","王圣鑫","王雪杨","宋文杰","李  壮","王  鹏","陈金明","秦洪坤","刘晓宇","刘超群","符延统","柴英杰","夏一辛","侯全爽",
//             "赵宗猛","贺子洋","盖天利","包鲁昌","田双勇","张婉君","孙才","闫光泽","闫青","孔永顺","吴优","霍兆威","靳瑞刚","于潇","商佳","陈志威","马汉超","王福虎","韩啸","李静","娄洪利","丁晓林","刘云虎","张龙霄","吕大伟","杨志鸿","施鹏振","徐海鹏","李现振","赵元昊","李元昌",
//             "于广阔","程雨杭","张国祥","甲丁","邵明奎","赵鑫呈","董治平","隋润泽","武祥可","王帅","谢东晋","张国强","薛天铖","齐吉波","李风澳","张海鹏","韩同臻","袁浩明","解慧文","孙国栋","杨港","端祥雲","徐昊东","马明欣","商显旭","李文杰","王美丽","郑妍妍","陈仪进","高洪亭",
//             "张振跃","朱立伟","张明冬","李  兵","聂孝磊","李  琳","潘业港","季宝才","孟德志","赵  齐","闫  凯","董良壮","王  冲","李忠治","胡业红","宁述言","李宗才","沈文科","马振宇","王  霞","田  新","冯  娟","朱从艺","徐一斌","平  旭","杨国柱",
//             "李金扬", "邓天赐","陈志杰","王浩","刘杨","姜昆","周哲","焦灵钰","葛爱敏","王景","纪世杰", "许思远","门光宇","张鑫源","刘旭","徐依超","梁辰","郑文硕","马科源","韩云龙","张天昊","宋风源",
//             //学士后
//             "李秀生","尹潇","肖会加","李沁雨","赵代鑫","杨小鸽","丛发民","胡大强","杨兆政","张林振","王丹","殷亮","张兴龙","周磊","王扩","杨金园","黄昕玲","杨正","李川","乔峰","桑宝恒","杜凯歌","王琨","卞功航",
//             "袁伟凯","赵洋","王路","王登强","庞玉波","杜书朝","杜解生","刘云芝","赵艳娇","范佳瑞","赵长琦","耿孟帅","胡宁涛","陈金强","王存泉","吕硕硕","王绪阳","闫循瑞","李昊阳","袁纪豪","张润泽","张忠魁","王永民",
//             "齐洪祥","叶震","廉文","郑玉浩","胡宗章","高明德","孙庆超","李兆福","孟祥明","郭滕","谢宗烜","张丛阵","戴金鸣" ];
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
    nametxt = $('.name');
    audio = document.getElementById('music1');
    td = Number($('.tnum').val())- Number($('.tcount').val());
    index = Number($('.tcount').val());

    $.get(baseUrl + '/meeting/getCuser.shtml', function (result) {
        var obj = eval(result.info);
        for (var i = 0; i < obj.length; i++) {
            xinm.push(obj[i].name)
            uid.push(obj[i].id);

        }
        pcount = xinm.length - 1;
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
    num = Math.floor(Math.random() * pcount);
    $('.name').html(xinm[num]);
    $(".phone").val(uid[num]);
    t = setTimeout(startNum, 100);
}

//更新数据
function u() {
    var load = layer.load();
    $.post(baseUrl + '/meeting.shtml', {id: $(".phone").val(), flg:$(".ctypeid").val()}
        , function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message), !1;
            }
        }, 'json');
}

//停止跳动
function stop() {
    pcount = xinm.length - 1;
    clearInterval(t);
    t = 0;
}

//打印列表
function zd() {
    if (td <= 0) {
        alert("投票结束");
    } else {
        index = index + 1;
        $('.c1').html(index);
        //将已中奖者从数组中"删除",防止二次中奖
        xinm.splice($.inArray(xinm[num], xinm), 1);
        uid.splice($.inArray(uid[num], uid), 1)
        td = td - 1;
    }
}

//音乐
function a() {
    //window.parent.location.reload();
}
