# Technical Homework Assessment
## (Coding Question) Bank Balance Sheet Application
Code Assumption
```
 - Class which is implementing requirement is BalancesheetApplicationTests.java
 
 - Code is not thread safe

 - Not 100% test coverage. I would prefer tdd approach but due to time constraints need to change the approach
 
 - Need improvement in unit testing.
 
 - There is no Java Doc. This needs to add.
```
## (SQL Question) Mobile Banking Analysis
### 1. Count the number of new accounts opened per day by people based in London.
```
select START_DATE, count(*) from ACCOUNT_DETAILS where CITY = 'London' group by START_DATE;
```
### 2. Rank accounts in terms of total time spent in the app (highest usage first) but excluding anyone who has been active for less than 5 minutes.
```
select ACCOUNT_NUMBER, sum_daily, RANK() over (order by sum_daily desc) from (select ACCOUNT_NUMBER,  SUM(DAILY_USAGE_MIN) sum_daily from DAILY_ACTIVITY where DAILY_USAGE_MIN > 5  group by ACCOUNT_NUMBER);

```
### 3. Identify how many accounts were opened on '2019-12-01' in Glasgow and then were still active after their first 10 days.
```
select  distinct ad.ACCOUNT_NUMBER from ACCOUNT_DETAILS ad join DAILY_ACTIVITY da on ad.ACCOUNT_NUMBER = da.ACCOUNT_NUMBER 
where ad.CITY = 'Glasgow'
and ad.START_DATE = '2019-12-01'
and datediff(day, ad.START_DATE,  da.DATE) >= 10 
```

*Please note all queries are executed on Snowflake DB*

## (NoSQL Question) NoSQL Mobile Banking Analysis
### 1. Based on the database schema used in Question 2, design a schema for the NoSQL solution that will return the example data in a performant manner and provide an example JSON response that includes all fields from both relational tables for a single account number.
```
Based on given scenario and problem statement, MongoDB will best fit. However if we are looking for more aggregation on data then we might need to think about Columnar database - i.e Cassandra. 

Here Is JSON Schema 
      {
      "ACCOUNT_NUMBER":5678756,
      "START_DATE":"2020-01-01",
      "CITY": "LONDON",
      "DAILY_ACTIVITY" : 
        [
          {"DAILY_USAGE_MIN" : 23, "DATE" : "2020-01-01"},
          {"DAILY_USAGE_MIN" : 13, "DATE" : "2020-01-02"},
          {"DAILY_USAGE_MIN" : 43, "DATE" : "2020-01-03"},
          {"DAILY_USAGE_MIN" : 6,  "DATE" : "2020-01-04"}
        ]
      }
```
### 2. What are the main advantages and disadvantages of using a NoSQL database over a relational database in our third scenario excluding those already listed above?
```
Here are some of the advantages of MongoDB 
  - Schema less 
  - No complex joins - Due to denormalization of data, there is no need of complex joins
  - Ease of scale-out - Like all other NoSQL, its easy to scale horizontally
  - Conversion/mapping of application objects to database objects not needed.

Here are some of the disadvatanges of MongoDB
  - Learning - Mongo does not support native SQL supports so there is learning curve to learn
  - Join and Multi Nested searched are not performant
  - Multiple nodes create complexity of Monitoring and Infrastructure management
```
