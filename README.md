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


## Countries (/api/countries)
- Using API datasource: "https://api.first.org/data/v1/countries", which provides country code, name, and region
- Extract country code and name from the API response to create a country object
- Display a list of country objects on our own page, formatted as a JSON array of JSON objects
