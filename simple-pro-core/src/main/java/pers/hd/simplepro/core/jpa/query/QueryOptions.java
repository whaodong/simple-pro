package pers.hd.simplepro.core.jpa.query;

import java.util.List;

public class QueryOptions {

    private ConditionGroup conditionGroup;

    private List<OrderBy> orderByList;

    public ConditionGroup getConditionGroup() {
        return conditionGroup;
    }

    public void setConditionGroup(ConditionGroup conditionGroup) {
        this.conditionGroup = conditionGroup;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<OrderBy> orderByList) {
        this.orderByList = orderByList;
    }
}
