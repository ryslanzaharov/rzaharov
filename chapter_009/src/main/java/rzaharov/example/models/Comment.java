package rzaharov.example.models;

import java.sql.Timestamp;

/**
 * @author rzaharov
 * @version 0.1
 * @since 07.06.2018
 *
 * Describe comment model in system.
 */

public class Comment {

    private int id;
    private String commentName;
    private String commentText;
    private Timestamp created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
