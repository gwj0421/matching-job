package kimchisoup.matchingjob.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cool-sms")
@RequiredArgsConstructor
@Getter
public class CoolSMSProperty {
    private final String url;
    private final String apiKey;
    private final String secretKey;
    private final String senderNumber;
}
