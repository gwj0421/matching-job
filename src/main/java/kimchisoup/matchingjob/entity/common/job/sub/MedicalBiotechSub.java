package kimchisoup.matchingjob.entity.common.job.sub;

public enum MedicalBiotechSub {
    DOCTOR("의사"),
    ORIENTAL_MEDICINE_DOCTOR("한의사"),
    NURSE("간호사"),
    NURSING_ASSISTANT("간호조무사"),
    PHARMACIST("약사·한약사"),
    MEDICAL_TECHNICIAN("의료기사"),
    VETERINARIAN("수의사"),
    VETERINARY_TECHNICIAN("수의테크니션"),
    HOSPITAL_COORDINATOR("병원코디네이터"),
    MEDICAL_ADMINISTRATION("원무행정"),
    OTHER_MEDICAL_PROFESSIONAL("기타의료종사자"),
    MEDICAL_SUPPORT("의료·약무보조"),
    BIOSCIENCE_PHARMACEUTICAL_RESEARCHER("바이오·제약연구원"),
    CLINICAL_RESEARCHER("임상연구원"),
    NONE("None");

    private final String name;

    MedicalBiotechSub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
