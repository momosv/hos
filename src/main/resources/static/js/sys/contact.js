
$( function () {
        getConList(-1,1)
}
);
var GET_TYPE=-1;
/*导航条页码*/
function navPage(t) {
    getConList(GET_TYPE,$(t).attr("pNum"))
}
function getOrderConList(t) {
    getConList(GET_TYPE,$(t).val())
}
function con_search_key() {
    getConList(GET_TYPE,1)
}

function getConList(type,pageNum) {
    GET_TYPE=type;
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/sys/getContactList",
        type: 'POST',
        data:{type:type,key:$("#con_search_key").val(),pageNum:pageNum,pageSize:10},
        success: function(re) {
            var extend=re.extend;

            if (re.code != 200) {
                var page=extend.page;
                pageMenu(page);
              /*  $("#org_table_data tr:eq(1)").nextAll().remove();*/
                $("#con_table_data tr:not(:first)").remove();
                var list=page.list;
                var htmlModel=document.getElementById("con_table_model").innerHTML;
                var inHtml="";
                for(var i=0;i<list.length;i++){
                    inHtml+=htmlModel
                        .replace("{id}",list[i].id)
                        .replace("{name}",list[i].name==undefined?"":list[i].name)
                        .replace("{title}",list[i].title==undefined?"":list[i].title)
                        .replace("{time}",list[i].create_time==undefined?"":new Date(list[i].create_time).Format("yyyy-MM-dd hh:mm"))
                        .replace("{phone}",list[i].phone==undefined?"":list[i].phone)
                        .replace("{email}",list[i].email==undefined?"":list[i].email)
                        .replace("{isRead}",list[i].is_read==1?"已读":"未读")
                        .replace("{isDeal}",list[i].is_deal==1?"已处理":"未处理");
                }
                $("#con_table_data").append(inHtml);
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
    $("#ul_page").append("<li><a onclick='navPage(this)' pNum='1'> <span aria-hidden=true>&laquo;</span></a></li>");
    if (page.hasPreviousPage == true) {
        $("#ul_page").append("<li><a onclick='navPage(this)' pNum=" + page.prePage + "><span aria-hidden=true>&lt;</span></a></li>");
    }
    for (var i = 0; i < page.navigatepageNums.length; i++) {
        $("#ul_page").append("<li><a onclick='navPage(this)' pNum=" + page.navigatepageNums[i] + ">" + page.navigatepageNums[i] + "</a></li>");
    }
    if (page.hasNextPage == true) {
        $("#ul_page").append("<li ><a onclick='navPage(this)' pNum=" + page.nextPage + "><span aria-hidden=true>&gt;</span></a></li>");
    }
    $("#ul_page").append("<li onclick='navPage(this)' pNum=" + page.pages + "><span aria-hidden=true>&raquo;</span></li>");
    $("#total_num").empty();
    $("#total_num").html("共" + page.pages + "页");
    $("#pre_num").empty();
    $("#pre_num").html("第" + page.pageNum + "页");

}

function getConDetail(t) {
    window.open("/hos/page/sys/conDetail/"+$(t).attr("conId"));
}

function saveCon() {
    $.ajax({
        url:"/hos/contact",
        type:"POST",
        data:{
            title:$("#title").val(),
            content:$("#content").val(),
            name:$("#name").val(),
            phone:$("#phone").val(),
            email:$("#email").val()},
        success:function(re){
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else{
                jQuery.alertWindow(re.msg);
            }
        }
    });
}