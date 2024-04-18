package com.dali.security.Service;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.Primary;
        import org.springframework.http.converter.*;
        import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
        import org.springframework.web.multipart.MultipartResolver;
        import org.springframework.web.multipart.support.StandardServletMultipartResolver;
        import org.springframework.web.servlet.config.annotation.CorsRegistry;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

        import java.util.List;

@Configuration
@EnableWebMvc
public class config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }


    @Bean
    public IServiseDemande serviseDemande(DemandeServise demandeServise) {
        return demandeServise;
    }

    @Bean
    @Primary
    public IServiceOffreS renamedServiseOffreS(ServiseOffreS serviseOffreS) {
        return serviseOffreS;
    }


    @Bean
    public ITechS renamedserviseTech (ServiseTech serviseTech) {
        return serviseTech;
    }

}


