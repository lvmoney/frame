package com.lvmoney.oauth2.server.ro;

import com.lvmoney.oauth2.server.config.FrameGrantedAuthority;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvmoney on 2019/1/18.
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserRo implements Serializable {
    private static final long serialVersionUID = -6838412339122016007L;
    private String username;
    private String password;
    private List<FrameGrantedAuthority> frameGrantedAuthorities;
}
