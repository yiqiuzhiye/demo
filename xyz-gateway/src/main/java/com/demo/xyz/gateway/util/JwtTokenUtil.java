package com.demo.xyz.gateway.util;

import com.demo.xyz.common.constant.RedisKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;
    // 过期时间 毫秒
    @Value("${jwt.expiration}")
    private Long expiration;
    // 刷新token过期时间 毫秒
    @Value("${jwt.expiration}")
    private Long refreshExpiration;
    @Value("${jwt.header}")
    private String header;

    @Resource
    private RedisTemplate redisTemplate;
 
    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims, Long time) {
        Date expirationDate = new Date(System.currentTimeMillis() + time);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
 
    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
 
    /**
     * 生成令牌
     *
     * @param username 用户
     * @return 令牌
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims, expiration);
    }

    /**
     * 生成刷新令牌
     *
     * @return 令牌
     */
    public String generateRefreshToken() {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims, refreshExpiration);
    }
 
    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUserFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
 
    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            if(StringUtils.isBlank(token)){
                return true;
            }
            String username = getUserFromToken(token);
            String cacheToken =(String) redisTemplate.opsForValue().get(RedisKey.LOGIN_USER_TOKEN_CACHE_KEY + username);
            if(!token.equals(cacheToken)){
                return true;
            }
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
 
    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims, expiration);
            String username = getUserFromToken(token);
            redisTemplate.opsForValue().set(RedisKey.LOGIN_USER_TOKEN_CACHE_KEY + username, refreshedToken);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
}