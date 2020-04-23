package ejb;

public interface CurrentUserLocal {
    String getUserFullName();
    void setCurrentUser(User currentUser);

}
