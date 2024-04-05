package kimchisoup.matchingjob.entity.common.job.sub;

public enum CustomerServiceRetailSub {
    INSTALLATION_REPAIR_TECHNICIAN("설치·수리기사"),
    MAINTENANCE_TECHNICIAN("정비기사"),
    HOTEL_STAFF("호텔종사자"),
    TRAVEL_AGENT("여행에이전트"),
    STORE_MANAGER("매장관리자"),
    BEAUTICIAN("뷰티·미용사"),
    PET_GROOMER_TRAINER("애견미용·훈련"),
    FRONT_DESK_RECEPTIONIST("안내데스크·리셉셔니스트"),
    SECURITY_GUARD("경호·경비"),
    OPERATIONS_ASSISTANT_MANAGER("운영보조·매니저"),
    EVENT_WEDDING_PLANNER("이벤트·웨딩플래너"),
    PARKING_ATTENDANT("주차·주유원"),
    STYLIST("스타일리스트"),
    FUNERAL_DIRECTOR("장례지도사"),
    HOUSEKEEPER("가사도우미"),
    FLIGHT_ATTENDANT("승무원"),
    FLORIST("플로리스트"),
    NONE("None");

    private final String name;

    CustomerServiceRetailSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
