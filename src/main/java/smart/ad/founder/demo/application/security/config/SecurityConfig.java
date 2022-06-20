package smart.ad.founder.demo.application.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import smart.ad.founder.demo.application.security.AudienceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")       // * Gets value from application.properties
    private String issuer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/users/**",
                        "/api/userInterests/**",
                        "/api/userAdverts/**",
                        "/api/foundAdverts/**",
                        "/api/kafkaMessages/**",

                        "/api/kafka-chat/**",
                        "/kafka-chat/**",
                        "/kafka-chat/info",
                        "/kafka/**").permitAll()        // *  GET requests don't need auth

//                .mvcMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .oauth2ResourceServer()
                .jwt()
                .decoder(jwtDecoder());



        // * Bez cors
//        http.authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/api/users/**",
//                        "/api/userInterests/**",
//                        "/api/userAdverts/**",
//                        "/api/foundAdverts/**",
//                        "/api/kafkaMessages/**",
//
//                        "/api/kafka-chat/**",
//                        "/kafka-chat/**",
//                        "/kafka-chat/info",
//                        "/kafka/**").permitAll()        // *  GET requests don't need auth
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .decoder(jwtDecoder());
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PATCH.name()
        ));


//        configuration.setAllowedOriginPatterns(List.of(
//                "http://localhost:3000/*"
//        ));



        ArrayList<String> dozvoleniOrigins = new ArrayList<>();

        dozvoleniOrigins.add("http://localhost:3000");
        configuration.setAllowedOrigins(dozvoleniOrigins);

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());


        // * Printa dozvoleni origins
        configuration.getAllowedOrigins().stream().collect(Collectors.toList()).forEach(orgn -> {
            System.out.println(orgn);
        });

        return source;
    }

    JwtDecoder jwtDecoder() {
        OAuth2TokenValidator<Jwt> withAudience = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);

        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
        jwtDecoder.setJwtValidator(validator);
        return jwtDecoder;
    }
}
