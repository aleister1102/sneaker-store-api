# Spring Boot with PostgreSQL

## Java
- Get generic type of elements in List: https://stackoverflow.com/questions/1942644/get-generic-type-of-java-util-list

## JPA
- Relationships: https://www.baeldung.com/spring-data-rest-relationships
- Loading initial data: https://www.baeldung.com/spring-boot-data-sql-and-schema-sql

## QueryDSL

The querydsl-apt dependency is an annotation processing tool (APT) — implementation of corresponding Java API that allows the processing of annotations in source files before they move on to the compilation stage. This tool generates the so-called Q-types — classes that directly relate to the entity classes of your application, but are prefixed with the letter Q. For instance, if you have a User class marked with the @Entity annotation in your application, then the generated Q-type will reside in a QUser.java source file.

The querydsl-jpa library is the Querydsl itself, designed to be used together with a JPA application.

Tutorials: 
- https://www.youtube.com/watch?v=f-2DOydkBEU&t=25s
- https://www.baeldung.com/querydsl-with-jpa-tutorial

Related:
- Entity manager: https://www.bezkoder.com/jpa-entitymanager-spring-boot/
- SimplePath: https://www.tabnine.com/code/java/classes/com.querydsl.core.types.dsl.SimplePath


## ObjectMapper

Tutorials:
- https://www.baeldung.com/jackson-object-mapper-tutorial

Docs:
- https://jse.readthedocs.io/en/latest/utils/jackson/jacksonObjectMapper.html

## Tasks

### Task 1

- [x] Implement the predicate builder for nested objects

Questions:
- [x] Is the current usage of the predicate builder correct? - use object mapper to map json string request param into filter object.
- [x] When query, we embed the nested object inside the main object? - with operators like "where", "gt", ...
- [x] I will parse query param to search criteria in LBPredicateBuilder and implement a getObjectPredicate method in LBPredicate? - juts implement a getObjectPredicate method in LBPredicate

### Task 2

- [x] Handle the case when the nested object have operators

### Task 3

- [x] Handle the case for nested array