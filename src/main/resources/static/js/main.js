
function user_case_manager(){
    hide_head();
    $("#user_page").show();
    $("#user_iframe").attr("src",'/allowHtml/user/caseHead.html');
}

function doc_case_manager(){
    hide_head();
    $("#doc_page").show();
    $("#doc_iframe").attr("src",'/allowHtml/doc/caseHead.html');
}

//org 管理员
function org_dept_manager(){
    hide_head();
    $("#org_page").show();
    $("#org_iframe").attr("src",'/allowHtml/org/deptHead.html');
}
function org_approve_manager(){
    hide_head();
    $("#org_page").show();
    $("#org_iframe").attr("src",'/allowHtml/org/approveHead.html');
}
function my_org(){
    hide_head();
    $("#org_page").show();
    $("#org_iframe").attr("src",'/hos/org/my');
}
function my_sys(){
    hide_head();
    $("#sys_page").show();
    $("#sys_iframe").attr("src",'/hos/sys/my');
}
function my_doc(){
    hide_head();
    $("#doc_page").show();
    $("#doc_iframe").attr("src",'/hos/doc/my');
}
function my_user(){
    hide_head();
    $("#doc_page").show();
    $("#doc_iframe").attr("src",'/hos/user/my');
}

//sys 管理员
function sys_org_manager(){
    hide_head();
    $("#sys_page").show();
    $("#sys_iframe").attr("src",'/allowHtml/sys/sysHead.html');
}
function user_org_manager(){
    hide_head();
    $("#sys_page").show();
    $("#sys_iframe").attr("src",'/allowHtml/sys/userHead.html');
}
function contact_manager(){
    hide_head();
    $("#sys_page").show();
    $("#sys_iframe").attr("src",'/allowHtml/sys/contactHead.html');
}

function hide_head() {
    $("#body").hide();
    $("#foot").hide();
}
function show_head() {
    $("#body").show();
    $("#foot").show();
    $("#sys_page").hide();
    $("#org_page").hide();
    $("#doc_page").hide();
    $("#user_page").hide();
}

function  hiddenLoginForm() {
    $("#loginDiv").hide();
}
function login_form(t){
    $("#loginDiv").show();
}

function login() {
        //var formData = new FormData(document.getElementById('loginForm'));
        $.ajax({
            url: "/hos/login/accept",
            type: 'POST',
            data: $('#loginForm').serialize(),
            success: function(re) {
                var extend=re.extend;
                if (re.code != 200) {
                    if(extend.identity=='sys'){
                        $('.a_sys').show();
                    }else if(extend.identity=='org'){
                        $('.a_org').show();
                    }else if(extend.identity=='doctor'){
                        $('.a_doc').show();
                    }else if(extend.identity=='normal'){
                        $('.a_user').show();
                    }
                    $('.exit').show();
                    $("#login_button").hide();
                    $("#loginDiv").hide();
                } else {
                    alert(re.msg);
                }
            },
            error: function(rs) {
                alert(rs.extend.msg);
            }
        });
}

function exit() {
        //var formData = new FormData(document.getElementById('loginForm'));
        $.ajax({
            url: "/hos/exit",
            type: 'POST',
            success: function(re) {
                if (re.code != 200) {
                   $(".a_head_child").hide();
                    $("#login_button").show();

                }
            },
            error: function(rs) {
                alert(rs.extend.msg);
            }
        });
        show_head();
}
function login_type_select(t) {
    if($(t).val()=="doctor"||$(t).val()=="org"){
        $("#orgSelect").show();
        $("#orgSelect").empty();
        var loginAccount=$("#loginAccount").val();
        var typeSelect=$("#typeSelect").val();
        $.ajax({
            url: "/hos/login/getOrg",
            type: 'POST',
            data: {loginAccount:loginAccount,type:typeSelect},
            dataType: 'json',
            beforeSend: function() {
               // $("#logintips").html("正在验证登录，请稍候");
            },

            success: function(re) {
                if (re.code != 200) {
                    if(re.extend.list==null){
                        alert("无法获取该账号所属的机构，请确认账号无误");
                        return;
                    }
                    $.each(re.extend.list,function(i,item){
                        var id=item.id;
                        var name = item.name;
                        $("#orgSelect").append('<option value='+id+'>'+name+'</option>');
                    });
                } else {
                    alert("无法获取该账号所属的机构，请确认账号无误");
                }
            },
            error: function(rs) {
                alert(rs.extend.msg);
            }
        });
    }else {
        $("#orgSelect").hide();
    }
}

$(
    function(){
    $("#orgSelect").hide();
    $("#orgSelect").empty();
    if($("#identity").val()=='sys'){
        $(".a_sys").show();
        $(".exit").show();
        $("#login_button").hide();
    }else if($("#identity").val()=='org'){
        $(".a_org").show();
        $(".exit").show();
        $("#login_button").hide();

    }else if($("#identity").val()=='doctor'){
        $(".a_doc").show();
        $(".exit").show();
        $("#login_button").hide();

    }else if($("#identity").val()=='normal'){
        $(".a_user").show();
        $(".exit").show();
        $("#login_button").hide();
    }
    }
);