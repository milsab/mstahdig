/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.msabouri.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Milad
 */
@Entity
public class Comment extends AbstractIdentifiedEntity{

    private static final Logger LOG = Logger.getLogger(Comment.class.getName());

    private String title;
    @NotNull
    private String text;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    
    private Comment parent;
    
    public Comment() {
    }

    public Comment(String title, String text, Date createdDate) {
        this.title = title;
        this.text = text;
        this.createdDate = createdDate;
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
        return "Comment{" + "commentId=" + super.getId() + ", title=" + title + ", text=" + text + ", createdDate=" + createdDate + '}';
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

}
