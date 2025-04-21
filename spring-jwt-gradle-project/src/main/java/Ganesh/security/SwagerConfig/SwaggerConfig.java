package Ganesh.security.SwagerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration 
public class SwaggerConfig {
//    @Bean
//    OpenAPI awesomeAPI() {
//      // custom swagger configurations  
//        return new OpenAPI()
//                .info(new Info().title("JWT-Token Security IMPL")
//                        .description("JWT- information")
//                        .version("1.0"))
//                        .externalDocs(new ExternalDocumentation()
//                        .description("Ganesh, ganesh.repalle949@gmail.com")
//                  );
//   }
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("BearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
            .info(new Info().title("Ganesh's API").version("1.0"));
    }
    
}
