package kimchisoup.matchingjob.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
@RequiredArgsConstructor
@Getter
public class MailProperty {
    private final String protocol;
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String auth;
    private final String starttls;
}
