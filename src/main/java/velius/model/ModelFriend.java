
package velius.model;

import java.math.BigDecimal;

/**
 *
 * @author Piotr Czarny
 */
    public class ModelFriend{
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

