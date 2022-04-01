package com.example.qsgruppe12.config;

import com.example.qsgruppe12.service.user.UserServiceDetails;
import com.example.qsgruppe12.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO skriv utfyllende her
 */
@Component
public class JWTConfig extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserServiceDetails userServiceDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String auth = httpServletRequest.getHeader("Authorization");
        String token = null;
        String email = null;

        if(null != auth && auth.startsWith("Bearer ")) {
            token = auth.substring(7);
            email = jwtUtil.getEmailFromToken(token);
        }

        if(null != email && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = userServiceDetails.loadUserByUsername(email);

            if(jwtUtil.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
