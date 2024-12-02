# DAY 16 LECTURE

## Students (/students)
- Creating "regular" webapp to create entries of student details
- Connects to local or railway-hosted Redis database
- Student details stored as Strings

### API and REST (/api/students)
- Using Redis as a datasource, StudentRestController displays Student details as JSON Objects
- Specifically, a JSON array of JSON objects
- At /api/students/create, Postman may be used to submit a POST request to create a new student JSON object
    - Submit in the following format:
      
        {
            "id": "002",
            "fullName": "Jane Smith",
            "email": "jane@email.com",
            "phone": "88889999"
        }
- StudentRestService class is a demonstration, does not have functionality in this project