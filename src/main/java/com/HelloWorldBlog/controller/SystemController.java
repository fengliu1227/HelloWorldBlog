package com.HelloWorldBlog.controller;

import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SystemController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    protected AuthenticationManager authenticationManager;

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String addNewUser(Model model){
        model.addAttribute("userInfo", new UserInfo());
        return "login";
    }
    @RequestMapping(value="register", method= RequestMethod.POST)
    public String register(UserInfo userInfo, HttpServletRequest request){
        String password = userInfo.getPassword();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setStatus(0);
        userInfo.setRole("USER");
        userInfoService.addUser(userInfo);

        // automatically login after registered successfully
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), password);
        try{
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authenticatedUser = authenticationManager
                    .authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        }
        catch( AuthenticationException e ){
            System.out.println("Authentication failed: " + e.getMessage());
            return "redirect:/register";
        }

        return "redirect:/blog";

    }
}




