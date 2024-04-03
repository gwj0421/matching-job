package kimchisoup.matchingjob.entity.common;

public enum CompanyType {
    MAJOR_COMPANY("대기업"),
    MIDSIZE_COMPANY("중견기업"),
    SMALL_COMPANY("중소기업"),
    FOREIGN("외국계"),
    PUBLIC_ENTERPRISE("공기업"),
    START_UP("스타트업");

    private final String desc;
    CompanyType(String desc){
        this.desc = desc;
    }
}
