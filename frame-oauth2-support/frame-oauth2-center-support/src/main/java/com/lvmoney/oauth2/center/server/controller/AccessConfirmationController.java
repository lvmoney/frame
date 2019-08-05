package com.lvmoney.oauth2.center.server.controller;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.ScopeDefinitionService;
import com.lvmoney.oauth2.center.server.vo.OauthClient;
import com.lvmoney.oauth2.center.server.vo.ScopeDefinition;
import com.lvmoney.oauth2.center.server.service.OauthClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {

    @Autowired
    OauthClientService oauthClientService;

    @Autowired
    ScopeDefinitionService scopeDefinitionService;


    @RequestMapping("/confirm_access")
    public String getAccessConfirmation(@ModelAttribute AuthorizationRequest clientAuth,
                                        ModelMap model,
                                        @RequestParam(value = "redirect_uri", required = false) String redirectUri) {
        OauthClient client = oauthClientService.findByClientId(clientAuth.getClientId());
        model.put("auth_request", clientAuth);
        model.put("applicationName", client.getApplicationName());
        if (StringUtils.isNotEmpty(redirectUri)) {
            model.put("from", getHost(redirectUri));
        }
        Map<String, String> scopes = new LinkedHashMap<>();
        for (String scope : clientAuth.getScope()) {
            ScopeDefinition scopeDefinition = scopeDefinitionService.findByScope(scope);
            if (scopeDefinition != null) {
                scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, scopeDefinition.getDefinition());
            } else {
                scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, scope);
            }
        }
        model.put("scopes", scopes);
        return "accessConfirmation";
    }

    /*@RequestMapping("/confirm_access")
    public ModelAndView getAccessConfirmation(@ModelAttribute AuthorizationRequest clientAuth) throws Exception {
        ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
        TreeMap<String, Object> model = new TreeMap<>();
        model.put("auth_request", clientAuth);
        model.put("client", client);
        ModelAndView a= new ModelAndView("accessConfirmation", model);
        return a;
    }*/

    @RequestMapping("/error")
    public void handleError(Map<String, Object> model, HttpServletRequest request) {
        throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_SERVER_ERROR);
    }

    private URI getHost(String url) {
        URI uri = URI.create(url);
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }
}
