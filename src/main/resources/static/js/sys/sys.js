
$( function () {
        getOrgList(3,1);

}
);
var GET_ORG_TYPE=3;
/*导航条页码*/
function navPage(t) {
    getOrgList(GET_ORG_TYPE,$(t).attr("pNum"))
}
function getOrderOrgList(t) {
    getOrgList(GET_ORG_TYPE,$(t).val())
}
function org_search_key() {
    getOrgList(GET_ORG_TYPE,1)
}

function getOrgList(type,pageNum) {
    GET_ORG_TYPE=type;
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/sys/getOrgList",
        type: 'POST',
        data:{type:type,key:$("#org_search_key").val(),pageNum:pageNum,pageSize:10},
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
                    if(list[i].act_code==1){
                        actCode="通过";
                    }else if(list[i].act_code==2){
                        actCode="不通过";
                    }
                    inHtml+=htmlModel
                        .replace("{id}",list[i].id)
                        .replace("{name}",list[i].name==undefined?"":list[i].name)
                        .replace("{linkman}",list[i].linkman==undefined?"":list[i].linkman)
                        .replace("{phone}",list[i].telephone==undefined?"":list[i].telephone)
                        .replace("{email}",list[i].email==undefined?"":list[i].email)
                        .replace("{act}",actCode)
                        .replace("{time}",list[i].create_time==undefined?"":new Date(list[i].create_time).Format("yyyy-MM-dd hh:mm"));
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
function getOrgDetail(t) {
    var id=$(t).attr("orgId");
    window.open("/hos/page/sys/orgDetail/"+id);
}