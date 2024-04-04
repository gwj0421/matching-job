package kimchisoup.matchingjob.entity.common.job.sub;

public enum EducationSub {
    KINDERGARTEN_TEACHER("유치원·보육교사"),
    SCHOOL_SPECIAL_EDUCATION_TEACHER("학교·특수학교교사"),
    UNIVERSITY_PROFESSOR_INSTRUCTOR("대학교수·강사"),
    ACADEMY_INSTRUCTOR("학원강사"),
    FOREIGN_LANGUAGE_INSTRUCTOR("외국어강사"),
    TECHNICAL_EXPERT_INSTRUCTOR("기술·전문강사"),
    LEARNING_CENTER_VISITING_TEACHER("학습지·방문교사"),
    EDUCATION_CENTER_COUNSELING_OPERATIONS("학원상담·운영"),
    EDUCATIONAL_STAFF_ASSISTANT("교직원·조교"),
    TEXTBOOK_DEVELOPMENT_INSTRUCTOR_DESIGN("교재개발·교수설계"),
    NONE("None");

    private final String name;

    EducationSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
