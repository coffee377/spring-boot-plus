///*
// * Copyright 2020-2021 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.voc.security.oauth2;
//
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author Joe Grandja
// * @since 0.0.1
// */
//@Configuration(proxyBeanMethods = false)
//public class AuthorizationServerConfiguration {
//
////	@Bean
////	@Order(Ordered.HIGHEST_PRECEDENCE)
////	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
////		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
////		return http.formLogin(Customizer.withDefaults()).build();
////	}
//
////	@Bean
////    @ConditionalOnMissingBean
////	public JWKSource<SecurityContext> jwkSource() {
////		RSAKey rsaKey = Jwks.generateRsa();
////		JWKSet jwkSet = new JWKSet(rsaKey);
////		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
////	}
//
////	@Bean
////    @ConditionalOnMissingBean
////	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
////		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
////	}
//
////	@Bean
////	public ProviderSettings providerSettings() {
////		ProviderSettings settings = new ProviderSettings().issuer("http://auth-server:9000");
////		return settings;
////	}
//
//}
