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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
/**
 *
 * @author Milad
 */
@Entity
public class Comment extends AbstractIdentifiedEntity{

    private static final Logger LOG = Logger.getLogger(Comment.class.getName());

    private String title;
    @NotBlank
    private String text;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    
    private Comment parent;
    
    /**
     *
     */
    public Comment() {
    }

    /**
     *
     * @param title
     * @param text
     * @param createdDate
     */
    public Comment(String title, String text, Date createdDate) {
        this.title = title;
        this.text = text;
        this.createdDate = createdDate;
    }

    /**
     * Helper function to manage One-to-many relationship with Comment
     * @param child
     */
    public void addChild(Comment child){
        if(!this.getChildren().contains(child))
            this.getChildren().add(child);
        if(!child.getParent().equals(this))
            child.setParent(this);
    }
    
    /**
     * Helper function to manage many-to-one relationship with Comment
     * @param parent
     */
    public void addParent(Comment parent){
        if(!this.getParent().equals(parent))
            this.setParent(parent);
        if(!parent.getChildren().contains(this))
            parent.getChildren().add(this);
    }
    
    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + super.getId() + ", title=" + title + ", text=" + text + ", createdDate=" + createdDate + '}';
    }

    /**
     *
     * @return
     */
    public List<Comment> getChildren() {
        return children;
    }

    /**
     *
     * @param children
     */
    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    /**
     *
     * @return
     */
    public Comment getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(Comment parent) {
        this.parent = parent;
    }

}
