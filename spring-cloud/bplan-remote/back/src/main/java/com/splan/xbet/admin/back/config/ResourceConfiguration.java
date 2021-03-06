//package com.splan.xbet.admin.back.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableOAuth2Client
//public class ResourceConfiguration {
//
//    @Bean
//    public OAuth2ProtectedResourceDetails hello() {
//        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
//        details.setId("hello");
//        details.setClientId("client_1");
//        details.setClientSecret("123456");
//        details.setAccessTokenUri("http://localhost:9311/oauth/token");//认证服务器地址+/oauth/token
//        details.setUserAuthorizationUri("http://localhost:9311/oauth/authorize");//认证服务器地址+/oauth/authorize
//        details.setScope(Arrays.asList("read", "write"));
//        details.setGrantType("client_credentials");
////        details.setPreEstablishedRedirectUri("http://baidu.com");
//        return details;
//    }
//
//    @Bean
//    public OAuth2RestTemplate helloRestTemplate(OAuth2ClientContext oauth2Context) {//客户端的信息被封装到OAuth2RestTemplate用于请求资源
//        return new OAuth2RestTemplate(hello(), oauth2Context);
//    }
//
//}
