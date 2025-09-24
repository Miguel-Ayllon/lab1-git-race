# Lab 1 Git Race -- Project Report

## Description of Changes
The application was modified to provide dynamic greetings based on the current time of day. The API endpoint `/api/hello` returns a JSON response containing a greeting message and a timestamp. The greeting changes depending on the hour: "Good morning" before 12 PM, "Good afternoon" between 12 PM and 6 PM, and "Good evening" after 6 PM.

The main work involved implementing this dynamic greeting logic in the `HelloController`. Additionally, integration and unit tests were written to verify that the API returns the correct greeting and timestamp under different scenarios.

## Technical Decisions
- Implemented dynamic greetings in the backend instead of static messages to make the application more interactive.
- Used a REST API returning JSON with `message` and `timestamp`, allowing easy testing and future consumption by clients.
- Chose `TestRestTemplate` for integration tests to check HTTP response status, content type, and JSON body.
- Created unit tests for the controller to verify the greeting logic without HTTP requests.

## Learning Outcomes
- Learned how to use `LocalTime` in Spring Boot controllers to implement time-based logic.
- Practiced returning structured JSON responses and validating them with tests.
- Gained experience in both integration testing (`TestRestTemplate`) and unit testing in Spring Boot.
- Learned how to design simple and testable REST endpoints.

## AI Disclosure
### AI Tools Used
- ChatGPT

### AI-Assisted Work
- AI assisted in generating suggestions for some tests and solving import issues in test classes.
- Estimated contribution: AI 20%, My Work 80%.
- All test code and controller logic were reviewed and adapted to match the project requirements.

### Original Work
- Implemented the `HelloController` with dynamic greeting logic and timestamp generation.
- Wrote most of the integration and unit tests to ensure correct API behavior.
- Managed Git workflow and maintained the project structure independently.
