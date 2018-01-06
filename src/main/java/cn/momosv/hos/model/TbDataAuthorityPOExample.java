package cn.momosv.hos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbDataAuthorityPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbDataAuthorityPOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdIsNull() {
            addCriterion("Apply_org_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdIsNotNull() {
            addCriterion("Apply_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdEqualTo(String value) {
            addCriterion("Apply_org_id =", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdNotEqualTo(String value) {
            addCriterion("Apply_org_id <>", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdGreaterThan(String value) {
            addCriterion("Apply_org_id >", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("Apply_org_id >=", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdLessThan(String value) {
            addCriterion("Apply_org_id <", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdLessThanOrEqualTo(String value) {
            addCriterion("Apply_org_id <=", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdLike(String value) {
            addCriterion("Apply_org_id like", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdNotLike(String value) {
            addCriterion("Apply_org_id not like", value, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdIn(List<String> values) {
            addCriterion("Apply_org_id in", values, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdNotIn(List<String> values) {
            addCriterion("Apply_org_id not in", values, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdBetween(String value1, String value2) {
            addCriterion("Apply_org_id between", value1, value2, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andApplyOrgIdNotBetween(String value1, String value2) {
            addCriterion("Apply_org_id not between", value1, value2, "applyOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdIsNull() {
            addCriterion("authorize_org_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdIsNotNull() {
            addCriterion("authorize_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdEqualTo(String value) {
            addCriterion("authorize_org_id =", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdNotEqualTo(String value) {
            addCriterion("authorize_org_id <>", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdGreaterThan(String value) {
            addCriterion("authorize_org_id >", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("authorize_org_id >=", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdLessThan(String value) {
            addCriterion("authorize_org_id <", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdLessThanOrEqualTo(String value) {
            addCriterion("authorize_org_id <=", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdLike(String value) {
            addCriterion("authorize_org_id like", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdNotLike(String value) {
            addCriterion("authorize_org_id not like", value, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdIn(List<String> values) {
            addCriterion("authorize_org_id in", values, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdNotIn(List<String> values) {
            addCriterion("authorize_org_id not in", values, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdBetween(String value1, String value2) {
            addCriterion("authorize_org_id between", value1, value2, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andAuthorizeOrgIdNotBetween(String value1, String value2) {
            addCriterion("authorize_org_id not between", value1, value2, "authorizeOrgId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andIsAllowIsNull() {
            addCriterion("is_allow is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowIsNotNull() {
            addCriterion("is_allow is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowEqualTo(Integer value) {
            addCriterion("is_allow =", value, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowNotEqualTo(Integer value) {
            addCriterion("is_allow <>", value, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowGreaterThan(Integer value) {
            addCriterion("is_allow >", value, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_allow >=", value, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowLessThan(Integer value) {
            addCriterion("is_allow <", value, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowLessThanOrEqualTo(Integer value) {
            addCriterion("is_allow <=", value, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowIn(List<Integer> values) {
            addCriterion("is_allow in", values, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowNotIn(List<Integer> values) {
            addCriterion("is_allow not in", values, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowBetween(Integer value1, Integer value2) {
            addCriterion("is_allow between", value1, value2, "isAllow");
            return (Criteria) this;
        }

        public Criteria andIsAllowNotBetween(Integer value1, Integer value2) {
            addCriterion("is_allow not between", value1, value2, "isAllow");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}