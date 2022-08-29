package Enums;

public class EnumUser {

    public static void main(String[] args) {
        EnumUser enumUser = new EnumUser();
        enumUser.go();
    }

    private void go() {
        sayHi("Chris", Gender.MALE);
        sayHi("Sarah", Gender.FEMALE);
        sayHi("Shade", Gender.OTHER);
    }

    public void sayHi(String name, Gender gender){
        switch(gender){
            case MALE -> System.out.println("Hi Mr " + name);
            case FEMALE -> System.out.println("Hi Ms " + name);
            case OTHER -> System.out.println("Hi " + name);
            case UNKNOWN -> System.out.println("Hi my dear " + name);
        }

    }


}
