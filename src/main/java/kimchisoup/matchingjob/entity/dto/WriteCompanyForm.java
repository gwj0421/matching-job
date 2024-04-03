package kimchisoup.matchingjob.entity.dto;

import kimchisoup.matchingjob.entity.common.Industry;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class WriteCompanyForm {
    private String name;
    private int peopleCnt;
    private BigDecimal sales;
    private String address;
    private Industry industry;
    private String introduction;

    @Builder
    public WriteCompanyForm(String name, int peopleCnt, BigDecimal sales, String address, Industry industry, String introduction) {
        this.name = name;
        this.peopleCnt = peopleCnt;
        this.sales = sales;
        this.address = address;
        this.industry = industry;
        this.introduction = introduction;
    }
}
