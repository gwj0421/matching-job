package kimchisoup.matchingjob.error;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    WRONG_PHONE_NUMBER("잘못된 전화번호입니다"),
    WRONG_PHONE_NUMBER_OR_CERTIFICATION_CODE("잘못된 전화번호 혹은 인증 코드입니다"),
    WRONG_EMAIL_OR_CERTIFICATION_CODE("잘못된 이메일 혹은 인증 코드입니다"),
    WRONG_SITE_USER_BY_PHONE_NUMBER("해당 번호로 저장된 계정이 없습니다"),
    WRONG_SITE_USER_BY_EMAIL("해당 이메일로 저장된 계정이 없습니다"),
    FAIL_TO_SEND_SMS("송신하지 못했습니다, 관리자에게 문의 바랍니다"),
    DEFAULT_ERROR_MESSAGE("알 수 없는 에러입니다");


    private String desc;

    ErrorMessage(String desc) {
        this.desc = desc;
    }
}
