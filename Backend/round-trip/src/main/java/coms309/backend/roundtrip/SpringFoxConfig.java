package coms309.backend.roundtrip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}

//@Configuration
//@EnableSwagger2
//public class SpringFoxConfig {                                    
//	
//	@Bean
//	public Docket myDocket() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("myProject"))
//				.paths(PathSelectors.any())
//				.build();
//	}
//	
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("CalorieCounter")
//				.description("Following is the API documentation!")
//				.termsOfServiceUrl("http://coms-309-056.class.las.iastate.edu:8080/terms")
//				.version("0.0.2")
//				.build();
//	}
//
//}

