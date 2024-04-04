package kimchisoup.matchingjob.entity.common.job.sub;

public enum FinanceInsuranceSub {
    FINANCE_CLERK("금융사무"),
    INSURANCE_PLANNER("보험설계사"),
    LOSS_ADJUSTER("손해사정사"),
    UNDERWRITER("심사"),
    BANK_TELLER("은행원·텔러"),
    ACCOUNTANT("계리사"),
    FUND_MANAGER("펀드매니저"),
    ANALYST("애널리스트"),
    NONE("None");

    private final String name;

    FinanceInsuranceSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
