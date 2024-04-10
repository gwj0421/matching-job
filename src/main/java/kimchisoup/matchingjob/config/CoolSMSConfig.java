package kimchisoup.matchingjob.config;

import kimchisoup.matchingjob.config.properties.CoolSMSProperty;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoolSMSConfig {
    private CoolSMSProperty coolSMSProperty;

    public CoolSMSConfig(CoolSMSProperty coolSMSProperty) {
        this.coolSMSProperty = coolSMSProperty;
    }

    @Bean
    DefaultMessageService defaultMessageService() {
        return NurigoApp.INSTANCE.initialize(coolSMSProperty.getApiKey(), coolSMSProperty.getSecretKey(), coolSMSProperty.getUrl());
    }
}
