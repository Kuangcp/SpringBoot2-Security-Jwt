package com.github.kuangcp.graduate.service.security;

import com.github.kuangcp.graduate.util.JSONResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:31
 */

public class TokenAuthenticationService {
    private static final long EXPIRATION_TIME = 432_000_000;     // 5天
    private static final String SIGN_SECRET = "jwt_P@ssw02d";            // JWT sign
    private static final String TOKEN_PREFIX = "CustomPrefix";        // Token前缀
    private static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    public static void addAuthentication(HttpServletResponse response, Authentication auth) {
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        // 生成JWT
        String JWT = Jwts.builder()
                // 权限/角色
                .claim("authorities", authorities)
                // 用户名写入标题
                .setSubject(auth.getName())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SIGN_SECRET)
                .compact();

        // 将 JWT 写入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(JSONResult.fillResultString(0, "", JWT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SIGN_SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            // 拿用户名
            String user = claims.getSubject();

            // 得到 角色/权限
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities)
                    : null;
        }
        return null;
    }
}
