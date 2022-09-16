# SpellTrackerV2

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#what-are-we-doing-and-why">What are we doing and why?</a> 
    <li><a href="#how-i-expected-the-challenge-to-go">How I expected the challenge to go</a></li>
    <li><a href="#what-went-well--what-went-not-so-well">What went well & not-so-well</a></li>
    <li><a href="#possible-improvements-for-future-revisions-of-the-project">Possible improvements for future revisions of the project</a></li>
    <li><a href="#postman--api-interactions">Postman & API interactions</a></li>
    <li><a href="#persistent-database">Persistent database</a></li>
    <li><a href="#test-results--coverage">Test results & coverage</a></li>
    <li><a href="#jira-board">Jira board</a></li>
    <li><a href="#special-thanks">Special thanks</a></li>
  </ol>
</details>

## What Are We Doing And Why?
As a means of putting into practice the skills which we have learned on this course with [QA](https://www.qa.com/), we are creating individual projects in order to showcase what we have learned. The objective:

>To create an OOP-based web application, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training.

This will be achieved by producing a back-end developed application written in **Java** with a persistent, managed database hosted locally using **MySQL** that is capable of working with API calls by means of **Postman**. Project management will be conducted through **Jira**. Testing will be carried out with **JUnit** and **Mockito**. A functional front-end will be developed using **HTML**, **CSS** and **JavaScript**.

To meet these goals I have decided to make an API which is capable of storing and manipulating some basic information about spells used in the Tabletop Role-Playing Game [Dungeons & Dragons](https://dnd.wizards.com/dungeons-and-dragons/what-is-dd).

### Scope & Minimum Viable Product
* Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features.
* A project management board with full expansion on user stories, acceptance criteria and tasks needed to complete the project.
* A risk assessment which outlines the issues and risks faced during the project timeframe.
*	A relational database, locally or within the Cloud, which is used to persist data for the project. 
*	A functional application ‘back-end’, written in a suitable framework of the language covered in training (Java/Spring Boot), which meets the requirements set on your Scrum Kanban board.
*	A build (.jar) of your application, including any dependencies it might need, produced using an integrated build tool (Maven).
*	A series of API calls designed with postman, used for CRUD functionality. (Create, Read, Update, Delete)
•	A functional ‘front-end’ website which connects to your back-end API.
*	Fully designed test suites for the application you are creating, including both unit and integration tests.

<p align="right">(<a href="#top">back to top</a>)</p>

## How I Expected The Challenge To Go
I entered into this project with some prior experience of building an API to rely upon. This was my first time working on anything front-end related so that was a point of concern.

With that being said, I was looking forward to the challenge and making something which I could be proud of.

<p align="right">(<a href="#top">back to top</a>)</p>

## What Went Well & What Went Not-So-Well?

Even as a solo project, being able to use the Agile methodology in a practical setting was both insightful and motivational.

There proved to be few serious stumbling blocks and the week-long project proceeded at a reasonably regular pace. All software worked as intended, as ultimately did the code. Adapting the project to use a new auto-incrementing Primary Key caused the most trouble, requiring changes throughout all of the code and increased scope in other areas such as testing.  Writing tests can be a taxing process but a high rate of coverage was achieved and it ended up being quite satisfying.

The biggest cause of lost time was solving minor errors in testing and one merging error on GitHub (which was successfully fixed). Having peers to bounce ideas off of and clarify things with is definitely valuable. That and Google! I am reminded of the value of peer programming.

<p align="right">(<a href="#top">back to top</a>)</p>

## Possible Improvements for Future Revisions Of The Project

There are multiple approaches which could be taken to improve upon this project:

* Expanding the Spell.class entity to hold more fields
* Adding additional entities such as a User/Character.class with one-to-many relationships
* Additional CRUD queries to reflect these additions and their relationships
* Improving the front-end UI as experience grows
* Implement new functionality into the front-end
* Work to make sure the UX is good

<p align="right">(<a href="#top">back to top</a>)</p>

## Postman & API Interactions

In this section we will go through the Postman requests which can be sent and the responding outputs fro the API.

### GET to show empty database - */spell*

A simple GET request to show the database starting empty

![PM Empty DB Start](https://user-images.githubusercontent.com/94961352/152522539-0841f820-def6-40e8-a990-91f6c8026536.png)

### POST to create new spell - */spell*

A request sent with the three required fields to construct a new Spell object in the database

![PM POST Spell](https://user-images.githubusercontent.com/94961352/152523237-01611016-1003-4d49-a9f5-3d5a4fdd84f3.png)

This request also has an extra header showing the new spell's location for finding it by name

![PM POST Spell Headers](https://user-images.githubusercontent.com/94961352/152523368-634c505f-e490-4941-96e3-cf96412a0e55.png)

### PUT to update spell info - */spell/{name}*

Updates the level and school of the spell using the name primary key as the address. The response body contains the updated entry

![PM PUT Spell](https://user-images.githubusercontent.com/94961352/152523723-9399aa17-c723-4c40-88b1-528a0e1077a4.png)

### DELETE to remove spell - */spell/{name}*

Removes spell entry from database, returns empty response body on success

![PM DEL Spell](https://user-images.githubusercontent.com/94961352/152524218-24b5a992-267f-4f30-9ffa-2ad095bf9796.png)

### GET by name - */spell/{name}*

Find a spell from its name (PK)

![PM GET by name](https://user-images.githubusercontent.com/94961352/152524768-940977cc-c57d-44c7-a68a-1680d42ae3d5.png)

### GET by level - */spell/lv/{level}*

Returns a list with all spells of the corresponding level

![PM GET by lv](https://user-images.githubusercontent.com/94961352/152525718-a9bc668f-c256-4520-8a60-6e7c3a8e8c6f.png)

### GET by school - */spell/school/{school}*

Returns a list with all spells of the corresponding school

![PM GET by school](https://user-images.githubusercontent.com/94961352/152525831-3ec88296-8ce1-4d73-869d-200738875fa9.png)

### Custom Exception to any request by - */spell/{name}*

When no entry can found with a matching name (PK), this exception is returned

![PM Custom Exception](https://user-images.githubusercontent.com/94961352/152524528-396ecf8c-26bc-4592-93f6-14f7a323d2eb.png)

### GET All spells - */spell* or */spell/name*

Returns a response with all spells, listed alphabetically by name

More data entries have been added at this point, refer to <a href="#persistent-database">Persistent Database</a> section)

![PM Get ALL by name](https://user-images.githubusercontent.com/94961352/152525206-a6d0ac2d-adde-4819-8c90-e0bd77923ee8.png)

### GET All spells by level - */spell/lv*

Returns a list of all spells sorted in ascending numerical order of level, then alphabetically

![PM GET ALL by lv](https://user-images.githubusercontent.com/94961352/152525399-161a4cda-4d36-49a3-8fba-89531ba114b2.png)

### GET All spells by school - */spell/school*

Returns a list of all spells sorted alphabetically by school, then name

![PM GET ALL by school](https://user-images.githubusercontent.com/94961352/152525525-3b80a12a-4087-446c-838b-fafa4fb893fd.png)

<p align="right">(<a href="#top">back to top</a>)</p>

## Persistent Database

This image shows the database used for the screenshots in this README.md, in MySQL and as a Postman GET request

![SQL PM GET All](https://user-images.githubusercontent.com/94961352/152526104-86c86f79-bc16-49a0-a8eb-142dad466601.png)

In this screenshot please note the Spring Boot API running and all table data being shown in MySQL:

![Persistent DB start](https://user-images.githubusercontent.com/94961352/152520044-337d354e-e779-469e-a245-e141e5793151.png)

Here you are able to note that the API has been stopped and a fresh query in MySQL still returns the same data, demonstrating persistence:

![Persistent DB end](https://user-images.githubusercontent.com/94961352/152520068-83ec9ae9-179b-4e44-a5c2-381a6ad60e12.png)

<p align="right">(<a href="#top">back to top</a>)</p>

## Test Results & Coverage

48 tests are run, none of which fail:

![JUnitTests](https://user-images.githubusercontent.com/94961352/152520138-0d5d4170-4e3b-4edd-ba29-469c5571f062.png)

This provided testing coverage for */src/main/java/* of **94%**:

![TestCoverage](https://user-images.githubusercontent.com/94961352/152520148-8e898ebc-83bd-418c-a4f2-f745b73522a8.png)


<p align="right">(<a href="#top">back to top</a>)</p>

## Jira Board

Please find a link to this project's Jira Board [here](https://joshoc.atlassian.net/jira/software/projects/QFP/boards/2)!

<p align="right">(<a href="#top">back to top</a>)</p>

## Special Thanks

This course has been a massively positive experience over the last three months.

I would like to thank all of the QA trainers who have helped and guided me, they have been great. With regards to this final project, I would like to highlight Morgan Walsh in particular for always being happy to field questions and impart his knowledge.

It could never have been as great without all of the other students. It's been a pleasure getting to know the cohort of DfESW7 and I wish all of them the very best in their future endeavours!

<p align="right">(<a href="#top">back to top</a>)</p>
