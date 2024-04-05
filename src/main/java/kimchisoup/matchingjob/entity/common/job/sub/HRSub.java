package kimchisoup.matchingjob.entity.common.job.sub;

public enum HRSub {
    HR_SPECIALIST("인사담당자"),
    HRD_HRM("HRD·HRM"),
    LABOR_RELATIONS_MANAGER("노무관리자"),
    JOB_MANAGER("잡매니저"),
    HEAD_HUNTER("헤드헌터"),
    CAREER_COUNSELOR("직업상담사"),
    None("None");

    private final String name;

    HRSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
