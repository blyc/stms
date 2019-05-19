var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(function () {
    $.post(baseUrl + '/employee/selectCompany.shtml', function (result) {
        obj = eval(result);
        $("#cid").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#cid").append(option);
            }
        }
        $('#cid option:first').attr("selected", true);
    });

    $.post(baseUrl + '/employee/selectRank.shtml', function (result) {
        obj = eval(result);
        $("#lid,#update_lid").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#lid,#update_lid").append(option);
            }
        }
        $('#lid,#update_lid option:first').attr("selected", true);
    });


    $.post(baseUrl + '/employee/selectDepartMent.shtml', function (result) {
        obj = eval(result);
        $("#did").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#did").append(option);
            }
        }
        $('#did option:first').attr("selected", true);

    });

    $.post(baseUrl + '/employee/selectPosition.shtml', function (result) {
        obj = eval(result);
        $("#pid").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#pid").append(option);
            }
        }
        $('#pid option:first').attr("selected", true);
    });

    $("#education").append($("<option>").val("").text("请选择"));
    for (var i in degrees) {
        var option = $("<option>").val(degrees[i]).text(degrees[i]);
        $("#education").append(option);
    }
    $('#education option:first').attr("selected", true);

    $("#addBtn").click(function () {

        $('#addEmployee').modal();
    });

    $("#savebtn").click(function () {
        var name = $('#name').val(),
            sex = $('input:radio[name="sex"]:checked').val(),
            birthday = $('#birthday').val(),
            tel = $('#tel').val(),
            email = $('#email').val(),
            shool = $('#shool').val(),
            major = $('#major').val(),
            education = $('#education').val(),
            // idcard = $('#idcard').val(),
            entryday = $('#entryday').val(),
            cid = $('#cid').val(),
            did = $('#did').val(),
            pid = $('#pid').val(),
            lid = $("#lid").val(),
            pinyin = $('#pinyin').val();


        if ($.trim(name) == '') {
            return layer.msg('用户姓名不能为空。', so.default), !1;
        }
        if ($.trim(pinyin) == '') {
            return layer.msg('姓名拼音不能为空。', so.default), !1;
        }

        // if ($.trim(tel) == '') {
        //     return layer.msg('电话号码不能为空。', so.default), !1;
        // }

        // if ($.trim(idcard) == '') {
        //     return layer.msg('身份证号不能为空。', so.default), !1;
        // }

        // if ($.trim(entryday) == '') {
        //     return layer.msg('入职时间不能为空。', so.default), !1;
        // }

        if (is_mobile($.trim(tel)) == false) {
            return layer.msg('电话格式不正确。', so.default), !1;
        }

        // if (checkCard($.trim(idcard)) == false) {
        //     return layer.msg('身份证格式不正确。', so.default), !1;
        // }

        if ($.trim(email) != "" && is_email($.trim(email)) == false) {
            return layer.msg('邮箱格式不正确。', so.default), !1;
        }

        // if (getBirthday($.trim(idcard)) != $.trim(birthday)) {
        //     return layer.msg('出生日期错误。', so.default), !1;
        // }

        if ($.trim(cid) == '') {
            return layer.msg('校区不能为空。', so.default), !1;
        }
        if ($.trim(pid) == '') {
            return layer.msg('部门不能为空。', so.default), !1;
        }
        if ($.trim(did) == '') {
            return layer.msg('职位不能为空。', so.default), !1;
        }


        var userflg = 0;
        if (true == $("#userflg").is(":checked")) {
            userflg = 1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/employee.shtml', {
                    name: name, sex: sex, tel: tel, email: email, shool: shool, major: major, education: education,
                    entryday: entryday, cid: cid, did: did, pid: pid, lid: lid, pinyin: pinyin, userflg: userflg
                }
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message, so.default), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });
    });

    $("#savebtn2").click(function () {
        var eid = $("#update_eid").val();
        var lid = $("#update_lid").val();
        var tel = $('#update_tel').val();
        var email = $('#update_email').val();

        var userflg = 0;
        if (true == $("#update_userflg").is(":checked")) {
            userflg = 1;
        }


        if ($.trim(tel) == '') {
            return layer.msg('电话不能为空。'), !1;
        }

        if ($.trim(email) != "" && is_email($.trim(email)) == false) {
            return layer.msg('邮箱格式不正确。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                url: baseUrl + "/employee.shtml",
                type: "post",
                dataType: "json",
                data: {
                    eid: eid,
                    email: email,
                    tel: tel,
                    lid: lid,
                    userflg:userflg,
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

    _initPopover();

    $('input[required]').before('<span style="color:red">*</span>');

    $("#name").live("keyup keydown change blur", function () {
        $("#pinyin").val($(this).toPinyin());
    });


    $("#btn3").click(function () {
        $("#importEmployee").modal();
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

    var load = layer.load();
    $.post(baseUrl + '/employee/getEmployee.shtml', {eid: a}
        , function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !1;
            }

            var obj = result.entity;
            $('#update_eid').val(obj.eid);
            $('#update_tel').val(obj.tel);
            $('#update_email').val(obj.email);
            $('#update_lid').val(obj.lid);
            if(obj.userflg==0){
                $('#update_userflg').prop("checked",false);
            }
            else{
                $('#update_userflg').prop("checked",true);
            }
            $('#updateEmployeeLabel').modal();

        }, 'json');
}

//根据ID数组，删除
function _delete(ids) {
     var index = layer.confirm("确定这" + ids.length + "个数据？该员工如果存在登录账号,会一并删除", function () {
        var load = layer.load();
        $.post(baseUrl + '/employee/deleteEmployee.shtml', {ids: ids.join(',')}, function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !0;
            } else {
                layer.msg('删除成功');
                setTimeout(function () {
                    $('#formId').submit();
                }, 1000);
            }
        }, 'json');
        layer.close(index);
    });
}
