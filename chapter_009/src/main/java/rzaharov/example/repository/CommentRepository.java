package rzaharov.example.repository;

import org.hibernate.Session;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.Comment;

import java.util.List;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 */

public class CommentRepository extends CommonRepository<Comment> {

    private static final CommentRepository instance = new CommentRepository();
    private DBManager dbManager;
    private CommentRepository() {
        this.dbManager = DBManager.getInstance();
    }

    public static CommentRepository getInstance() {
        return instance;
    }

    public void add(Comment comment) {
        super.execute(Session::save, comment);
    }

    public void update(Comment comment) {
        super.execute(Session::update, comment);
    }

    public void delete(Comment comment) {
        super.execute(Session::delete, comment);
    }

    public Comment getById(Integer id) {
        return super.getById(session -> session.get(Comment.class, id));
    }

    public List<Comment> getAll() {
        return super.getAll(session -> session.createQuery("from Comment").list());
    }
}
