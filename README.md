# Assignment 1

CEN 4072 UO1 - Assignment 1

The Java code represents an online store. You may register users and purchase items with those users. The code base consists of four packages: **clients, exceptions, models, **and** services. **

* The **clients** package contains classes that simulate connections to external dependencies. For example, the BankService simulates a connection to an external bank to charge a user's credit card.
* The **exceptions** package contains classes that extend the Exception class and may be thrown be other classes throughout the code base.
* The **models** package contains simple Java classes that are use the transport data.
* The **services** package contains classes that perform business logic. There are two services classes: **AccountService** and **PurchaseService**.
    * The **AccountService** class is used to manage users registered in the system. The **PurchaseService** is used to allow users to purchase items.