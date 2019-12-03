# genesis-confluence-based-employee-leave-tracker
Application to track Employees On leave between a given period with multiple options to get data

## The API capabilities:
The API provides capability to fetch employee data for employess who have registered leaves in Confluence calendar.

### API Endpoints:
| ENDPOINT                                               | METHOD  | DESCRIPTION                                           |
|--------------------------------------------------------|---------|-------------------------------------------------------|
| /leaves/date/{start date}/{end date}                   | GET     | get all employees on leave in between these dates     |
| /employee/{employee id}                                | GET     | get details of the given employee                     |
| /employees                                             | GET     | get all employees registered for leave                |
| /leaves/date/{leave date}                              | GET     | get all employees on leave on given date              |
| /leaves/employee/{employee id}/{start date}/{end date} | GET     | get all leaves of given employee between given dates  |




