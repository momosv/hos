
$( function () {
        getDeptList();

}
);

function addDoc(){
    window.open("/hos/page/org/addDoc")
}
function addDept() {
    $.ajax({
        url:"/org/addDept",
        type:"post",
        data:{name:$("#deptNameInput").val(),descr:$("#deptDescrInput").html()},
        success:function(re){
            if(re.code!=200){
                jQuery.alertWindow("保存成功");
                getDeptList();
            }else {
                jQuery.alertWindow(re.msg);
            }
            $("#addDeptDiv").hide();
        }
    });
}
function addDpetDiv() {
    $("#addDeptDiv").show();
}

function getDeptList(){
    $.ajax({
        url:"/org/getDeptList",
        type:"post",
        success:function(re){
            if(re.code!=200){
                var list=re.extend.list;
                $("#deptUl").empty();
                for (var dept of list) {
                    $("#deptUl").append("<li did="+dept.id+" onclick=getDeptPerson('"+dept.id+"',-1,1,'"+dept.name+"')>"+dept.name+"</li>");
                }
                if(list.length>0){
                    getDeptPerson(list[0].id,-1,1,list[0].name);

                }
            }
        }
    });
}



var isLeave=-1;
var did='';
var deptName;
/*导航条页码*/
function navPage(t) {
    getDeptPerson(did,isLeave,$(t).attr("pNum"),deptName)
}
function getOrderOrgList(t) {
    getDeptPerson(did,isLeave,$(t).val(),deptName)
}
function doc_search_key() {
    getDeptPerson(did,isLeave,1,deptName)
}

function getDeptPerson(d,isL,pageNum,name) {
    $("#deptName").html(name);
    if(d=='')return;
    did=d;
    isLeave=isL;
    //var formData = new FormData(document.getElementById('loginForm'));
    $.ajax({
        url: "/org/getDoctorList",
        type: 'POST',
        data:{deptId:did,isLeave:isLeave,key:$("#doctor_search_key").val(),pageNum:pageNum,pageSize:10},
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
                    inHtml+=htmlModel
                        .replace("{id}",list[i].id)
                        .replace("{name}",list[i].name==undefined?"":list[i].name)
                        .replace("{position}",list[i].position==undefined?"":list[i].position)
                        .replace("{entryTime}",list[i].entry_time==undefined?"":list[i].entry_time)
                        .replace("{isLeave}",list[i].is_leave==undefined?"":(list[i].is_leave==0?"在职":"离职"));
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

function getDoctorDetail(t) {
    var id=$(t).attr("doctorId");
    window.open("/hos/page/org/docDetail/"+id);
}