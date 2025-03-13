package org.dogra.stockflow.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dogra.stockflow.config.User.CatUserDetails;
import org.dogra.stockflow.config.User.CatUserDetailsService;
import org.dogra.stockflow.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationContext applicationContext;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtTokens = extractTokenFromRequest(request);
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(jwtTokens != null && auth == null){

                String username = jwtUtil.extractClaims(jwtTokens).getSubject();

               CatUserDetails userDetails =
                       applicationContext.getBean(CatUserDetailsService.class).loadUserByUsername(username);

               if(jwtUtil.validateTokens(jwtTokens, userDetails)){
                   UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                           userDetails, null,
                           userDetails.getAuthorities()
                   );

                   upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(upat);
               }
            }

        }catch (Exception e){
            logger.error("Jwt Security Filter failure -- "  + e.getMessage());
        }finally {
            filterChain.doFilter(request, response);
        }

    }


    public String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer"))
            return authHeader.substring(7);

        return null;
    }


}
