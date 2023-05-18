package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.MovieManager.*;

public class Main {
    public static void main(String[] args) throws IOException {

        MovieManager mm = new MovieManager();

        Scanner input = new Scanner(System.in);

        int choice;
        while (true){
            System.out.println("\nMovie Searcher:\n");
            System.out.println("1. Search movie by title");
            System.out.println("2. Search movie by ID");
            System.out.println("3. Search similar movies by ID");
            System.out.println("4. Compare movie to similars");
            System.out.println("5. External URL finder");
            System.out.println("6. Exit\n");
            System.out.println("Enter your choice!");

            choice = input.nextInt();

            switch(choice){

                case 1:
                    System.out.println("Enter a movie title!");
                    input.nextLine();
                    String title = input.nextLine();

                    List<Movie> movies = mm.SearchByName(title);
                    System.out.println("ID   |   Title   |   Avarage Vote   |   Release Date   |   Genre");
                    for (Movie movie : movies){
                        Movie tmp = SearchByID(movie.getId());
                        mm.WriteMovie(tmp);
                    }
                    break;
                case 2:
                    System.out.println("Enter a movie ID!");
                    input.nextLine();
                    String id = input.nextLine();

                    Movie movie = mm.SearchByID(id);
                    if (movie != null) {
                        System.out.println("ID   |   Title   |   Avarage Vote   |   Release Date   |   Genre");
                        mm.WriteMovie(movie);
                    }
                    break;
                case 3:
                    System.out.println("Enter a movie ID!");
                    input.nextLine();
                    String simId = input.nextLine();

                    List<Movie> similars = mm.SearchSimilarByID(simId);
                    if (similars != null) {
                        System.out.println("ID   |   Title   |   Avarage Vote   |   Release Date   |   Genre");
                        for (Movie m : similars) {
                            Movie tmp = SearchByID(m.getId());
                            mm.WriteMovie(tmp);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter a movie ID!");
                    input.nextLine();
                    String sId = input.nextLine();

                    List<Movie> sims = mm.SearchSimilarByID(sId);
                    if (sims != null) {
                        Movie mov = mm.SearchByID(sId);

                        ClassifyMovie(sims, mov);
                    }
                    break;
                case 5:
                    System.out.println("Enter a movie ID!");
                    input.nextLine();
                    String urls = input.nextLine();
                    Movie tmp = mm.SearchByID(urls);
                    if (tmp != null) {
                        System.out.println(tmp.getTitle() + ":");
                        mm.ExternalIdFinder(urls);
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        }
    }
}
