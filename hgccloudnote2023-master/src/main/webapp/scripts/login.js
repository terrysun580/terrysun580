$(function(){
    //页面加载时运行
    //alert("我是第一次写jq代码，保佑我别出错！")
    //获取账号和密码
    $("#login").click(function(){
        //清除提示信息
        $("#count_span").html("")
        $("#password_span").html("");
        //获取请求参数
        var name = $("#count").val().trim();
        var password = $("#password").val().trim();
        //console.log(name + "," +password);
        //检查参数格式：
        var ok = true;
        if(name == ""){
            ok = false;
            $("#count_span").html("用户名为空");
        }
        if(password == ""){
            ok = false;
            $("#password_span").html("密码为空");
        }
        //提交登录请求
        if(ok){
            $.ajax({
                url: "http://localhost:80/user/login.do",
                type: "post",
                data: {"name":name,"password":password},
                dataType: "json",
                success:function(result){//服务器成功返回信息
                    //console.log(result.status)
                    if (result.status == 0){
                        //获取用户id
                        var userId = result.data;
                        console.log(userId)
                        //将数据存储到cookie
                        addCookie("userId",userId,0.5);
                        window.location.href = "edit.html";

                    } else if(result.status == 1){
                        $("#count_span").html(result.msg);
                    }else if(result.status == 2){
                        $("#password_span").html(result.msg);
                    }
                },
                error:function(){//服务器处理失败
                    alert("登录失败，请联系管理员")
                }
            });

        }
    });


})