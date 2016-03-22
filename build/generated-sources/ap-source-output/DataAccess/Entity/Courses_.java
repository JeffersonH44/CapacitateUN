package DataAccess.Entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Courses.class)
public abstract class Courses_ {

	public static volatile SingularAttribute<Courses, Date> date;
	public static volatile SingularAttribute<Courses, Topic> topicID;
	public static volatile SingularAttribute<Courses, String> name;
	public static volatile SingularAttribute<Courses, Integer> id;
	public static volatile CollectionAttribute<Courses, UserRegister> userRegisterCollection;
	public static volatile SingularAttribute<Courses, User> trainerID;

}

