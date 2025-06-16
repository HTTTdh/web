package com.HTTTdh.project1.security.jwt;

import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
public class AuthTokenFilter extends OncePerRequestFilter {
  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsService;

  public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
    this.jwtUtils = jwtUtils;
    this.userDetailsService = userDetailsService;
  }

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    System.out.println("AuthTokenFilter đang chạy");

    String headerAuth = request.getHeader("Authorization");
    System.out.println("Header Authorization: " + headerAuth);

    try {
      String jwt = parseJwt(request);
      System.out.println("Token lấy được: " + jwt);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        System.out.println("Username lấy từ token: " + username);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("UserDetails: " + userDetails);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Đã set Authentication vào SecurityContextHolder");
      } else {
        System.out.println("Token không hợp lệ hoặc null");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    filterChain.doFilter(request, response);
  }


  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }

    return null;
  }

//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//      throws ServletException, IOException {
//    System.out.println("AuthTokenFilter đang chạy");
//
//    try {
//      String jwt = parseJwt(request);
//      System.out.println("Token lấy được: " + jwt);
//
//      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//        String username = jwtUtils.getUserNameFromJwtToken(jwt);
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        System.out.println("UserDetails: " + userDetails);
//
//        UsernamePasswordAuthenticationToken authentication =
//            new UsernamePasswordAuthenticationToken(
//                userDetails,
//                null,
//                userDetails.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//      }
//    } catch (Exception e) {
//      logger.error("Cannot set user authentication: {}", e);
//    }
//
//    filterChain.doFilter(request, response);
//  }
//
//  private String parseJwt(HttpServletRequest request) {
//    String headerAuth = request.getHeader("Authorization");
//
//    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//      return headerAuth.substring(7);
//    }
//
//    return null;
//  }
}
