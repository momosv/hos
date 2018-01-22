$(document).ready(function() {
    check_latest();
    if (getCookie("tel") != "") {
        $("#head_tel").html(getCookie("tel"));
        loginOrLogout();
    }
});

function loginOrLogout() {
    $('.sell').toggle();
    $('.register').toggle();
    $('.login').toggle();
    $('.logout').toggle();
}

function showLoginBox() {
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        title: "登录",
        area: ["420px", "320px"],
        content: $(".loginBox")
    });
}

function showRegBox() {
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        title: "注册",
        area: ["420px", "480px"],
        content: $(".regBox")
    });
}

function login() {
    var tel = $.trim($("#login_tel").val());
    var pwd = $.trim($("#login_pwd").val());
    $.ajax({
        type: 'post',
        url: 'login.php',
        data: {
            tel: tel,
            pwd: pwd
        },
        dataType: 'text',
        success: function(num) {
            if (num == 1) {
                $("#head_tel").html(getCookie("tel"));
                layer.closeAll();
                loginOrLogout();
            } else layer.alert("登录失败");
        }
    });
}

function logout() {

    clearCookie('tel');
    loginOrLogout();
    $("#head_tel").html('');
}
//获取cookie
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1
            c_end = document.cookie.indexOf(";", c_start)
            if (c_end == -1) c_end = document.cookie.length
            return decodeURI(document.cookie.substring(c_start, c_end))
        }
    }
    return "";
}
//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
//清除cookie  
function clearCookie(name) {
    setCookie(name, "", -1);
}

function checkCookie() {
    var user = getCookie("username");
    if (user != "") {
        alert("Welcome again " + user);
    } else {
        user = prompt("Please enter your name:", "");
        if (user != "" && user != null) {
            setCookie("username", user, 365);
        }
    }
}

function checkPwd_reg() {
    var pwd = $("#re_pwd").val();
    var pwd2 = $("#re_pwd2").val();
    if (pwd != pwd2) {
        layer.alert("您两次输入的密码不同！");
    }

}

function register() {
    var tel = $("#re_tel").val();
    var username = $("#re_username").val();
    var pwd = $("#re_pwd").val();
    var pwd2 = $("#re_pwd2").val();
    var dormitory = $("#re_dor").val();
    var classname = $("#re_class").val();

    if (tel == "") {
        layer.msg("请输入手机号码！");
    } else if (username == "") {
        layer.msg("请输入用户名！");
    } else if (pwd == "") {
        layer.msg("请输入密码！");
    } else if (pwd != pwd2) {
        layer.msg("您两次输入的密码不同！");
    } else if (classname == "") {
        layer.msg("请输入专业班级！");
    } else if (!checkphone(tel)) {
        layer.alert("您输入的手机号码格式不正确！");
    } else {
        $.ajax({
            type: 'post',
            url: 'register.php',
            data: {
                tel: tel,
                username: username,
                pwd: pwd,
                dormitory: dormitory,
                classname: classname
            },
            dataType: 'text',
            success: function(num) {
                if (num == 1) {
                    // layer.alert("您已注册成功，请登录！");
                    parent.location.reload();
                } else layer.alert("注册失败");
            }
        });

    }
}

function checkphone(tel) {
    var str = tel;
    var pattern = new RegExp(/^1[3|4|5|8][0-9]\d{4,8}$/);
    if (pattern.test(str) == true) {
        return true;
    } else {
        return false;
    }
}

function search() {
    var b_name = $.trim($("#b_name").val());

    $.ajax({
        type: 'post',
        url: 'search.php',
        data: {
            b_name: b_name
        },
        dataType: 'text',
        success: function(num) {
            if (num == 1) {
                $("#details").show();
            } else layer.alert("查无此书！");
        }
    });
}

// function showTel() {
// 	layer.alert("发布者的手机号码为……，您可私下咨询他/她，沟通达成共识。");
// }

function find_isbn() {
    ISBN = document.getElementById("findISBN").value;
    $.ajax({
        type: 'post',
        url: 'getIsbn.php',
        data: {
            isbn: ISBN
        },
        dataType: 'JSON',
        success: function(rs) {
            if (rs != 0) {
                dealISBN(rs);
            } else layer.alert("查无此书！请手动输入相关信息！");
        }
    });

}

function dealISBN(b) {
    // document.getElementById("isbn13").value = ISBN;
    document.getElementById("b_name").value = b.title;
    document.getElementById("b_author").value = b.author;
    document.getElementById("price").value = b.price;
    // document.getElementById("pages").value = b.pages;
    // document.getElementById("translator1").value = b.translator;
    document.getElementById("b_press").value = b.publisher;

    // document.getElementById("pubdate").value = b.pubdate;

    // document.getElementById("summary").value = b.summary.substring(0, 230);
    document.getElementById("image").src = b.images.medium;

    document.getElementById("pic_url").value = b.images.medium;
    // document.getElementById("rukushijian").value = new Date().Format("yyyy-MM-dd");
    //          alert(b.title);

}

function check_latest() {
    $.ajax({
        type: 'post',
        url: 'getLatestbook.php',
        data: {},
        dataType: 'JSON',
        success: function(rs) {
            if (rs != 0) {
                deal_latest(rs);
            }
        }
    });

}

function deal_latest(rs) {
    $('#latest_ul').html('');
    for (var book of rs) {
        var b_name = book.b_name;
        var b_price = book.b_price;
        var pic_url = book.pic_url;

        $('#latest_ul').append(
            "<li class='latest_li'>" +
            "<div class=>" +
            "<div class='cover'>" +
            "<a href='javascript:;'><img src=" + pic_url + " class='pic'></a>" +
            "</div><div class='content'>" +
            "<div class='name_and_price'>" +
            " <span><a href='' target='_blank' id='bookName' title=" + b_name + ">" +
            "<strong class='bookname'>&nbsp;" + b_name + "</strong></a></span>" +
            "&nbsp;&nbsp;&nbsp;<span>￥" + b_price +"</span></div> </div></div> </li>"
        );
    }
}

// function more_latest() {
// 	$.ajax({
// 		type: 'post',
// 		url: 'latestMore.php',
// 		data: {},
// 		dataType: 'JSON',
// 		success: function(rs) {
// 			if(rs != 0) {
// 				more(rs);
// 			}
// 		}
// 	});

// }

// function more(rs) {
// 	$('#latest_ul').html('');
// 	for(var book of rs) {
// 		var b_name = book.b_name;
// 		var b_price = book.b_price;
// 		var pic_url = book.pic_url;

// 		$('#latest_ul').append(
//             "<li class='latest_li'>"+
//                    "<div class=>"+
//                 "<div class='cover'>"+
//                "<a href='javascript:;'><img src="+pic_url+" class='pic'></a>"+
//                 "</div><div class='content'>"+
//               "<div class='name_and_price'>"+
//          " <span><a href='' target='_blank' id='bookName' title="+b_name+">"+
//           "<strong class='bookname'>&nbsp;"+b_name+"</strong></a></span>"+
//           "&nbsp;&nbsp;&nbsp;￥"+b_price+
//          "</div> </div></div> </li>"
// 		);	
// 	}
// }

// function showDetails(rs) {
// 	$('.Items').html('');
// 	for(var book of rs) {
// 		var b_name = book.b_name;
// 		var b_author = book.b_author;
// 		var b_press = book.b_press;
// 		var b_price = book.b_price;
// 		var addTime = book.addTime;
// 		var classname = book.classname;
// 		var b_quantity = book.b_quantity;

// 		$('.Items').append(
//             "<div id="details">"+
//                 "<div class="detailsItem">书籍名称："+"<div class="Items"><label id="title">"+b_name+"</label></div></div>"+
//                 "<div class="detailsItem">出版社：<div class="Items"><span class="b_press_detail">"+b_press+"</span></div></div>"+
//                 "<div class="detailsItem">作者：<div class="Items"><span class="b_author_detail">"+b_author+"</span></div></div>"+
//                 "<div class="detailsItem">出售价格：<div class="Items"><span class="b_price_detail">"+b_price+"</span></div></div>"+
//                 "<div class="detailsItem">发布时间：<div class="Items"><span class="b_posttime_detail">"+addTime+"</span></div></div>"+
//                 "<div class="detailsItem">发布者专业：<div class="Items"><span class="classname_detail">"+classname+"</span></div></div>"+
//                 "<div class="detailsItem">出售数量：<div class="Items"><span class="b_quantity_detail">"+b_quantity+"</span></div></div>"+

//             "</div>"
// 		);	
// 	}
// }
