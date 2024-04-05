package kimchisoup.matchingjob.entity.common.job;

import kimchisoup.matchingjob.entity.common.job.sub.*;
import lombok.Getter;

@Getter
public enum JobCategory {
    Planning_Strategy("기획·전략", PlanningStrategySub.values()),
    Legal_Administration("법무·사무·총무", LegalAdministrationSub.values()),
    HR("인사·HR", HRSub.values()),
    Accounting_Tax("회계·세무", AccountingTaxSub.values()),
    Marketing_Advertising_MD("마케팅·광고·MD", MarketingAdvertisingMDSub.values()),
    Development_Data("개발·데이터",DevelopmentDataSub.values()),
    Design("디자인",DesignSub.values()),
    Logistics_Trade("물류·무역",LogisticsTradeSub.values()),
    Driving_Transportation_Delivery("운전·운송·배송",DrivingTransportationDeliverySub.values()),
    Sales("영업",SalesSub.values()),
    CustomerService_TM("고객상담·TM",CustomerServiceTMSub.values()),
    Finance_Insurance("금융·보험",FinanceInsuranceSub.values()),
    Food_Beverage("식·음료",FoodBeverageSub.values()),
    CustomerService_Retail("고객서비스·리테일",CustomerServiceRetailSub.values()),
    Engineering_Design("엔지니어링·설계",EngineeringDesignSub.values()),
    Manufacturing_Production("제조·생산",ManufacturingProductionSub.values()),
    Education("교육",EducationSub.values()),
    Architecture_Facility("건축·시설",ArchitectureFacilitySub.values()),
    Medical_Biotech("의료·바이오",MedicalBiotechSub.values()),
    Media_Culture_Sports("미디어·문화·스포츠",MediaCultureSportsSub.values()),
    Public_Welfare("공공·복지",PublicWelfareSub.values()),
    NONE("None interest field type",null);

    private final String displayName;
    private final Enum[] subCategory;

    JobCategory(String displayName, Enum[] subCategory) {
        this.displayName = displayName;
        this.subCategory = subCategory;
    }
}