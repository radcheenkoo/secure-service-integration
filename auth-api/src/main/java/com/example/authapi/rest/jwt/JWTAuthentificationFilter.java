package com.example.authapi.rest.jwt;

import com.example.authapi.data.services.UserService;
import com.example.authapi.rest.jwt.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JWTAuthentificationFilter extends OncePerRequestFilter {

    public static final String AUTHENTICATION_SCHEME = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserService userService;
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        var authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.isBlank(authHeader)
                || !StringUtils.startsWith(authHeader, AUTHENTICATION_SCHEME)) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader.substring(AUTHENTICATION_SCHEME.length());

        try {
            var username = jwtService.extractUsernameFromToken(token);

            if (StringUtils.isNotBlank(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userService
                        .getUserDetailsService()
                        .loadUserByUsername(username);

                if (jwtService.isTokenValid(token, userDetails)) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                }
            }
        } catch (io.jsonwebtoken.JwtException | IllegalArgumentException ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
