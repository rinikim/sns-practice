package com.fastcampus.sns.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 요청할 때마다 필터를 씌움 (OncePerRequestFilter)
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    /**
     * request 들어올 떄 인증을 수행
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.startsWith("Bearer ")) {
            log.error("Error occurs while getting header. header is null of invalid");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = header.split(" ")[1].trim();
            // TODO check token is valid(expiredAt)

            // TODO get username form token
            String userName = "";
            // TODO : check the userName is valid

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            null, null, null
            );

            // requets context에 넣어서 다시 controller에 보내줌
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RuntimeException e) {
            log.error("Error occurs while validating. {}", e.toString());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
