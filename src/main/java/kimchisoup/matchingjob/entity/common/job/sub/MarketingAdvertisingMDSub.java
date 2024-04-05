package kimchisoup.matchingjob.entity.common.job.sub;

public enum MarketingAdvertisingMDSub {
    ADVERTISING_PLANNER("AE(광고기획자)"),
    BRAND_MARKETER("브랜드마케터"),
    PERFORMANCE_MARKETER("퍼포먼스마케터"),
    CRM_MARKETER("CRM마케터"),
    ONLINE_MARKETER("온라인마케터"),
    CONTENT_MARKETER("콘텐츠마케터"),
    PUBLIC_RELATIONS("홍보"),
    SURVEY_RESEARCHER("설문·리서치"),
    MARKETING_DIRECTOR("MD"),
    COPYWRITER("카피라이터"),
    CREATIVE_DIRECTOR("크리에이티브디렉터"),
    CHANNEL_MANAGER("채널관리자"),
    GROWTH_HACKER("그로스해커"),
    NONE("None");

    private final String name;

    MarketingAdvertisingMDSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
