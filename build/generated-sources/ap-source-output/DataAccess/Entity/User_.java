package DataAccess.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, Integer> role;
	public static volatile CollectionAttribute<User, Courses> coursesCollection;
	public static volatile CollectionAttribute<User, ExperienceRegister> experienceRegisterCollection;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile CollectionAttribute<User, UserRegister> userRegisterCollection;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> lastname;

}

