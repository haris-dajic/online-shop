# Online Market

Application is set on localhost:8761. You need to start all microservices inside project to fully use application.

In order to start an application, it is required to add following schemas into your local MySql Workbench.

CREATE SCHEMA adminservicedb; <br />
CREATE SCHEMA registrationservicedb; <br />
CREATE SCHEMA marketinfoservicedb; <br />
CREATE SCHEMA shoppingcartservicedb; <br />

CREATE USER 'root' IDENTIFIED BY 'root';
