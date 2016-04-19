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
        private String name;
        private String surname;
        private byte[] photo;
        private BigDecimal bilans;

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
        public byte[] getPhoto() {
            return photo;
        }

        /**
         * @param photo the photo to set
         */
        public void setPhoto(byte[] photo) {
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
            mf.setName(friend.getName());
            mf.setSurname(friend.getSurname());
            mf.setPhoto(friend.getImage());
            
            BigDecimal bilans =  BigDecimal.ZERO;
            List<Product> temp = productService.getMyDebtsToFriend(user, friend);
            for(Product prod : temp){
                BigDecimal debug = prod.getPricePerPerson();
                bilans.subtract(debug);
            }
            
            temp = productService.getFriendDebtsToMe(user, friend);
            for(Product prod : temp){
                BigDecimal debug = prod.getPricePerPerson();
                bilans.add(debug);
            }
            mf.setBilans(bilans);
            friendList.add(mf);
        }
        model.addAttribute("znajomi", friendList);
        
        return "friends";
    }
}
