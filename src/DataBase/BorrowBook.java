package DataBase;

public class BorrowBook
{
    private String bookName;
    private String authorName;
    private String numberOfPage;
    private String type;
    private String userName;

    public BorrowBook(String bookName, String authorName, String numberOfPage, String type, String userName) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.numberOfPage = numberOfPage;
        this.type = type;
        this.userName = userName;
    }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getNumberOfPage() { return numberOfPage; }
    public void setNumberOfPage(String numberOfPage) { this.numberOfPage = numberOfPage; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
