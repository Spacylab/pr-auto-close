# PR Auto Close
This project is a spring boot bot made to handle pull requests. 
What it can do is : 
  - Remind you of pending pull requests by sending you a message in slack at a specified time (scheduled cron job)
  - close any pull requests that have been untouched for more than a specified number of days

I did this over a week end for fun and to use it in my 9-5 job ;) 

You can do what ever you want with it :)

### Deployment
* Fork the repo, clone it, u know the drill.
* [Create a slack app](https://api.slack.com/apps) and add a `SLACK_WEBHOOK` in your environment variables.
* Create an access token in gitlab and add a `GITLAB_ACCESS_TOKEN` in your environment variables. 
* Define the projects ids you want to monitor and add them with `GITLAB_PROJECT_IDS` in your environment variables. (projects ids are comma separated with no spaces ex: "id1,id2,id3" )
* Replace the other values in `application.properties` with yours specifications.
* `./mvnw clean install`
* `java  -jar target/pr-auto-close-0.0.1-SNAPSHOT.jar`

### Collaboration

Want to add a feature or fix something? Lesgo my man!

Fork this repo, do stuff and create a pull request.

### Things I might do or you can do

- [ ] add docker support
- [ ] write tests 
- [ ] add interfaces between the Controller and the APIs (so maybe we can add Github support and another messaging app) 
- [ ] add Github support

### Development

* `./mvnw spring-boot:mvn`.
