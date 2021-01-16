package DataBase;

public interface IBaseManager
{
    void insert(String bookName,String authorName,String numberOfPage,String type, String userName);
    void delete(String bookName);
}
