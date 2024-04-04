package kimchisoup.matchingjob.entity.common.job.sub;

public enum PlanningStrategySub {
    BUSINESS_PLANNING("경영·비즈니스기획"),
    WEB_PLANNING("웹기획"),
    MARKETING_PLANNING("마케팅기획"),
    PL_PM_PO("PL·PM·PO"),
    CONSULTANT("컨설턴트"),
    CEO_COO_CTO("CEO·COO·CTO"),
    NONE("None");

    private final String name;

    PlanningStrategySub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
