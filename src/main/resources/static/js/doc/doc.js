
$( function () {
        getCaseList();

}
);

function addcase(){
    window.open("/hos/page/doc/addCase","newWindow")
}


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
        url: "/doc/getCaseList",
        type: 'POST',
        data:{key:$("#case_search_key").val(),keyType:$("#case_key").val(),pageNum:pageNum,pageSize:10},
        success: function(re) {
            var extend=re.extend;

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
                        .replace("{id}",list[i].id)
                        .replace("{cid}",list[i].id)
                        .replace("{name}",list[i].name==undefined?"":list[i].name)
                        .replace("{diagnosis}",list[i].diagnosis==undefined?"":list[i].diagnosis)
                        .replace("{createTime}",list[i].create_time==undefined?"":list[i].create_time)
                        .replace("{treatCode}",list[i].treat_code==undefined?"":list[i].treat_code);
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

function getCaseDetail(t) {
    var id=$(t).attr("caseId");
    window.open("/hos/page/doc/caseDetail/"+id,"detailWindow");
}



//改变行的样式
$("tr").hover(function(){
    $(this).addClass("hao");
},function(){
    $(this).removeClass("hao");
})
//全选的点击事件
function ischeck(aa){
    if(aa.checked){
        $("input[type='checkbox']").each(function(){
            this.checked=true;
        })
    }else{
        $("input[type='checkbox']").each(function(){
            this.checked=false;
        })
    }

}
//普通复选框的点击事件
function ppcheck(bb){
    if(bb.checked){
        if($("#checkAll").is("checked")==false){
            $("#checkAll").attr("checked",true);
        }
    }
    if(!bb.checked){
        if($("input[type='checkbox']:checked").size()==1){
            $("#checkAll").attr("checked",false);
        }
    }
}

function deleteCase(){
    // 数组返回格式
  /*  var chk_value = [];
    $('input[name="checkBoxId"]:checked').each(function () {
        chk_value.push($(this).val());
    });*/
    //["11", "12", "13", "14"]

    // 返回字符串 以,号分割  并去掉最后一个,
    var str='';
    $('input[name="checkBoxId"]:checked').each(function () {
        str+=$(this).val()+',';
    });
    var res_str = str.substr(0,str.length-1);

    $.ajax({
        url: "/doc/deleteCase",
        type: 'POST',
        data: {ids: res_str},
        success: function (re) {
            if (re.code != 200) {
                jQuery.alertWindow("删除成功");
                getCaseList(1)
            }
        }
    });
}
function addCase(){
    window.open("/hos/page/doc/addCase","newWindow")
}

