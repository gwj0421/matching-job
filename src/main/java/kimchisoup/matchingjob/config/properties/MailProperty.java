package kimchisoup.matchingjob.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
@Getter
public class MailProperty {
    private final String protocol;
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String auth;
    private final String starttls;

    public MailProperty(String protocol, String host, int port, String username, String password, String auth, String starttls) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.starttls = starttls;
    }
}
