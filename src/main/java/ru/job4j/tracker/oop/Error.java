package ru.job4j.tracker.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println(this.active);
        System.out.println(this.status);
        System.out.println(this.message);
    }

    public static void main(String[] args) {
        Error err = new Error();
        Error error1 = new Error(true, 404, "Not Found");
        Error error2 = new Error(true, 500, "Internal server error");
        Error error3 = new Error(true, 403, "Forbidden");
        err.printInfo();
        error1.printInfo();
        error2.printInfo();
        error3.printInfo();
    }
}
