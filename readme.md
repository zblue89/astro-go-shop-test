# Astro Go Shop - Technical Test

## How to test it?
1. Pull the source code from the repository
2. Build the application with the following command

        mvn clean package 

3. Run the application with the following command:

        java -jar targer/get-movie-title-1.0-SNAPSHOT.jar <movie title>
    
    For example:
    
        java -jar targer/get-movie-title-1.0-SNAPSHOT.jar water

    The `movie title` variable is optional. You may run the application without this variable. For example:
    
        java -jar targer/get-movie-title-1.0-SNAPSHOT.jar 