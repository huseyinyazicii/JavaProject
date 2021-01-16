package DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BooksManager
{
    public ObservableList<Books> getDataBooks() throws SQLException
    {
        DbHelper helper = new DbHelper();
        Connection connection = helper.getConnection();
        ObservableList<Books> list = FXCollections.observableArrayList();
        PreparedStatement ps = connection.prepareStatement("select BOOK_NAME,AUTHOR_NAME,NUMBEROFPAGE,TYPE from books");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            list.add(new Books(
                    rs.getString("BOOK_NAME"),
                    rs.getString("AUTHOR_NAME"),
                    rs.getString("NUMBEROFPAGE"),
                    rs.getString("TYPE")));
        }
        return list;
    }

    public void insertBook(String bookName,String authorName,String numberOfPage, String type)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        try{
            connection = helper.getConnection();
            String sql = "insert into books (BOOK_NAME,AUTHOR_NAME,NUMBEROFPAGE,TYPE) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,bookName);
            statement.setString(2,authorName);
            statement.setString(3,numberOfPage);
            statement.setString(4,type);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public void deleteBook(String bookName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        try{
            connection= helper.getConnection();
            statement = connection.prepareStatement("delete from books where BOOK_NAME =?");
            statement.setString(1,bookName);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public int bookControl(String borrowBookName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from books where BOOK_NAME=?");
            statement.setString(1,borrowBookName);
            resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return 0;
            }
            else
            {
                return 1;
            }

        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return 1;
        }
    }

    public void booksData(String bookName, String userName)
    {
        Connection connection;
        DbHelper helper = new DbHelper();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("select BOOK_NAME,AUTHOR_NAME,NUMBEROFPAGE,TYPE from books where BOOK_NAME=?");
            statement.setString(1,bookName.toUpperCase());
            resultSet = statement.executeQuery();
            BorrowBookManager borrowBookManager = new BorrowBookManager();
            while (resultSet.next()){
                borrowBookManager.insert(resultSet.getString("BOOK_NAME"),resultSet.getString("AUTHOR_NAME"),resultSet.getString("NUMBEROFPAGE"),resultSet.getString("TYPE"),userName);
                deleteBook(bookName);
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }



    public int numberOfBook()
    {
        int toplam = 0;
        Connection connection;
        DbHelper helper = new DbHelper();
        Statement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select (ID) from books");
            while (resultSet.next()){
                toplam ++;
            }
            return toplam;
        }catch (SQLException exception){
            return toplam;
        }
    }
}