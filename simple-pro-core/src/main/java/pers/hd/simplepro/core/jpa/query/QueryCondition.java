package pers.hd.simplepro.core.jpa.query;

public class QueryCondition {

    private String property;
    private String operator;
    private Object value;
    private Object begin;
    private Object end;

    public QueryCondition(String property, String operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public QueryCondition(String property, String operator, Object begin, Object end) {
        this.property = property;
        this.operator = operator;
        this.begin = begin;
        this.end = end;
    }

    public QueryCondition() {

    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getBegin() {
        return this.begin;
    }

    public void setBegin(Object begin) {
        this.begin = begin;
    }

    public Object getEnd() {
        return this.end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }
}
