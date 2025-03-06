package com.humber.restaurant.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "category" )
    private List<MenuItem> menuItems;

    // Default constructor (required by Hibernate)
    public Category() {}
    
    

	public Category(int id, String name, List<MenuItem> menuItems) {
		super();
		this.id = id;
		this.name = name;
		this.menuItems = menuItems;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

   
    
}
