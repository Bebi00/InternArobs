package Ex3;

public class Author {
    private String name;
    private String email;
    private char gender;

    public Author(String name,String email,char gender){
        this.name = name;
        this.email = email;
        if(gender == 'f'){
            this.gender=gender;
        }
        else if(gender == 'm'){
            this.gender=gender;
        }
        else {
            System.out.println("Gender is not valid.");
        }

    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name+" ("+gender+") at "+email;
    }
}
