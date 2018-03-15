
$( function () {
        getCaseList();
}
);

/*导航条页码*/
function navPage(t) {
    getCaseList($(t).attr("pNum"))
}
function getOrderCaseList(t) {
    getCaseList($(t).val())
}
function case_search_key() {
    getCaseList(1)
}
var isAllow='';
function getAuthList(t) {
    isAllow=t;
    getCaseList(1);
}
function getCaseList(pageNum) {
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/doc/getCaseApplyList",
        type: 'POST',
        data:{isAllow:isAllow,key:$("#case_search_key").val(),keyType:$("#case_key").val(),pageNum:pageNum,pageSize:10},
        success: function(re) {
            var extend=re.extend;
            if(re.code==-1){
                window.open("/hos/login")
            }
            if (re.code != 200) {
                var page=extend.page;
                pageMenu(page);
              /*  $("#org_table_data tr:eq(1)").nextAll().remove();*/
                $("#case_table_data tr:not(:first)").remove();
                var list=page.list;
                var htmlModel=document.getElementById("case_table_model").innerHTML;
                var inHtml="";
                for(var i=0;i<list.length;i++){
                    inHtml+=htmlModel
                        .replace("{id0}",list[i].case_id)
                        .replace("{id1}",list[i].case_id)
                        .replace("{id2}",list[i].case_id)
                        .replace("{user_name}",list[i].user_name==undefined?"":list[i].user_name)
                        .replace("{deadline}",list[i].deadline==undefined?"":list[i].deadline)
                        .replace("{doc_name}",list[i].case_doc_name==undefined?"":list[i].case_doc_name)
                        .replace("{diagnosis}",list[i].diagnosis==undefined?"":list[i].diagnosis)
                        .replace("{create_time}",list[i].case_time==undefined?"":new Date(list[i].case_time).Format("yyyy-MM-dd hh:mm:ss"))
                        .replace("{org_name}",list[i].from_org_name==undefined?"":list[i].from_org_name)
                        .replace("{dept_name}",list[i].from_dept_name==undefined?"":list[i].from_dept_name);
                }
                $("#case_table_data").append(inHtml);
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

function getUserCaseDetail(t) {
    var id=$(t).attr("id");
    window.open("/hos/page/user/caseDetail/"+id);
}

function applyAgain(t){
}




