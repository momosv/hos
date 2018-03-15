
$(function () {

});

function getCaseSecondDetail(isTreat){
    $.ajax({
        url: "/user/getCase",
        type: 'POST',
        data: {
            id:$("#caseId").val(),
        },
        success: function (re) {
            if (re.code != 200) {
                var patient=re.extend.patient;
                var user=re.extend.user;
                var caseo=re.extend.case;
                if(patient!=null) {
                    $("#patientId").val(patient.id);
                    $("#idCard").val(patient.userId);
                    $("#treatCode").val(patient.treatCode);
                }
                if(user!=null) {
                    $("#name").val(user.name);
                    $("#sex").val(user.sex);
                    $("#age").val(setAge(user.birthday));
                    $("#birthday").val(user.birthday);
                    $("#telephone").val(user.telephone);
                    $("#email").val(user.email);
                    $("#bloodType").val(user.bloodType);
                    $("#weight").val(user.weight);
                    $("#address").val(user.address);
                    $("#isAgent").val(patient.isAgent);
                    $("#maritalStatus").val(user.maritalStatus);
                    $("#allergicHistory").val('');
                }
                if(caseo!=null){
                    $("#caseDate1").val(new Date(caseo.createTime).Format("yyyy-MM-ddThh:mm"));
                    $("#bedNum").val(caseo.bedNum);
                    $("#complaint").val(caseo.complaint);
                    $("#allergicHistory").val(caseo.allergicHistory);
                    $("#orgName").val(caseo.fromOrgName);
                    $("#deptName").val(caseo.fromDeptName);
                }
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}


function getCaseDetail(){
    $.ajax({
        url: "/user/getCase",
        type: 'POST',
        data: {
            id:$("#caseId").val(),
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }
            if (re.code != 200) {
                var patient=re.extend.patient;
                var user=re.extend.user;
                var caseo=re.extend.case;
                if(patient!=null) {
                    $("#patientId").val(patient.id);
                    $("#idCard").val(patient.userId);
                    $("#treatCode").val(patient.treatCode);
                }
                if(user!=null) {
                    $("#name").val(user.name);
                    $("#sex").val(user.sex);
                    $("#age").val(setAge(user.birthday));
                    $("#birthday").val(user.birthday);
                    $("#telephone").val(user.telephone);
                    $("#email").val(user.email);
                    $("#bloodType").val(user.bloodType);
                    $("#weight").val(user.weight);
                    $("#address").val(user.address);
                    $("#isAgent").val(patient.isAgent);
                    $("#maritalStatus").val(user.maritalStatus);
                    $("#allergicHistory").val('');
                }
                if(caseo!=null){
                    $("#caseDate1").val(new Date(caseo.createTime).Format("yyyy-MM-ddThh:mm"));
                     $("#complaint").val(caseo.complaint);
                     $("#preHistory").val(caseo.preHistory);
                     $("#pastHistory").val(caseo.pastHistory);
                     $("#surgicalHistory").val(caseo.surgicalHistory);
                     $("#familyHistory").val(caseo.familyHistory);
                     $("#phyExam").val(caseo.phyExam);
                     $("#medicalRecord").val(caseo.medicalRecord);
                     $("#diagnosis").val(caseo.diagnosis);
                     $("#treat").val(caseo.treat);
                     $("#remark").val(caseo.remark);
                     $("#bedNum").val(caseo.bedNum);
                     $("#allergicHistory").val(caseo.allergicHistory);
                     $("#orgName").val(caseo.fromOrgName);
                     $("#deptName").val(caseo.fromDeptName);
                }
                getCaseSecondList();
            }else {
                jQuery.alertWindow(re.msg,2000);
            }
        }
    });
}


function getCaseSecondList() {
    $.ajax({
        url: "/doc/getCaseSecondList",
        type: 'POST',
        data: {
            caseId:$("#caseId").val(),
        },
        success:function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else if (re.code != 200) {
                $("#returnL").empty();
                $("#anaL").empty();
                $("#conL").empty();
                $("#leaL").empty();
                $("#hosL").empty();
                $("#surL").empty();
                $("#traL").empty();

                for (var obj of re.extend.hosList) {
                    var inH="";
                    for(var sc of obj.returnList){
                     inH+=" <li id="+sc.id+" sid="+obj.id+" onclick=\"getSecondReturn(2,this)\">" +
                         new Date(sc.createTime).Format("yyyyMMdd-hhmmss")+
                         "</li>";
                    }

                    $("#hosL").append(
                        " <li ><span id="+obj.id+" onclick=\"getSecond(2,this)\">"+
                        new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+
                        "<ul>" +
                        inH+
                        "</ul></li>");
                }
                for (var obj of re.extend.surList) {
                    var inH="";
                    for(var sc of obj.returnList){
                        inH+=" <li id="+sc.id+" sid="+obj.id+"  onclick=\"getSecondReturn(3,this)\">" +
                            new Date(sc.createTime).Format("yyyyMMdd-hhmmss")+
                            "</li>";
                    }

                    $("#surL").append(
                        "<li ><span id="+obj.id+" onclick=\"getSecond(3,this)\">"+
                        new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+
                        "<ul>" +
                        inH+
                        "</ul></li>");
                }
                for (var obj of re.extend.anaList) {
                    $("#anaL").append(" <li id="+obj.id+" onclick=\"getSecond(4,this)\">"+new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+"</li>")
                }
                for (var obj of re.extend.returnList) {
                    $("#returnL").append(" <li id="+obj.id+" onclick=\"getSecond(1,this)\">"+new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+"</li>")
                }
                for (var obj of re.extend.conList) {
                    $("#conL").append(" <li id="+obj.id+" onclick=\"getSecond(5,this)\">"+new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+"</li>")
                }

                for (var obj of re.extend.traList) {
                    $("#traL").append(" <li id="+obj.id+" onclick=\"getSecond(6,this)\">"+new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+"</li>")
                }
                for (var obj of re.extend.leaList) {
                    $("#leaL").append(" <li id="+obj.id+" onclick=\"getSecond(7,this)\">"+new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+"</li>")
                }
            }else {
               // jQuery.alertWindow(re.msg);
            }
        }
    });
}


function getPatient(){
    $.ajax({
        url: "/doc/getCasePatient",
        type: 'POST',
        data: {
            idCard: $("#idCard").val(),
            treatCode: $("#treatCode").val(),
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                var patient=re.extend.patient;
                var user=re.extend.user;
                if(patient!=null) {
                    $("#patientId").val(patient.id);
                    $("#treatCode").val(patient.treatCode);
                    $("#idCard").val(patient.userId);
                }
                if(user!=null) {
                    $("#name").val(user.name);
                    $("#sex").val(user.sex);
                    $("#age").val(setAge(user.birthday));
                    $("#birthday").val(user.birthday);
                    $("#telephone").val(user.telephone);
                    $("#email").val(user.email);
                    $("#bloodType").val(user.bloodType);
                    $("#weight").val(user.weight);
                    $("#address").val(user.address);
                    $("#isAgent").val(patient.isAgent);
                    $("#maritalStatus").val(user.maritalStatus);
                    $("#allergicHistory").val('');
                }else {
                    $("#name").val('');
                    $("#idCard").val('');
                    $("#sex").val('');
                    $("#age").val('');
                    $("#telephone").val('');
                    $("#email").val('');
                    $("#bloodType").val('');
                    $("#weight").val('');
                    $("#allergicHistory").val('');
                    $("#address").val('');
                }
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function setAge(date) {
   var ageNum= jsGetAge(date);
    $("#age").val(ageNum);
    return ageNum;
}


function getReturn() {
    $.ajax({
        url: "/doc/getReturnVisit",
        type: 'POST',
        data: {
            id: $("#secondId").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                var detail =re.extend.detail;
                $("#returnDate").val(new Date(detail.createTime).Format("yyyy-MM-ddThh:mm"));
                   $("#summary").val(detail.summary);
                    $("#phyExam").val(detail.phyExam);
                     $("#medicalRecord").val(detail.medicalRecord);
                     $("#diagnosis").val(detail.diagnosis);
                     $("#treat").val(detail.treat);
                    $("#remark").val(detail.remark);
            }else{
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function getHospitalized() {
    $.ajax({
        url: "/doc/getHospitalized",
        type: 'POST',
        data: {
            id: $("#secondId").val()
        },
        success: function (re) {
            if(re.code==-1){
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
                window.open("/hos/login");
            }else  if (re.code != 200) {
                var detail=re.extend.detail;
                     $("#hosDate").val(new Date(detail.createTime).Format("yyyy-MM-ddThh:mm"));
                     $("#summary").val(detail.summary);
                     $("#cause").val(detail.cause);
                     $("#internal").val(detail.internal);
                     $("#external").val(detail.external);
                     $("#phyExam").val(detail.phyExam);
                     $("#medicalRecord").val(detail.medicalRecord);
                     $("#diagnosis").val(detail.diagnosis);
                     $("#remark").val(detail.remark);
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function getSurgery() {
    $.ajax({
        url: "/doc/getSurgery",
        type: 'POST',
        data: {
            id: $("#secondId").val()
        },
        success: function (re) {
            if(re.code==-1){
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
                window.open("/hos/login");
            }else  if (re.code != 200) {
                var detail=re.extend.detail;
                $("#surgeryDate").val(new Date(detail.createTime).Format("yyyy-MM-ddThh:mm"));
                $("#cause").val(detail.cause);
                $("#start").val(detail.start);
                $("#finding").val(detail.finding);
                $("#process").val(detail.process);
                $("#treat").val(detail.treat);
                $("#end").val(detail.end);
                $("#afterTreat").val(detail.afterTreat);
                $("#diagnosis").val(detail.diagnosis);
                $("#afterTreat").val(detail.afterTreat);
                $("#attention").val(detail.attention);
                $("#surgeon").val(detail.surgeon);
                $("#remark").val(detail.remark);
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}


function getSecond(num,t) {
    window.open("/hos/page/user/getSecond/"+$("#caseId").val()+"/"+$(t).attr("id")+"/"+num);

}

function getSecondReturn(num,t) {
    window.open("/hos/page/user/getSecondReturn/"+$("#caseId").val()+"/"+$(t).attr("id")+"/"+num);
}


function getSecondReturnList(num){

    $.ajax({
        url: "/doc/getSecondReturn",
        type: 'POST',
        data: {
            secondId:$("#secondId").val(),
            type:num
        },
        success:function(re){
            $("#secondReturnUl").empty();
            var inH="";
            for (var obj of re.extend.list) {
                inH+="<li id="+obj.id+" onclick=getSecondReturn("+num+",this)>" +
                        new Date(obj.createTime).Format("yyyyMMdd-hhmmss")+
                        "</li>";
            }
            $("#secondReturnUl").html(inH);
        }
    });

}