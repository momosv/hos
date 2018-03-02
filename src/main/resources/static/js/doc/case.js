
$(function () {

});

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
            if (re.code != 200) {
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
    if(num==1){
        window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);
    }else if(num==2){
        window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);
    }else if(num==3){
        window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);
    }else if(num==4){
        window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);
    }else if(num==5){
        window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);
    }else if(num==6){
        window.open("/hos/page/doc/addSecond/"+$("#caseId").val()+"/"+num);
    }
}

function saveReturn(type) {
    $.ajax({
        url: "/doc/addReturnVisit",
        type: 'POST',
        data: {
            patientId: $("#patientId").val(),
            caseId: $("#caseId").val(),
            summary: $("#summary").val(),
            phyExam: $("#phyExam").val(),
            medicalRecord: $("#medicalRecord").val(),
            diagnosis: $("#diagnosis").val(),
            treat: $("#treat").val(),
            remark: $("#remark").val(),
            type: type,
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

function saveHospitalized() {
    $.ajax({
        url: "/doc/addHospitalized",
        type: 'POST',
        data: {
            caseId: $("#caseId").val(),
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
            if (re.code != 200) {
                jQuery.alertWindow("保存成功");
            }else {
                jQuery.alertWindow(re.msg);
            }
        }
    });
}