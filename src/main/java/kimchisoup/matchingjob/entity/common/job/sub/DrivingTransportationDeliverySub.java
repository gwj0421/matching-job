package kimchisoup.matchingjob.entity.common.job.sub;

public enum DrivingTransportationDeliverySub {
    DELIVERY_DRIVER("납품·배송기사"),
    COURIER("배달기사"),
    OPERATIONAL_DRIVER("수행·운전기사"),
    FREIGHT_DRIVER("화물·중장비기사"),
    BUS_DRIVER("버스기사"),
    TAXI_DRIVER("택시기사"),
    PILOT("조종·기관사"),
    NONE("None");

    private final String name;

    DrivingTransportationDeliverySub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
