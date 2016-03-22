package DataAccess.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRegister.class)
public abstract class UserRegister_ {

	public static volatile SingularAttribute<UserRegister, Integer> registerID;
	public static volatile SingularAttribute<UserRegister, Courses> coursesID;
	public static volatile SingularAttribute<UserRegister, User> userID;
	public static volatile SingularAttribute<UserRegister, String> status;

}

