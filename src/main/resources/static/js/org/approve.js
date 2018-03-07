$(function(){
        getAuthorityList(-2,1);
    }
);

var isAllow=-2;
/*导航条页码*/
function navPage(t) {
    getAuthorityList(isAllow,$(t).attr("pNum"))
}
function getOrderOrgList(t) {
    getAuthorityList(isAllow,$(t).val())
}
function auth_search_key() {
    getAuthorityList(isAllow,1)
}
function getAuthList(t) {
    getAuthorityList(t,1);
}

function getAuthorityList(allow,pageNum) {
    isAllow=allow;
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/org/getAuthorityList",
        type: 'POST',
        data:{isAllow:isAllow,keyType:$("#data_key").val(),key:$("#auth_search_key").val(),pageNum:pageNum,pageSize:10},
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
                    var allow_grade="个人";
                    if(list[i].allow_grade==1){
                        allow_grade="部门";
                    }else if(list[i].allow_grade==2){
                        allow_grade="机构";
                    }                    inHtml+=htmlModel
                        .replace("{dataId}",list[i].id)
                        .replace("{caseId}",list[i].case_id==undefined?"":list[i].case_id)
                        .replace("{diagnosis}",list[i].diagnosis==undefined?"":list[i].diagnosis)
                        .replace("{user_name}",list[i].user_name==undefined?"":list[i].user_name)
                        .replace("{doc_name}",list[i].doc_name==undefined?"":list[i].doc_name)
                        .replace("{dept_name}",list[i].dept_name==undefined?"":list[i].dept_name)
                        .replace("{org_name}",list[i].org_name==undefined?"":list[i].org_name)
                        .replace("{allow_grade}",allow_grade)
                        .replace("{create_time}",list[i].create_time==undefined?"":getMyDate(list[i].create_time))
                        .replace("{isAllow}",list[i].is_allow==undefined?"不通过":(list[i].isAllow==1?"通过":"不通过"));
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

function getAuthorityDetail(t) {
    var id = $(t).attr("id");
    window.open("/hos/page/org/AuthorityDetail/" + id);
}