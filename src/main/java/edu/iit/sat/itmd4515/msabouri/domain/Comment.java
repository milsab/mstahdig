/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Milad
 */
@Entity
public class Comment {

    private static final Logger LOG = Logger.getLogger(Comment.class.getName());

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long commentId;

    private String title;
    private String text;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    public Comment() {
    }

    /**
     * Get the value of commentId
     *
     * @return the value of commentId
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * Set the value of commentId
     *
     * @param commentId new value of commentId
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", title=" + title + ", text=" + text + ", createdDate=" + createdDate + '}';
    }

}
