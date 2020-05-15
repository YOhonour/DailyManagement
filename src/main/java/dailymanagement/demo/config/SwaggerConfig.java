package dailymanagement.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author MaYunHao
 * @version 1.0
 * @description
 * @date 2020/2/8 10:49
 */

@Configuration
@EnableSwagger2//开启swagger2
public class SwaggerConfig {
    //配置swagger
    @Bean
    public Docket docket(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("main")
                .enable(true);
    }
    //配置swagger-ui页面上的信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("itrover", "www.itrover.cn", "1172610139@qq.com");
        return new ApiInfo("我的Swaggger", "用于测试","1.0",null,contact,"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",new ArrayList());
    }

}