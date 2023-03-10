import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

public class Library {
    /*
    * The library should have a list of books.
    * The library should have a map of books ISBNs which is linked to the amount of book
    -> (for example: harry potter -> 4 means there are currently 4 harry potter books)
    * The library should have a list of users and a list of librarians.
     */

    private List<Book> bookList;

    private Map<String,Integer> mapBook;
    private List<User> userList;
    private List<Librarian> librarianList;
    private String userToken;
    private String librarianToken;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Map<String, Integer> getMapBook() {
        return mapBook;
    }

    public void setMapBook(Map<String, Integer> mapBook) {
        this.mapBook = mapBook;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Librarian> getLibrarianList() {
        return librarianList;
    }

    public void setLibrarianList(List<Librarian> librarianList) {
        this.librarianList = librarianList;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getLibrarianToken() {
        return librarianToken;
    }

    public void setLibrarianToken(String librarianToken) {
        this.librarianToken = librarianToken;
    }
    //book related functions

    public void addBook(Book book){
        //TODO
        this.bookList.add(book);
    }

    public void removeBook(Book book){
        //TODO
        this.bookList.remove(book);
    }

    public Optional<Book> searchBook(String bookName){

        //TODO
        return this.bookList.stream().filter(c->c.getName().equals(bookName)).findAny();

    }

    public void updateBook(Book book){
        //TODO
        bookList.forEach(c->{
            if(c.getId()==book.getId()){
                c.setName(book.getName()!=null?book.getName():c.getName());
                c.setISBN(book.getISBN()!=null?book.getISBN():c.getISBN());
                c.setYear(book.getYear()!=null?book.getYear():c.getYear());
                c.setAuthor(book.getAuthor()!=null?book.getAuthor():c.getAuthor());
            }
        });
        System.out.println("Book is update");

    }

    public void doesBookExist(Book book){
        //TODO
        Optional<Book> optionalBook =bookList.stream().filter(c->c.getName().equals(book.getName())).findAny();
        if(optionalBook.isPresent()){
            System.out.println("Book Exist");
        }else {
            System.out.println("Book Not Exist");
        }
    }

    public void increaseBook(Book book){
        //TODO
        int countBook=this.mapBook.get(book.getISBN());
        countBook++;
        this.mapBook.put(book.getISBN(),countBook);
        System.out.println("Book is added");
    }

    public void decreaseBook(Book book){
        //TODO
        int countBook=this.mapBook.get(book.getISBN());
        countBook--;
        this.mapBook.put(book.getISBN(),countBook);
        System.out.println("Book is Removed");
    }

    //user related functions

    public void addUser(User user){
        //TODO
        Optional<User> optionalUser=searchUser(user.getUsername());
        if(optionalUser.isPresent()){
            System.out.println("UserName Is Exist");
        }else {
            this.userList.add(user);
            System.out.println("User With UserName :"+user.getUsername()+" has been Created");
        }

    }

    public void removeUser(User user){
        //TODO
        this.userList.remove(user);
    }

    public Optional<User> searchUser(String username){
        //TODO
        return this.userList.stream().filter(c->c.getUsername().equals(username)).findFirst();

    }

    public void updateUser(User user){
        //TODO

        this.userList.forEach(c->{
            if(c.getUsername().equals(user.getUsername())){
                c.setBookList(user.getBookList()!=null?user.getBookList():c.getBookList());
                c.setPassword(user.getPassword()!=null?user.getPassword():c.getPassword());
                ;
            }
        });
        System.out.println("User is update");
    }

    public void doesUserExist(User user){
        //TODO

        Optional<User> optionalUser =userList.parallelStream().filter(c->c.getUsername().equals(user.getUsername())).findFirst();
        if(optionalUser.isPresent()){
            System.out.println("User Exist");
        }else {
            System.out.println("User NOT Exist");
        }
    }

    //librarian related functions

    public void addLibrarian(Librarian librarian){
        //TODO
        this.librarianList.add(librarian);
    }

    public void removeLibrarian(Librarian librarian){
        //TODO
        this.librarianList.remove(librarian);
    }

    public Optional<Librarian> searchLibrarian(String username){
        //TODO
        return this.librarianList.parallelStream().filter(c->c.getUsername().equals(username)).findFirst();

    }

    public void updateLibrarian(Librarian librarian){
        //TODO

        this.librarianList.forEach(c->{
            if(c.getUsername().equals(librarian.getUsername())){
                c.setPassword(librarian.getPassword()!=null?librarian.getPassword():c.getPassword());

            }
        });
        System.out.println("librarian is update");
    }

    public void doesLibrarianExist(Librarian librarian){
        //TODO
        Optional<Librarian> optionalLibrarian =librarianList.parallelStream().filter(c->c.getUsername().equals(librarian.getUsername())).findFirst();
        if(optionalLibrarian.isPresent()){
            System.out.println("librarian Exist");
        }else {
            System.out.println("librarian NOT Exist");
        }
    }

    public String loginUser(String username,String password){
        Optional<User> user =searchUser(username);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            setUserToken("Ab32587DA");
            user.get().setToken("Ab32587DA");

        }else {
            setUserToken("");

        }
        return userToken;

    }
    public  String loginUserLibrarian(String username,String password){
        Optional<Librarian> librarian=searchLibrarian(username);
        if(librarian.isPresent() && librarian.get().getPassword().equals(password)){
            setLibrarianToken("Ab32587DA");
            librarian.get().setToken("Ab32587DA");

        }else {
            setLibrarianToken("");

        }
        return librarianToken;
    }


    public Library(List<Book> bookList, List<User> userList, List<Librarian> librarianList) {
        this.bookList = bookList;
        this.userList = userList;
        this.librarianList = librarianList;
    }
}
