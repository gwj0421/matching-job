# Matching Job

Matching Job은 기업과 취업자 간의 연결 서비스를 제공하는 플랫폼입니다. 취업자는 자신의 프로필을 작성하고 이력서를 등록하여 관심 기업과의 매칭을 기다릴 수 있습니다. 기업은 자신의 채용 정보를 등록하고 필요한 인재를 찾을 수 있습니다.

## 주요 기능

- 취업자 프로필 등록
- 이력서 등록 및 관리
- 기업 채용 정보 등록
- 채용 정보 검색 및 지원
- 매칭 알림 기능

## 기술 스택

- Frontend: React.js
- Backend: Spring Boot
- 데이터베이스: H2
- 기타: Docker

## 프로젝트 구조

matching-job/
├── frontend/         # 프론트엔드 소스코드
│   ├── public/
│   └── src/
└── backend/          # 백엔드 소스코드
├── src/
└── Dockerfile

## 설치 및 실행
1. 프론트엔드 설치 및 실행
~~~bash
Copy code
cd frontend
npm install
npm start
~~~

2. 백엔드 설치 및 실행
~~~bash
Copy code
cd backend
./mvnw spring-boot:run
~~~

## 기여 방법
1. 이 저장소를 포크합니다.
2. 기능 추가나 버그 수정을 위한 브랜치를 생성합니다. (git checkout -b feature/add-new-feature)
3. 변경 사항을 커밋합니다. (git commit -am 'Add new feature')
4. 변경된 내용을 원본 저장소에 푸시합니다. (git push origin feature/add-new-feature)
5. Pull Request를 생성하여 변경 사항을 리뷰하고 병합합니다.

## 라이센스
(이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 LICENSE 파일을 참조하세요.)
