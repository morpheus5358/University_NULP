package Lab3.Client;

public record Client(String firstName, String lastName, String phoneNumber, String email) {

    @Override
    public String toString() {
        return "Client: " + firstName + " " + lastName + "    " +
                "Phone number: " + phoneNumber + "    " +
                "Email: " + email;
    }
}