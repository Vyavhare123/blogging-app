package com.codewithaniket.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
    
    
	private String title;
    
   
	private String content;
    
    
	private String imageName;
    
   
    private Date addedDate;
    
    @ManyToOne
    private Category category;
    @ManyToOne
    private  User user;
    @OneToMany(mappedBy ="post",cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private Set<Comment> comment=new HashSet<Comment>();
	
	
	
	

}
