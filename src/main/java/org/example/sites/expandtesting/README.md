# Expand Testing

## Resources
Using API: https://practice.expandtesting.com/notes/api/api-docs/ <br>
Using UI: https://practice.expandtesting.com/notes/app/

## Flow
1. Via API: Run health check of the app
2. Via API: Create a user
3. Via API: Login
4. Via API: Create 2 notes
5. Via selenium: Login
6. Via selenium: Validate 2 notes
7. Via selenium: Delete a note
8. Via selenium: Create a new note
9. Via API: Validate note list is as expected
10. Via API: Update a note
11. Via selenium: Validate updated note
12. Get and update user profile
13. Via API: User logout
14. Via API: Delete user

## TODO
1. UT
2. Logging
3. Run TestNG with maven
4. Test Report with screenshots (see https://www.browserstack.com/docs/automate/selenium/take-screenshots#automatically-capture-screenshots)
5. Modify to spring 3 project
