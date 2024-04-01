# Palestine Map Project

Welcome to the Palestine Map Project! This project is a Java-based program that provides a map of Palestine containing fifteen cities. The program is designed to find the shortest route between two cities using two different algorithms: Uniform Cost Search and A Star.

## Features

- Displays a map of Palestine with fifteen cities marked.
- Utilizes two algorithms, Uniform Cost Search and A Star, to find the shortest route between two cities.
- Provides detailed information about the shortest route found.

## Installation

1. Clone the repository:

2. Open the project in your favorite Java IDE.

3. Compile and run the `mainAIProject.class` file to start the program.

## Usage

1. Upon running the program, you will be presented with a map of Palestine containing fifteen cities.
2. Enter the names of the two cities between which you want to find the shortest route.
3. Choose the algorithm (Uniform Cost Search or A Star) to find the route.
4. The program will display the shortest route along with detailed information such as distance, time, etc.




## About

You have three files: Roads.csv, Cities.csv, and AirDistance.csv<br>

## Cities.csv:
This file is for adding new cities to the map. You can open it to add or delete cities
Just add the city name and the x and y coordinates to the map image like this:

                        city  x y 
                        Jerusalem 329.333 599.33
                        Ramallah 320.0 551.3333
                        Hebron 296.66 697.3
                        Qalqilya 265.333 461.3333
                        Jericho 402.65 583.3


<br><br><br>

## Roads.csv:
To add a road between the two cities, you can enter a new road or delete an existing road to add a road that looks like this<br>
  1: Open the Roads.csv file<br>
      2: Add a new line like this:                   <br>
                              
                              city1  city2 Distance
                              Ramallah Al-Bireh 2.5
                              Ramallah Beitunia 4.1
                              Al-Bireh Sinjil 32.3
                              Al-Bireh Jericho 50.1


<br><br><br>

## AirDistance.csv: 
In order to search through A* Algorithm, you must have the air distance to the two cities that were chosen. In this way, we have large possibilities and data that are difficult to enter. Therefore, I created this file that calculates the air distance by the difference between the lines of longitude and latitude between the cities using This is the method

       public double getDistance(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {

            
             fromLatitude = Math. toRadians(fromLatitude);
             fromLongitude = Math.toRadians(fromLongitude);
             toLatitude = Math. toRadians(toLatitude);
             toLongitude = Math. toRadians(toLongitude);
            
            
             double term1Input = (toLatitude - fromLatitude) / 2;
             double term1 = Math.pow(Math.sin(term1Input), 2);
            
             double term2 = Math.cos(fromLatitude) * Math.cos(toLatitude);
            
             double term3Input = (toLongitude - fromLongitude) / 2;
             double term3 = Math.pow(Math.sin(term3Input), 2);
            
             double combineTerms = term1 + term2 * term3;
            
             double distance = EARTH_RADIUS * 2 * Math.asin(Math.sqrt(combineTerms));
            
             return distance;

       }



You can change the map image and add other cities through these files, but if you want to add any new city, make sure to add it with the same name in all the files mentioned.

## Examples

After running the program, this destination will be cleared for you with dots indicating the fifteen cities. You can add another city or delete it through the cities file.<br>
      1:Now you choose the current city and the target city<br>
      2:You choose the search method<br>
      3:Press the run button<br>
An expressive image of the program interface <br>
![image](https://github.com/khaled7hasan7/ai_project2/assets/131875788/2685330a-a91d-40bb-b2b0-621c062122c8)


## Algorithms

This project implements the following algorithms:

- **Uniform Cost Search**: An algorithm used for traversing a weighted graph or tree to find the cheapest path from a starting node to a goal node.
- **A Star (A*)**: A heuristic search algorithm that is used to find the shortest path between two nodes or vertices in a graph.
 
## Contact

For any inquiries or support, feel free to contact the project maintainer:
- Khaled Hasan Omar
-  <a  href="https://www.linkedin.com/in/khaled-omar-5111672b9">linkedin</a>
- <a href="https://www.instagram.com/khaled7hasan7/?fbclid=IwAR3vHCpHKLOD14C8nwktymileXYeUaSAjea4-Zwdv9qSb8jK619u4lfo3sk"><img src="https://github.com/khaled7hasan7/ai_project2/assets/131875788/b08163b7-207c-43f1-af12-6cb928f28001" width="55">
</a>
 

