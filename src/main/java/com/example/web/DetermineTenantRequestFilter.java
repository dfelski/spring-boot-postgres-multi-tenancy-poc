package com.example.web;

import com.example.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
class DetermineTenantRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tenant = request.getHeader("tenant");
        if(Objects.isNull(tenant)){
            throw new IllegalArgumentException("tenant must be present");
        }
        TenantContext.setTenant(tenant);
        filterChain.doFilter(request, response);
        TenantContext.clear();
    }
}
