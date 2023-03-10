import java.util.List;

public class User {
    //User should have a list of books
    //User should have a username and a password

    private List<Book> bookList;
    private String username;
    private String password;
    private String token;



    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void rentBook(Book book){
        //TODO
        this.bookList.add(book);
    }

    public void returnBook(Book book){
        //TODO
        this.bookList.remove(book);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(List<Book> bookList, String username, String password) {
        this.bookList = bookList;
        this.username = username;
        this.password = password;
    }
}
