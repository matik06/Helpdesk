/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test;

/**
 *
 * @author matik06
 */
public class Item {
    private Long id;
    private String value;

    public Item(Long id, String value) {
        this.id = id;
        this.value = value;
    }
        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", value=" + value + '}';
    }
}
