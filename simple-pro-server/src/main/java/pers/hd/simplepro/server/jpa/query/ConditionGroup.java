package pers.hd.simplepro.server.jpa.query;

import java.util.List;

public class ConditionGroup {

    private List<QueryCondition> conditions;
    private List<ConditionGroup> subConditionGroups;
    private String queryRelation = "AND";

    public ConditionGroup() {

    }

    public ConditionGroup(List<QueryCondition> conditions, List<ConditionGroup> subConditionGroups, String queryRelation) {
        this.conditions = conditions;
        this.subConditionGroups = subConditionGroups;
        this.queryRelation = queryRelation;
    }

    public List<QueryCondition> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<QueryCondition> conditions) {
        this.conditions = conditions;
    }

    public List<ConditionGroup> getSubConditionGroups() {
        return this.subConditionGroups;
    }

    public void setSubConditionGroups(List<ConditionGroup> subConditionGroups) {
        this.subConditionGroups = subConditionGroups;
    }

    public String getQueryRelation() {
        return this.queryRelation;
    }

    public void setQueryRelation(String queryRelation) {
        this.queryRelation = queryRelation;
    }

    public void addCondition(QueryCondition... conditons) {
        for (QueryCondition queryCondition : conditons) {
            this.getConditions().add(queryCondition);
        }
    }

    public void addSubConditionGroup(ConditionGroup... conditonGroups) {
        for (ConditionGroup conditonGroup : conditonGroups) {
            this.getSubConditionGroups().add(conditonGroup);
        }
    }
}
