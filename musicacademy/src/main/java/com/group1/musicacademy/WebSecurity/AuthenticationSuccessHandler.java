//package com.group1.musicacademy.WebSecurity;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//
//import java.io.IOException;
//
//public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//        boolean isAdmin = authentication.getAuthorities().stream()
//                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
//        boolean isTeacher = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("TEACHER"));
//        boolean isStudent = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("STUDENT"));
//        if(isAdmin){
//            setDefaultTargetUrl("/admin");
//        }
//        else if(isTeacher) {
//            setDefaultTargetUrl("/teacher");
//        }
//        else if(isStudent) {
//            setDefaultTargetUrl("/lesson");
//        }
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}
