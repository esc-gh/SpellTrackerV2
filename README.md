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

![PM Empty DB Start](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/Empty.png)

### POST to create new spell - */spell*

A request sent with the three required fields to construct a new Spell object in the database

![PM POST Spell](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/Post.png)

This request also has an extra header showing the new spell's location for finding it by name

![PM POST Spell Headers](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/PostHeaders.png)

### PUT to update spell info by ID - */spell?id={id}*

Updates the level and school of the spell using the ID primary key as the address. The response body contains the updated entry

![PM PUT Spell](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/UpdateById.png)

### PUT to update spell info by Name - */spell/{name}*

Updates the level and school of the spell using the name as the address. The response body contains the updated entry

![PM PUT Spell](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/UpdateByName.png)

### DELETE to remove spell by ID - */spell?id={id}*

Removes spell entry from database, returns empty response body on success

![PM DEL Spell](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/DeleteById.png)

### GET by name - */spell/{name}*

Find a spell from its name

![PM GET by name](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/GetByName.png)

### GET by level - */spell/lv/{level}*

Returns a list with all spells of the corresponding level

![PM GET by lv](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/GetByLevel.png)

### GET by school - */spell/school/{school}*

Returns a list with all spells of the corresponding school

![PM GET by school](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/GetBySchool.png)

### Custom Exception to any request by - */spell/{name}*

When no entry can found with a matching name, this exception is returned

![PM Custom Exception](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/SNFE.png)

### GET All spells - */spell* or */spell/name*

Returns a response with all spells, listed alphabetically by name

![PM Get ALL by name](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/GetAll.png)

### GET All spells by level - */spell/lv*

Returns a list of all spells sorted in ascending numerical order of level, then alphabetically

![PM GET ALL by lv](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/GetAllByLevel.png)

### GET All spells by school - */spell/school*

Returns a list of all spells sorted alphabetically by school, then name

![PM GET ALL by school](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/postman/GetAllBySchool.png)

<p align="right">(<a href="#top">back to top</a>)</p>

## Persistent Database

This image shows the database used for the screenshots in this README.md, in MySQL and as a Postman GET request

![SQL PM GET All]()

In this screenshot please note the Spring Boot API running and all table data being shown in MySQL:

![Persistent DB start]()

Here you are able to note that the API has been stopped and a fresh query in MySQL still returns the same data, demonstrating persistence:

![Persistent DB end]()

<p align="right">(<a href="#top">back to top</a>)</p>

## Test Results & Coverage

51 tests are run, none of which fail:

![JUnitTests](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/Tests.png)

This provided testing coverage for */src/main/java/* of **95.7%**:

![TestCoverage](https://github.com/esc-gh/SpellTrackerV2/blob/dev/src/main/resources/images/TestCoverage.png)


<p align="right">(<a href="#top">back to top</a>)</p>

## Jira Board

Please find a link to this project's Jira Board [here](https://syca.atlassian.net/jira/software/projects/STV2/boards/2)!

<p align="right">(<a href="#top">back to top</a>)</p>

## Special Thanks

A special thanks goes to my colleagues on the course and all of the QA trainers so far. They have all helped to make this a rewarding and enjoyable experience!

<p align="right">(<a href="#top">back to top</a>)</p>
