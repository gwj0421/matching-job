package kimchisoup.matchingjob.entity.common;

import lombok.Getter;

@Getter
public enum CareerExperience {
    NONE("경력 무관"),
    FRESH_MAN("신입"),
    VETERAN("경력");

    private final String desc;

    CareerExperience(String desc) {
        this.desc = desc;
    }
}
