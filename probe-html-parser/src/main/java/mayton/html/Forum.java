package mayton.html;

public enum Forum {

    ADONET(1,"ADO.NET, LINQ, Entity Framework, NHibernate, DAL, ORM"),
    ASPNET(2,"ASP.NET"),
    JAVA(3,"Java"),
    ONEC(4,"1С"),
    DELPHI(5,"Delphi");

    public final int id;
    public final String name;

    Forum(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
