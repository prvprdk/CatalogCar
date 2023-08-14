# CatalogCar
Initial commit 
Spring Boot PostgresSQL Hibernate
Цель написать справочник автомобилей с возможностью добавления и удаления записей.

https://catalogcar.onrender.com/api/car
https://catalogcar.onrender.com/api/car/stat

package.controller
На данном этапе приложение принимая запрос отдает их нужным методам. Основные методы add, deleteCat, getList

package.service
Далее происходит обращение к сервису для обработки данных и вызова дополнительных методов сортировки или фильтрации по необходимости. 

package.domain
Модель объекта Car для представления в БД описана в одноименном классе. Модель CarStat в БД не хранится и сделана для удобного отображения статистики. 

package.repo
Для доступа к БД и выполнению основных операций CarRepo реализует JpaRepository
