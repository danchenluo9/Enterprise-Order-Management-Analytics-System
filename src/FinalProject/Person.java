/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

/**
 *
 * @author danchenluo
 */
public class Person {
    private String id;
    private String name;

    public Person(String name, String id) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
}
