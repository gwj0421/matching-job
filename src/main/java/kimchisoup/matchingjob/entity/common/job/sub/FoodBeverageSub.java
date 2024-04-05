package kimchisoup.matchingjob.entity.common.job.sub;

public enum FoodBeverageSub {
    CHEF("요리사"),
    COOK("조리사"),
    BAKER("제과제빵사"),
    BARISTA("바리스타"),
    CHEF_MANAGER("셰프·주방장"),
    CAFE_RESTAURANT_MANAGER("카페·레스토랑매니저"),
    SERVER("홀서버"),
    KITCHEN_ASSISTANT("주방보조"),
    SOMMELIER_BARTENDER("소믈리에·바텐더"),
    NUTRITIONIST("영양사"),
    FOOD_RESEARCHER("식품연구원"),
    FOOD_STYLIST("푸드스타일리스트"),
    NONE("None");

    private final String name;

    FoodBeverageSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
