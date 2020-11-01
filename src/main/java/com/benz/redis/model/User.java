package com.benz.redis.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable{

     
	private static final long serialVersionUID = 3160020895148367751L;
	
	  private String userId;
      private String userName;
      private double salary;
}
