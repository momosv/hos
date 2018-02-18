function login() {
        //var formData = new FormData(document.getElementById('loginForm'));
        $.ajax({
            url: "/hos/login/accept",
            type: 'POST',
            data: $('#loginForm').serialize(),
            success: function(re) {
                if (re.code != 200) {
                    alert(re.msg)
                } else {
                    alert(re.msg);
                }
            },
            error: function(rs) {
                alert(rs.extend.msg);
            }
        });
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
    }
);