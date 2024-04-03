package kimchisoup.matchingjob.entity.dto;

import lombok.Getter;

@Getter
public class SearchBox {
    private String searchingword;

    public SearchBox(String searchingword) {
        this.searchingword = searchingword;
    }
}
