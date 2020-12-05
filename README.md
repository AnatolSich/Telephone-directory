# Telephone-directory

Initial page - http://localhost:8080/

#### HW1

  You need to create a simple Spring MVC application, e.g., a telephone directory. 
  The telephone directory must contain full names and sets of phone numbers. 
  Each user may have several phone numbers, and each number belongs to a specific phone company.

 Your application must include the following functionality:

  1. Implement batch loading of users, phone number and phone company into the system.
  To do this, create a controller which accepts multipart file upload, parses it and stores it in the system.
  The format of the file (JSON, XML, ...) is up to you as long as you can implement the correct parsing procedure.
  2. Implement a controller that will return a list of users as a PDF document.
  Map this controller to a specific value of Accept request header — Accept=application/pdf.
  3. Implement a set of controllers that allow building a simple UI: links to get and upload files and a page with a list of all users.
  Pages must be implemented using Freemarker. Use FreeMarkerViewResolver to view resolving procedure.
  4. Implement a generic exception handler that redirects all controller exceptions to a simple Freemarker view that just prints the exception message.
  You need to configure Spring MVC application context and dispatcher servlet for this assignment.
  5. To store information, you must use any in-memory database (SQLite, H2, Derby...).

#### HW3

1. You need to add a new entity: UserAccount. Each account is associated with a specific phone operator 
and stores the amount of money the user has for operations from this number.
2. Add the method changeMobileOperator, which will allow changing the phone operator and storing the phone number. 
But users have to pay for this operation. If the sum on their account is less than required, the operation is aborted.
3. Configure appropriate PlatformTransactionManager implementation in a Spring application context.
Make changeMobileOperator methods transactional using Spring declarative transactions management (either xml or annotation-based config).