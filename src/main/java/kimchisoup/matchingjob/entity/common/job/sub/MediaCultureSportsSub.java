package kimchisoup.matchingjob.entity.common.job.sub;

public enum MediaCultureSportsSub {
    PD_DIRECTOR("PD·감독"),
    PHOTOGRAPHER("포토그래퍼"),
    VIDEO_EDITOR("영상편집자"),
    SOUND_ENGINEER("사운드엔지니어"),
    STAFF("스태프"),
    PUBLISHING_EDITING("출판·편집"),
    DISTRIBUTION_PRODUCER("배급·제작자"),
    CONTENTS_EDITOR("콘텐츠에디터"),
    CREATOR("크리에이터"),
    JOURNALIST("기자"),
    WRITER("작가"),
    ANNOUNCER("아나운서"),
    REPORTER_VOICE_ACTOR("리포터·성우"),
    MC_SHOW_HOST("MC·쇼호스트"),
    MODEL("모델"),
    ENTERTAINER_MANAGER("연예인·매니저"),
    INFLUENCER("인플루언서"),
    INTERPRETER_TRANSLATOR("통번역사"),
    CURATOR("큐레이터"),
    RECORD_PLANNER("음반기획"),
    SPORTS_INSTRUCTOR("스포츠강사"),
    NONE("None");

    private final String name;

    MediaCultureSportsSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
