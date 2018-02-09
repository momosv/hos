package cn.momosv.hos.util;


public class Constants {
   int MAX_FILE_UPLOAD_SIZE = 5242880;  
   String MOBILE_NUMBER_SESSION_KEY = "sessionMobileNumber";  
   String USER_CODE_SESSION_KEY = "userCode";  
   String SESSION_KEY = "sessionId";
   //全部-1,0未审批，1通过，2不通过，3已经审批
   public final static Integer USER_ALL_TYPE=-1;
   public final static Integer USER_UN_APPROVED=0;
   public final static Integer USER_PASSED=1;
   public final static Integer USER_UN_PASSED=2;
   public final static Integer USER_APPROVED=3;
}
