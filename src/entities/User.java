package entities;

public abstract class User {
    private final int userId;
    private String username;
    private String email;

    public User( int userId,String username , String email){
        this.userId=userId;
        this.username=username;
        this.email=email;
    }

    public int getUserId(){ return userId; }
    public String getUsername(){ return username; }
    public String getEmail(){ return email; }
    public void setUsername(String username){ this.username= username;}
    public void setEmail(String email){ this.email= email;}


    public String toString(){
        return "User [userId=" + userId + ", username=" + username + ", email=" + email + "]";
    }
}
