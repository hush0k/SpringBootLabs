package kz.kbtu.tsis2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.WatchEvent;

@Configuration
public class MLBBClientConfig {

    @Bean
    public WebClient mlbbWebClient(
            WebClient.Builder builder,
            @Value("${mlbb.base-url}") String baseUrl
    ) {
        int size = 20 * 1024 * 1024;

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(cfg -> cfg.defaultCodecs().maxInMemorySize(size))
                .build();

        return builder
                .baseUrl(baseUrl)
                .exchangeStrategies(strategies)
                .build();
    }
}
