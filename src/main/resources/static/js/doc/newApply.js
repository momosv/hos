
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

function getCaseList(pageNum) {
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/doc/getUserCaseList",
        type: 'POST',
        data:{id:$("#case_search_key").val(),diagnosis:$("#case_search_key0").val(),pageNum:pageNum,pageSize:10},
        success: function(re) {
            var extend=re.extend;

            if (re.code != 200) {
                var page=extend.page;
                $("#case_table_data tr:not(:first)").remove();
                pageMenu(page);
                if(page==null){
                    jQuery.alertWindow("无该患者其他病历信息");
                }
              /*  $("#org_table_data tr:eq(1)").nextAll().remove();*/
                var list=page.list;
                var htmlModel=document.getElementById("case_table_model").innerHTML;
                var inHtml="";
                for(var i=0;i<list.length;i++){
                    var allow_grade="个人";
                    if(list[i].allow_grade==undefined){
                        allow_grade="";
                    }else if(list[i].allow_grade==2){
                        allow_grade="机构";
                    }else if(list[i].allow_grade==1){
                        allow_grade="科室";
                    }

                    inHtml+=htmlModel
                        .replace("{id}",list[i].id)
                        .replace("{user_name}",extend.user.name)
                        .replace("{allow_grade}",allow_grade)
                        .replace("{is_allow}",list[i].is_allow==undefined?"未授权":list[i].is_allow==1?"已授权":list[i].is_allow==-1?"待审批":"授权不通过")
                        .replace("{doc_name}",list[i].doc_name==undefined?"":list[i].doc_name)
                        .replace("{diagnosis}",list[i].diagnosis==undefined?"":list[i].diagnosis)
                        .replace("{create_time}",list[i].create_time==undefined?"":list[i].create_time)
                        .replace("{org_name}",list[i].org_name==undefined?"":list[i].org_name)
                        .replace("{dept_name}",list[i].dept_name==undefined?"":list[i].dept_name);
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
    $("#total_num").empty();
    $("#pre_num").empty();
    $("#total").empty();
    if(page==null){
        $("#jump").hide()
    }else{
        $("#jump").show()
    }
    if(page==null)return;
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
    $("#total_num").html("共" + page.pages + "页");
    $("#pre_num").html("第" + page.pageNum + "页");
    $("#total").html("共" + page.total + "条");
}

function apply(t) {
    $("#cdiv").toggle();
    $("#hiddenCaseId").val($(t).attr("id"));
    $("#cname").html($(t).siblings().html());
    $("#caseDesc").html($(t).siblings().eq(1).html()+"|"+$(t).siblings().eq(5).html());
}

function applySub(){
    $("#cdiv").toggle();
    $.ajax({url:"/doc/applyCase",
        type:"post",
        data:{caseId:$("#hiddenCaseId").val(),reason:$("#reason").val(),deadline:$("#deadline").val(),allowGrade:$("#allowGrade").val()},
        success:function (re) {
            if(re.code==-1){
                window.open("/hos/login")
            }else{
                jQuery.alertWindow(re.msg);
            }
        }
    });
}





