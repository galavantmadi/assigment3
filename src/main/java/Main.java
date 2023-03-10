import java.util.*;

public class Main {
    /*
     * make a functional library app using oop
     * run the main program in Main.java and code the oop part in other classes
     * don't forget to add at least 1 librarian to the library to make it functionable.
     * *  *** don't limit yourself to our template ***
     */

    public static void main(String[] args) {


        List<Book> bookList= new ArrayList<>();
        bookList.add(new Book(1,"Book1","Auther1","2001","1234561"));
        bookList.add(new Book(2,"Book2","Author2","2002","1234562"));
        bookList.add(new Book(3,"Book3","Author3","2003","1234563"));
        bookList.add(new Book(4,"Book4","Author4","2004","1234564"));
        bookList.add(new Book(5,"Book5","Author5","2005","1234565"));
        bookList.add(new Book(6,"Book6","Author6","2006","1234566"));
        bookList.add(new Book(7,"Book7","Author7","2007","1234567"));


        List<User> userList=new ArrayList<>();
        List<Book> bookListUser1= new ArrayList<>();
        bookListUser1.add(new Book(1,"Book1","Auther1","2001","1234561"));
        bookListUser1.add(new Book(2,"Book2","Author2","2002","1234562"));
        User user1=new User(bookListUser1,"User1","Pass1");
        List<Book> bookListUser2= new ArrayList<>();
        bookListUser2.add(new Book(3,"Book3","Author3","2003","1234563"));
        bookListUser2.add(new Book(4,"Book4","Author4","2004","1234564"));
        User user2=new User(bookListUser2,"User2","Pass2");
        userList.add(user1);
        userList.add(user2);



        //fill init value
        Library library=new Library(bookList,userList,new ArrayList<>());
        library.setBookList(bookList);
        library.setUserList(userList);
        Librarian librarian = new Librarian("peyman","p123",library);

        library.addLibrarian(librarian);



        String[] options = {
                "0- Exist Menu",
                "1- Login User",
                "2- LogOut User",
                "3- Borrow Book",
                "4- Return Book",
                "5- Login Librarians",
                "6- LogOut Librarians ",
                "7- Add Book ",
                "8- Remove Book ",
                "9- Create User ",
                "10- Book Report ",
                "11- Librarian Report ",
        };
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option!=0){
            runMenu(options);

            try {
                option = scanner.nextInt();
                switch (option) {

                    case 1:
                        Scanner myObj = new Scanner(System.in);
                        System.out.println("Please UserName :");
                        String userName = myObj.nextLine();
                        System.out.println("Please Password :");
                        String password = myObj.nextLine();
                        String userToken = library.loginUser(userName, password);
                        if (!userToken.equals("")) {
                            System.out.println("Login Success and token is " + userToken);
                            break;
                        }
                        System.out.println("Login Fail");
                        break;
                    case 2:
                        if (library.getUserToken() != null && !library.getUserToken().equals("")) {
                            Optional<User> user = userList.stream().filter(c -> c.getToken().equals(library.getUserToken())).findAny();
                            user.ifPresent(value -> value.setToken(""));
                            user.ifPresent(library::updateUser);
                            library.setUserToken("");
                            System.out.println("LogOut Success");
                            break;
                        } else {
                            System.out.println("The user is not logged in yet");

                        }
                    case 3:
                        if (library.getUserToken() != null && !library.getUserToken().equals("")) {
                            myObj = new Scanner(System.in);
                            Optional<User> user = userList.stream().filter(c -> c.getToken().equals(library.getUserToken())).findAny();
                            if (user.isPresent()) {
                                System.out.println("Login user with username --->" + user.get().getUsername());
                                if (user.get().getBookList().size() > 0) {
                                    System.out.println("previous book list user is  ---->");
                                    user.get().getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }
                            }

                            System.out.println("Please Enter BookName for Rent:");
                            String bookName = myObj.nextLine();
                            Optional<Book> book = library.searchBook(bookName);
                            if (book.isPresent()) {

                                if (user.isPresent()) {
                                    user.get().rentBook(book.get());
                                    System.out.println("update book list user is  ---->");
                                    user.get().getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }

                            } else {
                                System.out.println("Book Not Found");
                            }
                            break;
                        } else {
                            System.out.println("The user is not logged in yet");

                        }
                    case 4:
                        if (library.getUserToken() != null && !library.getUserToken().equals("")) {
                            myObj = new Scanner(System.in);
                            Optional<User> user = userList.stream().filter(c -> c.getToken().equals(library.getUserToken())).findAny();
                            if (user.isPresent()) {
                                System.out.println("Login user with username --->" + user.get().getUsername());
                                if (user.get().getBookList().size() > 0) {
                                    System.out.println("previous book list user is  ---->");
                                    user.get().getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }
                            }
                            System.out.println("Please Enter BookName for Return:");
                            String bookName = myObj.nextLine();
                            Optional<Book> book = library.searchBook(bookName);
                            if (book.isPresent()) {

                                if (user.isPresent()) {
                                    user.get().returnBook(book.get());
                                    System.out.println("update book list user is  ---->");
                                    user.get().getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }

                            } else {
                                System.out.println("Book Not Found");
                            }
                            break;

                        } else {
                            System.out.println("The user is not logged in yet");

                        }
                    case 5:
                        myObj = new Scanner(System.in);
                        System.out.println("Please UserName For Librarian :");
                        String userNameLibrarian = myObj.nextLine();
                        System.out.println("Please Password For Librarian:");
                        String passwordLibrarian = myObj.nextLine();
                        String librarianToken = library.loginUserLibrarian(userNameLibrarian, passwordLibrarian);
                        if (!librarianToken.equals("")) {
                            System.out.println("Librarian Login Success and token is " + librarianToken);
                            break;
                        }
                        System.out.println("Librarian Login Fail");
                        break;
                    case 6:
                        if (library.getLibrarianToken() != null && !library.getLibrarianToken().equals("")) {
                            Optional<Librarian> librarianOptional = library.getLibrarianList().stream().filter(c -> c.getToken().equals(library.getLibrarianToken())).findAny();
                            librarianOptional.ifPresent(value -> value.setToken(""));
                            librarianOptional.ifPresent(library::updateLibrarian);
                            library.setLibrarianToken("");
                            System.out.println("LogOut Librarian Success");
                            break;
                        } else {
                            System.out.println("The Librarian is not logged in yet");

                        }
                        break;
                    case 7:
                        if (library.getLibrarianToken() != null && !library.getLibrarianToken().equals("")) {
                            myObj = new Scanner(System.in);
                            Optional<Librarian> librarianOptional = library.getLibrarianList().stream().filter(c -> c.getToken().equals(library.getLibrarianToken())).findAny();
                            if (librarianOptional.isPresent()) {
                                System.out.println("Login Librarian with username --->" + librarianOptional.get().getUsername());
                                if (library.getBookList().size() > 0) {
                                    System.out.println(" book list  is  ---->");
                                    library.getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }
                                System.out.println("Please Enter Information for Add Book");
                                System.out.println("Please Enter Name :");
                                String bookName = myObj.nextLine();
                                System.out.println("Please Enter Author :");
                                String authorName = myObj.nextLine();
                                System.out.println("Please Enter Year :");
                                String year = myObj.nextLine();
                                System.out.println("Please Enter ISBN :");
                                String ISBN = myObj.nextLine();
                                Book book = new Book(bookName, authorName, year, ISBN);
                                library.addBook(book);

                                if (library.getBookList().size() > 0) {
                                    System.out.println("Update book list  is  ---->");
                                    library.getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }
                            }
                        } else {
                            System.out.println("The Librarian is not logged in yet");

                        }
                        break;
                    case 8:
                        if (library.getLibrarianToken() != null && !library.getLibrarianToken().equals("")) {
                            myObj = new Scanner(System.in);
                            Optional<Librarian> librarianOptional = library.getLibrarianList().stream().filter(c -> c.getToken().equals(library.getLibrarianToken())).findAny();
                            if (librarianOptional.isPresent()) {
                                System.out.println("Login Librarian with username --->" + librarianOptional.get().getUsername());
                                if (library.getBookList().size() > 0) {
                                    System.out.println(" book list  is  ---->");
                                    library.getBookList().forEach(c -> {
                                        System.out.println(c.getName());
                                    });
                                }
                                System.out.println("Please Enter Information for Delete Book");
                                System.out.println("Please Enter Name :");
                                String bookName = myObj.nextLine();
                                Optional<Book> optionalBook = library.getBookList().stream().filter(c -> c.getName().equals(bookName)).findAny();
                                if (optionalBook.isPresent()) {
                                    library.removeBook(optionalBook.get());
                                    if (library.getBookList().size() > 0) {
                                        System.out.println("Update book list  is  ---->");
                                        library.getBookList().forEach(c -> {
                                            System.out.println(c.getName());
                                        });
                                    }
                                }else {
                                    System.out.println("Book Not Found");
                                }
                            }

                        } else {
                            System.out.println("The Librarian is not logged in yet");

                        }
                        break;
                    case 9:
                        myObj = new Scanner(System.in);
                        System.out.println("Welcome to create User");
                        System.out.println("Enter Username :");
                        String username = myObj.nextLine();
                        System.out.println("Enter Password :");
                        String passwordUser = myObj.nextLine();
                        User user=new User(username,passwordUser);
                        library.addUser(user);
                        System.out.println("User List is :");
                        library.getUserList().forEach(c->{
                            System.out.println(c.getUsername());
                        });
                    case 10:
                        System.out.println("Book  Report :");
                        library.getBookList().forEach(c->{
                            System.out.println("Name : "+c.getName()+" Author : "+c.getAuthor()+" Year : "+c.getYear()+" ISBN :"+c.getISBN());
                        });
                    case 11:
                        System.out.println("Librarian  Report :");
                        library.getLibrarianList().forEach(c->{
                            System.out.println("UserName Librarian: "+c.getUsername());
                        });
                    case 12:
                        System.out.println("User  Report :");
                        library.getUserList().forEach(c->{
                            System.out.println("UserName : "+c.getUsername());
                            System.out.println("Books of "+c.getUsername()+" :");
                            c.getBookList().forEach(d->{
                                System.out.println("BookName :" +d.getName());
                            });
                            System.out.println("-----------------------------");
                        });

                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
            catch (Exception ex){
                System.out.println("An unexpected error happened. Please try again");
                scanner.next();
            }
        }

        //runMenu();
    }

    public static void runMenu(String[] options){
        //TODO:

        System.out.println("------------------MAIN MENU-----------------");

        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");



    }
}
