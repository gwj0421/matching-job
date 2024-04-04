package kimchisoup.matchingjob.entity.common.job.sub;

public enum CustomerServiceTMSub {
    INBOUND_CONSULTANT("인바운드상담원"),
    OUTBOUND_CONSULTANT("아웃바운드상담원"),
    CUSTOMER_SERVICE_MANAGER("고객센터관리자"),
    NONE("None");

    private final String name;

    CustomerServiceTMSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
