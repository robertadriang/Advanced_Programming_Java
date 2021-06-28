package FII.Laborator11PA;

import FII.Laborator11PA.entity.User;
import FII.Laborator11PA.repository.FriendshipRepository;

import java.util.ArrayList;
import java.util.List;

public class Test {

// Class for testing getTheMostPopularUsers and getTheLeastPopularUsers before implementing the controller;

    public static void main(String[] args)
    {
        FriendshipRepository friendshipRepository = new FriendshipRepository();
        List<User> MostPopularUserList = friendshipRepository.getTheMostPopularUsers(4);
        System.out.println("Most popular users:");
        for(User user : MostPopularUserList)
            System.out.println(user.getUserName());

        System.out.println("\nLeast popular users:");
        List<User> LeastPopularUserList = friendshipRepository.getTheLeastPopularUsers(4);
        for(User user: LeastPopularUserList)
            System.out.println(user.getUserName());
    }
}
