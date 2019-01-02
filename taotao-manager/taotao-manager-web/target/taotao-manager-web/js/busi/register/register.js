// 可发送状态
var sendFlag = true;
$(function (){

    // 监控手机号码输入框
    $("#phone").keyup(function () {
        $(this).val($(this).val().replace(/[^\d]/g,''));
        validCheck();
    });

    // 监控验证码的输入
    $("#vcode").keyup(function () {
        $(this).val($(this).val().replace(/[^\d]/g,''));
        validCheck();
    });

    // 点击发送短信
    $(".js-sendMessage").click(function() {
        // 如果当前是可发送状态
        if(sendFlag) {
            var phone = $("#phone").val();
            if(phone.length == 11) {
                // 置为不可发送
                sendFlag = false;
                // 开始倒计时
                countDown(
                    60,
                    function (startNum) {
                        $(".js-sendMessage").text(startNum + "s后重试");
                        validCheck();
                    },
                    function () {
                        $(".js-sendMessage").text("获取验证码");
                        sendFlag = true;
                        // $(".js-logBtn").removeClass("on");
                    });
                sendMessage(phone);
            } else {
                showError("请输入正确的手机号码");
            }
        }
    });

    // 点击注冊按钮
    $(".register").click(function() {
        // 校验参数是否正确
        // if(validAndShowMsg()) {
        //     // 手机号码
        //     var phone = $("#phone").val().trim();
        //     // 姓名
        //     var userName = $("#userName").val().trim();
        //     register(phone, userName);
        // }
        var url = "confrim.html?+phone=" + phone;
        window.location.href = url;
    });

    //加载confirm页面给phone赋值
    $("#confirmPhone").value(phone);
});

/**
 * 发送短信
 * @param phone 手机号码
 */
function sendMessage(phone) {
    // 发送短信逻辑
    $.ajaxGet({
        url : appName + "/app/message/send/" + phone,
        success : function (data) {
            if(data.data.success == true) {
                showError("短信发送成功");
            } else if(data.data.success == false) { // 发送失败
                var errorMsg = data.data.errorMsg;
                if((typeof errorMsg) == "undefined" || errorMsg == null || errorMsg == "") {
                    errorMsg = "短信发送失败";
                }
                showError(errorMsg);
                $(".js-sendMessage").text("获取验证码");
                sendFlag = true;
            } else {
                showError("短信发送失败");
                $(".js-sendMessage").text("获取验证码");
                sendFlag = true;
            }
        },
        error : function (data) {
            showError("短信发送失败");
        }
    });


}

/**
 * 倒计时
 * @param startNum 多长时间的倒计时（单位：s）
 * @param func 具体的处理函数
 * @param stopFunc 倒计时到零的时候的处理
 */
function countDown(startNum, func, stopFunc) {
    if(startNum > 0) {
        setTimeout(function () {
            func(startNum);
            countDown(--startNum, func, stopFunc);
        }, 1000);
    } else {
        stopFunc();
    }
}

/**
 * 校验登录按钮是否可以点击
 */
function validCheck() {
    // 手机号码
    var phone = $("#phone").val().trim();
    // 验证码
    var vcode = $("#vcode").val().trim();
    // 切换是否同意条款
    var isChecked = $(".label_checkbox").hasClass("on");
    // 校验手机号、验证码、条款是否同意
    if(phone != null && phone.length == 11 && vcode != null
        && vcode.length == 6 && isChecked) {
        //$(".js-logBtn").addClass("on");
        return false;
    } else {
        // $(".js-logBtn").removeClass("on");
        return true;
    }
}

/**
 * 校验并提示
 */
function validAndShowMsg() {
    // 手机号码
    var phone = $("#phone").val().trim();
    // 姓名
    var userName = $("#userName").val().trim();

    if(phone == null || phone.length != 11) {
        showError("请输入正确的手机号码！");
        return false;
    }
    if(userName == null){
        showError("请输入姓名!")
    }
    return true;
}

/**
 * 显示错误信息
 * @param msg
 */
function showError(msg) {
    $(".js-msg").addClass("ft_red");
    $(".js-msg").text(msg);
    setTimeout(function () {
        $(".js-msg").removeClass("ft_red");
        $(".js-msg").text("短信验证码");
    }, 3000);
}

/**
 * 注冊方法
 * @param phone 手机号码
 * @param userName 姓名
 */
function register(phone, userName) {
    var nextUrl = appName + "/confirm.html";
    $.ajaxPost({
        url : appName + "/app/send/",
        data: JSON.stringify({
            phone: phone,
            userName: userName
        }),
        success: function(data){
            // 接口调用成功
            if(data.respCode == '0000') {
                window.location.href = preUrl;
            } else if(data.respCode == "2001") {
                showError("验证码错误");
            } else {
                jumpToErrorPage(500);
            }
        },
        error : function (data) {
            // 404异常
            if(data.status == 404) {
                jumpToErrorPage(404);
            } else { // 其他异常
                if(data.status == 400) {
                    if(data.responseJSON.respCode == "2001") {
                        showError("验证码错误");
                    }
                } else {
                    jumpToErrorPage(500);
                }
            }
        }
    });
}

/**
 * 注册页面跳转
 */
function toConfirm(phone) {
    var url = "confrim.html?+phone=" + phone;
    window.location.href = url;
}


