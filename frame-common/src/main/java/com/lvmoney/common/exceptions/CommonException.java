package com.lvmoney.common.exceptions;

/**
 * @describe：路由错误码定义接口
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:34:43
 */
public interface CommonException {
    final int PROXYERR = 900;

    enum Proxy implements ExceptionType {
        METHOD_NOT_MAP(PROXYERR + 1, "uri not mapping"),
        JSON_ERROR(PROXYERR + 2, "json invalid"),
        PARAMETER_ERROR(PROXYERR + 3, "param Violation error"),
        AUTHENTICATION_ERROR(PROXYERR + 4, "authentication Violation error"),
        PERMISSION_ERROR(PROXYERR + 5, "permission Violation error"),
        METHOD_INVOKE_ERROR(PROXYERR + 6, "method invoke error"),
        TOKEN_IS_REQUIRED(PROXYERR + 7, "token is Required"),
        TOKEN_NOT_EXSIT(PROXYERR + 8, "token not exist"),
        TOKEN_CHECK_FAILED(PROXYERR + 9, "token check failed"),
        TOKEN_USER_NOT_EXSIT(PROXYERR + 10, "token,user not exist"),
        TOKEN_USER_ID_ERROR(PROXYERR + 11, "token,get userid error"),
        TOKEN_VERIFY_ERROR(PROXYERR + 12, "token verify error"),
        SHIRO_VERIFY_ERROR(PROXYERR + 13, "shiro verify error"),
        SHIRO_KICKOUT_ERROR(PROXYERR + 14, "shiro kickout error"),
        SHIRO_UNAUTHORIZED_EXCEPTIONT(PROXYERR + 15, "shiro UnauthorizedException"),
        SHIRO_URI_EXCEPTIONT(PROXYERR + 30, "reids shiro URI is null"),
        SHIRO_AUTHORIZATION_EXCEPTIONT(PROXYERR + 16, "shiro AuthorizationException"),
        SHIRO_REDIS_NOT_EXSIT(PROXYERR + 17, "shiro,redis data error"),
        REDIS_NOT_EXSIT(PROXYERR + 18, "redis data error"),
        REDIS_KEY_IS_REQUIRED(PROXYERR + 19, "redis key is Required"),
        MONGO_SORT_TYPE_IS_REQUIRED(PROXYERR + 20, "mongo sortType is Required"),
        MONGO_SORT_TYPE_IS_ERROR(PROXYERR + 21, "mongo sortType value is desc or asc"),
        GRIDFS_SAVE_ERROR(PROXYERR + 22, "gridFs save error"),
        GRIDFS_FILE_SIZE(PROXYERR + 23, "gridFs fileSize too long"),
        GRIDFS_QUERY_FILE_ERROR(PROXYERR + 24, "gridFs get file error"),
        RABBITMQ_DYNAMIC_NOT_EXSIT(PROXYERR + 25, "rabbitmq @DynamicService bean not exist"),
        GRIDFS_QUERY_FILE_NOT_EXSIT(PROXYERR + 26, "gridFs get file not exsit"),
        ES_QUERY_CONTENT_IS_REQUIRED(PROXYERR + 40, "elasticsearch query content is required"),
        ES_QUERY_BEAN_IS_REQUIRED(PROXYERR + 41, "elasticsearch query bean is required"),
        ES_QUERY_PAGEABLE_IS_REQUIRED(PROXYERR + 42, "elasticsearch query pageable is required"),
        ES_QUERY_PERCENT_IS_ERROR(PROXYERR + 43, "elasticsearch query percent need Less Than One"),
        ES_DELETE_IS_ERROR(PROXYERR + 44, "elasticsearch delete error"),
        ES_DELETE_FIELDS_IS_REQUIRED(PROXYERR + 45, "elasticsearch FIELDS error is Required"),
        OAUTH2_USER_DETAIL_NO_EXIST(PROXYERR + 60, "oauth2 server not find userdetail"),
        OAUTH2_USER_DETAIL_ERROR(PROXYERR + 61, "oauth2 server  userdetail error"),
        OAUTH2_CLIENT_DETAIL_NO_EXIST(PROXYERR + 62, "oauth2 server not find clientdetail"),
        RABBIT_MESSAGE_RECEIVER_SIMPLE_ERROR(PROXYERR + 63, "rabbitmq receiver simple message error"),
        RABBIT_MESSAGE_RECEIVER_TOPIC_ERROR(PROXYERR + 64, "rabbitmq receiver topic message error"),
        RABBIT_MESSAGE_RECEIVER_FANOUT_ERROR(PROXYERR + 66, "rabbitmq receiver fanout message error"),
        KAFKA_SEND_SYN_INTERRUPTED_ERROR(PROXYERR + 67, "kafka Synchronous send  interrupted error"),
        KAFKA_SEND_SYN_EXE_ERROR(PROXYERR + 67, "kafka Synchronous send  Execution error"),
        KAFKA_SEND_SYN_TIME_ERROR(PROXYERR + 67, "kafka Synchronous send  timeout error"),
        KAFKA_DYNAMIC_NOT_EXSIT(PROXYERR + 68, "kafka @DynamicService bean not exist"),
        WORD_2_OFFICE_ERROR(PROXYERR + 69, "office word 2 pdf error"),
        TEMPLATE_2_WORD_ERROR(PROXYERR + 70, "office template 2 word error"),
        TEMPLATE_NOT_EXSIT(PROXYERR + 71, "office template not exsit"),
        TEMPLATE_ELEMENT_NOT_SUPPORT(PROXYERR + 72, "office template element not support"),
        SHIRO_SUPPORT_ERROR(PROXYERR + 81, "frame.shiro.support value is 'true' or 'false'"),
        BASE64_PARAM_IS_REQUIRED(PROXYERR + 82, "base64 paramter is required"),
        BASE64_ENCODING_ERROR(PROXYERR + 83, "base64 encoding Unsupported"),
        FILE_INPUTSTREAM2FILE_ERROR(PROXYERR + 84, "inputstream 2 file error"),
        TOKEN_SUPPORT_ERROR(PROXYERR + 81, "frame.jwt.support value is 'true' or 'false'"),
        EXCEL_DOWNLOAD_ERROR(PROXYERR + 82, "excel download error"),
        EXCEL_TEMPLATE_ERROR(PROXYERR + 83, "excel template error"),
        LOCK_SOURCE_ERROR(PROXYERR + 88, "get lock data error"),
        LOCK_SOURCE_INIT_ERROR(PROXYERR + 90, "lock data init error"),
        LOCK_SOURCE_NOT_EXIST(PROXYERR + 88, "lock data not exist"),
        BEAN_LIST_COPY_ERROR(PROXYERR + 89, "list bean copy error"),
        EXCEL_IMPORT_ERROR(PROXYERR + 84, "excel import error"),
        USERNMAE_IS_REQUIRED(PROXYERR + 85, "log record need token or username"),
        LOG_SUPPORT_ERROR(PROXYERR + 86, "frame.log.support value is 'true' or 'false'"),
        VALIDCOE_ERROR(PROXYERR + 87, "create Verification Code error"),
        QUARTZ_INIT_ERROR(PROXYERR + 95, "quartz init error"),
        QUARTZ_CRON_ERROR(PROXYERR + 96, "quartz cron error"),
        QUARTZ_JOB_ADD_ERROR(PROXYERR + 97, "quartz add job error"),
        QUARTZ_JOB_UPDATE_ERROR(PROXYERR + 98, "quartz update job error"),
        QUARTZ_JOB_DELETE_ERROR(PROXYERR + 99, "quartz delete job error"),
        QUARTZ_JOB_CLOSE_ALL_ERROR(PROXYERR + 100, "quartz close all job error"),
        QUARTZ_JOB_EXE_ERROR(PROXYERR + 101, "quartz  job execute error"),
        HTTPCLIENT_JSON_ERROR(PROXYERR + 102, "httpclient json request error"),
        HTTPCLIENT_FILE_ERROR(PROXYERR + 103, "httpclient file request error"),
        HTTPCLIENT_FILE2_ERROR(PROXYERR + 105, "httpclient file request error"),
        HTTPCLIENT_CONNECTION_ERROR(PROXYERR + 104, "httpclient request connection msg error"),


        OTHER(PROXYERR + 200, "unkonw exception");


        private int code;
        private String description;

        Proxy(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

//                public int getCode(String description){
//                        Proxy.
//                }
    }
}
