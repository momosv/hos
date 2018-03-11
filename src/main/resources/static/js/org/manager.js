
$( function () {
        getManagerList(1);

}
);
/*导航条页码*/
function navPage(t) {
    getManagerList($(t).attr("pNum"))
}
function getOrderManList(t) {
    getManagerList($(t).val())
}
function man_search_key() {
    getManagerList(1)
}

function getManagerList(pageNum) {
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/org/getChildManagerList",
        type: 'POST',
        data:{key:$("#man_search_key").val(),pageNum:pageNum,pageSize:10},
        success: function(re) {
            var extend=re.extend;

            if (re.code != 200) {
                var page=extend.page;
                pageMenu(page);
              /*  $("#org_table_data tr:eq(1)").nextAll().remove();*/
                $("#man_table_data tr:not(:first)").remove();
                var list=page.list;
                var htmlModel=document.getElementById("man_table_model").innerHTML;
                var inHtml="";
                for(var i=0;i<list.length;i++){
                        inHtml+=htmlModel
                        .replace("{did}",list[i].id)
                        .replace("{cid}",list[i].id)
                        .replace("{name}",list[i].name==undefined?"":list[i].name)
                        .replace("{email}",list[i].email==undefined?"":list[i].email)
                        .replace("{account}",list[i].account==undefined?"":list[i].account)
                        .replace("{phone}",list[i].phone==undefined?"":list[i].phone)
                        .replace("{grade}",list[i].grade==undefined?"":list[i].grade)
                        .replace("{passwd}",list[i].passwd==undefined?"":list[i].passwd)
                        .replace("{create_time}",list[i].create_time==undefined?"":new Date(list[i].create_time).Format("yyyy-MM-dd hh:mm:ss"));
                }
                $("#man_table_data").append(inHtml);
            } else {
                alert(re.msg);
            }
        },
        error: function(rs) {
            alert(rs.extend.msg);
        }
    });
}
function pageMenu(page) {
    $("#ul_page").empty();
    $("#ul_page").append("<li onclick='navPage(this)' pNum='1'>首页</li>");
    if (page.hasPreviousPage == true) {
        $("#ul_page").append("<li onclick='navPage(this)' pNum=" + page.prePage + ">上一页</li>");
    }
    for (var i = 0; i < page.navigatepageNums.length; i++) {
        $("#ul_page").append("<li onclick='navPage(this)' pNum=" + page.navigatepageNums[i] + ">" + page.navigatepageNums[i] + "</li>");
    }
    if (page.hasNextPage == true) {
        $("#ul_page").append("<li onclick='navPage(this)' pNum=" + page.nextPage + ">下一页</li>");
    }
    $("#ul_page").append("<li onclick='navPage(this)' pNum=" + page.pages + ">末页</li>");
    $("#total_num").empty();
    $("#total_num").html("共" + page.pages + "页");
    $("#pre_num").empty();
    $("#pre_num").html("第" + page.pageNum + "页");
}


function deleteM(t) {
    $.ajax({
        url:"/org/deleteChildManager",
        type:"post",
        data:{id:$(t).attr("id")},
        success:function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code == 200) {
                jQuery.alertWindow(re.msg);
            }else {
                getManagerList(1);
            }
        }
    });
}
function changeM(t) {
    $("#cdiv").toggle();
    $("#cTitle").html("更改管理员");
    $.ajax({url:"/org/getChildManager",
        type:"post",
        data:{id:$(t).attr("id")},
        success:function(re){
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code == 200) {
                jQuery.alertWindow(re.msg);
            }else {
                var deta=re.extend.detail;
                $("#hiddenId").val(deta.id);
                $("#caccount").val(deta.account);
                $("#cname").val(deta.name);
                $("#cemail").val(deta.email);
                $("#cpasswd").val(deta.passwd);
                $("#cgrade").val(deta.grade);
                $("#cphone").val(deta.phone);
            }
        }
        });
}
function hideC() {
    $("#cdiv").toggle();
}
function man_add() {
    $("#cdiv").toggle();
    $("#cTitle").html("新增管理员");
    $("#hiddenId").val("");
    $("#caccount").val("");
    $("#cname").val("");
    $("#cemail").val("");
    $("#cpasswd").val("");
    $("#cgrade").val("");
    $("#cphone").val("");
}
function saveOrUpdate() {
    if( $("#hiddenId").val()==""){
        $.ajax({url:"/org/addChildManager",
            type:"post",
            data:{  account: $("#caccount").val(),
                name: $("#cname").val(),
                email:$("#cemail").val(),
                passwd:$("#cpasswd").val(),
                grade:$("#cgrade").val(),
                phone:$("#cphone").val()},
            success:function(re){
                if(re.code==-1){
                    window.open("/hos/login");
                    jQuery.alertWindow("登录已经失效，请重新登录！",10000);
                }else if(re.code!=200){
                    $("#cdiv").toggle();
                    getManagerList(1);
                }
                jQuery.alertWindow(re.msg);
            }
        });
    }else{
        $.ajax({url:"/org/updateChildManager",
            type:"post",
            data:{
               id: $("#hiddenId").val(),
                account: $("#caccount").val(),
               name: $("#cname").val(),
                email:$("#cemail").val(),
                passwd:$("#cpasswd").val(),
                grade:$("#cgrade").val(),
                phone:$("#cphone").val()
            },
            success:function(re){
                if(re.code==-1){
                    window.open("/hos/login");
                    jQuery.alertWindow("登录已经失效，请重新登录！",10000);
                }else if(re.code!=200){
                    $("#cdiv").toggle();
                    getManagerList(1);
                }
                jQuery.alertWindow(re.msg);
            }
        });
    }
}