import java.util.ArrayList;

class User{
    String name;
    int userId;
    int age;
    ArrayList<User> friends;
    User next;

    public User(String name,int userId, int age){
        this.name = name;
        this.userId = userId;
        this.age = age;
        this.friends = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    User head;

    public void addUser(String name,int userId, int age){
        User user = new User(name,userId,age);

        if(head == null){
            head = user;
        }
        else{
            user.next = head;
            head = user;
        }
    }

    public User searchByUserId(int userId) {
        User temp = head;

        while (temp != null) {
            if (temp.userId == userId) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    public void addFriend(int userId, int friendId) {
        User user1 = searchByUserId(userId);
        User user2 = searchByUserId(friendId);

        if (user1 != null || user2 != null) {
            if (user1.friends.contains(user2)) {
                System.out.println("Already have friend");
            }

            user1.friends.add(user2);
            user2.friends.add(user1);
            System.out.println("Friend added");
        }
    }

    public void removeFriend(int userId, int friendId) {
        User user1 = searchByUserId(userId);
        User user2 = searchByUserId(friendId);
        if (user1 != null || user2 != null) {
            if (user1.friends.contains(user2)) {
                user1.friends.remove(user2);
                user2.friends.remove(user1);
                System.out.println("Friend removed");
            }
        } else {
            System.out.println("Friend not found");
        }
    }

    public ArrayList<User> findMutualFriends(int userId, int friendId) {
        ArrayList<User> mutualFriends = new ArrayList<>();
        User user1 = searchByUserId(userId);
        User user2 = searchByUserId(friendId);
        if (user1 != null || user2 != null) {
            for (int i = 0; i < user1.friends.size(); i++) {
                int id = user1.friends.get(i).userId;

                if (user2.friends.contains(id)) {
                    mutualFriends.add(user1.friends.get(i));
                }
            }
        }

        return mutualFriends;
    }

    public void displayFriends(int userId) {
        User user1 = searchByUserId(userId);
        if (user1 == null) {
            System.out.println("User not found with this ID");
            return;
        } else {
            for (int i = 0; i < user1.friends.size(); i++) {
                System.out.println("User ID : " + user1.friends.get(i).userId);
                System.out.println("User Name : " + user1.friends.get(i).name);
                System.out.println("User Age : " + user1.friends.get(i).age);
            }
        }
    }

    public void countFriends(){
        User temp = head;

        while (temp != null) {
            System.out.println(temp.name+ " has " + temp.friends.size() + " friend");
            temp = temp.next;
        }
    }
}
public class SocialMediaManagement {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        System.out.println();
        sm.addUser("Siddharth", 101, 23);
        sm.addUser("Abhi", 102, 23);
        sm.addUser("Kumar", 103, 25);
        sm.addUser("Kuldeep", 104, 29);

        //add friends
        System.out.println();
        sm.addFriend(101, 102);
        sm.addFriend(102, 103);
        sm.addFriend(101,104);
        sm.addFriend(103, 104);
        System.out.println();

        //remove friend
        sm.removeFriend(103, 104);
        System.out.println();

        //display friends
        sm.displayFriends(101);
        sm.displayFriends(102);
        System.out.println();

        //count friends
        sm.countFriends();
    }
}

//Friend added
//Friend added
//Friend added
//Friend added
//
//Friend removed
//
//User ID : 102
//User Name : Abhi
//User Age : 23
//User ID : 104
//User Name : Kuldeep
//User Age : 29
//User ID : 101
//User Name : Siddharth
//User Age : 23
//User ID : 103
//User Name : Kumar
//User Age : 25
//
//Kuldeep has 1 friend
//Kumar has 1 friend
//Abhi has 2 friend
//Siddharth has 2 friend
