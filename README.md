# Web-Library
This project is imitation of library web-site. All info about books, users, categories are stored in data base. Supports role based authorization (Librarian and Reader) with password encryption. Made with use of general REST principles. Created with usage of Tomcat, SQL and Spring Framework.
## Features
<ul>
  <li> Authentication and registration service</li>
  <li> Find books which are sorted by category</li>
  <li> Create/update books as Librarian</li>
  <li> Create, find and update categories as Librarian</li>
  <li> Find, update users as Librarian</li>
  <li> Ability to create category that attached to specific book</li>
  <li> Ability to display list of all books</li>
  <li> Ability to display list of all categories and users as Librarian</li>
  <li> Support roles with different access to resources (READER and LIBRARIAN)</li>
  <ul>
    <li>LIBRARIAN can get readers info, add/update/delete books, add/update/delete categories, add/update readers</li>
    <li>Readers can register, get list of books, see book atrebutes</li>
  </ul>
</ul>

## Getting Started
<ol>
  <li> Clone this reprositority</li>
  <li> Create schema in your MySql Workbanch</li>
  <li> In <code>resources/db.properties</code> add correspondive data, instead of templates(<code>YOUR_DRIVER</code>, <code>YOUR_DATABASE_URL</code>, <code>YOUR_USERNAME_IN_DATABASE</code>, <code>YOUR_PASSWORD_FOR_DATABASE</code>), for correct SQL connection</li>
  <li> Establish connection to your SQL database (for example using <code>Database</code> option in <code>Intelij Ultimate</code>)</li>
  <li> Configure <code>Apache Tomcat</code></li>
  <li> Launch application</li>
  <li> Copmlete authentication for librarian with data: login <code>librarian@i.ua</code> and password <code>wh40k</code> or for reader: login <code>reader@i.ua</code> and password <code>red40</code>. These users are already injected into our app.</li>
  <li> Navigate throught web page to access it functions </li>
</ol>

## Structure
<ul>
  <li><code>config</code> - contains configuration for Spring (including application context and configuration to beans)</li>
  <li><code>controller</code> - contains endpoints that can resive and handle various HTTP requests</li>
  <ul>
    <li><code>AuthenticationController</code> - for authentication proccess</li>
    <li><code>BookController</code> - for adding books, get list of all books, updating and deleting books.</li>
    <li><code>CategoryController</code> - for adding categories, get list of all categories, updating and deleting categories</li>
    <li><code>HomeController</code> - for accessing home page as librarian and reader.</li>
    <li><code>UserController</code> - for finding readers by id, updating them and getting list of all users.</li>
  </ul>
  <li><code>dao</code> - contains Data Access Object interfaces and their implementations for interaction with data base.</li>
  <li><code>dto</code> - contains Data Transfer Objects that helps to unify requests and responses in the controllers.</li>
  <li><code>exception</code> - contains custom DataProcessingException for handling exceptions in dao layer.</li>
  <li><code>model</code> - contains a models of the objects that the application works with.</li>
  <li><code>service</code> - contains services interface and implementations that are responsible for performing business logic and coordinating the interactions between the controllers and the DAO.</li>
</ul>

## Used technologies
<ul>
  <li>Java <code>v.17.0.5</code></li>
  <li>Maven <code>v.3.8.6</code></li>
  <li>MySql <code>v.8.0.24</code></li>
  <li>Tomcat <code>v.9.0.73</code></li>
  <li>JQUERY <code>v.3.6.4</code></li>
  <li>Spring Framework <code>v.5.3.20</code></li>
  <li>Spring Security<code>v.5.6.10</code></li>
  <li>HTML</li>
</ul>

## Authors
<a href="https://github.com/RandomEastEcho">Gleb Iaroshevych</a>
