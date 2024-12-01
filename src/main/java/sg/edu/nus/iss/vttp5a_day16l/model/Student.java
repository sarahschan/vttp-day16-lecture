package sg.edu.nus.iss.vttp5a_day16l.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Student {
    
        @NotBlank(message = "ID is mandatory")
        @Pattern(regexp = "^\\d{3}$", message = "ID must be exactly 3 digits long")
    private String id;

        @NotBlank(message = "Name is mandatory ") 
        @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
        @Pattern(regexp = "^[a-zA-Z@\\-\\s]*$", message = "Name can only contain alphabets, spaces, '@', or '-'")
    private String fullName;

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Please enter a valid email address")
    private String email;

        @NotBlank(message = "Phone number is mandatory")
        @Pattern(regexp = "^\\d{7,}$", message = "Phone number can only contain digits, must be at least 7 digits long")
    private String phone;


    public Student(){

    }

    public Student(String id, String fullName, String email, String phone){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }


    @Override
    public String toString() {
        return id + "," + fullName + "," + email + "," + phone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }

    
}
