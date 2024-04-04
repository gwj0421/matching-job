package kimchisoup.matchingjob.error;

public class EmptySuccessfulResumeException extends RuntimeException {
    public EmptySuccessfulResumeException(String message) {
        super(message);
    }
}
