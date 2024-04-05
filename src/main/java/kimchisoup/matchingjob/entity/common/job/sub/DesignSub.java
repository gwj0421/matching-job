package kimchisoup.matchingjob.entity.common.job.sub;

public enum DesignSub {
    GRAPHIC_DESIGNER("그래픽디자이너"),
    THREE_D_DESIGNER("3D디자이너"),
    PRODUCT_DESIGNER("제품디자이너"),
    INDUSTRIAL_DESIGNER("산업디자이너"),
    ADVERTISEMENT_DESIGNER("광고디자이너"),
    VISUAL_DESIGNER("시각디자이너"),
    VIDEO_DESIGNER("영상디자이너"),
    WEB_DESIGNER("웹디자이너"),
    UI_UX_DESIGNER("UI·UX디자이너"),
    FASHION_DESIGNER("패션디자이너"),
    EDITORIAL_DESIGNER("편집디자이너"),
    INTERIOR_DESIGNER("실내디자이너"),
    SPACE_DESIGNER("공간디자이너"),
    CHARACTER_DESIGNER("캐릭터디자이너"),
    ENVIRONMENTAL_DESIGNER("환경디자이너"),
    ART_DIRECTOR("아트디렉터"),
    ILLUSTRATOR("일러스트레이터"),
    NONE("None");

    private final String name;

    DesignSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
