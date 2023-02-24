package com.SprinBootMdbMRA.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Student")
public class Student {

	@Id
	private String id;
    private String Name;
    private String RollNo;

	public Student(String id, 
        String Name, 
        String RollNo
    ){
    this.id = id;
	this.Name = Name;
	this.RollNo = RollNo;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public void setName(String Name){
        this.Name = Name;
    }

    public String getName(){
        return this.Name;
    }
    public void setRollNo(String RollNo){
        this.RollNo = RollNo;
    }

    public String getRollNo(){
        return this.RollNo;
    }

    public String toString(){
        return "[id = " + this.id +
                "Name = " + this.Name +
                "RollNo = " + this.RollNo +
            "]";
    }

}
