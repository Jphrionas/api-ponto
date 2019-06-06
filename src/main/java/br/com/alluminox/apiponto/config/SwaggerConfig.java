package br.com.alluminox.apiponto.config;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String TERMS_AND_SERVICES = 
			"<h1>Terms of Service (\"Terms\")</h1>\n" + 
			"\n" + 
			"\n" + 
			"<p>Last updated: June 06, 2019</p>\n" + 
			"\n" + 
			"\n" + 
			"<p>Please read these Terms of Service (\"Terms\", \"Terms of Service\") carefully before using the http://alluminox.com.br website (the \"Service\") operated by Alluminox (\"us\", \"we\", or \"our\").</p>\n" + 
			"\n" + 
			"<p>Your access to and use of the Service is conditioned on your acceptance of and compliance with these Terms. These Terms apply to all visitors, users and others who access or use the Service.</p>\n" + 
			"\n" + 
			"<p>By accessing or using the Service you agree to be bound by these Terms. If you disagree with any part of the terms then you may not access the Service. The Terms of Service agreement  for Alluminox has been created with the help of <a href=\"https://www.termsfeed.com/\">TermsFeed</a>.</p>\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"<h2>Links To Other Web Sites</h2>\n" + 
			"\n" + 
			"<p>Our Service may contain links to third-party web sites or services that are not owned or controlled by Alluminox.</p>\n" + 
			"\n" + 
			"<p>Alluminox has no control over, and assumes no responsibility for, the content, privacy policies, or practices of any third party web sites or services. You further acknowledge and agree that Alluminox shall not be responsible or liable, directly or indirectly, for any damage or loss caused or alleged to be caused by or in connection with use of or reliance on any such content, goods or services available on or through any such web sites or services.</p>\n" + 
			"\n" + 
			"<p>We strongly advise you to read the terms and conditions and privacy policies of any third-party web sites or services that you visit.</p>\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"<h2>Governing Law</h2>\n" + 
			"\n" + 
			"<p>These Terms shall be governed and construed in accordance with the laws of Congo, without regard to its conflict of law provisions.</p>\n" + 
			"\n" + 
			"<p>Our failure to enforce any right or provision of these Terms will not be considered a waiver of those rights. If any provision of these Terms is held to be invalid or unenforceable by a court, the remaining provisions of these Terms will remain in effect. These Terms constitute the entire agreement between us regarding our Service, and supersede and replace any prior agreements we might have between us regarding the Service.</p>\n" + 
			"\n" + 
			"\n" + 
			"<h2>Changes</h2>\n" + 
			"\n" + 
			"<p>We reserve the right, at our sole discretion, to modify or replace these Terms at any time. If a revision is material we will try to provide at least 30 days notice prior to any new terms taking effect. What constitutes a material change will be determined at our sole discretion.</p>\n" + 
			"\n" + 
			"<p>By continuing to access or use our Service after those revisions become effective, you agree to be bound by the revised terms. If you do not agree to the new terms, please stop using the Service.</p>\n" + 
			"\n" + 
			"\n" + 
			"<h2>Contact Us</h2>\n" + 
			"\n" + 
			"<p>If you have any questions about these Terms, please contact us.</p>";
	
	public static final String TERMS_AND_SERVICES_TEXT = 
			"Terms of Service (\"Terms\")\n" + 
			"Last updated: June 06, 2019\n" + 
			"\n" + 
			"Please read these Terms of Service (\"Terms\", \"Terms of Service\") carefully before using the http://alluminox.com.br website (the \"Service\") operated by Alluminox (\"us\", \"we\", or \"our\").\n" + 
			"\n" + 
			"Your access to and use of the Service is conditioned on your acceptance of and compliance with these Terms. These Terms apply to all visitors, users and others who access or use the Service.\n" + 
			"\n" + 
			"By accessing or using the Service you agree to be bound by these Terms. If you disagree with any part of the terms then you may not access the Service. The Terms of Service agreement for Alluminox has been created with the help of TermsFeed.\n" + 
			"\n" + 
			"Links To Other Web Sites\n" + 
			"Our Service may contain links to third-party web sites or services that are not owned or controlled by Alluminox.\n" + 
			"\n" + 
			"Alluminox has no control over, and assumes no responsibility for, the content, privacy policies, or practices of any third party web sites or services. You further acknowledge and agree that Alluminox shall not be responsible or liable, directly or indirectly, for any damage or loss caused or alleged to be caused by or in connection with use of or reliance on any such content, goods or services available on or through any such web sites or services.\n" + 
			"\n" + 
			"We strongly advise you to read the terms and conditions and privacy policies of any third-party web sites or services that you visit.\n" + 
			"\n" + 
			"Governing Law\n" + 
			"These Terms shall be governed and construed in accordance with the laws of Congo, without regard to its conflict of law provisions.\n" + 
			"\n" + 
			"Our failure to enforce any right or provision of these Terms will not be considered a waiver of those rights. If any provision of these Terms is held to be invalid or unenforceable by a court, the remaining provisions of these Terms will remain in effect. These Terms constitute the entire agreement between us regarding our Service, and supersede and replace any prior agreements we might have between us regarding the Service.\n" + 
			"\n" + 
			"Changes\n" + 
			"We reserve the right, at our sole discretion, to modify or replace these Terms at any time. If a revision is material we will try to provide at least 30 days notice prior to any new terms taking effect. What constitutes a material change will be determined at our sole discretion.\n" + 
			"\n" + 
			"By continuing to access or use our Service after those revisions become effective, you agree to be bound by the revised terms. If you do not agree to the new terms, please stop using the Service.\n" + 
			"\n" + 
			"Contact Us\n" + 
			"If you have any questions about these Terms, please contact us.";
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select().apis(RequestHandlerSelectors.basePackage("br.com.alluminox.apiponto"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(info());
	}
	
	
	public ApiInfo info() {
		return new ApiInfoBuilder()
			.contact(new Contact("Alluminox Devellopers", "https://alluminox.com.br", "desenvolvimento@alluminox.com.br"))
			.description("Api Restfull para controle de pontos da empresa")
			.license("Apache License, Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.txt")
			.title("Documentação da Api de Pontos")
			.version("0.0.1-SNAPSHOT")
			.termsOfServiceUrl(TERMS_AND_SERVICES_TEXT)
			.build();
	}
	
}
