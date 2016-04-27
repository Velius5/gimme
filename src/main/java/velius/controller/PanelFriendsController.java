/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velius.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import velius.model.Friend;
import velius.model.ModelFriend;
import velius.model.Product;
import velius.model.User;
import velius.service.ProductService;
import velius.service.UserService;

/**
 *
 * @author Piotr
 */


@Controller
@RequestMapping("/panel/friends")
public class PanelFriendsController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping("")
    public String friendsPage(Model model, Principal principal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        
        List<User> friends = userService.getUserFriends(user.getId());
        List<ModelFriend> friendList = new ArrayList();
        for(User friend : friends){
            ModelFriend mf = new ModelFriend();
            mf.setId(friend.getId());
            mf.setName(friend.getName());
            mf.setSurname(friend.getSurname());
            mf.setPhoto(Base64.encodeBase64String(friend.getImage()));
            
            BigDecimal bilans =  BigDecimal.ZERO;
            List<Product> temp = productService.getMyDebtsToFriend(user, friend);
            for(Product prod : temp){
                bilans = bilans.subtract(prod.getPricePerPerson());
            }
            
            temp = productService.getFriendDebtsToMe(user, friend);
            for(Product prod : temp){
                bilans = bilans.add(prod.getPricePerPerson());
            }
            mf.setBilans(bilans);
            friendList.add(mf);
        }
        model.addAttribute("znajomi", friendList);
        model.addAttribute("userId", user.getId());
        
        List<User> invitations = userService.getUserInvitations(user.getId());
        model.addAttribute("invitations", invitations);
        
        return "panel_friends";
    }
    
    @RequestMapping("/addfriend/{id}")
    public String addFriend(Model model, Principal principal, @PathVariable("id") Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        
        User friend = userService.getUser(id);
        
        List<User> friends = userService.getUserFriends(user.getId());        
        List<User> invitations = userService.getUserInvitations(user.getId());
        
        String message;
        
        if(id == user.getId() || friends.contains(friend) || invitations.contains(friend)) {
            message = friend.getName() + " " + friend.getSurname() + " jest już w gronie Twoich znajomych, bądź też wysłaleś już zaproszenie";
        } else {
            List<Friend> friendList = user.getFriends();
            Friend fr = new Friend(user,friend, 0);
            friendList.add(new Friend(user,friend, 0));
            user.setFriends(friendList);
            userService.save(user);
            message = "Wysłano zaproszenie do grona znajomych dla " + friend.getName() + " " + friend.getSurname();
        }
        
        
        model.addAttribute("addingFriendMessage", message);
        
        return "panel_friends_adding";
    }
    
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public String removeFriend(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        
        User friend = userService.getUser(id);
        
        List<Friend> friendsList = user.getFriends();
        
       // friendsList.remove();
        
        return "redirect:/panel/friends";
    }
}