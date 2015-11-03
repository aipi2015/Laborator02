package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, Long> personalIdentifier;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, Long> phoneNumber;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> type;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> password;
}
