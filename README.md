Display a scrollable list of repositories of “square” organisation in GitHub.
The app should consist of only one screen (Repositories Screen). Each list
item should have at least the name and description of the repo.

API link: https://api.github.com/orgs/square/repos

# Focused on the junit tests, I used mockk.io library. I covered data and domain layers.
# As we simple app with one module, Hilt it's okay as a library for DI
# Clean Architecture - data, domain, presentation 
# MVVM pattern
# Coroutines (StateFlow

As we have only one screen and small requirement I focused as much as possible on the simplicity and
in the same time defending clean architecture principles. There are several ways to develop and improve
the project if this project will be grown:
1. Navigation. We must carefully plan the navigation between screens, modules. The solution depends on requirements. We can use Cicerone, own navigation or deeplink if we have multi-module structure
2. If we have requirement to store data we can use repository pattern to save data into the local cache or database (or both )
3. To tes business logic of application we can add View Model testing
