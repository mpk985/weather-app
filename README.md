# weather-app
A small API proof-of-concept weather application

###Requirements:
- JDK 23
- Local Redis Server

###To Run Locally:
- After installing JDK 23 and local Redis, run the local redis server by typing "redis-server" into a command line
    - Standard configuration will spin up the server on port 6379
- Select bootRun from the Gradle configuration 
- localhost:8080 will be the base API path

###To Test the API:
- Endpoint: /v1/weather
- Method: GET
- No Auth necessary for this demo
- Request Body
    - "location" : "string"
        - Can be Postal Code, City/State, Latitude/Longitude comma separated
    - "date" : "Date"
        - Format "yyyy-MM-dd" example "2025-03-30"
- Response Object
    - id
    - Location
    - CurrentWeather
    - ForecastWeather


