package com.zbq;

public class EnumTest {
    public static void main(String args[]){
        String roleName = "user";
        if(Role.contains(roleName)) {
            switch (Role.valueOf(roleName)) {
                case Admin:
                    System.out.println(Role.Admin.getScope());
                    System.out.println(Role.Admin.ordinal());
                    break;
                case SupperUser:
                    break;
                default:
                    new AssertionError("error");
            }
        }
    }
}
 enum Role{
    Admin("admin","all"),SupperUser("supper","su"),User("user","us");
    String name;
    String scope;

     Role(String _admin, String _all) {
         name  = _admin;
         scope = _all;
     }
     public static boolean contains(String name){
        Role[] roles = values();
        for(Role rl: roles){
            if(rl.name.equals(name)){
                return true;
            }
        }
        return false;
     }
     public String getScope(){
         return scope;
     }
 }