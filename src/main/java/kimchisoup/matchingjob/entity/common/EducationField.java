package kimchisoup.matchingjob.entity.common;

import lombok.Getter;

@Getter
public enum EducationField {
    NONE("상관 없음"),
    HIGH_SCHOOL("고등학교 졸업"),
    ASSOCIATE("2년제 대학교 졸업"),
    BACHELOR("4년제 대학교 졸업"),
    MASTER("석사 학위"),
    DOCTOR("박사 학위");

    private final String desc;

    EducationField(String desc) {
        this.desc = desc;
    }
}
