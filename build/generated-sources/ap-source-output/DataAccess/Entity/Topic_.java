package DataAccess.Entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Topic.class)
public abstract class Topic_ {

	public static volatile SingularAttribute<Topic, String> area;
	public static volatile CollectionAttribute<Topic, Courses> coursesCollection;
	public static volatile SingularAttribute<Topic, String> name;
	public static volatile CollectionAttribute<Topic, ExperienceRegister> experienceRegisterCollection;
	public static volatile SingularAttribute<Topic, Integer> id;

}

