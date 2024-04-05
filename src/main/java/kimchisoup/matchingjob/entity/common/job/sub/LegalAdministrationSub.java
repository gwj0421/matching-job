package kimchisoup.matchingjob.entity.common.job.sub;

public enum LegalAdministrationSub {
    BUSINESS_SUPPORT("경영지원"),
    OFFICE_ADMINISTRATOR("사무담당자"),
    GENERAL_AFFAIRS("총무"),
    OFFICE_ASSISTANT("사무보조"),
    LEGAL_AFFAIRS("법무담당자"),
    SECRETARY("비서"),
    LAWYER("변호사"),
    LEGAL_REPRESENTATIVE("법무사"),
    LEGAL_PARALEGAL("변리사"),
    LABOR_RELATIONS_SPECIALIST("노무사"),
    NONE("None");

    private final String name;

    LegalAdministrationSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
