package kimchisoup.matchingjob.entity.common;

import lombok.Getter;

@Getter
public enum WorkType {
    FULL_TIME("정규직"),
    FIXED_TERM("계약직"),
    DISPATCHED("파견직"),
    PART_TIME("아르바이트"),
    INTERNSHIP("인턴"),
    FREELANCER("프리랜서"),
    SELF_EMPLOYED("자영업"),
    DAILY("일용직"),
    TEMPORARY("임시직"),
    PROJECT_BASED("프로젝트 기반 근로");

    private final String desc;

    WorkType(String desc) {
        this.desc = desc;
    }
}
