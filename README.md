Introduction
===
A Spring-Boot boilerplate project for building a skeleton web based application. It tries to leverage the latest and
production ready technologies such as using WebSocket as client push, AMQP based  messaging etc. It also tries to
include some best practices of building large scale application.

It has clearly separated modules/layers for the sake of flexibility and scalability.
 * web-frontend : Purely for presentation layer
 * core-services : For domain services and REST API
 * core-models : Persistent layer implementation

This project is just my own journey of learning Spring framework. And hopefully it can provides some start points for other
developers if they are interested in Spring related technologies.


TODO
===
* Shows how to write unit tests using Spring
* Shows how to use Spring security
* .... (A lot of things haven't been decided yet.

Usages
===
* core-services produces a runnable jar. Run it with '--debug' can see a lot of useful information