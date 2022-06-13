package com.pabloriosramirez.api.rickandmorty.proxy;

import com.pabloriosramirez.api.rickandmorty.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InteractionContextInitializationFilterProxy extends OncePerRequestFilter implements ApplicationContextAware {

    private final static Logger LOGGER = LoggerFactory.getLogger(InteractionContextInitializationFilterProxy.class);
    public final static String INTERACTION_CONTEXT_LOCATOR_BEAN_NAME_FILTER_PARAM = "interactionContextLocatorBeanName";
    private ApplicationContext applicationContext;
    private boolean errorResponseREST = true;
    private String interactionContextLocatorBeanName;
    private InteractionContextLocator  interactionContextLocator;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
