package kimchisoup.matchingjob.entity.common.job.sub;

public enum EngineeringDesignSub {
    ELECTRICAL_ELECTRONIC_ENGINEER("전기·전자엔지니어"),
    MECHANICAL_ENGINEER("기계엔지니어"),
    DESIGN_ENGINEER("설계엔지니어"),
    FACILITIES_ENGINEER("설비엔지니어"),
    SEMICONDUCTOR_ENGINEER("반도체엔지니어"),
    CHEMICAL_ENGINEER("화학엔지니어"),
    PROCESS_ENGINEER("공정엔지니어"),
    HARDWARE_ENGINEER("하드웨어엔지니어"),
    COMMUNICATIONS_ENGINEER("통신엔지니어"),
    RF_ENGINEER("RF엔지니어"),
    FIELD_ENGINEER("필드엔지니어"),
    RESEARCH_DEVELOPMENT("R&D·연구원"),
    NONE("None");

    private final String name;

    EngineeringDesignSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
