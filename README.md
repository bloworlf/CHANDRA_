# CHANDRA

**Chandra** is an application that fetches a list of countries and display
* a few information about them.

Submitted by: **Mathurin Bloworlf**

## CHANDRA

The following **required** tasks are completed:

* [X] Fetch a list of countries in JSON format from this [URL](https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json)
* [X] Display all the countries in a RecyclerView ordered by the position they appear in the JSON. In each table cell, show the country's "name", "region", "code" and "capital" in this format:
---------------------------------------

| 							|

| "name", "region" 		"code" 	|

| 							|

| "capital" 				|

| 							|

---------------------------------------
* [X] The user should be able to scroll through the entire list of countries.
* [X] The implementation should be robust
  * [X] handle errors and edge cases
  * [X] support device rotation.


## Constraints

* [X] Use coroutines
* [X] Do not use Compose
* [X] Do not use Dagger


## The following **optional** features are implemented:

* [X] Background color of card is the dominant country's flag's color
  * [X] Displayed text switch from black to white according to the darkness of the background color to provide good contrast
* [X] Used Koin for dependency injection
* [X] Used MVVM design pattern
* [X] Added a search bar to filter out the list of countries
* [X] Toolbar hides/shows on scroll
* [X] Implemented swipe refresh


## Screenshots

![Main](https://github.com/bloworlf/CHANDRA_/blob/main/screens/main.png?raw=true)

![Filter](https://github.com/bloworlf/CHANDRA_/blob/main/screens/filter.png?raw=true)

![Rotation](https://github.com/bloworlf/CHANDRA_/blob/main/screens/rotation.png?raw=true)

![Scroll](https://github.com/bloworlf/CHANDRA_/blob/main/screens/scroll.png?raw=true)