public class Book {
    private String title;
    private String author;
    private int copies;
    public Book (String a, String b, int c) {
        title = a;
        author = b;
        copies = c;
    }
    public Book () {
        title = "none";
        author = "none";
        copies = 0;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getCopies() {
        return copies;
    }
    public void setTitle(String a) {
        title = a;
    }
    public void setAuthor(String a) {
        author = a;
    }
    public void setCopies(int a) {
        copies = a;
    }
}