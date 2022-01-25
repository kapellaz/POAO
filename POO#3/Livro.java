
package gerebilioteca;

/**
 * 
class that characterizes a book
 * @author conta
 */
public class Livro {
    private String title;
    private String bookAuthor;

    
    public Livro(String title, String bookAuthor) {
        this.title = title;
        this.bookAuthor = bookAuthor;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    
    
    @Override
    public String toString(){
        return title + " de " + bookAuthor;
    }

}