package kimchisoup.matchingjob.entity.common.job.sub;

public enum ManufacturingProductionSub {
    PRODUCTION_WORKER("생산직종사자"),
    PRODUCTION_PROCESS_MANAGER("생산·공정관리자"),
    QUALITY_MANAGER("품질관리자"),
    PACKAGING_PROCESSOR("포장·가공담당자"),
    FACTORY_MANAGER("공장관리자"),
    WELDER("용접사"),
    NONE("None");

    private final String name;

    ManufacturingProductionSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
