package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowBookManager implements IBaseManager
{
    @Override
    public void insert(String bookName,String authorName,String numberOfPage,String type, String userName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        try{
            connection = helper.getConnection();
            String sql = "insert into borrowbook (BOOK_NAME,AUTHOR_NAME,NUMBEROFPAGE,TYPE,USER_NAME) values (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,bookName);
            statement.setString(2,authorName);
            statement.setString(3,numberOfPage);
            statement.setString(4,type);
            statement.setString(5,userName);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }
    @Override
    public void delete(String bookName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        try{
            connection= helper.getConnection();
            statement = connection.prepareStatement("delete from borrowbook where BOOK_NAME =?");
            statement.setString(1,bookName);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public int borrowBookControl(String bookName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from borrowbook where BOOK_NAME=?");
            statement.setString(1,bookName);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return 0;
            }
            else {
                return 1;
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return 1;
        }
    }

    //Burada ödünç alınan kitap borrowbook tablosundan silinip books tablosuna ekleniyor
    public void borrowBookData(String bookName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("select BOOK_NAME,AUTHOR_NAME,NUMBEROFPAGE,TYPE from borrowbook where BOOK_NAME=?");
            statement.setString(1,bookName.toUpperCase());
            resultSet = statement.executeQuery();
            BooksManager booksManager = new BooksManager();
            while (resultSet.next()){
                booksManager.insertBook(resultSet.getString("BOOK_NAME"),resultSet.getString("AUTHOR_NAME"),resultSet.getString("NUMBEROFPAGE"),resultSet.getString("TYPE"));
                delete(bookName);
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public String userNameControl(String userName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from borrowbook where USER_NAME=?");
            statement.setString(1,userName);
            resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                String borrowBook = resultSet.getString("Book_Name");
                return borrowBook;
            }
            else{
                return "YOK";
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return "YOK";
        }
    }
}