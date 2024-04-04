package kimchisoup.matchingjob.entity.common.job.sub;

public enum PublicWelfareSub {
    SOCIAL_WORKER("사회복지사"),
    CARE_WORKER("요양보호사"),
    ENVIRONMENTAL_CLEANER("환경미화원"),
    HEALTH_ADMINISTRATOR("보건관리자"),
    LIBRARIAN("사서"),
    VOLUNTEER("자원봉사자"),
    EPIDEMIC_PREVENTION("방역·방재기사"),
    NONE("None");

    private final String name;

    PublicWelfareSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
