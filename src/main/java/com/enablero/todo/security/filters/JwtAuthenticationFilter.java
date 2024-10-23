package com.enablero.todo.security.filters;

import com.enablero.todo.dal.dataprovider.impl.UserDataProviderImpl;
import com.enablero.todo.dal.entity.User;
import com.enablero.todo.dal.repository.impl.UserRepositoryImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@WebFilter
@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private UserDataProviderImpl userDataProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI();

        if (path.equals("/graphiql")) {
            filterChain.doFilter(req, res);
            return;
        }


        try {

            String authorizationHeader = request.getHeader("Authorization");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authorizationHeader.startsWith("Bearer ")) {

                HttpServletResponse response = (HttpServletResponse) res;
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Authentication is null\", \"statusCode\": 401}");
                response.getWriter().flush();
                return;

            }

            Jwt jwt = (Jwt) authentication.getPrincipal();

            String email = jwt.getClaim("email");

            User user = userDataProvider.getUserByEmail(email);

            if (user == null) {

                HttpServletResponse response = (HttpServletResponse) res;

                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"User not found with the provided email\", \"statusCode\": 404}");
                response.getWriter().flush();
                return;

            }
            else {
                request.setAttribute("userEmail", user.getEmail());
                JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                jwtAuthenticationToken.setDetails(user);
            }

            System.out.println("============================"+email);

            filterChain.doFilter(req, res);

        } catch (Exception e) {

            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"An error occurred during authentication: " + e.getMessage() + "\", \"statusCode\": 500}");
            response.getWriter().flush();

            throw new RuntimeException(e);
        }

    }
}
