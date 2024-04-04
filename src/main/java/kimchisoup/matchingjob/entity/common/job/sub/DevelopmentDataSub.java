package kimchisoup.matchingjob.entity.common.job.sub;

public enum DevelopmentDataSub {
    BACKEND_DEVELOPER("백엔드개발자"),
    FRONTEND_DEVELOPER("프론트엔드개발자"),
    WEB_DEVELOPER("웹개발자"),
    APP_DEVELOPER("앱개발자"),
    SYSTEM_ENGINEER("시스템엔지니어"),
    NETWORK_ENGINEER("네트워크엔지니어"),
    DATABASE_ADMINISTRATOR("DBA"),
    DATA_ENGINEER("데이터엔지니어"),
    DATA_SCIENTIST("데이터사이언티스트"),
    SECURITY_ENGINEER("보안엔지니어"),
    SOFTWARE_DEVELOPER("소프트웨어개발자"),
    GAME_DEVELOPER("게임개발자"),
    HARDWARE_DEVELOPER("하드웨어개발자"),
    MACHINE_LEARNING_ENGINEER("머신러닝엔지니어"),
    BLOCKCHAIN_DEVELOPER("블록체인개발자"),
    CLOUD_ENGINEER("클라우드엔지니어"),
    WEB_PUBLISHER("웹퍼블리셔"),
    IT_CONSULTANT("IT컨설팅"),
    QA("QA"),
    NONE("None");

    private final String name;

    DevelopmentDataSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
