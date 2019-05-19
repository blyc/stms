<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>修改校区</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $('input[required]').before('<span style="color:red">*</span>');
        })
    </script>
</head>
<body data-target="#one" data-spy="scroll">
<@_top.top 3/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left3 1/>
        <div class="col-md-10">
            <h2>校区修改</h2>
            <hr>
            <form id="formId" enctype="multipart/form-data" action="${basePath}/company/updateCompany.shtml"
                  method="post">
                <input type="hidden" value="${page.cid}" name="cid"/>
                <div class="form-group">
                    <label for="nickname">校区名称（不准修改）</label>
                    <input type="text" class="form-control" disabled autocomplete="off" id="name" maxlength="8"
                           name="name" value="${page.name?default('未设置')}" placeholder="请输入昵称">
                </div>
                <div class="form-group">
                    <label for="email">校区地址</label>
                    <input type="text" class="form-control " autocomplete="off" id="address" maxlength="64"
                           name="address" value="${page.address?default('未设置')}" placeholder="请输入帐号" required="required">
                </div>
                <div class="form-group">
                    <label for="nickname">校区电话</label>
                    <input type="text" class="form-control" autocomplete="off" id="tel" maxlength="11" name="tel"
                           value="${page.tel?default('未设置')}" placeholder="请输入昵称">
                </div>
                <div class="form-group">
                    <label for="nickname">校区描述</label>
                    <textarea class="form-control" autocomplete="off" id="describtion" name="describtion"
                              placeholder="请输入校区描述">${page.describtion?default('未设置')}</textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">确定修改</button>
                </div>
            </form>
        </div>
    </div><#--/row-->
</div>
</body>
</html>