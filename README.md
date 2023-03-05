# MEDPRO 

MEDPRO is a Java Spring Boot application created for doctors, nurses, and medical providers to keep track of patient data.

It is the group capstone project for LaunchCode's Liftoff program. All CRUD operations (Create, Read/Retrieve, Update, and Delete) are demonstrated, which are the four basic functions of persistence storage. 

## Below is GIF of the app:

![MEDPRO]()

**CONTENTS**

- [Technologies Used](#technologies-used)
- [Crud Operations](#CRUD-operations-applying-rESTful-principles)
- [User-Scenarios](#User-Scenarios)
- [Application Flow](#Application-Flow)
- [Data Model and Entity Mapping](#data-model-and-entity-mapping)
- [Database and Back-end](#Database-and-Back-end)
- [Front-end](#Front-end)
- [Additional Features](#Additional-Features)
- [Installation](#installation)
- [About the Developers](#about-the-developers)


## Technologies Used

1. Java
2. Spring Boot
3. Gradle
4. Spring Data JPA
5. Hibernate
6. Javascript
7. HTML
8. CSS
9. Bootstrap 5
10. PostgreSQL
11. REST API

## CRUD Operations applying RESTful Principles
1. POST: Creates a new resource (A patient form can be filled out and saved to the database on form submit)
2. GET: Reads a resource (Patient data can be displayed from the database)
3. PUT: Updates an existing resource (Patient data can be edited and saved to the database)
4. DELETE: Deletes a resource (Patient data can be deleted)

## User Scenarios
The application provides the following user (doctor/provider) scenarios:

1. Creating a doctor/provider account: The doctor/provider (user) needs to create an account before patient information is saved in the back end.

2. Provider Log in - the medical professionals will be able to create a profile and log into their account.

3. Create and Update Records: the medical provider will be able to create and update notes for certain patients.

4. Deleting: Doctors/Providers (users) can delete patient information.

5. Accessing Records: the medical provider will be able to access their patient's profiles/records.

6. New Patient Registration - We need atleast one doctor to be already present.

7. Patient Log in - Patient will be able to log into their account.

8. Creating new appointment, Viewing the list of appointments.

9. Cancel the future appointments if needed.

## Application Flow
MEDPRO is a Health Care app to maintain patient visit updates from the provider side and the appointments from patient side. 
We have 2 different target users in our project. The first and primary user is a medical provider and the other user is a patient. Our application allows the provider to view the scheduled appointments and related patient details. They can also update the diagnostics, prescription, and the provider notes on the appointment visit for a selected patient. The app also allows the patient to schedule an appointment with the primary care provider and add or cancel appointments. 

Authentication: we have separate authentication (login feature) pages to authenticate both users (providers and patients). 

Doctor Flow:
1. New doctor registration
2. Doctor Login/Logout
3. Add Patient  details, diagnostics, prescription
4. Edit/update Patient
5. Delete Patient
6. View Appointments and pull patient record.

Patient Flow:
1. New Patient registration
2. Patient Login/Logout
3. View list of Appointments
4. Add New Appointment
5. Cancel Appointment

## Data Model and Entity Mapping

1. One doctor to many patients (@OneToMany on the Doctor @Entity model)
2. Many patients to one doctor (@ManyToOne on the Patient @Entity model)  
3. One patient can have many appointments (@OneToMany on the Patient @Entity model)
3. One doctor can have many appointments (@OneToMany on the Doctor @Entity model)
  
## Database and Back-end
1. Has at least 3 tables
2. Contains at least 1 one:many relationship between tables
3. Tables contain data
4. Connects to server
5. Server
6. Uses an ORM (such as Hibernate)
7. Uses DAO (Data Access Object) design
8. Handles requests
9. Sends responses
10. Accepts requests from a front-end

## Front-end
1. Web application with at least 3 views
2. Discord bot
3. Command Line Interface

## Additional Features
1.  The application is using a GET request to display data from OpenFDA through its [Drug API Endpoints](https://open.fda.gov/apis/drug/ "OpenFDA Drug API Endpoints").
  2. A dropdown menu allows the provider to select a drug from the OpenFDA Drug API. The drug dropdown is populated with a list of the most common medications seen on the NCLEX-RN exam, which is required for registered nurse licensure in the USA, Canada, and Australia.
  3. The selected drug is saved to the database table on submission of the patient form.
4. The provider can view associated drug information (e.g., adverse reactions, boxed warnings, and dosage and administration) pulled from the [OpenFDA Drug API](https://open.fda.gov/apis/drug/ "OpenFDA Drug API").

## Installation

#### Requirements:

- Java 11
- Spring Boot
- PostgreSQL

To have this app running on your local computer, please follow the below steps:

Clone repository:

```
$ git clone https://github.com/Jan-23-Liftoff-KC/team-lindsay-group-repo.git
```

Create new backend server:

Create a free database server with AWS or use [pgAdmin 4](https://www.pgadmin.org/download/ "pgAdmin 4") on your computer to create and maintain a local postgreSQL database session


Update application properties file:

```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=username
spring.datasource.password=password

# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto=create-drop
logging.level.sql=debug
spring.jpa.show-sql=true

spring.datasource.initialization-mode=always
spring.datasource.initialize=true
spring.datasource.schema=classpath:/schema.sql
spring.datasource.continue-on-error=true
```

Get URI from AWS or use pgAdmin 4 to connect to your localhost. If you're using [pgAdmin 4](https://www.pgadmin.org/download/ "pgAdmin 4"), the URI will look something like this:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
```


Run the file in IntelliJ (or your IDE of choice)


Navigate to `localhost:8080/home.html`

## About the Developers

Kevin Peery is a software engineer and registered nurse in Houston, TX. He holds a bachelor's degree with double majors in business economics and history from the University of Texas at Austin, a Bachelor of Science in Nursing from the University of Texas at Arlington, and an Associate of Science in Computer Science. He has also completed several software engineering immersive programs. Kevin has had previous experience with the Database as a Service (DBAAS) team at a major oil and gas company where he focused on Angular development. He has completed training programs and has coding experience in langauges such as Java, Spring Boot, JavaScript, Angular, React, Ruby, Ruby on Rails, and C#. In addition to software development and active learning, Kevin enjoys running and staying healthy.

Connect with Kevin: 
<p><a href="https://www.linkedin.com/in/kevin-peery/">
  <img
    alt="LinkedIn"
    src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"
  />
</a>
</p>


Connect with Megan: 
<p><a href="">
  <img
    alt="LinkedIn"
    src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"
  />
</a>
</p>

Connect with Ryan: 
<p><a href="">
  <img
    alt="LinkedIn"
    src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"
  />
</a>
</p>

Nisha Rajendran is a stay at home mom who holds a Bachelor Degree in Computer Science and Engineering and has about 10+ years of Software Industry work Experience in Financial Domain. She worked for Fiserv Inc., in India and took a break to provide care for her family and willing to restart her career now. She is continuing to upgrade her skills by learning new technologies and tools. She has completed LC101 web development course with Launchcode in December 2022 to learn technologies like Java, Springboot, Rest API, Javascript, Angular Basics, MYSQL, PostgreSQL, HTML, CSS and Bootstrap.

Connect with Nisha: 
<p><a href="https://www.linkedin.com/in/nisha-rajendran-989603251">
  <img
    alt="LinkedIn"
    src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"
  />
</a>
</p>


Connect with Kavin: 
<p><a href="">
  <img
    alt="LinkedIn"
    src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"
  />
</a>
</p>


