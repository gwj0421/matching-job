package kimchisoup.matchingjob.entity.common.job.sub;

public enum ArchitectureFacilitySub {
    ARCHITECT("건축가"),
    BUILDING_TECHNICIAN("건축기사"),
    CONSTRUCTION_TECHNICIAN("시공기사"),
    ELECTRICIAN("전기기사"),
    CIVIL_ENGINEERING_TECHNICIAN("토목기사"),
    FACILITY_MANAGER("시설관리자"),
    SITE_MANAGER("현장관리자"),
    SAFETY_MANAGER("안전관리자"),
    PUBLIC_OFFICIAL("공무"),
    FIRE_FACILITIES("소방설비"),
    SITE_ASSISTANT("현장보조"),
    SUPERVISOR("감리원"),
    URBAN_LANDSCAPE_DESIGN("도시·조경설계"),
    ENVIRONMENTAL_TECHNICIAN("환경기사"),
    NON_DESTRUCTIVE_INSPECTION("비파괴검사원"),
    CERTIFIED_REAL_ESTATE_APPRAISER("공인중개사"),
    APPRAISER("감정평가사"),
    SALES_MANAGER("분양매니저"),
    NONE("None");

    private final String name;

    ArchitectureFacilitySub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
