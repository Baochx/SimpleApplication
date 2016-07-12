package com;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;

class Student{

    String firstName;
    String lastName;
    Double grade;
    Double feeDiscount = 0.0;
    Double baseFee = 10000.0;

    public Student(String firstName, String lastName, Double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public void printFee(){
        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);
        System.out.println("The fee after discount: " + newFee);
    }

}


public class PreAndConTest {

   public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer) {
        /**
        *Use the predicate to decide when to update the discount.
        *Use the consumer to update the discount value.
        */
        if ( predicate.test(student)) consumer.accept(student); 
        return student;
    }

    public static void main(String[] args) {
    	updateStudentFee(new Student("Chongxue","Bao",9.5), student -> student.grade > 8.5, student -> student.feeDiscount = 30.0).printFee();
    } 

}