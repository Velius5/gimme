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
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velius.model.Product;
import velius.model.User;
import velius.service.ProductService;
import velius.service.UserService;

/**
 *
 * @author Piotr
 */


@Controller
public class FriendsController {
    class ModelFriend{
        private Long id;
        private String name;
        private String surname;
        private String photo;
        private BigDecimal bilans;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        
        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the surname
         */
        public String getSurname() {
            return surname;
        }

        /**
         * @param surname the surname to set
         */
        public void setSurname(String surname) {
            this.surname = surname;
        }

        /**
         * @return the photo
         */
        public String getPhoto() {
            return photo;
        }

        /**
         * @param photo the photo to set
         */
        public void setPhoto(String photo) {
            this.photo = photo;
        }

        /**
         * @return the bilans
         */
        public BigDecimal getBilans() {
            return bilans;
        }

        /**
         * @param bilans the bilans to set
         */
        public void setBilans(BigDecimal bilans) {
            this.bilans = bilans;
        }
    }
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping("/panel/friends")
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
        
        return "panel_friends";
    }
}
