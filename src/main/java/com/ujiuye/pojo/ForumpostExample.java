package com.ujiuye.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForumpostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ForumpostExample() {
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

        public Criteria andForumidIsNull() {
            addCriterion("forumid is null");
            return (Criteria) this;
        }

        public Criteria andForumidIsNotNull() {
            addCriterion("forumid is not null");
            return (Criteria) this;
        }

        public Criteria andForumidEqualTo(Integer value) {
            addCriterion("forumid =", value, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidNotEqualTo(Integer value) {
            addCriterion("forumid <>", value, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidGreaterThan(Integer value) {
            addCriterion("forumid >", value, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidGreaterThanOrEqualTo(Integer value) {
            addCriterion("forumid >=", value, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidLessThan(Integer value) {
            addCriterion("forumid <", value, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidLessThanOrEqualTo(Integer value) {
            addCriterion("forumid <=", value, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidIn(List<Integer> values) {
            addCriterion("forumid in", values, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidNotIn(List<Integer> values) {
            addCriterion("forumid not in", values, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidBetween(Integer value1, Integer value2) {
            addCriterion("forumid between", value1, value2, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumidNotBetween(Integer value1, Integer value2) {
            addCriterion("forumid not between", value1, value2, "forumid");
            return (Criteria) this;
        }

        public Criteria andForumtitleIsNull() {
            addCriterion("forumtitle is null");
            return (Criteria) this;
        }

        public Criteria andForumtitleIsNotNull() {
            addCriterion("forumtitle is not null");
            return (Criteria) this;
        }

        public Criteria andForumtitleEqualTo(String value) {
            addCriterion("forumtitle =", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleNotEqualTo(String value) {
            addCriterion("forumtitle <>", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleGreaterThan(String value) {
            addCriterion("forumtitle >", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleGreaterThanOrEqualTo(String value) {
            addCriterion("forumtitle >=", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleLessThan(String value) {
            addCriterion("forumtitle <", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleLessThanOrEqualTo(String value) {
            addCriterion("forumtitle <=", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleLike(String value) {
            addCriterion("forumtitle like", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleNotLike(String value) {
            addCriterion("forumtitle not like", value, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleIn(List<String> values) {
            addCriterion("forumtitle in", values, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleNotIn(List<String> values) {
            addCriterion("forumtitle not in", values, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleBetween(String value1, String value2) {
            addCriterion("forumtitle between", value1, value2, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumtitleNotBetween(String value1, String value2) {
            addCriterion("forumtitle not between", value1, value2, "forumtitle");
            return (Criteria) this;
        }

        public Criteria andForumcontentIsNull() {
            addCriterion("forumcontent is null");
            return (Criteria) this;
        }

        public Criteria andForumcontentIsNotNull() {
            addCriterion("forumcontent is not null");
            return (Criteria) this;
        }

        public Criteria andForumcontentEqualTo(String value) {
            addCriterion("forumcontent =", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentNotEqualTo(String value) {
            addCriterion("forumcontent <>", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentGreaterThan(String value) {
            addCriterion("forumcontent >", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentGreaterThanOrEqualTo(String value) {
            addCriterion("forumcontent >=", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentLessThan(String value) {
            addCriterion("forumcontent <", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentLessThanOrEqualTo(String value) {
            addCriterion("forumcontent <=", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentLike(String value) {
            addCriterion("forumcontent like", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentNotLike(String value) {
            addCriterion("forumcontent not like", value, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentIn(List<String> values) {
            addCriterion("forumcontent in", values, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentNotIn(List<String> values) {
            addCriterion("forumcontent not in", values, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentBetween(String value1, String value2) {
            addCriterion("forumcontent between", value1, value2, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andForumcontentNotBetween(String value1, String value2) {
            addCriterion("forumcontent not between", value1, value2, "forumcontent");
            return (Criteria) this;
        }

        public Criteria andEmpFk3IsNull() {
            addCriterion("emp_fk3 is null");
            return (Criteria) this;
        }

        public Criteria andEmpFk3IsNotNull() {
            addCriterion("emp_fk3 is not null");
            return (Criteria) this;
        }

        public Criteria andEmpFk3EqualTo(Integer value) {
            addCriterion("emp_fk3 =", value, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3NotEqualTo(Integer value) {
            addCriterion("emp_fk3 <>", value, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3GreaterThan(Integer value) {
            addCriterion("emp_fk3 >", value, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3GreaterThanOrEqualTo(Integer value) {
            addCriterion("emp_fk3 >=", value, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3LessThan(Integer value) {
            addCriterion("emp_fk3 <", value, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3LessThanOrEqualTo(Integer value) {
            addCriterion("emp_fk3 <=", value, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3In(List<Integer> values) {
            addCriterion("emp_fk3 in", values, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3NotIn(List<Integer> values) {
            addCriterion("emp_fk3 not in", values, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3Between(Integer value1, Integer value2) {
            addCriterion("emp_fk3 between", value1, value2, "empFk3");
            return (Criteria) this;
        }

        public Criteria andEmpFk3NotBetween(Integer value1, Integer value2) {
            addCriterion("emp_fk3 not between", value1, value2, "empFk3");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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