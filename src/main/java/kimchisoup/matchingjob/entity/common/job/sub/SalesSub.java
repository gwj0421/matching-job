package kimchisoup.matchingjob.entity.common.job.sub;

public enum SalesSub {
    PRODUCT_SALES("제품영업"),
    SERVICE_SALES("서비스영업"),
    OVERSEAS_SALES("해외영업"),
    ADVERTISING_SALES("광고영업"),
    FINANCIAL_SALES("금융영업"),
    CORPORATE_SALES("법인영업"),
    IT_TECH_SALES("IT·기술영업"),
    SALES_MANAGEMENT("영업관리"),
    SALES_SUPPORT("영업지원"),
    NONE("None");

    private final String name;

    SalesSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
