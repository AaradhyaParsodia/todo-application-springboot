package com.enablero.todo.utlis;

public class UserEmailContext {
    private static final ThreadLocal<String> currentUserEmail = new ThreadLocal<>();

    public static String getCurrentUserEmail(){
        return currentUserEmail.get();
    }

    public static void setCurrentUserEmail(String email){
        currentUserEmail.set(email);
    }

    public static void clear(){
        currentUserEmail.remove();
    }

}
//DATA will interact with the entity classes