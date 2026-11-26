package items;

abstract public class LibraryItem{
    //i made the libraryitem abstract because we want book,magazine,refbook to implement generic logic//
    private String id;
    private  String title;
    public LibraryItem(String id,String title){
        this.id=id;
        this.title=title;
    }
    //writing getters//
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public boolean isBorrowable(){
        return this instanceof Borrowable;// checking borrowable because ref book is not//
    }
    public  abstract String getType();//every child should implement this//
}
