package kimchisoup.matchingjob.entity.dao;

import jakarta.persistence.*;
import kimchisoup.matchingjob.entity.common.BaseTime;
import kimchisoup.matchingjob.entity.common.Industry;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int peopleCnt;
    private BigDecimal sales;
    private String address;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private String introduction;

    @OneToMany(mappedBy = "company")
    private List<Proposal> proposals = new ArrayList<>();
    @OneToMany(mappedBy = "company")
    private List<SuccessfulResume> successfulResumes = new ArrayList<>();

    @Builder
    public Company(String name, int peopleCnt, BigDecimal sales, String address, Industry industry, String introduction) {
        this.name = name;
        this.peopleCnt = peopleCnt;
        this.sales = sales;
        this.address = address;
        this.industry = industry;
        this.introduction = introduction;
    }

    public void changePeopleCnt(int peopleCnt) {
        this.peopleCnt = peopleCnt;
    }
}
