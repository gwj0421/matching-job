package kimchisoup.matchingjob.entity.common;

import lombok.Getter;

@Getter
public enum Authority {
    ADMIN("ROLE_ADMIN","관리자"),
    HEAD_HUNTER("ROLE_HEAD_HUNTER","헤드헌트"),
    JOB_SEEKER("ROLE_JOB_SEEKER","구직자");
    private String role;
    private String desc;

    Authority(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }
}
