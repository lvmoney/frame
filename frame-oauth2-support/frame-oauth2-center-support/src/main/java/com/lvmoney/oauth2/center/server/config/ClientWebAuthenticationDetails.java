package com.lvmoney.oauth2.center.server.config;

import com.lvmoney.oauth2.center.server.constant.Oauth2ServerConstant;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class ClientWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String inputVerificationCode;
    private final String graphId;

    public ClientWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        inputVerificationCode = request.getParameter(Oauth2ServerConstant.VERIFICATION_CODE);
        graphId = request.getParameter(Oauth2ServerConstant.GRAPH_ID);
    }

    public String getInputVerificationCode() {
        return inputVerificationCode;
    }

    public String getGraphId() {
        return graphId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; inputVerificationCode: ").append(this.getInputVerificationCode())
                .append("; graphId: ").append(this.getGraphId());
        return sb.toString();
    }
}
