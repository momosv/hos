
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
                        .replace("{case_dept}",list[i].from_dept_name==undefined?"":list[i].from_dept_name)
                        .replace("{case_org}",list[i].from_org_name==undefined?"":list[i].from_org_name)
                        .replace("{diagnosis}",list[i].diagnosis==undefined?"":list[i].diagnosis)
                        .replace("{case_time}",list[i].case_time==undefined?"":getMyDate(list[i].case_time))
                        .replace("{user_name}",list[i].user_name==undefined?"":list[i].user_name)
                        .replace("{doc_name}",list[i].doc_name==undefined?"":list[i].doc_name)
                        .replace("{dept_name}",list[i].dept_name==undefined?"":list[i].dept_name)
                        .replace("{org_name}",list[i].org_name==undefined?"":list[i].org_name)
                        .replace("{allow_grade}",allow_grade)
                        .replace("{create_time}",list[i].create_time==undefined?"":getMyDate(list[i].create_time))
                        .replace("{isAllow}",list[i].is_allow==undefined?"不通过":(list[i].is_allow==1?"通过":list[i].is_allow==0?"不通过":"待审批"));
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

function getAuthorityDetail(t) {
    var id = $(t).attr("dataId");
    window.open("/hos/page/org/AuthorityDetail/" + id);
}

function ajaxAuthorityDetail() {
    var id = $("#authId").val();
    $.ajax({
        url:"/org/getAuthorityDetail",
        data:{authId:id},
        type: 'POST',
        success:function (re) {
            if(re.code==-1){
                jQuery.alertWindow("登录已经失效，请重新登录！");
                window.open("/hos/login");
            }else  if (re.code != 200) {
                var detail=re.extend.detail[0];
                if(detail==null)return;
                $("#docName").val(detail.doc_name==undefined?"":detail.doc_name);
                $("#docPhone").val(detail.doc_phone==undefined?"":detail.doc_phone);
                $("#position").val(detail.position==undefined?"":detail.position);
                $("#deptName").val(detail.dept_name==undefined?"":detail.dept_name);
                $("#orgName").val(detail.org_name==undefined?"":detail.org_name);
                $("#email").val(detail.doc_email==undefined?"":detail.doc_email);
                $("#orgPhone").val(detail.org_phone==undefined?"":detail.org_phone);
                $("#linkman").val(detail.linkman==undefined?"":detail.linkman);

                $("#deadline").val(detail.deadline==undefined?"":detail.deadline);

                $("#caseTitle").val(detail.diagnosis==undefined?"":detail.diagnosis+"-"+detail.user_name==undefined?"":detail.user_name);
                $("#allowGrade").val(detail.allow_grade==undefined?"":detail.allow_grade);
                $("#caseA").attr("href","/hos/page/user/caseDetail/"+detail.case_id);
                $("#reason").val(detail.reason==undefined?"":detail.reason);
            }else {
                jQuery.alertWindow(re.msg);
            }
        }

    });
}



function approveAuthority(isA) {
    if(isA!=1)isA=0;
    $.ajax({
        url:"/org/approveAuthority",
        data:{id : $("#authId").val(),isAllow:isA,allowGrade:$("#allowGrade").val(),deadline:$("#deadline").val()},
        type: 'POST',
        success:function (re) {
            if(re.code==-1){
                jQuery.alertWindow("登录已经失效，请重新登录！");
                window.open("/hos/login");
            }else{
                jQuery.alertWindow(re.msg);
            }
        }
    });
}