package kimchisoup.matchingjob.entity.common.job.sub;

public enum AccountingTaxSub {
    ACCOUNTANT("회계담당자"),
    ACCOUNTING("경리"),
    TAX_ACCOUNTANT("세무담당자"),
    FINANCIAL_MANAGER("재무담당자"),
    AUDITOR("감사"),
    IR_DISCLOSURE("IR·공시"),
    CERTIFIED_ACCOUNTANT("회계사"),
    TAX_ADVISOR("세무사"),
    CUSTOMS_BROKER("관세사"),
    NONE("None");

    private final String name;

    AccountingTaxSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
