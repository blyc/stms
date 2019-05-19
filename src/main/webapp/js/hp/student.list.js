var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {
    $("#addBtn").click(function () {
        $('#addStudent').modal();
    });

    $("#savebtn").click(function () {
        var name = $('#name').val(),
            sex = $('input:radio[name="sex"]:checked').val(),
            nation = $('#nation').val(),
            idcard = $('#idcard').val(),
            birthday = $('#birthday').val(),
            education = $('#education').val(),
            email = $('#email').val(),
            qq = $('#qq').val(),
            tel = $('#tel').val(),
            province = $('#province').val(),
            city = $('#city').val(),
            politics = $('#politics').val(),
            address = $('#address').val(),
            grade = $('#grade').val(),
            postcode = $('#postcode').val(),
            cid = $('#cid').val(),
            mid = $('#mid').val(),
            fatherName = $('#fatherName').val(),
            motherName = $('#motherName').val(),
            fatherTel = $('#fatherTel').val(),
            motherTel = $('#motherTel').val(),
            otherName = $('#otherName').val(),
            otherTel = $('#otherTel').val(),
            roomcode = $('#roomcode').val();

        if ($.trim(name) == '') {
            return layer.msg('用户姓名不能为空。'), !1;
        }
        if ($.trim(birthday) == '') {
            return layer.msg('出生日期不能为空。'), !1;
        }
        if ($.trim(idcard) == '') {
            return layer.msg('身份证号不能为空。'), !1;
        }
        if (checkCard($.trim(idcard)) == false) {
            return layer.msg('身份证格式不正确。'), !1;
        }
        if (getBirthday($.trim(idcard)) != $.trim(birthday)) {
            return layer.msg('出生日期错误。'), !1;
        }
        if ($.trim(tel) == '') {
            return layer.msg('电话不能为空。'), !1;
        }
        if (is_mobile($.trim(tel)) == false) {
            return layer.msg('电话格式不正确。'), !1;
        }
        if ($.trim(email) != "" && is_email($.trim(email)) == false) {
            return layer.msg('邮箱格式不正确。'), !1;
        }
        if ($.trim(qq) == "") {
            return layer.msg('qq不能为空。'), !1;
        }
        if ($.trim(grade) == "") {
            return layer.msg('年级不能为空。'), !1;
        }
        if ($.trim(mid) == "") {
            return layer.msg('专业不能为空。'), !1;
        }


        if ($.trim(address) == "") {
            return layer.msg('通讯地址不能为空。'), !1;
        }
        if ($.trim(fatherTel) != "" && is_mobile($.trim(fatherTel)) == false) {
            return layer.msg('父亲电话格式不正确。'), !1;
        }
        if ($.trim(motherName) != "" && is_mobile($.trim(motherName)) == false) {
            return layer.msg('母亲电话格式不正确。'), !1;
        }
        if ($.trim(otherTel) != "" && is_mobile($.trim(otherTel)) == false) {
            return layer.msg('其他电话格式不正确。'), !1;
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/student.shtml', {
                    name: name,
                    sex: sex,
                    nation: nation,
                    idcard: idcard,
                    birthday: birthday,
                    education: education,
                    email: email,
                    qq: qq,
                    tel: tel,
                    province: province,
                    city: city,
                    politics: politics,
                    address: address,
                    grade: grade,
                    postcode: postcode,
                    cid: cid,
                    mid: mid,
                    fatherName: fatherName,
                    motherName: motherName,
                    fatherTel: fatherTel,
                    motherTel: motherTel,
                    otherName: otherName,
                    otherTel: otherTel,
                    roomcode: roomcode
                }
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });
    });
    $("#savebtn2").click(function () {
        var sid = $("#update_sid").val();
        var name = $('#update_name').val();
        var sex = $(":radio[name='update_sex']:checked").val();
        var tel = $('#update_tel').val();
        var qq = $('#update_qq').val();
        var roomcode = $('#update_roomcode').val();
        var email = $('#update_email').val();
        var fatherTel = $('#update_fatherTel').val();
        var motherTel = $('#update_motherTel').val();
        var otherName = $('#update_otherName').val();
        var otherTel = $('#update_otherTel').val();
        var mid = $('#update_mid').val();
        var state = $('#update_state').val();

        if ($.trim(name) === '') {
            return layer.msg('姓名不能为空。'), !1;
        }

        if ($.trim(sex) === '') {
            return layer.msg('性别不能为空。'), !1;
        }

        if ($.trim(tel) === '') {
            return layer.msg('电话不能为空。'), !1;
        }

        if ($.trim(qq) == '') {
            return layer.msg('qq不能为空。'), !1;
        }

        if ($.trim(email) != "" && is_email($.trim(email)) == false) {
            return layer.msg('邮箱格式不正确。'), !1;
        }

        if ($.trim(fatherTel) != "" && is_mobile($.trim(fatherTel)) == false) {
            return layer.msg('父亲电话格式不正确。'), !1;
        }
        if ($.trim(motherTel) != "" && is_mobile($.trim(motherTel)) == false) {
            return layer.msg('母亲电话格式不正确。'), !1;
        }
        if ($.trim(otherTel) != "" && is_mobile($.trim(otherTel)) == false) {
            return layer.msg('其他电话格式不正确。'), !1;
        }

        if ($.trim(mid) == '') {
            return layer.msg('专业不能为空。'), !1;
        }

        if ($.trim(state) == '') {
            return layer.msg('状态不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                url: baseUrl + "/student.shtml",
                type: "post",
                dataType: "json",
                data: {
                    sid: sid,
                    name: name,
                    sex: sex,
                    email: email,
                    qq: qq,
                    tel: tel,
                    fatherTel: fatherTel,
                    motherTel: motherTel,
                    otherName: otherName,
                    otherTel: otherTel,
                    roomcode: roomcode,
                    mid: mid,
                    state: state,
                    _method: "PUT"
                },
                success: function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }
            });
        });
    });

    for (var i in nations) {
        var option = $("<option>").val(nations[i]).text(nations[i]);
        $("#nation").append(option);
    }
    $('#nation option:first').attr("selected", true);

    for (var i in degrees) {
        var option = $("<option>").val(degrees[i]).text(degrees[i]);
        $("#education").append(option);
    }
    $('#education option:first').attr("selected", true);

    //获取专业
    $.get(baseUrl + '/student/getmajor.shtml', function (result) {
        obj = eval(result);
        $("#mid,#rearchmid,#update_mid").append($("<option>").val("").text("选择专业"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#mid,#rearchmid,#update_mid").append(option);
            }
        }
        $('#mid,#rearchmid,#update_mid option:first').attr("selected", true);

        var hidden_mid = $("#hidden_mid").val();
        if (hidden_mid != "") {
            $("#rearchmid").val(hidden_mid);
        }
    });

    //获取校区
    $.get(baseUrl + '/student/getcompany.shtml', function (result) {
        obj = eval(result);
        $("#rearchcid").append($("<option>").val("").text("选择校区"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#rearchcid").append(option);
            }
        }

        $('#rearchcid option:first').attr("selected", true);

        var hidden_cid = $("#hidden_cid").val();
        if (hidden_cid != "") {
            $("#rearchcid").val(hidden_cid);
        }
    });
    //获取年级
    $("#grade,#rearchgrade").append($("<option>").val("").text("选择年级"));
    var date = new Date();
    var year = date.getFullYear() - 2000;  //取后两位
    for (var i = year; i >= 13; i--) {
        var option = $("<option>").val(i).text(i + "级");
        $("#grade,#rearchgrade").append(option);
    }
    $('#grade,#rearchgrade option:first').attr("selected", true);

    var hidden_grade = $("#hidden_grade").val();
    if (hidden_grade != "") {
        $("#rearchgrade").val(hidden_grade);
    }


    $("#grade, #mid").bind("change", function () {
        var grade = $("#grade").val();
        var mid = $("#mid").val();

        if ($.trim(grade) != '' && $.trim(mid) != '') {
            $.get(baseUrl + '/student/getclass.shtml', {grade: grade, mid: mid}, function (result) {
                obj = eval(result);
                $("#cid").empty();
                $("#cid").append($("<option>").val("").text("请选择"));
                if (obj != undefined) {
                    for (var i = 0; i < obj.length; i++) {
                        var option = $("<option>").val(result[i].key).text(result[i].value);
                        $("#cid").append(option);
                    }
                }
            });
        }
    });
    $('input[required]').before('<span style="color:red">*</span>');
    _initProviceAndCity("province", "city");
    $("#province,#city").bind("change", function () {
        var province = $('#province').val();
        var city = $('#city').val();
        var postcode = findPostCode(province, city, '');
        $('#postcode').val(postcode);
    });
    $("#idcard").blur(function () {
        var idcard = $("#idcard").val();
        var birthday = getBirthday(idcard);
        if ($.trim(birthday) != '') {
            $("#birthday").val(birthday);

        }
    });
    _initPopover();


    $("#btn3").click(function () {
        $("#importStudent").modal();
    });

    $("#btnImport").click(function () {
            var f = $("#importFile").val();
            if (f == "") {
                return layer.msg('导入文件不能为空。'), !1;
            }
            return layer.confirm("确定保存", {
                icon: 3,
                title: "提示",
                btn: ['确定', '取消'],
            }, function () {
                var load = layer.load();
                $("#importForm").submit();
                $("#btnImport").attr('disabled', true);
                return true;
            });
        }
    );
});


function _openUpdateDlg(a) {
    var info = $('#H' + a).val();
    var arr = info.split(",");
    $('#update_sid').val(a);
    $('#update_name').val(arr[0]);
    if (arr[1] == '女') {
        $("#update_sex2").prop("checked", true);
        $("#update_sex1").prop("checked", false);
    }else {
        $("#update_sex1").prop("checked", true);
        $("#update_sex2").prop("checked", false);
    }
    $('#update_tel').val(arr[2]);
    $('#update_qq').val(arr[3]);
    $('#update_roomcode').val(arr[4]);
    $('#update_email').val(arr[5]);
    $('#update_fatherTel').val(arr[6]);
    $('#update_motherTel').val(arr[7]);
    $('#update_otherName').val(arr[8]);
    $('#update_otherTel').val(arr[9]);
    $('#update_mid').val(arr[10]);
    $('#update_state').val(arr[11]);
    var className = arr[12];

    if ($.trim(className) != "") {
        $('#update_mid').attr("disabled", true);
        // $('#update_state').attr("disabled", true);
    }
    else {
        $('#update_mid').removeAttr("disabled");
        // $('#update_state').removeAttr("disabled");
    }
    $('#updateStudent').modal();
}

function _openDeleteDlg(a, b) {
    if (b !== '') {
        layer.msg('请先把该学生移出所在班级');
    } else {
        var sid = a;
        var state = "删除";
        return layer.confirm("确定删除", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                url: baseUrl + "/student.shtml",
                type: "post",
                dataType: "json",
                data: {
                    sid: sid,
                    state: state,
                    _method: "PUT"
                },
                success: function (result) {
                    layer.close(load);
                    if (result && result.status !== 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }
            });
        });
    }
}


function importExp() {
    var formData = new FormData();
    var name = $("#file").val();
    formData.append("file", $("#file")[0].files[0]);
    formData.append("name", name);
    $.ajax({
        url: baseUrl + '/student/importStudentsInfo.shtml',
        type: 'POST',
        async: false,
        data: formData,
        // 告诉jQuery不要去处理发送的数据
        processData: false,
        // 告诉jQuery不要去设置Content-Type请求头
        contentType: false,
        beforeSend: function () {
            console.log("正在进行，请稍候");
        },
        success: function (responseStr) {
            if (responseStr == "01") {
                alert("导入成功");
            } else {
                alert("导入失败");
            }
        }
    });
}