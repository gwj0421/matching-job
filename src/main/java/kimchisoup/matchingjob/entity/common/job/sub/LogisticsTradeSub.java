package kimchisoup.matchingjob.entity.common.job.sub;

public enum LogisticsTradeSub {
    LOGISTICS_MANAGER("물류관리자"),
    PURCHASING_MANAGER("구매관리자"),
    MATERIAL_MANAGER("자재관리자"),
    DISTRIBUTION_MANAGER("유통관리자"),
    TRADE_OFFICER("무역사무원"),
    NONE("None");

    private final String name;

    LogisticsTradeSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
