
$(function () {

});

function getCaseSecondDetail(isTreat){
    $.ajax({
        url: "/doc/getCase",
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
                }
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}


function getCaseDetail(){
    $.ajax({
        url: "/doc/getCase",
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
                }
            }else {
                jQuery.alertWindow(re.msg);
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
                        "</span><input id="+obj.id+" type=\"button\" value=\"+\" class=\"btn\" onclick=\"addSecondReturn(2,this)\"><ul>" +
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
                        "</span><input id="+obj.id+" type=\"button\" value=\"+\" class=\"btn\" onclick=\"addSecondReturn(3,this)\"><ul>" +
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
                jQuery.alertWindow(re.msg);
            }
        }
    });
}
function updateCase(){
    $.ajax({
        url: "/doc/updateCase",
        type: 'POST',
        data: {
            id:$("#caseId").val(),
            createTime: $("#caseDate1").val(),
            allergicHistory: $("#allergicHistory").val(),
            complaint: $("#complaint").val(),
            preHistory: $("#preHistory").val(),
            pastHistory: $("#pastHistory").val(),
            surgicalHistory: $("#surgicalHistory").val(),
            familyHistory: $("#familyHistory").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            diagnosis: $("#diagnosis").val(),
            treat: $("#treat").val(),
            remark: $("#remark").val(),
            benNum: $("#bedNum").val(),
        },
        success: function (re) {
            if (re.code != 200) {
                jQuery.alertWindow("更新成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function saveCase(){
    $.ajax({
        url: "/doc/addCase",
        type: 'POST',
        data: {
            patientId: $("#patientId").val(),
            caseDate: $("#caseDate").val(),
            name: $("#name").val(),
            idCard: $("#idCard").val(),
            sex: $("#sex").val(),
            age: $("#age").val(),
            treatCode: $("#treatCode").val(),
            telephone: $("#telephone").val(),
            email: $("#email").val(),
            bloodType: $("#bloodType").val(),
            weight: $("#weight").val(),
            allergicHistory: $("#allergicHistory").val(),
            address: $("#address").val(),
            isAgent: $("#isAgent").val(),
            maritalStatus:  $("#maritalStatus").val(),
            bedNum:  $("#bedNum").val(),

            complaint: $("#complaint").val(),
            preHistory: $("#preHistory").val(),
            pastHistory: $("#pastHistory").val(),
            surgicalHistory: $("#surgicalHistory").val(),
            familyHistory: $("#familyHistory").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            diagnosis: $("#diagnosis").val(),
            treat: $("#treat").val(),
            remark: $("#remark").val(),
        },
        success: function (re) {
            if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
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

function addSecond(num){
    window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);

}
function addSecondReturn(num,t){
    alert($(t).attr("id"));
    window.open("/hos/page/doc/addSecondReturn/"+$("#caseId").val()+"/"+$(t).attr("id")+"/"+num);
}

function saveReturn(type) {
    $.ajax({
        url: "/doc/addReturnVisit",
        type: 'POST',
        data: {
            patientId: $("#patientId").val(),
            caseId: $("#caseId").val(),
            secondId: $("#secondId").val(),
            summary: $("#summary").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            diagnosis: $("#diagnosis").val(),
            treat: $("#treat").val(),
            remark: $("#remark").val(),
            type: type,
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
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

function updateReturn() {
    $.ajax({
        url: "/doc/updateReturnVisit",
        type: 'POST',
        data: {
            Id: $("#secondId").val(),
            summary: $("#summary").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            diagnosis: $("#diagnosis").val(),
            treat: $("#treat").val(),
            remark: $("#remark").val(),
            createTime: $("#returnDate").val(),
            type: type,
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                jQuery.alertWindow("保存成功");
                getReturn(1);
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function saveHospitalized() {
    $.ajax({
        url: "/doc/addHospitalized",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
            createTime: $("#hosDate").val(),
            summary: $("#summary").val(),
            cause: $("#cause").val(),
            internal: $("#internal").val(),
            external: $("#external").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            diagnosis: $("#diagnosis").val(),
            remark: $("#remark").val(),
            bedNum:  $("#bedNum").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function saveTransfer() {
    $.ajax({
        url: "/doc/addTransfer",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
            createTime: $("#transferDate").val(),
            summary: $("#summary").val(),
            phyExam: $("#phyExam").val(),
            pathologyReport: $("#pathologyReport").val(),
            diagnosis: $("#diagnosis").val(),
            inProcess: $("#inProcess").val(),
            finalDiagnosis: $("#finalDiagnosis").val(),
            transferReason: $("#transferReason").val(),
            transferOrg: $("#transferOrg").val(),
            remark: $("#remark").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}
function saveLeaveHos() {
    $.ajax({
        url: "/doc/addLeaveHospital",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
            entryTime: $("#entryTime").val(),
            leaveTime: $("#leaveDate").val(),
            summary: $("#summary").val(),
            diagnosis: $("#diagnosis").val(),
            inProcess: $("#inProcess").val(),
            leaveSituation: $("#leaveSituation").val(),
            enjoin: $("#enjoin").val(),
            remark: $("#remark").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}
function saveAnalyze() {
    $.ajax({
        url: "/doc/addAnalyze",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
            createTime: $("#analyzeDate").val(),
            summary: $("#summary").val(),
            preState: $("#preState").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            treat: $("#treat").val(),
            analyzes: $("#analyzes").val(),
            plan: $("#plan").val(),
            remark: $("#remark").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function saveSurgery() {
    $.ajax({
        url: "/doc/addSurgery",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
            createTime: $("#surgeryDate").val(),
            cause: $("#cause").val(),
            start: $("#start").val(),
            finding: $("#finding").val(),
            process: $("#process").val(),
            treat: $("#treat").val(),
            end: $("#end").val(),
            afterTreat: $("#afterTreat").val(),
            diagnosis:  $("#diagnosis").val(),
            attention:  $("#attention").val(),
            surgeon:  $("#surgeon").val(),
            remark:  $("#remark").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else  if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}
function saveConsultation() {
    $.ajax({
        url: "/doc/addConsultation",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
            createTime: $("#conDate").val(),
            hosAndDept: $("#hosAndDept").val(),
            doctors: $("#doctors").val(),
            aim: $("#aim").val(),
            summary: $("#summary").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            opinion: $("#opinion").val(),
            suggestion:  $("#suggestion").val(),
            remark:  $("#remark").val()
        },
        success: function (re) {
            if(re.code==-1){
                window.open("/hos/login");
                jQuery.alertWindow("登录已经失效，请重新登录！",10000);
            }else if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}

function getSecond(num,t) {
    window.open("/hos/page/doc/getSecond/"+$("#caseId").val()+"/"+$(t).attr("id")+"/"+num);

}

function getSecondReturn(num,t) {
    window.open("/hos/page/doc/getSecondReturn/"+$("#caseId").val()+"/"+$(t).attr("id")+"/"+num);
}