
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
                window.open("/hos/login","newWindow")
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

                    var ia="待审批";
                    if(list[i].is_allow==undefined){

                    }else if(list[i].is_allow==1){
                        ia="通过";
                    }else if(list[i].is_allow==0){
                        ia="不通过";
                    }
                    var allow_grade="个人";
                    if(list[i].allow_grade==undefined){

                    }else if(list[i].allow_grade==2){
                        allow_grade="机构";
                    }else if(list[i].allow_grade==1){
                        allow_grade="科室";
                    }
                    inHtml+=htmlModel
                        .replace("{id0}",list[i].case_id)
                        .replace("{id1}",list[i].case_id)
                        .replace("{id2}",list[i].auth_id)
                        .replace("{isAllow}",ia)
                        .replace("{user_name}",list[i].user_name==undefined?"":list[i].user_name)
                        .replace("{allow_grade}",allow_grade)
                        .replace("{deadline}",list[i].deadline==undefined?"":new Date(list[i].deadline).Format("yyyy-MM-dd"))
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

function getUserCaseDetail(t) {
    var id=$(t).attr("id");
    window.open("/hos/page/user/caseDetail/"+id,"newWindow");
}

function applyAgain(t){
    $("#cdiv").toggle();
    $("#hiddenApplyId").val($(t).attr("id"));
    $("#cname").html($(t).siblings().html());
    var allow_grade=($(t).siblings().eq(7).html());
    if(allow_grade==undefined){
        allow_grade=0;
    }else if(allow_grade=="科室"){
        allow_grade=1;
    }else if(allow_grade=="机构"){
        allow_grade=2;
    }else{
        allow_grade=0;
    }
    $("#allowGrade").val(allow_grade);
    $("#caseDesc").html($(t).siblings().eq(1).html()+"|"+$(t).siblings().eq(5).html());

}
function applyAgainSub(){
    $("#cdiv").toggle();
    $.ajax({url:"/doc/applyCase",
        type:"post",
        data:{id:$("#hiddenApplyId").val(),reason:$("#reason").val(),deadline:$("#deadline").val(),allowGrade:$("#allowGrade").val()},
        success:function (re) {
            if(re.code==-1){
                window.open("/hos/login","newWindow")
            }else{
                jQuery.alertWindow(re.msg);
            }
        }
    });
}
function newApplyCase() {
    window.open('/allowHtml/doc/newApplyCase.html',"newWindow");
}



