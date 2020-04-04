package info.sanaulla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class OAuth2Controller {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @RequestMapping("/hello")
    public String userinfo(OAuth2AuthenticationToken authentication) {
        // authentication.getAuthorizedClientRegistrationId() returns the
        // registrationId of the Client that was authorized during the Login flow
        OAuth2AuthorizedClient authorizedClient =
            this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("authentication: " + authentication);
        System.out.println();
        System.out.println("attributes: " + authentication.getPrincipal().getAttributes());
        System.out.println();
        System.out.println("access_token: " + accessToken.getTokenValue());
        return "hello";
    }

    /*@RequestMapping("/hello")
    public String hello(Principal principal) {
        System.out.println(principal.getName());
        System.out.println(principal.toString());
        return "hello";
    }*/

    /*@RequestMapping("/login/oauth2/code/cognito")
    public String redirect() {
        return "redirect:hello";
    }*/
}