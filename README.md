# Code Checker
**_wschecker_** is the checker component of our [Write2Code](https://github.com/NUIWrite2Code) App.  
It provides the service of:  
* Compiling the JAVA code received from the client side.
* Responding with the compilation result showing whether the code is correct or not.
# Requirements
The checker component was built using the following technologies:
* Programming Language: **Java SDK 12**.
* Framework: **Play v2.8. Web MVC framework** in which we developed the REST web service that offers the functionality of receiving and compiling JAVA code, and retrieves the list of compilation errors as text. 

* We deployed the component in [**Heroku**](https://www.heroku.com/), which is a SaaS (Software as a Service) cloud platform to deploy and host web applications. The deployment process happens through GitHub, using one of the features offered by [**Heroku**](https://www.heroku.com/), which is [deployment of branches on a GitHub repository](https://devcenter.heroku.com/articles/github-integration).

# Example
* URL of the component: https://checkerws.herokuapp.com/
* We recommend you to use [**Postman**](https://www.postman.com) to evaluate the component with real inputs. 
  * Using [**Postman**](https://www.postman.com), 
  * test the component with a parameter named “input”, 
  * and using a **POST** method.
# License
