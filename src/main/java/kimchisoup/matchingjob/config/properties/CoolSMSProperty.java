package kimchisoup.matchingjob.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cool-sms")
@Getter
public class CoolSMSProperty {
    private String url;
    private String apiKey;
    private String secretKey;
    private String senderNumber;

    public CoolSMSProperty(String url, String apiKey, String secretKey, String senderNumber) {
        this.url = url;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.senderNumber = senderNumber;
    }
}
