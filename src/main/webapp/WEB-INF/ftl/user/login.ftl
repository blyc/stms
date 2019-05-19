<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <title>信息管理系统 | 登录</title>
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <link   rel="icon" href="${basePath}/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${basePath}/favicon.ico" />
        <!-- CSS -->
        <link rel="stylesheet" href="${basePath}/css/login/reset.css"/>
        <link rel="stylesheet" href="${basePath}/css/login/supersized.css"/>
        <link rel="stylesheet" href="${basePath}/css/login/style.css"/>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="${basePath}/js/common/html5shiv.js"></script>
        <![endif]-->
		<style>
			canvas{position: fixed; top: 0px; left: 0px; }
			#vcode >img{cursor:pointer;margin-bottom: -15px;border-radius:5px;}
		</style>
    </head>

    <body id="body" style="background-color: rgb(47,135,193)">
    	<script>

    	</script>

        <div class="page-container" style="margin: 100px auto 0px;">
            <h1>信息管理平台</h1>
            <form id="_form" action="" method="post">
                <input type="text" name="account" class="username" placeholder="请输入用户名">
                <input type="password" name="password" class="password" placeholder="请输入密码">
                <div style="text-align: left; margin-left: 10px;" id="vcode">
                    <input type="text" name="vcode"   placeholder="请输入验证码" style="width: 110px; margin-left: -8px; margin-right: 8px;">
                    <img src="${basePath}/open/getGifCode.shtml" />
                </div>
                <div style="text-align: left; margin-left: 10px;">
                <label><input type="checkbox"  id="rememberMe"style="width: 10px; height: 10px;">记住我</label>
                 </div>
                <button type="button" id="login" style="width: 300px">登录</button>
                <div class="connect" >

	            </div>
                
                <div class="error"><span>+</span></div>
            </form>
        </div>
        <!-- Javascript -->
        <script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
        <script  src="${basePath}/js/common/MD5.js"></script>
        <script  src="${basePath}/js/common/supersized.3.2.7.min.js"></script>
        <script  src="${basePath}/js/common/supersized-init.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
        <script >
			jQuery(document).ready(function() {
				try{
					var _href = window.location.href+"";
					if(_href && _href.indexOf('?kickout')!=-1){
						layer.msg('您已经被踢出，请重新登录！');
					}
				}catch(e){
					
				}
				//回车事件绑定
				document.onkeydown=function(event){
					var e = event || window.event || arguments.callee.caller.arguments[0];
					if(e && e.keyCode==13){ 
						$('#login').click();
					}
				};

                //验证码
                $("#vcode").on("click", 'img', function () {
                    /**动态验证码，改变地址，多次在火狐浏览器下，不会变化的BUG，故这样解决*/
                    var i = new Image();
                    i.src = '${basePath}/open/getGifCode.shtml?' + Math.random();
                    $(i).replaceAll(this);
                    //$(this).clone(true).attr("src",'${basePath}/open/getGifCode.shtml?'  + Math.random()).replaceAll(this);
                });
			
				//登录操作
			    $('#login').click(function(){
			    	
			        var username = $('.username').val();
			        var password = $('.password').val();
			        var vcode = $('[name=vcode]').val();

			        if(username == '') {
			            $('.error').fadeOut('fast', function(){
			                $('.error').css('top', '27px').show();
			            });
			            $('.error').fadeIn('fast', function(){
			                $('.username').focus();
			            });
			            return false;
			        }
			        if(password == '') {
			            $('.error').fadeOut('fast', function(){
			                $('.error').css('top', '96px').show();
			            });
			            $(this).find('.error').fadeIn('fast', function(){
			                $('.password').focus();
			            });
			            return false;
			        }

                    if($('[name=vcode]').val().length !=4){
                        return layer.msg('验证码的长度为4位！',function(){}),!1;
                    }

			        var pswd = MD5(username +"#" + password),
			        	data = {pswd:pswd,email:username,rememberMe:$("#rememberMe").is(':checked'),vcode:vcode};
			        var load = layer.load();
			        
			        $.ajax({
			        	url:"${basePath}/u/submitLogin.shtml",
			        	data:data,
			        	type:"post",
			        	dataType:"json",
			        	beforeSend:function(){
			        		layer.msg('开始登录，请注意后台控制台。');
			        	},
			        	success:function(result) {
                            layer.close(load);
                            if (result && result.status != 200) {
                                layer.msg(result.message, function () {
                                });
                                /*$('.password').val('');*/
                                return;
                            } else {
                                layer.msg('登录成功！');
                                setTimeout(function () {
                                    //登录返回
                                    window.location.href = result.back_url || "${basePath}/";
                                }, 1000)
                            }
			        	},
			        	error:function(e){
			        		console.log(e,e.message);
			        		layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
			        	}
			        });
			    });
			    $('.page-container form .username, .page-container form .password').keyup(function(){
			        $(this).parent().find('.error').fadeOut('fast');
			    });
//			    //注册
//			    $("#register").click(function(){
//			    	window.location.href="register.shtml";
//			    });
			});
        </script>
    </body>

</html>

