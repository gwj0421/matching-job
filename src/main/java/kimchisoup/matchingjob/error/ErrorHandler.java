package kimchisoup.matchingjob.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({EmptySuccessfulResumeException.class, ProfileImageException.class})
    public ResponseEntity<Void> handler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
