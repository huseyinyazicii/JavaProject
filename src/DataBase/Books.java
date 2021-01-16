package DataBase;

public class Books extends BaseBook
{
    private String bookName;
    private String authorName;
    private String numberOfPage;
    private String type;

    public Books(String bookName, String authorName, String numberOfPage, String type)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.numberOfPage = numberOfPage;
        this.type = type;
    }

    @Override
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    @Override
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    @Override
    public String getNumberOfPage() { return numberOfPage; }
    public void setNumberOfPage(String numberOfPage) { this.numberOfPage = numberOfPage; }
    @Override
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
