package ua.lviv.lgs;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		
		configuration.configure("hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();

		
		Post post = new Post("Hi2all");
		
		Comment comment1 = new Comment("Andrew");
		comment1.setPost(post);
		
		Comment comment2 = new Comment("Max");
		comment2.setPost(post);

		
		
		Set<Comment> comments = new HashSet<>();
		comments.add(comment1);
		comments.add(comment2);
		
		post.setComment(comments);
		
		
		session.save(post);
		transaction.commit();
		
		Post postDb = (Post) session.get(Post.class, 1);
		System.out.println(postDb + "---->" + postDb.getComment());

		Comment commentDB = (Comment) session.get(Comment.class, 1);
		System.out.println(commentDB + "---->" + commentDB.getPost());
		
		Comment commentDB2 = (Comment) session.get(Comment.class, 2);
		System.out.println(commentDB2 + "---->" + commentDB2.getPost());
				
		session.close();

	}
}
