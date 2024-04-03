package kimchisoup.matchingjob.entity.common;

public enum InterestFieldType {
    PLANNING_STRATEGY("기획, 전략"),
    LEGAL_ADMINISTRATIVE("법무, 사무, 총무"),
    HUMAN_RESOURCES("인사, HR"),
    ACCOUNTING_TAXATION("회계, 세무"),
    MARKETING_ADVERTISING("마케팅, 광고, MD"),
    DEVELOPMENT_DATA("개발, 데이터"),
    DESIGN("디자인"),
    LOGISTICS_TRADE("물류, 무역"),
    DRIVING_TRANSPORTATION("운전, 운송, 배송"),
    SALES("영업"),
    CUSTOMER_SERVICE_TM("고객상담, TM"),
    FINANCE_INSURANCE("금융, 보험"),
    FOOD_BEVERAGE("식, 음료"),
    CUSTOMER_SERVICE_RETAIL("고객서비스, 리테일"),
    ENGINEERING_DESIGN("엔지니어링, 설계"),
    MANUFACTURING_PRODUCTION("제조, 생산"),
    EDUCATION("교육"),
    CONSTRUCTION_FACILITIES("건축, 시설"),
    MEDICAL_BIO("의료, 바이오"),
    MEDIA_CULTURE_SPORTS("미디어, 문화, 스포츠"),
    PUBLIC_WELFARE("공공, 복지"),
    NONE("None interest field type");

    private final String displayName;

    InterestFieldType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}