/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Klaus
 */
@RestController
public class UserResource {

  @Autowired
  private UserService userService;

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

  @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
  public void changePassword(@RequestBody Map<String, String> body) {
    userService.changePassword(body.get("newPassword"));    
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    userService.logout(request, response);
  }
}
