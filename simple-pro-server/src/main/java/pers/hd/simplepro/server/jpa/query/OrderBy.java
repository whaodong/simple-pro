package pers.hd.simplepro.server.jpa.query;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class OrderBy {

    private String property;
    private String direction;

    public OrderBy() {

    }

    public OrderBy(String property, String direction) {
        this.property = property;
        if (!StringUtils.isEmpty(direction)) {
            this.direction = direction;
        } else {
            this.direction = "desc";
        }

    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Sort.Order getOrderBy() {
        return new Sort.Order(Sort.Direction.fromString(this.direction), this.property);
    }
}
