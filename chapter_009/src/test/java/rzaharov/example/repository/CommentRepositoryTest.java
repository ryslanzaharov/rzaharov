package rzaharov.example.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rzaharov.example.database.DBManager;
import rzaharov.example.models.Comment;
import rzaharov.example.models.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CommentRepositoryTest {

    @BeforeClass
    public static void setUp() {
        DBManager.getInstance().buildSessionFactory();
    }

    @Test
    public void whenAddNewUser() {
        CommentRepository commentRepository = CommentRepository.getInstance();
        Comment comment = new Comment();
        comment.setCommentName("commentName");
        comment.setCommentText("commentText");
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
        commentRepository.add(comment);
        assertThat(comment.getId() != 0, is(true));
    }

    @Test
    public void whenUpdateUserAndGetById() {
        CommentRepository commentRepository = CommentRepository.getInstance();
        Comment comment = new Comment();
        comment.setCommentName("commentName");
        comment.setCommentText("commentText");
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
        commentRepository.add(comment);
        assertThat(comment.getId() != 0, is(true));
        comment.setCommentName("updateName");
        commentRepository.update(comment);
        assertThat(comment.getCommentName(), is(commentRepository.getById(comment.getId()).getCommentName()));
    }

    @Test
    public void delete() {
        CommentRepository commentRepository = CommentRepository.getInstance();
        Comment comment = new Comment();
        comment.setCommentName("commentName");
        comment.setCommentText("commentText");
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
        commentRepository.add(comment);
        assertThat(comment.getId() != 0, is(true));
        commentRepository.delete(comment);
        assertThat(commentRepository.getById(comment.getId()), is(nullValue()));
    }

    @Test
    public void whenGetAllUsers() {
        List<Comment> users = CommentRepository.getInstance().getAll();
        assertThat(users.size() > 0, is(true));
    }

    @AfterClass
    public static void tearDown() {
        DBManager.getInstance().closeSessionFactory();
    }

}