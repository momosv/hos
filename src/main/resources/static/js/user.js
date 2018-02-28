
$( function () {
        getUserList(-1,1);

}
);
var GET_USER_TYPE=3;
/*导航条页码*/
function navPage(t) {
    getUserList(GET_USER_TYPE,$(t).attr("pNum"))
}
function getOrderUserList(t) {
    getUserList(GET_USER_TYPE,$(t).val())
}
function user_search_key(){
    getUserList(GET_USER_TYPE,1)
}

function getUserList(type, pageNum) {
    GET_USER_TYPE=type;
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/sys/getUserList",
        type: 'POST',
        data:{type:type,key:$("#user_search_key").val(),pageNum:pageNum,pageSize:10},
        success: function(re) {
            var extend=re.extend;

            if (re.code != 200) {
                var page=extend.page;
                pageMenu(page);
              /*  $("#org_table_data tr:eq(1)").nextAll().remove();*/
                $("#org_table_data tr:not(:first)").remove();
                var list=page.list;
                var htmlModel=document.getElementById("org_table_model").innerHTML;
                var inHtml="";
                for(var i=0;i<list.length;i++){
                    var actCode="待审批";
                    if(list[i].act_code==3){
                        actCode="通过";
                    }else if(list[i].act_code==4){
                        actCode="不通过";
                    }
                    inHtml+=htmlModel
                        .replace("{id}",list[i].id)
                        .replace("{name}",list[i].name==undefined?"":list[i].name)
                        .replace("{phone}",list[i].telephone==undefined?"":list[i].telephone)
                        .replace("{email}",list[i].email==undefined?"":list[i].email)
                        .replace("{address}",list[i].address==undefined?"":list[i].address)
                        .replace("{act}",actCode);
                }
                $("#org_table_data").append(inHtml);
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

function getUserDetail(t) {
    var id=$(t).attr("userId");
    window.open("/hos/page/sys/userDetail/"+id);
}