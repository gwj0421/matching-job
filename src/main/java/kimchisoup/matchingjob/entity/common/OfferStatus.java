package kimchisoup.matchingjob.entity.common;

import lombok.Getter;

@Getter
public enum OfferStatus {
    PLAN("예정"),
    RECRUIT("모집중"),
    FINISH("종료");

    private final String desc;

    OfferStatus(String desc) {
        this.desc = desc;
    }
}
