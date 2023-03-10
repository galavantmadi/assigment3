import java.util.List;
import java.util.Optional;

public class Librarian {


    private String username;
    private String password;

    private String token;
    private final Library library;




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

    public Optional<User> searchUser(String username){
        return library.searchUser(username);
        //User user=new
    }

    public void searchLibrarian(String username){
        library.searchLibrarian(username);
    }

    public Optional<Book> searchBooks(String bookName){
        return library.searchBook(bookName);
    }

    public void addUser(User user){
        library.addUser(user);
    }
    public void removeUser(User user){
        library.removeUser(user);
    }
    public void updateUser(User user){
        library.updateUser(user);
    }

    public void addBook(Book book){
        library.addBook(book);
    }
    public void removeBook(Book book){
        library.removeBook(book);
    }
    public void updateBook(Book book){
        library.updateBook(book);
    }

    public void addLibrarian(Librarian librarian){
        library.addLibrarian(librarian);
    }
    public void removeLibrarian(Librarian librarian){
        library.removeLibrarian(librarian);
    }

    public void updateLibrarian(Librarian librarian){
        library.updateLibrarian(librarian);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Librarian(String username, String password, Library library) {
        this.username = username;
        this.password = password;
        this.library = library;
    }
}
